<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>

<c:set var="welcomeLocale" scope="session" value="en_US"/>
<html>
<body>
<jsp:forward page="jsp/welcome.jsp"/>
</body>
</html>
