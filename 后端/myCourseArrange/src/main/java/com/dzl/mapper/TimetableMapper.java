package com.dzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dzl.entity.Timetable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Resource
@Mapper
public interface TimetableMapper extends BaseMapper<Timetable> {
   Integer add(Timetable timetable);
     Integer test();
}
