package org.jee;

import java.sql.Connection;
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
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				membres.add(new Membre(compteur,nom,prenom));
				compteur +=1;
				
			}
			
		}catch(SQLException e) {
			System.out.println(e);
			
		}
		
		
		return membres;
		
		
	
	}

}
