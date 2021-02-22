<%--@elvariable id="page" type="com.example.app.util.Page"--%>
<%--@elvariable id="quizId" type="Integer"--%>
<%--@elvariable id="questionId" type="Integer"--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        <style>
            .table thead th {
                text-align: center;
                vertical-align: middle;
            }

            button.add {
                float: right;
            }

            .new-choice {
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

            .back {
                width: auto;
                float: left;
                padding-top: 22px;
                padding-left: 20px;
            }
        </style>
    </head>
<body>
<navbar:navbar/>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <h1 style="width: auto; float: left"><fmt:message key="choices" bundle="${bundle}"/></h1>
            <a class="back"
               href="${pageContext.request.contextPath}/controller/admin/questions/?quizId=${quizId}&page=1"><fmt:message
                    key="back" bundle="${bundle}"/></a>
        </div>
        <div class="col-md-4">
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
    <form id="saveForm">
        <div class="row new-choice" style="display: none;">
            <div class="loader" style="display: none;">
                <div class="spinner-border text-success"></div>
            </div>
            <div class="col-md-5">
                <input type="hidden" class="form-control" name="questionId" id="questionId" value="${questionId}"
                       required>
                <label for="text" class="sr-only"><fmt:message key="choice" bundle="${bundle}" var="choice"/></label>
                <textarea name="text" cols="50" rows="3" class="form-control" placeholder="${choice}"
                          id="text" required></textarea>
            </div>
            <div class="col-md-2">
                <label for="isCorrect" class="sr-only"><fmt:message key="correct" bundle="${bundle}"/></label>
                <input type="checkbox" class="form-control" name="isCorrect" id="isCorrect">
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
            <th><fmt:message key="choice" bundle="${bundle}"/></th>
            <th><fmt:message key="correct" bundle="${bundle}"/></th>
            <th><fmt:message key="actions" bundle="${bundle}"/></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${page.items}" var="choice">
            <tr id="choice_${choice.id}">
                <td><c:out value="${choice.text}" escapeXml="true"/></td>
                <td data-correct="${choice.isCorrect}">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="green"
                         class="bi bi-check2-all" viewBox="0 0 16 16"
                         style="display: ${choice.isCorrect ? "block" : "none"}">
                        <path d="M12.354 4.354a.5.5 0 0 0-.708-.708L5 10.293 1.854 7.146a.5.5 0 1 0-.708.708l3.5 3.5a.5.5 0 0 0 .708 0l7-7zm-4.208 7l-.896-.897.707-.707.543.543 6.646-6.647a.5.5 0 0 1 .708.708l-7 7a.5.5 0 0 1-.708 0z"></path>
                        <path d="M5.354 7.146l.896.897-.707.707-.897-.896a.5.5 0 1 1 .708-.708z"></path>
                    </svg>
                </td>
                <td>
                    <button type="button" class="btn btn-outline-success edit" data-id="${choice.id}">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-pencil" viewBox="0 0 16 16">
                            <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"></path>
                        </svg>
                        <fmt:message key="edit" bundle="${bundle}"/>
                    </button>
                    <button type="button" class="btn btn-outline-danger delete" data-id="${choice.id}">
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
            <td>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="green"
                     class="bi bi-check2-all" viewBox="0 0 16 16" style="display: none">
                    <path d="M12.354 4.354a.5.5 0 0 0-.708-.708L5 10.293 1.854 7.146a.5.5 0 1 0-.708.708l3.5 3.5a.5.5 0 0 0 .708 0l7-7zm-4.208 7l-.896-.897.707-.707.543.543 6.646-6.647a.5.5 0 0 1 .708.708l-7 7a.5.5 0 0 1-.708 0z"></path>
                    <path d="M5.354 7.146l.896.897-.707.707-.897-.896a.5.5 0 1 1 .708-.708z"></path>
                </svg>
            </td>
            <td>
                <button type="button" class="btn btn-outline-success edit" data-id="">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-pencil" viewBox="0 0 16 16">
                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"></path>
                    </svg>
                    <fmt:message key="edit" bundle="${bundle}"/>
                </button>
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
                    <a href="${pageContext.request.contextPath}/controller/admin/choices?questionId=${questionId}&page=${page.number-1}&s=${page.size}">
                        <span aria-hidden="true">&larr;</span>
                    </a>
                </li>
            </c:if>

            <c:if test="${!page.last}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller/admin/choices?questionId=${questionId}&page=${page.number+1}&s=${page.size}">
                        <span aria-hidden="true">&rarr;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<script>
    $(function () {
        const saveForm = document.getElementById("saveForm");
        const addBtn = $(".add");
        const submitBtn = $(".add-submit, .update-submit");
        updateListeners();

        if (!HTMLFormElement.prototype.reportValidity) {
            HTMLFormElement.prototype.reportValidity = function () {
                if (this.checkValidity()) return true;
                const btn = document.createElement('button');
                this.appendChild(btn);
                btn.click();
                this.removeChild(btn);
                return false;
            }
        }

        function edit() {
            const id = $(this).data("id");
            const row = $("tr#choice_" + id).children();
            $("textarea#text").val(row[0].innerText);
            $("input#isCorrect").prop("checked", row[1].dataset.correct === 'true');
            $(".update-submit").show();
            $(".new-choice").data("id", id).show();
            addBtn.hide();
        }

        function del() {
            const id = $(this).data("id");
            $.post(
                "${pageContext.request.contextPath}/controller/admin/choice/delete",
                {id: id},
                function (data) {
                    if (data.result) {
                        $("tr#choice_" + id).remove();
                    }
                },
                "json");
        }

        function cancel() {
            $(".new-choice").removeClass('disabled');
            submitBtn.prop('disabled', false);
            $("textarea#text").val("");
            $("input#isCorrect").prop("checked", false);
            $(".new-choice, .loader, .update-submit").hide();
            addBtn.show();
        }

        $(".cancel").on("click", cancel);

        addBtn.on("click", function () {
            $(".new-choice").show();
            $(".add, .update-submit").hide();
        });


        submitBtn.on("click", function () {
            if (!saveForm.checkValidity()) {
                saveForm.reportValidity();
                return false;
            }
            $(this).prop('disabled', true);
            $(".new-choice").toggleClass('disabled');
            $(".loader").show();
            if ($(this).data('action') === 'save') add();
            if ($(this).data('action') === 'update') {
                update();
            }
            cancel();
        });

        function update() {
            $.post("${pageContext.request.contextPath}/controller/admin/choice/update",
                $("#saveForm").serialize() + '&id=' + $(".new-choice").data("id"), update_row, "json")
                .fail(function () {
                    console.log("error");
                })
                .always(cancel);
        }

        function add() {
            $.post("${pageContext.request.contextPath}/controller/admin/choice/add",
                $("#saveForm").serialize(), add_row, "json")
                .fail(function () {
                    console.log("error");
                })
                .always(cancel);
        }

        function add_row(data) {
            const row = $('.template').clone();
            const choice = row.find(".choices");
            row.attr('id', 'choice_' + data.id);
            row.children()[0].append(data.text);
            if (data.isCorrect) {
                row.children()[1].innerHTML = row.children()[1].innerHTML.replace("display: none", "display: block");
            }
            row.find(".delete").data("id", data.id);
            row.find(".edit").data("id", data.id);
            choice.data("id", data.id);
            row.removeClass("template");
            $(".table>tbody").append(row);
            row.show();
            updateListeners();
        }

        function update_row(data) {
            const row = $("tr#choice_" + data.id).children();
            row[0].innerText = data.text;
            if (data.isCorrect) {
                row[1].innerHTML = row[1].innerHTML.replace("display: none", "display: block");
            } else {
                row[1].innerHTML = row[1].innerHTML.replace("display: block", "display: none");
            }
            $(".update-submit").hide();
        }

        function updateListeners() {
            $(".edit").off("click").on("click", edit);
            $(".delete").off("click").on("click", del);
        }
    });

</script>

</body>
</html>
