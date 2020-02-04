<%--
  Created by IntelliJ IDEA.
  User: agata
  Date: 02.02.2020
  Time: 09:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <p>Zalogowany jako: <sec:authentication property="name"/></p>
    <p>Posiada role: <sec:authentication property="authorities"/></p>
</sec:authorize>


</body>
</html>
