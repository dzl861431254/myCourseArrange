package com.dzl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dzl.common.R;
import com.dzl.entity.*;
import com.dzl.entity.Class;
import com.dzl.ga.GeneticAlgorithm;
import com.dzl.ga.Population;
import com.dzl.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.schema.Entry;

import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping("/timetable")
@CrossOrigin
public class TimetableController {
    @Autowired
    TimetableService timetableService;
    @Autowired
    CourseService courseService;
    @Autowired
    ClassService classService;
    @Autowired
    RoomService roomService;
    @Autowired
    ProfessorService professorService;
    @Autowired
    TeamService teamService;
   public  Timetable myTimetable;
   public  Class[] myclasses;
    //得到最合适的课程安排的数组后，可以遍历得到对应的时间段应该在课程表的第x个格，然后创建一个数组，在对应的x下标的数组加入当前最好课程的下标，否则为空
    public static int[] c=new int[40];

    //new一个timetable对象，序列化，自动插入timetableid，后面要取出来timetableId
    @GetMapping("create")
    public  void create(){
        Timetable timetable=new Timetable();
        timetableService.save(timetable);

        myTimetable=timetable;


//        myTimetable.addTimeslot(1,"Mon 9:00-11:00");
//        myTimetable.addTimeslot(2,"Mon 11:00-13:00");
//        myTimetable.addTimeslot(3,"Mon 13:00-15:00");
//        myTimetable.addTimeslot(4,"Tue 9:00-11:00");
//        myTimetable.addTimeslot(5,"Tue 11:00-13:00");
//        myTimetable.addTimeslot(6,"Tue 13:00-15:00");
//        myTimetable.addTimeslot(7,"Wed 9:00-11:00");
//        myTimetable.addTimeslot(8,"Wed 11:00-13:00");
//        myTimetable.addTimeslot(9,"Wed 13:00-15:00");
//        myTimetable.addTimeslot(10,"Thu 9:00-11:00");
//        myTimetable.addTimeslot(11,"Thu 11:00-13:00");
//        myTimetable.addTimeslot(12,"Thu 13:00-15:00");
//        myTimetable.addTimeslot(13,"Fri 9:00-11:00");
//        myTimetable.addTimeslot(14,"Fri 11:00-13:00");
//        myTimetable.addTimeslot(15,"Fri 13:00-15:00");


        myTimetable.addTimeslot(1,"Mon 8:30-9:10");
        myTimetable.addTimeslot(2,"Mon 9:20-10:00");
        myTimetable.addTimeslot(3,"Mon 10:20-11:00");
        myTimetable.addTimeslot(4,"Mon 11:10-11:50");
        myTimetable.addTimeslot(5,"Mon 14:00-14:40");
        myTimetable.addTimeslot(6,"Mon 14:50-15:30");
        myTimetable.addTimeslot(7,"Mon 15:40-16:20");
        myTimetable.addTimeslot(8,"Mon 16:30-17:10");
        myTimetable.addTimeslot(9,"Tue 8:30-9:10");
        myTimetable.addTimeslot(10,"Tue 9:20-10:00");
        myTimetable.addTimeslot(11,"Tue 10:20-11:00");
        myTimetable.addTimeslot(12,"Tue 11:10-11:50");
        myTimetable.addTimeslot(13,"Tue 14:00-14:40");
        myTimetable.addTimeslot(14,"Tue 14:50-15:30");
        myTimetable.addTimeslot(15,"Tue 15:40-16:20");
        myTimetable.addTimeslot(16,"Tue 16:30-17:10");
        myTimetable.addTimeslot(17,"Wed 8:30-9:10");
        myTimetable.addTimeslot(18,"Wed 9:20-10:00");
        myTimetable.addTimeslot(19,"Wed 10:20-11:00");
        myTimetable.addTimeslot(20,"Wed 11:10-11:50");
        myTimetable.addTimeslot(21,"Wed 14:00-14:40");
        myTimetable.addTimeslot(22,"Wed 14:50-15:30");
        myTimetable.addTimeslot(23,"Wed 15:40-16:20");
        myTimetable.addTimeslot(24,"Wed 16:30-17:10");
        myTimetable.addTimeslot(25,"Thu 8:30-9:10");
        myTimetable.addTimeslot(26,"Thu 9:20-10:00");
        myTimetable.addTimeslot(27,"Thu 10:20-11:00");
        myTimetable.addTimeslot(28,"Thu 11:10-11:50");
        myTimetable.addTimeslot(29,"Thu 14:00-14:40");
        myTimetable.addTimeslot(30,"Thu 14:50-15:30");
        myTimetable.addTimeslot(31,"Thu 15:40-16:20");
        myTimetable.addTimeslot(32,"Thu 16:30-17:10");
        myTimetable.addTimeslot(33,"Fri 8:30-9:10");
        myTimetable.addTimeslot(34,"Fri 9:20-10:00");
        myTimetable.addTimeslot(35,"Fri 10:20-11:00");
        myTimetable.addTimeslot(36,"Fri 11:10-11:50");
        myTimetable.addTimeslot(37,"Fri 14:00-14:40");
        myTimetable.addTimeslot(38,"Fri 14:50-15:30");
        myTimetable.addTimeslot(39,"Fri 15:40-16:20");
        myTimetable.addTimeslot(40,"Fri 16:30-17:10");


        System.out.println("开始创建");

    }
    //添加教室到timetable中
    @ApiOperation(value="添加教室列表")
    @GetMapping("addRoom/{roomId}")
    public R addRoom(@ApiParam(name = "roomId", value = "教室id", required = true)
                            @PathVariable int roomId){
        final Room room = roomService.getById(roomId);
        if(room==null){
            return R.error().message("出错了");
        }else {
            myTimetable.addRoom(roomId,room.getRoomName(),room.getRoomCapacity());
            return R.ok().message("成功");
        }


    }

