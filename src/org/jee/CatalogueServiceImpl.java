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
	

	
	Statement stmt, stmt2;
	ResultSet rs,rs2;
	Connection connexion;
	List<ElementDeCatalogue> catalogueElements = new ArrayList<>();
	List<Playlist> titresPlaylists = new ArrayList<>();
	
	
	
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
	

	public List<Playlist> getTitresAlbumPersoMembre(int idMembre)
	{
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 rs = stmt.executeQuery("select*from PlaylistPerso,membres,lienPlaylistPersoMembre where PlaylistPerso.id = lienPlaylistPersoMembre.idPlaylistPerso AND lienPlaylistPersoMembre.idMembre = membres.id AND membres.id ="+idMembre);	
			}
			
			// Itérer sur le resultSet : 
			while (rs.next()) {
			
				//String titre, String interprete, int nombreDecoute, int id
				// je ne récupère que les elements avec les attributs de la classe mère , les spécification de chaque classe ne sont pas interessantes ici 
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
			
				//titre, interprete, nombreDecoute, type, annee, duree, idElement
				
				titresPlaylists.add(new PlaylistPerso(id,nom));	
			}
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
		return titresPlaylists;
	}
	
	public void newPlaylistMembre(String nomPlaylist,int idMembre)
	{		
		ResultSet rs3, rs4;
		Statement stmt3,stmt4, stmt5;
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 stmt3 = connexion.createStatement();
			 stmt4 = connexion.createStatement();
			 stmt5 = connexion.createStatement();
			 
			 System.out.println("ok");
			 stmt.executeUpdate("INSERT INTO PlaylistPerso (nom) VALUES ('"+nomPlaylist+"')");
			 System.out.println("ok");
			 rs3 = stmt3.executeQuery("SELECT id from PlaylistPerso WHERE nom ='"+nomPlaylist+"'");
			 System.out.println("pas ok");
			 rs4 = stmt4.executeQuery("SELECT id from membres WHERE id='"+idMembre+"'");
			 
			 rs3.next();
			 rs4.next();
			 int idPlaylistPerso = rs3.getInt("id");
			 int idDuMembre = rs4.getInt("id");
			 
			 stmt5.executeUpdate("INSERT INTO lienPlaylistPersoMembre VALUES(("+idPlaylistPerso+"),("+idDuMembre+"))");
			}
			
			// Itérer sur le resultSet : 
		
		}catch(SQLException e) {
			System.out.println(e);
		
		}
	}
	
	
	
	//select Titres.titre from Titres,lienPlaylistPersoTitre,lienPlaylistPersoMembre, membres, PlaylistPerso where Titres.id = lienPlaylistPersoTitre.idTitre AND PlaylistPerso.id = lienPlaylistPersoTitre.idPlaylistPerso AND lienPlaylistPersoMembre.idPlaylistPerso = PlaylistPerso.id and lienPlaylistPersoMembre.idMembre = membres.id and membres.id = idMembre
	
	

	public void newElementInPrivatePlaylist(String nomPlaylistPerso, String section, String titre)
	{
		ResultSet rs3, rs4;
		Statement stmt3,stmt4, stmt5;
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 stmt3 = connexion.createStatement();
			 stmt4 = connexion.createStatement();
			 stmt5 = connexion.createStatement();
			 
			 System.out.println("ok");
			 rs3 = stmt.executeQuery("select id from PlaylistPerso where nom like'%"+nomPlaylistPerso+"%'");
			 System.out.println("ok");
			 rs4 = stmt3.executeQuery("select id from "+section+" where titre like '%"+titre+"%'");
			 
			 rs3.next();
			 rs4.next();
			 int idPlaylistPerso = rs3.getInt("id");
			 int idTitre = rs4.getInt("id");
			 
			 stmt5.executeUpdate("INSERT INTO lienPlaylistPersoTitre VALUES(("+idPlaylistPerso+"),("+idTitre+"))");
			}
			
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
	}
	
	
	
	public List<ElementDeCatalogue>  getAllElementsFromPlaylistPerso(int idMembre, String nomPlaylistPerso)
	{
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 rs = stmt.executeQuery("Select Titres.titre, Titres.interprete, Titres.id, Titres.nbEcoutePeriode from Titres,lienPlaylistPersoTitre,lienPlaylistPersoMembre, membres, PlaylistPerso where Titres.id = lienPlaylistPersoTitre.idTitre AND PlaylistPerso.id = lienPlaylistPersoTitre.idPlaylistPerso AND lienPlaylistPersoMembre.idPlaylistPerso = PlaylistPerso.id and lienPlaylistPersoMembre.idMembre = membres.id and membres.id ="+idMembre+" AND PlaylistPerso.nom = '"+nomPlaylistPerso+"'");
			}
			
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
}	
	
