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

    <title>Newsstand - <fmt:message key="admin" bundle="${bundle}"/> - <fmt:message key="addAdmin" bundle="${bundle}"/></title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<navbar:navbar/>

<!-- Page Content -->

<div class="container">

    <div class="col-md-2"></div>

    <div class="col-md-8">

        <c:if test="${success != null && success == true}">
            <div class="alert alert-success" role="alert"><fmt:message key="adminCreated" bundle="${bundle}"/></div>
        </c:if>
        <c:if test="${success != null && success == false}">
            <div class="alert alert-danger" role="alert"><fmt:message key="adminExists" bundle="${bundle}"/></div>
        </c:if>

        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/admins/add">
            <fieldset>

                <legend><fmt:message key="addAdmin" bundle="${bundle}"/></legend>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="fname"><fmt:message key="firstName" bundle="${bundle}"/></label>
                    <div class="col-md-4">
                        <input id="fname" name="fname" class="form-control input-md" type="text" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="lname"><fmt:message key="lastName" bundle="${bundle}"/></label>
                    <div class="col-md-4">
                        <input id="lname" name="lname" class="form-control input-md" type="text" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="email"><fmt:message key="email" bundle="${bundle}"/></label>
                    <div class="col-md-4">
                        <input id="email" name="email" class="form-control input-md" type="email" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-4 control-label" for="password"><fmt:message key="password" bundle="${bundle}"/></label>
                    <div class="col-md-4">
                        <input id="password" name="password" class="form-control input-md" type="password" required>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-4"></div>
                    <div class="col-md-4">
                        <button id="addbutton" name="addbutton" class="btn btn-primary"
                                type="submit"><fmt:message key="add" bundle="${bundle}"/></button>
                    </div>
                </div>

            </fieldset>
        </form>

        <a class='btn btn-default' href="${pageContext.request.contextPath}/admin/admins?p=1&s=10">
            <span class="glyphicon glyphicon-chevron-left"></span> <fmt:message key="back" bundle="${bundle}"/></a>

    </div>

    <div class="col-md-2"></div>

</div>
<!-- /.container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>
