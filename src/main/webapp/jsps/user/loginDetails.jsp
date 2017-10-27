<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录记录</title>
</head>
<style type="text/css">
    a{
        text-decoration: none;
    }
</style>
<body>
<a href="${pageContext.request.contextPath}/jsps/user/home.jsp" style="text:20px">我的主页</a>
<table align="center" width="100%" border="1">
    <tr>
        <td>
            登录时间
        </td>
        <td>
            参考地点
        </td>
    </tr>

    <c:forEach items="${page.ipList}" var="login">
        <tr>
            <td>
                    ${login.loginTime}
            </td>
            <td>
                    ${login.location}
            </td>
        </tr>
    </c:forEach>
</table>

<table align="center" width="100%" border="1">
    <tr>
        当前第${page.page}页/共${page.totalPages}页|
<%--        <form action="${pageContext.request.contextPath}/user/queryLoginDetails.action">
            跳转到第<input type="text" maxlength="2" name="page">页<input type="submit" value="跳转">
            <input type="hidden" name="uname" value="${user.uname}">
        </form>
        |--%>
        <a href="${pageContext.request.contextPath}/user/queryLoginDetails.action?uname=${user.uname}&page=1">首页</a>|

        <c:if test="${page.page > 1}">
            <a href="${pageContext.request.contextPath}/user/queryLoginDetails.action?uname=${user.uname}&page=${page.page - 1}">上一页</a>|
        </c:if>

        <%--工具条--%>
        <c:forEach begin="${page.page-4>0 ? page.page-4 : 1}" step="1"
                   end="${page.page+5 < page.totalPages ? page.page+5 : page.totalPages}" var="p">
            <c:if test="${p == page.page}" var="flag">
                ${p} |
            </c:if>
            <c:if test="${!flag}">
                <a href="${pageContext.request.contextPath}/user/queryLoginDetails.action?uname=${user.uname}&page=${p}">${p} | </a>
            </c:if>
        </c:forEach>

        <c:if test="${page.page < page.totalPages}">
            <a href="${pageContext.request.contextPath}/user/queryLoginDetails.action?uname=${user.uname}&page=${page.page + 1}">下一页</a>|
        </c:if>

        <a href="${pageContext.request.contextPath}/user/queryLoginDetails.action?uname=${user.uname}&page=${page.totalPages}">尾页</a>
    </tr>
</table>
${info}
</body>
</html>
