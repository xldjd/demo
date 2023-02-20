package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.Employeemapper;
import com.example.demo.service.Employeeserice;
import org.springframework.stereotype.Service;

@Service
public class Employeesericeimpl extends ServiceImpl<Employeemapper, Employee> implements Employeeserice {
}
