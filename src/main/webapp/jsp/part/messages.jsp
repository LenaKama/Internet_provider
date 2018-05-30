<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@include file="bundle.jsp" %>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <fmt:message key="page.messages.title" bundle="${loc}" var="title"/>
    <title>${title}</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
<c:choose>
<c:when test="${sessionScope.usRole eq 'admin'}">
    <%@include file="admin_menu.jsp" %>
</c:when>
<c:otherwise>
    <%@include file="client_menu.jsp" %>
</c:otherwise>
</c:choose>
<c:forEach items="${userFeedbacks}" var="userFeedback" varStatus="theCount">

</c:forEach>
</div>
</body>
</html>
