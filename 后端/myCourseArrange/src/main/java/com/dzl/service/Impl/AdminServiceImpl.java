package com.dzl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.entity.Admin;
import com.dzl.mapper.AdminMapper;
import com.dzl.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
