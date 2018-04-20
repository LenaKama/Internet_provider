<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="en_US"/>
    <fmt:setBundle basename="prop.text" var="lang"/>
    <meta usName="viewport" content="width=device-width, initial-scale=1">

    <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/usLogin.css" rel="stylesheet">
</head>
<body>
<form id="registration-form" class="form-sign" action="/Controller" method="post"/>
<input type="hidden" usName="command" value="registration"/>

<h2 class="form-sign-heading"><fmt:message key="registration" bundle="${lang}"/></h2>
<div class="field">
<label for="usLogin"><fmt:message key="form.usLogin" bundle="${lang}"/></label>
<input id="usLogin" type="text" usName="usLogin" class="form-control" title='<fmt:message key="form.usLogin.title" bundle="${lang}"/>'
       required pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}">
</div>
<div class="field">
    <label for="usPassword"><fmt:message key="form.usPassword" bundle="${lang}"/></label>
<input id="usPassword" type="usPassword" usName="usPassword" class="form-control"
       title='<fmt:message key="form.usPassword.title" bundle="${lang}"/>'
       required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{4,}">
</div>
<div class="field">
<label for="password_repeat"><fmt:message key="form.usPassword.repeat" bundle="${lang}"/></label>
<input id='password_repeat' type="usPassword" class="form-control" usName="usPassword" required
       title='<fmt:message key="form.usPassword.repeat.title" bundle="${lang}"/>'
       pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{6,}"/>
</div>
<div class="field">
<label for="usName"><fmt:message key="form.usName" bundle="${lang}"/></label>
<input id="usName" type="text" usName="usName" class="form-control"
       title='<fmt:message key="form.usName.title" bundle="${lang}"/>'/>
</div>
<div class="field">
<label for="usSurname"><fmt:message key="form.usSurname" bundle="${lang}"/></label>
<input id="usSurname" type="text" usName="usSurname" class="form-control"
       title='<fmt:message key="form.usSurname.title" bundle="${lang}"/>'/>
</div>
<div class="field">
<label for="usEmail"><fmt:message key="form.usEmail" bundle="${lang}"/></label>
<input id="usEmail" type="text" usName="usEmail" class="form-control"
       title='<fmt:message key="form.usEmail.title" bundle="${lang}"/>' required
       pattern="^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$"/>
</div>
<div class="field">
<label for="usPassport"><fmt:message key="form.usPassport" bundle="${lang}"/></label>
<input id="usPassport" type="text" class="form-control" usName="usPassport" required
       title='<fmt:message key="form.usPassport.title" bundle="${lang}"/>'/>
</div>
<div class="field">
    <label for="tariff"><fmt:message key="form.tariff" bundle="${lang}"/></label>
    <input id='tariff' type="text" class="form-control" usName="tariff" required
           title='<fmt:message key="form.tariff.title" bundle="${lang}"/>'/>
</div>
<input id="submit_registration" usName="form_submit" type="submit" class="form-control" value='<fmt:message key="form.button.enter" bundle="${lang}"/>'/>
</form>
</div>
</body>
</html>
