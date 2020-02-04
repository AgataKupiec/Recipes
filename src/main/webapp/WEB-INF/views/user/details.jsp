<%--
  Created by IntelliJ IDEA.
  User: agata
  Date: 03.02.2020
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<sec:authorize access="isAuthenticated()">

    Your e-mail: ${user.email}
    <c:if test="${user.vege}">
        <br>Vegetarian
    </c:if>
    <c:if test="${user.vegan}">
        <br>Vegan
    </c:if>
    <br>
    You don't like:<br>
    <c:forEach items="${user.eliminatedProducts}" var="product">
        <p style="color: darkred">
                ${product.name}
                    <button type="button"
                            onClick="document.location.href='/chef/eliminateProduct/remove/${product.id}'">-
                    </button>
        </p>
    </c:forEach>
        Add a product you don't like:
        <form method="get" action="/chef/eliminateProduct/add">
            <select name="productId">
                <c:forEach items="${products}" var="product">
                    <option value="${product.id}">${product.name}</option>
                </c:forEach>
            </select>
            <input type="submit">
        </form>

<%--        <form:form method="post" modelAttribute="product" action="/chef/eliminateProduct/add">--%>
<%--            <form:select path="id" multiple="false">--%>
<%--                <form:options items="${products}" itemLabel="name" itemValue="id"/>--%>
<%--            </form:select>--%>
<%--            <input type="submit"><br>--%>
<%--        </form:form>--%>

    <br>
    <br>

    You always have:<br>
    <c:forEach items="${user.availableProducts}" var="product">
        <p style="color: darkgreen">
                ${product.name}
            <button type="button"
                    onClick="document.location.href='/chef/availableProduct/remove/${product.id}'">-
            </button>
        </p>
    </c:forEach>
    Add a product you always have:
    <form method="get" action="/chef/availableProduct/add">
        <select name="productId">
            <c:forEach items="${products}" var="product">
                <option value="${product.id}">${product.name}</option>
            </c:forEach>
        </select>
        <input type="submit">
    </form>
<%--    <button type="button" onClick="document.location.href='/chef/eliminateProduct/add/${product.id}'">Add a product you don't like</button>--%>
<%--    <button type="button" onClick="document.location.href='/chef/availableProduct/add/${product.id}'">Add a product you always have</button>--%>

</sec:authorize>


</body>
<head>
    <title>Title</title>
</head>
</html>
