<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<c:set var="title">:&nbsp;<fmt:message key="signIn" bundle="${bundle}"/></c:set>
<c:set var="isSignIn" value="true" />
<html>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
<body class="text-center">
<navbar:navbar/>

<div class="container">
    <div class="row alert alert-danger" role="alert" style="display: none">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <fmt:message key="loginFail" bundle="${bundle}"/>
    </div>
    <div class="row">
        <form class="form-login" accept-charset="UTF-8" role="form" method="post" action="${pageContext.request.contextPath}/controller/login">
            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="signIn" bundle="${bundle}"/></h1>
            <div class="form-group">
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="email" id="inputEmail" class="form-control"
                       placeholder="<fmt:message key="email" bundle="${bundle}"/>" required autofocus name="email" >
            </div>
            <div class="form-group">
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" required
                       placeholder="<fmt:message key="password" bundle="${bundle}"/>" name="password">
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="signIn" bundle="${bundle}"/></button>
        </form>
    </div>


</div>


<script>
    $(function() {
        $(".form-login").on('submit',
            function(){
                $.post( $(this).attr("action"),
                    $(this).serialize(),
                    function(data) {
                        if (data.result) {
                            window.location.href="${pageContext.request.contextPath}";
                        } else { $(".alert").show(); }
                    },
                    "json");
                return false;
            });
    });

</script>
</body>
</html>
