<%@page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="nav nav-tabs" role="tablist">
    <li class="nav-item">
        <a class="nav-link active" href="#general" data-toggle="tab" role="tab">
            <fmt:message key="client_account.nav.general" bundle="${loc}"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#messages" data-toggle="tab" role="tab">
            <fmt:message key="client_account.nav.messages" bundle="${loc}"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#traffic" data-toggle="tab" role="tab">
            <fmt:message key="client_account.nav.trafficStatus" bundle="${loc}"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#transactions" data-toggle="tab" role="tab">
            <fmt:message key="client_account.nav.transactions" bundle="${loc}"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#sessions" data-toggle="tab" role="tab">
            <fmt:message key="client_account.nav.sessions" bundle="${loc}"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#settings" data-toggle="tab" role="tab">
            <fmt:message key="client_account.nav.accountSettings" bundle="${loc}"/></a>
    </li>
</ul>
<div class="tab-content">
    <div class="tab-pane fade in active" id="general" role="tabpanel">
        <%@include file="general.jsp"%>
    </div>
    <div class="tab-pane fade" id="messages" role="tabpanel">
        msg
    </div>
    <div class="tab-pane fade" id="traffic" role="tabpanel">
        <%@include file="trafficStatus.jsp"%>
    </div>
    <div class="tab-pane fade" id="transactions" role="tabpanel">
        <%@include file="transactions.jsp"%>
    </div>
    <div class="tab-pane fade" id="sessions" role="tabpanel">
        <%@include file="sessions.jsp"%>
    </div>
    <div class="tab-pane fade" id="settings" role="tabpanel">
        <%@include file="client_account.jsp" %>
    </div>
</div>
