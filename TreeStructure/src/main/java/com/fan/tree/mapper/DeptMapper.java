package com.fan.tree.mapper;

import com.fan.tree.domain.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept where parentCode = #{parentCode}")
    public List<Department> findAllDept(Integer parentCode);

    @Insert("insert into dept(deptCode, parentCode, deptName, deptLeader, deptState, deptDesc) values (#{deptCode}, #{parentCode}, #{deptName}, #{deptLeader}, #{deptState}, #{deptDesc})")
    void addDepartment(Department department);

    @Update("update dept set deptName=#{deptName}, deptLeader=#{deptLeader}, deptState=#{deptState}, deptDesc=#{deptDesc} where deptCode=#{deptCode}")
    void updateDepartment(Department department);

    //@Delete("delete from dept where deptCode=#{deptCode} and parentCode=#{deptCode}")
    @Delete("delete from dept where deptCode=#{deptCode}")
    void deleteDepartment(Integer deptCode);
}

//<where>
//<choose>
//<when test="parent_id!=null">
//        and parent_id = #{parent_id}
//</when>
//<otherwise>
//                and parent_id = 0
//</otherwise>
//</choose>
//</where>

//https://www.bilibili.com/video/av331110999/