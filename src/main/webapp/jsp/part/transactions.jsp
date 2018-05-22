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
        <th scope="col"><fmt:message key="transactions.table.date" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="transactions.table.status" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="transactions.table.sum" bundle="${loc}"/></th>
        <th scope="col"><fmt:message key="transactions.table.Info" bundle="${loc}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="transaction" items="${transactionList}" varStatus="status">
        <tr>
            <td>${transaction.trDate}</td>
            <td>
                <c:if test="${transaction.trSum > 0}">
                    <label class="label-success"><fmt:message key="transaction.status.debited" bundle="${loc}"/></label>
                </c:if>
                <c:if test="${transaction.trSum < 0}">
                    <label class="label-danger"><fmt:message key="transaction.status.credited" bundle="${loc}"/></label>
                </c:if>
            </td>
            <td>${transaction.trSum}</td>
            <td>${transaction.trInfo}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
    <%@include file="../fragment/footer.jspf"%>
</div>
</body>
</html>