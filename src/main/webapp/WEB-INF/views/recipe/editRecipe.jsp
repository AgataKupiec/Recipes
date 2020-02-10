<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gentelella Alela! | </title>
    <!-- Custom fonts for this template-->
    <!-- Bootstrap -->
    <%@include file="../styles.jsp" %>
    <script src="https://cdn.ckeditor.com/ckeditor5/16.0.0/classic/ckeditor.js"></script>



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
                            <div class="">
                                <div class="page-title">
                                    <div class="title_left">
                                        <h3>Dodaj swój przepis</h3>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="row">
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="x_panel">
                                            <div class="x_content">
                                                <br/>
                                                <sec:authorize access="isAuthenticated()">

                                                    <div class="col-md-3 col-sm-3 col-xs-12 profile_left">
                                                        <div class="profile_img">
                                                            <div id="crop-avatar">
                                                                <!-- Current avatar -->
                                                                <c:if test="${not empty recipe.image}">
                                                                    <img class="img-responsive avatar-view"
                                                                         src="data:image/jpeg;base64,${recipe.imageBuff}" alt="image"/>
                                                                </c:if>
                                                                <c:if test="${empty recipe.image}">
                                                                    <img class="img-responsive avatar-view"
                                                                         src="/resources/img/default.jpg" alt="image"/>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                        <h3>Składniki:</h3>

                                                        <ul class="list-unstyled user_data">
                                                            <c:forEach items="${recipe.products}" var="product">
                                                                <li><i class="fa fa-check user-profile-icon"></i> ${product.product.name} ${product.quantity} ${product.unit.name}
                                                                </li>
                                                            </c:forEach>
                                                        </ul>

                                                    </div>
                                                    <div class="col-md-9 col-sm-9 col-xs-12">
                                                    <form:form method="post" modelAttribute="recipe" action="/recipe/edit"
                                                               enctype="multipart/form-data"
                                                               id="demo-form2" class="form-horizontal form-label-left">
                                                        <div class="form-group">



                                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">Tytuł <span
                                                                    class="required"></span>
                                                            </label>
                                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                                <form:input path="title" type="text" id="title" required="required"
                                                                            class="form-control col-md-7 col-xs-12" value="${recipe.title}"/>
                                                                <form:errors path="title" cssClass="error"/><br>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-3">Liczba porcji </label>
                                                            <div class="col-md-6 col-sm-6 col-xs-9">
                                                                <div>
                                                                    <form:input path="servings" type="number" value="${recipe.servings}"
                                                                                min="1" max="100" step="1"/>
                                                                    <form:errors path="servings" cssClass="error"/><br>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-3">Czas przygotowania </label>
                                                            <div class="col-md-6 col-sm-6 col-xs-9">
                                                                <div>
                                                                    <form:input path="time" type="number" value="${recipe.time}"
                                                                                min="0" max="1000" step="5"/>
                                                                        <%--                                        <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>--%>
                                                                    <form:errors path="time" cssClass="error"/><br>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">Podmień zdjęcie:
                                                            </label>
                                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                                <input type="file" name="imageInput"/>
                                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                            </div>
                                                        </div>

                                                        <div class="x_title">
                                                            <h2>Przepis</h2>
                                                            <div class="clearfix"></div>
                                                        </div>
                                                        <div class="btn-toolbar editor" data-role="editor-toolbar"
                                                             data-target="#editor-one">
                                                            <div id="editor-one" class="editor-wrapper">
                                                                <form:textarea path="content" id="editor" name="content"
                                                                               class="form-control" value="${recipe.content}"/>
                                                                <form:errors path="content" cssClass="error"/><br>
                                                            </div>
                                                        </div>

                                                        <form:input path="id" type="hidden" value="${recipe.id}"/>
                                                        <form:input path="author" type="hidden" value="${recipe.author.id}"/>
                                                        <button type="submit" class="btn btn-primary">Edytuj przepis</button>

                                                    </form:form>
                                                    </div>

                                                </sec:authorize>
                                                <sec:authorize access="!isAuthenticated()">
                                                    <div class="x_title">
                                                        <h2>Aby edytować przepis, musisz być zalogowany</h2>
                                                    </div>
                                                </sec:authorize>
                                            </div>
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
<script>
    ClassicEditor
        .create( document.querySelector('#editor'))
        .catch( error => {
        console.error( error );
    });
</script>
<%--<script src="<c:url value="/resources/js/jquery/bootstrap-input-spinner.js" />"></script>--%>
<%--<script>--%>
<%--    $("input[type='number']").inputSpinner()--%>
<%--</script>--%>

</body>
</html>
