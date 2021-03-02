package org.jee;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jee.DBManager;
import org.jee.ElementDeCatalogue;

public class CatalogueServiceImpl implements CatalogueService {
	
	
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++ PUSH D'ALEX ATTTTTENTIOOOON  ++++++++++++++++++++++++++++++++++++++++++++++++++++++
	Statement stmt, stmt2;
	ResultSet rs,rs2;
	Connection connexion;
	List<ElementDeCatalogue> catalogueElements = new ArrayList<>();
	
	
	
	public List<ElementDeCatalogue> getAllTitres()
	{
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 rs = stmt.executeQuery("select * from Titres");	
			}
			
			// Itérer sur le resultSet : 
			while (rs.next()) {
			
				//String titre, String interprete, int nombreDecoute, int id
				// je ne récupère que les elements avec les attributs de la classe mère , les spécification de chaque classe ne sont pas interessantes ici 
				int id = rs.getInt("id");
				String titre = rs.getString("titre");
				String interpret = rs.getString("interprete");
				int dureeTotale = rs.getInt("dureeTotale");
				int nbEcoutePeriode = rs.getInt("nbEcoutePeriode");
				String genre = rs.getString("type");
				Date dateCreation = rs.getDate("anneeCreation");
				
				catalogueElements.add(new Titre(titre, interpret, nbEcoutePeriode , id, genre, dureeTotale, dateCreation));
				//String titre, String interprete,int nbEcoutePeriode, int idElement,String genre, int dureeTotale, Date anneeCreation
			}
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
		return catalogueElements;
	}
	
	
	
	public List<ElementDeCatalogue> getAllAlbums()
	{
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 rs = stmt.executeQuery("select * from Albums");	
			}
			
			// Itérer sur le resultSet : 
			while (rs.next()) {
			
				//String titre, String interprete, int nombreDecoute, int id
				// je ne récupère que les elements avec les attributs de la classe mère , les spécification de chaque classe ne sont pas interessantes ici 
				int id = rs.getInt("id");
				String titre = rs.getString("titre");
				String interprete = rs.getString("interprete");
				int duree = rs.getInt("duree");
				int nombreDecoute = rs.getInt("nombreEcoute");
				String type = rs.getString("type");
				Date annee = rs.getDate("annee");
				
				//titre, interprete, nombreDecoute, type, annee, duree, idElement
				
				catalogueElements.add(new Album(titre, interprete, nombreDecoute ,type, annee, duree,id));			
			}
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
		return catalogueElements;
	}
	
	
	
	public List<ElementDeCatalogue> getAllPodcasts()
	{
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 rs = stmt.executeQuery("select * from Podcasts");	
			}
			
			// Itérer sur le resultSet : 
			while (rs.next()) {
			
				//String titre, String interprete, int nombreDecoute, int id
				// je ne récupère que les elements avec les attributs de la classe mère , les spécification de chaque classe ne sont pas interessantes ici 
				int id = rs.getInt("id");
				String titre = rs.getString("titre");
				String interprete = rs.getString("interprete");
				int duree = rs.getInt("duree");
				int nombreDecoute = rs.getInt("nombreEcoute");
				String categorie = rs.getString("categorie");
				int nombreEcoutePeriode = rs.getInt("nbEcoutePeriode");
				
				//titre, interprete, nombreDecoute, type, annee, duree, idElement
				
				catalogueElements.add(new Podcast(titre, interprete, nombreDecoute ,duree, categorie,nombreEcoutePeriode ,id));		
			}
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
		return catalogueElements;
	}
	
	

	public List<ElementDeCatalogue> getAllRadios()
	{
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 rs = stmt.executeQuery("select * from Radios");	
			}
			
			// Itérer sur le resultSet : 
			while (rs.next()) {
			
				//String titre, String interprete, int nombreDecoute, int id
				// je ne récupère que les elements avec les attributs de la classe mère , les spécification de chaque classe ne sont pas interessantes ici 
				int id = rs.getInt("id");
				String titre = rs.getString("titre");
				String interprete = rs.getString("interprete");
				String genre = rs.getString("genre");
				int nombreDecoute = rs.getInt("nombreEcoute");
				int nombreEcoutePeriode = rs.getInt("nbEcoutePeriode");
				
				//titre, interprete, nombreDecoute, type, annee, duree, idElement
				
				catalogueElements.add(new Radio(titre, interprete, nombreDecoute ,genre,nombreEcoutePeriode ,id));	
			}
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
		return catalogueElements;
	}
	
	
	
	public List<ElementDeCatalogue> getElementByTitle(String search)
	{
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 rs = stmt.executeQuery("select id,titre,interprete,nbEcoutePeriode from Titres where titre like '%"+search+"%'");	
			}
			
			// Itérer sur le resultSet : 
			while (rs.next()) {
			
				//String titre, String interprete, int nombreDecoute, int id
				// je ne récupère que les elements avec les attributs de la classe mère , les spécification de chaque classe ne sont pas interessantes ici 
				int id = rs.getInt("id");
				String titre = rs.getString("titre");
				String interprete = rs.getString("interprete");
				int nombreEcoutePeriode = rs.getInt("nbEcoutePeriode");
				
				//titre, interprete, nombreDecoute, type, annee, duree, idElement
				
				catalogueElements.add(new Titre(titre, interprete,nombreEcoutePeriode ,id));	
			}
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
		return catalogueElements;
	}
	
	
	public List<ElementDeCatalogue> getAllElementsOfCategorie(String table,String categorie)
	{
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 rs = stmt.executeQuery("select * from "+table+" where type ="+"'"+categorie+"'");	
			}
			
			// Itérer sur le resultSet : 
			while (rs.next()) {
			
				//String titre, String interprete, int nombreDecoute, int id
				// je ne récupère que les elements avec les attributs de la classe mère , les spécification de chaque classe ne sont pas interessantes ici 
				int id = rs.getInt("id");
				String titre = rs.getString("titre");
				String interprete = rs.getString("interprete");
				int nombreEcoutePeriode = rs.getInt("nbEcoutePeriode");
				
				//titre, interprete, nombreDecoute, type, annee, duree, idElement
				
				catalogueElements.add(new ElementDeCatalogue(titre, interprete,nombreEcoutePeriode ,id));	
			}
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
		return catalogueElements;
	}
	
	
	
	public List<ElementDeCatalogue> titreBelongingToAnAlbum(String inter)
	{
		String titre;
		String interprete;
		int nombreEcoutePeriode;
		int id ;
		
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 rs = stmt.executeQuery("select * from Titres where interprete ='"+inter+"'");	
			 
			}
			// Itérer sur le resultSet : 
			while (rs.next()) {
			 
				int refAlbum = rs.getInt("refAlbum");
				
				if(refAlbum != 0) // Ce titre apparetient à un album, mais lequel ? 
				{
					  stmt2 = connexion.createStatement();
					  rs2 = stmt2.executeQuery("select * from Albums where interprete ='"+inter+"'");
					  while (rs2.next()) {
						  if(refAlbum ==  rs2.getInt("id"))
						  {
							  	id = rs.getInt("id");
							    titre = rs.getString("titre");
								interprete = rs.getString("interprete");
								nombreEcoutePeriode = rs.getInt("nbEcoutePeriode");
								
								// On récupère alors le titre qui correspond à l'album 
								catalogueElements.add(new ElementDeCatalogue(titre, interprete,nombreEcoutePeriode ,id));
						  }  
					  }	  
				}
					
			}
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
		return catalogueElements;
	}
	
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++ PUSH D'ALEX ATTTTTENTIOOOON  ++++++++++++++++++++++++++++++++++++++++++++++++++++++

}
