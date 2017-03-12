<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page import="qq.upyachka.js.contest.core.roles.Roles" %>

<c:set var="editEnabled" value="false"/>
<sec:authorize access="hasAuthority('${Roles.TASK_CREATOR}')">
    <c:set var="editEnabled" value="true"/>
</sec:authorize>

<t:general>
    <jsp:attribute name="miscHead">
    </jsp:attribute>
    <jsp:attribute name="topBar">
        <t:nav-menu taskPageActive="true"/>
    </jsp:attribute>
    <jsp:body>
        <form method="POST" modelAttribute="task" class="task-form" action="/task">
            <t:task-selector tasksList="${tasksList}" path="TASK.id"/>
            <t:simple-input id="task-name-input" path="TASK.name" label="Task name:" type ="text"
                            enabled="${editEnabled}"/>
            <t:textarea-input id="task-description-input" path="TASK.description" label="Description:" type ="text"
                              enabled="${editEnabled}"/>
            <t:simple-input id="script-engine-name-input" path="TASK.engine" label="Script engine name:" type ="text"
                            enabled="${editEnabled}"/>
            <t:textarea-input id="precondition-script-input" path="TASK.preconditionScript" label="Environment setup script:"
                              type ="text" enabled="${editEnabled}"/>
            <t:textarea-input id="output-script-input" path="TASK.outputScript" label="Script to retrieve output:"
                              type ="text" enabled="${editEnabled}"/>
            <t:simple-input id="test-iterations-number-input" path="TASK.testIterationsNumber" label="Number of iterations to test script:"
                            type ="text" enabled="${editEnabled}"/>
            <t:textarea-input id="execution-output-input" path="TASK.output" label="Required scripts output:" type ="text"
                              enabled="${editEnabled}"/>
            <c:if test="${editEnabled}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-md btn-primary btn-block save" type="submit">Save</button>
            </c:if>
        </form>
    </jsp:body>
</t:general>
