<%@page session="false"%>
<html>
<head>
    <title>Access Denied</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container" id="article">
    <h3 style="color:red;">${message}</h3>
</div>

</body>
</html>