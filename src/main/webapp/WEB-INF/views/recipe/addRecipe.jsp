<%--
  Created by IntelliJ IDEA.
  User: agata
  Date: 03.02.2020
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.ckeditor.com/ckeditor5/16.0.0/classic/ckeditor.js"></script>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <form:form method="post" modelAttribute="recipe" action="/recipe/add" enctype="multipart/form-data">
        Title: <form:input path="title"/><br>
        <form:errors path="title" cssClass="error"/><br>
        Number of servings: <form:input path="servings"/><br>
        <form:errors path="servings" cssClass="error"/><br>
        Time (minutes): <form:input path="time"/><br>
        <form:errors path="time" cssClass="error"/><br>
        Content: <form:textarea id="editor" path="content"/><br>
        <form:errors path="content" cssClass="error"/><br>
        File to upload: <input type="file" name="imageInput"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit"><br>
    </form:form>
</sec:authorize>
<script>
    ClassicEditor
        .create( document.querySelector('#editor'))
        .catch( error => {
        console.error( error );
    });
</script>
</body>
</html>