    @ApiOperation(value="添加教授列表")
    @GetMapping("addProfessor/{professorId}")
    public R addProfessor(@ApiParam(name = "professorId", value = "教授id", required = true)
                        @PathVariable int  professorId){
        final Professor professor = professorService.getById(professorId);
        if(professor==null){
            return R.error().message("出错了");
        }else {
            myTimetable.addProfessor(professor.getProfessorId(),professor.getProfessorName());
            return R.ok().message("成功");
        }

    }

    @ApiOperation(value="添加课程列表")
    @GetMapping("addCourse/{courseId}")
    public R addCourse(@ApiParam(name = "courseId", value = "课程id", required = true)
                             @PathVariable int courseId){
        final Course course = courseService.getById(courseId);
        if(course==null){
            return R.error().message("出错了");
        }else {
            myTimetable.addCourse(course.getCourseId(),course.getCourseCode(),course.getCourseName());
            return R.ok().message("成功");
        }
    }

    @ApiOperation(value="添加班级列表")
    @GetMapping("addTeam/{teamId}")
    public R addTeam(@ApiParam(name = "teamId", value = "班级id", required = true)
                          @PathVariable int teamId){
        Team team=teamService.getById(teamId);
        if(team==null){
            return R.error().message("出错了");
        }else {
            myTimetable.addTeam(team.getTeamId(),team.getTeamSize(),team.getTeamName());
            return R.ok().message("成功");
        }
    }




    //添加教室到timetable中
    @ApiOperation(value="从时间表中删除教室")
    @GetMapping("deleteRoom/{roomId}")
    public R deleteRoom(@ApiParam(name = "roomId", value = "教室对象id", required = true)
                        @PathVariable int roomId){
        final Room remove = myTimetable.getRooms().remove(roomId);
        if (remove==null){
            return R.error();
        }else{
            return R.ok();
        }

    }

    @ApiOperation(value="从时间表中删除教授")
    @GetMapping("deleteProfessor/{professorId}")
    public R deleteProfessor(@ApiParam(name = "professorId", value = "教授对象id", required = true)
                        @PathVariable int professorId){
        final Professor remove = myTimetable.getProfessors().remove(professorId);
        if (remove==null){
            return R.error();
        }else{
            return R.ok();
        }

    }

    @ApiOperation(value="从时间表中删除课程")
    @GetMapping("deleteCourse/{courseId}")
    public R deleteCourse(@ApiParam(name = "courseId", value = "课程对象id", required = true)
                             @PathVariable int courseId){
        final Course remove = myTimetable.getCourses().remove(courseId);
        if (remove==null){
            return R.error();
        }else{
            return R.ok();
        }

    }

    @ApiOperation(value="从时间表中删除班级")
    @GetMapping("deleteTeam/{teamId}")
    public R deleteTeam(@ApiParam(name = "teamId", value = "班级id", required = true)
                          @PathVariable int teamId){
        final Team remove = myTimetable.getTeams().remove(teamId);
        if (remove==null){
            return R.error();
        }else{
            return R.ok();
        }

    }





    @ApiOperation(value="添加课程的教授列表")
    @GetMapping("setProfessorCourse/{courseId}/{professorId}")
    public void setProfessorCourse( @PathVariable int courseId,@PathVariable int professorId){
        myTimetable.setProfessorCourse(courseId,professorId);

    }

