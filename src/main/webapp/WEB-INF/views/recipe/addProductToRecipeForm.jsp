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

    <script>
        $(function() {
            $("#searchBox").autocomplete({
                source : function(request, response) {
                    $.ajax({
                        url : "http://localhost:8080/getProducts",
                        dataType : "json",
                        data : {
                            q : request.term
                        },
                        success : function(data) {
                            //alert(data);
                            console.log(data);
                            response(data);
                        }
                    });
                },
                minLength : 2
            });
        });
    </script>
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

                        <%@include file="formAddProduct.jsp" %>

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

<script src="./src/bootstrap-input-spinner.js"></script>
<script>
    $("input[type='number']").inputSpinner()
</script>
</body>
</html>
