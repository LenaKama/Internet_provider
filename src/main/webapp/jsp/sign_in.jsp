<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<fmt:setLocale value="en_US"/>--%>
<%--<fmt:setBundle basename="prop.locale.text" var="loc"/>--%>
<%--<fmt:message key="submit" bundle="${loc}" var="submit"/>--%>
<html>
<head>

    <%@include file="part/bundle.jsp"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">

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

        <h2 class="form-sign-heading"><fmt:message key="word.login" bundle="${loc}"/></h2>
        ${message}
           <div class="sign-in">
               <input id="login_login" type="text" name="usLogin" class="form-control"
               title='<fmt:message key="form.usLogin.title" bundle="${loc}"/>' placeholder='<fmt:message key="form.usLogin" bundle="${loc}"/>' required pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}" />
           </div>
        <div class="sign-in">
        <input id="login_password" type="password" name="usPassword" class="form-control"
               title='<fmt:message key="form.usPassword.title" bundle="${loc}"/>' placeholder='<fmt:message key="form.usPassword" bundle="${loc}"/>' required/>
        </div>
        <div class="checkbox">
            <label>
            <input type="checkbox" value="reminder"><fmt:message key="form.reminder" bundle="${loc}"/>
        </label>
        </div>
        <input name="form_submit" class="form-control sign-in" type="submit" value='<fmt:message key="form.button.enter" bundle="${loc}"/>'/>
    <a href="../jsp/registration.jsp"><fmt:message key="form.button.register" bundle="${loc}"/>
    </a>

        <%--<input type="hidden" usName="command" value="go_to_registration"/>--%>
        <%--<input type="submit" value=--%>
    </form>
</div>
</body>
</html>
