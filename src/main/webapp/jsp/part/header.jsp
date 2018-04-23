<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<fmt:setLocale value="en_US"/>--%>
<%--<fmt:setBundle basename="prop.locale.text" var="loc"/>--%>

<%--<fmt:setLocale scope="session" value="${sessionScope.userLocale}"/>--%>
<%--<fmt:setBundle basename="prop.locale.text" scope="session" var="loc"/>--%>
<fmt:message bundle="${loc}" key="button.sign_in" var="sign_in"/>
<fmt:message bundle="${loc}" key="button.tariffs" var="tariffs"/>
<fmt:message bundle="${loc}" key="button.news" var="news"/>
<fmt:message bundle="${loc}" key="button.about_us" var="about_us"/>


<c:set var="imgLogo" scope="application" value="${pageContext.request.contextPath}/img/logo.png"/>
<c:set var="imgRU" scope="page" value="${pageContext.request.contextPath}/img/ru_icon.png"/>
<c:set var="imgEN" scope="page" value="${pageContext.request.contextPath}/img/en_icon.png"/>
<c:set var="url" scope="page" value="${pageContext.request.requestURL}"/>

<%--<c:choose>--%>
<%--<c:when test="${sessionScope.userLocale eq 'en'}">--%>
<%--<c:set var="curImg" value="${imgEN}"/>--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--<c:set var="curImg" value="${imgRU}"/>--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>

<nav class="navbar navbar-fixed-top">
    <div class="container">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="redirect"/>
            <input class="imgLogo" type="image" src="${imgLogo}" onclick="$(this).parent('form').submit()"/>
            <%--<input class="navbar-toggler" type="image" src="${imgLogo}" data-toggle="collapse"--%>
            <%--aria-label="Toggle navigation"/>--%>
        </form>
        <div class="collapse navbar-collapse navHeaderCollapse" id="myNavbar">
            <ul class="nav navbar-nav ml-auto mr-auto">
                <li class="nav-item">
                    <form action="/Controller" method="post">
                        <input type="hidden" name="command" value="show_sign_in"/>
                        <input class="nav-link active" type="submit" value="${sign_in}"/>
                    </form>
                </li>
                <li class="nav-item">
                    <form action="/Controller" method="post">
                        <input type="hidden" name="command" value="show_tariffs"/>
                        <input type="submit" value="${tariffs}"/>
                    </form>
                </li>
                <li class="nav-item">
                    <form action="/Controller" method="post">
                        <input type="hidden" name="command" value="show_news"/>
                        <input type="submit" value="${news}"/>
                    </form>
                </li>
                <li class="nav-item">
                    <form action="/Controller" method="post">
                        <input type="hidden" name="command" value="show_about_us"/>
                        <input type="submit" value="${about_us}"/>
                    </form>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="nav-item active">
                    <form class="locale" action="/Controller" method="post">
                        <input type="hidden" name="command" value="change_locale"/>
                        <input type="hidden" name="locale" value="ru_RU">
                        <input type="hidden" name="previousPage" value="${url}">
                        <input type="image" src="${imgRU}" onclick="$(this).parent('form').submit()"/>
                    </form>
                </li>
                <li class="nav-item">
                    <form class="locale" action="/Controller" method="post">
                        <input type="hidden" name="command" value="change_locale"/>
                        <input type="hidden" name="locale" value="en_US">
                        <input type="hidden" name="previousPage" value="${url}">
                        <input type="image" src="${imgEN}" onclick="$(this).parent('form').submit()"/>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>