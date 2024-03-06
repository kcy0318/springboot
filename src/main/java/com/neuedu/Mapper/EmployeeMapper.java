package com.neuedu.Mapper;

import com.neuedu.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
// 雇员（员工）数据接口
public interface EmployeeMapper {
    // 查询所有的员工
    List<Employee> getAllEmployee();

    // 根据ID查询员工信息
    Employee getEmployee(int id);

    // 模糊查询（根据名字）
    List<Employee> getEmployeeByName(String name);

    // 根据ID修改员工信息
    int updateEmployee(Employee employee);

    // 删除员工信息
    int deleteEmployee(int id);

    // 添加员工信息
    int insertEmployee(Employee employee);

    Employee findByEmailAndPwd(Employee employee);

    int updateEmployeeHeader(String imgSrc, int id);
}
