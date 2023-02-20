package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.CustomExcetion;
import com.example.demo.entity.Category;
import com.example.demo.entity.Dish;
import com.example.demo.entity.Setmeal;
import com.example.demo.mapper.Categorymapper;
import com.example.demo.service.Categoryserice;
import com.example.demo.service.Dishserice;
import com.example.demo.service.Setmalserice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Categorysericeimpl extends ServiceImpl<Categorymapper, Category> implements Categoryserice {

    @Autowired
    private Dishserice dishserice;

    @Autowired
    private Setmalserice setmalserice;

    @Override
    public void remove(Long id){
        LambdaQueryWrapper<Dish> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count=dishserice.count(lambdaQueryWrapper);
if (count>0) {
//已有一个菜品，抛出异常
    throw new CustomExcetion("已有一个菜品关联，无法删除。");
}

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper=new LambdaQueryWrapper<>();
       setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
int count2=setmalserice.count(setmealLambdaQueryWrapper);
if (count2>0){
    //关联套餐，抛出异常
    throw new CustomExcetion("已有一个套餐关联，无法删除。");
}

super.removeById(id);
    }
}
