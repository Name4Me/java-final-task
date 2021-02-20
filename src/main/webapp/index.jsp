<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
    </head>
    <body>
    <navbar:navbar/>
    <div class="container">
        <c:if test="${errorMessage != null}">
            <div class="row alert alert-danger" role="alert" style="display: block">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">${errorMessage}</span>
            </div>
        </c:if>
    </div>
    <div class="container" id="quizzes"></div>


    <script>
        const content = document.getElementById('quizzes');
        updateListeners();
        $.get("${pageContext.request.contextPath}/controller/user/quizzes",
            response => {
                response = response.replace(/\s*<(head|header)[^>]*>[\S\s]*?<\/(head|header)>\s*/gm,'')
                    .replace(/\s*<(script)[^>]*>[\S\s]*?<\/(script)>\s*/,'')
                    .replace(/<\/?(html|body)>/gm,'');
            const code = $('<div>'+response+'</div>');
            content.innerHTML = code.find('div.container')[1].innerHTML;
            updateListeners();
        });

        <c:if test="${errorMessage != null}">
            window.history.pushState("error", "Testing", "${pageContext.request.contextPath}/");
        </c:if>
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
                response => {content.innerHTML = response; updateListeners();});
        }


        function updateListeners() {
            $(".bi-arrow-down-up, .bi-arrow-down, .bi-arrow-up").off("click").on("click", filter);
        }
    </script>
    </body>
</html>