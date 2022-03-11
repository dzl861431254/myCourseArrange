package com.dzl.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue
    @TableId(type= IdType.AUTO)
    private int teacherId;
    private String teacherName;
    private String passwd;
    private String phoneNum;

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
