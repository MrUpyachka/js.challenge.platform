<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="executionData" required="true" type="qq.upyachka.js.challenge.platform.script.ScriptExecutionResultDto" %>

<div class="execution-item">
    <h5>
        <c:out value="${executionData.getUsername()}" />
    </h5>
    <h5>
        <c:out value="${executionData.getStartTime()}" />
    </h5>
    <span class="label label-default">
        <c:choose>
            <c:when test="${executionData.getErrorCause() != null}">
               Failed with exception: "${executionData.getErrorCause().toString()}"
            </c:when>
            <c:when test="${executionData.getMinExecutionTimeInNanoseconds() == null}">
               Not finished.
            </c:when>
            <c:otherwise>
                <c:out value="${executionData.getMinExecutionTimeInNanoseconds()}" />
            </c:otherwise>
        </c:choose>
    </span>
</div>