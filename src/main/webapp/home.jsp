<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<t:general>
    <jsp:attribute name="miscHead">
        <link href="${contextPath}/resources/css/fileinput.min.css" rel="stylesheet">
        <script src="${contextPath}/resources/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
        <script src="${contextPath}/resources/js/plugins/sortable.min.js" type="text/javascript"></script>
        <script src="${contextPath}/resources/js/plugins/purify.min.js" type="text/javascript"></script>
        <script src="${contextPath}/resources/js/fileinput.min.js"></script>
        <script>
            window.onload = function() {
                $("#script-file-input").fileinput({'showUpload':false, 'showRemove':false});
            }
        </script>
        <t:task-selector-script/>
    </jsp:attribute>
    <jsp:attribute name="topBar">
        <t:nav-menu homePageActive="true"/>
    </jsp:attribute>
    <jsp:body>
        <form method="POST" enctype="multipart/form-data" class="script-form panel-default">
            <h4 class="action-header">Select task:</h4>
            <t:parent-task-selector tasksList="${tasksList}" name="taskId"/>
            <h4 class="action-header">Select script:</h4>
            <input id="script-file-input" name="file" type="file" class="file" showUpload="false" showRemove="false"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-md btn-primary btn-block" type="submit">Send</button>
        </form>
        <div class="results-box">
            <c:if test="${topList != null && topList.size() > 0}">
                <div class="last-execution">
                    <c:if test="${previousResult != null}">
                        <div class="panel panel-primary">
                            <h5 class="panel-heading">Your last execution:</h4>
                            <div class="well well-sm">
                                <t:execution executionData="${previousResult}"/>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div class="panel panel-default top-list-box">
                    <h5 class="panel-heading">TOP 100 runs:</h4>
                    <c:forEach var="item" items="${topList}">
                        <div class="well well-sm">
                            <t:execution executionData="${item}"/>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </jsp:body>
</t:general>
