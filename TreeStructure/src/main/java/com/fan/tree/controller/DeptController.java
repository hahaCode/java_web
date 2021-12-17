package com.fan.tree.controller;

import com.fan.tree.domain.Department;
import com.fan.tree.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/hello")
    public String test(){
        return "hello";
    }

    @RequestMapping("/getDeptList")
    public List<Department> getDeptList(){
        return deptService.findAllDept(0);
    }
}
