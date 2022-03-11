package com.dzl.service.Impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.entity.Room;
import com.dzl.mapper.RoomMapper;
import com.dzl.service.RoomService;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Override
    public void test() {
        System.out.println("这个是我的测试方法"+baseMapper.toString());
    }

    @Override
    public BaseMapper returnBaseMapper() {
        return this.baseMapper;
    }
}
