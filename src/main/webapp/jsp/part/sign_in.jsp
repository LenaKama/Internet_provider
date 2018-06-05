<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="sign_form">
    <form class="form-sign-in" action="/Controller" method="post">
        <input type="hidden" name="command" value="authenticate"/>
        <h2 style="font-weight: bold"><fmt:message key="sign_in.authentication" bundle="${loc}"/></h2>
        <c:if test="${wrongUserCredentials}">
            <div class="text-danger">
                <fmt:message key="sign_in.wrong.user.credentials" bundle="${loc}"/></div>
        </c:if>
        <div class="form-group row">
            <div class="col-md-8">
                <input id="login_login" type="text" name="usLogin" class="form-control"
                       title='<fmt:message key="form.usLogin.title" bundle="${loc}"/>'
                       placeholder='<fmt:message key="form.usLogin" bundle="${loc}"/>' required
                       pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}" autofocus/>
            </div>
        </div>
        <div class="from-group row">
            <div class="col-md-8">
                <input id="login_password" type="password" name="usPassword" class="form-control"
                       title='<fmt:message key="form.usPassword.title" bundle="${loc}"/>'
                       placeholder='<fmt:message key="form.usPassword" bundle="${loc}"/>' required/>
            </div>
        </div>
        <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="checker">
            <label class="form-check-label" for="checker">
                <fmt:message key="form.reminder" bundle="${loc}"/></label>
        </div>
        <div class="form-group row text-center">
            <button type="submit" class="btn btn-primary">
                <fmt:message key="sign_in.button.sign_in" bundle="${loc}"/></button>
        </div>
        <div class="form-group row text-center">
            <button id="register_button" class="nav-link">
                <fmt:message key="form.button.register" bundle="${loc}"/></button>
        </div>
    </form>
</div>
<div id="registration_form" style="display:none">
    <%@ include file="registration.jsp" %>
</div>

