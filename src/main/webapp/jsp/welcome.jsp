<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<html>
<head>
    <%@include file="part/bundle.jsp" %>

    <fmt:message key="sign_in.authentication" bundle="${loc}" var="sign_in"/>
    <fmt:message key="client_account.title" bundle="${loc}" var="account"/>
    <c:choose>
        <c:when test="${sessionScope.usRole eq 'quest'}">
            <c:set var="title" value="${sign_in}"/>
        </c:when>
        <c:otherwise>
            <c:set var="title" value="${account}"/>
        </c:otherwise>
    </c:choose>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png" type="image/x-icon">

    <title>${title}</title>

    <script>
        //       $(document).ready(function() {
      /*  jQuery(document).ready(function ($) {

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
       */
    </script>

</head>
<body>
<%--<div id="myCarousel" class="carousel container slide">--%>
    <%--<div class="carousel-inner">--%>
        <%--<div class="active item one"></div>--%>
        <%--<div class="item two"></div>--%>
        <%--<div class="item three"></div>--%>
    <%--</div>--%>
<%--</div>--%>
<%@include file="part/header.jsp" %>
<div class="container">
    <c:choose>
        <c:when test="${sessionScope.usRole eq 'quest'}">
            <%@include file="sign_in.jsp" %>
        </c:when>
        <c:when test="${sessionScope.usRole eq 'admin'}">
            <%@include file="part/admin_menu.jsp" %>
            <%@include file="part/general.jsp"%>
        </c:when>
        <c:when test="${sessionScope.usRole eq 'client'}">
            <%@include file="part/client-menu.jsp" %>
            <%@include file="part/general.jsp"%>
        </c:when>
    </c:choose>
</div>

<script src="../js/bootstrap/bootstrap.min.js"></script>
<script src="../js/bootstrap/jquery.min.js"></script>
<script src="../js/carousel.js"></script>
<script src="../js/script.js"></script>
<script>
    function trackChange(value){
        $.get("AjaxHandler", {"checkLogin":value}, function (responseText) {
            if (responseText === "false") {
                document.getElementById("errorLogin").style.display = 'block';
                document.getElementById("submit_registration").setAttribute("disabled", "")
            } else {
                document.getElementById("errorLogin").style.display = 'none';
                document.getElementById("submit_registration").removeAttribute("disabled");
            }
        });
        }
        /*
    $(document).ready(function () {
        $('#somebutton').onmousedown(function () {
            alert("pressed");
            $.get("AjaxHandler", function (responseText) {
                if (responseText === "false") {
                    document.getElementById("errorLogin").style.display = 'block';
                    document.getElementById("submit_registration").setAttribute("disabled", "")
                } else {
                    alert("else");
                    document.getElementById("submit_registration").removeAttribute("disabled");
                }
            });
        });
    });*/

</script>
</body>
</html>