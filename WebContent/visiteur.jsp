<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Visiteur</title>
		<link rel="stylesheet" href="style.css" />

	</head>

	<body>
		<section class="inscription">
			<h1>Visiteur</h1>
			<form method="post" action="Inscription">
				<fieldset>
					<legend>Inscription</legend>
					<p>Veuillez remplire les champs nécessaires pour vous inscrire.</p>


					<label for="civilite-select">Civilité:</label>
					<select name="civilite" id="civilite-select">
						<option value="">--Please choose an option--</option>
						<option value=0>Madame</option>
						<option value=1>Monsieur</option>
						<option value=2>Ne se prononce pas</option>
						<option value=3>Non binaire</option>

					</select>
					<br />

					<label for="nomInscription">nom<span class="requis">*</span></label>
					<input type="text" id="nomInscription" name="nomInscription" value="" size="20" maxlength="60" />
					<br />

					<label for="prenomInscription">prenom<span class="requis">*</span></label>
					<input type="text" id="prenomInscription" name="prenomInscription" value="" size="20" maxlength="20" />
					<br />



					<label for="email">Adresse email <span class="requis">*</span></label>
					<input type="text" id="email" name="email" value="" size="20" maxlength="60" />
					<br />

					<label for="motdepasse">Mot de passe <span class="requis">*</span></label>
					<input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
					<br />

					<label for="confirmationMotdepasse">Confirmer mot de passe<span class="requis">*</span></label>
					<input type="password" id="confirmationMotdepasse" name="confirmationMotdepasse" value="" size="20"
						maxlength="20" />
					<br />

					<label for="dateDeNaissance">Entrez votre date de naissance au format AAAA-MM-JJ<span
							class="requis">*</span></label>
					<input type="text" id="dateDeNaissance" name="dateDeNaissance" value="" size="20"
						maxlength="60" />
					<br />

					<label for="Rue">Rue<span class="requis">*</span></label>
					<input type="text" id="Rue" name="Rue" value="" size="20" maxlength="60" />
					<br />

					<label for="Complement">Complément d'adresse<span class="Nonrequis"></span></label>
					<input type="text" id="Complement" name="Complement" value="" size="20" maxlength="60" />
					<br />

					<label for="CodePostal">Code Postal<span class="requis">*</span></label>
					<input type="text" id="CodePostal" name="CodePostal" value="" size="20" maxlength="60" />
					<br />

					<label for="Ville">Ville<span class="requis">*</span></label>
					<input type="text" id="Ville" name="Ville" value="" size="20" maxlength="60" />
					<br />

					<label for="Pays">Pays<span class="requis">*</span></label>
					<input type="text" id="Pays" name="Pays" value="" size="20" maxlength="60" />
					<br />


					<label for="preference-select">Style de musique préféré:</label>
					<select name="preference" id="preference-select">
						<option value="">--Please choose an option--</option>
						<option value=0>Classique</option>
						<option value=1>House</option>
						<option value=2>Jazz</option>
						<option value=3>Métal</option>
						<option value=4>Pop</option>
						<option value=5>Rock</option>

					</select>
					<br />



					<input type="submit" value="Inscription" class="sansLabel" />
					<br />
				</fieldset>

			</form>
		</section>

		<section class="visiteur">
			<form method="post" action="ConnectionTemporaire">
				<fieldset>
					<legend>Connection en tant qu'utilisateur invite</legend>
					<p>Veuillez rentrer un nom et un prenom pour votre session.</p>

					<label for="nom">nom <span class="requis">*</span></label>
					<input type="text" id="nom" name="nom" value="" size="20" maxlength="60" />
					<br />

					<label for="prenom">prenom<span class="requis">*</span></label>
					<input type="text" id="prenom" name="prenom" value="" size="20" maxlength="20" />
					<br />

					<input type="submit" value="Lancer la session" class="sansLabel" />
					<br />
				</fieldset>

			</form>
		</section>


		<hr>
		<section class="Connection">
			<form method="post" action="ConnectionTemporaire">
				<input type="submit" value="J'ai déjà un compte" class="sansLabel" />
				<br />

			</form>
		</section>


	</body>

	</html>