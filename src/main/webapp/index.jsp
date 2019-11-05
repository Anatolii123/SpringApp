<%--
  Created by IntelliJ IDEA.
  servlets.User: kusakin
  Date: 08.10.2019
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="myApp">
<head>
    <title>Sign In</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8"
          http-equiv="Cache-Control" content="nocache">
    <link href="<c:url value="/resources/assets/css/SigninStyle.css"/>" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
    <script src="/resources/js/app.js"></script>
</head>
<body ng-controller="firstCtrl">
<h4><c:out value="${sessionScope.loginError ne null ? sessionScope.loginError : ''}"/></h4>
<div ng-app>
    1 + 2 = {{1+2}}
    <p ng-repeat="task in tasksArray">{{task}}</p>
</div>
<div>
    <form id="form">
        <table width="200%" cellspacing="0" cellpadding="4">
            <tr>
                <td align="right">Email:</td>
                <td><input type="text" id="login" name="EMAIL" ng-model="tempInput" maxlength="50" size="20"
                           value="<c:out value="${sessionScope.email ne null ? sessionScope.email : ''}"/>"></td>
            </tr>
            <tr>
                <td align="right">Пароль:</td>
                <td><input type="password" id="password" name="PASSWORD" maxlength="50" size="20"
                           value="<c:out value="${sessionScope.password ne null ? sessionScope.password : ''}"/>"></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" formaction="View" formmethod="post" value="Войти" ng-click="addNew()">
                </td>
                <td>
                    <input type="submit" formaction="SignUp" formmethod="post" value="Зарегистрироваться">
                </td>
            </tr>
        </table>
        <%
            session.setAttribute("salt", Long.toHexString((long) ((Math.random() * 900000000000000000L) + 100000000000000000L)));
        %>
        <input id="salt" name="SALT"
               value="<c:out value="${sessionScope.salt ne null ? sessionScope.salt : ''}"/>">
        <script src="<c:url value="/resources/js/encodePassword.js"/>"></script>
    </form>
</div>
</body>
</html>
