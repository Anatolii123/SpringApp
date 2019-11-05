<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<head>
    <title>Sign up</title>
    <meta charset="UTF-8" pageEncoding="UTF-8">
    <link href="<c:url value="/resources/assets/css/SignupStyle.css"/>" rel="stylesheet">
    <script async src="<c:url value="/resources/js/validateForm.js"/>"></script>
</head>
<body>
<h4><c:out value="${sessionScope.Error ne null ? sessionScope.Error : ''}"/></h4>
<%--@elvariable id="user" type="application.entity.User"--%>
<form:form method="POST" id="form" action="/addUser"  modelAttribute="user" acceptCharset="utf-8" >
    <table width="200%" cellspacing="0" cellpadding="4">
        <tr>
            <td align="right" width="150"><p id="lname" style="color: red">*Имя:</p></td>
            <td><form:input type="text" onkeyup="checkForm()" id="name" path="name" required="required"/></td>
        </tr>
        <tr>
            <td align="right"><p id="lsurname" style="color: red">*Фамилия:</p></td>
            <td><form:input type="text" id="surname" onkeyup="checkForm()" path="surname" required="required"/></td>
        </tr>
        <tr>
            <td align="right"><p id="lemail" style="color: red">*Email:</p></td>
            <td><form:input type="text" id="login" path="email" onkeyup="checkForm()"
                            required="required" pattern="(([^<>()\[\]\\.,;:\s@\"]+(\.[^<>()\[\]\\.,;:\s@\"]+)*)|
                            (\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))"/></td>
            <td align="left"><output id="loginOutput" style="color: red"></output></td>
        </tr>
        <tr>
            <td align="right"><p id="lpassword" style="color: red">*Пароль:</p></td>
            <td><form:input type="password" id="password" path="password" onkeyup="checkForm()"
                            required="required" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}" minlength="8"/></td>
        </tr>
        <tr>
            <td align="right"><p id="lcopyPassword" style="color: red">*Подтверждение пароля:</p></td>
            <td><input type="password" id="copyPassword" name="COPY_PASSWORD" onkeyup="checkForm()" maxlength="50" size="20"
                       required="required" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}" minlength="8"></td>
        </tr>
        <tr>
            <td align="right"><p id="lbirth" style="color: red">*Дата рождения:</p></td>
            <td><form:input type="date" id="birth" onkeyup="checkForm()" path="dateOfBirth" required="required"/></td>
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
                <form:radiobutton name="bug" value="Аминь" path="bug"/> Аминь
                <form:radiobutton name="bug" value="Алюминь" path="bug"/> Алюминь
                <form:radiobutton name="bug" value="Нет" path="bug" checked="true"/> Нет
            </td>
        </tr>
        <tr>
            <td align="right" valign="top">Комментарий</td>
            <td><form:textarea name="COMMENTS" cols="49" rows="10" path="comments"/></td>
        </tr>
    </table>
    <input id="publicValue" name="publicValue"
           value="<c:out value="${sessionScope.publicValue ne null ? sessionScope.publicValue : ''}"/>">
    <script async src="<c:url value="/resources/js/diffieHellmanAlgorithm.js"/>"></script>
    <p>* - поля, обязательные для заполнения.</p>
    <p>Пароль должен состоять минимум из 8 символов, содержать строчные</p>
    <p>и прописные буквы латинского алфавита, а также содержать минимум</p>
    <p>одну цифру.</p>
    <br>
    <input type="submit" id="registration" value="Регистрация" disabled="disabled">
</form:form>
</body>
</html>
