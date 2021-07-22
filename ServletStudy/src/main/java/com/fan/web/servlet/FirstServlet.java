package com.fan.web.servlet;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {

    /**
     * 初始化方法, 在Servlet创建时执行, 只会执行一次
     * 默认情况下, 当Servlet第一次被访问时创建
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init.....");
    }

    /**
     * 获取ServletConfig对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 提供服务的方法, 每一次Servlet被访问时执行, 执行多次
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("This my FirstServlet");
    }

    /**
     * 获取Servlet的一些信息, 版本, 作者等...
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 销毁: 在服务器正常关闭时执行, 只执行一次
     *
     */
    @Override
    public void destroy() {
        System.out.println("destroy.....");
    }
}
