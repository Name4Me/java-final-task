<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="header"%>
<html>
<head>
    <header:header title="${msg.signIn}"/>
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
        .form-signin .checkbox {
            font-weight: 400;
        }
        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }
        .form-signin .form-control:focus {
            z-index: 2;
        }
        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
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
    <style>
        header{
            height: 90px;
        }
    </style>
</head>
<body class="text-center">
<navbar:navbar/>

<div class="container">
    <div class="row alert alert-danger" role="alert" style="display: none">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <fmt:message key="loginFail" bundle="${bundle}"/>
    </div>
    <div class="row">
        <form class="form-signin" accept-charset="UTF-8" role="form" method="post" action="${pageContext.request.contextPath}/login">
            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="signIn" bundle="${bundle}"/></h1>
            <div class="form-group">
                <label for="inputEmail" class="sr-only">Email address</label>
                <input type="email" id="inputEmail" class="form-control"
                       placeholder="<fmt:message key="email" bundle="${bundle}"/>" required autofocus name="email" >
            </div>
            <div class="form-group">
                <label for="inputPassword" class="sr-only">Password</label>
                <input type="password" id="inputPassword" class="form-control" placeholder="Password" required
                       placeholder="<fmt:message key="password" bundle="${bundle}"/>" name="password">
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="signIn" bundle="${bundle}"/></button>
        </form>
    </div>


</div>


<script>
    $(function() {
        $(".form-signin").on('submit',
            function(){
                $.post( $(this).attr("action"),
                    $(this).serialize(),
                    function(data) {
                        if (data.result) {
                            window.location.href="${pageContext.request.contextPath}";
                        } else {
                            $(".alert").show();
                        }
                    },
                    "json");
                return false;
            });
    });

</script>
</body>
</html>
