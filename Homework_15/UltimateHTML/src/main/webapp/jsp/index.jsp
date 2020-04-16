<!DOCTYPE html>
<html lang="en">
<head>
	<title>Main</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<meta charset="utf-8">
</head>
<body>
	<div class="all">
		<input checked type="radio" name="respond" id="desktop">
			<article id="slider">
					<input checked type="radio" name="slider" id="switch1">
					<input type="radio" name="slider" id="switch2">
					<input type="radio" name="slider" id="switch3">
					<input type="radio" name="slider" id="switch4">
					<input type="radio" name="slider" id="switch5">
				<div id="slides">
					<div id="overflow">
						<div class="image">
							<article><img src="${pageContext.request.contextPath}/img/6.jpg"></article>
							<article><img src="${pageContext.request.contextPath}/img/7.jpg"></article>
							<article><img src="${pageContext.request.contextPath}/img/8.jpg"></article>
							<article><img src="${pageContext.request.contextPath}/img/9.jpg"></article>
							<article><img src="${pageContext.request.contextPath}/img/10.jpg"></article>
						</div>
					</div>
				</div>
				<div id="controls">
					<label for="switch1"></label>
					<label for="switch2"></label>
					<label for="switch3"></label>
					<label for="switch4"></label>
					<label for="switch5"></label>
				</div>
				<div id="active">
					<label for="switch1"></label>
					<label for="switch2"></label>
					<label for="switch3"></label>
					<label for="switch4"></label>
					<label for="switch5"></label>
				</div>
			</article>
	</div>
</body>
</html>