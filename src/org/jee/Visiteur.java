package org.jee;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Visiteur {

	private String nom;
	private String prenom;

	enum returnStatement {
		OK, CONNECTION_FAIL, EMAIL_NON_DISPO, MDP_DIFF
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Visiteur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public static returnStatement CreerCompte(MainServlet.civilite civilite, String nom, String prenom, String email,
			String password, String confirmPassword, String naissance, String addr_rue, String addr_complement,
			int addr_code_postal, String ville, String pays, MainServlet.preference preference)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		// connexion a la base de donnees
		Connection connexion = DBManager.getInstance().getConnection();

		// verification de la dispo. de l'email
		Boolean emailDispo = AlgorithmeDeVerification.emailDispo(email);

		// si l'email n'est pas dispo
		if (!emailDispo) {
			// ANNULER LA PROCEDURE
			return returnStatement.EMAIL_NON_DISPO;
		} else {
			// sinon
			// l'email est dispo.

			// verification des mots de passes
			if (!password.equals(confirmPassword)) {
				// si les mots de passe sont differents
				
				// ANNULER LA PROCEDURE
				return returnStatement.MDP_DIFF;

			} else {
				// sinon les mots de passe sont identiques
				try (Statement stmt = connexion.createStatement()) {

					// on hash le mdp avant de le stocker dans la BDD
					String passwordHash = JavaMD5Hash.md5(password);

					String insert_query = String.format(
							"INSERT INTO membres(civilite,nom,prenom,email,password, naissance,addr_rue"
									+ ",addr_complement,addr_code_postal,addr_ville,addr_pays,preference,bloque,tentatives,temps) "
									+ "VALUES ( '%s', '%s','%s', '%s', '%s','%s', '%s', '%s','%s', '%s', '%s','%s','%s','%s','%s');",
							civilite.ordinal(), nom, prenom, email, passwordHash, naissance, addr_rue, addr_complement,
							addr_code_postal, ville, pays, preference.ordinal(), 0, 0, 0);

					int rs = stmt.executeUpdate(insert_query);

					return returnStatement.OK;
				} catch (SQLException e) {

					System.out.println(e);
					return returnStatement.CONNECTION_FAIL;
				}
			}
		}
	}
}
