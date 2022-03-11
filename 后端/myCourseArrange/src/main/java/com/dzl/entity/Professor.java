package com.dzl.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Professor {
    @Id
    @GeneratedValue
    @TableId(type= IdType.AUTO)//MP自带策略随机生成19位值作为主键，当主键类型为数字类型时使用 Long型
    private  int professorId;
    private  String professorName;




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




    public Professor(){}
    public Professor(int professorId,String professorName){
        this.professorId=professorId;
        this.professorName=professorName;
    }
    public int getProfessorId(){
        return this.professorId;
    }
    public String getProfessorName(){
        return this.professorName;
    }
}
