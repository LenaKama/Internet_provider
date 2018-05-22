<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%@include file="bundle.jsp" %>

    <fmt:message key="page.clients.title" bundle="${loc}" var="title"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <title>${title}</title>

    <script>
        function showInfo(count) {
            var x = document.getElementById("clientInfo" + count);
            if (x.style.display === "none") {
                x.style.display = "block";
            } else {
                x.style.display = "none";
            }
        }
    </script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
    <%@include file="admin_menu.jsp"%>
<div class="panel-group">
    <c:forEach items="${requestScope.clients}" var="client" varStatus="theCount">
    <div class="panel panel-default">
        <div class="panel-heading">
                ${client.usName}
            <button class="btn-primary" onclick="showInfo(${theCount.count})">Show more</button>
        </div>
        <div id="clientInfo${theCount.count}" class="panel-body" style="display: none">
            Client info:
                ${client.usEmail}
        </div>
    </div>
    </c:forEach>
<%--<%@include file="../fragment/footer.jspf"%>--%>
</div>
        <script src="../../js/bootstrap/jquery.min.js"></script>
        <script src="../../js/bootstrap/bootstrap.min.js"></script>

</body>
</html>
