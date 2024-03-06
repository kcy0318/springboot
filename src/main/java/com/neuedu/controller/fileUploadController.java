package com.neuedu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

// @Controller标记当前类为控制层，当有请求时会先和这个类中的路径进行匹配
@Controller
public class fileUploadController {
    @Value("${file.path}")
    private String filePath;

    @RequestMapping("/toUploadPage")
    public String toUploadPage() {
        return "filePage/form-control";
    }

    /**
     * 处理文件上传请求的方法（单文件上传）
     * @param file 和页面中的form表单中的name要保持一致，因为是一个文件类型的，所以file的类型是MultipartFile
     * @return
     */
//    @PostMapping("/upLoadFile")
//    public String upLoadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
//        if (file.isEmpty()) {
//            request.setAttribute("msg","请选择文件！");
//            request.getRequestDispatcher("/toUploadPage").forward(request,response);
//        } else {
//            // 获取文件名字
//            String filename = file.getOriginalFilename();
//            // 获取文件大小（字节形式）
//            double size = file.getSize() / 1024;
//            if (size > 10240) {
//                request.setAttribute("msg","请上传不超过10MB的文件");
//                request.getRequestDispatcher("/toUploadPage").forward(request,response);
//            }
//            // 写入磁盘
//            File file1 = new File(filePath,filename);
//            file.transferTo(file1);
//            model.addAttribute("msg","上传成功！");
//            // 获取文件
////            Resource resource = file.getResource();
////            System.out.println("fileName:"+filename+",size:"+size+",resource:"+resource.isFile());
//        }
//        return "filePage/form-control";
//    }

    /**
     * 多文件上传
     * @param file
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping("/upLoadFile")
    public String upLoadFiles(MultipartFile[] file, HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        // 判断页面中已经选择了文件，如果没有返回异常信息
        for (MultipartFile multipartFile : file) {
            if (multipartFile.isEmpty()) {
                request.setAttribute("msg","请选择文件！");
                request.getRequestDispatcher("/toUploadPage").forward(request,response);
            } else {
                String filename = multipartFile.getOriginalFilename();
                File file1 = new File(filePath, filename);
                multipartFile.transferTo(file1);
            }
        }
        return "filePage/form-control";
    }
}
