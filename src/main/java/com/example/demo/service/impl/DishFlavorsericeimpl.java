package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.DishFlavor;
import com.example.demo.mapper.DishFlavormapper;
import com.example.demo.service.DishFlavorsercie;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorsericeimpl extends ServiceImpl<DishFlavormapper, DishFlavor> implements DishFlavorsercie {
}