    @ApiOperation(value="添加班级的课程列表")
    @GetMapping("setTeamCourse/{teamId}/{courseId}")
    public void setTeamCourse( @PathVariable int teamId,@PathVariable  int courseId){
        myTimetable.setTeamCourse(teamId,courseId);

    }

    @ApiOperation(value="返回课程列表")
    @GetMapping("getCourses")
    public R getCourses(){
         HashMap<Integer, Course> courses = myTimetable.getCourses();
        courses.put(1,new Course(1,"js","计算机导论",new int[]{1,2}));
        courses.put(2,new Course(2,"java","java",new int[]{1,3}));
        courses.put(3,new Course(3,"c","c++",new int[]{1,2}));
        courses.put(4,new Course(4,"en1","英语课",new int[]{3,4}));
        courses.put(5,new Course(5,"hi1","历史课",new int[]{4}));
        courses.put(6,new Course(6,"ph1","物理课",new int[]{1,4}));


        ArrayList<String> listName=new ArrayList<>();
        ArrayList<Course> list=new ArrayList<>();
        listName.add("课程");
        final Iterator<Map.Entry<Integer, Course>> iterator = courses.entrySet().iterator();
        while (iterator.hasNext()){
            final Map.Entry<Integer, Course> next = iterator.next();
            listName.add(next.getValue().getCourseName());
            list.add(next.getValue());
        }
        return R.ok().data("coursesName",listName).data("courses",list);
    }


    @ApiOperation(value="返回班级列表")
    @GetMapping("getTeams")
    public R getTeams(){
         HashMap<Integer, Team> teams = myTimetable.getTeams();
        teams.put(1,new Team(1,10,"18级一班",new int[]{1,3,4}));
        teams.put(2,new Team(2,30,"18级二班",new int[]{2,3,5,6}));
        teams.put(3,new Team(3,18,"18级三班",new int[]{3,4,5}));
        teams.put(4,new Team(4,25,"18级四班",new int[]{1,4}));
        teams.put(5,new Team(5,20,"18级五班",new int[]{2,3,5}));
        teams.put(6,new Team(6,22,"18级六班",new int[]{1,4,5}));
        teams.put(7,new Team(7,16,"19级一班",new int[]{1,3}));
        teams.put(8,new Team(8,18,"19级二班",new int[]{2,6}));
        teams.put(9,new Team(9,24,"19级三班",new int[]{1,6}));
        teams.put(10,new Team(10,25,"19级四班",new int[]{3,4}));

        ArrayList<String> listName=new ArrayList<>();
        ArrayList<Team> list=new ArrayList<>();
        listName.add("班级");
        final Iterator<Map.Entry<Integer, Team>> iterator = teams.entrySet().iterator();
        while (iterator.hasNext()){
            final Map.Entry<Integer, Team> next = iterator.next();
            listName.add(next.getValue().getTeamName());
            list.add(next.getValue());
        }
        return R.ok().data("teamsName",listName).data("teams",list);
    }


    @ApiOperation(value="返回教授列表")
    @GetMapping("getProfessors")
    public R getProfessors(){
         HashMap<Integer, Professor> professors = myTimetable.getProfessors();
        professors.put(1,new Professor(1,"陈祥春"));
        professors.put(2,new Professor(2,"李小龙"));
        professors.put(3,new Professor(3,"刘德华"));
        professors.put(4,new Professor(4,"张学友"));
        ArrayList<String> listName=new ArrayList<>();
        ArrayList<Professor> list=new ArrayList<>();
        listName.add("教授");
        final Iterator<Map.Entry<Integer, Professor>> iterator = professors.entrySet().iterator();
        while (iterator.hasNext()){
            final Map.Entry<Integer, Professor> next = iterator.next();
            listName.add(next.getValue().getProfessorName());
            list.add(next.getValue());
        }
        return R.ok().data("professorsName",listName).data("professors",list);
    }


    @ApiOperation(value="返回教室列表")
    @GetMapping("getRooms")
    public R getRooms(){
         HashMap<Integer, Room> rooms = myTimetable.getRooms();
        rooms.put(1,new Room(1,"A1",15));
        rooms.put(2,new Room(2,"B1",30));
        rooms.put(3,new Room(3,"C1",20));
        rooms.put(4,new Room(4,"D1",25));
        ArrayList<String> listName=new ArrayList<>();
        ArrayList<Room> list=new ArrayList<>();
        listName.add("教室");
        final Iterator<Map.Entry<Integer, Room>> iterator = rooms.entrySet().iterator();
        while (iterator.hasNext()){
            final Map.Entry<Integer, Room> next = iterator.next();
            listName.add(next.getValue().getRoomName());
            list.add(next.getValue());
        }

        return R.ok().data("rooms",list).data("roomsName",listName);
    }

