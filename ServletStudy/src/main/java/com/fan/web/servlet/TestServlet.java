package com.fan.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Request对象获取请求行数据
 *
 * http://localhost:8080/ServletStudy/user/testServlet?name=zhangsan
 */

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("-------------------获取请求行数据----------------------");
        //获取请求方式
        System.out.println(req.getMethod());  //GET

        //获取虚拟目录(重要)
        System.out.println(req.getContextPath());  //   /ServletStudy

        //获取Servlet路径
        System.out.println(req.getServletPath()); //   /user/testServlet

        //获取get方式请求参数
        System.out.println(req.getQueryString()); //  name=zhangsan

        //获取请求的URI(重要)
        System.out.println(req.getRequestURI());  //  /ServletStudy/user/testServlet

        //获取请求的URL
        System.out.println(req.getRequestURL());  //  http://localhost:8080/ServletStudy/user/testServlet

        //获取协议版本
        System.out.println(req.getProtocol());   //  HTTP/1.1

        //获取客户机的IP
        System.out.println(req.getRemoteAddr()); //  0:0:0:0:0:0:0:1

        System.out.println("--------------------获取请求头数据---------------------");

        //获取所有的请求头名称
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String headerValue = req.getHeader(name);  //根据名称获取请求头的值
            System.out.println(name + ":" + headerValue);
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        //获取指定请求头数据(重要)
        String agent = req.getHeader("user-agent");//不区分大小写
        if(agent.contains("Chrome")) {
            System.out.println("谷歌浏览器");
        } else if (agent.contains("Firefox")) {
            System.out.println("火狐浏览器");
        } else {
            System.out.println("我也不认识");
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        String referer = req.getHeader("referer");
        System.out.println(referer);  //http://localhost:8080/ServletStudy/login.html

        //防盗链
        resp.setContentType("text/html;charset=utf-8");
        if(referer!=null){
            if(referer.contains("/ServletStudy")) {
                //正常访问
                resp.getWriter().write("欢迎!");
            } else {
                //盗链
                resp.getWriter().write("请支持正版!");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
//https://www.bilibili.com/video/BV1qv4y1o79t?p=249&spm_id_from=pageDriver