package com.fan.web.session;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class MySession_02 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取Session
        HttpSession session = req.getSession();

        //2.获取数据
        System.out.println(session.getAttribute("msg"));
    }
}
