package com.neuedu.Service.Impl;

import com.neuedu.Mapper.DepartmentMapper;
import com.neuedu.Service.DepartmentService;
import com.neuedu.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment();
    }

    @Override
    public Department getDepartment(int id) {
        return departmentMapper.getDepartment(id);
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentMapper.updateDepartment(department);
    }

    @Override
    public int insertDepartment(Department department) {
        return departmentMapper.insertDepartment(department);
    }

    @Override
    public int deleteDepartment(int id) {
        return departmentMapper.deleteDepartment(id);
    }
}
