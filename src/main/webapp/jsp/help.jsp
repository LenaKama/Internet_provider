<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <%@include file="part/bundle.jsp" %>
    <fmt:message key="page.help.title" bundle="${loc}" var="title"/>

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
<div class="container">
    <div class="form-group text-center" style="margin: 20px;font-size: 20px;font-weight: bold">
        <fmt:message key="help.heading" bundle="${loc}"/>
    </div>
    <c:forEach items="${userFeedbacks}" var="userFeedback" varStatus="theCount">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <label class="form-group label-info text-left"><c:out value="${userFeedback.fName}"/></label>
                    <label class="form-group text-muted" style="display: inline-block;">
                        <c:out value="${userFeedback.fEmail}email"/></label>
                </div>
                <div class="panel-body">
                    <div class="form-control-lg card card-body nav-justified">
                        <c:out value="${userFeedback.fMessage}"/></div>
                </div>
                <div class="panel-footer card">
                    <div class="form-control-lg card card-body nav-justified">
                        <c:out value="${userFeedback.fAnswer}"/></div>
                </div>
            </div>
    </c:forEach>
    <div style="margin-left: 800px">
        <%@include file="part/feedback_form.jsp" %>
    </div>
</div>
<%@include file="part/footer.jspf"%>

<script src="../js/bootstrap/bootstrap.min.js"></script>
<script src="../js/bootstrap/jquery.min.js"></script>
<script src="../js/script.js"></script>

</body>
</html>
