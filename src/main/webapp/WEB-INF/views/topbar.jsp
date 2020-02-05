<div class="top_nav">
    <div class="nav_menu">
        <nav>
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <sec:authorize access="isAuthenticated()">
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown"
                       aria-expanded="false">
                        <i href="/resources/vendors/select2/docs/images/chefIcon.png"></i>
<%--                        <img src="/resources/vendors/select2/docs/images/chefIcon.png" alt="">--%>
                        <sec:authentication property="principal.username"/>
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li><a href="javascript:;"> Profile</a></li>
                        <li><a href="javascript:;">Settings</a></li>
                        <li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                    </ul>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
<%--                        <img src="/resources/vendors/select2/docs/images/chefIcon.png" alt="">--%>
                        <i href="/resources/vendors/select2/docs/images/chefIcon.png"></i>
                        <a href="/login">Zaloguj</a>
                    </sec:authorize>

                </li>
            </ul>
        </nav>
    </div>
</div>