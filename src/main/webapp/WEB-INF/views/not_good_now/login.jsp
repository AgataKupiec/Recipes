<%--
  Created by IntelliJ IDEA.
  User: agata
  Date: 01.02.2020
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <sec:authorize access="isAuthenticated()">
        <p>Zalogowany jako: <sec:authentication property="principal.username"/></p>
        <p>Posiada role: <sec:authentication property="principal.authorities"/></p>
    </sec:authorize>

</form>
</body>
</html>
