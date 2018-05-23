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
    <%--<span id="edit" onclick="justEdit('edit')">Click to edit</span>--%>
    <%--<label class="control-label lbl" id="txt" onclick="edit2('txt')">Saghir</label>--%>
    <%--<input id="lll" type="text" placeholder="edit me" style="display: none;"/>--%>
    <%--<div class="controls">--%>
        <%--<span class="btn" onclick="edit2('txt')">Edit</span>--%>
    <%--</div>--%>
    <c:choose>
        <c:when test="${sessionScope.usRole eq 'quest'}">
            <%@include file="sign_in.jsp" %>
        </c:when>
        <c:when test="${sessionScope.usRole eq 'admin'}">
            <%@include file="part/admin_menu.jsp" %>
            <%@include file="part/general.jsp"%>
        </c:when>
        <c:when test="${sessionScope.usRole eq 'client'}">
            <%@include file="part/client_menu.jsp" %>
            <%@include file="part/general.jsp"%>
        </c:when>
    </c:choose>
    <%--<%@include file="fragment/footer.jspf"%>--%>
</div>

<span class="edit-on-click">Click to edit</span>


<script src="../js/bootstrap/bootstrap.min.js"></script>
<script src="../js/bootstrap/jquery.min.js"></script>
<script src="../js/ajax.js"></script>
<script src="../js/carousel.js"></script>
<script src="../js/script.js"></script>

<script>
    function edit2(val) {
        var text = $('#val').text();
        var t = document.getElementById("val").text();
        var input = $('<input type="text" placeholder="' + text + '" />');
        alert("text - "+text+"t - "+t);
        $('#val').text('').append(input);
    }
    $(document).ready(function () {
        $('#edit1').click(function () {
            var text = $('#txt').text();
            var input = $('<input type="text" placeholder="' + text + '" />')
            $('#txt').text('').append(input);
        });
        $('.lbl').click(function () {
            var text = $(this).text();
            alert("text - "+ text);
          //  document.getElementsByClassName("lbl").style.display= 'none';
            document.getElementById("lll").style.display= 'block';
            });
        $('.edit-on-click').click(function () {
            var $text = $(this).text(),
                $input = $('<input type="text" />');
            alert("pressed");
            $text.hide()
                .after($input);

            $input.val($text.html()).show().focus()
                .keypress(function (e) {
                    var key = e.which;
                    if (key == 13) // enter key
                    {
                        $input.hide();
                        $text.html($input.val())
                            .show();
                        return false;
                    }
                })
                .focusout(function () {
                    $input.hide();
                    $text.show();
                })
        });
    });
</script>
</body>
</html>