    @ApiOperation(value="返回班级的课程列表")
    @GetMapping("getTeamCourse/{teamId}")
    public R getTeamCourse(@PathVariable int teamId){
        final Team team = myTimetable.getTeam(teamId);
        final int[] courseIds = team.getCourseIds();
        List<Integer> courses=new ArrayList<>();
        for (int i:courseIds){
            courses.add(i);
        }
        return R.ok().data("teamCourse",courses);
    }


    @ApiOperation(value="返回课程的教授列表")
    @GetMapping("getProfessorCourse/{courseId}")
    public R getProfessorCourse(@PathVariable int courseId){
        final Course course = myTimetable.getCourse(courseId);
        final int[] professorIds = course.getProfessorIds();
        List<Integer> professors=new ArrayList<>();
        for (int i :professorIds){
            professors.add(i);
        }
        return R.ok().data("courseProfessor",professors);
    }



    @GetMapping("initial")
    public  void initialTimetable(){

        //Create timetable
//         myTimetable=new Timetable();
        //Set up rooms
        //todo:插入对应的房间到到当前的课程表，做个插入方法
        myTimetable.addRoom(1,"A1",15);
        myTimetable.addRoom(2,"B1",30);
        myTimetable.addRoom(3,"C1",20);
        myTimetable.addRoom(4,"D1",25);

        //Set up timeslots
        //个人感觉这个到时候可以写死，但为了前期的代码调试就先设置为可改
        myTimetable.addTimeslot(1,"Mon 8:30-9:10");
        myTimetable.addTimeslot(2,"Mon 9:20-10:00");
        myTimetable.addTimeslot(3,"Mon 10:20-11:00");
        myTimetable.addTimeslot(4,"Mon 11:10-11:50");
        myTimetable.addTimeslot(5,"Mon 14:00-14:40");
        myTimetable.addTimeslot(6,"Mon 14:50-15:30");
        myTimetable.addTimeslot(7,"Mon 15:40-16:20");
        myTimetable.addTimeslot(8,"Mon 16:30-17:10");
        myTimetable.addTimeslot(9,"Tue 8:30-9:10");
        myTimetable.addTimeslot(10,"Tue 9:20-10:00");
        myTimetable.addTimeslot(11,"Tue 10:20-11:00");
        myTimetable.addTimeslot(12,"Tue 11:10-11:50");
        myTimetable.addTimeslot(13,"Tue 14:00-14:40");
        myTimetable.addTimeslot(14,"Tue 14:50-15:30");
        myTimetable.addTimeslot(15,"Tue 15:40-16:20");
        myTimetable.addTimeslot(16,"Tue 16:30-17:10");
        myTimetable.addTimeslot(17,"Wed 8:30-9:10");
        myTimetable.addTimeslot(18,"Wed 9:20-10:00");
        myTimetable.addTimeslot(19,"Wed 10:20-11:00");
        myTimetable.addTimeslot(20,"Wed 11:10-11:50");
        myTimetable.addTimeslot(21,"Wed 14:00-14:40");
        myTimetable.addTimeslot(22,"Wed 14:50-15:30");
        myTimetable.addTimeslot(23,"Wed 15:40-16:20");
        myTimetable.addTimeslot(24,"Wed 16:30-17:10");
        myTimetable.addTimeslot(25,"Thu 8:30-9:10");
        myTimetable.addTimeslot(26,"Thu 9:20-10:00");
        myTimetable.addTimeslot(27,"Thu 10:20-11:00");
        myTimetable.addTimeslot(28,"Thu 11:10-11:50");
        myTimetable.addTimeslot(29,"Thu 14:00-14:40");
        myTimetable.addTimeslot(30,"Thu 14:50-15:30");
        myTimetable.addTimeslot(31,"Thu 15:40-16:20");
        myTimetable.addTimeslot(32,"Thu 16:30-17:10");
        myTimetable.addTimeslot(33,"Fri 8:30-9:10");
        myTimetable.addTimeslot(34,"Fri 9:20-10:00");
        myTimetable.addTimeslot(35,"Fri 10:20-11:00");
        myTimetable.addTimeslot(36,"Fri 11:10-11:50");
        myTimetable.addTimeslot(37,"Fri 14:00-14:40");
        myTimetable.addTimeslot(38,"Fri 14:50-15:30");
        myTimetable.addTimeslot(39,"Fri 15:40-16:20");
        myTimetable.addTimeslot(40,"Fri 16:30-17:10");

        //Set up professors
        //todo:添加教师信息
        myTimetable.addProfessor(1,"Dr P Smith");
        myTimetable.addProfessor(2,"Mrs E Mitchell");
        myTimetable.addProfessor(3,"Dr R Williams");
        myTimetable.addProfessor(4,"Mr A Thompson");

        //Set up courses and define the professors that teach them
        //Set up courses and define the professors that teach them
        //todo:添加课程 到时候是将后面的代表教师的数组改成自由添加的那种，显示全部教师的类型
//        timetable.addCourse(1,"cs1","计算机科学",new int[]{1,2});
//        timetable.addCourse(2,"en1","英文课",new int[]{1,3});
//        timetable.addCourse(3,"ma1","数学课",new int[]{1,2});
//        timetable.addCourse(4,"ph1","物理课",new int[]{3,4});
//        timetable.addCourse(5,"hi1","历史课",new int[]{4});
//        timetable.addCourse(6,"dr1","戏剧课",new int[]{1,4});

        //todo:添加课程 到时候是将后面的代表教师的数组改成自由添加的那种，显示全部教师的类型
        myTimetable.addCourse(1,"cs1","计算机科学");
        myTimetable.setProfessorCourse(1,1);
        myTimetable.setProfessorCourse(1,2);
        myTimetable.addCourse(2,"en1","英文课");
        myTimetable.setProfessorCourse(2,1);
        myTimetable.setProfessorCourse(2,3);
        myTimetable.addCourse(3,"ma1","数学课");
        myTimetable.setProfessorCourse(3,1);
        myTimetable.setProfessorCourse(3,2);
        myTimetable.addCourse(4,"ph1","物理课");
        myTimetable.setProfessorCourse(4,3);
        myTimetable.setProfessorCourse(4,4);
        myTimetable.addCourse(5,"hi1","历史课");
        myTimetable.setProfessorCourse(5,4);
        myTimetable.addCourse(6,"dr1","戏剧课");
        myTimetable.setProfessorCourse(6,1);
        myTimetable.setProfessorCourse(6,4);

        //Set up student groups and the courses they take
        //todo:不同的班级有不同的课程，到时候和老师数组一样，要可以自己设置
//        timetable.addGroup(1,10,new int[]{1,3,4});
//        timetable.addGroup(2,30,new int[]{2,3,5,6});
//        timetable.addGroup(3,18,new int[]{3,4,5});
//        timetable.addGroup(4,25,new int[]{1,4});
//        timetable.addGroup(5,20,new int[]{2,3,5});
//        timetable.addGroup(6,22,new int[]{1,4,5});
//        timetable.addGroup(7,16,new int[]{1,3});
//        timetable.addGroup(8,18,new int[]{2,6});
//        timetable.addGroup(9,24,new int[]{1,6});
//        timetable.addGroup(10,25,new int[]{3,4});
        myTimetable.addTeam(1,10,"18级一班");
        myTimetable.setTeamCourse(1,1);
        myTimetable.setTeamCourse(1,3);
        myTimetable.setTeamCourse(1,4);
        myTimetable.addTeam(2,30,"18级二班");
        myTimetable.setTeamCourse(2,2);
        myTimetable.setTeamCourse(2,3);
        myTimetable.setTeamCourse(2,5);
        myTimetable.setTeamCourse(2,6);
        myTimetable.addTeam(3,18,"18级三班");
        myTimetable.setTeamCourse(3,3);
        myTimetable.setTeamCourse(3,4);
        myTimetable.setTeamCourse(3,5);
        myTimetable.addTeam(4,25,"18级四班");
        myTimetable.setTeamCourse(4,1);
        myTimetable.setTeamCourse(4,4);
        myTimetable.addTeam(5,20,"18级五班");
        myTimetable.setTeamCourse(5,2);
        myTimetable.setTeamCourse(5,3);
        myTimetable.setTeamCourse(5,5);
        myTimetable.addTeam(6,22,"18级六班");
        myTimetable.setTeamCourse(6,1);
        myTimetable.setTeamCourse(6,4);
        myTimetable.setTeamCourse(6,5);
        myTimetable.addTeam(7,16,"19级一班");
        myTimetable.setTeamCourse(7,1);
        myTimetable.setTeamCourse(7,3);
        myTimetable.addTeam(8,18,"19级二班");
        myTimetable.setTeamCourse(8,2);
        myTimetable.setTeamCourse(8,6);
        myTimetable.addTeam(9,24,"19级三班");
        myTimetable.setTeamCourse(9,1);
        myTimetable.setTeamCourse(9,6);
        myTimetable.addTeam(10,25,"19级四班");
        myTimetable.setTeamCourse(10,3);
        myTimetable.setTeamCourse(10,4);
     }

