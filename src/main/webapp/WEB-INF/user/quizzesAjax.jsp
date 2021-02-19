<%--@elvariable id="page" type="com.example.app.util.Page"--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<c:forEach items="${page.items}" var="quiz">
    <tr id="quiz_${quiz.id}">
        <td>${quiz.name}</td>
        <td>${quiz.description}</td>
        <td>${quiz.difficulty}</td>
        <td>${quiz.time}</td>
        <td>${quiz.numberOfQuestion}</td>
        <td>
            <c:if test="${sessionScope.blocked == null}">
                <button type="button" class="btn btn-outline-success add">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
                        <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"></path>
                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
                    </svg>
                    Add
                </button>
            </c:if>
        </td>
    </tr>
</c:forEach>