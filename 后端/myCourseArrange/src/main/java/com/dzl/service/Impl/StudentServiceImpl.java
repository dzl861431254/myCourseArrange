package com.dzl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.entity.Student;
import com.dzl.mapper.StudentMapper;
import com.dzl.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}
