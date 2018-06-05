<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="part/bundle.jsp" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png" type="image/x-icon">

    <fmt:message key="page.error.title" bundle="${loc}" var="title"/>
    <title>${title}</title>
</head>
<body>
<%@include file="part/header.jsp" %>
<div class="container">
    <div class="card bg-danger text-white">
        <div class="card-body">
            <ctg:error message="${errorMessage}"/>
        </div>
    </div>
    <div class="text-danger text-center">
        <c:out value="${errorMessage}"/>
    </div>
    <div class="panel-default">
       <c:out value="${errorTrace}"/>
    </div>
</div>

<script src="../js/bootstrap/bootstrap.min.js"></script>
<script src="../js/bootstrap/jquery.min.js"></script>
<script src="../js/script.js"></script>
</body>
</html>
