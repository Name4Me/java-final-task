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
<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Periodicals</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <form class="navbar-form navbar-left" role="search" method="get" action="${pageContext.request.contextPath}/search">
                    <input type="hidden" name="p" value="1"/>
                    <input type="hidden" name="s" value="9"/>
                    <div class="form-group">
                        <input type="text" class="form-control" name="q" placeholder="<fmt:message key="search" bundle="${bundle}"/>">
                    </div>
                    <button type="submit" class="btn btn-default"><fmt:message key="search" bundle="${bundle}"/></button>
                </form>
            </ul>

            <ul class="nav navbar-nav navbar-right">

                <%--Admin--%>
                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false"><fmt:message key="admin" bundle="${bundle}"/> <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/magazines?p=1&s=10">
                                    <fmt:message key="magazines" bundle="${bundle}"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/publishers">
                                    <fmt:message key="publishers" bundle="${bundle}"/>
                                </a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/categories">
                                    <fmt:message key="categories" bundle="${bundle}"/>
                                </a>
                            </li>
                            <li role="separator" class="divider"></li>
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/subscriptions?p=1&s=10">
                                    <fmt:message key="subscriptions" bundle="${bundle}"/>
                                </a>
                            </li>
                            <li role="separator" class="divider"></li>
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/users?p=1&s=10">
                                    <fmt:message key="users" bundle="${bundle}"/>
                                </a>
                            </li>
                        </ul>
                    </li>
                </c:if>

                <%--User not logged in--%>
                <c:if test="${sessionScope.authenticated == null}">
                    <li>
                        <a href="${pageContext.request.contextPath}/login"><fmt:message key="signin" bundle="${bundle}"/></a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/register"><fmt:message key="signup" bundle="${bundle}"/></a>
                    </li>
                </c:if>

                <%--User controlls--%>
                <c:if test="${sessionScope.authenticated != null && sessionScope.authenticated == true}">
                    <c:if test="${sessionScope.role != 'ADMIN'}">
                        <li>
                            <a href="${pageContext.request.contextPath}/account"><c:out value="${sessionScope.username}"/></a>
                        </li>
                    </c:if>
                    <li>
                        <a href="${pageContext.request.contextPath}/logout"><fmt:message key="signout" bundle="${bundle}"/></a>
                    </li>
                </c:if>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>