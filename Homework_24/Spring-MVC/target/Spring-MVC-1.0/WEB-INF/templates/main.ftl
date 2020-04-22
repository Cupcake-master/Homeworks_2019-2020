<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
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

                if (scrolled >= 95) {
                    $(show23).show();
                }
            });
    })
    }
</script>
<body onload="refresh()">
<div id="show23">
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
</body>