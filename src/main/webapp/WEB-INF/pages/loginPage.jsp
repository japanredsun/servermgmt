<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head><title>Login</title>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container" id="article">
    <div class="row">

        <div class="col-md-4 col-md-offset-4 text-center">
            <div class="search-box">
                <div class="caption">
                    <h2 id="login-title">Login</h2>
                    <h3>Enter email and password:</h3>
                </div>
                <form class="loginForm" name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
                    <div class="input-group">
                        <input type="text" name="username" id="name" class="form-control" placeholder="Email">
                        <input type="password" name="password" id="paw" class="form-control" placeholder="Password">
                        <input type="submit" name="submit" id="submit" class="form-control" value="Submit">
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-4">
            <div class="aro-pswd_info">
                <div id="pswd_info">
                <!-- /login?error=true -->
                <c:if test="${param.error == 'true'}">
                    <div style="color:red;margin:10px 0px;">

                        Login Failed!!!<br />
                        Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

                    </div>
                </c:if>
                </div>
            </div>

        </div>


    </div>






</div>
<jsp:include page="footer.jsp"/>
</body>
</html>