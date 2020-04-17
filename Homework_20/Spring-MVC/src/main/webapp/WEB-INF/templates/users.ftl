<html lang="en">
<head>
    <title>Users</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Email</th>
        <th scope="col">Password</th>
        <th scope="col">Telephone number</th>
        <th scope="col">Date of Birth</th>
        <th scope="col">Gender</th>
        <th scope="col">Country</th>
        <th scope="col">About myself</th>
    </tr>
    </thead>
    <#list users as user>
    <tr>
        <td>${user.id}</td>
        <td>${user.email} </td>
        <td>${user.password} </td>
        <td>${user.telephone_number}</td>
        <td>${user.dateOfBirth}</td>
        <td>${user.gender}</td>
        <td>${user.country}</td>
        <td>${user.aboutMyself} </td>
        </#list>
    </tr>
</table>
</body>
</html>
