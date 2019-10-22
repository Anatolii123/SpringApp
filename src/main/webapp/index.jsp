<%--
  Created by IntelliJ IDEA.
  servlets.User: kusakin
  Date: 08.10.2019
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Sign In</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8"
          http-equiv="Cache-Control" content="nocache">
    <link rel="stylesheet" type="text/css" href="assets/css/SigninStyle.css">
</head>
<body>
<%--<h4><c:out value="${sessionScope.loginError ne null ? sessionScope.loginError : ''}"/></h4>--%>
<div>
    <form action="SignUp" method="post">
        <table width="200%" cellspacing="0" cellpadding="4">
            <tr>
                <td align="right">Email:</td>
                <td><input type="text" name="EMAIL" maxlength="50" size="20"
                           value=""></td>
            </tr>
            <tr>
                <td align="right">Пароль:</td>
                <td><input type="text" name="PASSWORD" maxlength="50" size="20"></td>
            </tr>
        </table>
        <br>
        <input type="submit" value="Войти">
    </form>
</div>
</body>
</html>
