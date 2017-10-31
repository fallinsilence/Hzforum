<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/31
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${lookedUser.nickname}</title>
</head>
<body>
    ${lookedUser.uname}
    <img src="${lookedUser.photo}" height="80" width="80">
    ${lookedUser.signment}
</body>
</html>
