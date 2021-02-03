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

    <title>Newsstand - <fmt:message key="admin" bundle="${bundle}"/> - <fmt:message key="admins" bundle="${bundle}"/></title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>

<navbar:navbar/>

<!-- Page Content -->

<div class="container">

    <div class="col-md-1"></div>

    <div class="col-md-10">

        <ul class="nav nav-tabs nav-justified">
            <li role="presentation"><a href="${pageContext.request.contextPath}/admin/users?p=1&s=10"><fmt:message key="users" bundle="${bundle}"/></a></li>
            <li role="presentation" class="active"><a href="#"><fmt:message key="admins" bundle="${bundle}"/></a></li>
        </ul>

        <h1><fmt:message key="admins" bundle="${bundle}"/></h1>

        <div class="pull-right">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/admins/add" role="button">
                <fmt:message key="add" bundle="${bundle}"/>
            </a>
        </div>

        <c:if test="${currSize == 0}">
            <h1><fmt:message key="nothing" bundle="${bundle}"/></h1>
        </c:if>

        <c:if test="${currSize != 0}">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="firstName" bundle="${bundle}"/></th>
                    <th><fmt:message key="lastName" bundle="${bundle}"/></th>
                    <th><fmt:message key="email" bundle="${bundle}"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${page.items}" var="user">
                    <tr>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <div class="row">
            <ul class="pager">
                <c:if test="${!page.first}">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/admins?p=${page.number-1}&s=${page.size}">
                            <span aria-hidden="true">&larr;</span>
                        </a>
                    </li>
                </c:if>

                <c:if test="${!page.last}">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin/admins?p=${page.number+1}&s=${page.size}">
                            <span aria-hidden="true">&rarr;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>

    <div class="col-md-1"></div>

</div>
<!-- /.container -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</body>
</html>
