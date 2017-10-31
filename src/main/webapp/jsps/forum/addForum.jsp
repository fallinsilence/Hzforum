<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/28
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建贴吧</title>
</head>
<style>
    #allDiv{
        text-align: left;
        width: 800px;
        position: absolute;
        left: 450px;
    }
</style>
<body>
<div id="allDiv">
    <div>
        你好，${fName}吧不存在，你可以创建此贴吧
    </div>
    <br>
    <br>
    <h3>创建贴吧</h3>
    <hr>
    <div id="all">
        <form action="${pageContext.request.contextPath}/forum/addForum.action" method="post">
            <div>贴吧名称：
                <input type="text" name="fname" value="${fName}">
            </div>
            <div>
                1. 吧名不超过14个汉字，限汉字、字母、数字和下划线。 <br>
                2. 吧名不能与已有贴吧名称重复。 <br>
                3. 吧名不能包含“医疗机构、具有药用性产品名、股票期货彩票”等金融信息。<br>
                4. 普通用户注册3个月以上且全吧发言30条以上方可建吧，每月不超过2个。 <br>
            </div>
            <div>
                <input type="submit" value="创建贴吧">
            </div>
        </form>
        ${msg}
        <a href="${pageContext.request.contextPath}/jsps/user/home.jsp">我的主页</a>
    </div>
</div>
</body>
</html>
