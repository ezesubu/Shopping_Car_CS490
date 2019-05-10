<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/include.jsp"%>

<%--
  ~ Created by: Ezesubu
  ~ Date: April 20, 2019
  ~ Name: list.jsp
  ~ DependingIn:  Boostrap3.6.->
  ~ Description: UI Template
  ~ Module: UI Template
  --%>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Name</th>
        <th>Parent Category</th>
        <th>Status</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="row">
        <tr <c:if test="${row.status ne 'ENABLED'}">class="danger" </c:if>>
            <td>
                <a class="btn btn-primary btn-xs" href="#parentId" onclick="module.childList('${row.id}')">
                    <i>${row.name}</i>
                </a>
            </td>
            <td>
                <c:if test="${row.parentCategory ne null}"><i>${row.parentCategory.name}</i></c:if>
            </td>
            <td>${row.status}</td>
            <td>
                    <%--<a href="#" type="button" onclick="module.delete('${row.id}')">
                        <i class="glyphicon glyphicon-info-sign"></i>
                    </a>--%>
                <a href="#create" onclick="module.create('${row.id}')">
                    <i class="glyphicon glyphicon-pencil"></i>
                </a>
                <c:if test="${row.status eq 'ENABLED'}">
                    <a href="#delete" type="button" onclick="module.delete('${row.id}')">
                        <i class="fa fa-times"></i>
                    </a>
                </c:if>
                <c:if test="${row.status ne 'ENABLED'}">
                    <a href="#changeStatus" type="button" onclick="module.changeStatus('${row.id}', 'ENABLED')">
                        <i class="fa fa-check"></i>
                    </a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
