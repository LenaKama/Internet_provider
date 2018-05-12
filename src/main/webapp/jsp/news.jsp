<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<html>
<head>
    <%@include file="part/bundle.jsp" %>

    <fmt:message key="sign_in.authentication" bundle="${loc}" var="sign_in"/>
    <fmt:message key="client_account.title" bundle="${loc}" var="account"/>
    <c:choose>
        <c:when test="${sessionScope.usRole eq 'quest'}">
            <c:set var="title" value="${sign_in}"/>
        </c:when>
        <c:otherwise>
            <c:set var="title" value="${account}"/>
        </c:otherwise>
    </c:choose>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png" type="image/x-icon">

    <title>${title}</title>

</head>
<body>
<%@include file="about_us.jsp"%>
</body>
</html>
