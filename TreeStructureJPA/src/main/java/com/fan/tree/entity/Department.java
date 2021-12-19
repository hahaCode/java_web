package com.fan.tree.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "dept")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deptID")
    private Integer deptID;

    @Column(name = "deptCode")
    private Integer deptCode;

    @Column(name = "parentCode")
    private Integer parentCode;

    @Column(name = "deptName")
    private String deptName;

    @Column(name = "deptLeader")
    private String deptLeader;

    @Column(name = "deptState")
    private Boolean deptState;

    @Column(name = "deptDesc")
    private String deptDesc;

    @Transient
    private List<Department> _childDepts;
}
