package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.dto.DishDto;
import com.example.demo.entity.Dish;
import com.example.demo.entity.Employee;

public interface Dishserice extends IService<Dish> {

    //新增菜品，同时插入口味表和菜品表
    public void savewithdishflavor(DishDto dishDto);

    public  DishDto getbywithflavor(long id);

    //更新菜品信息，同时更新对应的口味信息
    public void updateWithFlavor(DishDto dishDto);
}
