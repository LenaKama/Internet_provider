<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

    <%@include file="bundle.jsp"%>

    <fmt:message key="client_account.title" bundle="${loc}" var="title"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../../css/style.css" rel="stylesheet">

    <title>${title}</title>
</head>
<body>
<div class="text-info">
    <label for="usLogin"><fmt:message key="form.usLogin" bundle="${loc}"/></label>
    <input id="usLogin" type="text" name ="usLogin" class="form-control"
    placeholder="${user.usLogin}"/>
    <fmt:message key="form.usName" bundle="${loc}"/>${user.usName}
    <fmt:message key="form.usSurname" bundle="${loc}"/>${user.usSurname}
    <fmt:message key="form.usEmail" bundle="${loc}"/>${user.usEmail}
    <fmt:message key="form.usPassport" bundle="${loc}"/>${user.usPassport}

    <label for="usSurname"><fmt:message key="form.usSurname" bundle="${loc}"/></label>
    <input id="usSurname" type="text" name="usSurname" class="form-control"
           title='<fmt:message key="form.usSurname.title" bundle="${loc}"/>'/>
</div>
</body>
</html>