     @GetMapping("bestclass")
    public  R getBestClass() throws InterruptedException {
         //TODO:Crete Timetable and initialize with all the available courses,rooms,timeslots,professor,courses,and groups √
         Timetable timetable=myTimetable;

         //Initialize GA
         GeneticAlgorithm ga=new GeneticAlgorithm(100,0.01,0.9,2,5);

         //TODO:Initialize poupulation √
         Population population=ga.initPopulation(timetable);

         //TODO:Evaluate population √
         ga.evalPopulation(population,timetable);
         //Keep track of current generation

         int generation =1;//经历种群的迭代数
         //Start evoluation loop

         //TODO:Add termination condition
         while(ga.isTerminationConditionMet(generation,50)==false&&ga.isTerminationConditionMet(population)==false){
             //print fitness
             System.out.println("第"+generation+"代:当前代最大适应度为: "+population.getFittest(0).getFitness());

             //Apply crossover

             population=ga.crossoverPopulation(population);

             //TODO:Apply mutation
             //Apply mutation
             population=ga.mutatePopulation(population,timetable);

             //Evaluate population
             ga.evalPopulation(population,timetable);
             //Increment the current generation

             generation++;
         }
         //TODO:Print final fitness
         //print fitness

         timetable.createClasses(population.getFittest(0));

         if(population.getFittest(0).getFitness()<1.0){
             System.out.println("无法找到符合当前条件的最优结果");
             timetableService.removeById(myTimetable.getTimetableId());
             return R.error();
         }

         System.out.println();
         System.out.println("最终在第 "+generation+" 找到合适结果");
         System.out.println("Final solution fitness "+generation+" Best fitness"+population.getFittest(0).getFitness());
         System.out.println("Clashes: "+timetable.calcClashes());
         //print classes
         System.out.println();
         Class classes[]=timetable.getClasses();
         myclasses=classes;
         ArrayList<Class> classesList=new ArrayList<>();
         int classIndex=1;
         for (Class bestClass:classes){
             classesList.add(bestClass);
             bestClass.setTimetableId(timetable.getTimetableId());
             System.out.println("Class"+classIndex+":");//最适应课程下标
             System.out.println("Course:"+timetable.getCourse(bestClass.getCourseId()).getCourseName());//课程名：物理课
             System.out.println("Group:"+timetable.getTeam(bestClass.getTeamId()).getTeamName());//班级
             System.out.println("Room"+timetable.getRoom(bestClass.getRoomId()).getRoomName());//课室名称
             System.out.println("Professor:"+timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());//教授名称
             System.out.println("Time:"+timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());//对应的时间段
             System.out.println("第"+bestClass.getTimetableId()+"张表的数据");
             c[bestClass.getTimeslotId()-1]=classIndex;
             System.out.println("-----------");
             classIndex++;
         }
         boolean save = classService.saveBatch(classesList);

         if (save) {
             timetable.setNumClasses(classIndex);
             System.out.println("更新是否成功"+timetableService.updateById(timetable));
             return R.ok();
         } else {
             return R.error();
         }
     }


