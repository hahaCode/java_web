package com.fan.web.cookies;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 创建Cookie对象, 绑定数据
        Cookie c = new Cookie("msg", "hello");

        //设置Cookie的存活时间
        //正数:将cookie数据写到硬盘的文件中。持久化存储。    负数: 默认值    零: 删除Cookie信息
        c.setMaxAge(30);   //将cookie持久化到硬盘, 30s后自动删除cookie文件

        //2. 发送Cookie对象
        resp.addCookie(c);
    }
}