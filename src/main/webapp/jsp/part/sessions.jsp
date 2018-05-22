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
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
<%@include file="client_menu.jsp"%>
<table class="table">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="sessions.table.sessionBeginning" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="sessions.table.duration" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="sessions.table.uploadingTraffic" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="sessions.table.downloadingTraffic" bundle="${loc}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="session" items="${sessionList}" varStatus="status">
        <tr>
            <td>${session.sessionStart}</td>
            <td>${session.sessionEnd}</td>
            <td>${session.trafficIn}</td>
            <td>${session.trafficOut}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>
