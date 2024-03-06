package com.neuedu.controller;

import com.neuedu.Service.DepartmentService;
import com.neuedu.Service.EmployeeService;
import com.neuedu.pojo.Department;
import com.neuedu.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {
    @Value("${file.path}")
    private String filePath;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    // 跳转首页
    @RequestMapping("/index")
    public String toindex() {
        return "index";
    }
    // 登录
    @PostMapping("/login")
    public String login(String email, HttpSession session, String password, Model model) {
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setPassword(password);
        Employee employee1 = employeeService.findByEmailAndPwd(employee);
        // 一旦设置了session，那就是全局可以取用里面的信息
        session.setAttribute("employee",employee1);
        if (employee1 != null) {
            return "employee/layout";
        }else {
            model.addAttribute("msg","账号或者密码错误");
            return "index";
        }
    }
    // 退出登录
    @RequestMapping("/loginOut")
//    public String loginOut(HttpSession session) {
//        session.removeAttribute("employee");
//        return "index";
//    }
    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("msg", "已退出登录");
        request.getSession().removeAttribute("employee");
        request.getRequestDispatcher("/index").forward(request, response);
    }
    // 查询所有员工
    @RequestMapping("/getAllEmployee")
    public String getAllEmployee(Model model) {
        // 写访问服务层的代码
        List<Employee> allEmployee = employeeService.getAllEmployee();
        model.addAttribute("employees",allEmployee);
        return "employee/basic-tables";
    }

    @RequestMapping("/layout.html")
    public String tolayout(Model model) {
        // 查询所有部门
        List<Department> allDepartment = departmentService.getAllDepartment();
        model.addAttribute("departments", allDepartment);
        return "employee/layout";
    }

    @RequestMapping("/basic_table.html")
    public String basic_table(Model model) {
        // 写访问服务层的代码
        List<Employee> allEmployee = employeeService.getAllEmployee();
        model.addAttribute("employees",allEmployee);
        return "employee/basic-tables";
    }

    @RequestMapping("/toUpdatePage/{id}")
    public String toUpdatePage(@PathVariable int id, Model model) {
        Employee employee = employeeService.getEmployee(id);
        // 查询所有部门
        List<Department> allDepartment = departmentService.getAllDepartment();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", allDepartment);
        return "employee/updatePage";
    }

    @PostMapping("/updateEmployee/{id}")
//    public String updateEmployee(@PathVariable int id, Employee employee,Model model) {
//        int i = employeeService.updateEmployee(employee);
//        if (i == 0) {
//            model.addAttribute("msg", "修改失败！");
//            return "redirect:/toUpdatePage/"+id;
//        }
//        return "redirect:/getAllEmployee";
//    }
    public void updateEmployee(@PathVariable int id, Employee employee,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int i = employeeService.updateEmployee(employee);
        if (i == 0) {
            request.setAttribute("msg", "修改失败！");
            request.getRequestDispatcher("/toUpdatePage/"+id).forward(request,response);
        } else {
            request.setAttribute("msg", employee.getName()+"的信息修改成功！");
            request.getRequestDispatcher("/getAllEmployee").forward(request,response);
        }
    }

    @PostMapping("/insertEmployee")
    public String addEmployee(Employee employee) {
        employeeService.insertEmployee(employee);
        return "redirect:/getAllEmployee";
    }

    @RequestMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/getAllEmployee";
    }
    @PostMapping("/search")
    public String search(String search){
        System.out.println(search);
        return "";
    }
    // 更新用户头像
    @RequestMapping("/toUpHeader")
    public String toUpHeader(HttpSession session, Model model) {
        session.getAttribute("employee");
        model.addAttribute("esession",session.getAttribute("employee"));
        return "filePage/upHeader";
    }
    @PostMapping("/upLoadHeader/{id}")
    public String upLoadHeader(MultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model,@PathVariable int id) throws ServletException, IOException {
        if (file.isEmpty()) {
            request.setAttribute("msg","请选择头像！");
            request.getRequestDispatcher("/toUpHeader").forward(request,response);
        } else {
            String header = file.getOriginalFilename();
            File file1 = new File(filePath, header);
            file.transferTo(file1);
            // 将来在数据中的存储形式
            String imgSrc = "/file/"+header;
            int i = employeeService.updateEmployeeHeader(imgSrc,id);
        }
        return "redirect:/loginOut";
    }
}
