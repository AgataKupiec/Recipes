<%--
  Created by IntelliJ IDEA.
  User: agata
  Date: 02.02.2020
  Time: 11:14
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
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <form:form method="post" modelAttribute="recipe" action="/recipe/add">
        Title: <form:input path="title"/><br>
        <form:errors path="title" cssClass="error"/><br>
        Number of servings: <form:input path="servings"/><br>
        <form:errors path="servings" cssClass="error"/><br>
        Time (minutes): <form:input path="time"/><br>
        <form:errors path="time" cssClass="error"/><br>
        Ingredients:<br>
        <table>

            <tr>
                <th></th>
                <th>Ingredient</th>
                <th>Amount</th>
                <th>Unit</th>
            </tr>
            <div data-id="0">
                <tr>
                    <td>
                        <button id="addProductFields" content="+"></button>
                    </td>
                    <td>
                        <form:input path="products[0].product.name" list="${productsList}"/>
                    </td>
                    <td>
                        <form:input path="products[0].quantity" />
                    </td>
                    <td>
                        <select name="unit" multiple="false">
                            <c:forEach items="${units}" var="unit">
                                <option value="${unit.id}" label="${unit.name}"/>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </div>
        </table>
        <br>
        Treść: <form:textarea path="content"/><br>
        <input type="submit"><br>
    </form:form>


</sec:authorize>
</body>
</html>
