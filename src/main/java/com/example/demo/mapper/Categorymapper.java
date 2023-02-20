package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Category;
import com.example.demo.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Categorymapper extends BaseMapper<Category> {
}
