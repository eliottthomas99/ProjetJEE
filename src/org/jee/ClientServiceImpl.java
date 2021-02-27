package org.jee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jee.DBManager;
import org.jee.Membre;

public class ClientServiceImpl implements ClientService{

	

	@Override
	public List<Membre> getAllMembre() {
		// TODO Auto-generated method stub
		
		Connection connexion = DBManager.getInstance().getConnection();
		
		List<Membre> membres = new ArrayList<>();
		int compteur = 1;
		
		//Créer un java.sql.Statement à partir de cette connexion en utilisant: 
		try(Statement stmt = connexion.createStatement()){
			//  Exécuter la requête SQL  et récupérer un java.sql.ResultSet
			ResultSet rs = stmt.executeQuery("select * from membres");
			
			// Itérer sur le resultSet : 
			while (rs.next()) {
				
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
				
				membres.add(new Membre(id,civilite,nom,prenom,email,password,naissance,addr_rue,addr_complement,addr_code_postal,ville,pays,preference));
				
			}
			
		}catch(SQLException e) {
			System.out.println(e);
			
		}
		
		
		return membres;
		
		
	
	}

}
