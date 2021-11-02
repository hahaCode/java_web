package com.fan.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向: 访问 /resp1 会自动跳转到 success.html
 *
 */

public class ResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("This is resp1.....");

        //1.设置状态码
        //resp.setStatus(302);

        //2.设置响应头location
        //resp.setHeader("location", "/ServletStudy/success.html");

        //有一种简单的重定向方法
        resp.sendRedirect("/ServletStudy/success.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
