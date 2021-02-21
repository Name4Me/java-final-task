$(function() {
    const registerForm = $("#registerForm");
    const password = document.getElementById("password");
    const confirm_password = document.getElementById("confirm_password");
    const email = document.getElementById("inputEmail");
    const msgPass = document.getElementById("msgPass");
    const msgEmail = document.getElementById("msgEmail");
    const redirect = () =>  window.location.href = "${pageContext.request.contextPath}";

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
    email.onchange = validateEmail;

    function validatePassword() {
        confirm_password.setCustomValidity(password.value !== confirm_password.value ? msgPass.value : '');
    }

    function validateEmail() {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        email.setCustomValidity(re.test(String(email.value).toLowerCase()) ? '' : msgEmail.value);
    }

    $(".registerForm").on("submit", () => {
        $(".submit").prop('disabled', true);
        $.post(registerForm.attr("action"), registerForm.serialize(),
            data => { data.result ? redirect() : $(".submit").prop('disabled', false) && $(".alert").show(); }, "json");
        return false;
    });
});
