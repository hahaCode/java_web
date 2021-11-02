package com.fan.web.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyContext extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取ServletContext对象
          1.通过request对象获取
          2.通过HttpServlet获取
        */

        ServletContext servletContext1 = req.getServletContext();
        ServletContext servletContext2 = this.getServletContext();

        System.out.println( servletContext1 == servletContext2); //true

        //获取文件的MIME类型
        //String mimeType = servletContext1.getMimeType("a.jpg");//image/jpeg

        //获取文件的服务器路径
        String realPath = servletContext1.getRealPath("/test.txt");
        System.out.println(realPath);
    }
}