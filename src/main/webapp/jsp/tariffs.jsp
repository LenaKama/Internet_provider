<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%@include file="part/bundle.jsp" %>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">

    <fmt:message key="page.tariffs.title" bundle="${loc}" var="title"/>
    <title>${title}</title>

    <script>
        function myFunction(count) {
            var x = document.getElementById("tariffInfo" + count);
            if (x.style.display === "none") {
                x.style.display = "inline";
            } else {
                x.style.display = "none";
            }
        }
    </script>
</head>
<body>
<%@include file="part/header.jsp" %>

<div class="container">
    <div class="panel-group">
        <c:forEach items="${requestScope.tariffList}" var="tariff" varStatus="theCount">
            <div class="myPanel panel panel-default">
                <div class="panel-heading">
                        ${tariff.tName}
                    <span class="label label-default">${tariff.connectionPayment}</span>
                <%--</div>--%>
                <%--<div class="panel-body">--%>
                    <%--<button onclick="myFunction(${theCount.count})">--%>
                        <i class="fa fa-angle-down" aria-hidden="true"
                           onClick="($(this)[0].className === 'fa fa-angle-down')?$(this)[0].className='fa fa-angle-up':$(this)[0].className='fa fa-angle-down'"></i>

                    <%--</button>--%>
                </div>
            <div class="panel-collapse collapse in show" aria-expanded="false" aria-hidden="true"
                 style="display: none; overflow: visible; height: auto;">
                <div class="panel-body card-block card-body">
                    <p class="ql-align-justify"> ${tariff.dailyFee}</p>
                    <p class="ql-align-justify"> ${tariff.trafficLimit}</p>
                </div>
            </div>
            <%--<div class="panel-collapse collapse in show" id="tariffInfo${theCount.count}">--%>
                    <%--${tariff.dailyFee}--%>
            <%--</div>--%>

            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
