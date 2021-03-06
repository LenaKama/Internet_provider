<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@include file="part/bundle.jsp" %>
    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <fmt:message key="page.tariffs.title" bundle="${loc}" var="title"/>
    <title>${title}</title>
</head>
<body>
<%@include file="part/header.jsp" %>
<div class="container">
    <%@include file="part/menu.jsp" %>
    <div class="card form-group">
        <label class="label-default text-center" style="background-color: rgba(5, 250, 224, 0.5);">
            <fmt:message key="traffic_status.uploaded" bundle="${loc}"/></label>
        <div class="progress">
            <div class="progress-bar" role="progressbar" aria-valuenow="${trafficInStatus}"
                 aria-valuemin="0" aria-valuemax="${currentTariff.trafficLimit}"
                 style="width:${trafficInStatus / currentTariff.trafficLimit * 100}%">
                ${trafficInStatus}
            </div>
        </div>
        <label class="text-center" style="background-color: rgba(5, 250, 224, 0.5);">
            <fmt:message key="traffic_status.downloaded" bundle="${loc}"/></label>
        <div class="progress">
            <div class="progress-bar" role="progressbar" aria-valuenow="${trafficOutStatus}"
                 aria-valuemin="0" aria-valuemax="${currentTariff.trafficLimit}"
                 style="width:${trafficOutStatus / currentTariff.trafficLimit * 100}%">
                ${trafficOutStatus}
            </div>
        </div>
    </div>
</div>
<div style="margin-left: 800px">
    <%@include file="part/feedback_form.jsp" %>
</div>
<%@include file="part/footer.jspf" %>
</body>
</html>