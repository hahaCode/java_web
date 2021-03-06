package com.fan.tree.service;

import com.fan.tree.domain.Department;
import com.fan.tree.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;

    public List<Department> findAllDept(Integer parentCode){

        List<Department> departmentList = deptMapper.findAllDept(parentCode);
        departmentList.forEach(department -> department.set_childDepts(findAllDept(department.getDeptCode())));
        return departmentList;
    }

    public void addDepartment(Department department){
        deptMapper.addDepartment(department);
    }

    public void updateDepartment(Department department){
        deptMapper.updateDepartment(department);
    }

    public void deleteDepartment(Integer deptCode){
        //递归删除
        //找到要删除的部门的所有子部门
        List<Department> childDeptList = findAllDept(deptCode);
        childDeptList.forEach(department -> deleteDepartment(department.getDeptCode()));

        //删除自己
        deptMapper.deleteDepartment(deptCode);
    }
}
