<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <%--Localization--%>
    <c:if test="${sessionScope.locale == null}">
        <fmt:setLocale value="ru"/>
    </c:if>
    <c:if test="${sessionScope.locale != null}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>

    <fmt:setBundle basename="localization" var="bundle"/>
    <%----%>

    <title>Newsstand - <fmt:message key="signup" bundle="${bundle}"/></title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
                                <input class="form-control" placeholder="<fmt:message key="firstName" bundle="${bundle}"/>" name="fname" type="text" required>
                            </div>

                            <div class="form-group">
                                <input class="form-control" placeholder="<fmt:message key="lastName" bundle="${bundle}"/>" name="lname" type="text" required>
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>
