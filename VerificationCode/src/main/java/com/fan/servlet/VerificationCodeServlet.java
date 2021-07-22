package com.fan.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 一个简单的验证码实现
 *
 */

public class VerificationCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 在内存中创建一个验证码图片对象
        int width = 100;
        int height = 60;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2. 美化图片

        //2.1 填充背景色
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.pink);
        graphics.fillRect(0, 0, width, height);

        //2.2 边框
        graphics.setColor(Color.blue);
        graphics.drawRect(0, 0, width-1, height-1);

        //2.3 写验证码
        String str = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
        Random random = new Random();

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(str.length());
            char c = str.charAt(index);
            sb.append(c);
            graphics.drawString(c+"", width/5*i, height/2);
        }

        String checkCode = sb.toString();
        req.getSession().setAttribute("myCheckCode", checkCode);
        //2.4画干扰线
        graphics.setColor(Color.green);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1, x2, y1, y2);
        }

        //3. 将图片输出到页面展示
        ImageIO.write(bufferedImage, "jpg", resp.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
