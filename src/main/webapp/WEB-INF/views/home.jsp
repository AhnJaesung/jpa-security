<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Home</title>
</head>
<body>
<sec:authorize access="isAnonymous()">
<a href="/login">로그인</a> / <a href="/registerForm">회원가입</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<form action="/logout" method="POST">
    <input type="submit" value="로그아웃">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
</sec:authorize>
</body>
</html>