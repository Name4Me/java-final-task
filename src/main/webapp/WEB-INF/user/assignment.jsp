<%--@elvariable id="quiz" type="com.example.app.model.assignment.Assignment"--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ page import="com.example.app.model.assignment.AssignmentStatus" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<a href="${pageContext.request.contextPath}/controller/user/assignments"><fmt:message key="back" bundle="${bundle}"/></a>

<div class="row">
    <div class="col-md-12">
        <h3>${quiz.quiz.name}</h3>
    </div>
</div>
<div class="box">
    <div class="box-item">
        ID: ${quiz.quizId}
    </div>
    <div class="box-item">
        <svg width="20" height="20" xmlns="http://www.w3.org/2000/svg"><path d="M9.99 0C4.47 0 0 4.48 0 10s4.47 10 9.99 10C15.52 20 20 15.52 20 10S15.52 0 9.99 0zM10 18c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8zm.5-13H9v6l5.25 3.15.75-1.23-4.5-2.67V5z" fill="#009ECC" fill-rule="evenodd"></path></svg>
        ${quiz.quiz.time} <fmt:message key="min" bundle="${bundle}"/>
    </div>
    <div class="box-item">
        <svg width="19" height="16" xmlns="http://www.w3.org/2000/svg"><path d="M19 15v-2H5v2h14zm0-6V7H5v2h14zM5 3h14V1H5v2zM1 1v2h2V1H1zM0 1a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 01-1 1H1a1 1 0 01-1-1V1zm1 6v2h2V7H1zM0 7a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 01-1 1H1a1 1 0 01-1-1V7zm1 6v2h2v-2H1zm-1 0a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 01-1 1H1a1 1 0 01-1-1v-2z" fill="#009ECC" fill-rule="evenodd"></path></svg>
        ${quiz.quiz.numberOfQuestion} <fmt:message key="questions_short" bundle="${bundle}"/>
    </div>
    <c:if test="${quiz.score != 0 && quiz.status == AssignmentStatus.Completed}">
    <div class="box-item">
        <div class="ScoreBar">
            <div class="ScoreBarPe" style="width: ${quiz.score}%;"></div>
        </div>
        <div class="score">&nbsp;${quiz.score}%</div>
    </div>
    </c:if>
    <c:if test="${quiz.status == AssignmentStatus.NotStarted && sessionScope.blocked == null}">
        <a class="btn btn-outline-success start" data-id="${quiz.quizId}" href="${pageContext.request.contextPath}/controller/user/assignments/assignment?quizId=${quiz.quizId}"> Start </a>
    </c:if>
    <c:if test="${quiz.status != AssignmentStatus.NotStarted}">
        <div class="box-item status"><fmt:message key="${quiz.status}" bundle="${bundle}"/></div>
    </c:if>
</div>



