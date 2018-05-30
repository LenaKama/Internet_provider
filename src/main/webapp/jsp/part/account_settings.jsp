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
    <%@include file="client_menu.jsp" %>
    <form class="form-horizontal" action="/Controller" method="post">
        <input type="hidden" name="command" value="update_user">
        <div class="profile_photo">
            <img src="${imgAvatar}"/>
        </div>
        <button class="btn btn-default" style="display:block;height:50px;width:200px"
                onclick="document.getElementById('getFile').click()">${avatarButton}</button>
        <input type='file' id="getFile" style="display:none">

        <div class="form-group">
            <label class="control-label col-sm-1" for="login" style="display: inline-block">
                <fmt:message key="form.usLogin" bundle="${loc}"/></label>
            <div class="col-sm-10">
                <p id="login" name="usLogin" class="form-control-static">${user.usLogin}</p>
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
            <div class="col-sm-10 field">
                <p id="email" class="form-control-static info-field">${user.usEmail}</p>
                <input type="text" name="usEmail" class="edit-field" value="${user.usEmail}" style="display: none"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-1" for="name" style="display: inline-block">
                <fmt:message key="form.usName" bundle="${loc}"/></label>
            <div class="col-sm-10 field">
                <p id="name" class="form-control-static info-field">${user.usName}</p>
                <input type="text" name="usName" class="edit-field" value="${user.usName}" style="display: none"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-1" for="surname" style="display: inline-block">
                <fmt:message key="form.usSurname" bundle="${loc}"/></label>
            <div class="col-sm-10 field">
                <p id="surname" class="form-control-static info-field">${user.usSurname}</p>
                <input type="text" name="usSurname" class="edit-field" value="${user.usSurname}" style="display: none"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-1" for="passport" style="display: inline-block">
                <fmt:message key="form.usPassport" bundle="${loc}"/></label>
            <div class="col-sm-10 field">
                <p id="passport" class="form-control-static info-field">${user.usPassport}</p>
                <input type="text" name="usPassport" class="edit-field" value="${user.usPassport}" style="display: none"/>
            </div>
        </div>

        <button id="saveButton" class="btn btn-default" type="submit" disabled>
            <fmt:message key="form.button.save" bundle="${loc}"/>
        </button>
    </form>
    <%--<%@include file="../fragment/footer.jspf"%>--%>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ajax.js"></script>
<script src="${pageContext.request.contextPath}/js/carousel.js"></script>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
<script>

    $('.info-field').click(function () {
        $('#saveButton').removeAttr("disabled");
        $(this).hide();
        $(this).closest('.field').children('.edit-field').show();
    });
</script>
</body>
</html>

