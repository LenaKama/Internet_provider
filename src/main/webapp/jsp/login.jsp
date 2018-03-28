<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
<div>
    <br/>
    <fmt:message key="selection" bundle="${lang}" />
    <form method="post" action="/FunctionalServlet">
        <input type="hidden" name="command" value="Language"/>
        <br/>
        <select name="Language" size="1">
            <option>English</option>
            <option>Russian</option>
        </select>
        <br/>
        <br/>
        <input type="submit" value="${submit}"/>
    </form>
</div>
</body>
</html>
