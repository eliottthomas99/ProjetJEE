package org.jee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Visiteur {

	private String nom;
	private String prenom;

	
	enum returnStatement {
		  OK,
		  CONNECTION_FAIL,
		  EMAIL_NON_DISPO,
		  MDP_DIFF,
		  POP,
		  ROCK
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

	public static returnStatement CreerCompte(MainServlet.civilite civilite, String nom, String prenom, String email, String password,
			String confirmPassword, String naissance, String addr_rue, String addr_complement, int addr_code_postal,
			String ville, String pays, MainServlet.preference preference) {

		// 1) Vérifier que tous les champs sont remplis

		Connection connexion = DBManager.getInstance().getConnection();

		// 2) Vérifier que l'email est dispo

		Boolean emailDispo = AlgorithmeDeVerification.emailDispo(email);

		
		
		
		if (!emailDispo) {
			// ANNULER LA PROCEDURE
			System.out.println("email non disponible");
			return returnStatement.EMAIL_NON_DISPO;
			// AFFICHER UN MESSAGE DERREUR DANS LINTERFACE
		} else {

			
			
			// 3) Vérifier l'égalité des mots de passe
			if (!password.equals(confirmPassword)) {
				//TODO
				// ANNULER LA PROCEDURE
				// AFFICHER UN MESSAGE DERREUR DANS LINTERFACE
				System.out.println("mots de passes non identiques");
				return returnStatement.MDP_DIFF;
				
			} else {
				
				
				
				
				try (Statement stmt = connexion.createStatement()) {

					String insert_query = String.format(
							"INSERT INTO membres(civilite,nom,prenom,email,password,naissance,addr_rue"
									+ ",addr_complement,addr_code_postal,addr_ville,addr_pays,preference,bloque,tentatives,temps) "
									+ "VALUES ( '%s', '%s','%s', '%s', '%s','%s', '%s', '%s','%s', '%s', '%s','%s','%s','%s','%s');",
							civilite.ordinal(), nom, prenom, email, password, naissance, addr_rue, addr_complement,
							addr_code_postal, ville, pays, preference.ordinal(),0,0,0);
					
					System.out.println(insert_query);
					
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
