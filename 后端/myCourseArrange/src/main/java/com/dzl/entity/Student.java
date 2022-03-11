package com.dzl.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue
    @TableId(type= IdType.AUTO)
    private int stuId;
    private String stuName;
    private int stuYear;
    private String majorId;
    private int teamId;
    private String passwd;
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

}
