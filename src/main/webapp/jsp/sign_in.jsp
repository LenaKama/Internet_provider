<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div style="border: 3px solid grey; margin: 10px; padding: 10px">
    <form method="post" action="/Controller">
        <input type="hidden" name="command" value="authenticate"/>

        <h2><fmt:message key="word.login" bundle="${loc}"/></h2>
           <div class="form-group row col-md-4">
               <input id="login_login" type="text" name="usLogin" class="form-control"
               title='<fmt:message key="form.usLogin.title" bundle="${loc}"/>' placeholder='<fmt:message key="form.usLogin" bundle="${loc}"/>' required pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}" />
           </div>
         <div class="from-group row col-md-4">
        <input id="login_password" type="password" name="usPassword" class="form-control"
               title='<fmt:message key="form.usPassword.title" bundle="${loc}"/>' placeholder='<fmt:message key="form.usPassword" bundle="${loc}"/>' required/>
        </div>
        <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="checker">
            <label class="form-check-label" for="checker"><fmt:message key="form.reminder" bundle="${loc}"/></label>
        </div>
        <button type="submit" class="btn-primary"><fmt:message key="form.button.enter" bundle="${loc}"/></button>
    </form>
    <form method="post" action="/Controller">
        <input type="hidden" name="command" value="go_to_registration"/>
        <button type="submit" class="btn btn-primary"><fmt:message key="form.button.register" bundle="${loc}"/></button>
    </form>
</div>
</div>
</body>
</html>
