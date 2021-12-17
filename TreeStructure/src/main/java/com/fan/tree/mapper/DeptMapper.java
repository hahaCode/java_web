package com.fan.tree.mapper;

import com.fan.tree.domain.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept where parentCode = #{parentCode}")
    public List<Department> findAllDept(Integer parentCode);

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