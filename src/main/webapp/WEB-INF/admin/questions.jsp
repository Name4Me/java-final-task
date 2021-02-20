<%--@elvariable id="page" type="com.example.app.util.Page"--%>
<%--@elvariable id="quizId" type="Integer"--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        <style>
            button.add {
                float: right;
            }

            .new-question {
                margin: 10px 0;
                position: relative;
            }

            .loader {
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
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h1><fmt:message key="questions" bundle="${bundle}"/></h1>
                </div>
                <div class="col-md-6">
                    <button type="button" class="btn btn-outline-success add">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-plus-square" viewBox="0 0 16 16">
                            <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"></path>
                            <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"></path>
                        </svg>
                        <fmt:message key="add" bundle="${bundle}"/>
                    </button>
                </div>
            </div>
            <form id="save-form">
                <div class="row new-question" style="display: none;">
                    <div class="loader" style="display: none;">
                        <div class="spinner-border text-success"></div>
                    </div>
                    <div class="col-md-5">
                        <input type="hidden" class="form-control" name="quizId" id="quizId" value="${quizId}">
                        <label for="text" class="sr-only"><fmt:message key="question" bundle="${bundle}" var="question"/></label>
                        <textarea name="text" cols="50" rows="3" class="form-control" placeholder="${question}" id="text"></textarea>
                    </div>
                    <div class="col-md-2">
                        <label for="type" class="sr-only"><fmt:message key="questionType" bundle="${bundle}"/></label>
                        <select class="form-control" name="type" id="type">
                            <option value="0" data-type="Single"><fmt:message key="questionType.Single"
                                                                              bundle="${bundle}"/></option>
                            <option value="1" data-type="Multiple"><fmt:message key="questionType.Multiple"
                                                                                bundle="${bundle}"/></option>
                        </select>
                    </div>
                    <div class="col-md-5">
                        <button type="button" class="btn btn-outline-info update-submit" data-action="update"
                                style="display: none">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-save" viewBox="0 0 16 16">
                                <path d="M2 1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H9.5a1 1 0 0 0-1 1v7.293l2.646-2.647a.5.5 0 0 1 .708.708l-3.5 3.5a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L7.5 9.293V2a2 2 0 0 1 2-2H14a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h2.5a.5.5 0 0 1 0 1H2z"></path>
                            </svg>
                            <fmt:message key="update" bundle="${bundle}"/>
                        </button>
                        <button type="button" class="btn btn-outline-success add-submit" data-action="save">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-save" viewBox="0 0 16 16">
                                <path d="M2 1a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H9.5a1 1 0 0 0-1 1v7.293l2.646-2.647a.5.5 0 0 1 .708.708l-3.5 3.5a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L7.5 9.293V2a2 2 0 0 1 2-2H14a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h2.5a.5.5 0 0 1 0 1H2z"></path>
                            </svg>
                            <fmt:message key="save" bundle="${bundle}"/>
                        </button>
                        <button type="button" class="btn btn-outline-danger cancel">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-x-square" viewBox="0 0 16 16">
                                <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"></path>
                                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"></path>
                            </svg>
                            <fmt:message key="cancel" bundle="${bundle}"/>
                        </button>
                    </div>
                </div>
            </form>
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="question" bundle="${bundle}"/></th>
                    <th><fmt:message key="questionType" bundle="${bundle}"/></th>
                    <th><fmt:message key="actions" bundle="${bundle}"/></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${page.items}" var="question">
                    <tr id="question_${question.id}">
                        <td class="question_text">${question.text}</td>
                        <td data-id="${question.type.ordinal()}"><fmt:message key="questionType.${question.type}"
                                                                              bundle="${bundle}"/></td>
                        <td>
                            <button type="button" class="btn btn-outline-success edit" data-id="${question.id}">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-pencil" viewBox="0 0 16 16">
                                    <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"></path>
                                </svg>
                                <fmt:message key="edit" bundle="${bundle}"/>
                            </button>
                            <a type="button" class="btn btn-outline-success choices" data-id="${question.id}"
                               href="${pageContext.request.contextPath}/controller/admin/choices?quizId=${quizId}&questionId=${question.id}&page=1">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-diagram-3" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                          d="M6 3.5A1.5 1.5 0 0 1 7.5 2h1A1.5 1.5 0 0 1 10 3.5v1A1.5 1.5 0 0 1 8.5 6v1H14a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-1 0V8h-5v.5a.5.5 0 0 1-1 0V8h-5v.5a.5.5 0 0 1-1 0v-1A.5.5 0 0 1 2 7h5.5V6A1.5 1.5 0 0 1 6 4.5v-1zM8.5 5a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1zM0 11.5A1.5 1.5 0 0 1 1.5 10h1A1.5 1.5 0 0 1 4 11.5v1A1.5 1.5 0 0 1 2.5 14h-1A1.5 1.5 0 0 1 0 12.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zm4.5.5A1.5 1.5 0 0 1 7.5 10h1a1.5 1.5 0 0 1 1.5 1.5v1A1.5 1.5 0 0 1 8.5 14h-1A1.5 1.5 0 0 1 6 12.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zm4.5.5a1.5 1.5 0 0 1 1.5-1.5h1a1.5 1.5 0 0 1 1.5 1.5v1a1.5 1.5 0 0 1-1.5 1.5h-1a1.5 1.5 0 0 1-1.5-1.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1z"></path>
                                </svg>
                                <fmt:message key="choices" bundle="${bundle}"/>
                            </a>
                            <button type="button" class="btn btn-outline-danger delete" data-id="${question.id}">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-trash" viewBox="0 0 16 16">
                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"></path>
                                    <path fill-rule="evenodd"
                                          d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"></path>
                                </svg>
                                <fmt:message key="delete" bundle="${bundle}"/>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                <tr class="template" style="display: none">
                    <td></td>
                    <td></td>
                    <td>
                        <button type="button" class="btn btn-outline-success edit" data-id="">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-pencil" viewBox="0 0 16 16">
                                <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"></path>
                            </svg>
                            <fmt:message key="edit" bundle="${bundle}"/>
                        </button>
                        <a type="button" class="btn btn-outline-success choices" data-id=""
                           href="${pageContext.request.contextPath}/controller/admin/choices?quizId=${quizId}&questionId=0&page=1">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-diagram-3" viewBox="0 0 16 16">
                                <path fill-rule="evenodd"
                                      d="M6 3.5A1.5 1.5 0 0 1 7.5 2h1A1.5 1.5 0 0 1 10 3.5v1A1.5 1.5 0 0 1 8.5 6v1H14a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-1 0V8h-5v.5a.5.5 0 0 1-1 0V8h-5v.5a.5.5 0 0 1-1 0v-1A.5.5 0 0 1 2 7h5.5V6A1.5 1.5 0 0 1 6 4.5v-1zM8.5 5a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1zM0 11.5A1.5 1.5 0 0 1 1.5 10h1A1.5 1.5 0 0 1 4 11.5v1A1.5 1.5 0 0 1 2.5 14h-1A1.5 1.5 0 0 1 0 12.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zm4.5.5A1.5 1.5 0 0 1 7.5 10h1a1.5 1.5 0 0 1 1.5 1.5v1A1.5 1.5 0 0 1 8.5 14h-1A1.5 1.5 0 0 1 6 12.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1zm4.5.5a1.5 1.5 0 0 1 1.5-1.5h1a1.5 1.5 0 0 1 1.5 1.5v1a1.5 1.5 0 0 1-1.5 1.5h-1a1.5 1.5 0 0 1-1.5-1.5v-1zm1.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1z"></path>
                            </svg>
                            <fmt:message key="choices" bundle="${bundle}"/>
                        </a>
                        <button type="button" class="btn btn-outline-danger delete" data-id="">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-trash" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"></path>
                                <path fill-rule="evenodd"
                                      d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"></path>
                            </svg>
                            <fmt:message key="delete" bundle="${bundle}"/>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="row">
                <ul class="pager">
                    <c:if test="${!page.first}">
                        <li>
                            <a href="${pageContext.request.contextPath}/controller/admin/questions?quizId=${quizId}&page=${page.number-1}&s=${page.size}">
                                <span aria-hidden="true">&larr;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${!page.last}">
                        <li>
                            <a href="${pageContext.request.contextPath}/controller/admin/questions?quizId=${quizId}&page=${page.number+1}&s=${page.size}">
                                <span aria-hidden="true">&rarr;</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
        <script>
            $(function () {
                const addBtn = $(".add");
                const submitBtn =  $(".add-submit, .update-submit");
                updateListeners();

                function edit() {
                    const id = $(this).data("id");
                    const row = $("tr#question_" + id).children();
                    $("textarea#text").val(row[0].innerText);
                    $("select#type").val($(row[1]).data("id"));
                    $(".update-submit").show();
                    $(".new-question").data("id", id).show();
                    addBtn.hide();
                }

                function del() {
                    const id = $(this).data("id");
                    $.post(
                        "${pageContext.request.contextPath}/controller/admin/question/delete",
                        {id: id},
                        function (data) {
                            if (data.result) {
                                $("tr#question_" + id).remove();
                            }
                        },
                        "json");
                }

                function cancel() {
                    $(".new-question").removeClass('disabled');
                    submitBtn.prop('disabled', false);
                    $("textarea#text").val("");
                    $("select#type").val(0);
                    $(".new-question, .loader, .update-submit").hide();
                    addBtn.show();
                }

                $(".cancel").on("click", cancel);

                addBtn.on("click", function () {
                    $(".new-question").show();
                    $(".add, .update-submit").hide();
                });


                submitBtn.on("click", function () {
                    $(this).prop('disabled', true);
                    $(".new-question").toggleClass('disabled');
                    $(".loader").show();
                    if ($(this).data('action') === 'save') add();
                    if ($(this).data('action') === 'update') update();
                    cancel();
                });

                function update() {
                    $.post("${pageContext.request.contextPath}/controller/admin/question/update",
                        $("#save-form").serialize() + '&id=' + $(".new-question").data("id"), update_row, "json")
                        .always(cancel);
                }

                function add() {
                    $.post("${pageContext.request.contextPath}/controller/admin/question/add",
                        $("#save-form").serialize(), add_row, "json").always(cancel);
                }

                function add_row(data) {
                    const row = $('.template').clone();
                    const choice = row.find(".choices");
                    row.attr('id', 'question_' + data.id);
                    row.children()[0].append(data.text);
                    row.children()[1].append($("option[data-type='" + data.type + "']").text());
                    row.find(".delete").data("id", data.id);
                    row.find(".edit").data("id", data.id);
                    choice.data("id", data.id).attr("href", choice.attr("href").replace("questionId=0", "questionId=" + data.id));
                    row.removeClass("template");
                    $(".table>tbody").append(row);
                    row.show();
                    updateListeners();
                }

                function update_row(data) {
                    const row = $("tr#question_" + data.id).children();
                    row[0].innerText = data.text;
                    row[1].innerText = $("option[data-type='" + data.type + "']").text();
                    $(".update-submit").hide();
                }

                function updateListeners(){
                    $(".edit").off("click").on("click", edit);
                    $(".delete").off("click").on("click", del);
                }
            });

        </script>
    </body>
</html>
