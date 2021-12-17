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
}
