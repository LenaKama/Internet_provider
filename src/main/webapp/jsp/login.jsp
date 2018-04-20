<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setLocale value="en_US"/>
    <fmt:setBundle basename="prop.locale.text" var="lang"/>
    <fmt:message key="submit" bundle="${lang}" var="submit"/>
    <meta usName="viewport" content="width=device-width, initial-scale=1">

    <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="css/usLogin.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-5">
            hop
        </div>
    </div>
    <form class="form-sign" method="post" action="/Controller">
        <input type="hidden" usName="command" value="authentication"/>

        <h2 class="form-sign-heading"><fmt:message key="authorization" bundle="${lang}"/></h2>
        ${message}
           <div class="sign-in">
               <input id="login_login" type="text" usName="usLogin" class="form-control"
               title='<fmt:message key="form.usLogin.title" bundle="${lang}"/>' placeholder='<fmt:message key="form.usLogin" bundle="${lang}"/>' required pattern="^[a-zA-Z]{1}[a-zA-Z0-9_]{3,}" />
           </div>
        <div class="sign-in">
        <input id="login_password" type="usPassword" usName="usPassword" class="form-control"
               title='<fmt:message key="form.usPassword.title" bundle="${lang}"/>' placeholder='<fmt:message key="form.usPassword" bundle="${lang}"/>' required/>
        </div>

        <input type="hidden" usName="command" value="delete_user"/>
<input type="text" usName="loginForDelete">
        <div class="checkbox">
            <label>
            <input type="checkbox" value="reminder"><fmt:message key="form.reminder" bundle="${lang}"/>
        </label>
        </div>
        <input usName="form_submit" class="form-control" class="sign-in" type="submit" value='<fmt:message key="form.button.enter" bundle="${lang}"/>'/>
    <a href="jsp/registration.jsp"><fmt:message key="form.button.register" bundle="${lang}"/>
    </a>

        <%--<input type="hidden" usName="command" value="go_to_registration"/>--%>
        <%--<input type="submit" value=--%>
    </form>
</div>
</body>
</html>
