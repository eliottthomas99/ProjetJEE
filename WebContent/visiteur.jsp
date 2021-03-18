<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
HttpSession maSession = request.getSession();
String codeRetour = (String) maSession.getAttribute("codeRetour");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Visiteur</title>
<link rel="stylesheet" href="style.css" />

</head>

<body>
	<div class="corps">
		<section class="inscription">
			<h1 class="title">Inscription</h1>
			<form method="post" action="Inscription" class="form">
				<legend>Inscription</legend>
				<p>Veuillez remplire les champs nécessaires pour vous inscrire.</p>


				<label for="civilite-select">Civilité:</label> <select
					name="civilite" id="civilite-select">
					<option value="">--Please choose an option--</option>
					<option value=0>Madame</option>
					<option value=1>Monsieur</option>
					<option value=2>Ne se prononce pas</option>
					<option value=3>Non binaire</option>

				</select> <br />

				<div class="form__group field">
					<input type="input" class="form__field" placeholder="Nom"
						name="nomInscription" id='nomInscription' required /> <label
						for="nomInscription" class="form__label">Nom</label>
				</div>

				<div class="form__group field">
					<input type="input" class="form__field" placeholder="Prénom"
						name="prenomInscription" id='prenomInscription' required /> <label
						for="prenomInscription" class="form__label">Prénom</label>
				</div>

				<div class="form__group field">
					<input type="input" class="form__field" placeholder="Email"
						name="email" id='email' required /> <label for="email"
						class="form__label">Email</label>
				</div>

				<div class="form__group field">
					<input type="password" class="form__field"
						placeholder="Mot de passe" name="motdepasse" id='motdepasse'
						required /> <label for="motdepasse" class="form__label">Mot
						de passe</label>
				</div>

				<div class="form__group field">
					<input type="password" class="form__field"
						placeholder="Confirmer mot de passe" name="confirmationMotdepasse"
						id='confirmationMotdepasse' required /> <label
						for="confirmationMotdepasse" class="form__label">Confirmer
						mot de passe</label>
				</div>
				<br /> <label for="dateDeNaissance">Entrez votre date de
					naissance<span class="requis">*</span>
				</label> <input type="date" id="dateDeNaissance" name="dateDeNaissance"
					value="" size="20" maxlength="60" /> <br />

				<div class="form__group field">
					<input type="text" class="form__field" placeholder="Rue" name="Rue"
						id='Rue' required /> <label for="Rue" class="form__label">Rue</label>
				</div>

				<div class="form__group field">
					<input type="text" class="form__field"
						placeholder="Complément d'adresse" name="Complement"
						id='Complement' value='' /> <label for="Complement"
						class="form__label">Complément d'adresse</label>
				</div>

				<div class="form__group field">
					<input type="number" class="form__field" placeholder="Code Postal"
						name="CodePostal" id='CodePostal' required /> <label
						for="CodePostal" class="form__label">Code Postal</label>
				</div>

				<div class="form__group field">
					<input type="text" class="form__field" placeholder="Ville"
						name="Ville" id='Ville' required /> <label for="Ville"
						class="form__label">Ville</label>
				</div>

				<div class="form__group field">
					<input type="text" class="form__field" placeholder="Pays"
						name="Pays" id='Pays' required /> <label for="Pays"
						class="form__label">Pays</label>
				</div>

				<label for="preference-select">Style de musique préféré:</label> <select
					name="preference" id="preference-select">
					<option value="">--Please choose an option--</option>
					<option value=0>Classique</option>
					<option value=1>House</option>
					<option value=2>Jazz</option>
					<option value=3>Métal</option>
					<option value=4>Pop</option>
					<option value=5>Rock</option>

				</select> <br /> <input type="submit" value="Inscription" class="button" />
				<br />
				

			</form>
		</section>


		<section class="visiteur">
			<h1 class="title">Visiteur</h1>
			<form method="post" action="ConnectionTemporaire" class="form">

				<legend>Connection en tant qu'utilisateur invite</legend>
				<p>Veuillez rentrer un nom et un prenom pour votre session.</p>

				<label for="nom">nom <span class="requis">*</span></label> <input
					type="text" id="nom" name="nom" value="" size="20" maxlength="60" />
				<br /> <label for="prenom">prenom<span class="requis">*</span></label>
				<input type="text" id="prenom" name="prenom" value="" size="20"
					maxlength="20" /> <br /> <input type="submit"
					value="Lancer la session" class="button" /> <br />


			</form>
		</section>
	</div>

	<br>
	<br>
	<br>
	<br>

	<section class="Connection">

		<a href="http://localhost:8080/ProjetJEE/Connection"> <input
			type="submit" value="J'ai déjà un compte" class="button" />
		</a> <br />


	<br />

	<form method="get" action="Accueil" >

		
			
			<input color="red" type="submit" value="Accueil du site"
				class="button" /> <br />
		
	</form>

	</section>



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