package org.jee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Visiteur {

	private String nom;
	private String prenom;

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

	public void CreerCompte(int civilite, String nom, String prenom, String email, String password,
			String naissance, String addr_rue, String addr_complement, int addr_code_postal, String ville, String pays,
			int preference) {

		Connection connexion = DBManager.getInstance().getConnection();
		
		try (Statement stmt = connexion.createStatement()) {
			
			String insert_query = String.format(
					"INSERT INTO membres(civilite,nom,prenom,email,password,naissance,addr_rue"
					+ ",addr_complement,addr_code_postal,addr_ville,addr_pays,preference) "
							+ "VALUES ( '%s', '%s','%s', '%s', '%s','%s', '%s', '%s','%s', '%s', '%s','%s')",
					civilite, nom, prenom, email, password, naissance, addr_rue, addr_complement, addr_code_postal,
					ville, pays, preference);
			int rs = stmt.executeUpdate(insert_query);
			
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		

	}

}
