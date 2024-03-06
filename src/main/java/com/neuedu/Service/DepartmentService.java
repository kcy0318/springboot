package com.neuedu.Service;

import com.neuedu.pojo.Department;

import java.util.List;

public interface DepartmentService {
    // 获取部门列表
    List<Department> getAllDepartment();

    // 根据ID获取相应的部门信息
    Department getDepartment(int id);

    // 根据ID修改部门名称
    int updateDepartment(Department department);

    // 添加部门信息
    int insertDepartment(Department department);

    // 删除部门信息
    int deleteDepartment(int id);
}
