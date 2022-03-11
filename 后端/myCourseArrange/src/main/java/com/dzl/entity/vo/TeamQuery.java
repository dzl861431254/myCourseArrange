package com.dzl.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeamQuery {
    @ApiModelProperty(value = "班级名称，模糊查询")
    private String teamName;

    @ApiModelProperty(value = "班级要上的课程，模糊查询")
    private String courseName;

    @ApiModelProperty(value = "班级人数最小值")
    private int small;

    @ApiModelProperty(value = "班级人数最大值")
    private int  big;
}
