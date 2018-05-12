/**
 * Created by Администратор on 10.05.2018.
 */
$('#register_button').click(function () {
    document.getElementById("sign_form").style.display = 'none';
    document.getElementById("registration_form").style.display = "block";
});

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
    document.getElementById("transfer_form").style.display = 'block';
});

function redact(f, s) {
    var email = document.getElementById("profile-" + s);
    email.removeAttribute("disabled");
    var form = document.getElementById("form-" + s);
    var btn = document.createElement('input');
    btn.type = "submit";
    form.appendChild(btn);
    f.style.display = "none";
}

function show(form1, form2) {
    document.getElementById(form1).style.display = 'block';
    document.getElementById(form2).style.display = 'none';
}
document.getElementById("uploadBtn").onchange = function () {
    document.getElementById("uploadFile").value = this.value;
};
