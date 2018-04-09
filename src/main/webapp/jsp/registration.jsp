<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="en_US"/>
    <fmt:setBundle basename="properties.text" var="lang"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/login.css" rel="stylesheet">
</head>
<body>
<form id="registration-form" class="form-sign" action="/Controller" method="post"/>
<input type="hidden" name="command" value="registration"/>

<h2 class="form-sign-heading"><fmt:message key="registration" bundle="${lang}"/></h2>
<div class="field">
<label for="login"><fmt:message key="form.login" bundle="${lang}"/></label>
<input id="login" type="text" name="login" class="form-control" title='<fmt:message key="form.login.title" bundle="${lang}"/>'
       required pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}">
</div>
<div class="field">
    <label for="password"><fmt:message key="form.password" bundle="${lang}"/></label>
<input id="password" type="password" name="password" class="form-control"
       title='<fmt:message key="form.password.title" bundle="${lang}"/>'
       required pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{4,}">
</div>
<div class="field">
<label for="password_repeat"><fmt:message key="form.password.repeat" bundle="${lang}"/></label>
<input id='password_repeat' type="password" class="form-control" name="password" required
       title='<fmt:message key="form.password.repeat.title" bundle="${lang}"/>'
       pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z*]{6,}"/>
</div>
<div class="field">
<label for="name"><fmt:message key="form.name" bundle="${lang}"/></label>
<input id="name" type="text" name="name" class="form-control"
       title='<fmt:message key="form.name.title" bundle="${lang}"/>'/>
</div>
<div class="field">
<label for="surname"><fmt:message key="form.surname" bundle="${lang}"/></label>
<input id="surname" type="text" name="surname" class="form-control"
       title='<fmt:message key="form.surname.title" bundle="${lang}"/>'/>
</div>
<div class="field">
<label for="email"><fmt:message key="form.email" bundle="${lang}"/></label>
<input id="email" type="text" name="email" class="form-control"
       title='<fmt:message key="form.email.title" bundle="${lang}"/>' required
       pattern="^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$"/>
</div>
<div class="field">
<label for="passport"><fmt:message key="form.passport" bundle="${lang}"/></label>
<input id="passport" type="text" class="form-control" name="passport" required
       title='<fmt:message key="form.passport.title" bundle="${lang}"/>'/>
</div>
<input id="submit_registration" name="form_submit" type="submit" class="form-control" value='<fmt:message key="form.button.enter" bundle="${lang}"/>'/>
</form>
</div>
</body>
</html>
