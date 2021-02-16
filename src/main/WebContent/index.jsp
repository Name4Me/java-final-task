<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
    <body>
    <navbar:navbar/>
    <div class="container">
        <c:if test="${errorMessage != null}">
            <div class="row alert alert-danger" role="alert" style="display: block">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">${errorMessage}</span>
            </div>
        </c:if>
    </div>


    <script>
        <c:if test="${errorMessage != null}">
            window.history.pushState("error", "Testing", "${pageContext.request.contextPath}/");
        </c:if>
    </script>
    </body>
</html>