<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<c:set var="title">:&nbsp;<fmt:message key="signup" bundle="${bundle}"/></c:set>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/assets/css/register.css"/>
    </head>
    <body class="text-center">
        <navbar:navbar/>
        <div class="container">
            <div class="row alert alert-danger" role="alert" style="display: none">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <fmt:message key="emailExists" bundle="${bundle}"/>
            </div>
            <div class="row">
                <input type="hidden" id="msgEmail" value="<fmt:message key="emailWarning" bundle="${bundle}"/>">
                <input type="hidden" id="msgPass" value="<fmt:message key="passwordMatchFail" bundle="${bundle}"/>">
                <form class="registerForm" id="registerForm" action="${pageContext.request.contextPath}/controller/register">
                    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="signup" bundle="${bundle}"/></h1>
                    <%@ include file="/WEB-INF/jspf/login.jspf" %>
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
            <script src="${pageContext.request.contextPath}/assets/js/register.js"></script>
        </div>
    </body>
</html>