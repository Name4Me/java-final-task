<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.app.model.userQuize.UserQuizStatus" %>

<%--Localization--%>
<c:if test="${sessionScope.locale == null}">
    <fmt:setLocale value="ru"/>
</c:if>
<c:if test="${sessionScope.locale != null}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="localization" var="bundle"/>
<%----%>

<a href="${pageContext.request.contextPath}/assignments"><fmt:message key="back" bundle="${bundle}"/></a>


<div class="row">
    <div class="col-md-6">
        <h3>${quiz.quiz.name}</h3>
        <h3>${quiz.status}</h3>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <fmt:message key="id" bundle="${bundle}"/>: ${quiz.quizId}
    </div>
</div>
<div class="box">Thank you for passing the Assignment Container! We will contact you after checking the results.</div>

<c:if test="${quiz.status == UserQuizStatus.NotStarted}">

        <a class="btn btn-outline-success add" href="${pageContext.request.contextPath}/quizzes">
            Start
        </a>

</c:if>

