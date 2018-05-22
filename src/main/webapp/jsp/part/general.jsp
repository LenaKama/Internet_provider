<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<c:set var="credit_cards" scope="page" value="${pageContext.request.contextPath}/img/credit_cards.png"/>
<c:set var="m_banking" scope="page" value="${pageContext.request.contextPath}/img/m_banking.png"/>

<%--<c:choose>--%>
<%--<c:if test="${user.usAvatar == null}">--%>
<c:set var="imgAvatar" value="${pageContext.request.contextPath}/img/anonym.jpg"/>
<fmt:message key="general.button.loadAvatar" bundle="${loc}" var="avatarButton"/>
<%--</c:if>--%>
<%--<c:otherwise>--%>
<%--<c:set var="imgAvatar" value="${user.usAvatar}"/>--%>
<%--<c:set var="avatarButton" value='<fmt:message key="general.button.changeAvatar" bundle="${loc}"/>'/>--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<div class="row">
    <div class="col-sm-2">
        <div class="profile_photo">
            <img src="${imgAvatar}" style="border-radius: 50%;font-size: 36px;"/>
        </div>
    </div>
    <div class="col-sm-3">
        <c:choose>
            <c:when test="${user.usBan}">
                <i class="fa fa-circle" style="font-size:24px;color:red"></i>
                <fmt:message key="general.account.unactive" bundle="${loc}"/>
            </c:when>
            <c:otherwise>
                <i class="fa fa-circle" style="font-size:24px;color:green"></i>
                <fmt:message key="general.account.active" bundle="${loc}"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div class="row">
    <label class="col-sm-2" for="usLogin"><fmt:message key="form.usLogin" bundle="${loc}"/></label>
    <div id="usLogin" class="col-sm-3">${user.usLogin}</div>
</div>
<div class="row">
       <label class="col-sm-2" for="usTariff"><fmt:message key="general.current_tariff" bundle="${loc}"/></label>
    <div id="usTariff" class="col-sm-3">
        <c:choose>
            <c:when test="${empty currentTariff}">
                <fmt:message key="general.empty.tariff" bundle="${loc}"/>
                <form action="/Controller" method="post">
                    <input type="hidden" name="command" value="show_tariffs"/>
                <button class="btn" type="submit"><fmt:message key="general.button.choose.tariff" bundle="${loc}"/></button>
                </form>
            </c:when>
            <c:otherwise>
                ${currentTariff.tName}
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div class="row">
    <label class="col-sm-2" for="tariff_limit"><fmt:message key="general.tariff.limit" bundle="${loc}"/></label>
    <div id="tariff_limit" class="col-sm-3">${currentTariff.trafficLimit}</div>
</div>
<div class="row">
    <label class="col-sm-2" for="speed_in"><fmt:message key="general.tariff.speed_in" bundle="${loc}"/></label>
    <div id="speed_in" class="col-sm-3">${currentTariff.speedIn}</div>
</div>
<div class="row">
    <label class="col-sm-2" for="speed_out"><fmt:message key="general.tariff.speed_out" bundle="${loc}"/></label>
    <div id="speed_out" class="col-sm-3">${currentTariff.speedOut}</div>
</div>
<div class="row">
    <label class="col-sm-2" for="curBalance"><fmt:message key="general.current_balance" bundle="${loc}"/></label>
    <div id="curBalance" class="col-sm-3">${currentBalance}</div>
</div>
<div class="row">
    <div class="col-sm-2 col-sm-offset-2">
        <button id="charge_button" class="btn btn-default"><fmt:message key="general.button.recharge" bundle="${loc}"/></button>
    </div>
    <div class="col-sm-4">
        <form id="transfer_form" action="/Controller" method="post" style="display: none">
            <input type="hidden" name="command" value="recharge_account">
            <label class="center-pill">
                <fmt:message key="general.button.paymentMethod" bundle="${loc}"/></label>
            <div class="row">
            <input type="image" class="img-thumbnail" src="${credit_cards}"
                   onclick="show('card_form', 'banking_form')"/>
            <input type="image" class="img-thumbnail" src="${m_banking}" onclick="show('banking_form', 'card_form')"/>
            </div>
            <div class="form-group" id="card_form" style="display: none">
                <input type="text" placeholder="Enter the card number" required/>
                <input type="text" name="amount" placeholder="Enter the amount" required
                       pattern="^[0-9]+(\\.[0-9]+)?$"/>
            </div>
            <div class="form-group" id="banking_form" style="display: none">
                <input type="text" placeholder="Enter the phone number" required/>
                <input type="text" name="amount" placeholder="Enter the amount" required
                       pattern="^[0-9]+(\\.[0-9]+)?$"/>
            </div>
            <button class="form-group btn btn-primary" type="submit">
                <fmt:message key="general.button.submit" bundle="${loc}"/></button>
        </form>
    </div>
</div>


