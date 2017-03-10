<%-- Selector for task as an reference. --%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<%@ attribute name="tasksList" required="true" type="java.util.List" %>
<%@ attribute name="name" required="true" %>

<t:task-selector-script/>
<select id="task-selector" name="${name}" class="form-control" onchange="onTaskChanged()">
    <c:forEach var="item" items="${tasksList}">
        <c:if test="${addNewAllowed}">
           <option value="-1">Add new...</option>
        </c:if>
        <c:choose>
            <c:when test="${item.getId().equals(sessionScope.taskId)}">
               <c:set var="selectedFlag" value="selected"/>
            </c:when>
            <c:otherwise>
                <c:set var="selectedFlag" value=""/>
            </c:otherwise>
        </c:choose>
        <option value="${item.getId()}" ${selectedFlag}>${item.getName()}</option>
    </c:forEach>
</select>
