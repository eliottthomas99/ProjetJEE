<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

String str = (String)request.getAttribute("valideStr");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connection</title>
</head>
<body>
	<p>Connection</p>
	
	<form method="post" action="Connection">
			<fieldset>
                <legend>Connection</legend>
                <p>Veuillez rentrer vos identifiants pour vous connecter.</p>

                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="text" id="email" name="email" value="" size="20" maxlength="60" />
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <br />

                <input type="submit" value="Connexion" class="sansLabel" />
                <br />
            </fieldset>
    	<span class="erreur"><%=str %></span>
	</form>
</body>
</html>