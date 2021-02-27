package org.jee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Catalogue {
	
	
	// Cette classe n'est pas utile ( du moins pour l'instant ) 
	
	
	private ArrayList<ElementDeCatalogue> listeElements;

	public ArrayList<ElementDeCatalogue> getListeElements() {
		return listeElements;
	}

	public void setListeElements(ArrayList<ElementDeCatalogue> listeElements) {
		this.listeElements = listeElements;
	}
	
	public List<ElementDeCatalogue> rechecheMotCle(String arg){
		
		Connection connexion = DBManager.getInstance().getConnection();
		List<ElementDeCatalogue> liste = new ArrayList<>();
		
		try(Statement stmt = connexion.createStatement()){
			//  Exécuter la requête SQL  et récupérer un java.sql.ResultSet
			ResultSet rs = stmt.executeQuery("select * from ElementDeCatalogue where titre like %"+arg+"% ;"); //A changer
			while(rs.next()) {
				String titre = rs.getString("titre");
				String interprete = rs.getString("interprete");
				int nbrEcoute = rs.getInt("nbrEcoute");
				liste.add(new ElementDeCatalogue(titre,interprete,nbrEcoute));
			}
			
		}catch(SQLException e) {
			System.out.println(e);
			
		}
		return liste;
	}
}
