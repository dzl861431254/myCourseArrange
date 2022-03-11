package com.dzl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dzl.common.R;
import com.dzl.entity.Professor;
import com.dzl.entity.vo.ProfessorQuery;
import com.dzl.service.ProfessorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    ProfessorService professorService;
    @ApiOperation(value = "所有教授列表")
    @GetMapping("findAll")
    public R findAllProfessor(){
        List<Professor> list=professorService.list(null);
        return  R.ok().data("items",list);
    }
    //逻辑删除讲师方法
    @ApiOperation(value = "逻辑删除教授")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id",value = "教授id",required = true)
            @PathVariable Long id){
        boolean flag=professorService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //分页查询
    //current 当前第几页
    //limit  每页显示几条数据
    @ApiOperation(value = "分页教授列表")
    @GetMapping("/{current}/{limit}")
    public R pageListProfessor(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        //创page对象
        Page<Professor> pageProfessor = new Page<>(current, limit);

//        try {
//            int i = 10/0;
//        }catch (Exception e){
//            throw new GuliException(2001,"执行了自定义异常...");
//        }

        //调用方法实现分页
        professorService.page(pageProfessor, null);

        long total = pageProfessor.getTotal();//总记录数
        List<Professor> records = pageProfessor.getRecords();//数据list集合

        return R.ok().data("total", total).data("rows", records);
    }


    //条件查询带分页方法
    @ApiOperation(value = "条件查询带分页教授列表")
    @PostMapping("pageProfessorCondition/{current}/{limit}")
    public R pageProfessorCondition(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @RequestBody(required = false) ProfessorQuery professorQuery) {
        //创page对象
        Page<Professor> pageProfessor = new Page<>(current, limit);

        //构建条件
        QueryWrapper<Professor> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //类比mybatis学的动态sql
        String name = professorQuery.getProfessorName();
        String begin = professorQuery.getBegin();
        String end = professorQuery.getEnd();
        //判断条件是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("professor_name", name);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("create_time", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("create_time", end);
        }

        //排序
        wrapper.orderByDesc("create_time");

        //调用方法实现分页
        professorService.page(pageProfessor, wrapper);

        long total = pageProfessor.getTotal();//总记录数
        List<Professor> records = pageProfessor.getRecords();//数据list集合

        return R.ok().data("total", total).data("rows", records);
    }
    //添加教授
    @ApiOperation(value="添加教授列表")
    @PostMapping("addProfessor")
    public R addProfessor(@ApiParam(name = "professor", value = "讲师对象", required = true)
                        @RequestBody Professor professor) {
        boolean save = professorService.save(professor);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    //根据id查询讲师
    @ApiOperation(value = "根据id查询教授")
    @GetMapping("getProfessor/{id}")
    public R getProfessor(@ApiParam(name = "id", value = "讲师ID", required = true)
                        @PathVariable int id) {
        Professor professor = professorService.getById(id);
        return R.ok().data("professor", professor);
    }

    //更新讲师
    @ApiOperation(value = "更新教授")
    @PostMapping("updateProfessor")
    public R updateProfessor(@ApiParam(name = "professor", value = "教授对象", required = true)
                           @RequestBody Professor professor) {
        boolean flag = professorService.updateById(professor);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}
