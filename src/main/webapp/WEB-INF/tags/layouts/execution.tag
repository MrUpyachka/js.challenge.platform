<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="executionData" required="true" type="qq.upyachka.js.contest.core.dto.ScriptExecutionResultDto" %>

<div class="execution-item">
    <h5>
        <c:out value="${executionData.getOwner()}" />
    </h5>
    <h5>
        <a href="./details?id=${executionData.getId()}"><c:out value="${executionData.getStartTime()}" /></a>
    </h5>
    <c:choose>
        <c:when test="${executionData.getErrorCause() != null}">
            <h5>Failed with exception:</h5>
            <span class="label label-default">
                "${executionData.getErrorCause().toString()}"
            </span>
        </c:when>
        <c:when test="${executionData.getMinExecutionTimeInNanoseconds() == null}">
            <h5>Pending</h5>
            <span class="label label-default">
                Not finished.
            </span>
        </c:when>
        <c:when test="${executionData.getMinExecutionTimeInNanoseconds() != null && !executionData.getSucceeded()}">
            <h5>Wrong output</h5>
            <span class="label label-default">
                <c:out value="${executionData.getMinExecutionTimeInNanoseconds()}" />
            </span>
        </c:when>
        <c:otherwise>
            <h5>OK</h5>
            <span class="label label-default">
                <c:out value="${executionData.getMinExecutionTimeInNanoseconds()}" />
            </span>
        </c:otherwise>
    </c:choose>

</div>
