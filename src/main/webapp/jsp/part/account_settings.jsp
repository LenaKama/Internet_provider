<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>

    <%@include file="bundle.jsp" %>

    <c:set var="imgAvatar" value="${pageContext.request.contextPath}/img/anonym.jpg"/>
    <fmt:message key="general.button.loadAvatar" bundle="${loc}" var="avatarButton"/>

    <fmt:message key="client_account.title" bundle="${loc}" var="title"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

    <title>${title}</title>

</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
<%@include file="client-menu.jsp"%>
<form class="form-horizontal">
    <div class="profile_photo">
        <img src="${imgAvatar}"/>
    </div>
    <%--<button class="btn btn-default" style="display:block;height:50px;width:200px" onclick="document.getElementById('getFile').click()">${avatarButton}</button>--%>
    <%--<input type='file' id="getFile" style="display:none">--%>

    <div class="form-group">
        <label class="control-label col-sm-1" for="login" style="display: inline-block">
            <fmt:message key="form.usLogin" bundle="${loc}"/></label>
        <div class="col-sm-10">
            <p id="login" class="form-control-static">${user.usLogin}</p>
        </div>
    </div>
    <%--<div class="form-group">--%>
        <%--<label class="control-label col-sm-1" for="password" style="display: inline-block">--%>
            <%--<fmt:message key="form.usPassword" bundle="${loc}"/></label>--%>
        <%--<div class="col-sm-10">--%>
            <%--<label id="password" class="form-control info-field">${user.usPassword}</label>--%>
        <%--</div>--%>
    <%--</div>--%>
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
            <p id="passport" class="form-control-static editText" style="display: none">${user.usPassport}</p>
        </div>
    </div>

    <button type="button" id="editButton" class="btn" onclick="edit1()">Edit</button>

    <button id="submitButton" class="btn btn-default" type="submit">
        <fmt:message key="form.button.save" bundle="${loc}"/>
    </button>

</form>

    <%@include file="../fragment/footer.jspf"%>
</div>

<script src="../../js/bootstrap/jquery.min.js"></script>
<script src="../../js/bootstrap/bootstrap.min.js"></script>
<script>
    function edit1() {
        document.getElementsByClassName('info-field').style.display='none';
        document.getElementsByClassName('edit-field').style.display='block';
    }
    function edit() {
        var text = $('.editText').text();
        var input = $('<input type="text" placeholder="' + text + '" />')
        $('.editText').text('').append(input);
    }
</script>
</body>
</html>

