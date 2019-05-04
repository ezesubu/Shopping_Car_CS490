<%--Created by Ezequiel Suarez Buitrago
Date April 20, 2019
--%>
<%@include file="/WEB-INF/include.jsp"%>
/**
* Created by Ezequiel Suarez Buitrago, Thomas Tibebu,
* Innocent Kateba, shuling he, Wenxin He, Tram Ly
* Date April 20, 2019
**/
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/template/header.jsp"%>



<!-- Products section -->
        <!-- start prduct navigation -->

            <c:set var="counter" value="0" scope="page" />
            <c:forEach items="${mainCategoryNameList}" var="category">
                <c:set var="counter" value="${counter + 1}" scope="page"/>
                <c:if test="${counter eq 1}">
                    <li class="active"><a href="#${category}" data-toggle="tab">${category}</a></li>
                </c:if>
                <c:if test="${counter ne 1}">
                    <li><a href="#${category}" data-toggle="tab">${category}</a></li>
                </c:if>
            </c:forEach>


        <!-- / start prduct navigation -->



<!-- / Products section -->






<%@include file="/WEB-INF/jsp/template/footer.jsp"%>
