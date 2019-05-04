<%--Created by Ezequiel Suarez Buitrago
Date April 20, 2019
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<c:set var="signedUser" value="<%=edu.mum.cs490.shoppingcart.utility.SignedUserUtility.getSignedUser()%>"/>
<spring:eval var="resourcePath" expression="@environment.getProperty('resource.path')" scope="application"/>
