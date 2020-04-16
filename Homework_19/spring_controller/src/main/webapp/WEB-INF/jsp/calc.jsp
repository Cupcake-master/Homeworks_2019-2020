<html>
<head>
    <title>Calc</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<form class="form-horizontal" method="post">
    <fieldset>

        <div class="form-group">
            <label class="col-md-4 control-label" for="firstOperator">First operator:</label>
            <div class="col-md-4">
                <input id="firstOperator" name="firstOperator" type="number" placeholder="" class="form-control input-md" required="">

            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="secondOperator">Second operator:</label>
            <div class="col-md-4">
                <input id="secondOperator" name="secondOperator" type="number" placeholder="" class="form-control input-md" required="">

            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="operand">Operand:</label>
            <div class="col-md-4">
                <select id="operand" name="operand" class="form-control">
                    <option value="+">+</option>
                    <option value="-">-</option>
                    <option value="*">*</option>
                    <option value="/">/</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="enter"></label>
            <div class="col-md-4">
                <button id="enter" name="enter" class="btn btn-primary">Enter</button>
            </div>
        </div>
    </fieldset>
</form>

<div style="border:3px double black;margin:100px; padding:30px">
    ${viewVariable}
</div>

</body>
</html>
