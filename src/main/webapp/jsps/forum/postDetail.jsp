<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/29
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${post.title}</title>
</head>
<style>
/*
    #whole {
        margin: 0px auto;
    }

    #all,#comment {
        text-align: left;
        width: 800px;
        position: absolute;
        left: 500px;
    }

    #title {
        width: 800px;
    }

    #sidebar {
        width: 200px;
    }
    #content {
        position: absolute;
        top: 50px;
        left: 100px;
        width: 600px;
        line-height: 25px;
    }

    a {
        text-decoration: none;
    }

    #kuaijie {
        position: absolute;
        right: 150px;
        top: 5px;
    }
    #writeComment{
        text-align: left;
        width: 800px;
        position: absolute;
        left: 500px;
        bottom: -800px;
    }

    .sidebar {
        width: 200px;
        position: relative;
        top: 220px;
    }
    .content{
        position: relative;
        top: 320px;
        left: 100px;
        width: 600px;
        line-height: 25px;
    }
*/
    #reply{
        cursor: pointer;
        color: #2D64B3;
    }

</style>
<body>
<div id="whole">
    <form action="${pageContext.request.contextPath}/forum/selectForum.action" method="post">
        <input type="text" name="selectForum">
        <input type="submit" value="进入贴吧">
    </form>
    <div id="all">
        <div><a href="${pageContext.request.contextPath}/forum/queryPosts.action?fname=${forum.fname}">${forum.fname}吧</a> </div>
        <div id="title">
            ${post.title}
        </div>
        <div id="kuaijie">
            <a href="">只看楼主</a>
            <a href="">收藏</a>
            <span id="reply" onclick="reply()">回复</span>
        </div>
        <hr>
        <div id="sidebar">
            <div><img src="${post.user.photo}" width="80" height="80"></div>
            <div><a href="${pageContext.request.contextPath}/user/queryUser.action?uname=${post.user.uname}">${post.user.nickname}</a></div>
            <div>${post.user.signment}</div>
        </div>
        <div id="content">
            <div>${post.content}</div>
            1楼 <br>
            ${post.postTime}
        </div>
    </div>
    <div id="comment">
        <c:forEach items="${comments}" var="comment">
            <hr>
            <div class="sidebar">
                <div><img src="${comment.user.photo}" width="80" height="80"></div>
                <div><a href="${pageContext.request.contextPath}/user/queryUser.action?uname=${comment.user.uname}">${comment.user.nickname}</a></div>
                <div>${comment.user.signment}</div>
            </div>
            <div class="content">
                <div>${comment.comment}</div>
                <div>${comment.floor}楼</div>
                <div>${comment.commentTime}</div>
            </div>
        </c:forEach>

    </div>
    <div id="writeComment">
        <form method="post" action="${pageContext.request.contextPath}/forum/addComment.action">
            <textarea rows="20" cols="100" name="comment" id="replyArea"></textarea>
            <br>
            <input type="hidden" value="${user.uname}" name="uname">
            <input type="hidden" value="${post.maxFloor}" name="maxFloor">
            <input type="hidden" value="${post.pid}" name="pid">
            <input type="hidden" value="${post.pcount}" name="pcount">
            <input type="submit" value="回帖">
        </form>
    </div>
</div>
</body>
<script type="text/javascript">
    function reply() {
        document.getElementById("replyArea").focus();
    }
</script>
</html>
