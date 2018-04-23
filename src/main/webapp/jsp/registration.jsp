<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="en_US"/>
    <fmt:setBundle basename="prop.locale.text" var="loc"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<form id="registration-form" class="form-sign" action="/Controller" method="post">
<input type="hidden" name="command" value="registration"/>

<h2 class="form-sign-heading"><fmt:message key="registration" bundle="${loc}"/></h2>
<div class="field">
<label for="usLogin"><fmt:message key="form.usLogin" bundle="${loc}"/></label>
<input id="usLogin" type="text" name="usLogin" class="form-control" title='<fmt:message key="form.usLogin.title" bundle="${loc}"/>'
       required pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}">
</div>
<div class="field">
    <label for="usPassword"><fmt:message key="form.usPassword" bundle="${loc}"/></label>
<input id="usPassword" type="password" name="usPassword" class="form-control"
       title='<fmt:message key="form.usPassword.title" bundle="${loc}"/>'
       required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{4,}">
</div>
<div class="field">
<label for="password_repeat"><fmt:message key="form.usPassword.repeat" bundle="${loc}"/></label>
<input id='password_repeat' type="password" class="form-control" name="upassword" required
       title='<fmt:message key="form.usPassword.repeat.title" bundle="${loc}"/>'
       pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{6,}"/>
</div>
<div class="field">
<label for="usName"><fmt:message key="form.usName" bundle="${loc}"/></label>
<input id="usName" type="text" name="usName" class="form-control"
       title='<fmt:message key="form.usName.title" bundle="${loc}"/>'/>
</div>
<div class="field">
<label for="usSurname"><fmt:message key="form.usSurname" bundle="${loc}"/></label>
<input id="usSurname" type="text" name="usSurname" class="form-control"
       title='<fmt:message key="form.usSurname.title" bundle="${loc}"/>'/>
</div>
<div class="field">
<label for="usEmail"><fmt:message key="form.usEmail" bundle="${loc}"/></label>
<input id="usEmail" type="text" name="usEmail" class="form-control"
       title='<fmt:message key="form.usEmail.title" bundle="${loc}"/>' required
       pattern="^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$"/>
</div>
<div class="field">
<label for="usPassport"><fmt:message key="form.usPassport" bundle="${loc}"/></label>
<input id="usPassport" type="text" class="form-control" name="usPassport" required
       title='<fmt:message key="form.usPassport.title" bundle="${loc}"/>'/>
</div>
<div class="field">
    <label for="tariff"><fmt:message key="form.tariff" bundle="${loc}"/></label>
    <input id='tariff' type="text" class="form-control" name="tariff" required
           title='<fmt:message key="form.tariff.title" bundle="${loc}"/>'/>
</div>
<input id="submit_registration" name="form_submit" type="submit" class="form-control" value='<fmt:message key="form.button.enter" bundle="${loc}"/>'/>
</form>
</body>
</html>
