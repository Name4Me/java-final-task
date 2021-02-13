<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="navbar"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="header"%>
<!DOCTYPE html>
<html>
    <head>
        <header:header/>
    </head>
    <body>
    <navbar:navbar/>
    <div class="container">
        <h1>${msg.header}</h1>
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