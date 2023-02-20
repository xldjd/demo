package com.example.demo.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 员数据对象处理器
 */

@Component
@Slf4j
public class mymeteObjectHandle implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
log.info("公共字段自动填充");
log.info("插入");
metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContent.getcurrentid());
        metaObject.setValue("updateUser", BaseContent.getcurrentid());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
log.info("更新");
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser",BaseContent.getcurrentid());
    }
}
