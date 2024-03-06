package com.neuedu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String name;
    private String email;
    private Integer gender;// 1:男  0:女
    private Integer department;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String password;
    private Department eDepartment;
    private String imgPath;
}
