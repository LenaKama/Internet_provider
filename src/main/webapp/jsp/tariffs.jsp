<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <%@include file="part/bundle.jsp" %>

    <meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png" type="image/x-icon">
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">

    <fmt:message key="page.tariffs.title" bundle="${loc}" var="title"/>
    <title>${title}</title>

</head>
<body>
<%@include file="part/header.jsp" %>

<div class="container">
    <div class="form-group text-center" style="font-weight: bold;font-size: 20px;">
        <fmt:message key="tariffs.heading" bundle="${loc}"/>
    </div>
    <div class="form-group text-center">
        <c:if test="${sessionScope.usRole eq 'admin'}">
            <button id="add_tariff_button" class="btn btn-info btn-group-lg">
                <fmt:message key="tariffs.button.add_new_tariff" bundle="${loc}"/>
            </button>

            <div class="modal fade" id="tariff_modal" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header" style="padding:35px 50px;">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h2><fmt:message key="tariffs.button.add_new_tariff" bundle="${loc}"/></h2>
                        </div>
                        <div class="modal-body" style="padding:40px 50px;">
                            <form role="form" action="/Controller" method="post">
                                <input type="hidden" name="command" value="add_new_tariff">

                                <div class="form-group">
                                    <label for="tName"><fmt:message key="tariff.name" bundle="${loc}"/></label>
                                    <input type="text" class="form-control" id="tName" name="tName">
                                </div>
                                <div class="form-group">
                                    <label for="connectionPayment">
                                        <fmt:message key="tariff.connectionPayment" bundle="${loc}"/></label>
                                    <input type="text" class="form-control" id="connectionPayment"
                                           name="connectionPayment">
                                </div>
                                <div class="form-group">
                                    <label for="dailyFee"><fmt:message key="tariff.dailyFee" bundle="${loc}"/></label>
                                    <input type="text" class="form-control" id="dailyFee" name="dailyFee">
                                </div>
                                <div class="radio">
                                    <div class="form-group">
                                        <input type="radio" id="limited_tariff" name="tariffType" value="limit"
                                               checked/>
                                        <fmt:message key="tariff.limited" bundle="${loc}"/>
                                    </div>
                                    <div class="form-group">
                                        <input type="radio" id="unlimited_tariff" name="tariffType" value="unlimit"/>
                                        <fmt:message key="tariff.unlimited" bundle="${loc}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="trafficLimit">
                                        <fmt:message key="tariff.trafficLimit" bundle="${loc}"/></label>
                                    <input type="text" class="form-control" id="trafficLimit" name="trafficLimit">
                                </div>
                                <div class="form-group">
                                    <label for="speedIn"><fmt:message key="tariff.speedIn" bundle="${loc}"/></label>
                                    <input type="text" class="form-control" id="speedIn" name="speedIn">
                                </div>
                                <div class="form-group">
                                    <label for="speedOut"><fmt:message key="tariff.speedOut" bundle="${loc}"/></label>
                                    <input type="text" class="form-control" id="speedOut" name="speedOut">
                                </div>
                                <div class="form-group">
                                    <label for="overrunFee">
                                        <fmt:message key="tariff.overrunFee" bundle="${loc}"/></label>
                                    <input type="text" class="form-control" id="overrunFee" name="overrunFee">
                                </div>
                                <button type="submit" class="btn btn-info btn-block">
                                    <fmt:message key="tariffs.button.add_new_tariff" bundle="${loc}"/>
                                </button>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-danger btn-default pull-right" data-dismiss="modal">
                                <fmt:message key="form.button.cancel" bundle="${loc}"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
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
</div>
<%@include file="part/footer.jspf" %>

<script src="../js/bootstrap/jquery.min.js"></script>
<script src="../js/bootstrap/bootstrap.min.js"></script>
<script src="../js/script.js"></script>

<script>
    $("#add_tariff_button").click(function () {
        $("#tariff_modal").modal();
    });
    $('input[type=radio][name=tariffType]').change(function () {
        if (this.value == 'limit') {
            $('#trafficLimit').attr('disabled', false);
            $('#overrunFee').attr('disabled', false);
        }
        else if (this.value == 'unlimit') {
            $('#trafficLimit').attr('disabled', true);
            $('#overrunFee').attr('disabled', true);
        }
    });
    function changeTariff(tId) {
        if (confirm('Are you sure you want to change your tariff?')) {
            $.get("AjaxHandler", {"command": "changeTariff", "tId": tId, "usLogin":${sessionScope.usLogin}}, function (responseText) {
                if (responseText === "true") {
                    alert("Tariff is successfully changed.");
                } else {
                    alert("There isn't enough money on your account.")
                }
            });
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
