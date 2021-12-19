package com.fan.tree.controller;

import com.fan.tree.entity.Department;
import com.fan.tree.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/deleteDept/{deptCode}")
    public boolean deleteDept(@PathVariable("deptCode") Integer deptCode){
        try {
            deptService.deleteDepartment(deptCode);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

   //http://localhost:8080/dept/addDept?deptCode=202&parentCode=100&deptName=销售部&deptLeader=销售总监&deptState=1&deptDesc=test
    @RequestMapping("/addDept")
    public boolean addDept(Department department){
        try {
            deptService.addDepartment(department);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequestMapping("/updateDept")
    public boolean updateDept(Department department){
        try {
            deptService.updateDepartment(department);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
