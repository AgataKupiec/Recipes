<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- page content -->
<div role="main">
    <%--    <div class="col-md-12">--%>
    <div class="page-title">
        <div class="title_left">
            <h3> Lista przepisów <small> smacznego!</small></h3>
        </div>

        <div class="title_right">
            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                <form method="get" action="/recipe/find" id="searchForm">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search for...">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="button" form="searchForm">Go!</button>
                    </span>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="clearfix"></div>

    <div class="row">
        <div class="col-md-12">
            <div class="x_panel">
                <div class="x_content">
                    <div class="row">
                        <c:forEach items="${recipes.content}" var="recipe">
                            <div class="col-md-55">
                                <div class="thumbnail">
                                    <div class="image view view-first">
                                        <a href="/recipe/${recipe.id}">
                                            <c:if test="${not empty recipe.image}">
                                                <img style="width: 100%; display: block;"
                                                     src="data:image/jpeg;base64,${recipe.imageBuff}" alt="image"/>
                                            </c:if>
                                            <c:if test="${empty recipe.image}">
                                                <img style="width: 100%; display: block;"
                                                     src="/resources/img/default.jpg" alt="image"/>
                                            </c:if>
                                            <div class="mask">
                                                <p>Zjedz mnie</p>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="caption">
                                        <a href="/recipe/${recipe.id}">${recipe.title}</a>

                                    </div>
                                    <br>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
            </div>
            <util:pagination thispage="${recipes}"></util:pagination>
        </div>
    </div>
</div>
<%--    </div>--%>
</div>
<!-- /page content -->

