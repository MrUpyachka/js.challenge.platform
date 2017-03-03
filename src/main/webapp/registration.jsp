<%@taglib prefix="t" tagdir="/WEB-INF/tags/layouts" %>

<t:user actionHeader="Register new user:" actionName="register" actionButton="Register">
    <jsp:attribute name="alternativeAction">
        <a href="./login">Already have account?</a>
    </jsp:attribute>
</t:user>