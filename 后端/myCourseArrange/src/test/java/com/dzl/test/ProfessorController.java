package com.dzl.test;

import com.dzl.entity.Professor;
import com.dzl.mapper.ProfessorMapper;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.boot.test.context.SpringBootTest;
@EnableSwagger2
@SpringBootTest
@ComponentScan(basePackages="com.dzl.mapper")
public class ProfessorController {
    public static void main(String[] args) {
        SpringApplication.run(ProfessorController.class,args);
    }
    @Autowired
    private ProfessorMapper professorMapper;
    @Test
    @ApiOperation("插入一个教师信息")
   public void insert(){
        Professor professor=new Professor();
        professor.setProfessorId(1);
        professor.setProfessorName("马思唯");
        System.out.println(professorMapper.hashCode());
//        int insert=professorMapper.insert(professor);
//        System.out.println("插入完成，影响条数为"+insert+"条");
    }
}
