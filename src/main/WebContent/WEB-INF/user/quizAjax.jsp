<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--Localization--%>
<c:if test="${sessionScope.locale == null}">
    <fmt:setLocale value="ru"/>
</c:if>
<c:if test="${sessionScope.locale != null}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="localization" var="bundle"/>
<%----%>
<div class="row">
    <div class="col-md-2">
        <ul class="list-group questions" style="list-style-position: inside;">
            <c:forEach items="${quiz.quiz.questions}" var="question" varStatus="loopCounter">
                <li class="list-group-item question_item" data-id="${question.id}"> <c:out value="${loopCounter.count}"/> <fmt:message key="question" bundle="${bundle}"/>
                    <div style="display: none" id="question${question.id}"></div>
                    <div class="question" id="question${question.id}">
                        <h3>${question.text}</h3>
                        <div><pre><code></code></pre></div>
                        <div><input name="name_78582" id="id_78582" data-id="20774" type="radio"><label for="id_78582">The code prints Error</label></div>
                        <div><input name="name_78583" id="id_78583" data-id="20774" type="radio"><label for="id_78583">The code prints the employee ID</label></div>
                        <div><input name="name_78584" id="id_78584" data-id="20774" type="radio"><label for="id_78584">Compilation error at //Line1</label></div>
                        <div><input name="name_78585" id="id_78585" data-id="20774" type="radio"><label for="id_78585">Compilation error at //Line2</label></div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-md-8 question_content">

    </div>
</div>