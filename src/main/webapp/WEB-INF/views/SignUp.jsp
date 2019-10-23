<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Sign up</title>
    <meta charset="UTF-8">
    <link href="<c:url value="/resources/assets/css/SignupStyle.css"/>" rel="stylesheet">
</head>
<body>
<h3>Вы не зарегистрированы! Введите данные для регистрации.</h3>
<h4></h4>
<%--@elvariable id="user" type="application.entity.User"--%>
<form:form method="POST" action="addUser" modelAttribute="user">
    <table width="200%" cellspacing="0" cellpadding="4">
        <tr>
            <td align="right" width="150">*Имя:</td>
            <td><form:input type="text" name="name" maxlength="50" size="20" path="name" value=""/></td>
        </tr>
        <tr>
            <td align="right">*Фамилия:</td>
            <td><form:input type="text" name="surname" maxlength="50" size="20" path="surname"
                      value=""/></td>
        </tr>
        <tr>
            <td align="right">*Email:</td>
            <td><form:input type="text" name="email" maxlength="50" size="20" path="email"
                      value=""/></td>
        </tr>
        <tr>
            <td align="right">*Пароль:</td>
            <td><form:input type="text" name="password" maxlength="50" size="20" path="password"
                      value=""/></td>
        </tr>
        <tr>
            <td align="right">*Подтверждение пароля:</td>
            <td><input type="text" name="COPY_PASSWORD" maxlength="50" size="20" path=""
                      value=""/></td>
        </tr>
        <tr>
            <td align="right">*Дата рождения:</td>
            <td><form:input type="text" name="dateOfBirth" maxlength="50" size="20" path="dateOfBirth"
                      value=""/></td>
        </tr>
        <tr>
            <td align="right">Пол:</td>
            <td>
                <form:select type="select" name="GENDER" rows="1" path="gender">
                    <form:option value="мужской"/>
                    <form:option value="женский"/>
                    <form:option value="не определилось"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td align="right">Хотите поговорить о баге?</td>
            <td>
                <form:radiobutton name="bug" type="radio" value="Аминь" path="bug"/> Аминь
                <form:radiobutton name="bug" type="radio" value="Алюминь" path="bug"/> Алюминь
                <form:radiobutton name="bug" type="radio" value="Нет" path="bug" checked="true"/> Нет
            </td>
        </tr>
        <tr>
            <td align="right" valign="top">Комментарий</td>
            <td><form:textarea name="COMMENTS" cols="49" rows="10" path="comment"/></td>
        </tr>
    </table>
    <p>* - поля, обязательные для заполнения</p>
    <br>
    <input type="submit" value="Регистрация">
</form:form>
</body>
</html>
