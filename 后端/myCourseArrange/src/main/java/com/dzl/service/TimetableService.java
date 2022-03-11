package com.dzl.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dzl.entity.Timetable;

public interface TimetableService extends IService<Timetable> {
    Integer saveReturnId(Timetable timetable);
    Integer test();
}
