package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.R;
import com.example.demo.entity.Category;
import com.example.demo.service.Categoryserice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    Categoryserice categoryserice;


    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){

        Page pageinfo=new Page(page,pageSize);
        //条件
        LambdaQueryWrapper<Category> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.orderByDesc(Category::getSort);
        //执行查询
       categoryserice.page(pageinfo,queryWrapper);
        return R.success(pageinfo);

}

@PostMapping
public R<String> save(@RequestBody Category category){
categoryserice.save(category);
        return R.success("添加菜品成功");
}

@DeleteMapping
public  R<String> delete(long id){

categoryserice.remove(id);
        return R.success("删除成功");
}


public R<String> update(@RequestBody Category category){

        categoryserice.updateById(category);
        return R.success("修改成功");
}

@GetMapping("/list")
public R<List<Category>> list(Category category){
LambdaQueryWrapper<Category> lambdaQueryWrapper=new LambdaQueryWrapper();
lambdaQueryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
lambdaQueryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
List<Category> list=categoryserice.list(lambdaQueryWrapper);
        return R.success(list);
}




}