     public  String getInformation(int id){
        int classIndex=c[id];
        if (classIndex==0){
            return "当前时段无安排";
        }
       String information= myTimetable.getCourse(myclasses[classIndex-1].getCourseId()).getCourseName()+myTimetable.getTeam(myclasses[classIndex-1].getTeamId()).getTeamName()+myTimetable.getRoom(myclasses[classIndex-1].getRoomId()).getRoomName()+myTimetable.getProfessor(myclasses[classIndex-1].getProfessorId()).getProfessorName();
        return information;
     }


     public  R getTimetable(){
        WeekDay[] weekDays=new WeekDay[8];
         for (int i = 0; i <8 ; i++) {
             WeekDay weekDay=new WeekDay();
             weekDays[i]=weekDay;
             weekDays[i].setId(i+1);
             weekDays[i].setMonday(getInformation(i));
             weekDays[i].setTuesday(getInformation(i+8));
             weekDays[i].setWednesday(getInformation(i+16));
             weekDays[i].setThursday(getInformation(i+24));
             weekDays[i].setFriday(getInformation(i+32));
         }
         return R.ok().data("rows", weekDays);


     }


    //根据timetableId获取对应的所有的课程安排
    //条件查询带分页方法
    @ApiOperation(value = "根据timetableId获取对应的所有的课程安排")
    @GetMapping("getTimetableInfo/{id}")
    public R getTimetableInfo(  @ApiParam(name = "timetableId", value = "课程表id", required = true)
                                @PathVariable int id){
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.eq("timetable_id",id);
        List<Class> list = classService.list(wrapper);
        String[] information=new String[40];
        for (Class bestclass:list){
            String courseName=courseService.getById(bestclass.getCourseId()).getCourseName();
                String teamName=teamService.getById(bestclass.getTeamId()).getTeamName();
            String roomName=roomService.getById(bestclass.getRoomId()).getRoomName();
            String professorName=professorService.getById(bestclass.getProfessorId()).getProfessorName();
            if (!StringUtils.isEmpty(information[bestclass.getTimeslotId()-1])){
             information[bestclass.getTimeslotId()-1]=information[bestclass.getTimeslotId()-1]+ courseName + " " + teamName + " " + roomName + " " + professorName;
            }else {
                information[bestclass.getTimeslotId()-1] = courseName + " " + teamName + " " + roomName + " " + professorName;
            }
        }
        WeekDay[] weekDays=new WeekDay[8];
        for (int i = 0; i <8 ; i++) {
            WeekDay weekDay=new WeekDay();
            weekDays[i]=weekDay;
            weekDays[i].setId(i+1);
            weekDays[i].setMonday(information[i]);
            weekDays[i].setTuesday(information[i+8]);
            weekDays[i].setWednesday(information[i+16]);
            weekDays[i].setThursday(information[i+24]);
            weekDays[i].setFriday(information[i+32]);
        }
        return R.ok().data("rows", weekDays);
    }


