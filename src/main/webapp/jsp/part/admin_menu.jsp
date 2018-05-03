<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <%@include file="bundle.jsp"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">

    <title>Admin account</title>

    <script>
    </script>
</head>
<ul class="nav nav-tabs justify-content-center">
    <li class="nav-item">
        <a class="active" href="#profile" data-toggle="tab">Profile</a>
    </li>
    <li class="nav-item">
        <a href="#users" data-toggle="tab">Users
            <%--<input type="hidden" name="command" value="load_user_list">--%>
            <%--<input type="submit" value="Users"/>--%>
        </a>
    </li>
</ul>
<div class="tab-content">
    <div class="tab-pane fade in active" id="profile">
        ...
    </div>
    <div class="tab-pane fade" id="users">
        <%@include file="user_list.jsp"%>
    </div>
</div>