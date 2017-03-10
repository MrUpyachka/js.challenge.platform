<%-- Selector for task on details view. --%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<%@ attribute name="tasksList" required="true" type="java.util.List" %>
<%@ attribute name="path" required="true" %>

<t:task-selector-script/>
<form:select id="task-selector" path="${path}" class="form-control" onchange="onTaskChanged()">
    <option value="-1">Add new...</option>
    <form:options items="${tasksList}" itemLabel="name" itemValue="id"/>
</form:select>
