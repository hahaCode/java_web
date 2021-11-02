package com.fan.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    /**
     * 监听ServletContext对象的创建, ServletContext对象在服务器启动后自动创建
     * 在服务器启动后自动创建
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext被创建了");

        //加载资源文件
        ServletContext servletContext = sce.getServletContext();
    }

    /**
     * 在服务器关闭后, ServletContext对象被销毁, 当服务器正常关闭后该方法被调用
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext被销毁了");
    }
}
