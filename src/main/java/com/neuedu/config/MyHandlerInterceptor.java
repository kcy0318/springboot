package com.neuedu.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断用户是否登录
        Object attribute = request.getSession().getAttribute("employee");
        System.out.println(attribute);
        if (attribute == null) {
            String servletPath = request.getServletPath();
            System.out.println("已拦截的路径为："+servletPath);
            request.setAttribute("msg","没有权限，请先登录！");
            request.getRequestDispatcher("/index").forward(request, response);
            return false;
        }
        return true;
    }
}
