<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@include file="part/bundle.jsp" %>

    <fmt:message key="page.about_us.title" bundle="${loc}" var="title"/>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png" type="image/x-icon">

    <title>${title}</title>
</head>
<body>
<%@include file="part/header.jsp" %>
<div class="container">
    <div class="about-us text-center form-group">
        <fmt:message key="page.about_us.slogan" bundle="${loc}"/>
    </div>
    <form action="/Controller" method="post">
        <input type="hidden" name="command" value="show_welcome_page"/>
        <input class="imgLogo" type="image" src="${imgLogo}" onclick="$(this).parent('form').submit()"/>
    </form>
    <div class="form-group center-block" style="margin: 30px">
        <fmt:message key="page.about_us.info" bundle="${loc}"/>
    </div>
    <form action="/Controller" method="post">
        <input type="hidden" name="command" value="show_welcome_page"/>
        <button class="btn btn-primary" type="submit">
            <fmt:message key="about_us.button.want_to_connect" bundle="${loc}"/></button>
    </form>
</div>
</body>
</html>
