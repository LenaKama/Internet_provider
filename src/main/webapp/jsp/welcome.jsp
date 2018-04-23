<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="alert" var="alert"/>
<%--<c:set var="command" value="${requestScope.command}"/>--%>

<%--<fmt:setLocale value="en_US"/>--%>
<%--<fmt:setBundle basename="prop.locale.text" var="loc"/>--%>

<%--<fmt:setBundle basename="by.tr.hotelbooking.localization.front-end" scope="session" var="loc"/>--%>

<%--<!DOCTYPE html>--%>
<html>
<head>
   <%@include file="part/bundle.jsp"%>

 <fmt:message key="page.home.title" bundle="${loc}" var="title"/>
    <fmt:message key="submit" bundle="${loc}" var="submit"/>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">
    
    <title>${title}</title>
</head>
<body>

<%@include file="part/header.jsp" %>
<div class="container">
<%@include file="sign_in.jsp"%>
</div>


<%@include file="fragment/footer.jspf" %>
</body>
</html>
