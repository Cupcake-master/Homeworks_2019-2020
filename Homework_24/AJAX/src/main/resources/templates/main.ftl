<head>
    <meta charset="UTF-8">
    <title>Document</title>
</head>
<body onload="">
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>
<div id="show">
    <p>Hello</p>
    <p>World!</p>
</div>

<script>
    function refresh() {

        $.ajax({
            url: "/",
            method: "POST",
            contentType: "application/json",
            dataType: "json",

            $(window).scroll(function() {
                let wintop = $(window).scrollTop(),
                    docheight = $(document).height(),
                    winheight = $(window).height(),
                    scrolled = (wintop / (docheight - winheight)) * 100;

                if (scrolled >= 100) {
                    $(show).show();
                }
            });
    })
    }
</script>
</body>