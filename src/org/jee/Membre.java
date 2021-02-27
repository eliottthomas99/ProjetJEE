package org.jee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Membre {

	private int id;

	private MainServlet.civilite civilite;

	private String nom;

	private String prenom;

	private String email;

	private String password;

	private String naissance;

	private String addr_rue;

	private String addr_complement;

	private int addr_code_postal;

	private String ville;

	private String pays;

	private MainServlet.preference preference;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public org.jee.MainServlet.civilite getCivilite() {
		return civilite;
	}

	public void setCivilite(org.jee.MainServlet.civilite civilite) {
		this.civilite = civilite;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNaissance() {
		return naissance;
	}

	public void setNaissance(String naissance) {
		this.naissance = naissance;
	}

	public String getAddr_rue() {
		return addr_rue;
	}

	public void setAddr_rue(String addr_rue) {
		this.addr_rue = addr_rue;
	}

	public String getAddr_complement() {
		return addr_complement;
	}

	public void setAddr_complement(String addr_complement) {
		this.addr_complement = addr_complement;
	}

	public int getAddr_code_postal() {
		return addr_code_postal;
	}

	public void setAddr_code_postal(int addr_code_postal) {
		this.addr_code_postal = addr_code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public org.jee.MainServlet.preference getPreference() {
		return preference;
	}

	public void setPreference(org.jee.MainServlet.preference preference) {
		this.preference = preference;
	}

	public Membre(int id, org.jee.MainServlet.civilite civilite, String nom, String prenom, String email, String password, String naissance,
			String addr_rue, String addr_complement, int addr_code_postal, String ville, String pays, org.jee.MainServlet.preference preference) {
		super();
		this.id = id;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.naissance = naissance;
		this.addr_rue = addr_rue;
		this.addr_complement = addr_complement;
		this.addr_code_postal = addr_code_postal;
		this.ville = ville;
		this.pays = pays;
		this.preference = preference;
	}

	public Membre(int id, org.jee.MainServlet.civilite civilite, String nom, String prenom, String email, String password, String naissance,
			String addr_rue, int addr_code_postal, String ville, String pays, org.jee.MainServlet.preference preference) {
		super();
		this.id = id;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.naissance = naissance;
		this.addr_rue = addr_rue;
		this.addr_code_postal = addr_code_postal;
		this.ville = ville;
		this.pays = pays;
		this.preference = preference;
	}

	public Membre(int id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;

	}

	public Boolean validerAuthentification(String email, String password) {
		/*
		 * On regarde s'il exite quelqu'un avec la bonne combinaison id/mdp
		 * 
		 * 
		 * */
		
		
		Boolean status = false;

		Connection connexion = DBManager.getInstance().getConnection();

		// Créer un java.sql.Statement à partir de cette connexion en utilisant:
		try (Statement stmt = connexion.createStatement()) {
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String query = "select * from membres where email='" + email + "' and password='" + password + "';";
			//System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);

			int idV = -1;
			int civiliteV = -1;
			String nomV  = "-1";
			String prenomV = "-1";
			String emailV = "-1" ;
			String passwordV = "-1";
			String naissanceV = "1900-01-01";
			String addr_rueV = "-1";
			String addr_complementV = "-1";
			int addr_code_postalV =-1;
			String villeV = "-1";
			String paysV = "-1";
			int preferenceV = -1;

			int rowCount = 0;
			while (rs.next()) {
				rowCount++;

				idV = rs.getInt("id");
				civiliteV = rs.getInt("civilite");
				nomV = rs.getString("nom");
				prenomV = rs.getString("prenom");
				emailV = rs.getString("email");
				passwordV = rs.getString("password");
				naissanceV = rs.getString("naissance");
				addr_rueV = rs.getString("addr_rue");
				addr_complementV = rs.getString("addr_complement");
				addr_code_postalV = rs.getInt("addr_code_postal");
				villeV = rs.getString("addr_ville");
				paysV = rs.getString("addr_pays");
				preferenceV = rs.getInt("preference");

			}

			//System.out.println(idV + " " + civiliteV + " " + nomV + " " + prenomV+" "+naissanceV);
			//System.out.println("Total number of rows in ResultSet object = " + rowCount);

			if (rowCount == 1) {
				status = true;
			}
		} catch (SQLException e) {
			System.out.println(e);

		}

		return status;

	}

}
