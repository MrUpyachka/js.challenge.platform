<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="active" required="true" type="java.lang.Boolean" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="href" required="true" %>

<c:set var="style" value=""/>
<c:if test="${active}">
   <c:set var="style" value="active"/>
</c:if>
<li class="${style}">
    <a href="${href}">${name} <c:if test="${active}"><span class="sr-only">(current)</span></c:if>
    </a>
</li>
