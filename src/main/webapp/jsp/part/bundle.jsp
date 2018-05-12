<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<c:choose>--%>
    <%--<c:when test="${empty sessionScope.usLocale}">--%>
        <%--<fmt:setLocale scope="session" value="${sessionScope.welcomeLocale}"/>--%>
    <%--</c:when>--%>
<%--<c:otherwise>--%>
    <%--<fmt:setLocale value="${sessionScope.usLocale}"/>--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<fmt:setLocale scope="session" value="en_US"/>
<fmt:setBundle basename="prop.locale.text" var="loc"/>