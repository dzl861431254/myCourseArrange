package com.dzl.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@MapperScan("com.dzl.mapper")
public class MpConfig {
    //乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }
    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
    //逻辑删除插件
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }
    //性能分析插件
    /*
    *开发环境使用，线上不推荐
    * 三种环境
    * dev 开发环境
    * test 测试环境
    * prod 生产环境
    * */
    @Profile({"dev","test"})//此处设置为对dev,test生效
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor=new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(10);//单位毫秒，超过此处设置时长的sql语句不执行
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

}
