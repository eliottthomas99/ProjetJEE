<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.util.List,org.jee.Membre"%>

<%

//String str = (String)request.getAttribute("valideStr");
HttpSession maSession = request.getSession();

String codeRetour = (String)maSession.getAttribute("codeRetour");

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
         
	</form>
	
	<%
		/* A LAISSER A LA FIN DU CODE */
		/* permet d'afficher la pop up APRES que la page ait chargee */
		System.out.println(codeRetour);
		if (codeRetour != null)
		{ %>
		<script>
			console.log("<%= codeRetour%>");
			alert("<%= codeRetour%>");
		</script>
		<%}
		codeRetour = null;
		maSession.setAttribute("codeRetour", codeRetour);
		%>
	
</body>
</html>