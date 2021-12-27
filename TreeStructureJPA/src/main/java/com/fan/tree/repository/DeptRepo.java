package com.fan.tree.repository;


import com.fan.tree.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptRepo extends CrudRepository<Department, Integer>{ //JpaRepository<Department, Integer> {

    //    //@Select("select * from dept where parentCode = #{parentCode}")
    //@Query("from Department where parentCode=:parentCode")
    //public List<Department> findAllDept(@Param("parentCode") Integer parentCode);
    List<Department> findByParentCode(Integer parentCode);

    Department findByDeptCode(Integer deptCode);
//
//   //@Insert("insert into dept(deptCode, parentCode, deptName, deptLeader, deptState, deptDesc) values (#{deptCode}, #{parentCode}, #{deptName}, #{deptLeader}, #{deptState}, #{deptDesc})")
//    @Modifying
//    @Query(value = "insert into dept(deptCode, parentCode, deptName, deptLeader, deptState, deptDesc) values (:#{#department.deptCode}, :#{#department.parentCode}, :#{#department.deptName}, :#{#department.deptLeader}, :#{#department.deptState}, :#{#department.deptDesc})", nativeQuery = true)
//    void addDepartment(Department department);
//
//    //@Update("update dept set deptName=#{deptName}, deptLeader=#{deptLeader}, deptState=#{deptState}, deptDesc=#{deptDesc} where deptCode=#{deptCode}")
//    @Modifying
//    @Query(value = "update dept set deptName=:#{#department.deptName}, deptLeader=:#{#department.deptLeader}, deptState=:#{#department.deptState}, deptDesc=:#{#department.deptDesc} where deptCode=:#{#department.deptCode}", nativeQuery = true)
//    void updateDepartment(Department department);
//
//    //@Delete("delete from dept where deptCode=#{deptCode} and parentCode=#{deptCode}")
//    //@Delete("delete from dept where deptCode=#{deptCode}")
//    @Modifying
//    @Query(value = "delete from dept where deptCode=:deptCode", nativeQuery = true)
//    void deleteDepartment(@Param("deptCode") Integer deptCode);
}