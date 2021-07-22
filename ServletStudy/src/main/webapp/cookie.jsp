<%--
   用jsp简化CookieDemo的案例   https://www.bilibili.com/video/BV1qv4y1o79t?p=300&spm_id_from=pageDriver
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>CookieDemo</title>
</head>

<body>
    <%
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

    %>
</body>
</html>
