package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.R;
import com.example.demo.entity.Employee;
import com.example.demo.service.Employeeserice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.Name;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeController {

    @Autowired
    Employeeserice employeeserice;




    @PostMapping("/login")
  public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
String password2=employee.getPassword();
    String password= DigestUtils.md5DigestAsHex(password2.getBytes());
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<>();
queryWrapper.eq(Employee::getUsername,employee.getUsername());
Employee em=employeeserice.getOne(queryWrapper);

if (em==null ) {
    return R.error("登入失败");
}

        if (!em.getPassword().equals(password) ) {
            return R.error("密码错误");
        }

if (em.getStatus()==0){
    return R.error("账号已禁用");
}

request.getSession().setAttribute("employee",em.getId());
        return R.success(em);
    }


@PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){

        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

@PostMapping
    public  R<String> save(HttpServletRequest request, @RequestBody Employee employee){
log.info("新增员工",employee.toString());

employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

//employee.setCreateTime(LocalDateTime.now());
//employee.setUpdateTime(LocalDateTime.now());

//long empid= (long) request.getSession().getAttribute("employee");
//employee.setCreateUser(empid);
//employee.setUpdateUser(empid);
    employeeserice.save(employee);
return R.success("新增加员工成功");
    }


    @GetMapping("/page")
    public R<Page>  page(int page,int pageSize,String name){
//分页
        Page pageinfo=new Page(page,pageSize);

        //条件
LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.like(!StringUtils.isEmpty(name),Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
       employeeserice.page(pageinfo,queryWrapper);
        return R.success(pageinfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){

//        employee.setUpdateTime(LocalDateTime.now());
//      long emd= (long) request.getSession().getAttribute("employee");
//        employee.setUpdateUser(emd);
        employeeserice.updateById(employee);
        return  R.success("员工信息更改成功");
    }

    @GetMapping("/{id}")
    public R<Employee> getbyid(@PathVariable long id){

    Employee employee= employeeserice.getById(id);

        return R.success(employee);
    }

}
