<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Przepisy Agaty</title>
    <!-- Custom fonts for this template-->
    <!-- Bootstrap -->
    <%@include file="../styles.jsp" %>
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="/" class="site_title"><i class="fa fa-cutlery"></i>
                        <span>Przepisy Agaty!</span></a>
                </div>

                <div class="clearfix"></div>
                <br/>
                <%@include file="../sidebar.jsp" %>
            </div>
        </div>
        <%@include file="../topbar.jsp" %>
        <div class="right_col" role="main">
            <!-- page content -->
            <div role="main">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">

                        <div role="main">
                            <div class="page-title">
                                <div class="title_left">
                                    <h3>${recipe.title}</h3>
                                </div>
                            </div>

                            <div class="clearfix"></div>

                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="x_panel">
                                        <div class="x_content">
                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                <c:forEach items="${recipe.products}" var="product">
                                                    <p>
                                                        <a class="btn btn-round btn-default btn-small font-italic"
                                                           href="/recipe/${recipe.id}/delProduct/${product.id}">usuń</a>
                                                        <a class="font-weight-bold"> - ${product.product.name} ${product.quantity} ${product.unit.name}</a>
                                                    </p>
                                                </c:forEach>
                                                </br>
                                                <form:form method="post" modelAttribute="recipeProducts"
                                                           action="/recipe/addProduct">
                                                    <form:input path="recipe" type="hidden" value="${recipe.id}"/>
                                                    <div class="row">
                                                        <div class="col-md-2 col-sm-4 col-xs-12">
                                                            <a>Składnik</a>
                                                        </div>
                                                        <div class="col-md-2 col-sm-2 col-xs-4">
                                                            <a>Ilość</a>
                                                        </div>
                                                        <div class="col-md-2 col-sm-4 col-xs-8">
                                                            <a>Jednostka</a>
                                                        </div>
                                                        <div class="col-md-2 col-sm-2 col-xs-4">

                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-2 col-sm-4 col-xs-12">
                                                            <form:select path="product" multiple="false" class="form-control">
                                                                <form:options items="${products}" itemLabel="name"
                                                                              itemValue="id"/>
                                                            </form:select>
                                                        </div>
                                                        <div class="col-md-2 col-sm-2 col-xs-4">
                                                            <form:input path="quantity" type="number" class="form-control"/>
                                                            <form:errors path="quantity" cssClass="error"/><br>
                                                        </div>
                                                        <div class="col-md-2 col-sm-4 col-xs-8">
                                                            <form:select path="unit" multiple="false" class="form-control">
                                                                <form:options items="${units}" itemLabel="name"
                                                                              itemValue="id"/>
                                                            </form:select>
                                                        </div>
                                                        <div class="col-md-2 col-sm-2 col-xs-4">
                                                            <input type="submit" class="btn btn-round btn-success" value="Dodaj">
                                                        </div>
                                                    </div>
                                                </form:form>
                                            </div>

                                            <div class="col-md-3 col-sm-3 col-xs-12 profile_left">
                                                <div class="profile_img">
                                                    <div id="crop-avatar">
                                                        <!-- Current avatar -->
                                                        <c:if test="${not empty recipe.image}">
                                                            <img class="img-responsive avatar-view"
                                                                 src="data:image/jpeg;base64,${recipe.imageBuff}"
                                                                 alt="image"/>
                                                        </c:if>
                                                        <c:if test="${empty recipe.image}">
                                                            <img class="img-responsive avatar-view"
                                                                 src="/resources/img/default.jpg" alt="image"/>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 col-sm-12 col-xs-12">
                                    <div class="x_panel">
                                        <div class="x_content">
                                            <div class="col-md-9 col-sm-9 col-xs-12">
                                                ${recipe.content}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                </div>
            </div>
        </div>
        <footer>
            <div class="pull-right">
                Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
            </div>
            <div class="clearfix"></div>
        </footer>
    </div>
    <%@include file="../scripts.jsp" %>
</body>
</html>
