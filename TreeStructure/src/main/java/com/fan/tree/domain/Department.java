package com.fan.tree.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Department {

    @TableId(type = IdType.AUTO)
    private Integer deptID;
    private Integer deptCode;
    private Integer parentCode;
    private String deptName;
    private String deptLeader;
    private Boolean deptState;
    private String deptDesc;

    @TableField(exist = false)
    private List<Department> _childDepts;
}
