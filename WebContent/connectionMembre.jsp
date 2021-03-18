<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List,org.jee.Membre"%>

<%
HttpSession maSession = request.getSession();

String codeRetour = (String) maSession.getAttribute("codeRetour");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connection</title>
<link rel="stylesheet" href="style.css" />
</head>
<body>

	<form method="post" action="Connection" class="form">
	
		<legend class="title">Connection</legend>
		<p>Veuillez rentrer vos identifiants pour vous connecter.</p>

		<label for="email">Adresse email <span class="requis">*</span></label>
		<input type="text" id="email" name="email" value="" size="20"
			maxlength="60"  /> <br /> <label for="motdepasse">Mot de
			passe <span class="requis">*</span>
		</label> <input type="password" id="motdepasse" name="motdepasse" value=""
			size="20" maxlength="20" /> <br /> <input type="submit"
			value="Connexion" class="button" /> <br />
	</form>
	<br/>

	<form method="get" action="Visiteur" >

		
			<input color="red" type="submit" value="Creer un compte"
				class="button" /> <br />
		
	</form>
	
	<br />
	<form method="get" action="Accueil" >

		
			
			<input color="red" type="submit" value="Accueil du site"
				class="button" /> <br />
		
	</form>


	<%
	/* A LAISSER A LA FIN DU CODE */
	/* permet d'afficher la pop up APRES que la page ait chargee */
	if (codeRetour != null) {
	%>
	<script>
			console.log("<%=codeRetour%>");
			alert("<%=codeRetour%>
		");
	</script>
	<%
	}
	codeRetour = null;
	maSession.setAttribute("codeRetour", codeRetour);
	%>

</body>
</html>