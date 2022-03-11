package com.dzl.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

//课程类，保存有关课程学习单元的信息，参加课程的学生分组在每周的不同时间听不同教授讲课

@Data
public class Course {
@Id
@GeneratedValue
    @TableId(type = IdType.AUTO)
    private  int courseId;

    private  String courseCode;

    private  String courseName;
    //todo:这个属性得想个办法将其序列化到数据库字段中
    @TableField(exist = false)
    private  int[] professorIds;
    //这个属性是用来转存professorIds数组为string类型方便持久化，因此要提供从字符串转化成数组，以及数组转化成字符串的方法
    // professorIds[0]+*+professorIds[1)+.....(加一个分隔符*)
    private  String pId;

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
    public  void switchPid(String pId){
        int index=-1;
        int temp=0;
        for (int i = 0; i <pId.length() ; i++) {
            if (pId.charAt(i)=='*'){
                index++;
            }
        }
        int[] myProfessorIds=new int[index];
        index=0;
        for (int i = 1; i <pId.length()-1 ; i++) {
            if (pId.charAt(i)=='*'){
                myProfessorIds[index]=temp;
                index++;
                temp=0;
            }else{
                temp=temp*10+ Integer.parseInt(String.valueOf(pId.charAt(i)));
            }
        }
        myProfessorIds[myProfessorIds.length-1]=temp;
        for (int i = 0; i <myProfessorIds.length ; i++) {
            System.out.println(myProfessorIds[i]);
        }
        this.professorIds=myProfessorIds;

    }

    public  void switchProfessorIds(int[] professorIds){
        StringBuilder myPid=new StringBuilder();
        myPid.append('*');
        myPid.append(professorIds[0]);
        for (int i = 1; i <professorIds.length ; i++) {
            String s = String.valueOf(professorIds[i]);
            myPid.append('*');
            myPid.append(s);
        }
        myPid.append('*');
        this.pId=myPid.toString();
        System.out.println(myPid);
    }

    public Course(){}
    public Course(int couseId,String courseCode,String course){

        this.courseName=course;

        this.courseCode=courseCode;

        this.courseId=couseId;


    }
    public Course(int couseId,String courseCode,String course,int[] professorIds){

        this.courseName=course;

        this.courseCode=courseCode;

        this.courseId=couseId;

        this.professorIds=professorIds;
    }

    public String getCourseName() {
        return courseName;
    }



    public int haveProfessors() {

        int professorId=this.professorIds[(int)(professorIds.length*Math.random() )];

        return professorId;
    }



}
