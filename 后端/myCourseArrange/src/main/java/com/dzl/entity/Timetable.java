package com.dzl.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.dzl.ga.Individual;
import lombok.Data;
import java.util.Date;
import java.util.HashMap;

/**
 * timeTable将所有这些对象封装到一个时间表对象中，Timetable类
是至今为止最重量的类。因为它是唯一理解不同的约束彼此之
间应该如何相互交互的一个类
*/
/**
 * 这个类包含一些方法，将教室，时段，教授，学习单元和学生分组添加到时间表中，通过这个方式，
 * Timetable类有双重目的
 * */
@Data
public class Timetable {
        @TableId(type = IdType.AUTO)
    private int timetableId;
    @TableField(exist = false)
    private final HashMap<Integer,Room> rooms;
    @TableField(exist = false)
    private final HashMap<Integer,Professor> professors;
    @TableField(exist = false)
    private final HashMap<Integer,Course> courses;
    @TableField(exist = false)
    private final HashMap<Integer,Team> teams;
    @TableField(exist = false)
    private final HashMap<Integer,Timeslot> timeslots;
    @TableField(exist = false)
    private Class classes[];
    private int numClasses=0;
    //create_time
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //update_time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;//版本号
    @TableLogic
    private  Integer deleted;
    /**
     * 初始化 new Timetable
     */
    public Timetable(int timetableId,int numClasses){
        this.timetableId=timetableId;
        this.numClasses=numClasses;
        this.rooms=new HashMap<Integer, Room>();
        this.professors=new HashMap<Integer, Professor>();
        this.courses=new HashMap<Integer, Course>();
        this.teams=new HashMap<Integer, Team>();
        this.timeslots=new HashMap<Integer, Timeslot>();
        this.classes=new Class[50];
    }
    public Timetable(){

        this.rooms=new HashMap<Integer, Room>();
        this.professors=new HashMap<Integer, Professor>();
        this.courses=new HashMap<Integer, Course>();
        this.teams=new HashMap<Integer, Team>();
        this.timeslots=new HashMap<Integer, Timeslot>();
        this.classes=new Class[50];
    }

    public Timetable(Timetable cloneable){
        this.rooms=cloneable.getRooms();
        this.professors=cloneable.getProfessors();
        this.courses=cloneable.getCourses();
        this.teams=cloneable.getTeams();
        this.timeslots=cloneable.getTimeslots();

    }

    public HashMap<Integer,Team> getTeams(){
        return this.teams;
    }
    public HashMap<Integer,Timeslot> getTimeslots(){
        return this.timeslots;
    }
    public HashMap<Integer,Course> getCourses(){
        return this.courses;
    }
    public HashMap<Integer,Professor> getProfessors(){
        return this.professors;
    }

    /**
     * Add new room
     *
     * @param roomId
     * @param roomName
     * @param capacity
     */
    public void addRoom(int roomId,String roomName,int capacity){
        this.rooms.put(roomId,new Room(roomId,roomName,capacity));
    }
    /**
     * Add new professor
     * @param professorId
     * @param professorName
     */
    public void addProfessor(int professorId,String professorName){
        this.professors.put(professorId,new Professor(professorId,professorName));
    }

    /**
     * Add new course
     * @param courseId
     * @param courseCode
     * @param  course
//     * @param professorIds
     * */
//    public void addCourse(int courseId,String courseCode,String course,int[] professorIds){
//        this.courses.put(courseId,new Course(courseId,courseCode,course,professorIds));
//    }
    public void addCourse(int courseId,String courseCode,String course){
        this.courses.put(courseId,new Course(courseId,courseCode,course));
    }

    public void setProfessorCourse(int courseId,int professorId){
         Course course = this.courses.get(courseId);
         int[] professorIds = course.getProfessorIds();
         int[] newProfessors;
         if (professorIds!=null){
             newProfessors=new int[professorIds.length+1];
             for (int i = 0; i <professorIds.length ; i++) {
                    newProfessors[i]=professorIds[i];
                }
                newProfessors[newProfessors.length-1]=professorId;
         }else {
              newProfessors=new int[]{professorId};
         }

        course.setProfessorIds(newProfessors);
         course.switchProfessorIds(newProfessors);
    }


    /**
     * Add new team
     *
     * @param teamId
     * @param teamSize
//     * @param courseIds
     * */
//    public void addGroup(int groupId,int groupSize,int courseIds[]){
//        this.groups.put(groupId,new Group(groupId,groupSize,courseIds));
//        this.numClasses=0;
//    }

    public void addTeam(int teamId,int teamSize,String teamName){
        this.teams.put(teamId,new Team(teamId,teamSize,teamName));
        this.numClasses=0;
    }
    //设置班级有哪些课程
    public void setTeamCourse(int teamId,int courseId){
        Team team = this.teams.get(teamId);
        int[] courseIds = team.getCourseIds();
        int[] newCourseIds;
        if (courseIds!=null){
            newCourseIds=new int[courseIds.length+1];
            for (int i = 0; i <courseIds.length ; i++) {
                newCourseIds[i]=courseIds[i];
            }
            newCourseIds[newCourseIds.length-1]=courseId ;
        }else {
            newCourseIds=new int[]{courseId};
        }

        team.setCourseIds(newCourseIds);
    }




