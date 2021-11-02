package com.fan.filter;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 过滤敏感词汇, 如张三, 笨蛋
 */
public class SensitiveWordsFilter implements Filter {

    private List<String> list = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            ServletContext servletContext = filterConfig.getServletContext();
            String realPath = servletContext.getRealPath("sensitiveWords.txt");
            System.out.println(realPath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(realPath));

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }

            bufferedReader.close();
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter....");
        //1.创建代理对象, 增强getParameter方法
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(servletRequest.getClass().getClassLoader(), servletRequest.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("getParameter")) {
                    String value = (String) method.invoke(servletRequest, args);
                    if (value!=null) {
                        for (String s : list) {
                            if (value.contains(s)) {
                                value = value.replaceAll(s, "***");
                            }
                        }
                    }
                    return value;
                }

                return method.invoke(servletRequest, args);
            }
        });

        filterChain.doFilter(proxy_req, servletResponse);
    }
}
