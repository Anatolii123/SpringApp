<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Sign up</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/assets/css/SignupStyle.css">
</head>
<body>
<h3>Вы не зарегистрированы! Введите данные для регистрации.</h3>
<h4></h4>
<form action="View" method="post">
    <table width="200%" cellspacing="0" cellpadding="4">
        <tr>
            <td align="right" width="150">*Имя:</td>
            <td><input type="text" name="NAME" maxlength="50" size="20"
                       value=""></td>
        </tr>
        <tr>
            <td align="right">*Фамилия:</td>
            <td><input type="text" name="SURNAME" maxlength="50" size="20"
                       value=""></td>
        </tr>
        <tr>
            <td align="right">*Email:</td>
            <td><input type="text" name="EMAIL" maxlength="50" size="20"
                       value=""></td>
        </tr>
        <tr>
            <td align="right">*Пароль:</td>
            <td><input type="text" name="PASSWORD" maxlength="50" size="20"
                       value=""></td>
        </tr>
        <tr>
            <td align="right">*Подтверждение пароля:</td>
            <td><input type="text" name="COPY_PASSWORD" maxlength="50" size="20"
                       value=""></td>
        </tr>
        <tr>
            <td align="right">*Дата рождения:</td>
            <td><input type="text" name="DATE_OF_BIRTH" maxlength="50" size="20"
                       value=""></td>
        </tr>
        <tr>
            <td align="right">Пол:</td>
            <td><select type="select" name="GENDER" rows="1">
                <option>мужской</option>
                <option>женский</option>
                <option>не определилось</option>
            </select>
            </td>
        </tr>
        <tr>
            <td align="right">Хотите поговорить о баге?</td>
            <td>
                <input name="BUG" type="radio" value="Аминь"> Аминь
                <input name="BUG" type="radio" value="Алюминь"> Алюминь
                <input name="BUG" type="radio" value="Нет" checked> Нет
            </td>
        </tr>
        <tr>
            <td align="right" valign="top">Комментарий</td>
            <td><textarea name="COMMENTS" cols="49" rows="10"></textarea></td>
        </tr>
    </table>
    <p>* - поля, обязательные для заполнения</p>
    <br>
    <input type="submit" value="Регистрация">
</form>
</body>
</html>
