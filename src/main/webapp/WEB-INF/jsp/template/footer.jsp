<%--Created by Ezequiel Suarez Buitrago
Date April 20, 2019
--%>
<%@include file="/WEB-INF/include.jsp"%>

<!-- footer -->
<footer id="aa-footer">

    <!-- footer-bottom -->
    <div class="aa-footer-bottom">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="aa-footer-bottom-area">
                        <p>PM Store </p>
                        <div class="aa-footer-payment">
                            MUM 2019
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- / footer -->

<div id="edit-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div id="edit-target"></div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- jQuery library -->
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<%--Form--%>
<script src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
<script src="${pageContext.request.contextPath}/static/js/smoke/smoke.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/sticky/sticky.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.simpleLens.js"></script>
<script src="${pageContext.request.contextPath}/static/js/sequence.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<!-- SmartMenus jQuery plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.smartmenus.js"></script>
<!-- SmartMenus jQuery Bootstrap Addon -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.smartmenus.bootstrap.js" ></script>
<!-- To Slider JS -->
<script src="${pageContext.request.contextPath}/static/js/jquery.simpleGallery.js" ></script>
<script src="${pageContext.request.contextPath}/static/js/sequence-theme.modern-slide-in.js"  ></script>
<!-- prduct view slider -->
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.simpleGallery.js"   ></script>
<!-- slick slider -->
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/slick.js"  ></script>
<!-- Price picker slider -->
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/nouislider.min.js" ></script>
<!-- Custom js -->
<script src="${pageContext.request.contextPath}/static/js/custom.js" ></script>
<script  src="${pageContext.request.contextPath}/static/js/controller.js" ></script>
<!--payment js-->
<script src="${pageContext.request.contextPath}/static/paymentdetail/js/jquery.payform.min.js" charset="utf-8"></script>
<!--cart for header js-->
<script>
    var contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/static/js/order/cart.js"></script>

</body>
</html>