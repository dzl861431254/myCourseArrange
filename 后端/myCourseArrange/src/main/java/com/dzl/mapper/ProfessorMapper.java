package com.dzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dzl.entity.Professor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Resource
public interface ProfessorMapper extends BaseMapper<Professor> {
}
