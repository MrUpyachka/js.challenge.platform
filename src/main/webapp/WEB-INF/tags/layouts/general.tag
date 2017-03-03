<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@tag description="General page template" pageEncoding="UTF-8" %>

<%@attribute name="topBar" fragment="true" %>
<%@attribute name="bottomBar" fragment="true" %>
<%@attribute name="miscHead" fragment="true" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="pageTitle" value="JS contest"/>

<html>
    <head>
        <title>"${pageTitle}"</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <!--
            <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
            <link href="./resources/css/bootstrap-theme.min.css" rel="stylesheet">
            <script src="./resources/js/bootstrap.min.js"/></script>
        -->

        <link href="./resources/css/general.css" rel="stylesheet">
        <jsp:invoke fragment="miscHead"/>
    </head>

    <body>
        <div class="container">
            <div class="top-bar">
              <jsp:invoke fragment="topBar"/>
            </div>
            <div class="content">
              <jsp:doBody/>
            </div>
            <div class="bottom-bar">
              <jsp:invoke fragment="bottomBar"/>
            </div>
        </div>
    </body>
</html>