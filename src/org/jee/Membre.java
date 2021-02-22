package org.jee;

import java.sql.Date;

public class Membre {

	private int id;
	
	private int civilite;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	private String password;
	
	private Date naissance;
	
	private String addr_rue;
	
	private String addr_complement;
	
	private int addr_code_postal;
	
	private String ville;
	
	private String pays;
	
	private int preference;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCivilite() {
		return civilite;
	}

	public void setCivilite(int civilite) {
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

	public Date getNaissance() {
		return naissance;
	}

	public void setNaissance(Date naissance) {
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

	public int getPreference() {
		return preference;
	}

	public void setPreference(int preference) {
		this.preference = preference;
	}

	public Membre(int id, int civilite, String nom, String prenom, String email, String password, Date naissance,
			String addr_rue, String addr_complement, int addr_code_postal, String ville, String pays, int preference) {
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

	public Membre(int id, int civilite, String nom, String prenom, String email, String password, Date naissance,
			String addr_rue, int addr_code_postal, String ville, String pays, int preference) {
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

	public Membre(int id , String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		
	}
	
	
	
}
