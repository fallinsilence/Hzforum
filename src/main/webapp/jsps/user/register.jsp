<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/user/register.action" method="post" onsubmit="return beforeSubmit(this)" id="form">
    <table border="1">
        <tr>
            <td> 用户名</td>
            <td><input type="text" name="username" id="username" onblur="usernameCheck()"></td>
            <td><span id="uSpan"></span></td>
        </tr>

        <tr>
            <td> 性别</td>
            <td>
                <select name="gender">
                    <option value="男">男</option>
                    <option value="女">女</option>
                    <option value="保密">保密</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>昵称</td>
            <td>
                <input type="text" name="nickname">
            </td>
        </tr>

        <tr>
            <td> 手机号码</td>
            <td><input type="text" name="cellphone" id="cellphone" onblur="phoneCheck()"></td>
            <td><span id="cSpan"></span></td>
        </tr>

        <tr>
            <td> 邮箱</td>
            <td><input type="email" name="email" id="email" onblur="emailCheck()"></td>
            <td><span id="eSpan"></span></td>
        </tr>

        <tr>
            <td> 密码</td>
            <td><input type="password" id="pass1" onblur="checkPassword()"></td>
        </tr>

        <tr>
            <td> 确认密码</td>
            <td><input type="password" name="password" id="pass2" onblur="checkPassword()"></td>
            <td><span id="pSpan"></span></td>
        </tr>
        <input type="hidden" name="reClick" id="reClick">

        <tr>
            <td colspan="2"><input type="submit" value="注册"></td>
        </tr>
    </table>
</form>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
<script src="${pageContext.request.contextPath}/js/check.js"></script>
</html>
