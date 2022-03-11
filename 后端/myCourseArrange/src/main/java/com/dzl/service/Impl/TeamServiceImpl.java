package com.dzl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dzl.entity.Team;
import com.dzl.mapper.TeamMapper;
import com.dzl.service.TeamService;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {
}
