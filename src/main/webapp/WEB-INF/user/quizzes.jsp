<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="header"%>
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
                <th data-name="name">
                    <fmt:message key="name" bundle="${bundle}"/>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-up" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M11.5 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L11 2.707V14.5a.5.5 0 0 0 .5.5zm-7-14a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V1.5a.5.5 0 0 1 .5-.5z"></path>
                    </svg>
                </th>
                <th><fmt:message key="description" bundle="${bundle}"/></th>
                <th data-name="difficulty">
                    <fmt:message key="difficulty" bundle="${bundle}"/>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-up" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M11.5 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L11 2.707V14.5a.5.5 0 0 0 .5.5zm-7-14a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V1.5a.5.5 0 0 1 .5-.5z"></path>
                    </svg>
                </th>
                <th><fmt:message key="time" bundle="${bundle}"/></th>
                <th data-name="numberOfQuestion">
                    <fmt:message key="numberOfQuestion" bundle="${bundle}"/>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-up" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M11.5 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L11 2.707V14.5a.5.5 0 0 0 .5.5zm-7-14a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V1.5a.5.5 0 0 1 .5-.5z"></path>
                    </svg>
                </th>
                <th><fmt:message key="actions" bundle="${bundle}"/></th>
            </tr>
            </thead>

            <tbody id="content">
            <c:forEach items="${page.items}" var="quiz">
                <tr id="quiz_${quiz.id}">
                    <td align="center">${quiz.name}</td>
                    <td align="center">${quiz.description}</td>
                    <td align="center">${quiz.difficulty}</td>
                    <td align="center">${quiz.time}</td>
                    <td align="center">${quiz.numberOfQuestion}</td>
                    <td align="center">
                        <button type="button" class="btn btn-outline-success add" data-id="${quiz.id}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
                                <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"></path>
                                <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
                            </svg>
                            Add
                        </button>
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
                    <a href="${pageContext.request.contextPath}/user/quizzes?page=${page.number-1}&s=${page.size}">
                        <span aria-hidden="true">&larr;</span>
                    </a>
                </li>
            </c:if>

            <c:if test="${!page.last}">
                <li>
                    <a href="${pageContext.request.contextPath}/user/quizzes?page=${page.number+1}&s=${page.size}">
                        <span aria-hidden="true">&rarr;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
    <div class="row arrows" style="display: none">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z"></path>
        </svg>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-up" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z"></path>
        </svg>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-up" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M11.5 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L11 2.707V14.5a.5.5 0 0 0 .5.5zm-7-14a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V1.5a.5.5 0 0 1 .5-.5z"></path>
        </svg>
    </div>

</div>
<!-- /.container -->


<script>
    const content = document.getElementById('content');
    const search = document.getElementById('search');
    $(function() {
        $(".bi-arrow-down-up, .bi-arrow-down, .bi-arrow-up").on( "click", filter);
        $(".add").on( "click", add);
    });
    function add(){
        const quizId = $(this).data("id");
        $.post( "${pageContext.request.contextPath}/user/quizzes/add", {quizId : quizId}, function(data) {
            console.log(data);
        }, "json");
    }

    function filter(){
        const svg = $(this);
        const th = svg.closest("th");
        const sortField = th.data("name");
        let sortOrder = 'desc';
        if (svg.hasClass("bi-arrow-down-up")){
            $("th:has(.bi-arrow-down, .bi-arrow-up)").append($(".arrows .bi-arrow-down-up").clone())
            $("th>.bi-arrow-down, th>.bi-arrow-up").remove();
        }
        if (svg.hasClass("bi-arrow-down-up")|| svg.hasClass("bi-arrow-up")) {
            th.append($(".arrows .bi-arrow-down").clone());
        }
        if (svg.hasClass("bi-arrow-down")) {
            th.append($(".arrows .bi-arrow-up").clone());
            sortOrder = 'asc';
        }
        svg.remove();
        $(".bi-arrow-down-up, .bi-arrow-down, .bi-arrow-up").off( "click").on( "click", filter);
        console.log("sortOrder: ", sortOrder, " sortField: ", sortField);
    }

    function sort(){
        $.post("${pageContext.request.contextPath}/user/quizzes/searchAjax", {search : search.value}, response => content.innerHTML = response);
    }
    //search.onkeyup = event => event.key === 'Enter' && getArticles();
</script>

</body>
</html>
