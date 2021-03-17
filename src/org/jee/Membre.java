package org.jee;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Membre {

	@Override
	public String toString() {
		return "Membre [id=" + id + ", civilite=" + civilite + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", password=" + password + ", naissance=" + naissance + ", addr_rue=" + addr_rue
				+ ", addr_complement=" + addr_complement + ", addr_code_postal=" + addr_code_postal + ", ville=" + ville
				+ ", pays=" + pays + ", preference=" + preference + ", bloque=" + bloque + ", tentatives=" + tentatives
				+ ", temps=" + temps + "]";
	}


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
	
	private int bloque;
	
	private int tentatives;
	
	private long temps;
	
	private int adminMusique;
	
	private int adminCompte; 

	
	
	public int getTentatives() {
		return tentatives;
	}

	public void setTentatives(int tentatives) {
		this.tentatives = tentatives;
	}

	public long getTemps() {
		return temps;
	}

	public void setTemps(long temps) {
		this.temps = temps;
	}

	public int getAdminMusique() {
		return adminMusique;
	}

	public void setAdminMusique(int adminMusique) {
		this.adminMusique = adminMusique;
	}

	public int getAdminCompte() {
		return adminCompte;
	}

	public void setAdminCompte(int adminCompte) {
		this.adminCompte = adminCompte;
	}

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

	
	public int getBloque() {
		return bloque;
	}

	public void setBloque(int bloque) {
		this.bloque = bloque;
	}
	
	
	
	
	// Pour tester l'admin en dur !! 
	public Membre(int id, String prenom, int adminMusique)
	{
		super();
		this.id = id;
		this.adminMusique = adminMusique;
	}

	
	public Membre(int id, MainServlet.civilite civilite, String nom, String prenom, String email, String password, String naissance,
			String addr_rue, String addr_complement, int addr_code_postal, String ville, String pays, MainServlet.preference preference) {
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
		this.setBloque(0);
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

	public Membre() {
		// TODO Auto-generated constructor stub
	}

	public static Boolean validerAuthentification(String email, String password) throws InterruptedException, NoSuchAlgorithmException, InvalidKeySpecException {
		/*
		 * On regarde s'il exite quelqu'un avec la bonne combinaison id/mdp
		 * 
		 * 
		 * */
		
		
		Boolean status = false; // le booleen qui précise si la combinaison est validee

		Connection connexion = DBManager.getInstance().getConnection(); // on se connecte à la BDD
		
		String passwordHash = JavaMD5Hash.md5(password); // on hash le mot de passe fourni

		// Créer un java.sql.Statement à partir de cette connexion en utilisant:
		try (Statement stmt = connexion.createStatement()) {
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String query = "select * from membres where email='" + email + "' and password='" + passwordHash + "';";
			ResultSet rs = stmt.executeQuery(query);

			// initialisation des variables
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
			int bloqueV = -1;
			int tentativesV = -1;
			int tempsV = -1;
			
			int rowCount = 0;
			
			while (rs.next()) {
				rowCount++;

				// on récupère les informations dans la BDD
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
				bloqueV = rs.getInt("bloque");
				tentativesV = rs.getInt("tentatives");
				tempsV = rs.getInt("temps");
				
				
				
			}

			
			try (Statement stmt2 = connexion.createStatement()) { // on regarde si l'email existe

				String insert_query = "select * from membres where email='" + email + "';";
				ResultSet rs2 = stmt2.executeQuery(insert_query);
				
				int rowCount2 = 0;
				int bloqueV2 = 0;
				int tempsV2 = 0;
				while (rs2.next()) {
					bloqueV2 = rs2.getInt("bloque"); // on regarde si le compte a été bloque
					tempsV2 = rs2.getInt("temps"); // on regarde quand le compte a été bloqué
					rowCount2++;						
					
				}
				
				if(rowCount2==1) { // si l'email appartient à quelqu'un
					
					if( bloqueV2==1 ) { // si le compte est bloque
						
						int maintenant = (int)(System.currentTimeMillis()/1000); //on met en secondes le temps courant. Plus facile à stocker
						
						if(maintenant-tempsV2>=3600) { //si le temps de bloquage (1H)  est ecoulé
							
							//on passe bloque à 0
							insert_query = "UPDATE  membres SET bloque=0 where email='"+emailV+"';";
							int rs3 = stmt2.executeUpdate(insert_query);
							
							
						}
						else {
							//le compte reste bloqué
							return false;
						}
						
					}
					
					
					
					
				}
				
			} catch (SQLException e) {
				System.out.println(e);
			}
			
			
			
			
			
			
			if (rowCount == 1 ) { // si la combinaison est correcte
				
				
				
				
				
				
				
				if(bloqueV == 0) { // si le compte n'est pas bloqué
					
					status = true; // la combinaison est valide
					//ecrire dans la base				
					try (Statement stmt1 = connexion.createStatement()) {

						
						String insert_query = "UPDATE  membres SET tentatives=0 where email='"+emailV+"';"; // on remet à 0 le nombres de tentatives sur ce compte
						int rs2 = stmt1.executeUpdate(insert_query);
						
						
						

					} catch (SQLException e) {
						System.out.println(e);
					}
					
					
					
					
				}
				
			}
			else { //la combinaison n'exite pas
				
				//L'email existe il malgré tout ? 
				try (Statement stmt2 = connexion.createStatement()) {

					String insert_query = "select * from membres where email='" + email + "';";
					ResultSet rs2 = stmt2.executeQuery(insert_query);
					
					int rowCount2 = 0;
					int tentativesV2 = 0;
				
					while (rs2.next()) {
						tentativesV2 = rs2.getInt("tentatives"); // on veut voir combien de fois une personne tente de se connecter à ce compte
						
						rowCount2++;						
						
					}
					if(rowCount2==1) { //là il existe
						
						
						//mise à jour tentative et temps
						try (Statement stmt1 = connexion.createStatement()) {

							
							int calcul = tentativesV2+1;
							int cast = (int)(System.currentTimeMillis()/1000); //on met en secondes le temps actuel
							
							String insert_query2 = "UPDATE  membres SET tentatives="+calcul+" , temps="+ cast+"  where email='"+email+"';"; // on met à jour les tentatives et l'heure de la dernière tentative
							int rs3 = stmt1.executeUpdate(insert_query2);
							
							if(calcul>=3) { // au bout de 3 tentatives le compte est bloqué 
								String insert_query3 = "UPDATE  membres SET bloque=1  where email='"+email+"';";
								rs3 = stmt1.executeUpdate(insert_query3);
							}
														

						} catch (SQLException e) {
							System.out.println(e);
						}
								
					}
					
					

				} catch (SQLException e) {
					System.out.println(e);
				}
					
			}
		} catch (SQLException e) {
			System.out.println(e);

		}

		return status;

	}

	
	public static Boolean modifCompte(MainServlet.civilite civilite, String nom, String prenom, String naissance, String addr_rue, String addr_complement, int addr_code_postal,
			String ville, String pays, MainServlet.preference preference, String email) {

		Connection connexion = DBManager.getInstance().getConnection(); // on se connecte à la BDD

		try (Statement stmt = connexion.createStatement()) {

			// on modifie la BDD en fonction des entrées. Les vérifications éventuelles sont faites avant
			String insert_query = String.format(
					"UPDATE membres set civilite='%s',nom='%s',prenom='%s',naissance='%s',addr_rue='%s'"
							+ ",addr_complement='%s',addr_code_postal='%s',addr_ville='%s',addr_pays='%s',preference='%s'"
							+ "where email='%s';",
					civilite.ordinal(), nom, prenom , naissance, addr_rue, addr_complement,
					addr_code_postal, ville, pays, preference.ordinal(),email);
			
			
			
			int rs = stmt.executeUpdate(insert_query);
			return true;
		} catch (SQLException e) {
			
			System.out.println(e);
			return false;
		}
	}
	
	
	public static Membre getMembre(String emailP) {
		
		// Récupérer  le membre qui correspond à un email avec ses données dans la BDD
		
		Connection connexion = DBManager.getInstance().getConnection(); // on se connecte à la BDD
		
		Membre membre = null;
		
		//Créer un java.sql.Statement à partir de cette connexion en utilisant: 
		try(Statement stmt = connexion.createStatement()){
			//  Exécuter la requête SQL  et récupérer un java.sql.ResultSet
			ResultSet rs = stmt.executeQuery("select * from membres where email='"+emailP+ "' ;");
			
			// Itérer sur le resultSet : 
			while (rs.next()) {
				
				// on récupère les données dans la BDD
				int id = rs.getInt("id");
				MainServlet.civilite civilite = MainServlet.civilite.values()[rs.getInt("civilite")] ; // Peut ralentir les performance, à voir si cela nous gène
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String naissance = rs.getString("naissance");
				String addr_rue = rs.getString("addr_rue");
				String addr_complement = rs.getString("addr_complement");
				int addr_code_postal = rs.getInt("addr_code_postal");
				String ville = rs.getString("addr_ville");
				String pays = rs.getString("addr_pays");
				MainServlet.preference preference = MainServlet.preference.values()[rs.getInt("preference")];
				
				int adminMusique = rs.getInt("adminMusique");
				int adminCompte = rs.getInt("adminCompte");
				int bloque  = rs.getInt("bloque");
				
				
				// on crée un objet membre avec ces infos
				membre = new Membre(id,civilite,nom,prenom,email,password,naissance,addr_rue,addr_complement,addr_code_postal,ville,pays,preference);
				membre.setAdminMusique(adminMusique);
				membre.setAdminCompte(adminCompte);
				membre.setBloque(bloque);
			}
			
		}catch(SQLException e) {
			System.out.println(e);
			
		}
		
		return membre;
		
	}

			

	public static Boolean modifAvanceeCompte(String ancienEmail, String nouveauEmail, String nouveauMotDePasse) {

		// modification de l email et/ou du mot de passe d un compte
		
		Connection connexion = DBManager.getInstance().getConnection(); // on se connecte à la BDD

		//Vérifier que l'email est dispo
		Boolean emailDispo = AlgorithmeDeVerification.emailDispo(nouveauEmail) || nouveauEmail.equals(ancienEmail);
		
		if (emailDispo) {
			try (Statement stmt = connexion.createStatement()) {
	
				// on met à jour la BDD avec es données fournies. Les éventuelles vérifications sont faites avant l'appel de la fonction
				String insert_query = String.format(
						"UPDATE membres set email='%s',password='%s'"
								+ "where email='%s';",
						nouveauEmail, nouveauMotDePasse, ancienEmail);
								
				int rs = stmt.executeUpdate(insert_query);
				return true;
			} catch (SQLException e) {
				
				System.out.println(e);
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	
	public static Boolean supprimerCompte( String emailSup) {


		Connection connexion = DBManager.getInstance().getConnection(); // on se connecte à la BDD 

		try (Statement stmt = connexion.createStatement()) {
	
			String delete_query = String.format("DELETE FROM membres where email='%s';",emailSup); // on supprime le compte qui correspond à lemail fourni, les vérifications sont faites avant			
			int rs = stmt.executeUpdate(delete_query);
			return true;
			
		} catch (SQLException e) {
				
			System.out.println(e);
			return false;
		}
		
		
	}
			


}
