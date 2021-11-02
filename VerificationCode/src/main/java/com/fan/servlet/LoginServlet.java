package com.fan.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置request编码
        req.setCharacterEncoding("utf-8");
        //2.获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkCode = req.getParameter("checkCode");

        System.out.println("@@@@"+username);  //张三是敏感词汇, 已经被拦截替换

        //3.先判断验证码
        HttpSession session = req.getSession();
        String myCheckCode = (String) session.getAttribute("myCheckCode");

        //获取完就删除session中的验证码, 保证每个验证码只用一次
        session.removeAttribute("myCheckCode");

        //忽略大小写
        if (myCheckCode != null && myCheckCode.equalsIgnoreCase(checkCode)) {
            if("***".equals(username) && "123456".equals(password)) {
                //登录成功, 重定向到success.jsp

                session.setAttribute("user", username);

                resp.sendRedirect(req.getContextPath()+"/success.jsp");

            } else {
                //登录失败
                req.setAttribute("msg", "用户名或密码错误!");
                //转发到登录页面
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }

        } else {
            //验证码不一致
            req.setAttribute("msg", "验证码错误!");
            //转发到登录页面
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
