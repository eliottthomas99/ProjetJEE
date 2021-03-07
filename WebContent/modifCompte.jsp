<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Visiteur</title>
		<link rel="stylesheet" href="style.css" />

	</head>

	<body>
		<div class="corps">
		<section class="modifCompte">
			<h1>Modification Compte</h1>
			<form method="post" action="ModifCompte">
				<fieldset>
					<legend>Inscription</legend>
					<p>Veuillez remplire les champs nécessaires.</p>


					<label for="civilite-select">Civilité:</label>
					<select name="civilite" id="civilite-select">
						<option value="">--Please choose an option--</option>
						<option value=0>Madame</option>
						<option value=1>Monsieur</option>
						<option value=2>Ne se prononce pas</option>
						<option value=3>Non binaire</option>

					</select>
					<br />

					<div class="form__group field">
						<input type="input" class="form__field" placeholder="Nom" name="nom" id='nomInscription' required />
						<label for="nomInscription" class="form__label">Nom</label>
					</div>

					<!--label for="nomInscription">nom<span class="requis">*</span></label>
					<input type="text" id="nomInscription" name="nom" value="" size="20" maxlength="60" />
					<br /-->

					<div class="form__group field">
						<input type="input" class="form__field" placeholder="Prénom" name="prenom" id='prenomInscription' required />
						<label for="prenomInscription" class="form__label">Prénom</label>
					</div>

					<!--label for="c">prenom<span class="requis">*</span></label>
					<input type="text" id="prenomInscription" name="prenom" value="" size="20" maxlength="20" />
					<br /-->


					<label for="dateDeNaissance">Entrez votre date de naissance<span
							class="requis">*</span></label>
					<input type="date" id="dateDeNaissance" name="dateDeNaissance" value="" size="20"
						maxlength="60" />
					<br />
					
					<div class="form__group field">
						<input type="text" class="form__field" placeholder="Rue" name="Rue" id='Rue' required />
						<label for="Rue" class="form__label">Rue</label>
					</div>

					<!--label for="Rue">Rue<span class="requis">*</span></label>
					<input type="text" id="Rue" name="Rue" value="" size="20" maxlength="60" />
					<br /-->

					<div class="form__group field">
						<input type="text" class="form__field" placeholder="Complément d'adresse" name="Complement" id='Complement' value='' />
						<label for="Complement" class="form__label">Complément d'adresse</label>
					</div>

					<!--label for="Complement">Complément d'adresse<span class="Nonrequis"></span></label>
					<input type="text" id="Complement" name="Complement" value="" size="20" maxlength="60" />
					<br /-->

					<div class="form__group field">
						<input type="number" class="form__field" placeholder="Code Postal" name="CodePostal" id='CodePostal' required />
						<label for="CodePostal" class="form__label">Code Postal</label>
					</div>

					<!--label for="CodePostal">Code Postal<span class="requis">*</span></label>
					<input type="number" id="CodePostal" name="CodePostal" value="" size="20" maxlength="60" />
					<br /-->

					<div class="form__group field">
						<input type="text" class="form__field" placeholder="Ville" name="Ville" id='Ville' required />
						<label for="Ville" class="form__label">Ville</label>
					</div>

					<!--label for="Ville">Ville<span class="requis">*</span></label>
					<input type="text" id="Ville" name="Ville" value="" size="20" maxlength="60" />
					<br /-->

					<div class="form__group field">
						<input type="text" class="form__field" placeholder="Pays" name="Pays" id='Pays' required />
						<label for="Pays" class="form__label">Pays</label>
					</div>

					<!--label for="Pays">Pays<span class="requis">*</span></label>
					<input type="text" id="Pays" name="Pays" value="" size="20" maxlength="60" />
					<br /-->


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

		<hr>
		
		


	</body>

	</html>