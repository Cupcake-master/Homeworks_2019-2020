<!doctype html>
<html>
<head>
    <meta charset = "utf8" />
    <title>Бесконечный скроллинг с JavaScript</title>
</head>
<body>
<div id="infinite-scroll">
    <div>
        <script>
            for( let i = 0; i < 100; i++ )
                document.write("<div>Случайный текст или еще, что то</div>");
        </script>
    </div>
</div>
<script>
    window.addEventListener("scroll", function() {

        let block = document.getElementById('infinite-scroll');
        let counter = 1;

        let contentHeight = block.offsetHeight;      // 1) высота блока контента вместе с границами
        let yOffset = window.pageYOffset;      // 2) текущее положение скролбара
        let window_height = window.innerHeight;      // 3) высота внутренней области окна документа
        let y = yOffset + window_height;

        // если пользователь достиг конца
        if (y >= contentHeight) {
            //загружаем новое содержимое в элемент
            block.innerHTML = block.innerHTML + "<div>Случайный текст или еще, что то</div>";
        }
    });

</script>
</body>
</html>