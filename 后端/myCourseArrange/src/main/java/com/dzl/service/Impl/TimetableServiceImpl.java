package com.dzl.service.Impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.entity.Timetable;
import com.dzl.mapper.TimetableMapper;
import com.dzl.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TimetableService")
public class TimetableServiceImpl extends ServiceImpl<TimetableMapper, Timetable> implements TimetableService {
    @Autowired
    TimetableMapper timetableMapper;
    @Override
    public Integer saveReturnId(Timetable timetable) {
        return     timetableMapper.add(timetable);
    }

    public Integer  test(){
        System.out.println("测试中");
        return         timetableMapper.test();

    }


}
