<%--
  Created by IntelliJ IDEA.
  User: agata
  Date: 03.02.2020
  Time: 10:47
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
    <h2>Dodaj składniki do swojego przepisu!</h2>
    <p>${recipeAtt.title}</p>
    <p>${recipeAtt.servings}</p>
    <c:forEach items="${recipeAtt.products}" var="product">
        <p>- ${product.product.name} ${product.quantity} ${product.unit.name}
            <a href="/recipe/${recipeAtt.id}/delProduct/${product.id}"> usuń</a>
        </p>
    </c:forEach>

    <form:form method="post" modelAttribute="recipeProducts" action="/recipe/addProduct">
        <form:input path="recipe" type="hidden" value="${recipeAtt.id}"/>
        <table>
            <tr>
                <th>Ingredient</th>
                <th>Amount</th>
                <th>Unit</th>
            </tr>
            <tr>

                <td>
                    <form:select path="product" multiple="false">
                        <form:options items="${products}" itemLabel="name" itemValue="id"/>
                    </form:select>
                    <form:errors path="product" cssClass="error"/><br>
                </td>
                <td>
                    <form:input path="quantity"/>
                    <form:errors path="quantity" cssClass="error"/><br>
                </td>
                <td>
                    <form:select path="unit" multiple="false">
                        <form:options items="${units}" itemLabel="name" itemValue="id"/>
                    </form:select>
                    <form:errors path="unit" cssClass="error"/><br>
                </td>
            </tr>
        </table>
        <input type="submit" content="submit">
    </form:form>
    <br>
    <p>Przepis:</p>
    <p>${recipeAtt.content}</p>

</sec:authorize>


</body>
</html>
