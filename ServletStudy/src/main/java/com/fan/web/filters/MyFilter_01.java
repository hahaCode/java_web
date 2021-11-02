package com.fan.web.filters;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter_01 implements Filter {
    /**
     * 在服务器启动后, 会创建Filter对象, 然后调用init方法, 只会执行一次, 用于加载资源
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init....");
    }

    /**
     * 每一次请求被拦截资源时, 都会被执行
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //先对request对象的请求消息增强
        System.out.println("doFilter....");

        //放行
        filterChain.doFilter(servletRequest, servletResponse);

        //接下来可以对response对象的响应消息增强
    }

    /**
     * 在服务器关闭后, Filter对象被销毁, 如果服务器是正常关闭, 则会执行destroy方法, 只会执行一次, 用于释放资源
     */
    @Override
    public void destroy() {
        System.out.println("destroy....");
    }
}
