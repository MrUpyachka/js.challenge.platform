<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="executionData" required="true" type="qq.upyachka.js.challenge.platform.script.ScriptExecutionResultDto" %>

<div class="execution-details-box panel panel-default">
    <div class="panel-body">
        <h5>Owner:</h5>
        <h5>${executionData.getUsername()}</h5>
    </div>
    <div class="panel-body">
        <h5>Start time:</h5>
        <h5>${executionData.getStartTime()}</h5>
    </div>
    <div class="panel-body">
        <h5>Minimum execution time:</h5>
        <h5>${executionData.getMinExecutionTimeInNanoseconds()}</h5>
    </div>
    <div class="panel-body">
        <h5>Average execution time:</h5>
        <h5>${executionData.getExecutionTimeInNanoseconds()}</h5>
    </div>
    <div class="script-body-wrapper well well-sm collapse">
        <h5>Script:</h5>
        <code>${executionData.getBody()}</code>
    </div>
    <div class="script-result-wrapper well well-sm collapse">
        <h5>Result:</h5>
        <code>${executionData.getResult()}</code>
    </div>
    <div class="script-output-wrapper well well-sm collapse">
        <h5>Output:</h5>
        <code>${executionData.getOutput()}</code>
    </div>
</div>