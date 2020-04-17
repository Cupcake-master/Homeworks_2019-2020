<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>

<html lang="en">
<head>
    <title>Sign up</title>
</head>
<body>
<@sf.form action="http://localhost:8080/Spring_MVC_war_exploded/users/new" method="post" modelAttribute="user">
    <div>
        <@sf.label path="email">Email: </@sf.label>
        <@sf.input path="email"/>
        <@sf.errors path="email"/>
    </div>
    <div>
        <@sf.label path="password">Password: </@sf.label>
        <@sf.input path="password"/>
        <@sf.errors path="password"/>
    </div>
    <div>
        <@sf.label path="passwordRepeat">Re-password: </@sf.label>
        <@sf.input path="passwordRepeat"/>
        <@sf.errors path="passwordRepeat"/>
    </div>
    <div>
        <@sf.label path="telephone_number">Telephone number: </@sf.label>
        <@sf.input path="telephone_number"/>
        <@sf.errors path="telephone_number"/>
    </div>
    <div>
        <@sf.label path="dateOfBirth">Date of birth: </@sf.label>
        <@sf.input path="dateOfBirth"/>
        <@sf.errors path="dateOfBirth"/>
    </div>
    <div>
        <@sf.label path="gender">Gender: </@sf.label>
        <@sf.input path="gender"/>
        <@sf.errors path="gender"/>
    </div>
    <div>
        <@sf.label path="country">Country: </@sf.label>
        <@sf.input path="country"/>
        <@sf.errors path="country"/>
    </div>
    <div>
        <@sf.label path="aboutMyself">About myself: </@sf.label>
        <@sf.input path="aboutMyself"/>
        <@sf.errors path="aboutMyself"/>
    </div>
    <div>
        <@sf.checkbox path="check"></@sf.checkbox>
        <@sf.label path="check">I accept the Terms and Conditions </@sf.label>
        <@sf.errors path="check"/>
    </div>
    <input type="submit">
</@sf.form>
</body>
</html>