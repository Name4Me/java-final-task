<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<c:set var="title">:&nbsp;<fmt:message key="quizzes" bundle="${bundle}"/></c:set>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<navbar:navbar/>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <h1><fmt:message key="quizzes" bundle="${bundle}"/></h1>
        </div>
        <div class="col-md-9">
            <div class="alert alert-success" role="alert" style="display: none;">
            </div>
        </div>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th data-name="name">
                <fmt:message key="name" bundle="${bundle}"/>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-arrow-down-up" viewBox="0 0 16 16">
                    <path fill-rule="evenodd"
                          d="M11.5 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L11 2.707V14.5a.5.5 0 0 0 .5.5zm-7-14a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V1.5a.5.5 0 0 1 .5-.5z"></path>
                </svg>
            </th>
            <th><fmt:message key="description" bundle="${bundle}"/></th>
            <th data-name="difficulty">
                <fmt:message key="difficulty" bundle="${bundle}"/>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-arrow-down-up" viewBox="0 0 16 16">
                    <path fill-rule="evenodd"
                          d="M11.5 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L11 2.707V14.5a.5.5 0 0 0 .5.5zm-7-14a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V1.5a.5.5 0 0 1 .5-.5z"></path>
                </svg>
            </th>
            <th><fmt:message key="time" bundle="${bundle}"/></th>
            <th data-name="numberOfQuestion">
                <fmt:message key="numberOfQuestion" bundle="${bundle}"/>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-arrow-down-up" viewBox="0 0 16 16">
                    <path fill-rule="evenodd"
                          d="M11.5 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L11 2.707V14.5a.5.5 0 0 0 .5.5zm-7-14a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V1.5a.5.5 0 0 1 .5-.5z"></path>
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
                    <button type="button" class="btn btn-outline-success addUserQuiz" data-id="${quiz.id}">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-plus-square" viewBox="0 0 16 16">
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
    <div class="row arrows" style="display: none">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down"
             viewBox="0 0 16 16">
            <path fill-rule="evenodd"
                  d="M8 1a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L7.5 13.293V1.5A.5.5 0 0 1 8 1z"></path>
        </svg>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-up"
             viewBox="0 0 16 16">
            <path fill-rule="evenodd"
                  d="M8 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L7.5 2.707V14.5a.5.5 0 0 0 .5.5z"></path>
        </svg>
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-up"
             viewBox="0 0 16 16">
            <path fill-rule="evenodd"
                  d="M11.5 15a.5.5 0 0 0 .5-.5V2.707l3.146 3.147a.5.5 0 0 0 .708-.708l-4-4a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L11 2.707V14.5a.5.5 0 0 0 .5.5zm-7-14a.5.5 0 0 1 .5.5v11.793l3.146-3.147a.5.5 0 0 1 .708.708l-4 4a.5.5 0 0 1-.708 0l-4-4a.5.5 0 0 1 .708-.708L4 13.293V1.5a.5.5 0 0 1 .5-.5z"></path>
        </svg>
    </div>

</div>
<script>
    const content = document.getElementById('content');
    const search = document.getElementById('search');

    updateListeners();

    function updateListeners() {
        $(".bi-arrow-down-up, .bi-arrow-down, .bi-arrow-up").off("click").on("click", filter);
        $(".addUserQuiz").off("click").on("click", addUserQuiz);
    }

    function addUserQuiz() {
        const tr = $(this).closest("tr");
        const quizId = $(this).data("id");
        const alert = $(".alert");
        $.post("${pageContext.request.contextPath}/controller/user/quizzes/add", {quizId: quizId}, function (data) {
            alert.html("<fmt:message key="quiz" bundle="${bundle}"/>" + " " +
                tr.find("td")[0].innerText + " " + "<fmt:message key="user.quiz.successfullyAdded" bundle="${bundle}"/>").show();
            tr.remove();
            setTimeout(() => alert.hide(), 5000);
        }, "json");
    }

    function filter() {
        const svg = $(this);
        const th = svg.closest("th");
        const sortField = th.data("name");
        let sortOrder = 'desc';
        if (svg.hasClass("bi-arrow-down-up")) {
            $("th:has(.bi-arrow-down, .bi-arrow-up)").append($(".arrows .bi-arrow-down-up").clone())
            $("th>.bi-arrow-down, th>.bi-arrow-up").remove();
        }
        if (svg.hasClass("bi-arrow-down-up") || svg.hasClass("bi-arrow-up")) {
            th.append($(".arrows .bi-arrow-down").clone());
        }
        if (svg.hasClass("bi-arrow-down")) {
            th.append($(".arrows .bi-arrow-up").clone());
            sortOrder = 'asc';
        }
        svg.remove();
        $.post("${pageContext.request.contextPath}/controller/user/quizzes", {
                sortOrder: sortOrder,
                sortField: sortField,
                isJson: false
            },
            response => (content.innerHTML = response) && updateListeners());
    }
</script>

</body>
</html>
