<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="en_US"/>
<fmt:setBundle basename="prop.locale.text" var="loc"/>
<%--<fmt:setLocale scope="session" value="${sessionScope.userLocale}"/>--%>
<%--<fmt:setBundle basename="prop.locale.text" scope="session" var="loc"/>--%>

<fmt:message key="client_account.title" bundle="${loc}" var="title"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">

    <title>${title}</title>
</head>
<body>
<div class="navbar-header">
<%@include file="part/header.jsp" %>
</div>
<div class="container">
    oiguoh
    <%@include file="part/client-menu.jsp" %>
</div>
<div class="nav navbar-fixed-bottom">
<%@include file="fragment/footer.jspf" %>
</div>
</body>
</html>

