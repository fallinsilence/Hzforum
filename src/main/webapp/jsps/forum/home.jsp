<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${forum.fname}吧-胡子贴吧</title>
</head>
<style>
    a {
        text-decoration: none;
    }

    #all {
        text-align: left;
        width: 800px;
        position: absolute;
        left: 500px;
    }

    #fname {
        font-size: larger;
        font-style: oblique;
        color: black;
    }

    #title {
        color: #2D64B3;
    }

    #post {
        margin-bottom: 40px;
    }

    .lastReplyTime {
        position: absolute;
        right: 200px;
    }

    #lastReplyUser {
        position: absolute;
        right: 300px;
    }

    .lastReplyTime, #lastReplyUser, #uname {
        color: #999999;
    }

    #uname {
        position: absolute;
        right: 300px;
    }

    #newTitle {
        margin-top: 10px;
        margin-bottom: 10px;
    }

    #icon {
        text-align: right;
        position: fixed;
        top: 350px;
        right: 300px;
        cursor: pointer;
    }
    #newPost{
        margin-top: 10px;
        margin-bottom: 50px;
    }
</style>
<body>
<div id="all">
    <form action="${pageContext.request.contextPath}/forum/selectForum.action" method="post">
        <input type="text" name="selectForum">
        <input type="submit" value="进入贴吧">
    </form>
    <div id="fname">
        ${forum.fname}吧  &nbsp; &nbsp; 本吧创建于${forum.createTime}
    </div>
    <br>
    <br>

    <c:if test="${empty info}" var="flag">
        <c:forEach items="${forum.posts}" var="post">
            <div id="post">
                    ${post.pcount} &nbsp; <span id="title"><a href="${pageContext.request.contextPath}/forum/postDetail.action?pid=${post.pid}&uname=${post.uname}">${post.title}</a></span>
                <span id="uname">${post.uname}</span>
                &nbsp; <br>
                &nbsp; &nbsp; &nbsp;<span class="content">${post.content}</span>&nbsp; <span
                    id="lastReplyUser">${post.lastReplyUser} </span>
                <span class="lastReplyTime">${post.lastReplyTime}</span>
            </div>
        </c:forEach>
    </c:if>

    <c:if test="${!flag}">
        此吧还没有帖子，请去创建帖子！
    </c:if>

    <div>
        <form method="post" action="${pageContext.request.contextPath}/forum/addNewPost.action">
            <div>发表新帖</div>
            <div id="newTitle"><input id="focus" type="text" name="title" placeholder="请输入标题" maxlength="100"></div>
            <div>
                <textarea name="content" id="" cols="100" rows="10" placeholder="请输入内容"></textarea>
            </div>
            <input type="hidden" name="uname" value="${user.uname}">
            <input type="hidden" name="fname" value="${forum.fname}">
            <div>
                <input type="submit" value="发帖" id="newPost">
            </div>
        </form>
    </div>
</div>

<div id="icon">
    <img src="${pageContext.request.contextPath}/photo/forum/jiahao.jpg" width="50" height="50" onclick="post()">
</div>

</body>
<script type="text/javascript">
    window.onload = timeHandle();

    function dateFtt(fmt, date) { //author: meizz
        var o = {
            "M+": date.getMonth() + 1,               //月份
            "d+": date.getDate(),                    //日
            "h+": date.getHours(),                   //小时
            "m+": date.getMinutes(),                 //分
            "s+": date.getSeconds(),                 //秒
            "q+": Math.floor((date.getMonth() + 3) / 3), //季度
            "S": date.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    function post() {
        document.getElementById("focus").focus();
    }

    function timeHandle() {
        for (var i = 0;i < ${forum.posts.size()};i++)
        {
            var time = document.getElementsByClassName("lastReplyTime")[i].innerText;
            //回帖的日期
            var day = time.substring(0, time.indexOf(" "));
            //回帖的时间
            var hour = time.substring(time.indexOf(" ") + 1, time.length);
            var today = dateFtt("yyyy-MM-dd", new Date());      //yyyy-MM-dd hh:mm:ss
            if (day == today)
                document.getElementsByClassName("lastReplyTime")[i].innerText = hour;
            else
                document.getElementsByClassName("lastReplyTime")[i].innerText = day;
            //处理文本长度
            var content = document.getElementsByClassName("content")[i].innerText;
            if (content.length > 24) {
                var front = content.substring(0, 24) + "...";
                document.getElementsByClassName("content")[i].innerText = front;
            }
        }
    }

</script>
</html>
