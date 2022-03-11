package com.dzl.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.entity.Course;
import com.dzl.entity.Professor;
import com.dzl.mapper.ProfessorMapper;
import com.dzl.service.ProfessorService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfessorServiceImpl extends ServiceImpl<ProfessorMapper,Professor>implements ProfessorService {
    @Override
    public Map<String, Object> getProfessorList(Page<Professor> pageParam) {
        QueryWrapper<Professor> wrapper=new QueryWrapper<>();
        wrapper.orderByAsc("professor_id");
        baseMapper.selectPage(pageParam,wrapper);
        List<Professor>  records=pageParam.getRecords();
        for (Professor professor:records){
            System.out.println("xxxxxxxxxx"+professor.toString());
        }
        long current=pageParam.getCurrent();
        long pages=pageParam.getPages();
        long size=pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();//下一页
        boolean hasPrevious = pageParam.hasPrevious();//上一页
        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("pages", pages);
        map.put("current", current);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public long getProfessorId(String name) {
        QueryWrapper<Professor> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.eq("professor_name", name);
        }
        final Professor professor = baseMapper.selectOne(wrapper);
        if (professor!=null){
            return professor.getProfessorId();
        }
        return 0;
    }

}
