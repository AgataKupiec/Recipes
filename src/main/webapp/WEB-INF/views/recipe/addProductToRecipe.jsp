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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
<%--    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>--%>
    <script src="<c:url value="/resources/vendors/jquery/dist/jquery.min.js" />"></script>
    <script type="text/javascript" src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

    <script type="text/javascript">// <![CDATA[
    function inputFocus(i){
        if(i.value==i.defaultValue){ i.value=""; i.style.color="#000"; }
    }
    function inputBlur(i){
        if(i.value==""){ i.value=i.defaultValue; i.style.color="#848484"; }
    }
    // ]]></script>
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
                    <div class="ui-widget">
                        <form:input path="product" id="autocomplete-custom-append" type="text" name="tagQuery" onFocus="inputFocus(this)" onBlur="inputBlur(this)"/>
                    </div>

<%--                    <form:select path="product" multiple="false">--%>
<%--                        <form:options items="${products}" itemLabel="name" itemValue="id"/>--%>
<%--                    </form:select>--%>
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
<script src="<c:url value="/resources/js/custom.js" />"></script>

</body>
</html>
