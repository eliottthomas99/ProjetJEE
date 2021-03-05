<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visiteur</title>
    
</head>
<body>
	<h1>Visiteur</h1>
	<form method="post" action="Inscription">
			<fieldset>
                <legend>Inscription</legend>
                <p>Veuillez remplire les champs nécessaires pour vous inscrire.</p>


				<label for="civilite-select">Civilité:</label>
					<select name="civilite" id="pet-select">
					    <option value="">--Please choose an option--</option>
					    <option value=0>Madame</option>
					    <option value=1>Monsieur</option>
					    <option value=2>Ne se prononce pas</option>
					    <option value=3>Non binaire</option>
					    
					</select>
				<br />

				<label for="nomInscription">nom<span class="requis">*</span></label>
                <input type="text" id="nomInscription" name="nom" value="" size="20" maxlength="60" />
                <br />

                <label for="prenomInscription">prenom<span class="requis">*</span></label>
                <input type="text" id="prenomInscription" name="prenom" value="" size="20" maxlength="20" />
                <br />



                <label for="email">Adresse email <span class="requis">*</span></label>
                <input type="text" id="email" name="email" value="" size="20" maxlength="60" />
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <br />

                <input type="submit" value="Connexion" class="sansLabel" />
                <br />
            </fieldset>
	    	
	</form>
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
	<hr>
	
	<form method="post" action="ConnectionTemporaire" >
				<input  type="submit" value="J'ai déjà un compte" class="sansLabel" />
	            <br />
	
	</form>
	
	
</body>
</html>