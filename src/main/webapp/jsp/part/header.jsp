<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:message bundle="${loc}" key="button.sign_in" var="sign_in"/>
<fmt:message bundle="${loc}" key="button.tariffs" var="tariffs"/>
<fmt:message bundle="${loc}" key="button.news" var="news"/>
<fmt:message bundle="${loc}" key="button.about_us" var="about_us"/>


<c:set var="imgLogo" scope="application" value="${pageContext.request.contextPath}/img/logo.png"/>
<c:set var="imgRU" scope="page" value="${pageContext.request.contextPath}/img/ru_icon.png"/>
<c:set var="imgEN" scope="page" value="${pageContext.request.contextPath}/img/en_icon.png"/>
<c:set var="url" scope="page" value="${pageContext.request.requestURL}"/>
<%--<c:set var="pagename" scope="page" value="${pageContext.request.servletContext.contextPath}"/>--%>
<%--<c:choose>--%>
<%--<c:when test="${sessionScope.userLocale eq 'en'}">--%>
<%--<c:set var="curImg" value="${imgEN}"/>--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--<c:set var="curImg" value="${imgRU}"/>--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<c:choose>
    <c:when test="${empty activeClass}">
        <c:set var="active_class" value="sign_in"/>
    </c:when>
    <c:otherwise>
        <c:set var="active_class" value="${activeClass}"/>
    </c:otherwise>
</c:choose>

<script>
    window.onload = function () {
        document.getElementById("${active_class}").classList.add('active');
        document.getElementById("${active_locale}").classList.add('active');
    };

</script>
<nav id="my_header" class="navbar navbar-static-top">
    <div class="container" style="background-color: transparent">
        <form action="/Controller" method="post">
            <input type="hidden" name="command" value="show_welcome_page"/>
            <input class="imgLogo" type="image" src="${imgLogo}" onclick="$(this).parent('form').submit()"/>
        </form>
        <div class="collapse navbar-collapse navHeaderCollapse" id="myNavbar">
            <ul class="nav navbar-nav ml-auto mr-auto">
                <li id="sign_in" class="nav-item">
                    <form action="/Controller" method="post">
                        <input type="hidden" name="command" value="show_welcome_page"/>
                        <input type="submit" value="${sign_in}"/>
                    </form>
                </li>
                <li id="tariffs" class="nav-item">
                    <form action="/Controller" method="post">
                        <input type="hidden" name="command" value="show_tariffs"/>
                        <input type="submit" value="${tariffs}"/>
                    </form>
                </li>
                <li id="news" class="nav-item">
                    <form action="/Controller" method="post">
                        <input type="hidden" name="command" value="show_news"/>
                        <input class="active" type="submit" value="${news}"/>
                    </form>
                </li>
                <li id="about_us" class="nav-item">
                    <form action="/Controller" method="post">
                        <input type="hidden" name="command" value="show_about_us"/>
                        <input type="submit" value="${about_us}"/>
                    </form>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form id="ru_RU" class="nav-item locale" action="/Controller" method="post">
                    <input type="hidden" name="command" value="change_locale"/>
                    <input type="hidden" name="locale" value="ru_RU">
                    <input type="hidden" name="previousPage" value="${url}">
                    <input type="image" src="${imgRU}" onclick="this.form.submit()"/>
                </form>
                <form id="en_US" class="nav-item locale" action="/Controller" method="post">
                    <input type="hidden" name="command" value="change_locale"/>
                    <input type="hidden" name="locale" value="en_US">
                    <input type="hidden" name="previousPage" value="${url}">
                    <input type="image" src="${imgEN}" onclick="$(this).parent('form').submit()"/>
                </form>
            </ul>
        </div>
    </div>
</nav>
<script>
</script>
<%--$(this).parent('form').submit()--%>