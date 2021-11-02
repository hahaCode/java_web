package com.fan.web.cookies;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Cookie小案例
 * 如果是第一次访问, 响应数据: 您好, 欢迎首次访问
 * <p>
 * 如果不是第一次访问, 响应数据: 欢迎回来, 您上次访问时间为: xxxx年xx月xx日
 */
public class CookieDemo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        //1.获取所有的Cookie
        Cookie[] cookies = req.getCookies();
        boolean flag = false;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("lastTime".equals(cookie.getName())) {
                    flag = true;
                    //不是第一次访问
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String str_date = simpleDateFormat.format(date);
                    //str_date这个字符串里含有空格, cookie不支持这种特殊字符,需要使用URL编码
                    cookie.setValue(URLEncoder.encode(str_date, "utf-8"));
                    cookie.setMaxAge(60 * 60 * 24 * 30); //存一个月
                    resp.addCookie(cookie);

                    String time = cookie.getValue();
                    resp.getWriter().write("<h1>欢迎回来, 您上次访问时间为:" + URLDecoder.decode(time, "utf-8") + "</h1>");
                    break;
                }
            }
        }

        if(cookies == null || cookies.length ==0 || flag == false) {

            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String str_date = simpleDateFormat.format(date);

            Cookie c = new Cookie("lastTime", URLEncoder.encode(str_date, "utf-8"));
            c.setMaxAge(60 * 60 * 24 * 30); //存一个月
            resp.addCookie(c);
            resp.getWriter().write("<h1>您好, 欢迎首次访问</h1>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
