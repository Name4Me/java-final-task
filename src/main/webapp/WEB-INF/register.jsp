<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<c:set var="title">:&nbsp;<fmt:message key="signup" bundle="${bundle}"/></c:set>
<c:set var="isSignUp" value="true" />
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body class="text-center">
<navbar:navbar/>
<div class="container">
    <div class="row alert alert-danger" role="alert" style="display: none">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <fmt:message key="emailExists" bundle="${bundle}"/>
    </div>
    <div class="row">
        <form class="pure-form" action="${pageContext.request.contextPath}/controller/register">
            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="signup" bundle="${bundle}"/></h1>
            <div class="form-group">
                <label for="inputEmail" class="sr-only">Email address</label>
                <input class="form-control" placeholder="<fmt:message key="email" bundle="${bundle}"/>" name="email"
                       id="inputEmail" type="email" required>
            </div>
            <div class="form-group">
                <label for="password" class="sr-only">password</label>
                <input class="form-control" placeholder="<fmt:message key="password" bundle="${bundle}"/>"
                       name="password"
                       id="password" type="password" required>
            </div>
            <div class="form-group">
                <label for="confirm_password" class="sr-only">confirm password</label>
                <input type="password" class="form-control" id="confirm_password"
                       name="confirm_password"
                       placeholder="<fmt:message key="confirmPassword" bundle="${bundle}"/>" required>
            </div>
            <input class="btn btn-lg btn-success submit" type="submit"
                   value="<fmt:message key="signup" bundle="${bundle}"/>">
        </form>
    </div>
</div>
<script>

    <fmt:message key="passwordMatchFail" bundle="${bundle}" var="msg"/>
    <fmt:message key="passwordEmailIncorect" bundle="${bundle}" var="msgEmail"/>
    const password = document.getElementById("password");
    const confirm_password = document.getElementById("confirm_password");
    const email = document.getElementById("inputEmail");
    const redirect = () =>  window.location.href = "${pageContext.request.contextPath}";

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
    email.onchange = validateEmail;

    function validatePassword() {
        confirm_password.setCustomValidity(password.value !== confirm_password.value ? '${msg}' : '');
    }

    function validateEmail() {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(email.value).toLowerCase());
    }

    $(".pure-form").on("submit", () => {
        $(".submit").prop('disabled', true);
        $.post($(this).attr("action"), $(this).serialize(),
            data => { data.result ? redirect() : $(".submit").prop('disabled', false) && $(".alert").show(); }, "json");
        return false;
    });

</script>
</body>
</html>