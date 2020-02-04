<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Gentelella Alela! | </title>

    <!-- Bootstrap -->
    <link href="../gentelella/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="../gentelella/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="../gentelella/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="../gentelella/vendors/iCheck/skins/flat/green.css" rel="stylesheet">

    <!-- bootstrap-progressbar -->
    <link href="../gentelella/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- JQVMap -->
    <link href="../gentelella/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>
    <!-- bootstrap-daterangepicker -->
    <link href="../gentelella/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="../gentelella/build/css/custom.min.css" rel="stylesheet">
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="index.html" class="site_title"><i class="fa fa-cutlery"></i> <span>Twoje przepisy!</span></a>
                </div>

                <div class="clearfix"></div>
                <br/>
                <!-- sidebar menu -->
                <jsp:include page="sidebar.jsp"></jsp:include>
                <!-- sidebar menu -->
            </div>
        </div>

        <!-- top navigation -->
        <jsp:include page="topbar.jsp"></jsp:include>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <!-- top tiles -->

            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">
                    <jsp:include page="project_detail.jsp"></jsp:include>
                </div>

            </div>
            <br/>

            <div class="row">


            </div>


            <div class="row">
                <div class="col-md-4 col-sm-4 col-xs-12">

                </div>
            </div>


            <div class="col-md-8 col-sm-8 col-xs-12">


            </div>
            <div class="row">


            </div>
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
<script src="../gentelella/vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="../gentelella/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="../gentelella/vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="../gentelella/vendors/nprogress/nprogress.js"></script>
<!-- Chart.js -->
<script src="../gentelella/vendors/Chart.js/dist/Chart.min.js"></script>
<!-- gauge.js -->
<script src="../gentelella/vendors/gauge.js/dist/gauge.min.js"></script>
<!-- bootstrap-progressbar -->
<script src="../gentelella/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<!-- iCheck -->
<script src="../gentelella/vendors/iCheck/icheck.min.js"></script>
<!-- Skycons -->
<script src="../gentelella/vendors/skycons/skycons.js"></script>
<!-- Flot -->
<script src="../gentelella/vendors/Flot/jquery.flot.js"></script>
<script src="../gentelella/vendors/Flot/jquery.flot.pie.js"></script>
<script src="../gentelella/vendors/Flot/jquery.flot.time.js"></script>
<script src="../gentelella/vendors/Flot/jquery.flot.stack.js"></script>
<script src="../gentelella/vendors/Flot/jquery.flot.resize.js"></script>
<!-- Flot plugins -->
<script src="../gentelella/vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
<script src="../gentelella/vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
<script src="../gentelella/vendors/flot.curvedlines/curvedLines.js"></script>
<!-- DateJS -->
<script src="../gentelella/vendors/DateJS/build/date.js"></script>
<!-- JQVMap -->
<script src="../gentelella/vendors/jqvmap/dist/jquery.vmap.js"></script>
<script src="../gentelella/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
<script src="../gentelella/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
<!-- bootstrap-daterangepicker -->
<script src="../gentelella/vendors/moment/min/moment.min.js"></script>
<script src="../gentelella/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

<!-- Custom Theme Scripts -->
<script src="../gentelella/build/js/custom.min.js"></script>

</body>
</html>
