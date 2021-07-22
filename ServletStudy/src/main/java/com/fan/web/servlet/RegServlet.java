package com.fan.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置流的编码
        req.setCharacterEncoding("utf-8");

        //获取请求消息体

        //1.从流对象中获取数据
//        BufferedReader br = req.getReader();
//
//        String line = null;
//
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }

        //获取请求参数的通用方式

        //根据参数名称获取参数值
        String username = req.getParameter("username");
        System.out.println("post---" + username);

        //根据参数名称获取参数值的数组
        String[] hobbies = req.getParameterValues("hobbies");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }

        System.out.println("---------------------------");

        //获取请求的所有参数名称
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String s = parameterNames.nextElement();
            System.out.println(s + ":" + req.getParameter(s));
            System.out.println("+++++++++++++++++++++++");
        }
        System.out.println("---------------------------");

        //获取所有参数的map集合
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        for (String key : keySet) {
            String[] values = parameterMap.get(key);
            System.out.println(key);
            for (String value : values) {
                System.out.println(value);
            }
            System.out.println("+++++++++++++++++++++++");
        }

        //存储数据到request域中
        req.setAttribute("msg", "I am coming!");

        //转发到HelloServlet
        req.getRequestDispatcher("/hello").forward(req,resp);
    }
}
