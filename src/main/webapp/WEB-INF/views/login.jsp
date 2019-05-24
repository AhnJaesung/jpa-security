<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script type="text/javascript" src="/resources/js/jquery-3.2.1.min.js"></script>
    <title>로그인</title>
    <script>
        /*$(document).ready(function () {
            $("#submit").click(function () {

                var formData = $("#loginForm").serialize();
                var id = $("#username").val();
                var pwd = $("#password").val();

                console.log(formData);

                if(id == "" || pwd == ""){
                    alert("아이디 또는 패스워드를 입력하세요.");
                    return false;
                }

                $.ajax({
                    url: "/loginCheck",
                    type : "POST",
                    data : formData,
                    dataType : 'json',
                    success : function (result) {
                        if(result > 0){
                            alert("로그인 되었습니다.");
                        }else{
                            alert("아이디 또는 패스워드가 일치하지 않습니다.");
                        }
                    }
                });

            });
        });*/
    </script>
</head>
<body>
<form id="loginForm" action="/loginCheck" method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="text" name="username" id="username"><br>
    <input type="password" name="password" id="password"><br>
    아이디 저장 : <input type="checkbox" name="remember"><br>
    <button type="submit" id="submit">login</button>
</form>
</body>
</html>