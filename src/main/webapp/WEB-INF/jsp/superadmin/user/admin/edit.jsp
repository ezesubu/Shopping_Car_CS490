<%--Created by Ezequiel Suarez Buitrago
Date April 20, 2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>

<script>
    create = {
        init: function() {
            $('#adminForm').ajaxForm({
                target:'#edit-target',
                url:'${pageContext.request.contextPath}/admin/user/admin/edit'
            });
        },
        success: function() {
            $('#edit-modal').modal('hide');
            module.list();
        },
        submit: function() {
            $('#adminForm').submit();
        }
    };
</script>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title">Edit <i>${adminForm.username}</i> Admin</h4>
</div>
<div class="modal-body">
    <form:form modelAttribute="adminForm" method="post">
        <form:hidden path="id"/>
        <div class="form-group">
            <form:errors path="firstName" cssStyle="color: red" />
            <label for="firstName">firstName</label>
            <form:input path="firstName" class="form-Control" />
        </div>
        <div class="form-group">
            <form:errors path="lastName" cssStyle="color: red" />
            <label for="lastName">lastName</label>
            <form:input path="lastName" class="form-Control" />
        </div>
        <div class="form-group">
            <form:errors path="username" cssStyle="color: red" />
            <label for="username">username</label>
            <form:input path="username" class="form-Control" />
        </div>
        <div class="form-group">
            <form:errors path="email" cssStyle="color: red" />
            <label for="email">email</label>
            <form:input path="email" type="email" class="form-Control" />
        </div>
    </form:form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    <button type="button" class="btn btn-primary" onclick="create.submit()">Save changes</button>
</div>

<script type="text/javascript">
    $(function () {
        create.init();
    });
</script>

<c:if test="${!empty message}">
    <script>
        $.sticky('<spring:message code="${message.message}"/>', {autoclose : 5000, position: "top-right", type: "st-${fn:toLowerCase(message.type)}" });

        <c:if test="${message.type eq 'SUCCESS'}">
        create.success();
        </c:if>
    </script>
</c:if>