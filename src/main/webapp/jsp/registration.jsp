<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@include file="part/bundle.jsp" %>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">

    <fmt:message key="page.registration.title" bundle="${loc}" var="title"/>
    <title>${title}</title>

</head>
<body>
<form class="form-registration" action="/Controller" method="post">
    <input type="hidden" name="command" value="register"/>
    <h2 style="font-weight: bold"><fmt:message key="registration" bundle="${loc}"/></h2>
    <div class="form-group row">
        <label class="col-sm-4" for="usLogin"><fmt:message key="form.usLogin" bundle="${loc}"/></label>
        <div class="col-sm-7">
            <input id="usLogin" type="text" name="usLogin"
                   title='<fmt:message key="form.usLogin.title" bundle="${loc}"/>'
                   required pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}" onkeyup="trackChange(this.value)">
            <small class="text-muted" id="errorLogin" style="display: none; color:red;">
                <fmt:message key="form.errorLogin" bundle="${loc}"/>
            </small>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-4" for="usPassword"><fmt:message key="form.usPassword" bundle="${loc}"/></label>
        <div class="col-sm-7">
        <input id="usPassword" type="password" name="usPassword"
               title='<fmt:message key="form.usPassword.helpMessage" bundle="${loc}"/>' required
               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{4,}"/>
    </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-4" for="usPassword_repeat"><fmt:message key="form.usPassword.repeat"
                                                                     bundle="${loc}"/></label>
        <div class="col-sm-7">
        <input id='usPassword_repeat' type="password" required
               title='<fmt:message key="form.usPassword.repeat.title" bundle="${loc}"/>'
               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{6,}" onkeyup="checkPasswords()"/>
    </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-4" for="usName"><fmt:message key="form.usName" bundle="${loc}"/></label>
        <div class="col-sm-7">
            <input id="usName" type="text" name="usName" required
               title='<fmt:message key="form.usName.title" bundle="${loc}"/>'/>
    </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-4" for="usSurname"><fmt:message key="form.usSurname" bundle="${loc}"/></label>
        <div class="col-sm-7">
            <input id="usSurname" type="text" name="usSurname" required
               title='<fmt:message key="form.usSurname.title" bundle="${loc}"/>'/>
    </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-4" for="usEmail"><fmt:message key="form.usEmail" bundle="${loc}"/></label>
        <div class="col-sm-7">
            <input id="usEmail" type="text" name="usEmail"
               title='<fmt:message key="form.usEmail.title" bundle="${loc}"/>' required
               pattern="^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$"/>
    </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-4" for="usPassport"><fmt:message key="form.usPassport" bundle="${loc}"/></label>
        <div class="col-sm-7">
            <input id="usPassport" type="text" name="usPassport" required
               title='<fmt:message key="form.usPassport.title" bundle="${loc}"/>'/>
    </div>
    </div>
    <button id="submit_registration" class="btn btn-default" type="submit">
        <fmt:message key="form.button.enter" bundle="${loc}"/></button>
</form>

<script src="../js/bootstrap/jquery.min.js"></script>
<script src="../js/bootstrap/bootstrap.min.js"></script>
<script src="../js/ajax.js"></script>
<script src="../js/carousel.js"></script>
<script src="../js/script.js"></script>

</body>
</html>
