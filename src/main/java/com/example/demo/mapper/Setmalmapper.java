package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Setmalmapper extends BaseMapper<Setmeal> {
}
