package com.dzl.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dzl.entity.Room;

public interface RoomService extends IService<Room> {
    public void test();
    BaseMapper returnBaseMapper();
}
