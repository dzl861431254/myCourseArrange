package com.dzl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dzl.common.R;
import com.dzl.entity.Class;
import com.dzl.entity.Course;
import com.dzl.entity.Professor;
import com.dzl.service.ClassService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;
    //添加教授
    @ApiOperation(value="添加课程安排列表")
    @PostMapping("addClasses")
    public R addProfessor(@ApiParam(name = "class", value = "课程安排", required = true)
                          @RequestBody ArrayList<Class> myClassList) {
        boolean save = classService.saveBatch(myClassList);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}
