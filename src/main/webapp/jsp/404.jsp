<%@ page isErrorPage="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="part/bundle.jsp" %>
<fmt:message key="404.page_not_found" var="error_message"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style-404.css" type="text/css">
</head>

<body>
<%@include file="part/header.jsp" %>
<div class="container">
    <div class="page-404">
        <img src="images/background/cables.jpg" alt="404 page picture">
        <h1>${error_message}</h1>
    </div>
</div>
</body>
</html>
