<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List,org.jee.Membre,org.jee.MainServlet"%>

<%
HttpSession maSession = request.getSession();
Membre membre = (Membre) maSession.getAttribute("membreModifie");

if (membre == null) {
	membre = (Membre) maSession.getAttribute("membreConnecte");
}

String codeRetour = (String) maSession.getAttribute("codeRetour");
String lemail = (String) request.getAttribute("lemail");

if (lemail == null) {
	lemail = "";
}
%>


<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Modification Compte</title>
<link rel="stylesheet" href="style.css" />

</head>

<body>
	<div class="corps">

		<section class="modifCompte">
			<h1>Modification Compte Admin</h1>

			<form method="post" action="AdminChooseCompte">

				<div class="form__group field">
					<input type="input" class="form__field" placeholder="email"
						name="email" id="email" value="<%=lemail%>" required /> <label
						for="email" class="form__label">Email</label>
				</div>

				<input type="submit" value="Choisir" class="sansLabel" /> <br />

			</form>
			<form method="post" action="AdminModifCompte">
				<fieldset>
					<legend>Modification Compte Admin</legend>

					<p>Veuillez remplir les champs nécessaires.</p>

					<label for="civilite-select">Civilité:</label> <select
						name="civilite" id="civilite-select">
						<option value="<%=membre.getCivilite().ordinal()%>"><%=membre.getCivilite()%></option>
						<option value=0>Madame</option>
						<option value=1>Monsieur</option>
						<option value=2>Ne se prononce pas</option>
						<option value=3>Non binaire</option>
					</select> <br />

					<div class="form__group field">
						<input type="input" class="form__field" placeholder="Nom"
							name="nom" id='nomInscription' value="<%=membre.getNom()%>"
							required /> <label for="nomInscription" class="form__label">Nom</label>
					</div>

					<div class="form__group field">
						<input type="input" class="form__field" placeholder="Prénom"
							name="prenom" id="prenomInscription"
							value="<%=membre.getPrenom()%>" required /> <label
							for="prenomInscription" class="form__label">Prénom</label>
					</div>

					<label for="dateDeNaissance">Entrez votre date de naissance<span
						class="requis">*</span></label> <input type="date" id="dateDeNaissance"
						name="dateDeNaissance" value="<%=membre.getNaissance()%>"
						size="20" maxlength="60" /> <br />

					<div class="form__group field">
						<input type="text" class="form__field" placeholder="Rue"
							name="Rue" id='Rue' value="<%=membre.getAddr_rue()%>" required />
						<label for="Rue" class="form__label">Rue</label>
					</div>

					<div class="form__group field">
						<input type="text" class="form__field"
							placeholder="Complément d'adresse" name="Complement"
							id='Complement' value="<%=membre.getAddr_complement()%>" /> <label
							for="Complement" class="form__label">Complément d'adresse</label>
					</div>

					<div class="form__group field">
						<input type="number" class="form__field" placeholder="Code Postal"
							name="CodePostal" id='CodePostal'
							value="<%=membre.getAddr_code_postal()%>" required /> <label
							for="CodePostal" class="form__label">Code Postal</label>
					</div>
					<div class="form__group field">
						<input type="text" class="form__field" placeholder="Ville"
							name="Ville" id='Ville' value="<%=membre.getVille()%>" required />
						<label for="Ville" class="form__label">Ville</label>
					</div>

					<div class="form__group field">
						<input type="text" class="form__field" placeholder="Pays"
							name="Pays" id='Pays' value="<%=membre.getPays()%>" required />
						<label for="Pays" class="form__label">Pays</label>
					</div>

					<label for="preference-select">Style de musique préféré:</label> <select
						name="preference" id="preference-select">
						<option value="<%=membre.getPreference().ordinal()%>"><%=membre.getPreference()%></option>
						<option value=0>Classique</option>
						<option value=1>House</option>
						<option value=2>Jazz</option>
						<option value=3>Métal</option>
						<option value=4>Pop</option>
						<option value=5>Rock</option>
					</select> <br /> <input type="submit" value="Modification"
						class="sansLabel" /> <br />
				</fieldset>

			</form>
		</section>

		<section class="AdminModifAvanceeCompte">
			<h1>Modification Avancée Compte</h1>
			<form method="post" action="AdminModifAvanceeCompte">
				<fieldset>
					<legend>Modificaction Avancee Admin Compte</legend>

					<div class="form__group field">
						<input type="input" class="form__field" placeholder="nouveauEmail"
							name="nouveauEmail" id="nouveauEmail" value="" required /> <label
							for="nouveauEmail" class="form__label">nouveauEmail</label>
					</div>

					<div class="form__group field">
						<input type="password" class="form__field"
							placeholder="nouveauMotDePasse" name="nouveauMotDePasse"
							id="nouveauMotDePasse" value="" required /> <label
							for="nouveauMotDePasse" class="form__label">nouveauMotDePasse</label>
					</div>

					<input type="submit" value="ModificationAvancee" class="sansLabel" />
					<br />
				</fieldset>

			</form>

		</section>


		<form method="post" action="AdminSupprimerCompte">

			<fieldset>
				<legend>Suppression du Compte</legend>
				<input color="red" type="submit" value="Supprimer" class="sansLabel" />
				<br />
			</fieldset>
		</form>


		<form method="post" action="MainServlet">

			<fieldset>
				<legend>Accueil</legend>
				<input color="red" type="submit" value="Accueil" class="sansLabel" />
				<br />
			</fieldset>
		</form>



		<%
		/* A LAISSER A LA FIN DU CODE */
		/* permet d'afficher la pop up APRES que la page ait chargee */
		System.out.println(codeRetour);
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
	</div>

</body>

</html>