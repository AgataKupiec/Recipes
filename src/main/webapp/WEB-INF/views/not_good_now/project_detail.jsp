<div role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Przeglądaj przepisy</h3>
            </div>

            <div class="title_right">
                <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search for...">
                        <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                    </div>
                </div>
            </div>
        </div>

        <div class="clearfix"></div>

        <div class="row">
            <div class="col-md-12">
                <div class="x_panel">

                    <div class="x_content">

                        <div class="col-md-6 col-sm-9 col-xs-12">
                            <div id="mainb" style="height:350px;"></div>
                            <div>
                                <ul class="messages">
                                    <c:forEach items="${recipes}" var="recipe">
                                        <li>
                                            <c:if test="${not empty recipe.image}">
                                                <img src="data:image/jpeg;base64,${recipe.imageBuff}" class="recipe"
                                                     alt="">
                                            </c:if>

                                            <div class="message_wrapper">
                                                <h4 class="heading">${recipe.title}</h4>
                                                    <%--                                <blockquote class="message"></blockquote>--%>
                                                <br/>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>
                                <!-- end of user messages -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
