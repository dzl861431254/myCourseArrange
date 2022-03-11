package com.dzl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.entity.Class;
import com.dzl.mapper.ClassMapper;
import com.dzl.service.ClassService;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {
}
