<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
</body>
</html>
