package com.dzl.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dzl.entity.Professor;

import java.util.Map;

public interface ProfessorService extends IService<Professor> {
    Map<String, Object> getProfessorList(Page<Professor> pageProfessor);
    long getProfessorId(String name);

}
