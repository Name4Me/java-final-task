<%--@elvariable id="page" type="com.example.app.util.Page"--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<jsp:useBean id="date" class="java.util.Date"/>
<%@ page import="com.example.app.model.assignment.AssignmentStatus" %>
<c:set var="title">:&nbsp;<fmt:message key="assignments" bundle="${bundle}"/></c:set>
<c:set var="isAsigments" value="true" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<navbar:navbar/>
<div class="container" id="assignments">
    <div class="row">
        <div class="col-md-6">
            <h1><fmt:message key="assignments" bundle="${bundle}"/></h1>
        </div>
    </div>

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
                <td>${quiz.quizId}</td>
                <td>${quiz.quiz.name}</td>
                <td><fmt:formatDate value="${quiz.createdAt}" pattern="dd MMM yyyy, hh:mm"/></td>
                <td><fmt:message key="${quiz.status}" bundle="${bundle}"/></td>
                <td>
                    <c:if test="${ quiz.score != 0 && quiz.status == AssignmentStatus.Completed}">
                        ${quiz.score}% <fmt:message key="correct" bundle="${bundle}"/>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="row">
        <ul class="pager">
            <c:if test="${!page.first}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller/user/quizzes?page=${page.number-1}&s=${page.size}">
                        <span aria-hidden="true">&larr;</span>
                    </a>
                </li>
            </c:if>

            <c:if test="${!page.last}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller/user/quizzes?page=${page.number+1}&s=${page.size}">
                        <span aria-hidden="true">&rarr;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<script>
    const assignments = document.getElementById('assignments');
    $("tr").on("click", get)
    function get(){
        const id = this.dataset.id;
        console.log(id);
        $.post("${pageContext.request.contextPath}/controller/user/assignments/assignment",
            {quizId : id}, response => assignments.innerHTML = response);
    }
</script>

</body>
</html>
