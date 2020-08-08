<%@page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="header.jsp"/>


<div class="container" id="article">

    <div class="panel panel-primary">
        <div class="panel-heading">Overview</div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>#Server</th>
                    <th>Platform</th>
                    <th>Owner</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>AMS Version</th>
                    <th>Note</th>
                </tr>
                </thead>
                    <c:forEach var="server" items="${servers}">
                        <tr>
                            <td>${server.server}</td>
                            <td>${server.platform}</td>
                            <td>${server.owner}</td>
                            <td>${server.startDate}</td>
                            <td>${server.endDate}</td>
                            <td>${server.amsVersion}</td>
                            <td>${server.note}</td>
                        </tr>
                    </c:forEach>
            </table>


        </div>
    </div>

</div>

<jsp:include page="footer.jsp"/>

</body>
</html>