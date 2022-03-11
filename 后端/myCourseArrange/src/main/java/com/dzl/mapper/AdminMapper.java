package com.dzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dzl.entity.Admin;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Resource
@Component
public interface AdminMapper extends BaseMapper<Admin> {
}
