<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<t:user actionHeader="Please, login:" actionName="login" actionButton="Login">
    <jsp:attribute name="alternativeAction">
        <a href="./registration">Don&#39;t have account?</a>
    </jsp:attribute>
</t:user>