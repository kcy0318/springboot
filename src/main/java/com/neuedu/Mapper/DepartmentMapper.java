package com.neuedu.Mapper;

import com.neuedu.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
// 部门数据接口
public interface DepartmentMapper {
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
