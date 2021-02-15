<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="header"%>
<jsp:useBean id="date" class="java.util.Date"/>
<%@ page import="com.example.app.model.userQuize.UserQuizStatus" %>

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
        .questions>li {
            padding-left: 25px;
            text-align: center;
        }
        .questions>li::before {
            position: absolute;
            content: "";
            background-color: rgba(255,0,0,.5);
            display: block;
            border-radius: 50%;
            width: 5px;
            height: 5px;
            top: 22px;
            left: 15px;
        }
        .box {
            border: 1px solid #999;
            background: white;
            height: 60px;
            padding: 15px;
            border-radius: 5px;
        }
        .box-item {
            float: left;
            margin-right: 20px;
            text-align: left;
        }
        .start {
            float: right;
            margin-top: -5px;
        }
        .status {
            float: right;
        }
        .score {
            float: left;
        }
        .ScoreBar {
            min-width: 0px;
            flex-grow: 1;
            flex-basis: 0px;
            width: 200px;
            height: 15px;
            background: #eaedf5;
            border-radius: 7px;
            margin-top: 6px;
            float: left;
        }
        .ScoreBarPe {
            background: rgb(103, 163, 0);
            height: inherit;
            border-radius: 7px;
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
                    <td align="center">
                        <c:if test="${ quiz.score != 0 && quiz.status == UserQuizStatus.Completed}">
                            ${quiz.score}% <fmt:message key="correct" bundle="${bundle}"/>
                        </c:if>
                    </td>
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
    const assignments = document.getElementById('assignments');

    function get(element){
        $.post("${pageContext.request.contextPath}/user/assignments/assignment",
            {quizId : element.dataset.id}, response => {assignments.innerHTML = response; updateListener();});
    }
    function updateListener(){ $(".question_item").off('click').on('click', showQuestion); }
    function showQuestion(){ $(".question_content").html($(this).find('div').html()); }
</script>

</body>
</html>
