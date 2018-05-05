<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@include file="part/bundle.jsp" %>

    <fmt:message key="page.home.title" bundle="${loc}" var="title"/>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">

    <link href="../css/style.css" rel="stylesheet">

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png" type="image/x-icon">

    <title>${title}</title>

    <script>
        //       $(document).ready(function() {
        jQuery(document).ready(function ($) {

            alert('ready');
//           $("#editButton").click(edit);

//           $(document).on('click', '#somebutton', function () {
            $("#somebutton").click(function () {
                //        $(document).on('click','ul li', function(){
//               document.getElementById("somebutton").onclick = function(){ show();}
//            function show() {
//            $(document).on("click", "#somebutton", function () {
//                alert("hey");
                $.get("Controller?type=ajax&command=check_login", function (responseText) {
                    if (responseText == "true") {
                        $("#somediv").text(responseText);
                    } else {
                        $("#somediv").text("false");
                    }
                });
            });
        });
        function edit1() {
            var text = $('.textinfo').text();
            var input = $('<input type="text" placeholder="' + text + '" />')
            $('.textinfo').text('').append(input);
        }
        function edit() {
            var text = $('.editText').text();
            var input = $('<input type="text" placeholder="' + text + '" />')
            $('.editText').text('').append(input);
        }
    </script>

</head>
<body class="bg-faded">
<%@include file="part/header.jsp" %>
<div class="container">
${message}
    <label class="control-label" style="display: inline-block">
        <p class="textinfo" style="display: inline-block">Saghir</p>
        <i class="icon-star"></i></label>
    <div class="controls">
        <button id="edit" class="btn" onclick="edit1()">Edit</button>
    </div>

    <button id="somebutton">press here</button>
    <div id="somediv"></div>
    <c:choose>
        <c:when test="${sessionScope.usRole eq 'quest'}">
            <%@include file="sign_in.jsp" %>
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