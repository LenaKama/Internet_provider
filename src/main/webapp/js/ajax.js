/**
 * @author Lena Kamotskaya
 */
$(document).on("change", "#usLogin", function () {
    $.get("AjaxHandler", function (responseText) {
        if (responseText == "true") {
            $("#somediv").text(responseText);
        } else {
            $("#somediv").text("false");
        }
    });
});
/*
 function checkLogin() {
 var login = $("#usLogin").val();
 var params = {
 command: "check_login",
 searchLogin: login
 };
 //        $.post("Controller", $.param(params), function(response) {
 //            // ...
 //        });
 $.get('${pageContext.request.contextPath}/Controller', {
 "command": "check_login",
 "searchLogin": login
 }, function () {
 if (${loginExists} == true
 )
 {
 document.getElementById('errorLogin').style.display = 'block';
 }
 else
 {
 document.getElementById('errorLogin').style.display = 'none';
 }
 });
 }
 */

/*
 $.ajax({
 url: "test.html",
 cache: false
 })
 .done(function( html ) {
 $( "#results" ).append( html );
 });
 */
