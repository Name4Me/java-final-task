<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<c:set var="title">:&nbsp;<fmt:message key="signIn" bundle="${bundle}"/></c:set>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/assets/css/login.css"/>
        <script src="${pageContext.request.contextPath}/assets/js/login.js"></script>
    </head>
    <body class="text-center">
        <navbar:navbar/>
        <div class="container">
            <div class="row alert alert-danger" role="alert" style="display: none">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <fmt:message key="loginFail" bundle="${bundle}"/>
            </div>
            <div class="row">
                <input type="hidden" id="msgEmail" value="<fmt:message key="emailWarning" bundle="${bundle}"/>">
                <form class="form-login" id="loginForm" accept-charset="UTF-8" role="form" method="post" action="${pageContext.request.contextPath}/controller/login">
                    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="signIn" bundle="${bundle}"/></h1>
                    <%@ include file="/WEB-INF/jspf/login.jspf" %>
                    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="signIn" bundle="${bundle}"/></button>
                </form>
            </div>
        </div>
    </body>
</html>
