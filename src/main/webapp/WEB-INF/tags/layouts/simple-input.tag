<%-- Defines template for simple input field of specified type with validation and data mapping. --%>
<%-- NOTE: Requires bootstrap. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@attribute name="id" required="true" %><%-- ID of page element. --%>
<%@attribute name="path" required="true" %><%-- Data-mapping path. --%>
<%@attribute name="label" required="true" %><%-- Label for input field. --%>
<%@attribute name="type" required="true" %><%-- Type of data for input field. --%>
<%@ attribute name="enabled" required="false" type="java.lang.Boolean" %>

<c:choose>
    <c:when test="${enabled != null && !enabled}">
        <c:set var="disabledKey" value="disabled"/>
    </c:when>
    <c:otherwise>
        <c:set var="disabledKey" value=""/>
    </c:otherwise>
</c:choose>

<div class="form-group input-group">
    <label for="${id}">${label}</label>
    <form:input id="${id}" path="${path}" type="${type}" class="form-control" disabled="${!enabled}"/>
    <form:errors path="${path}"/>
</div>

