<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="navbar" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <div class="col-md-6">
            <button type="button" class="btn btn-outline-success add">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
                    <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"></path>
                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
                </svg>
                Add
            </button>
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
                <th><fmt:message key="name" bundle="${bundle}"/></th>
                <th><fmt:message key="description" bundle="${bundle}"/></th>
                <th><fmt:message key="difficulty" bundle="${bundle}"/></th>
                <th><fmt:message key="time" bundle="${bundle}"/></th>
                <th><fmt:message key="numberOfQuestion" bundle="${bundle}"/></th>
                <th><fmt:message key="actions" bundle="${bundle}"/></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${page.items}" var="quiz">
                <tr id="quiz_${quiz.id}">
                    <td align="center">${quiz.name}</td>
                    <td align="center">${quiz.description}</td>
                    <td align="center">${quiz.difficulty}</td>
                    <td align="center">${quiz.time}</td>
                    <td align="center">${quiz.numberOfQuestion}</td>
                    <td align="center">
                        <button type="button" class="btn btn-outline-success edit" data-id="${quiz.id}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"></path>
                            </svg>
                            Edit
                        </button>
                        <a type="button" class="btn btn-outline-success" href="${pageContext.request.contextPath}/admin/questions/?quizId=${quiz.id}&page=1">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-question-circle" viewBox="0 0 16 16">
                                <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
                                <path d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"></path>
                            </svg>
                            <fmt:message key="questions" bundle="${bundle}"/>
                        </a>
                        <button type="button" class="btn btn-outline-danger delete" data-id="${quiz.id}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"></path>
                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"></path>
                            </svg>
                            Delete
                        </button>
                    </td>
                </tr>
            </c:forEach>
            <tr class="template" style="display: none">
                <td align="center"></td>
                <td align="center"></td>
                <td align="center"></td>
                <td align="center"></td>
                <td align="center"></td>
                <td align="center">
                    <button type="button" class="btn btn-outline-success edit" data-id="">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                            <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"></path>
                        </svg>
                        Edit
                    </button>
                    <button type="button" class="btn btn-outline-success">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-question-circle" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
                            <path d="M5.255 5.786a.237.237 0 0 0 .241.247h.825c.138 0 .248-.113.266-.25.09-.656.54-1.134 1.342-1.134.686 0 1.314.343 1.314 1.168 0 .635-.374.927-.965 1.371-.673.489-1.206 1.06-1.168 1.987l.003.217a.25.25 0 0 0 .25.246h.811a.25.25 0 0 0 .25-.25v-.105c0-.718.273-.927 1.01-1.486.609-.463 1.244-.977 1.244-2.056 0-1.511-1.276-2.241-2.673-2.241-1.267 0-2.655.59-2.75 2.286zm1.557 5.763c0 .533.425.927 1.01.927.609 0 1.028-.394 1.028-.927 0-.552-.42-.94-1.029-.94-.584 0-1.009.388-1.009.94z"></path>
                        </svg>
                        <fmt:message key="questions" bundle="${bundle}"/>
                    </button>
                    <button type="button" class="btn btn-outline-danger delete" data-id="">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"></path>
                            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"></path>
                        </svg>
                        Delete
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </c:if>

    <div class="row">
        <ul class="pager">
            <c:if test="${!page.first}">
                <li>
                    <a href="${pageContext.request.contextPath}/admin/quizzes?p=${page.number-1}&s=${page.size}">
                        <span aria-hidden="true">&larr;</span>
                    </a>
                </li>
            </c:if>

            <c:if test="${!page.last}">
                <li>
                    <a href="${pageContext.request.contextPath}/admin/quizzes?p=${page.number+1}&s=${page.size}">
                        <span aria-hidden="true">&rarr;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<!-- /.container -->


<script>
    $(function() {
        function edit(){
            const id = $(this).data("id");
            const row = $("tr#quiz_"+id).children();
            $("input#name").val(row[0].innerText);
            $("input#description").val(row[1].innerText);
            $("select#difficulty").val(row[2].innerText);
            $("input#time").val(row[3].innerText);
            $("input#numberOfQuestion").val(row[4].innerText);
            $(".add-submit").html($(".add-submit").html().replace('Save', 'Update')).data("action", "update");
            $(".new-quiz").data("id", id).show();

        }
        function del(){
            const id = $(this).data("id");
            $.post(
                "${pageContext.request.contextPath}/admin/quiz/delete",
                {id: id},
                function(data) { if (data.result){ $("tr#quiz_"+id).remove(); } },
                "json");
        }

        $(".edit").on( "click", edit);
        $(".delete").on( "click", del);
        $(".add").on( "click", function() {
            if ($(".new-quiz").is(":visible")){
                $(".new-quiz").hide();
            } else {
                $(".new-quiz").show();
            }
        });


        $(".add-submit").on( "click", function() {
            $(this).prop('disabled', true);
            $(".new-quiz").toggleClass('disabled');
            $(".loader").show();
            if ($(this).data('action') == 'save') add();
            if ($(this).data('action') == 'update') {
                update();
            }
        });

        function update(){
            $.post( "${pageContext.request.contextPath}/admin/quiz/update",
                $("#save-form").serialize()+'&id='+$(".new-quiz").data("id"),
                update_row)
                .fail(function() { console.log( "error" ); })
                .always(function() {
                $(".new-quiz, .loader").hide();
                $(".new-quiz").toggleClass('disabled');
                $(".add-submit").prop('disabled', false);
            },"json");
        }

        function add(){
            $.post( "${pageContext.request.contextPath}/admin/quiz/add",
                $("#save-form").serialize(),
                add_row).done(function() {
                console.log( "second success" );
            }).fail(function() {
                console.log( "error" );
            }).always(function() {
                console.log( "finished" );
                $(".new-quiz, .loader").hide();
                $(".new-quiz").toggleClass('disabled');
                $(".add-submit").prop('disabled', false);
            },"json");
        }

        function add_row(data){
            let row = $('.template').clone();
            row.attr('id', 'quiz_' + data.id);
            row.children()[0].append(data.name);
            row.children()[1].append(data.description);
            row.children()[2].append(data.difficulty);
            row.children()[3].append(data.time);
            row.children()[4].append(data.numberOfQuestion);
            row.find(".delete").data("id", data.id);
            row.find(".edit").data("id", data.id);
            $(".table>tbody").append(row);
            row.show();
            $(".edit").off( "click").on( "click", edit);
            $(".delete").off( "click").on( "click", del);
        }

        function update_row(data){
            const row = $("tr#quiz_"+data.id).children();
            row[0].innerText = data.name;
            row[1].innerText = data.description;
            row[2].innerText = data.difficulty;
            row[3].innerText = data.time;
            row[4].innerText = data.numberOfQuestion;
            $(".add-submit").html($(".add-submit").html().replace('Update', 'Save')).data("action", "save");
        }
    });

</script>

</body>
</html>
