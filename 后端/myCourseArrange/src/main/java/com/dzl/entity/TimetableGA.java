package com.dzl.entity;

import com.dzl.ga.GeneticAlgorithm;
import com.dzl.ga.Population;

public class TimetableGA {
    public static void main(String[] args) {
        //TODO:Crete Timetable and initialize with all the available courses,rooms,timeslots,professor,courses,and groups √
        Timetable timetable=initializeTimetable();

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
            return;
        }

        System.out.println();
        System.out.println("最终在第 "+generation+" 找到合适结果");
        System.out.println("Final solution fitness "+generation+" Best fitness"+population.getFittest(0).getFitness());
        System.out.println("Clashes: "+timetable.calcClashes());
        //print classes
        System.out.println();
        Class classes[]=timetable.getClasses();
        int classIndex=1;
        for (Class bestClass:classes){

            System.out.println("Class"+classIndex+":");
            System.out.println("Course:"+timetable.getCourse(bestClass.getCourseId()).getCourseName());
            System.out.println("Group:"+timetable.getTeam(bestClass.getTeamId()).getTeamName());
            System.out.println("Room"+timetable.getRoom(bestClass.getRoomId()).getRoomName());
            System.out.println("Professor:"+timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
            System.out.println("Time:"+timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
            System.out.println("时间段的id"+bestClass.getTimeslotId());
            System.out.println("-----------");
            classIndex++;
        }

        //TODO:Print final timetable √

    }
    /**
     * Create a Timetable with all the necessary course information
     * @return
     * */

    private  static  Timetable initializeTimetable(){
        //Create timetable
        Timetable timetable=new Timetable();
        //Set up rooms
        //todo:插入对应的房间到到当前的课程表，做个插入方法
        timetable.addRoom(1,"A1",50);
        timetable.addRoom(2,"B1",50);
        timetable.addRoom(3,"C1",50);
        timetable.addRoom(4,"D1",50);
        timetable.addRoom(5,"A2",50);
        timetable.addRoom(6,"B2",50);
        timetable.addRoom(7,"C2",50);
        timetable.addRoom(8,"D2",50);
        timetable.addRoom(9,"A3",50);
        timetable.addRoom(10,"B3",50);
        timetable.addRoom(11,"C3",50);
        timetable.addRoom(12,"D3",50);
        //Set up timeslots
        //个人感觉这个到时候可以写死，但为了前期的代码调试就先设置为可改
        timetable.addTimeslot(1,"Mon 8:30-9:10");
        timetable.addTimeslot(2,"Mon 9:20-10:00");
        timetable.addTimeslot(3,"Mon 10:20-11:00");
        timetable.addTimeslot(4,"Mon 11:10-11:50");
        timetable.addTimeslot(5,"Mon 14:00-14:40");
        timetable.addTimeslot(6,"Mon 14:50-15:30");
        timetable.addTimeslot(7,"Mon 15:40-16:20");
        timetable.addTimeslot(8,"Mon 16:30-17:10");
        timetable.addTimeslot(9,"Tue 8:30-9:10");
        timetable.addTimeslot(10,"Tue 9:20-10:00");
        timetable.addTimeslot(11,"Tue 10:20-11:00");
        timetable.addTimeslot(12,"Tue 11:10-11:50");
        timetable.addTimeslot(13,"Tue 14:00-14:40");
        timetable.addTimeslot(14,"Tue 14:50-15:30");
        timetable.addTimeslot(15,"Tue 15:40-16:20");
        timetable.addTimeslot(16,"Tue 16:30-17:10");
        timetable.addTimeslot(17,"Wed 8:30-9:10");
        timetable.addTimeslot(18,"Wed 9:20-10:00");
        timetable.addTimeslot(19,"Wed 10:20-11:00");
        timetable.addTimeslot(20,"Wed 11:10-11:50");
        timetable.addTimeslot(21,"Wed 14:00-14:40");
        timetable.addTimeslot(22,"Wed 14:50-15:30");
        timetable.addTimeslot(23,"Wed 15:40-16:20");
        timetable.addTimeslot(24,"Wed 16:30-17:10");
        timetable.addTimeslot(25,"Thu 8:30-9:10");
        timetable.addTimeslot(26,"Thu 9:20-10:00");
        timetable.addTimeslot(27,"Thu 10:20-11:00");
        timetable.addTimeslot(28,"Thu 11:10-11:50");
        timetable.addTimeslot(29,"Thu 14:00-14:40");
        timetable.addTimeslot(30,"Thu 14:50-15:30");
        timetable.addTimeslot(31,"Thu 15:40-16:20");
        timetable.addTimeslot(32,"Thu 16:30-17:10");
        timetable.addTimeslot(33,"Fri 8:30-9:10");
        timetable.addTimeslot(34,"Fri 9:20-10:00");
        timetable.addTimeslot(35,"Fri 10:20-11:00");
        timetable.addTimeslot(36,"Fri 11:10-11:50");
        timetable.addTimeslot(37,"Fri 14:00-14:40");
        timetable.addTimeslot(38,"Fri 14:50-15:30");
        timetable.addTimeslot(39,"Fri 15:40-16:20");
        timetable.addTimeslot(40,"Fri 16:30-17:10");



        //Set up professors
        //todo:添加教师信息
        timetable.addProfessor(1,"Dr P Smith");
        timetable.addProfessor(2,"Mrs E Mitchell");
        timetable.addProfessor(3,"Dr R Williams");
        timetable.addProfessor(4,"Mr A Thompson");
        timetable.addProfessor(5,"张一");
        timetable.addProfessor(6,"张二");
        timetable.addProfessor(7,"张三");
        timetable.addProfessor(8,"张四");
        timetable.addProfessor(9,"赵一");
        timetable.addProfessor(10,"赵二");
        timetable.addProfessor(11,"赵三");
        timetable.addProfessor(12,"赵四");
        timetable.addProfessor(13,"陈一");
        timetable.addProfessor(14,"陈二");
        timetable.addProfessor(15,"陈三");
        timetable.addProfessor(16,"陈四");
        //Set up courses and define the professors that teach them
        //todo:添加课程 到时候是将后面的代表教师的数组改成自由添加的那种，显示全部教师的类型
//        timetable.addCourse(1,"cs1","计算机科学",new int[]{1,2});
//        timetable.addCourse(2,"en1","英文课",new int[]{1,3});
//        timetable.addCourse(3,"ma1","数学课",new int[]{1,2});
//        timetable.addCourse(4,"ph1","物理课",new int[]{3,4});
//        timetable.addCourse(5,"hi1","历史课",new int[]{4});
//        timetable.addCourse(6,"dr1","戏剧课",new int[]{1,4});

        //todo:添加课程 到时候是将后面的代表教师的数组改成自由添加的那种，显示全部教师的类型
        timetable.addCourse(1,"cs1","计算机科学");
        timetable.setProfessorCourse(1,7);
        timetable.setProfessorCourse(1,12);
        timetable.addCourse(2,"en1","英文课");
        timetable.setProfessorCourse(2,1);
        timetable.setProfessorCourse(2,9);
        timetable.addCourse(3,"ma1","数学课");
        timetable.setProfessorCourse(3,11);
        timetable.setProfessorCourse(3,2);
        timetable.addCourse(4,"ph1","物理课");
        timetable.setProfessorCourse(4,4);
        timetable.setProfessorCourse(4,6);
        timetable.addCourse(5,"hi1","历史课");
        timetable.setProfessorCourse(5,3);
        timetable.addCourse(6,"dr1","戏剧课");
        timetable.setProfessorCourse(6,5);
        timetable.setProfessorCourse(6,14);
        timetable.addCourse(7,"che1","经济课");
        timetable.setProfessorCourse(7,11);
        timetable.setProfessorCourse(7,4);
        timetable.addCourse(8,"my1","马原课");
        timetable.setProfessorCourse(8,1);
        timetable.setProfessorCourse(8,4);

        timetable.addCourse(9,"cs2","计算机科学2");
        timetable.setProfessorCourse(9,7);
        timetable.setProfessorCourse(9,12);
        timetable.addCourse(10,"en2","英文课2");
        timetable.setProfessorCourse(10,1);
        timetable.setProfessorCourse(10,9);
        timetable.addCourse(11,"ma2","数学课2");
        timetable.setProfessorCourse(11,11);
        timetable.setProfessorCourse(11,2);
        timetable.addCourse(12,"ph2","物理课2");
        timetable.setProfessorCourse(12,4);
        timetable.setProfessorCourse(12,6);
        timetable.addCourse(13,"hi2","历史课2");
        timetable.setProfessorCourse(13,3);
        timetable.addCourse(14,"dr2","戏剧课2");
        timetable.setProfessorCourse(14,5);
        timetable.setProfessorCourse(14,14);
        timetable.addCourse(15,"che2","经济课2");
        timetable.setProfessorCourse(15,11);
        timetable.setProfessorCourse(15,4);
        timetable.addCourse(16,"my2","马原课2");
        timetable.setProfessorCourse(16,1);
        timetable.setProfessorCourse(16,4);
        timetable.addCourse(17,"jw1","计算机网络1");
        timetable.setProfessorCourse(17,8);
        timetable.setProfessorCourse(17,10);
        timetable.addCourse(18,"jw2","计算机网络2");
        timetable.setProfessorCourse(18,8);
        timetable.setProfessorCourse(18,10);
        timetable.addCourse(19,"cz1","操作系统1");
        timetable.setProfessorCourse(19,13);
        timetable.setProfessorCourse(19,15);
        timetable.addCourse(20,"cz2","操作系统2");
        timetable.setProfessorCourse(20,13);
        timetable.setProfessorCourse(20,15);
        timetable.addCourse(21,"sj1","数据结构1");
        timetable.setProfessorCourse(21,16);
        timetable.setProfessorCourse(21,15);
        timetable.addCourse(22,"sj2","数据结构2");
        timetable.setProfessorCourse(22,16);
        timetable.setProfessorCourse(22,15);

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
        timetable.addTeam(1,10,"18级一班");
        timetable.setTeamCourse(1,1);
        timetable.setTeamCourse(1,2);
        timetable.setTeamCourse(1,3);
        timetable.setTeamCourse(1,4);
        timetable.addTeam(2,30,"18级二班");
        timetable.setTeamCourse(2,1);
        timetable.setTeamCourse(2,2);
        timetable.setTeamCourse(2,3);
        timetable.setTeamCourse(2,4);
        timetable.addTeam(3,18,"18级三班");
        timetable.setTeamCourse(3,1);
        timetable.setTeamCourse(3,2);
        timetable.setTeamCourse(3,3);
        timetable.setTeamCourse(3,4);
        timetable.addTeam(4,25,"18级四班");
        timetable.setTeamCourse(4,9);
        timetable.setTeamCourse(4,10);
        timetable.setTeamCourse(4,11);
        timetable.setTeamCourse(4,12);
        timetable.addTeam(5,20,"18级五班");
        timetable.setTeamCourse(5,9);
        timetable.setTeamCourse(5,10);
        timetable.setTeamCourse(5,11);
        timetable.setTeamCourse(5,12);
        timetable.addTeam(6,22,"18级六班");
        timetable.setTeamCourse(6,9);
        timetable.setTeamCourse(6,10);
        timetable.setTeamCourse(6,11);
        timetable.setTeamCourse(6,12);
        timetable.addTeam(7,16,"19级一班");
        timetable.setTeamCourse(7,5);
        timetable.setTeamCourse(7,6);
        timetable.setTeamCourse(7,7);
        timetable.setTeamCourse(7,8);
        timetable.addTeam(8,18,"19级二班");
        timetable.setTeamCourse(8,5);
        timetable.setTeamCourse(8,6);
        timetable.setTeamCourse(8,7);
        timetable.setTeamCourse(8,8);
        timetable.addTeam(9,24,"19级三班");
        timetable.setTeamCourse(9,5);
        timetable.setTeamCourse(9,6);
        timetable.setTeamCourse(9,7);
        timetable.setTeamCourse(9,8);
        timetable.addTeam(10,25,"19级四班");
        timetable.setTeamCourse(10,13);
        timetable.setTeamCourse(10,14);
        timetable.setTeamCourse(10,15);
        timetable.setTeamCourse(10,16);
        timetable.addTeam(11,25,"19级五班");
        timetable.setTeamCourse(11,13);
        timetable.setTeamCourse(11,14);
        timetable.setTeamCourse(11,15);
        timetable.setTeamCourse(11,16);
        timetable.addTeam(12,25,"19级六班");
        timetable.setTeamCourse(12,13);
        timetable.setTeamCourse(12,14);
        timetable.setTeamCourse(12,15);
        timetable.setTeamCourse(12,16);
        timetable.addTeam(13,25,"20级1班");
        timetable.setTeamCourse(13,17);
        timetable.setTeamCourse(13,18);
        timetable.setTeamCourse(13,19);
        timetable.addTeam(14,25,"20级2班");
        timetable.setTeamCourse(14,17);
        timetable.setTeamCourse(14,18);
        timetable.setTeamCourse(14,19);
        timetable.addTeam(15,25,"20级3班");
        timetable.setTeamCourse(15,17);
        timetable.setTeamCourse(15,18);
        timetable.setTeamCourse(15,19);
        timetable.addTeam(16,25,"20级4班");
        timetable.setTeamCourse(16,20);
        timetable.setTeamCourse(16,21);
        timetable.setTeamCourse(16,22);
        timetable.addTeam(17,25,"20级5班");
        timetable.setTeamCourse(17,20);
        timetable.setTeamCourse(17,21);
        timetable.setTeamCourse(17,22);
        timetable.addTeam(18,25,"20级6班");
        timetable.setTeamCourse(18,20);
        timetable.setTeamCourse(18,21);
        timetable.setTeamCourse(18,22);
        return  timetable;

    }
}
