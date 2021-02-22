<%--@elvariable id="page" type="com.example.app.util.Page"--%>
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
                <h1><fmt:message key="users" bundle="${bundle}"/></h1>
                <input type="hidden" id="msgEmail" value="<fmt:message key="emailWarning" bundle="${bundle}"/>">
                <form action="" id="updateForm" style="display: none">
                    <div class="form-group">
                        <label for="inputEmail" class="sr-only">Email address</label>
                        <input type="email" id="inputEmail" class="form-control" style="position: absolute;
                        width: 350px; top: 12px; left: 25px;"
                               placeholder="<fmt:message key="email" bundle="${bundle}"/>" required autofocus name="email" >
                    </div>
                </form>
                    <table class="table">
                        <thead>
                        <tr>
                            <th><fmt:message key="email" bundle="${bundle}"/></th>
                            <th><fmt:message key="status" bundle="${bundle}"/></th>
                            <th><fmt:message key="actions" bundle="${bundle}"/></th>
                        </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${page.items}" var="user">
                                <tr>
                                    <td style="position: relative;"><c:out value="${user.email}" escapeXml="true"/></td>
                                    <td><c:out value="${user.userStatus}" escapeXml="true"/></td>
                                    <td>
                                        <button type="button" class="btn btn-outline-success edit" data-id="${user.id}">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                                <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"></path>
                                            </svg>
                                            Edit
                                        </button>
                                        <button type="button" class="btn btn-outline-success unblock" data-id="${user.id}"  style="display: ${user.userStatus == 'BLOCKED' ? 'initial' : 'none'}">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-unlock" viewBox="0 0 16 16">
                                                <path d="M11 1a2 2 0 0 0-2 2v4a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V9a2 2 0 0 1 2-2h5V3a3 3 0 0 1 6 0v4a.5.5 0 0 1-1 0V3a2 2 0 0 0-2-2zM3 8a1 1 0 0 0-1 1v5a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V9a1 1 0 0 0-1-1H3z"></path>
                                            </svg>
                                            Unlock
                                        </button>
                                        <button type="button" class="btn btn-outline-danger block" data-id="${user.id}" style="display: ${user.userStatus == 'BLOCKED' ? 'none' : 'initial'}" >
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-lock" viewBox="0 0 16 16">
                                                <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"></path>
                                            </svg>
                                            Block
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
                                <a href="${pageContext.request.contextPath}/controller/admin/users?p=${page.number-1}&s=${page.size}">
                                    <span aria-hidden="true">&larr;</span>
                                </a>
                            </li>
                        </c:if>

                        <c:if test="${!page.last}">
                            <li>
                                <a href="${pageContext.request.contextPath}/controller/admin/users?p=${page.number+1}&s=${page.size}">
                                    <span aria-hidden="true">&rarr;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        <script>
            $(function() {
                const email = document.getElementById("inputEmail");
                const msgEmail = document.getElementById("msgEmail");
                const updateForm = document.getElementById("updateForm");
                email.onchange = validateEmail;

                $(email).on('focusout',function (){
                    if(updateForm.checkValidity() && email.value !== email.dataset.text){
                        $(updateForm).submit();
                    }
                })

                function validateEmail() {
                    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                    email.setCustomValidity(re.test(String(email.value).toLowerCase()) ? '' : msgEmail.value);
                }

                $("#updateForm").on("submit", () => {
                    console.log("id: ", email.dataset.id, " email: ", email.value);
                    $.post( "${pageContext.request.contextPath}/controller/admin/user/update", {id: email.dataset.id, email: email.value},
                            function(data) {
                                console.log(data);
                                if (data.result){
                                }
                            },"json");

                    return false;
                });

                $(".block").on("click",
                    function(){
                        const id = $(this).data("id");
                        $.post( "${pageContext.request.contextPath}/controller/admin/user/block", {id: id},
                            function(data) {
                                if (data.result){
                                    $("button.block[data-id="+id+"]").hide();
                                    $("button.unblock[data-id="+id+"]").show();
                                }
                            },"json");
                    });
                $(".unblock").on("click",
                    function(){
                        const id = $(this).data("id");
                        $.post( "${pageContext.request.contextPath}/controller/admin/user/unblock", {id: id},
                            function(data) {
                                if (data.result){
                                    $("button.block[data-id="+id+"]").show();
                                    $("button.unblock[data-id="+id+"]").hide();
                                }
                            },"json");
                    });

                $(".edit").on("click",
                    function(){
                        const id = $(this).data("id");
                        const tr = $(this).closest('tr');
                        const td = $(tr.find('td')[0]);
                        email.dataset.id = id;
                        email.dataset.text = td.text();
                        email.value = td.text();
                        td.append($('#updateForm').show());
                    });


            });
        </script>
    </body>
</html>
