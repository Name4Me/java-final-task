<%--@elvariable id="page" type="com.example.app.util.Page"--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page import="com.example.app.model.assignment.AssignmentStatus" %>
<jsp:useBean id="date" class="java.util.Date"/>
<c:set var="title" value="true" />
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/assets/css/assignment.css"/>
    </head>
    <body>
        <navbar:navbar/>
        <div class="container" id="assignments">
           <div class="row">
              <div class="col-md-6">
                 <h1><fmt:message key="assignments" bundle="${bundle}"/></h1>
              </div>
           </div>

           <table class="table">
              <thead>
              <tr>
                 <th><fmt:message key="id" bundle="${bundle}"/></th>
                 <th><fmt:message key="name" bundle="${bundle}"/></th>
                 <th><fmt:message key="assigned" bundle="${bundle}"/></th>
                 <th><fmt:message key="status" bundle="${bundle}"/></th>
                 <th><fmt:message key="result" bundle="${bundle}"/></th>
              </tr>
              </thead>

              <tbody>
                      <c:forEach items="${page.items}" var="assigment">
                          <tr id="quiz_${assigment.quizId}" onclick="get(this)" data-id="${assigment.quizId}">
                              <td>${assigment.quizId}</td>
                              <td>${assigment.quiz.name}</td>
                              <td><fmt:formatDate value="${assigment.createdAt}" pattern="dd MMM yyyy, hh:mm"/></td>
                              <td><fmt:message key="${assigment.status}" bundle="${bundle}"/></td>
                              <td>
                                  <c:if test="${ assigment.score != 0 && assigment.status == AssignmentStatus.Completed}">
                                      ${assigment.score}% <fmt:message key="correct" bundle="${bundle}"/>
                                  </c:if>
                              </td>
                          </tr>
                      </c:forEach>
              </tbody>
           </table>

           <div class="row">
              <ul class="pager">
                 <c:if test="${!page.first}">
                    <li>
                       <a href="${pageContext.request.contextPath}/controller/user/quizzes?page=${page.number-1}&s=${page.size}">
                          <span aria-hidden="true">&larr;</span>
                       </a>
                    </li>
                 </c:if>

                 <c:if test="${!page.last}">
                    <li>
                       <a href="${pageContext.request.contextPath}/controller/user/quizzes?page=${page.number+1}&s=${page.size}">
                          <span aria-hidden="true">&rarr;</span>
                       </a>
                    </li>
                 </c:if>
              </ul>
           </div>
        </div>
        <script>
           const assignments = document.getElementById('assignments');
           $("tr").on("click", get)
           function get(){
              const id = this.dataset.id;
              console.log(id);
              $.post("${pageContext.request.contextPath}/controller/user/assignments/assignment",
                      {quizId : id}, response => assignments.innerHTML = response);
           }
        </script>
    </body>
</html>
