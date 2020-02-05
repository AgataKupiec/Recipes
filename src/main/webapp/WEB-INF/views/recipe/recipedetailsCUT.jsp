<!-- page content -->
        <div role="main">
            <div class="page-title">
              <div class="title_left">
                <h3>${recipe.title}</h3>
              </div>
            </div>

            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_content">
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

                      <!-- start skills -->

                      <!-- end of skills -->

                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-12">
                      ${recipe.content}
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
        <!-- /page content -->
