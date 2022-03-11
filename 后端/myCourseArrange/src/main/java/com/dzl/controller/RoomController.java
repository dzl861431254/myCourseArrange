package com.dzl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dzl.common.R;
import com.dzl.entity.Professor;
import com.dzl.entity.Room;
import com.dzl.entity.vo.ProfessorQuery;
import com.dzl.entity.vo.RoomQuery;
import com.dzl.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api("课室管理")
@RestController
@RequestMapping("/room")
@CrossOrigin
public class RoomController {
    @Autowired
    private RoomService roomService;

    @ApiOperation(value = "所有教室列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<Room> list = roomService.list(null);
        return R.ok().data("items", list);
    }

    //逻辑删除讲师方法
    @ApiOperation(value = "逻辑删除教室")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "教室ID", required = true)
            @PathVariable int id) {
        boolean flag = roomService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //分页查询
    //current 当前第几页
    //limit  每页显示几条数据
    @ApiOperation(value = "分页教室列表")
    @GetMapping("/{current}/{limit}")
    public R pageListRoom(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        //创page对象
        Page<Room> pageRoom = new Page<>(current, limit);

//        try {
//            int i = 10/0;
//        }catch (Exception e){
//            throw new GuliException(2001,"执行了自定义异常...");
//        }

        //调用方法实现分页
        roomService.page(pageRoom, null);

        long total = pageRoom.getTotal();//总记录数
        List<Room> records = pageRoom.getRecords();//数据list集合

        return R.ok().data("total", total).data("rows", records);
    }



    //添加教室
    @ApiOperation(value="添加教室列表")
    @PostMapping("addRoom")
    public R addRoom(@ApiParam(name = "room", value = "教室对象", required = true)
                          @RequestBody Room room) {
        boolean save = roomService.save(room);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //根据id查询讲师
    @ApiOperation(value = "根据id查询教室")
    @GetMapping("getRoom/{id}")
    public R getRoom(@ApiParam(name = "id", value = "教室ID", required = true)
                          @PathVariable int id) {
        Room room =roomService.getById(id);
        return R.ok().data("room", room);
    }

    //条件查询带分页方法
    @ApiOperation(value = "条件查询带分页教授列表")
    @PostMapping("pageRoomCondition/{current}/{limit}")
    public R pageProfessorCondition(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @RequestBody(required = false) RoomQuery roomQuery) {
        //创page对象
        Page<Room> pageRoom = new Page<>(current, limit);

        //构建条件
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //类比mybatis学的动态sql
        String name = roomQuery.getRoomName();
        int small = roomQuery.getSmall();
        int big = roomQuery.getBig();
        //判断条件是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("room_name", name);
        }
        if (!StringUtils.isEmpty(small)) {
            wrapper.ge("room_capacity", small);
        }
        if (!StringUtils.isEmpty(big)) {
            wrapper.le("room_capacity", big);
        }

        //排序
        wrapper.orderByDesc("room_capacity");

        //调用方法实现分页
        roomService.page(pageRoom, wrapper);
        roomService.test();
        long total = pageRoom.getTotal();//总记录数
        List<Room> records = pageRoom.getRecords();//数据list集合

        return R.ok().data("total", total).data("rows", records);
    }

}
