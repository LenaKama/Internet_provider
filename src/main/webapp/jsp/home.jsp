<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
   <%@include file="part/bundle.jsp"%>
    <fmt:message key="submit" bundle="${loc}" var="submit"/>
    
    <fmt:message key="page.home.title" bundle="${loc}" var="title"/>

    <meta usName="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">
    
    <title>${title}</title>
</head>
<body>

<%@include file="part/header.jsp" %>
<div class="container">

</div>


<%@include file="fragment/footer.jspf" %>
</body>
</html>
