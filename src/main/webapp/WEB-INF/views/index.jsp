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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Gentelella Alela! | </title>
    <!-- Custom fonts for this template-->

    <!-- Bootstrap -->
    <link href="<c:url value ="/resources/vendors/bootstrap/dist/css/bootstrap.min.css" />" rel="stylesheet"
          type="text/css">
    <!-- Font Awesome -->
    <link href="<c:url value ="/resources/vendors/font-awesome/css/font-awesome.min.css" />" rel="stylesheet"
          type="text/css">
    <!-- NProgress -->
    <link href="<c:url value ="/resources/vendors/nprogress/nprogress.css" />" rel="stylesheet" type="text/css">
    <!-- iCheck -->
    <link href="<c:url value ="/resources/vendors/iCheck/skins/flat/green.css" />" rel="stylesheet" type="text/css">

    <!-- bootstrap-progressbar -->
    <link href="<c:url value ="/resources/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" />"
          rel="stylesheet" type="text/css">
    <!-- JQVMap -->
    <link href="<c:url value ="/resources/vendors/jqvmap/dist/jqvmap.min.css" />" rel="stylesheet" type="text/css"/>
    <!-- bootstrap-daterangepicker -->
    <link href="<c:url value ="/resources/vendors/bootstrap-daterangepicker/daterangepicker.css" />" rel="stylesheet"
          type="text/css">

    <!-- Custom Theme Style -->
    <link href="<c:url value ="/resources/css/custom.min.css" />" rel="stylesheet" type="text/css">

</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title"><i class="fa fa-cutlery"></i>
                        <span>Przepisy Agaty!</span></a>
                </div>

                <div class="clearfix"></div>
                <br/>
                <!-- sidebar menu -->
                <%--                <jsp:include page="sidebar.jsp"></jsp:include>--%>
                <%@include file="sidebar.jsp" %>
                <!-- sidebar menu -->
            </div>
        </div>

        <!-- top navigation -->
        <%--        <jsp:include page="topbar.jsp"></jsp:include>--%>
        <%@include file="topbar.jsp" %>
        <!-- /top navigation -->

        <div class="right_col" role="main">
        <!-- page content -->
        <div role="main">
            <!-- top tiles -->

            <div class="row">
                <%--                <div class="col-md-12 col-sm-12 col-xs-12">--%>
                <%--                    <jsp:include page="project_detail.jsp"></jsp:include>--%>
                <%--                </div>--%>
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <%@include file="media_gallery.jsp" %>
                </div>
            </div>
            <br/>

<%--            <div class="row">--%>


<%--            </div>--%>


<%--            <div class="row">--%>
<%--                <div class="col-md-4 col-sm-4 col-xs-12">--%>

<%--                </div>--%>
<%--            </div>--%>


<%--            <div class="col-md-8 col-sm-8 col-xs-12">--%>


<%--            </div>--%>
<%--            <div class="row">--%>


<%--            </div>--%>
        </div>
    </div>
</div>
<!-- /page content -->

<!-- footer content -->
<footer>
    <div class="pull-right">
        Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
    </div>
    <div class="clearfix"></div>
</footer>
<!-- /footer content -->
</div>
</div>


<!-- jQuery -->
<script src="<c:url value="/resources/vendors/jquery/dist/jquery.min.js" />"></script>
<!-- Bootstrap -->
<script src="<c:url value="/resources/vendors/bootstrap/dist/js/bootstrap.min.js" />"></script>
<!-- FastClick -->
<script src="<c:url value="/resources/vendors/fastclick/lib/fastclick.js" />"></script>
<!-- NProgress -->
<script src="<c:url value="/resources/vendors/nprogress/nprogress.js" />"></script>
<!-- Chart.js -->
<script src="<c:url value="/resources/vendors/Chart.js/dist/Chart.min.js" />"></script>
<!-- gauge.js -->
<script src="<c:url value="/resources/vendors/gauge.js/dist/gauge.min.js" />"></script>
<!-- bootstrap-progressbar -->
<script src="<c:url value="/resources/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js" />"></script>
<!-- iCheck -->
<script src="<c:url value="/resources/vendors/iCheck/icheck.min.js" />"></script>
<!-- Skycons -->
<script src="<c:url value="/resources/vendors/skycons/skycons.js" />"></script>
<!-- Flot -->
<script src="<c:url value="/resources/vendors/Flot/jquery.flot.js" />"></script>
<script src="<c:url value="/resources/vendors/Flot/jquery.flot.pie.js" />"></script>
<script src="<c:url value="/resources/vendors/Flot/jquery.flot.time.js" />"></script>
<script src="<c:url value="/resources/vendors/Flot/jquery.flot.stack.js" />"></script>
<script src="<c:url value="/resources/vendors/Flot/jquery.flot.resize.js" />"></script>
<!-- Flot plugins -->
<script src="<c:url value="/resources/vendors/flot.orderbars/js/jquery.flot.orderBars.js" />"></script>
<script src="<c:url value="/resources/vendors/flot-spline/js/jquery.flot.spline.min.js" />"></script>
<script src="<c:url value="/resources/vendors/flot.curvedlines/curvedLines.js" />"></script>
<!-- DateJS -->
<script src="<c:url value="/resources/vendors/DateJS/build/date.js" />"></script>
<!-- JQVMap -->
<script src="<c:url value="/resources/vendors/jqvmap/dist/jquery.vmap.js" />"></script>
<script src="<c:url value="/resources/vendors/jqvmap/dist/maps/jquery.vmap.world.js" />"></script>
<script src="<c:url value="/resources/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js" />"></script>
<!-- bootstrap-daterangepicker -->
<script src="<c:url value="/resources/vendors/moment/min/moment.min.js" />"></script>
<script src="<c:url value="/resources/vendors/bootstrap-daterangepicker/daterangepicker.js" />"></script>

<!-- Custom Theme Scripts -->
<script src="<c:url value="/resources/js/custom.min.js" />"></script>

</body>
</html>
