package com.dzl.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseQuery {
    @ApiModelProperty(value = "课程名称，模糊查询")
    private String CourseName;

    @ApiModelProperty(value = "课程编号，模糊查询")
    private String CourseCode;

    @ApiModelProperty(value = "课程教授，模糊查询")
    private String professorName;
    //想要通过在搜索栏里添加对应的教授，搜索出分配了对应的教授的课程出来，但传的是名称来。首先要在教授那边查出对应的id
    //然后根据id再从课程的已经分配的教授列表，即数组列表中找，但是感觉这个好难实现，有点难度。

}
