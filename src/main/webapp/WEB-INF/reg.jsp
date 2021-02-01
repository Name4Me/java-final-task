<%--
  Created by IntelliJ IDEA.
  User: Roman
  Date: 01.02.2021
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<html>
<head>


    <%--Localization--%>
    <c:if test="${sessionScope.locale == null}">
        <fmt:setLocale value="ru"/>
    </c:if>
    <c:if test="${sessionScope.locale != null}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>

    <fmt:setBundle basename="localization" var="bundle"/>
    <%----%>
    <title><fmt:message key="signup" bundle="${bundle}"/></title>
</head>
<body>
<navbar:navbar/>
<div class="container">
    <div class="row vertical-offset-100">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><fmt:message key="signup" bundle="${bundle}"/></h3>
                </div>
                <div class="panel-body">
                    <form class="pure-form" accept-charset="UTF-8" role="form" method="post" action="${pageContext.request.contextPath}/register">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="firstName" bundle="${bundle}"/>" name="firstName" type="text" required>
                            </div>

                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="lastName" bundle="${bundle}"/>" name="lastName" type="text" required>
                            </div>

                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="email" bundle="${bundle}"/>" name="email" type="email" required>
                            </div>

                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="address" bundle="${bundle}"/>" name="address" type="address" required>
                            </div>

                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="password" bundle="${bundle}"/>" name="password"
                                       id="password" type="password" required>
                            </div>

                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="confirmPassword" bundle="${bundle}"/>" name="confirm_password"
                                       id="confirm_password" type="password" required>
                            </div>

                            <input class="btn btn-lg btn-success btn-block" type="submit" value="<fmt:message key="signup" bundle="${bundle}"/>">
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<script>

    <fmt:message key="passwordMatchFail" bundle="${bundle}" var="msg"/>
    var msg = "${msg}";

    var password = document.getElementById("password");
    var confirm_password = document.getElementById("confirm_password");

    function validatePassword(){
        if(password.value != confirm_password.value) {
            confirm_password.setCustomValidity(msg);
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;

</script>
</body>
</html>
