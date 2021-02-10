<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%--Localization--%>
<c:if test="${sessionScope.locale == null}">
    <fmt:setLocale value="ru"/>
</c:if>
<c:if test="${sessionScope.locale != null}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="localization" var="bundle"/>
<%----%>

<c:forEach items="${page.items}" var="quiz">
    <tr id="quiz_${quiz.id}">
        <td align="center">${quiz.name}</td>
        <td align="center">${quiz.description}</td>
        <td align="center">${quiz.difficulty}</td>
        <td align="center">${quiz.time}</td>
        <td align="center">${quiz.numberOfQuestion}</td>
        <td align="center">
            <button type="button" class="btn btn-outline-success add">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
                    <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"></path>
                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
                </svg>
                Add
            </button>
        </td>
    </tr>
</c:forEach>