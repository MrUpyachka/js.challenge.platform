<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ attribute name="executionData" required="true" type="qq.upyachka.js.contest.platform.script.ScriptExecutionResultDto" %>

<div class="execution-details-box panel panel-default">
    <div class="panel-body">
        <div class="primary">
            <h5>Owner:</h5>
            <h5>${executionData.getUsername()}</h5>
        </div>
        <div class="primary">
            <h5>Start time:</h5>
            <h5>${executionData.getStartTime()}</h5>
        </div>
        <div class="primary">
            <h5>Minimum execution time:</h5>
            <h5>${executionData.getMinExecutionTimeInNanoseconds()}</h5>
        </div>
        <div class="primary">
            <h5>Average execution time:</h5>
            <h5>${executionData.getExecutionTimeInNanoseconds()}</h5>
        </div>
    </div>
    <button class="btn btn-default" data-toggle="collapse" data-target="#script-body">Script</button>
    <div id="script-body" class="script-body-wrapper collapse panel-body">
        <code>${executionData.getBody()}</code>
    </div>
    <button class="btn btn-default" data-toggle="collapse" data-target="#script-result">Result</button>
    <div id="script-result" class="script-result-wrapper collapse panel-body">
        <code>${executionData.getResult()}</code>
    </div>
    <button class="btn btn-default" data-toggle="collapse" data-target="#script-output">Output</button>
    <div id="script-output" class="script-output-wrapper collapse panel-body">
        <code>${executionData.getOutput()}</code>
    </div>
</div>