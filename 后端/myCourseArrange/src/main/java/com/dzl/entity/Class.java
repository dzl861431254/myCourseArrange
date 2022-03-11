package com.dzl.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

//Class类代表所有上述信息的组合，它代表一个学生分组在特定的时间，在特定教室，跟随特定教授，学习某个课程
@Data
@Entity
public class Class {
    @Id
    @GeneratedValue
    @TableId(type = IdType.AUTO)
    private  int classId;
    private  int teamId;
    private  int courseId;
    private int professorId;
    private int timeslotId;
    private int roomId;
    private int timetableId;

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
    private  Boolean deleted;
    public Class(){}
    public Class(int classId, int teamId, int courseId) {
        this.classId = classId;
        this.teamId = teamId;
        this.courseId = courseId;
    }

    public void addProfessor(int professorId){
        this.professorId=professorId;
    }

    public void addTimeslot(int timeslotId){
        this.timeslotId=timeslotId;
    }
    public void setRoomId(int roomId){
        this.roomId=roomId;
    }

    public int getClassId() {
        return classId;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getProfessorId() {
        return professorId;
    }

    public int getTimeslotId() {
        return timeslotId;
    }

    public int getRoomId() {
        return roomId;
    }
}