    /**
     * Add new timeslot
     *
     * @param timeslotId
     * @param timeslot
     * */
    public void addTimeslot(int timeslotId,String timeslot){
        this.timeslots.put(timeslotId,new Timeslot(timeslotId,timeslot));
    }
    /**
     * Create classes using individual's chromosome
     *
     * @param individual
     * */
    public void createClasses(Individual individual){
        //Init classes
        Class classes[]=new Class[this.getNumClasses()];
        //Get individual's chromosome
        int chromosome[]=individual.getChromosome();
        int chromosomePos=0;
        int classIndex=0;

        for (Team team:this.getTeamsAsArray()){
            int courseIds[]=team.getCourseIds();
            for (int courseId:courseIds){
                classes[classIndex]=new Class(classIndex,team.getTeamId(),courseId);

                //Add timeslot

                classes[classIndex].addTimeslot(chromosome[chromosomePos]);
                chromosomePos++;

                //Add room

                classes[classIndex].setRoomId(chromosome[chromosomePos]);
                chromosomePos++;

                //Add professor

                classes[classIndex].addProfessor(chromosome[chromosomePos]);
                chromosomePos++;

                classIndex++;
            }

        }
        this.classes=classes;
    }

    /**
     * Get room from roomId
     *
     * @param  roomId
     * @return room
     * */
    public Room getRoom(int roomId){
        if (!this.rooms.containsKey(roomId)){
            System.out.println("Rooms doesn't contain key"+roomId);
        }
        return (Room)this.rooms.get(roomId);
    }
    public HashMap<Integer,Room> getRooms(){
        return this.rooms;
    }

    /**
     * Get random room
     *
     * @return  room
     * */
    public Room returnRandomRoom(){
        Object[] roomsArray=this.rooms.values().toArray();
        Room room=(Room) roomsArray[(int)(roomsArray.length*Math.random())];
        return room;
    }

    /**
     * Get professor from professorId
     * @param professorId
     * @return professor
     *
     */

    public Professor getProfessor(int professorId){
        return (Professor)this.professors.get(professorId);
    }


    /**
     * Get course from courseId
     *
     * @param  courseId
     * @return course
     * */

    public Course getCourse(int courseId){
        return (Course) this.courses.get(courseId);
    }

    /**
     * Get courseIds of student group
     *
     * @param teamId
     * @return courseId array
     * */
    public int[] getTeamCourses(int teamId){
        Team team =(Team)this.teams.get(teamId);
        return team.getCourseIds();
    }

    /**
     * Get group from groupId
     *
     * @param teamId
     * @return  group
     * */

    public Team getTeam(int teamId){
        return (Team) this.teams.get(teamId);
    }

    /**
     * Get all student groups
     *
     * @return array of groups
     * */

    public Team[] getTeamsAsArray(){
        return (Team[]) this.teams.values().toArray(new Team[this.teams.size()]);
    }

    /**
     * Get timeslot by timeslotId
     * @param timeslotId
     * @return timeslot
     * */

    public Timeslot getTimeslot(int timeslotId){
        return (Timeslot) this.timeslots.get(timeslotId);
    }

    /**
     * Get random timeslotId
     *
     * @return  timeslot
     * */

    public Timeslot returnRandomTimeslot(){
        Object[] timesoltArray=this.timeslots.values().toArray();
        Timeslot timeslot=(Timeslot) timesoltArray[(int)(timesoltArray.length*Math.random())];
        return timeslot;
    }

    /**
     * Get classes
     * @return classes
     * */

    public Class[] getClasses(){
        return this.classes;
    }

    /**
     * Get number of classes that need scheduling
     *
     * @return numClasses
     * */

    public int getNumClasses(){
        if (this.numClasses>0){
            return this.numClasses;
        }

        int numClasses=0;
        Team teams[]=(Team[])this.teams.values().toArray(new Team[this.teams.size()]);
        for (Team team :teams){
            numClasses+=team.getCourseIds().length;
        }
        this.numClasses=numClasses;
        return this.numClasses;
    }
    /**
     * Calculate the number of clashes
     * @return numClashes
     * */
    public int calcClashes(){
        int clashes=0;
        for (Class classA:this.classes){
            //Check room capacity
            int roomCapacity=this.getRoom(classA.getRoomId()).getRoomCapacity();
            int teamSize=this.getTeam(classA.getTeamId()).getTeamSize();
            if (roomCapacity<teamSize){
                clashes++;
            }

            //Check if room is taken
            for (Class classB:this.classes){
                if (classA.getRoomId()==classB.getRoomId()&&classA.getTimeslotId()==classB.getTimeslotId()&&classA.getClassId()!=classB.getClassId()){
                    clashes++;
                    break;
                }
            }
            //Check if professor is available
            for (Class classB:this.classes){
                if (classA.getProfessorId()==classB.getProfessorId()&&classA.getTimeslotId()==classB.getTimeslotId()&&classA.getClassId()!=classB.getClassId()){
                    clashes++;
                    break;
                }
            }

        }
        return clashes;

    }



}
