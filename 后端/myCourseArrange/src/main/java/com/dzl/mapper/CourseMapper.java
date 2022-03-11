package com.dzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dzl.entity.Course;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Resource
public interface CourseMapper extends BaseMapper<Course> {
public boolean insertCourse(Course course);
}
