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
    #all{
        text-align: center;
    }
    #all a{
        margin-right: 30px;
    }
    #updateNickname{
        position: absolute;
        top: 22px;
        left: 1000px;
    }
    #updateSignment{
        position: absolute;
        top: 75px;
        left: 1000px;
    }
</style>
<body>
<c:if test="${not empty user}" var="flag">
    <table align="center">
        <tr>
            <td rowspan="2">
                <img src="${user.photo}" height="80" width="80"> <br>
                <a href="${pageContext.request.contextPath}/jsps/user/selectPhoto.jsp">更改头像</a>
            </td>
            <td>
                    ${user.nickname},欢迎回到胡子贴吧！
            </td>
            <td>
                <span id="nick" style="color: red; cursor: pointer" onclick="show('updateNickname')">更改昵称</span>
            </td>
        </tr>

        <tr>
            <td>
                个人签名：${user.signment}
            </td>
            <td>
                <span id="sign" style="color: red; cursor: pointer" onclick="show('updateSignment')">更改签名</span>
            </td>
        </tr>
    </table>

    <br>
    <br>

    <form method="post" action="${pageContext.request.contextPath}/user/updateNickname.action" id="updateNickname" style="display: none">
        <input type="text" name="nickname">
        <input type="hidden" name="uname" value="${user.uname}">
        <input type="submit" value="更改">
    </form>
    <form method="post" action="${pageContext.request.contextPath}/user/updateSignment.action" id="updateSignment" style="display: none">
        <input type="text" name="signment">
        <input type="hidden" name="uname" value="${user.uname}">
        <input type="submit" value="更改">
    </form>

    <div id="all">
                <a href="">关注的吧</a>
                <a href="">帖子</a>
                <a href="">回复</a>
                <a href="">收藏</a>
                <a href="${pageContext.request.contextPath}/user/queryLoginDetails.action?uname=${user.uname}&page=1">登录记录</a>
    </div>

</c:if>

<c:if test="${!flag}">
    亲，请去<a href="login.jsp">登录</a>,如无账号，请去<a href="register.jsp">注册</a>
</c:if>
</body>
<script type="text/javascript">
    function show(who) {
        document.getElementById(who).style.display = "block";
    }
</script>
</html>