    @ApiOperation(value = "根据timetableId获取对应的所有的课程安排")
    @GetMapping("getTimetableByTeamId/{teamId}")
    public R getTimetableByTeamId(  @ApiParam(name = "teamId", value = "班级id", required = true)
                                @PathVariable int teamId){
        QueryWrapper<Class> wrapper=new QueryWrapper<>();
        wrapper.groupBy("timetable_id");
        wrapper.eq("team_id",teamId);
         int size = classService.list(wrapper).size();

         HashMap<Integer,List<Class>> map=new HashMap();


        wrapper = new QueryWrapper<>();
        wrapper.eq("team_id",teamId);
         List<Class> classes = classService.list(wrapper);
         List temp=new ArrayList();
         for (int i=1;i<=classes.size();i++){
            if (i%(classes.size()/size)==0){
                temp.add(classes.get(i-1));
                map.put(classes.get(i-1).getTimetableId(),temp);
                temp=new ArrayList();
             }else{
                temp.add(classes.get(i-1));
            }

         }
         for (Map.Entry<Integer, List<Class>> set: map.entrySet()){
             for (Class myClass:set.getValue()){
                 System.out.println("教授名："+professorService.getById(myClass.getProfessorId()).getProfessorName());
            System.out.println("课程名："+courseService.getById(myClass.getCourseId()).getCourseName());
            System.out.println("班级名："+teamService.getById(myClass.getTeamId()).getTeamName());
            System.out.println("教室名: "+roomService.getById(myClass.getRoomId()).getRoomName());
            System.out.println("时间表："+myClass.getTimeslotId());
            System.out.println("课表id："+myClass.getTimetableId());
                 System.out.println("------------");
                 System.out.println();
             }
             System.out.println("xxxxxxxxxxxxxxxx");
             System.out.println();
         }
         HashMap<Integer,WeekDay[]> result=new HashMap<>();
        final Iterator<Integer> iterator = map.keySet().iterator();
         while (iterator.hasNext()){
             int myTimetableId=iterator.next();

             String[] information=new String[40];
             for (Class bestclass:map.get(myTimetableId)){
                 String courseName=courseService.getById(bestclass.getCourseId()).getCourseName();
                 String teamName=teamService.getById(bestclass.getTeamId()).getTeamName();
                 String roomName=roomService.getById(bestclass.getRoomId()).getRoomName();
                 String professorName=professorService.getById(bestclass.getProfessorId()).getProfessorName();
                 if (!StringUtils.isEmpty(information[bestclass.getTimeslotId()-1])){
                     information[bestclass.getTimeslotId()-1]=information[bestclass.getTimeslotId()-1]+ courseName + " " + teamName + " " + roomName + " " + professorName;
                 }else {
                     information[bestclass.getTimeslotId()-1] = courseName + " " + teamName + " " + roomName + " " + professorName;
                 }
             }
             WeekDay[] weekDays=new WeekDay[8];
             for (int i = 0; i <8 ; i++) {
                 WeekDay weekDay=new WeekDay();
                 weekDays[i]=weekDay;
                 weekDays[i].setId(i+1);
                 weekDays[i].setMonday(information[i]);
                 weekDays[i].setTuesday(information[i+8]);
                 weekDays[i].setWednesday(information[i+16]);
                 weekDays[i].setThursday(information[i+24]);
                 weekDays[i].setFriday(information[i+32]);
             }
             result.put(myTimetableId,weekDays);
         }

         return R.ok().data("result",result).data("size",result.size());

    }


