<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="en_US"/>
    <fmt:setBundle basename="properties.text" var="lang"/>
    <fmt:message key="submit" bundle="${lang}" var="submit"/>
    <style>
        div {
            width: 200px;	height: 130px;
            outline: 2px solid #000;
            border-radius: 10px;
        }
    </style>
</head>
<body>
<ctg:hello role="admin">
    Hello, I'm admin.
</ctg:hello>
<div>
    <br/>
    <fmt:message key="authorization" bundle="${lang}"/>
    <form method="post" action="/Controller">
        <input type="hidden" name="command" value="authorization"/>
        <br/>
        <input type="text" name="login" value="Login"/>
        <input type="text" name="login" value="Password"/>
        <br/>
        <input type="submit" value="${submit}"/>
    </form>
</div>
</body>
</html>
