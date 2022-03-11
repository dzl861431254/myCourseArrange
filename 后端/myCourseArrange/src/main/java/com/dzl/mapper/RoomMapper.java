package com.dzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dzl.entity.Room;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Resource
public interface RoomMapper extends BaseMapper<Room> {
}
