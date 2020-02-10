<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util" %>

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

                        <!-- page content -->
                        <div role="main">
                            <div class="">

                                <div class="clearfix"></div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="x_panel">
                                            <div class="x_title">
                                                <h2>Twoje przepisy <small>edycja i usuwanie</small></h2>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="x_content">
                                                <sec:authorize access="isAuthenticated()">
                                                <!-- start project list -->
                                                <table class="table table-striped projects">
                                                    <thead>
                                                    <tr>
                                                        <th style="width: 1%">#</th>
                                                        <th style="width: 15%">Zdjęcie</th>
                                                        <th style="width: 20%">Tytuł</th>
                                                        <th>Czas przygotowania</th>
                                                        <th>Vege/vegan</th>
                                                        <th style="width: 20%">Edycja</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${recipes.content}" var="recipe"
                                                               varStatus="status">

                                                        <tr>
                                                            <td><a>${status.count}</a></td>
                                                            <td>
                                                                <c:if test="${not empty recipe.image}">
                                                                    <a href="/recipe/${recipe.id}">
                                                                        <img style="width: 100%; display: block;"
                                                                             src="data:image/jpeg;base64,${recipe.imageBuff}"
                                                                             alt="brak"/>
                                                                    </a>
                                                                </c:if>
                                                                <c:if test="${empty recipe.image}">
                                                                    <img style="width: 100%; display: block;"
                                                                         src="/resources/img/default.jpg" alt="brak"/>
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <a href="/recipe/${recipe.id}">${recipe.title}</a>
                                                            </td>
                                                            <td>
                                                                <a>${recipe.time} min</a>
                                                            </td>
                                                            <td>
                                                                <c:if test="${recipe.vege}">
                                                                    <i class="fa fa-leaf fa-2x"
                                                                       style="color: darkgreen"></i>
                                                                </c:if>
                                                                <c:if test="${recipe.vegan}">
                                                                    <i class="fa fa-check-circle-o fa-2x"
                                                                       style="color: #70AD47"></i>
                                                                </c:if>
                                                            </td>
                                                            <td>
                                                                <a href="/recipe/${recipe.id}"
                                                                   class="btn btn-primary btn-xs"><i
                                                                        class="fa fa-folder"></i> Pokaż </a>
                                                                <a href="/recipe/edit/${recipe.id}"
                                                                   class="btn btn-info btn-xs"><i
                                                                        class="fa fa-pencil"></i> Edytuj </a>
                                                                <a href="/recipe/details/${recipe.id}"
                                                                   class="btn btn-info btn-xs"><i
                                                                        class="fa fa-pencil"></i> Dodaj usuń składniki </a>
                                                                <a href="/recipe/delete/${recipe.id}"
                                                                   class="btn btn-danger btn-xs confirm"><i
                                                                        class="fa fa-trash-o"></i> Usuń </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>

                                                    </tbody>
                                                </table>
                                            </div>
                                            <util:pagination thispage="${recipes}"></util:pagination>
                                            </sec:authorize>
                                            <sec:authorize access="!isAuthenticated()">
                                                <div class="x_title">
                                                    <h2>Aby przeglądać swoje przepisy, musisz być zalogowany</h2>
                                                </div>
                                            </sec:authorize>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /page content -->
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
