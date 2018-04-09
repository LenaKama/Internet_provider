<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="en_US"/>
    <fmt:setBundle basename="properties.text" var="lang"/>
    <fmt:message key="submit" bundle="${lang}" var="submit"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="../css/login.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-5">
            hop
        </div>
    </div>
    <form class="form-sign" method="post" action="/Controller">
        <input type="hidden" name="command" value="authentication"/>

        <h2 class="form-sign-heading"><fmt:message key="authorization" bundle="${lang}"/></h2>
        ${message}
           <div class="sign-in">
               <input id="login_login" type="text" name="login" class="form-control"
               title='<fmt:message key="form.login.title" bundle="${lang}"/>' placeholder='<fmt:message key="form.login" bundle="${lang}"/>' required pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}" autofocus/>
           </div>
        <div class="sign-in">
        <input id="login_password" type="password" name="password" class="form-control"
               title='<fmt:message key="form.password.title" bundle="${lang}"/>' placeholder='<fmt:message key="form.password" bundle="${lang}"/>' required/>
        </div>
        <div class="checkbox">
            <label>
            <input type="checkbox" value="reminder"><fmt:message key="form.reminder" bundle="${lang}"/>
        </label>
        </div>
        <input name="form_submit" class="form-control" class="sign-in" type="submit" value='<fmt:message key="form.button.enter" bundle="${lang}"/>'/>
    <form action="/Controller" method="post">
        <input type="hidden" name="command" value="go_to_registration"/>
        <fmt:message key="form.button.register" bundle="${lang}"/>
    </form>
    </form>
</div>
</body>
</html>
