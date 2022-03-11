package com.dzl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dzl.common.R;

import com.dzl.entity.Team;
import com.dzl.entity.vo.TeamQuery;
import com.dzl.service.CourseService;
import com.dzl.service.TeamService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    @Autowired
    CourseService courseService;
    //添加班级信息
    @ApiOperation(value="添加班级列表")
    @PostMapping("addTeam")
    public R addCourse(@ApiParam(name = "team", value = "班级对象", required = true)
                       @RequestBody Team team) {
        boolean save = teamService.save(team);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "所有班级列表")
    @GetMapping("findAll")
    public R findAllCourse() {
        List<Team> list = teamService.list(null);
        return R.ok().data("items", list);
    }

    //逻辑删除讲师方法
    @ApiOperation(value = "逻辑删除班级")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "班级ID", required = true)
            @PathVariable int id) {
        boolean flag = teamService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //分页查询
    //current 当前第几页
    //limit  每页显示几条数据
    @ApiOperation(value = "分页班级列表")
    @GetMapping("/{current}/{limit}")
    public R pageListGroup(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        //创page对象
        Page<Team> pageGroup = new Page<>(current, limit);
        teamService.page(pageGroup, null);

        long total = pageGroup.getTotal();//总记录数
        List<Team> records = pageGroup.getRecords();//数据list集合

        return R.ok().data("total", total).data("rows", records);
    }

    //根据id查询课程
    @ApiOperation(value = "根据id查询班级")
    @GetMapping("getTeam/{id}")
    public R getGroup(@ApiParam(name = "id", value = "班级ID", required = true)
                     @PathVariable int id) {
        Team team =teamService.getById(id);
        return R.ok().data("team", team);
    }

//    //条件查询带分页方法
    @ApiOperation(value = "条件查询带分页班级列表")
    @PostMapping("pageTeamCondition/{current}/{limit}")
    public R pageTeamCondition(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @RequestBody(required = false) TeamQuery teamQuery) {
        //创page对象
        Page<Team> pageTeam = new Page<>(current, limit);

        //构建条件
        QueryWrapper<Team> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //类比mybatis学的动态sql
        String name = teamQuery.getTeamName();
        int small=teamQuery.getSmall();
        int big=teamQuery.getBig();
        String courseName=teamQuery.getCourseName();
        //判断条件是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("team_name", name);
        }
        if (!StringUtils.isEmpty(courseName)) {
            final long courseId1 = courseService.getCourseId(courseName);
            if (courseId1!=0){
                final String courseId = '*'+String.valueOf(courseId1)+'*';
                wrapper.like("c_id", courseId);
            }
        }
        if (!StringUtils.isEmpty(small)) {
            wrapper.ge("team_size", small);
        }
        if (!StringUtils.isEmpty(big)) {
            wrapper.le("team_size", big);
        }


        //排序
        wrapper.orderByDesc("team_id");

        //调用方法实现分页
        teamService.page(pageTeam, wrapper);

        long total = pageTeam.getTotal();//总记录数
        List<Team> records = pageTeam.getRecords();//数据list集合

        return R.ok().data("total", total).data("rows", records);
    }

}
