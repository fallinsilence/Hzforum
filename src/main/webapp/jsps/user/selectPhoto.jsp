<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择头像</title>
</head>
<style>
    #allPhoto{
        text-align: center;
    }
    #show{
        display: none;
        position: absolute;
        left: 600px;
    }
</style>
<body>
<a href="${pageContext.request.contextPath}/jsps/user/home.jsp">我的主页</a>
<div id="allPhoto">
    <div>更改头像</div>
    <br>
    <div>方式一：上传自定义头像</div>
    <form action="${pageContext.request.contextPath}/user/updatePhoto.action" method="post"
          enctype="multipart/form-data">
        <input type="file" name="file"> <br>
        <input type="hidden" name="uname" value="${user.uname}">
        <br>
        <br>

    <div>方式二：使用推荐的头像</div>
    <div>
        <img src="/Hzforum/photo/recommend/1.jpg" height="80" width="80" id="photo1" onclick="update(this)">
        <img src="/Hzforum/photo/recommend/2.jpg" height="80" width="80" id="photo2" onclick="update(this)">
        <img src="/Hzforum/photo/recommend/3.jpg" height="80" width="80" id="photo3" onclick="update(this)">
        <img src="/Hzforum/photo/recommend/4.jpg" height="80" width="80" id="photo4" onclick="update(this)">
    </div>

    <div>
        <img src="/Hzforum/photo/recommend/5.jpg" height="80" width="80" id="photo5" onclick="update(this)">
        <img src="/Hzforum/photo/recommend/6.jpg" height="80" width="80" id="photo6" onclick="update(this)">
        <img src="/Hzforum/photo/recommend/7.jpg" height="80" width="80" id="photo7" onclick="update(this)">
        <img src="/Hzforum/photo/recommend/8.jpg" height="80" width="80" id="photo8" onclick="update(this)">
    </div>

    <div>
        <img src="/Hzforum/photo/recommend/9.jpg" height="80" width="80" id="photo9" onclick="update(this)">
        <img src="/Hzforum/photo/recommend/10.jpg" height="80" width="80" id="photo10" onclick="update(this)">
        <img src="/Hzforum/photo/recommend/11.jpg" height="80" width="80" id="photo11" onclick="update(this)">
        <img src="/Hzforum/photo/recommend/12.jpg" height="80" width="80" id="photo12" onclick="update(this)">
    </div>

    <div>
        <img src="/Hzforum/photo/recommend/13.jpg" height="80" width="80" id="photo13" onclick="update(this)">
        <img src="/Hzforum/photo/recommend/14.jpg" height="80" width="80" id="photo14" onclick="update(this)">
    </div>
    -------------------------------------------------------------------------------------------
    <input type="hidden" id="path" name="path">
    <div id="show">
        你选中的图片为：<img src="" id="select" height="80" width="80" name="photo">
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <input type="submit" value="保存头像" onclick="window.location.href = ('${pageContext.request.contextPath}/user/test.action')">
    </form>
    ${msg}
</div>

</body>
<script type="text/javascript">
 function update(who) {
     //将要上传图片的路径存到隐藏域中
     var a = document.getElementById(who.id).src;
     var path = a.substring(a.indexOf("/Hzforum"), a.length);
     document.getElementById("path").value = path;
     document.getElementById("select").src = path;
     //使一个隐藏的图片可见
     document.getElementById("show").style.display = "block";
 }
</script>
</html>
