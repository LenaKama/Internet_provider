<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="part/bundle.jsp" %>

<fmt:message key="sign_in.authentication" bundle="${loc}" var="sign_in"/>
<fmt:message key="user_account.title" bundle="${loc}" var="account"/>
<c:choose>
    <c:when test="${sessionScope.usRole eq 'guest'}">
        <c:set var="title" value="${sign_in}"/>
    </c:when>
    <c:otherwise>
        <c:set var="title" value="${account}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${sessionScope.usRole eq 'guest'}">
        <c:set var="bg_style" value="transparent"/>
    </c:when>
    <c:otherwise>
        <c:set var="bg_style" value="rgba(5, 250, 224, 0.1)"/>
    </c:otherwise>
</c:choose>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png" type="image/x-icon">

    <title>${title}</title>
</head>
<body>
<%@include file="part/header.jsp" %>
<div class="container" style="background-color: ${bg_style}">
    <c:if test="${sessionScope.usRole eq 'guest'}">
        <div id="myCarousel" class="carousel container slide">
            <div class="carousel-inner">
                <div class="active item one"></div>
                <div class="item two"></div>
                <div class="item three"></div>
            </div>
        </div>
    </c:if>
    <c:choose>
        <c:when test="${sessionScope.usRole eq 'guest'}">
            <%@include file="part/sign_in.jsp" %>
        </c:when>
        <c:otherwise>
            <%@include file="part/general.jsp" %>
        </c:otherwise>
    </c:choose>
    <div style="margin-left: 800px">
        <%@include file="part/feedback_form.jsp" %>
    </div>
</div>

<script src="../js/bootstrap/bootstrap.min.js"></script>
<script src="../js/bootstrap/jquery.min.js"></script>
<script src="../js/carousel.js"></script>
<script src="../js/script.js"></script>
</body>
</html>