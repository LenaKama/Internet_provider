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
    <div class="form-group text-center label-info-lg">
        <fmt:message key="help.heading" bundle="${loc}"/>
    </div>
    <c:forEach items="${userFeedbacks}" var="userFeedback" varStatus="theCount">
        <c:if test="${empty userFeedback.usLogin}">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <label class="form-group label-info text-left"><c:out value="${userFeedback.fName}"/></label>
                    <label class="form-group text-muted" style="display: inline-block;">
                        <c:out value="${userFeedback.fEmail}"/></label>
                </div>
                <div class="panel-body">
                    <div class="form-control-lg card card-body nav-justified">
                        <c:out value="${userFeedback.fMessage}"/></div>
                </div>
                <div class="panel-footer">
                    <button class="btn btn-info col-md-offset-11 form-group" onclick="showReplyForm(${theCount.count})">
                        <fmt:message key="form.button.reply" bundle="${loc}"/></button>
                    <div class="panel-body" id="reply_form${theCount.count}" style="display: none;">
                        <form action="/Controller" method="post">
                            <input type="hidden" name="command" value="reply_on_feedback">
                            <input type="hidden" name="fId" value="${userFeedback.fId}">
                            <input class="form-control col-md-11 form-group" type="text" name="fAnswer"
                                   placeholder='<fmt:message key="message.input.reply" bundle="${loc}"/>'/>
                            <button class="btn btn-info col-md-offset-11 form-group" type="submit">
                                <fmt:message key="form.button.enter" bundle="${loc}"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>

<script src="../js/bootstrap/bootstrap.min.js"></script>
<script src="../js/bootstrap/jquery.min.js"></script>
<script src="../js/ajax.js"></script>
<script src="../js/carousel.js"></script>
<script src="../js/script.js"></script>

</body>
</html>
