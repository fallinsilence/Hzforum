<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>胡子贴吧</title>
</head>
<body>
<c:if test="${not empty user}" var="flag">

    <table>
        <tr>
                ${user.nickname},欢迎回到胡子贴吧！
        </tr>
        <tr>
            <td>
                <a href="">关注的吧</a>
            </td>
            <td>
                <a href="">帖子</a>
            </td>
            <td>
                <a href="">回复</a>
            </td>
            <td>
                <a href="">收藏</a>
            </td>
        </tr>
    </table>

</c:if>

<c:if test="${!flag}">
    亲，请去<a href="jsps/user/login.jsp">登录</a>,如无账号，请去<a href="jsps/user/register.jsp">注册</a>
</c:if>
</body>
</html>
