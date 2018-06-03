/**
 * Created by Администратор on 10.05.2018.
 */
$("#feedback_button").click(function () {
    $("#feedback").slideToggle();
});
$('#register_button').click(function () {
    document.getElementById("sign_form").style.display = 'none';
    document.getElementById("registration_form").style.display = "block";
});
function checkUsLogin(usLogin){
    $.get("AjaxHandler", {"checkLogin":usLogin, "command":"checkLogin"}, function (responseText) {
        if (responseText === "false") {
            document.getElementById("errorLogin").style.display = 'block';
            document.getElementById("submit_registration").setAttribute("disabled", "")
        } else {
            document.getElementById("errorLogin").style.display = 'none';
            document.getElementById("submit_registration").removeAttribute("disabled");
        }
    });
}
function checkPasswords() {
    var pass1 = document.getElementById('usPassword');
    var pass2 = document.getElementById('usPassword_repeat');
    if (pass1.value != pass2.value) {
        document.getElementById("submit_registration").setAttribute("disabled", "");
    } else {
        document.getElementById("submit_registration").removeAttribute("disabled");
    }

}
$('#charge_button').click(function () {
    document.getElementById("payment_form").style.display = 'block';
});
//
// $("#feedback_button").click(function() {
//     $("#feedback_form").slideToggle();
//     $("#feedback_form").css.display;
// });

document.onkeydown = function(){
    switch (event.keyCode){
        case 116 : //F5 button
            event.returnValue = false;
            event.keyCode = 0;
            return false;
        case 82 : //R button
            if (event.ctrlKey){
                event.returnValue = false;
                event.keyCode = 0;
                return false;
            }
    }
};

function show(form1, form2) {
    //document.getElementById("transfer_form").style.display = 'none';
    document.getElementById("transfer_form").style.display = 'block';
    document.getElementById(form1).style.display = 'block';
    document.getElementById(form2).style.display = 'none';
}
document.getElementById("uploadBtn").onchange = function () {
    document.getElementById("uploadFile").value = this.value;
};
function showMore(count) {
    var angleStatus = document.getElementById("angle" + count).className;
    if (angleStatus === "fa fa-angle-down") {
        document.getElementById("angle" + count).className = "fa fa-angle-up";
    } else {
        document.getElementById("angle" + count).className = "fa fa-angle-down";
    }
    var info = document.getElementById("tariffInfo" + count);
    if (info.style.display === "none") {
        document.getElementById("tariffInfo" + count).style.display = "inline";
    } else {
        document.getElementById("tariffInfo" + count).style.display = "none";
    }
}

function showReplyForm(val) {
    var form = document.getElementById("reply_form" + val);
    if (form.style.display === "none") {
        document.getElementById("reply_form" + val).style.display = "inline";
    } else {
        document.getElementById("reply_form" + val).style.display = "none";
    }
}

