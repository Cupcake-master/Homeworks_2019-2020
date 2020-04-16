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
            <img src="14.gif"
                 width="50" height="50" alt="logo">
            <a href="https://github.com/BulHub" style="color: white"><h4>BulHub Company</h4></a>
        </a>
    </div>
    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/Entrance_war/main">Back</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/Entrance_war/aboutUs">About us</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/Entrance_war/feedback">Feedback</a>
            </li>
        </ul>
    </div>
</nav>

<form class="form-horizontal">
    <fieldset>

        <!-- Form Name -->
        <legend></legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="E-mai">Your e-mail:</label>
            <div class="col-md-4">
                <input id="E-mai" name="E-mai" type="text" placeholder="Write your e-mail" class="form-control input-md" required="">

            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="question subject">Question subject:</label>
            <div class="col-md-4">
                <input id="question subject" name="question subject" type="text" placeholder="Write question subject" class="form-control input-md" required="">

            </div>
        </div>

        <!-- Textarea -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="your letter">Your letter:</label>
            <div class="col-md-4">
                <textarea style="width:400px; height:200px;" class="form-control" id="your letter" name="your letter">Hello! I want to say that ...</textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label"></label>
            <div class="col-md-4">
                <div class="checkbox">
                    <label for="agree-0">
                        <input type="checkbox" name="agree" id="agree-0" value="1" >
                        I agree to the processing of personal data
                    </label>
                </div>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="enter"></label>
            <div class="col-md-4">
                <button id="enter" name="enter" class="btn btn-primary">Enter</button>
            </div>
        </div>

    </fieldset>
</form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>