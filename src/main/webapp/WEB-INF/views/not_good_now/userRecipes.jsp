<%--
  Created by IntelliJ IDEA.
  User: agata
  Date: 03.02.2020
  Time: 11:24
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
<p>Twoje przepisy:</p>

<c:forEach items="${recipes}" var="recipe">
<c:if test="${not empty recipe.image}">
    <img src="data:image/jpeg;base64,${recipe.imageBuff}" alt="">
</c:if>
<p>${recipe.title}</p>
<p>${recipe.servings}</p>
<c:forEach items="${recipe.products}" var="product">
    <p>- ${product.product.name} ${product.quantity} ${product.unit.name}</p>
</c:forEach>

<p>Przepis:</p>
<p>${recipe.content}</p>
    <br>
    <a href="javascript:document.location.href='/recipe/details/${recipe.id}'">Dodaj składnik</a>
<%--    <button type="button" onClick="javascript:document.location.href='/recipe/delete/${recipe.id}'">Usuń</button><br>--%>
</c:forEach>




</body>
</html>
