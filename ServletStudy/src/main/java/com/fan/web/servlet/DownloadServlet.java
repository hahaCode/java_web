package com.fan.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 案例: 下载文件
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String filename = req.getParameter("filename");

        //2.使用字节输入流加载文件到内存
        ServletContext servletContext = this.getServletContext();
        String filePath = servletContext.getRealPath(filename);

        //System.out.println("+++++++++"+filePath);
        FileInputStream fis = new FileInputStream(filePath);

        //3.设置response的响应头
        resp.setContentType(servletContext.getMimeType(filename));
        resp.setHeader("content-disposition", "attachment;filename=" + filename); //以附件形式打开

        //4.将输入流数据写出到输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(buff)) != -1) {
            outputStream.write(buff, 0, len);
        }
        fis.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
