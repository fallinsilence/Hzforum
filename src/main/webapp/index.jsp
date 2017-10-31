<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>胡子贴吧</title>
</head>
<style>
    a {
        text-decoration: none;
    }

    #whole, #b {
        text-align: center;
    }
</style>
<body>
<div id="whole">
    <c:if test="${not empty user}" var="flag">
        <div>
            <div>
                <a href="jsps/user/home.jsp">${user.nickname}</a>,欢迎回到胡子贴吧！
            </div>
            <br>
            <br>
        </div>
    </c:if>

    <c:if test="${!flag}">
        亲，请去<a href="jsps/user/login.jsp">登录</a>,如无账号，请去<a href="jsps/user/register.jsp">注册</a>
    </c:if>


    <div id="b">
        <form action="${pageContext.request.contextPath}/forum/selectForum.action" method="post">
            <input type="text" name="selectForum">
            <input type="submit" value="进入贴吧">
        </form>
    </div>

    <div>
        <a href="">贴吧推荐</a>
        <a href="">帖子推荐</a>
    </div>
</div>
</body>
</html>
