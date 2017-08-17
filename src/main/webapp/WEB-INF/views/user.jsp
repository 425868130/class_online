<%--
  Created by IntelliJ IDEA.
  User: 42586
  Date: 2017/8/10
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="shortcut icon" type="image/x-icon" href="static/ico/favicon.ico"/>
</head>
<body>
<h1>${msg}</h1>
Welcome ${user.nickname},Login Time ${user.loginTime}!
<a href="userLoginOut">注销</a>

<hr>
<h2 style="color:red;">${regMsg}</h2>
</body>
</html>
