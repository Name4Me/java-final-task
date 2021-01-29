<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<form action="hello-servlet" method="post">
    <input name="name" value="">
    <input type="password" name="password" value="">
    <BR>
    <input type="submit">
    <% System.out.println("Hi");%>
</form>
<BR>
<form action="hello-servlet" >
    <input name="name" value="">
    <BR>
    <input type="submit">
    <% System.out.println("Hi");%>
</form>
</body>
</html>