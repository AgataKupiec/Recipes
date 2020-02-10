<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                            <li><a href="/chef/profile"> Profil</a></li>
                            <li><a href="javascript:;">Ustawienia</a></li>
                            <li>
                                <form action="<c:url value="/logout"/>" method="post">
                                    <i class="fa fa-sign-out pull-right"></i>
                                    <input class="btn-link" type="submit" value="Wyloguj">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                </form>
                            </li>
                        </ul>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <i href="/resources/vendors/select2/docs/images/chefIcon.png"></i>
                        <a href="/login">Zaloguj</a>
                    </sec:authorize>

                </li>
            </ul>
        </nav>
    </div>
</div>