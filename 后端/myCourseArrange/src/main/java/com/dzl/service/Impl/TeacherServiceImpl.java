package com.dzl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.entity.Teacher;
import com.dzl.mapper.TeacherMapper;
import com.dzl.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
}
