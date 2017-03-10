<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@attribute name="actionHeader" required="true" %>
<%@attribute name="actionName" required="true" %>
<%@attribute name="actionButton" required="true" %>
<%@attribute name="alternativeAction" fragment="true" %>

<t:general>
    <jsp:attribute name="topBar">
        <h2>Welcome to JS contest!</h2>
    </jsp:attribute>
    <jsp:attribute name="bottomBar"><jsp:invoke fragment="alternativeAction"/></jsp:attribute>
    <jsp:body>
        <form:form method="POST" modelAttribute="USER" class="user-form ${actionName}">
            <h4 class="action-header">${actionHeader}</h4>
            <div class="test">
                <c:forEach items="${status.errorMessages}" var="error">
                    Error code: <c:out value="${error}"/><br>
                </c:forEach>
            </div>
            <div class="test"><form:errors path="*"/></div>
            <spring:bind path="username">
                <div class="form-group input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <form:input id="username-input" type="text" path="username" class="form-control" placeholder="Username" autofocus="true"
                     cssErrorClass="has-error"/>
                    <form:errors path="username"/>
                </div>
            </spring:bind>
            <spring:bind path="password">
                <div class="form-group input-group">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <form:input id="password-input" type="password" path="password" class="form-control" placeholder="Password"
                     cssErrorClass="has-error"/>
                    <form:errors path="password"/>
                </div>
            </spring:bind>
            <button class="btn btn-md btn-primary btn-block ${actionName}" type="submit">${actionButton}</button>
        </form:form>
    </jsp:body>
</t:general>
