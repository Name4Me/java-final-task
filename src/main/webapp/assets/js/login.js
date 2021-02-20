const loginForm = $("#loginForm");
const email = document.getElementById("inputEmail");
email.onchange = validateEmail;

loginForm.on('submit', login);

function validateEmail() {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email.value).toLowerCase());
}

function login(){
    $.post( loginForm.attr("action"), loginForm.serialize(), processResponse, "json");
    return false;
}

function processResponse(data){
    if (data.result) window.location.href="${pageContext.request.contextPath}"; else $(".alert").show();
}