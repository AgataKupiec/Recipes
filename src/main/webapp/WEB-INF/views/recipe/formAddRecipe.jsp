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
                            <form:form method="post" modelAttribute="recipe" action="/recipe/add"
                                       enctype="multipart/form-data"
                                       id="demo-form2" class="form-horizontal form-label-left">
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">Tytuł <span
                                            class="required"></span>
                                    </label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">
                                        <form:input path="title" type="text" id="title" required="required"
                                                    class="form-control col-md-7 col-xs-12"/>
                                        <form:errors path="title" cssClass="error"/><br>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Liczba porcji </label>
                                    <div class="col-md-6 col-sm-6 col-xs-9">
                                        <div>
                                            <form:input path="servings" type="number" value="1"
                                                        min="1" max="100" step="1"/>
                                            <form:errors path="servings" cssClass="error"/><br>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-3">Czas przygotowania </label>
                                    <div class="col-md-6 col-sm-6 col-xs-9">
                                        <div class="row">
                                            <form:input path="time" type="number" value="10"
                                                        min="0" max="1000" step="10"/>
                                                <%--                                        <span class="fa fa-user form-control-feedback right" aria-hidden="true"></span>--%>
                                            <form:errors path="time" cssClass="error"/><br>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">Zdjęcie
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
                                                       class="form-control"/>
                                        <form:errors path="content" cssClass="error"/><br>
                                    </div>
                                </div>


                                <button type="submit" class="btn btn-default">Dodaj przepis</button>

                            </form:form>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            <div class="x_title">
                                <h2>Aby dodać swój przepis, musisz być zalogowany</h2>
                            </div>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->
