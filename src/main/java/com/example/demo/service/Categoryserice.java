package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Category;
import com.example.demo.entity.Employee;

public interface Categoryserice extends IService<Category> {
    void remove(Long id);
}
