<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Login</title>

    <script>
        window.onload = function(){
          var img = document.getElementById("img");
          img.onclick = function(){
            var date = new Date().getTime();
            img.src = "/VerificationCode/vericode?time="+date;
          }
        }
    </script>

    <style>
        div {
            color: red;
        }
    </style>
</head>

<body>
    <h2>Login</h2>
    <form action="/VerificationCode/login" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>验证码</td>
                <td><input type="text" name="checkCode"/></td>
            </tr>
            <tr>
                <td colspan="2"><img id="img" src="/VerificationCode/vericode"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="登录"/></td>
            </tr>

        </table>
    </form>

    <div>
        <%=request.getAttribute("msg") == null ? "":request.getAttribute("msg")%>
    </div>
</body>
</html>