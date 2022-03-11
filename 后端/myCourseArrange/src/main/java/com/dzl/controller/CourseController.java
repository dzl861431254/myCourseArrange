package com.dzl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dzl.common.R;
import com.dzl.entity.Course;


import com.dzl.entity.vo.CourseQuery;
import com.dzl.service.CourseService;
import com.dzl.service.ProfessorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    ProfessorService professorService;
    //添加教授
    @ApiOperation(value="添加课程列表")
    @PostMapping("addCourse")
    public R addCourse(@ApiParam(name = "course", value = "课程对象", required = true)
                          @RequestBody Course course) {
        boolean save = courseService.save(course);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "所有课程列表")
    @GetMapping("findAll")
    public R findAllTCourse() {
        List<Course> list = courseService.list(null);
        return R.ok().data("items", list);
    }

    //逻辑删除讲师方法
    @ApiOperation(value = "逻辑删除课程")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable int id) {
        boolean flag = courseService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //分页查询
    //current 当前第几页
    //limit  每页显示几条数据
    @ApiOperation(value = "分页课程列表")
    @GetMapping("/{current}/{limit}")
    public R pageListRoom(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        //创page对象
        Page<Course> pageCourse = new Page<>(current, limit);
        courseService.page(pageCourse, null);

        long total = pageCourse.getTotal();//总记录数
        List<Course> records = pageCourse.getRecords();//数据list集合

        return R.ok().data("total", total).data("rows", records);
    }

    //根据id查询课程
    @ApiOperation(value = "根据id查询课程")
    @GetMapping("getCourse/{id}")
    public R getRoom(@ApiParam(name = "id", value = "课程ID", required = true)
                     @PathVariable int id) {
        Course course =courseService.getById(id);
        return R.ok().data("course", course);
    }

    //条件查询带分页方法
    @ApiOperation(value = "条件查询带分页课程列表")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @RequestBody(required = false) CourseQuery courseQuery) {
        //创page对象
        Page<Course> pageCourse = new Page<>(current, limit);

        //构建条件
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //类比mybatis学的动态sql
        String name = courseQuery.getCourseName();
        String code= courseQuery.getCourseCode();
        String professorName=courseQuery.getProfessorName();
        //判断条件是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("course_name", name);
        }
        if (!StringUtils.isEmpty(code)) {
            wrapper.like("course_code", code);
        }

        if (!StringUtils.isEmpty(professorName)) {
            final long professorId1 = professorService.getProfessorId(professorName);
            System.out.println("教授id为"+professorId1);
            if (professorId1!=0){
                final String courseId = '*'+String.valueOf(professorId1)+'*';
                wrapper.like("P_id", professorId1);
            }

        }


        //排序
        wrapper.orderByDesc("course_id");

        //调用方法实现分页
        courseService.page(pageCourse, wrapper);
        System.out.println("xxxxxxxxxxxxx");
        long total = pageCourse.getTotal();//总记录数
        System.out.println("yyyyyyyyyy");
        List<Course> records = pageCourse.getRecords();//数据list集合
        System.out.println("zzzzzzzzzzzzzzzz");

        return R.ok().data("total", total).data("rows", records);
    }



}
