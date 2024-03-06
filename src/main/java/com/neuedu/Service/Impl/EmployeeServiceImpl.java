package com.neuedu.Service.Impl;

import com.neuedu.Mapper.EmployeeMapper;
import com.neuedu.Service.EmployeeService;
import com.neuedu.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeMapper.getAllEmployee();
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeMapper.getEmployee(id);
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        return null;
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public int deleteEmployee(int id) {
        return employeeMapper.deleteEmployee(id);
    }

    @Override
    public int insertEmployee(Employee employee) {
        return employeeMapper.insertEmployee(employee);
    }

    @Override
    public Employee findByEmailAndPwd(Employee employee) {
        return employeeMapper.findByEmailAndPwd(employee);
    }

    @Override
    public int updateEmployeeHeader(String imgSrc, int id) {
        return employeeMapper.updateEmployeeHeader(imgSrc, id);
    }
}
