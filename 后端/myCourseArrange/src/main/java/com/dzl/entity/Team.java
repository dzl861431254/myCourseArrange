package com.dzl.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

//保存有关学生分组的信息
@Entity
@Data
public class Team {
    @Id
    @GeneratedValue
    @TableId(type = IdType.AUTO)
    private  int teamId;
    private  int teamSize;
    private String teamName;
    @TableField(exist = false)
    private  int[] courseIds;
    private String cId;
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
    public Team(int teamId,int teamSize,String teamName,int[] courseIds ){
        this.teamId=teamId;
        this.teamSize=teamSize;
        this.teamName=teamName;
        this.courseIds=courseIds;
    }

    public Team(int teamId,int teamSize,String teamName){
        this.teamId=teamId;
        this.teamSize=teamSize;
        this.teamName=teamName;

    }
    public Team(int teamId, int teamSize,int[] courseIds) {
        this.teamId = teamId;
        this.teamSize = teamSize;

        this.courseIds = courseIds;
    }
    public Team(){}
    public String getTeamName(){
        return this.teamName;
    }


    public  void switchCid(String cId){
        int index=-1;
        int temp=0;
        for (int i = 0; i <cId.length() ; i++) {
            if (cId.charAt(i)=='*'){
                index++;
            }
        }
        int[] myCourseIds=new int[index];
        index=0;
        for (int i = 1; i <cId.length()-1 ; i++) {
            if (cId.charAt(i)=='*'){
                myCourseIds[index]=temp;
                index++;
                temp=0;
            }else{
                temp=temp*10+ Integer.parseInt(String.valueOf(cId.charAt(i)));
            }
        }
        myCourseIds[myCourseIds.length-1]=temp;
        for (int i = 0; i <myCourseIds.length ; i++) {
            System.out.println(myCourseIds[i]);
        }
        this.courseIds=myCourseIds;

    }

    public  void switchProfessorIds(int[] couresIds){
        StringBuilder myCid=new StringBuilder();
        myCid.append('*');
        myCid.append(couresIds[0]);
        for (int i = 1; i <couresIds.length ; i++) {
            String s = String.valueOf(couresIds[i]);
            myCid.append('*');
            myCid.append(s);
        }
        myCid.append('*');
        this.cId=myCid.toString();
        System.out.println(myCid);
    }
}
