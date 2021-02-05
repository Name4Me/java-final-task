<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="header"%>
<html>
    <head>
        <header:header title="${msg.signup}"/>
        <style>
            html,
            body {
                height: 100%;
            }

            body {
                display: -ms-flexbox;
                display: -webkit-box;
                display: flex;
                -ms-flex-align: center;
                -ms-flex-pack: center;
                -webkit-box-align: center;
                align-items: center;
                -webkit-box-pack: center;
                justify-content: center;
                padding-top: 40px;
                padding-bottom: 40px;
                background-color: #f5f5f5;
            }

            .form-signin {
                width: 100%;
                max-width: 330px;
                padding: 15px;
                margin: 0 auto;
            }
            .pure-form .checkbox {
                font-weight: 400;
            }
            .pure-form .form-control {
                position: relative;
                box-sizing: border-box;
                height: auto;
                padding: 10px;
                font-size: 16px;
            }
            .pure-form .form-control:focus {
                z-index: 2;
            }
            .pure-form input[type="email"] {
                margin-bottom: -1px;
                border-bottom-right-radius: 0;
                border-bottom-left-radius: 0;
            }
            .pure-form input[type="password"] {
                margin-bottom: 10px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
            }
        </style>
        <%--Localization--%>
        <c:if test="${sessionScope.locale == null}">
            <fmt:setLocale value="ru"/>
        </c:if>
        <c:if test="${sessionScope.locale != null}">
            <fmt:setLocale value="${sessionScope.locale}"/>
        </c:if>

        <fmt:setBundle basename="localization" var="bundle"/>
        <%----%>
    </head>
    <body  class="text-center">
        <navbar:navbar/>

        <form class="pure-form" accept-charset="UTF-8" role="form" method="post" action="${pageContext.request.contextPath}/register">
            <fieldset>
                <div class="form-group">
                    <input class="form-control" placeholder="<fmt:message key="name" bundle="${bundle}"/>" name="firstName" type="text" required value="Roman">
                </div>

                <div class="form-group">
                    <input class="form-control" placeholder="<fmt:message key="email" bundle="${bundle}"/>" name="email" type="email" required value="7380003@gmail.com">
                </div>

                <div class="form-group">
                    <input class="form-control" placeholder="<fmt:message key="password" bundle="${bundle}"/>" name="password"
                           id="password" type="password" required value="1">
                </div>

                <div class="form-group">
                    <input type="password" class="form-control" id="confirm_password"
                           name="confirm_password" value="1"
                           placeholder="<fmt:message key="confirmPassword" bundle="${bundle}"/>" required>
                </div>

                <input class="btn btn-lg btn-success btn-block" type="submit" value="<fmt:message key="signup" bundle="${bundle}"/>">
            </fieldset>
        </form>
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
            $(function() {
                $(".pure-form").submit(
                    function(){
                        $.post( $(this).attr("action"),
                            $(this).serialize(),
                            function(data) { if (data.result) window.location.href="${pageContext.request.contextPath}"; },
                            "json");
                        return false;
                    });
            });

        </script>
    </body>
</html>
