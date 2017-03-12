<%-- Navigation menu for portal. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%@ attribute name="homePageActive" required="false" type="java.lang.Boolean" %>
<%@ attribute name="taskPageActive" required="false" type="java.lang.Boolean" %>

<c:set var="homePageName" value="Home"/>
<c:set var="taskPageName" value="Tasks"/>

<c:set var="homePageHref" value="${contextPath}/"/>
<c:set var="taskPageHref" value="${contextPath}/task"/>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <a class="navbar-brand" href="#">Contest</a>
        </div>
        <ul class="nav navbar-nav">
            <t:link name="${homePageName}" active="${homePageActive}" href="${homePageHref}"/>
            <t:link name="${taskPageName}" active="${taskPageActive}" href="${taskPageHref}"/>
        </ul>
    </div><!-- /.container-fluid -->
</nav>
