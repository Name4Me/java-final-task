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

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h1><fmt:message key="quizzes" bundle="${bundle}"/></h1>
        </div>
    </div>
    <form id="save-form">
        <div class="row new-quiz" style="display: none;">
            <div class="loader" style="display: none;" >
                <div class="spinner-border text-success"></div>
            </div>
            <div class="col-md-2">
                <input type="text" class="form-control" placeholder="<fmt:message key="name" bundle="${bundle}"/>"
                       name="name" id="name">
            </div>
            <div class="col-md-4">
                <input type="text" class="form-control" placeholder="<fmt:message key="description" bundle="${bundle}"/>"
                       name="description" id="description">
            </div>
            <div class="col-md-2">
                <select class="form-control" name="difficulty" id="difficulty">
                    <option value="0">Low</option>
                    <option value="1">Medium</option>
                    <option value="2">High</option>
                </select>
            </div>
            <div class="col-md-1">
                <input type="text" class="form-control" placeholder="<fmt:message key="time" bundle="${bundle}"/>"
                       name="time" id="time">
            </div>
            <div class="col-md-1">
                <input type="text" class="form-control"
                       placeholder="<fmt:message key="numberOfQuestion" bundle="${bundle}"/>" id="numberOfQuestion"
                       name="numberOfQuestion">
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-outline-success add-submit" data-action="save">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-save" viewBox="0 0 16 16">
                        <path d="M2 1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H9.5a1 1 0 0 0-1 1v7.293l2.646-2.647a.5.5 0 0 1 .708.708l-3.5 3.5a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L7.5 9.293V2a2 2 0 0 1 2-2H14a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h2.5a.5.5 0 0 1 0 1H2z"/>
                    </svg>
                    Save
                </button>
            </div>
        </div>
    </form>


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

            <tbody id="content">
            <c:forEach items="${page.items}" var="quiz">
                <tr id="quiz_${quiz.quizId}">
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

    function get(){
        $.post("${pageContext.request.contextPath}/assignments/assignment", {quizId : 9}, response => content.innerHTML = response);
    }
    //search.onkeyup = event => event.key === 'Enter' && getArticles();
</script>

</body>
</html>
