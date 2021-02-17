<%--@elvariable id="page" type="com.example.app.util.Page"--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>

<navbar:navbar/>

<!-- Page Content -->

<div class="container">
        <h1><fmt:message key="users" bundle="${bundle}"/></h1>
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
                            <td>${user.email}</td>
                            <td>${user.userStatus}</td>
                            <td>
                                <a type="button" class="btn btn-outline-success" href="${pageContext.request.contextPath}/controller/admin/user?id=${user.id}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
                                        <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"></path>
                                        <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"></path>
                                    </svg>
                                    <fmt:message key="view" bundle="${bundle}"/>
                                </a>
                                <button type="button" class="btn btn-outline-success" data-id="${user.id}">
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
<!-- /.container -->
<script>
    $(function() {
        $(".block").on("click",
            function(){
                const id = $(this).data("id");
                console.log(id);
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
                console.log(id);
                $.post( "${pageContext.request.contextPath}/controller/admin/user/unblock", {id: id},
                    function(data) {
                        if (data.result){
                            $("button.block[data-id="+id+"]").show();
                            $("button.unblock[data-id="+id+"]").hide();
                        }
                    },"json");
            });
    });
</script>
</body>
</html>
