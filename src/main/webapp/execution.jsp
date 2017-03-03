<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:general>
    <jsp:body>
        <a href="./">Home</a>
        <t:execution-details executionData="${executionDetails}"/>
    </jsp:body>
</t:general>