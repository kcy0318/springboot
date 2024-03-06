package com.neuedu.controller;

import com.neuedu.Service.DepartmentService;
import com.neuedu.pojo.Department;
import com.neuedu.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/getAllDepartment")
    public String getAllDepartment(Model model) {
        List<Department> allDepartments = departmentService.getAllDepartment();
        model.addAttribute("departments", allDepartments);
        return "department/basic_tables1";
    }
    @RequestMapping("/layout1.html")
    public String tolayout1(Model model) {
        return "department/layout1";
    }
    @RequestMapping("/basic_table1.html")
    public String basic_table1(Model model) {
        List<Department> allDepartments = departmentService.getAllDepartment();
        model.addAttribute("departments", allDepartments);
        return "department/basic_tables1";
    }
    @RequestMapping("/insertDepartment")
    public String insertDepartment(Department department) {
        departmentService.insertDepartment(department);
        System.out.println(department);
        return "redirect:/getAllDepartment";
    }
    @RequestMapping("/deleteDepartment/{id}")
    public String deleteEmployee(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return "redirect:/getAllDepartment";
    }
    @PostMapping("/updateDepartment/{id}")
    public String updateDepartment(@PathVariable int id, Department department, Model model) {
//        System.out.println("===="+department);
        int i = departmentService.updateDepartment(department);
        if (i == 0) {
            model.addAttribute("msg", "修改失败！");
            return "redirect:/toUpdatePage1/"+id;
        }
        return "redirect:/getAllDepartment";
    }
    @RequestMapping("/toUpdatePage1/{id}")
    public String toUpdatePage(@PathVariable int id, Model model) {
        Department department = departmentService.getDepartment(id);
//        System.out.println("****"+department);
        model.addAttribute("department", department);
        return "department/updatePage1";
    }
}
