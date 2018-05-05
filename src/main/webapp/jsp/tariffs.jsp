<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%@include file="part/bundle.jsp" %>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">

    <fmt:message key="page.tariffs.title" bundle="${loc}" var="title"/>
    <title>${title}</title>

    <script>
        function showMore(count) {
            var angleStatus = document.getElementById("angle" + count).className;
            if (angleStatus === "fa fa-angle-down") {
                document.getElementById("angle" + count).className = "fa fa-angle-up";
            } else {
                document.getElementById("angle" + count).className = "fa fa-angle-down";
            }
            var info = document.getElementById("tariffInfo" + count);
            if (info.style.display === "none") {
                info.style.display = "inline";
            } else {
                info.style.display = "none";
            }
        }
    </script>
</head>
<body>
<%@include file="part/header.jsp" %>

<div class="container">
    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" href="#limited" data-toggle="tab" role="tab">
                <fmt:message key="page.tariffs.limited" bundle="${loc}"/></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#unlimited" data-toggle="tab" role="tab">
                <fmt:message key="page.tariffs.unlimited" bundle="${loc}"/></a>
        </li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane fade in active" id="limited" role="tabpanel">
            <c:forEach items="${requestScope.tariffList}" var="tariff" varStatus="theCount">
                <c:if test="${tariff.trafficLimit != 0}">
                <div class="panel panel-default">
                    <div class="panel-heading">
                            ${tariff.tName}
                        <span class="label label-default">${tariff.connectionPayment}</span>
                        <button class="btn btn-primary" type="button" onclick="showMore(${theCount.count})">
                            <i id="angle${theCount.count}" class="fa fa-angle-down"></i>
                        </button>
                        <form class="glyphicon-align-right" action="/Controller" method="post">
                            <button type="submit" class="btn-primary">
                                <input type="hidden" name="command" value="change_tariff">
                                <fmt:message key="tariff.button.connect" bundle="${loc}"/></button>
                        </form>
                    </div>
                    <div class="panel" id="tariffInfo${theCount.count}" style="display: none">
                        <div class="panel-body card-block card-body">
                            <p><label class="label-info"><fmt:message key="tariff.dailyFee" bundle="${loc}"/></label>
                                    ${tariff.dailyFee}</p>
                            <p><label class="label-info">
                                <fmt:message key="tariff.trafficLimit" bundle="${loc}"/></label>
                                    ${tariff.trafficLimit}</p>
                            <p><label class="label-info"><fmt:message key="tariff.speedIn" bundle="${loc}"/></label>
                                    ${tariff.speedIn}</p>
                            <p><label class="label-info"><fmt:message key="tariff.speedOut" bundle="${loc}"/></label>
                                    ${tariff.speedOut}</p>
                            <p><label class="label-info"><fmt:message key="tariff.overrunFee" bundle="${loc}"/></label>
                                    ${tariff.overrunFee}</p>
                                <%--sale--%>
                        </div>
                    </div>
                </div>
                </c:if>
            </c:forEach>
        </div>
        <div class="tab-pane fade" id="unlimited" role="tabpanel">
            <c:forEach items="${requestScope.tariffList}" var="tariff" varStatus="theCount">
                <c:if test="${tariff.trafficLimit eq 0}">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                                ${tariff.tName}
                            <span class="label label-default">${tariff.connectionPayment}</span>
                            <button class="btn btn-primary" type="button" onclick="showMore(${theCount.count})">
                                <i id="angle${theCount.count}" class="fa fa-angle-down"></i>
                            </button>
                        </div>
                        <div class="panel" id="tariffInfo${theCount.count}" style="display: none">
                            <div class="panel-body card-block card-body">
                                <p><label class="label-info"><fmt:message key="tariff.dailyFee" bundle="${loc}"/></label>
                                        ${tariff.dailyFee}</p>
                                <p><label class="label-info"><fmt:message key="tariff.speedIn" bundle="${loc}"/></label>
                                        ${tariff.speedIn}</p>
                                <p><label class="label-info"><fmt:message key="tariff.speedOut" bundle="${loc}"/></label>
                                        ${tariff.speedOut}</p>
                                    <%--sale--%>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
<div class="navbar navbar-fixed-bottom bg-primary">
    <%@include file="fragment/footer.jspf" %>
</div>

<script src="../js/bootstrap/jquery.min.js"></script>
<script src="../js/bootstrap/bootstrap.min.js"></script>

</body>
</html>