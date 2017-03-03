<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:general>
    <jsp:attribute name="miscHead">
        <link href="./resources/css/fileinput.min.css" rel="stylesheet">
        <script src="./resources/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
        <script src="./resources/js/plugins/sortable.min.js" type="text/javascript"></script>
        <script src="./resources/js/plugins/purify.min.js" type="text/javascript"></script>
        <script src="./resources/js/fileinput.min.js"></script>
        <script>
            window.onload = function() {
                $("#script-file-input").fileinput({'showUpload':false, 'showRemove':false});
            }
        </script>
    </jsp:attribute>
    <jsp:body>
        <form method="POST" enctype="multipart/form-data" class="script-form">
            <div class="well well-sm action-header">Select script:</div>
            <input id="script-file-input" name="file" type="file" class="file" showUpload="false" showRemove="false"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Send</button>
        </form>
        <div class="results-box">
            <c:if test="${topList != null && topList.size() > 0}">
                <div class="last-execution">
                    <c:if test="${previousResult != null}">
                        <t:execution executionData="${previousResult}"/>
                    </c:if>
                </div>
                <div class="well well-sm top-list-box">
                    <c:forEach var="item" items="${topList}">
                        <t:execution executionData="${item}"/>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </jsp:body>
</t:general>