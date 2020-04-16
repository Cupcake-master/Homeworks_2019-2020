<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>BulHub</title>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <a class="navbar-brand">
            <img src="img/14.gif"
                 width="50" height="50" alt="logo">
            <a href="https://github.com/BulHub" style="color: white"><h4>BulHub Company</h4></a>
        </a>
    </div>
    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Back</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">About us</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Feedback</a>
            </li>
        </ul>
    </div>
</nav>
<form class="form-horizontal" autocomplete="off" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>Entrance: </legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="e-mail">E-mail:</label>
            <div class="col-md-4">
                <input id="e-mail" name="e-mail" type="email" placeholder="Write e-mail" class="form-control input-md" required="required">

            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password">Password:</label>
            <div class="col-md-4">
                <input id="password" name="password" type="password" placeholder="Write password" class="form-control input-md" required="required">

            </div>
        </div>

        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="Entrance"></label>
            <div class="col-md-8">
                <button id="Entrance" name="Entrance" class="btn btn-success" type="submit">Enter</button>
                <button id="Registration" name="Registration" class="btn btn-danger"><a class="text-white" href="${pageContext.request.contextPath}/registration">Registration</a></button>
            </div>
        </div>

    </fieldset>
</form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>