<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/table" method="post">
    <input  type="submit" value="Sort by name" name="sortName" />
    <input type="submit" value="Sort by country" name="sortCountry" />
    <input type="submit" value="Save Cookies" name="saveCookies">
</form>
<table class="table">
    <thead class="thead-inverse">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Country</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${linesForTable}" var="line">
        <tr>
            <td>${line.ID}</td>
            <td>${line.firstName}</td>
            <td>${line.country}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>