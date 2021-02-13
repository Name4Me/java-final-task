<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="header"%>
<jsp:useBean id="date" class="java.util.Date"/>

<html>
<head>
    <header:header title="${msg.admin} - ${msg.quizzes}"/>

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
        .table thead th{
            text-align: center;
            vertical-align: middle;
        }
        button.add{
            float: right;
        }
        .new-quiz{
            margin: 10px 0;
            position: relative;
        }
        .loader{
            position: absolute;
            height: 100%;
            width: 100%;
            text-align: center;
            padding-top: 3px;
        }
        .disabled {
            pointer-events: none;
            opacity: 0.4;
        }
    </style>
</head>
<body>

<navbar:navbar/>

<!-- Page Content -->

<div class="container" id="assignments">
    <div class="row">
        <div class="col-md-6">
            <h1><fmt:message key="assignments" bundle="${bundle}"/></h1>
        </div>
    </div>

    <c:if test="${currSize == 0}">
        <h1><fmt:message key="nothing" bundle="${bundle}"/></h1>
    </c:if>

    <c:if test="${currSize != 0}">
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="id" bundle="${bundle}"/></th>
                <th><fmt:message key="name" bundle="${bundle}"/></th>
                <th><fmt:message key="assigned" bundle="${bundle}"/></th>
                <th><fmt:message key="status" bundle="${bundle}"/></th>
                <th><fmt:message key="result" bundle="${bundle}"/></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${page.items}" var="quiz">
                <tr id="quiz_${quiz.quizId}" onclick="get(this)" data-id="${quiz.quizId}">
                    <td align="center">${quiz.quizId}</td>
                    <td align="center">${quiz.quiz.name}</td>
                    <td align="center"><fmt:formatDate value="${quiz.createdAt}" pattern="dd MMM yyyy, hh:mm"/></td>
                    <td align="center"><fmt:message key="${quiz.status}" bundle="${bundle}"/></td>
                    <td align="center">${quiz.score !=0 ? quiz.score : ''}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <div class="row">
        <ul class="pager">
            <c:if test="${!page.first}">
                <li>
                    <a href="${pageContext.request.contextPath}/quizzes?page=${page.number-1}&s=${page.size}">
                        <span aria-hidden="true">&larr;</span>
                    </a>
                </li>
            </c:if>

            <c:if test="${!page.last}">
                <li>
                    <a href="${pageContext.request.contextPath}/quizzes?page=${page.number+1}&s=${page.size}">
                        <span aria-hidden="true">&rarr;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<!-- /.container -->


<script>
    const content = document.getElementById('content');
    const search = document.getElementById('search');

    function get(element){
        $.post("${pageContext.request.contextPath}/user/assignments/assignment", {quizId : element.dataset.id}, response => content.innerHTML = response);
    }
    //search.onkeyup = event => event.key === 'Enter' && getArticles();
</script>

</body>
</html>
