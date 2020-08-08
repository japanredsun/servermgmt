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
        <div class="panel-heading">Available Server</div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>#Server</th>
                    <th>Platform</th>
                    <th>AMS Version</th>
                    <th>Action</th>
                </tr>
                </thead>
                <c:forEach var="server" items="${servers}">
                    <tr>
                        <td>${server.server}</td>
                        <td>${server.platform}</td>
                        <td>${server.amsVersion}</td>
                        <td>
                            <spring:url value="/server/${server.id}/register" var="registerUrl" />
                            <spring:url value="/server/${server.id}/delete" var="deleteUrl" />
                            <spring:url value="/server/${server.id}/update" var="updateURL" />

                            <button class="btn btn-info"
                                    type="button" data-toggle="modal" data-target="#reg_${server.id}">Register</button>
                                    <div class="modal fade" id="reg_${server.id}" role="dialog">
                                                                      <div class="modal-dialog">

                                                                                      <!-- Modal content-->
                                                                            <div class="modal-content">
                                                                                              <div class="modal-header">
                                                                                                          <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                                                          <h4 class="modal-title">Register server ${server.server}?</h4>
                                                                                               </div>
                                                                                 <div class="modal-body">
                                                                                    <form:form class="form-horizontal" method="post"
                                                                                                 modelAttribute="serverForm" action="${registerUrl}">

                                                                                     <spring:bind path="startDate">
                                                                                               <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                                                   <label class="col-sm-3 control-label">Start date</label>
                                                                                                   <div class="col-sm-9">
                                                                                                        <form:input path="startDate" class="form-control"
                                                                                                                                        id="startDate" type="date" />
                                                                                                       <%--<form:errors path="startDate" class="control-label" />--%>
                                                                                                   </div>
                                                                                               </div>
                                                                                           </spring:bind>
                                                                                        <spring:bind path="endDate">
                                                                                                   <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                                                       <label class="col-sm-3 control-label">End date</label>
                                                                                                       <div class="col-sm-9">
                                                                                                            <form:input path="endDate" class="form-control"
                                                                                                                                            id="endDate" type="date" />
                                                                                                            <%--<form:errors path="endDate" class="control-label" />--%>
                                                                                                       </div>
                                                                                                   </div>
                                                                                               </spring:bind>
                                                                         <spring:bind path="note">
                                                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                                <label class="col-sm-3 control-label">Note</label>
                                                                                <div class="col-sm-9">
                                                                                    <form:input path="note" type="text" class="form-control"
                                                                                                id="note"/>
                                                                                    <form:errors path="note" class="control-label" />
                                                                                </div>
                                                                            </div>
                                                                        </spring:bind>
                                                                                      <button type="submit" class="btn-lg btn-info">Register</button>


                                                                                             </form:form>
                                                                                       </div>

                                                                                          <div class="modal-footer">
                                                                                               <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                                                     </div>
                                                                                                  </div>

                                                                                          </div>
                                                                                </div>

                                <security:authorize access="hasRole('ROLE_ADMIN')">
                                    <button class="btn btn-primary"
                                            type="button" data-toggle="modal" data-target="#${server.id}">Update</button>

                                    <div class="modal fade" id="${server.id}" role="dialog">
                                        <div class="modal-dialog">

                                            <!-- Modal content-->
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                    <h4 class="modal-title">Server: ${server.server}</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <spring:url value="/admin/add" var="addServerUrl" />
                                                    <form:form class="form-horizontal" method="post"
                                                               modelAttribute="serverForm" action="${updateURL}">

                                                        <spring:bind path="server">
                                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                <label class="col-sm-3 control-label">Server</label>
                                                                <div class="col-sm-9">
                                                                    <form:input path="server" type="text" class="form-control"
                                                                                id="server" value="${server.server}" />
                                                                    <form:errors path="server" class="control-label" />
                                                                </div>
                                                            </div>
                                                        </spring:bind>
                                                        <spring:bind path="platform">
                                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                <label class="col-sm-3 control-label">Platform</label>
                                                                <div class="col-sm-9">
                                                                    <form:input path="platform" type="text" class="form-control"
                                                                                id="platform" value="${server.platform}" />
                                                                    <form:errors path="platform" class="control-label" />
                                                                </div>
                                                            </div>
                                                        </spring:bind>
                                                        <spring:bind path="sshUserPassword">
                                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                <label class="col-sm-3 control-label">ssh <br> User/Password</label>
                                                                <div class="col-sm-9">
                                                                    <form:input path="sshUserPassword" type="text" class="form-control"
                                                                                id="sshUserPassword" value="${server.sshUserPassword}" />
                                                                    <form:errors path="sshUserPassword" class="control-label" />
                                                                </div>
                                                            </div>
                                                        </spring:bind>
                                                        <spring:bind path="rootPassword">
                                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                <label class="col-sm-3 control-label">Root Password</label>
                                                                <div class="col-sm-9">
                                                                    <form:input path="rootPassword" type="text" class="form-control"
                                                                                id="rootPassword" value="${server.rootPassword}" />
                                                                    <form:errors path="rootPassword" class="control-label" />
                                                                </div>
                                                            </div>
                                                        </spring:bind>
                                                        <spring:bind path="amsVersion">
                                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                <label class="col-sm-3 control-label">AMS Version</label>
                                                                <div class="col-sm-9">
                                                                    <form:input path="amsVersion" type="text" class="form-control"
                                                                                id="amsVersion" value="${server.amsVersion}" />
                                                                    <form:errors path="amsVersion" class="control-label" />
                                                                </div>
                                                            </div>
                                                        </spring:bind>
                                                        <spring:bind path="note">
                                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                <label class="col-sm-3 control-label">Note</label>
                                                                <div class="col-sm-9">
                                                                    <form:input path="note" type="text" class="form-control"
                                                                                id="note" value="${server.note}" />
                                                                    <form:errors path="note" class="control-label" />
                                                                </div>
                                                            </div>
                                                        </spring:bind>
                                                        <%--<div class="col-sm-10">--%>
                                                        <%--<button type="reset" class="btn-lg btn-warning">Reset</button>--%>
                                                        <%--</div>--%>
                                                        <button type="submit" class="btn-lg btn-primary">Update</button>


                                                    </form:form>
                                                </div>

                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                </div>
                                            </div>

                                        </div>
                                    </div>


                            <button class="btn btn-danger"
                                     type="button" data-toggle="modal" data-target="#del_${server.id}">Delete</button>
                             <div class="modal fade" id="del_${server.id}" role="dialog">
                                                                     <div class="modal-dialog">

                                                                         <!-- Modal content-->
                                                                         <div class="modal-content">
                                                                             <div class="modal-header">
                                                                                 <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                                 <h4 class="modal-title">Do you want to delete server ${server.server}?</h4>
                                                                             </div>
                                                                             <div class="modal-body">
                                                                                 <form:form class="form-horizontal" method="post"
                                                                                            modelAttribute="serverForm" action="${deleteUrl}">
                                                                                     <button type="submit" class="btn-lg btn-danger">Delete</button>


                                                                                 </form:form>
                                                                             </div>

                                                                             <div class="modal-footer">
                                                                                 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                             </div>
                                                                         </div>

                                                                     </div>
                                                                 </div>

                                </security:authorize>
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



</body>
<jsp:include page="footer.jsp"/>
</html>