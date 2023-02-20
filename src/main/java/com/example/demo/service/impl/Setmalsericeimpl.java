package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Dish;
import com.example.demo.entity.Setmeal;
import com.example.demo.mapper.Dishmapper;
import com.example.demo.mapper.Setmalmapper;
import com.example.demo.service.Dishserice;
import com.example.demo.service.Setmalserice;
import org.springframework.stereotype.Service;

@Service
public class Setmalsericeimpl extends ServiceImpl<Setmalmapper, Setmeal> implements Setmalserice {
}
