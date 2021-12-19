package com.fan.tree.service;

import com.fan.tree.entity.Department;
import com.fan.tree.repository.DeptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {

    @Autowired
    private DeptRepo deptRepo;

    public List<Department> findAllDept(Integer parentCode){

        //List<Department> departmentList = deptRepo.findAllDept(parentCode);
        List<Department> departmentList = deptRepo.findByParentCode(parentCode);
        System.out.println("=========>" + departmentList);
        departmentList.forEach(department -> department.set_childDepts(findAllDept(department.getDeptCode())));
        return departmentList;
    }

    public void addDepartment(Department department){
        System.out.println(department);
        //deptRepo.addDepartment(department);
        deptRepo.save(department);
    }

    //有问题
    public void updateDepartment(Department department){

        //deptRepo.updateDepartment(department);
        deptRepo.save(department);
    }

    //有问题
    public void deleteDepartment(Integer deptCode){
        //递归删除
        //找到要删除的部门的所有子部门
        List<Department> childDeptList = findAllDept(deptCode);
        childDeptList.forEach(department -> deleteDepartment(department.getDeptCode()));

        //删除自己
        //deptRepo.deleteDepartment(deptCode);
        deptRepo.delete(deptRepo.findById(deptCode).get());
    }
}
