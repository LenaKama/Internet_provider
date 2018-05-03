<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
   <%@include file="part/bundle.jsp"%>

 <fmt:message key="page.home.title" bundle="${loc}" var="title"/>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">

   <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png" type="image/x-icon">

   <title>${title}</title>
</head>
<body class="bg-faded">
<%@include file="part/header.jsp" %>
<div class="container">
   <c:choose>
   <c:when test="${sessionScope.usRole eq 'quest'}">
<%@include file="sign_in.jsp"%>
   </c:when>
      <c:when test="${sessionScope.usRole eq 'admin'}">
         <%@include file="part/admin_menu.jsp" %>
      </c:when>
      <c:when test="${sessionScope.usRole eq 'client'}">
         <%@include file="part/client-menu.jsp" %>
      </c:when>
   </c:choose>
</div>
<div class="navbar navbar-fixed-bottom bg-primary">
<%@include file="fragment/footer.jspf" %>
</div>
<script src="../js/bootstrap/jquery.min.js"></script>
<script src="../js/bootstrap/bootstrap.min.js"></script>

</body>
</html>