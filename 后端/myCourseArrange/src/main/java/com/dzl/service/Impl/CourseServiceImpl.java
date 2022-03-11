package com.dzl.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.entity.Course;
import com.dzl.entity.Professor;
import com.dzl.mapper.CourseMapper;
import com.dzl.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Override
    public boolean insertCourse(Course course) {
        return         courseMapper.insertCourse(course);
    }
    @Override
    public long getCourseId(String name) {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.eq("course_name", name);
        }
        final Course course = baseMapper.selectOne(wrapper);
        if (course!=null){
            return course.getCourseId();
        }
        return 0;
    }
}
