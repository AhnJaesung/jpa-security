<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
<form action="/register" method="POST">
    아이디(이메일) : <input type="text" name="uemail"/><br>
    비밀번호 : <input type="password" name="upw"/><br>
    닉네임 : <input type="text" name="uid"/><br>
    권한 : <input type="text" name="role"/>
    <input type="submit" value="확인">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</body>
</html>