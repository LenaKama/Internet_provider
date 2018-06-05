<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<meta name="viewport" content="width=device-width, initial-scale=1" charset="UTF-8">
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="form-inline">
            <h2 class="text-bold"><c:out value="${tariff.TName}"/></h2>
            <div class="label label-default">
                <c:out value="${tariff.connectionPayment}"/></div>
            <c:if test="${tariff.salePercent != 0}">
                <label class="label-danger">
                    <c:out value="${tariff.salePercent}% off"/>
                </label>
            </c:if>
        </div>
        <c:if test="${sessionScope.usRole eq 'client'|| sessionScope.usRole eq 'admin'}">
            <div class="row col-sm-offset-10">
                <c:if test="${sessionScope.usRole eq 'admin'}">
                    <form class="col-md-2" action="/Controller" method="post">
                        <input type="hidden" name="command" value="delete_tariff">
                        <input type="hidden" name="tId" value="${tariff.TId}"/>
                        <input type="submit" class="nav-link"
                               value='<fmt:message key="tariff.button.delete" bundle="${loc}"/>'/>
                    </form>
                </c:if>
            </div>
            <div class="row col-sm-offset-10">
                <form class="col-md-2" action="/Controller" method="post">
                    <input type="hidden" name="command" value="change_tariff">
                    <input type="hidden" name="tId" value="${tariff.TId}"/>
                    <button type="button" class="btn-primary" onclick="changeTariff(${tariff.TId})">
                        <fmt:message key="tariff.button.connect" bundle="${loc}"/></button>
                </form>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${tariff.trafficLimit != 0}">
                <c:set var="tariffType" value="100"/>
            </c:when>
            <c:otherwise>
                <c:set var="tariffType" value="200"/>
            </c:otherwise>
        </c:choose>
        <button class="btn" type="button" onclick="showMore(${tariffType+theCount.count})"
                style="width: 100%; height: 20px;">
            <i id="angle${tariffType+theCount.count}" class="fa fa-angle-down glyphicon-align-center"></i>
        </button>
    </div>
    <div class="panel" id="tariffInfo${tariffType+theCount.count}" style="display: none">
        <div class="panel-body card-block card-body">
            <form id="panel_info" action="/Controller" method="post">
                <input type="hidden" name="command" value="update_tariff">
                <input type="hidden" name="tId" value="${tariff.TId}">
                <c:if test="${sessionScope.usRole eq 'admin'}">
                    <div class="form-group row">
                        <label class="control-label label-default col-md-3" for="tName">
                            <fmt:message key="tariff.name" bundle="${loc}"/></label>
                        <div class="col-md-2 field">
                            <p id="tName" class="info-field">${tariff.TName}</p>
                            <input type="text" name="tName" class="edit-field"
                                   value="${tariff.TName}" style="display: none"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label label-default col-md-3" for="connectionPayment">
                            <fmt:message key="tariff.connectionPayment" bundle="${loc}"/></label>
                        <div class="col-md-2 field">
                            <p id="connectionPayment" class="info-field">
                                <c:out value="${tariff.connectionPayment}"/></p>
                            <input type="text" name="connectionPayment" class="edit-field"
                                   value="${tariff.connectionPayment}" style="display: none"/>
                        </div>
                    </div>
                </c:if>
                <div class="form-group row">
                    <label class="control-label label-default col-md-3" for="daily_fee">
                        <fmt:message key="tariff.dailyFee" bundle="${loc}"/></label>
                    <div class="col-md-2 field">
                        <p id="daily_fee" class="info-field">
                            <c:out value="${tariff.dailyFee}"/></p>
                        <input type="text" name="dailyFee" class="edit-field" value="${tariff.dailyFee}"
                               style="display: none"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="control-label label-default col-md-3" for="speedIn">
                        <fmt:message key="tariff.speedIn" bundle="${loc}"/></label>
                    <div class="col-md-2 field">
                        <p id="speedIn" class="info-field">${tariff.speedIn}</p>
                        <input type="text" name="speedIn" class="edit-field"
                               value="${tariff.speedIn}" style="display: none"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="control-label label-default col-md-3" for="speedOut" style="display: inline-block">
                        <fmt:message key="tariff.speedOut" bundle="${loc}"/>
                    </label>
                    <div class="col-md-2 field">
                        <p id="speedOut" class="info-field">
                            <c:out value="${tariff.speedOut}"/></p>
                        <input type="text" name="speedOut" class="edit-field"
                               value="${tariff.speedOut}" style="display: none"/>
                    </div>
                </div>
                <c:if test="${tariff.trafficLimit != 0}">
                <div class="form-group row">
                    <label class="control-label label-default col-md-3" style="display: inline-block">
                        <fmt:message key="tariff.trafficLimit" bundle="${loc}"/></label>
                    <div class="col-md-2 field">
                        <p id="trafficLimit" class="info-field">
                            <c:out value="${tariff.trafficLimit}"/></p>
                        <input type="text" name="trafficLimit" class="edit-field"
                               value="${tariff.trafficLimit}" style="display: none"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="control-label label-default col-md-3" for="overrunFee">
                        <fmt:message key="tariff.overrunFee" bundle="${loc}"/></label>
                    <div class="col-md-3 field">
                        <p id="overrunFee" class="info-field">
                            <c:out value="${tariff.overrunFee}"/></p>
                        <input type="text" name="overrunFee" class="edit-field"
                               value="${tariff.overrunFee}" style="display: none"/>
                    </div>
                </div>
                </c:if>
                <button class="saveButton" class="form-control btn btn-default" type="submit" disabled>
                    <fmt:message key="form.button.save" bundle="${loc}"/>
                </button>
            </form>

            <c:if test="${sessionScope.usRole eq 'admin'}">
            <form action="/Controller" method="post">
                <button class="btn btn-primary" type="button" data-toggle="collapse"
                        data-target="#saleInfo${theCount.count}" aria-expanded="false" aria-controls="saleInfo">
                    <fmt:message key="tariff.button.add_sale" bundle="${loc}"/></button>
                <div class="collapse" id="saleInfo${theCount.count}">
                    <div class="card card-body">
                        <div class="form-group row">
                            <label class="col-sm-2" for="salePercent">
                                <fmt:message key="tariff.sale.percent" bundle="${loc}"/></label>
                            <div class="col-sm-3">
                                <input id="salePercent" type="text" name="salePercent"
                                       title='<fmt:message key="tariff.sale_percent.title" bundle="${loc}"/>'/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="saleExpirationDate" class="col-sm-2 col-form-label">
                                <fmt:message key="tariff.sale.expiration_date" bundle="${loc}"/></label>
                            <div class="col-sm-3">
                                <input class="form-control" type="date" name="saleExpirationDate"
                                       title='<fmt:message key="tariff.sale_expiration_date.title" bundle="${loc}"/>'
                                       id="saleExpirationDate">
                            </div>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="command" value="add_sale">
                <input type="hidden" name="tId" value="${tariff.TId}"/>
                <button type="submit" class="btn-primary">
                    <fmt:message key="form.button.save" bundle="${loc}"/></button>
            </form>
            </c:if>
        </div>
    </div>
</div>