<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@tag description="Page navigation bar" pageEncoding="UTF-8"%>
<%@attribute name="navbar" fragment="true" %>

<%--Localization--%>
<c:if test="${sessionScope.locale == null}">
    <fmt:setLocale value="ru"/>
</c:if>
<c:if test="${sessionScope.locale != null}">
    <fmt:setLocale value="${sessionScope.locale}"/>
</c:if>

<fmt:setBundle basename="localization" var="bundle"/>
<%----%>

<!-- Navigation -->
<style>
    header{
        height: 90px;
    }
</style>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Testing</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
                </li>
                <c:if test="${sessionScope.authenticated == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login"><fmt:message key="signIn" bundle="${bundle}"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/register"><fmt:message key="signup" bundle="${bundle}"/></a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.authenticated != null && sessionScope.authenticated == true}">
                    <c:if test="${sessionScope.role == 'USER'}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/account"><c:out value="${sessionScope.username}"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/assignments"><fmt:message key="assignments" bundle="${bundle}"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user/quizzes"><fmt:message key="quizzes" bundle="${bundle}"/></a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.role == 'ADMIN'}">
                        <li class="nav-item dropdown show">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <fmt:message key="admin" bundle="${bundle}"/> <span class="caret"></span></a>
                            <div class="dropdown-menu" aria-labelledby="dropdown01">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/users">
                                    <fmt:message key="users" bundle="${bundle}"/>
                                </a>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/quizzes">
                                    <fmt:message key="quizzes" bundle="${bundle}"/>
                                </a>
                            </div>
                        </li>
                    </c:if>

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout"><fmt:message key="signOut" bundle="${bundle}"/></a>
                    </li>
                </c:if>



                <%--<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a></li>--%>
            </ul>
        </div>
    </nav>
</header>