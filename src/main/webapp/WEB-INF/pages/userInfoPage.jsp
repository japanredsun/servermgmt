<%@page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <title>${title}</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
<jsp:include page="header.jsp"/>


<div class="container" id="article">
    <h2>${message}</h2>
    <div class="panel panel-primary">
        <div class="panel-heading">Registered Server</div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>#Server</th>
                    <th>Platform</th>
                    <th>SSH User/Password</th>
                    <th>Root Password</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>AMS Version</th>
                    <th>Note</th>
                    <th>Action</th>
                </tr>
                </thead>
                <c:forEach var="server" items="${servers}">
                    <tr>
                        <td>${server.server}</td>
                        <td>${server.platform}</td>
                        <td>${server.sshUserPassword}</td>
                        <td>${server.rootPassword}</td>
                        <td>${server.startDate}</td>
                        <td>${server.endDate}</td>
                        <td>${server.amsVersion}</td>
                        <td>${server.note}</td>
                        <td>
                            <spring:url value="/server/${server.id}/unregister" var="unregisterUrl" />
                            <spring:url value="/server/${server.id}/extend" var="extendUrl" />

                            <button class="btn btn-warning"
                                    type="button" data-toggle="modal" data-target="#${server.id}">Unregister</button>
                            <div class="modal fade" id="${server.id}" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Un-register server ${server.server}?</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form:form class="form-horizontal" method="post"
                                                       modelAttribute="serverForm" action="${unregisterUrl}">
                                                <spring:bind path="server">
                                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                                        <form:input path="server" class="form-control"
                                                                    id="server" type="hidden" value ="${server.server} " />
                                                    </div>
                                                </spring:bind>
                                                <button type="submit" class="btn-lg btn-warning">Unregister</button>
                                            </form:form>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button class="btn btn-success"
                                    type="button" data-toggle="modal" data-target="#ext_${server.id}">Extend</button>
                            <div class="modal fade" id="ext_${server.id}" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Extend server ${server.server}?</h4>
                                        </div>
                                        <div class="modal-body">
                                            <form:form class="form-horizontal" method="post"
                                                       modelAttribute="serverForm" action="${extendUrl}">
                                                <spring:bind path="server">
                                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                                        <form:input path="server" class="form-control"
                                                                    id="server" type="hidden" value ="${server.server} " />
                                                    </div>
                                                </spring:bind>
                                                <spring:bind path="startDate">
                                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                                            <form:input path="startDate" class="form-control"
                                                                        id="startDate" type="hidden" value ="${server.startDate} " />
                                                    </div>
                                                </spring:bind>
                                                <spring:bind path="endDate">
                                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                                        <label class="col-sm-3 control-label">End date</label>
                                                        <div class="col-sm-9">
                                                            <form:input path="endDate" class="form-control"
                                                                        id="endDate" type="date" value ="${server.endDate}" />
                                                        </div>
                                                    </div>
                                                </spring:bind>
                                                <button type="submit" class="btn-lg btn-warning">Extend</button>
                                            </form:form>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </td>
                    </tr>
                </c:forEach>
            </table>


        </div>
    </div>
    <!-- Trigger the modal with a button -->
    <%--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>--%>

    <!-- Modal -->

</div>


<jsp:include page="footer.jsp"/>
</body>
</html>