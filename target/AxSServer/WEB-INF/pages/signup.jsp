<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SignUp</title>

</head>
<body>

<jsp:include page="header.jsp" />
<spring:url value="/signup" var="userSignup" />

<div class="container" id="article">
    <div class="row">

        <div class="col-md-4 col-md-offset-4 text-center">
            <div class="search-box">
                <div class="caption">
                    <h2 id="login-title">Sign Up</h2>
                </div>
                <form:form class="signForm" method="post"
                           modelAttribute="userForm" action="${userSignup}">
                <div class="input-group">
                    <spring:bind path="userName">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input path="userName" class="form-control"
                                            id="userName" type="text" placeholder="Email"  />
                            <form:errors path="userName" class="control-label" />
                        </div>
                    </spring:bind>
                    <spring:bind path="password">
                        <div class="form-group ${status.error ? 'has-error' : ''}">

                            <form:input path="password" class="form-control"
                                        id="password" type="text" placeholder="Password"/>
                            <form:errors path="password" class="control-label" />
                        </div>
                    </spring:bind>

                    <input name="submit" class="form-control" id="submit" type="submit" value="Submit" />
                    <input class="form-control" type="reset" value="Reset" />

                </div>

                </form:form>


            </div>
        </div>


    </div>




</div>




<jsp:include page="footer.jsp" />

</body>
</html>