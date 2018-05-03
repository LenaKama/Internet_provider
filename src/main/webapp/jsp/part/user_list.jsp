<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Users</title>
    <script>
        function showInfo(count) {
            var x = document.getElementById("clientInfo" + count);
            if (x.style.display === "none") {
                x.style.display = "block";
            } else {
                x.style.display = "none";
            }
        }
    </script>
</head>
<body>
ewrtfhg
<div class="panel-group">
    <c:forEach items="${requestScope.userList}" var="client" varStatus="theCount">
    <div class="panel panel-default">
        <div class="panel-heading">
                ${client.usName}
            <button class="btn-primary" onclick="showInfo(${theCount.count})">Show more</button>
        </div>
        <div id="clientInfo${theCount.count}" class="panel-body" style="display: none">
            Client info:
                ${client.usEmail}
        </div>
    </div>
    </c:forEach>
</body>
</html>
