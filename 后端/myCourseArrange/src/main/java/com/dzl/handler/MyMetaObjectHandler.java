package com.dzl.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

//用以自动填充字段
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    //使用mp实现添加操作，这个方法执行
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("version",1,metaObject);
    }

    @Override
    //使用mp实现修改操作，这个方法执行
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
