package com.dzl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class RoomQuery {
    @ApiModelProperty(value = "教室名称，模糊查询")
    private String roomName;


    @ApiModelProperty(value = "教室容量最小值")
    private int small;

    @ApiModelProperty(value = "教室容量最大值")
    private int  big;
}
