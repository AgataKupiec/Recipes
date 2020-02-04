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
<c:forEach items="${products}" var="product">
    ${product.id} ${product.name}<br>
</c:forEach>
<c:forEach items="${units}" var="unit">
    ${unit.id} ${unit.name}<br>
</c:forEach>
<br>
<sec:authorize access="isAuthenticated()">
    is authenticated
    <form>
    <table>
            <tr>
            <th>Ingredient</th>
            <th>Amount</th>
            <th>Unit</th>
            </tr>
            <tr>

                <td>
                    <select name="product" multiple="false">
                        <c:forEach items="${products}" var="product">
                            <option value="${product.id}" label="${product.name}"/>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input type="number" name="quantity">
                </td>
                <td>
                    <select name="unit" multiple="false">
                        <c:forEach items="${units}" var="unit">
                            <option value="${unit.id}" label="${unit.name}"/>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" content="submit">
    </form>
        <br>


</sec:authorize>
</body>
</html>
