package com.dzl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dzl.entity.Course;

public interface CourseService extends IService<Course> {
    public boolean insertCourse(Course course);
    public long getCourseId(String name);
}
