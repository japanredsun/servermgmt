<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@page session="true"%>

<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container" id="article">

    <h2>${message}</h2>

    <div class="panel panel-primary">
        <div class="panel-heading">Server Form</div>
        <div class="panel-body">
            <spring:url value="/admin/add" var="addServerUrl" />
            <form:form class="form-horizontal" method="post"
                       modelAttribute="serverForm" action="${addServerUrl}">

                <spring:bind path="server">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-1 control-label">Server</label>
                        <div class="col-sm-10">
                            <form:input path="server" type="text" class="form-control"
                                        id="server" placeholder="192.168.92.201" />
                            <form:errors path="server" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="platform">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-1 control-label">Platform</label>
                        <div class="col-sm-10">
                            <form:input path="platform" type="text" class="form-control"
                                        id="platform" placeholder="RedHat/Linux" />
                            <form:errors path="platform" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="sshUserPassword">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-1 control-label">ssh <br> User/Password</label>
                        <div class="col-sm-10">
                            <form:input path="sshUserPassword" type="text" class="form-control"
                                        id="sshUserPassword" placeholder="root/12345" />
                            <form:errors path="sshUserPassword" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="rootPassword">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-1 control-label">Root Password</label>
                        <div class="col-sm-10">
                            <form:input path="rootPassword" type="text" class="form-control"
                                        id="rootPassword" placeholder="12345" />
                            <form:errors path="rootPassword" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="amsVersion">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-1 control-label">AMS Version</label>
                        <div class="col-sm-10">
                            <form:input path="amsVersion" type="text" class="form-control"
                                        id="amsVersion" placeholder="9.5.1.2" />
                            <form:errors path="amsVersion" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="note">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label class="col-sm-1 control-label">Note</label>
                        <div class="col-sm-10">
                            <form:input path="note" type="text" class="form-control"
                                        id="note" placeholder="Note for new server" />
                            <form:errors path="note" class="control-label" />
                        </div>
                    </div>
                </spring:bind>
                <%--<div class="col-sm-10">--%>
                    <%--<button type="reset" class="btn-lg btn-warning">Reset</button>--%>
                <%--</div>--%>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <button type="submit" class="btn-lg btn-primary pull-right">Update</button>
                </security:authorize>

            </form:form>


        </div>
    </div>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>