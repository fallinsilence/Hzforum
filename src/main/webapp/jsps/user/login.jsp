<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/17
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <title>登录</title>
</head>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
<script>
    //刷新验证码
    function changeSrc() {
        document.getElementById("captcha").src = "${pageContext.request.contextPath}/user/captcha.action?timeStamp=" + new Date().getTime();
    }

    //刷新页面时保存上次输入的用户名和密码
    function load(){
        var username = "${ username }";
        var password = "${ password }"
        var name = decodeURIComponent(username);
        document.getElementById("username").value = name;
        document.getElementById("password").value = password;
    }
</script>
<body onload=load()>

<form action="${pageContext.request.contextPath}/user/login.action" method="post">
    <input type="text" placeholder="用户名" name="username" id="username"> <span >${info}</span> <br>
    <input type="password" placeholder="密码" name="password" id="password"> <br>

    <input type="text" placeholder="验证码" name="captcha" >
    <img src="${pageContext.request.contextPath}/user/captcha.action" id="captcha" onclick="changeSrc()" style="cursor: pointer;">
    <span id="verify">${msg}</span> <br>

    <input type="text" placeholder="请输入短信验证码" hidden>
    <input type="button" value="获取验证码" hidden>

    <%--checkbox值不为null则为勾选--%>
    <input type="checkbox" name="autoLogin"> 记住我一周        <br>
    <input type="submit" value="登录">
</form>
</body>
</html>
