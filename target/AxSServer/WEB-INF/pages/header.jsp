<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html lang="en">
<head>

    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/welcome">AxS Production Servers</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/welcome">Home</a></li>
                <%--<li><a href="${pageContext.request.contextPath}/userInfo">User Info</a></li>--%>
                <li><a href="${pageContext.request.contextPath}/admin">Admin</a> </li>
                <security:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/server/list">Register Server</a> </li>
                </security:authorize>
                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <li><a href="${pageContext.request.contextPath}/login">Login</a> </li>
                    <li><a href="${pageContext.request.contextPath}/signup">Sign Up</a> </li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav" id="usernavbar">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li><a href="${pageContext.request.contextPath}/userInfo">Hi, ${pageContext.request.userPrincipal.name}</a> </li>
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a> </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<!-- /.container -->
<script type="text/javascript"
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>