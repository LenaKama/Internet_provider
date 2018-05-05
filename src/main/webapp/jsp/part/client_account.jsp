<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <%@include file="bundle.jsp" %>

    <fmt:message key="client_account.title" bundle="${loc}" var="title"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../../css/style.css" rel="stylesheet">

    <title>${title}</title>

</head>
<body>
<form class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-1" for="login" style="display: inline-block">
            <fmt:message key="form.usLogin" bundle="${loc}"/></label>
        <div class="col-sm-10">
            <p id="login" class="form-control-static">${user.usLogin}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="password" style="display: inline-block">
            <fmt:message key="form.usPassword" bundle="${loc}"/></label>
        <div class="col-sm-10">
            <p id="password" class="form-control-static editText">${user.usPassword}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="email" style="display: inline-block">
            <fmt:message key="form.usEmail" bundle="${loc}"/></label>
        <div class="col-sm-10">
            <p id="email" class="form-control-static editText">${user.usEmail}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="name" style="display: inline-block">
            <fmt:message key="form.usName" bundle="${loc}"/></label>
        <div class="col-sm-10">
            <p id="name" class="form-control-static editText">${user.usName}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="surname" style="display: inline-block">
            <fmt:message key="form.usSurname" bundle="${loc}"/></label>
        <div class="col-sm-10">
            <p id="surname" class="form-control-static editText">${user.usSurname}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="passport" style="display: inline-block">
            <fmt:message key="form.usPassport" bundle="${loc}"/></label>
        <div class="col-sm-10">
            <p id="passport" class="form-control-static editText">${user.usPassport}</p>
        </div>
    </div>

    <button type="button" id="editButton" class="btn" onclick="edit()">Edit</button>

    <button id="submitButton" class="btn btn-default" type="submit">
        <fmt:message key="form.button.save" bundle="${loc}"/>
    </button>

</form>

<br/>
<fmt:message key="form.usSurname" bundle="${loc}"/>${user.usSurname}
<fmt:message key="form.usEmail" bundle="${loc}"/>${user.usEmail}
<fmt:message key="form.usPassport" bundle="${loc}"/>${user.usPassport}

<script src="../../js/bootstrap/jquery.min.js"></script>
<script src="../../js/bootstrap/bootstrap.min.js"></script>

</body>
</html>

