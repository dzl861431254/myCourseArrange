package com.dzl.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue
    @TableId(type= IdType.AUTO)
    private  int roomId;
    private  String roomName;//roomName=roomName
    private  int roomCapacity;

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

    public Room(int roomId,String roomName,int roomCapacity){
        this.roomId=roomId;
        this.roomCapacity=roomCapacity;
        this.roomName=roomName;
    }

    public Room(){}
    public int getRoomId(){
        return this.roomId;
    }
    public String getRoomName(){
        return this.roomName;
    }
    public int getRoomCapacity(){
        return this.roomCapacity;
    }


}
