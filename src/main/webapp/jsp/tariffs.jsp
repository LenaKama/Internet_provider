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

</head>
<body>
<%@include file="part/header.jsp" %>

<div class="container">
    <ul class="nav nav-tabs nav-justified" role="tablist">
        <li class="nav-item active">
            <a class="nav-link" href="#limited" data-toggle="tab" role="tab">
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
                    <%@include file="part/tariff_list.jsp" %>
                </c:if>
            </c:forEach>
        </div>
        <div class="tab-pane fade" id="unlimited" role="tabpanel">
            <c:forEach items="${requestScope.tariffList}" var="tariff" varStatus="theCount">
                <c:if test="${tariff.trafficLimit == 0}">
                    <%@include file="part/tariff_list.jsp" %>
                </c:if>
            </c:forEach>
        </div>
    </div>
    <%@include file="fragment/footer.jspf" %>
</div>

<script src="../js/bootstrap/jquery.min.js"></script>
<script src="../js/bootstrap/bootstrap.min.js"></script>
<script src="../js/ajax.js"></script>
<script src="../js/carousel.js"></script>
<script src="../js/script.js"></script>


<script>
    function changeTariff(tId) {
        if (confirm('Are you sure you want to change your tariff?')) {
            alert("Done");
            $.get("AjaxHandler", {"command": "changeTariff", "tId": tId}, function (responseText) {
                if (responseText === "true") {
                    alert("Tariff is successfully changed.");
                } else {
                    alert("There isn't enough money on your account.")
                }
            });
        } else {

        }
    }

    $('.info-field').click(function () {
        if (${sessionScope.usRole eq 'admin'}) {
            $(this).hide();
            $(this).closest('.field').children('.edit-field').show();
            $(this).closest('#panel_info').children('.saveButton').removeAttr("disabled");
        }
    });
</script>
</body>
</html>
