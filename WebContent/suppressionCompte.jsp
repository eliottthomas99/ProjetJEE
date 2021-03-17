<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Suppression compte</title>
<link rel="stylesheet" href="style.css" />
</head>
<body>

	<div class="corps">
		<section class=confirmation>
			<form method="post" action="suppression">
				<input type="submit" value="Oui je suis certain" class="dangereux" />
			</form>
		</section>

		<section class="confirmation">
			<a href="http://localhost:8080/ProjetJEE/MainServlet"> <input
				type="submit" value="Non je ne veux plus" class="submitButton" />
			</a>
		</section>
	</div>
</body>
</html>