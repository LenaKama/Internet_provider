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
    <fmt:message key="page.tariffs.title" bundle="${loc}" var="title"/>
    <title>${title}</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
<%@include file="menu.jsp"%>
    <label class="label-default text-center" style="background-color: rgba(5, 250, 224, 0.5);"><fmt:message key="traffic_status.uploaded" bundle="${loc}"/></label>
<div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="${trafficInStatus}"
         aria-valuemin="0" aria-valuemax="2000" style="width:70%">
        ${trafficInStatus}
    </div>
</div>
<label class="text-center" style="background-color: rgba(5, 250, 224, 0.5);"><fmt:message key="traffic_status.downloaded" bundle="${loc}"/></label>
<div class="progress">
    <div class="progress-bar" role="progressbar" aria-valuenow="${trafficOutStatus}"
         aria-valuemin="0" aria-valuemax="2000" style="width:70%">
        ${trafficOutStatus}
    </div>
</div>
    <%@include file="../fragment/footer.jspf"%>
</div>
</body>
</html>