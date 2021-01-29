<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Result</title>
</head>
<body>
    <a href="index.jsp">Back</a>
    ${result}
    <hr>
    ${requestScope.result}
    <hr>
    <%
        //pageContext
        request.getAttribute("result");
        //session
        //application
    %>
</body>
</html>