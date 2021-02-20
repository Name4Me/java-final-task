<%--@elvariable id="page" type="com.example.app.util.Page"--%>
<%--@elvariable id="assignment" type="com.example.app.model.assignment.Assignment"--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page import="com.example.app.model.question.QuestionType" %>
<jsp:useBean id="date" class="java.util.Date"/>
<c:set var="title" value="true" />
<c:set var="isTest" value="true" />
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<navbar:navbar isTest="true"/>
<div class="container" id="assignments">
    <div class="row">
        <div class="col-md-2">
            <ul class="list-group questions" style="list-style-position: inside;">
                <c:forEach items="${assignment.quiz.questions}" var="question" varStatus="loopCounter">
                    <li class="list-group-item question_item" data-id="${question.id}">
                        <c:out value="${loopCounter.count}"/>
                        <fmt:message key="question" bundle="${bundle}"/>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-md-8 question_content">
        </div>
    </div>
    <div>
        <c:forEach items="${assignment.quiz.questions}" var="question" varStatus="loopCounter">
            <div style="display: none" class="question" id="question${question.id}">
                <h3><c:out value="${question.text}" escapeXml="true"/></h3>
                <hr>
                    <%--<div><pre><code></code></pre></div>--%>
                <c:forEach items="${question.choices}" var="choice">
                    <div>
                        <input class="answer" name="name_${question.id}" id="id_${choice.id}" data-id="${loopCounter.count}"
                               type="${question.type == QuestionType.Multiple ? "checkbox" : "radio"}">
                        <label for="id_${choice.id}"><c:out value="${choice.text}" escapeXml="true"/></label>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
    <button id="completeBtn" class="btn btn-outline-success complete" data-id="${assignment.quizId}">
        <fmt:message key="complete" bundle="${bundle}"/>
    </button>
</div>
</body>
</html>