    @ApiOperation(value = "根据timetableId获取对应的所有的课程安排")
    @GetMapping("getTimetableByProfessorName/{p_Name}")
    public R getTimetableByProfessorName(  @ApiParam(name = "professorName", value = "教授姓名", required = true)
                                    @PathVariable String p_Name){

         long professorId = professorService.getProfessorId(p_Name);
        System.out.println("教授id   "+professorId);
//        Professor professor = professorService.getOne(professorWrapper);
//         if(professor==null){
//             return R.error().message("查无此人");
//         }
//         int professorId = professor.getProfessorId();

        QueryWrapper<Class> wrapper=new QueryWrapper<>();
        wrapper.groupBy("timetable_id");
        wrapper.eq("professor_id",professorId);
        int size = classService.list(wrapper).size();

        HashMap<Integer,List<Class>> map=new HashMap();


        wrapper = new QueryWrapper<>();
        wrapper.eq("professor_id",professorId);
        List<Class> classes = classService.list(wrapper);
        List temp=new ArrayList();
        for (int i=1;i<=classes.size();i++){
            if (i%(classes.size()/size)==0){
                temp.add(classes.get(i-1));
                map.put(classes.get(i-1).getTimetableId(),temp);
                temp=new ArrayList();
            }else{
                temp.add(classes.get(i-1));
            }

        }
        for (Map.Entry<Integer, List<Class>> set: map.entrySet()){
            for (Class myClass:set.getValue()){
                System.out.println("教授名："+professorService.getById(myClass.getProfessorId()).getProfessorName());
                System.out.println("课程名："+courseService.getById(myClass.getCourseId()).getCourseName());
                System.out.println("班级名："+teamService.getById(myClass.getTeamId()).getTeamName());
                System.out.println("教室名: "+roomService.getById(myClass.getRoomId()).getRoomName());
                System.out.println("时间表："+myClass.getTimeslotId());
                System.out.println("课表id："+myClass.getTimetableId());
                System.out.println("------------");
                System.out.println();
            }
            System.out.println("xxxxxxxxxxxxxxxx");
            System.out.println();
        }
        HashMap<Integer,WeekDay[]> result=new HashMap<>();
        final Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            int myTimetableId=iterator.next();

            String[] information=new String[40];
            for (Class bestclass:map.get(myTimetableId)){
                String courseName=courseService.getById(bestclass.getCourseId()).getCourseName();
                String teamName=teamService.getById(bestclass.getTeamId()).getTeamName();
                String roomName=roomService.getById(bestclass.getRoomId()).getRoomName();
                String professorName=professorService.getById(bestclass.getProfessorId()).getProfessorName();
                if (!StringUtils.isEmpty(information[bestclass.getTimeslotId()-1])){
                    information[bestclass.getTimeslotId()-1]=information[bestclass.getTimeslotId()-1]+ courseName + " " + teamName + " " + roomName + " " + professorName;
                }else {
                    information[bestclass.getTimeslotId()-1] = courseName + " " + teamName + " " + roomName + " " + professorName;
                }
            }
            WeekDay[] weekDays=new WeekDay[8];
            for (int i = 0; i <8 ; i++) {
                WeekDay weekDay=new WeekDay();
                weekDays[i]=weekDay;
                weekDays[i].setId(i+1);
                weekDays[i].setMonday(information[i]);
                weekDays[i].setTuesday(information[i+8]);
                weekDays[i].setWednesday(information[i+16]);
                weekDays[i].setThursday(information[i+24]);
                weekDays[i].setFriday(information[i+32]);
            }
            result.put(myTimetableId,weekDays);
        }

        return R.ok().data("result",result).data("size",result.size());

    }







    @GetMapping("test")
    public   R get() throws InterruptedException {

        create();
        initialTimetable();
        getBestClass();
        return getTimetable();
    }

    //逻辑删除讲师方法
    @ApiOperation(value = "逻辑删除时间表")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "时间表ID", required = true)
            @PathVariable int id) {
        boolean flag = timetableService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "所有时间安排表列表")
    @GetMapping("findAll")
    public R findAllTimetable() {
        List<Timetable> list = timetableService.list(null);
        return R.ok().data("items", list);
    }


    @ApiOperation(value = "分页时间安排表列表")
    @GetMapping("/{current}/{limit}")
    public R pageListTimetable(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        //创page对象
        Page<Timetable> pageTimetable = new Page<>(current, limit);
        timetableService.page(pageTimetable, null);

        long total = pageTimetable.getTotal();//总记录数
        List<Timetable> records = pageTimetable.getRecords();//数据list集合

        return R.ok().data("total", total).data("rows", records);
    }
}
