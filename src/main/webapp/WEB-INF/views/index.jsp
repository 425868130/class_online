<%--
  Created by IntelliJ IDEA.
  User: 42586
  Date: 2017/8/8
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>

    <link rel="shortcut icon" type="image/x-icon" href="static/ico/favicon.ico"/>
    <link rel="stylesheet" href="static/css/index.css">
</head>
<body>
<form action="userLogin" method="post">
    <input name="account">
    <input name="passWord" type="password">
    <input type="submit">
</form>

<form action="userRegister" method="post">
    账号：<input name="account"><br>
    密码：<input name="passWord" type="password"><br>
    邮箱:<input name="email"><br>
    电话：<input name="telephone"><br>
    <input type="submit">
</form>
</body>
</html>
