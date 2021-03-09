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
	

	
	Statement stmt, stmt2,stmt3;
	ResultSet rs,rs2, rs3;
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
			
			stmt.close();
			connexion.close();
			System.out.println("connection fermé");
			
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
			
			stmt.close();
			connexion.close();
		
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
			
			stmt.close();
			connexion.close();
		
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
			
			stmt.close();
			connexion.close();
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
		return catalogueElements;
	}
	
	
	/*
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
			
			stmt.close();
			stmt2.close();
			connexion.close();
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
		return catalogueElements;
	}
	*/
	
	public List<ElementDeCatalogue> titreBelongingToAnAlbum(String titreAlbum)
	{
	
		int nombreEcoutePeriode;
		int id ;
		
		try {
				connexion = DBManager.getInstance().getConnection();
				
				if (connexion != null)
				{
				 stmt = connexion.createStatement();
				 rs = stmt.executeQuery("select Titres.titre, Titres.interprete from Titres,Albums,lienAlbumTitres where Titres.id = lienAlbumTitres.idTitre AND lienAlbumTitres.idAlbum = Albums.id AND Albums.titre = '"+titreAlbum+"'");	 
				}
				// Itérer sur le resultSet : 
				while (rs.next()) {
				   String titre = rs.getString("titre");
				   String interprete = rs.getString("interprete");
				   catalogueElements.add(new Album(titre,interprete));					
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
			
			stmt.close();
			connexion.close();
		
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
			stmt = connexion.createStatement();
			stmt3 = connexion.createStatement();
			stmt4 = connexion.createStatement();
			stmt5 = connexion.createStatement();
			
			if (connexion != null)
			{
		
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
			
			stmt.close();
			stmt3.close();
			stmt4.close();
			stmt5.close();
			connexion.close();
		
		}catch(SQLException e) {
			System.out.println(e);
		
		}
		
	
	}
	
	//select Titres.titre from Titres,lienPlaylistPersoTitres,lienPlaylistPersoMembre, membres, PlaylistPerso where Titres.id = lienPlaylistPersoTitres.idTitre AND PlaylistPerso.id = lienPlaylistPersoTitres.idPlaylistPerso AND lienPlaylistPersoMembre.idPlaylistPerso = PlaylistPerso.id and lienPlaylistPersoMembre.idMembre = membres.id and membres.id = idMembre
	
	public void newElementInPrivatePlaylist(String nomPlaylistPerso, String section, String titre )
	{
		ResultSet rs3, rs4;
		Statement stmt3,stmt4, stmt5, stmt6;
		try {
			connexion = DBManager.getInstance().getConnection();
			stmt = connexion.createStatement();
			stmt3 = connexion.createStatement();
			stmt4 = connexion.createStatement();
			stmt5 = connexion.createStatement();
			stmt6 = connexion.createStatement();
			
			if (connexion != null)
			{
			 
			 System.out.println("ok");
			 rs3 = stmt.executeQuery("select id from PlaylistPerso where nom like'%"+nomPlaylistPerso+"%'");
			 System.out.println("ok");
			 rs4 = stmt3.executeQuery("select id from "+section+" where titre like '%"+titre+"%'");
			 
			 rs3.next();
			 rs4.next();
			 int idPlaylistPerso = rs3.getInt("id");
			 int id = rs4.getInt("id");
			 
			 stmt5.executeUpdate("INSERT INTO lienPlaylistPerso"+section+" VALUES(("+idPlaylistPerso+"),("+id+"))");
			 
			}
			
			stmt.close();
			stmt3.close();
			stmt4.close();
			stmt5.close();
			connexion.close(); 
		
		}catch(SQLException e) {
		System.out.println("rrrooooooooh"+e);
		
		}
		
	}
	
	
	public List<ElementDeCatalogue>  getAllElementsFromPlaylistPerso(int idMembre, String nomPlaylistPerso)
	{
		List<ElementDeCatalogue> check;
		
		try {
			connexion = DBManager.getInstance().getConnection();
			
			if (connexion != null)
			{
			 stmt = connexion.createStatement();
			 stmt3 = connexion.createStatement();
			 //rs = stmt.executeQuery("Select Titres.titre, Titres.interprete, Titres.id, Titres.nbEcoutePeriode from Titres,lienPlaylistPersoTitres,lienPlaylistPersoMembre, membres, PlaylistPerso where Titres.id = lienPlaylistPersoTitres.idTitre AND PlaylistPerso.id = lienPlaylistPersoTitres.idPlaylistPerso AND lienPlaylistPersoMembre.idPlaylistPerso = PlaylistPerso.id and lienPlaylistPersoMembre.idMembre = membres.id and membres.id ="+idMembre+" AND PlaylistPerso.nom = '"+nomPlaylistPerso+"'");
			  rs = stmt.executeQuery("Select DISTINCT Titres.titre  from Titres,lienPlaylistPersoTitres,lienPlaylistPersoMembre,membres, PlaylistPerso, Albums where \n"
			  		+ "\n"
			  		+ "Titres.id = lienPlaylistPersoTitres.idTitres AND PlaylistPerso.id = lienPlaylistPersoTitres.idPlaylistPerso AND \n"
			  		+ "\n"
			  		+ "lienPlaylistPersoMembre.idPlaylistPerso = PlaylistPerso.id AND lienPlaylistPersoMembre.idMembre = membres.id AND \n"
			  		+ "membres.id = "+idMembre+" AND PlaylistPerso.nom = '"+nomPlaylistPerso+"'");
			  
			  rs2 = stmt3.executeQuery("Select Albums.titre, Albums.interprete from lienPlaylistPersoAlbums,PlaylistPerso,lienPlaylistPersoMembre, Albums,membres where \n"
			 		+ "\n"
			 		+ "PlaylistPerso.id = lienPlaylistPersoAlbums.idPlaylistPerso AND lienPlaylistPersoAlbums.idAlbums = Albums.id  AND \n"
			 		+ "\n"
			 		+ "lienPlaylistPersoMembre.idPlaylistPerso = PlaylistPerso.id AND lienPlaylistPersoMembre.idMembre = membres.id AND \n"
			 		+ "\n"
			 		+ "membres.id ="+idMembre+" AND PlaylistPerso.nom = '"+nomPlaylistPerso+"'");
			}
			
			while (rs.next()) {
				
				//String titre, String interprete, int nombreDecoute, int id
				// je ne récupère que les elements avec les attributs de la classe mère , les spécification de chaque classe ne sont pas interessantes ici 
				//int id = rs.getInt("id");
				String titre = rs.getString("titre");
				//String interprete = rs.getString("interprete");
				//int nombreEcoutePeriode = rs.getInt("nbEcoutePeriode");
				
				//titre, interprete, nombreDecoute, type, annee, duree, idElement
				System.out.println((new Titre(titre)).getTitre());
				catalogueElements.add(new Titre(titre));	
			}
			
			while (rs2.next()) {
				
				System.out.println("yes yes");
				//String titre, String interprete, int nombreDecoute, int id
				// je ne récupère que les elements avec les attributs de la classe mère , les spécification de chaque classe ne sont pas interessantes ici 

				String titre = rs2.getString("titre");
				String interprete= rs2.getString("interprete");
				
				
				//titre, interprete, nombreDecoute, type, annee, duree, idElement
				System.out.println((new ElementDeCatalogue(titre)).getTitre());
				catalogueElements.add(new Album(titre,interprete));
			}
			
			
			stmt.close();
			stmt3.close();
			connexion.close();
		
		}catch(SQLException e) {
		System.out.println(e);
		
		}
		
		return catalogueElements;
		
	}
	
	
	public List<ElementDeCatalogue> getAllElementsFromPlaylistPublic()
	{
		try {
			
			connexion = DBManager.getInstance().getConnection();
			stmt = connexion.createStatement();
			stmt2 = connexion.createStatement();
			
			if(connexion!= null)
			{
				rs = stmt.executeQuery("SELECT Albums.titre, Albums.interprete from Albums where Albums.inPb = 1");
				rs2 = stmt2.executeQuery("SELECT Titres.titre, Titres.interprete from Titres where Titres.inPb = 1");
			}
			
			while (rs.next()) {
				
				String titre = rs.getString("titre");
				String interprete = rs.getString("interprete");	
				
				catalogueElements.add(new Album(titre,interprete));
			}
			
			while (rs2.next()) { 

				String titre = rs2.getString("titre");
				String interprete= rs2.getString("interprete");
				
				catalogueElements.add(new Titre(titre,interprete));
			}
			
			stmt.close();
			stmt2.close();
			connexion.close();
			
		}catch(SQLException e) {
			
			System.out.println("rrrooooooooh"+e);
		}
		
		return catalogueElements;
	}
	
	
	public void deletePlaylistMembre(String nomPlaylist)
	{
		Statement stmt3,stmt4, stmt5, stmt6, stmt7;
		try {
			connexion = DBManager.getInstance().getConnection();
			stmt3 = connexion.createStatement();
			stmt4 = connexion.createStatement();
			stmt5 = connexion.createStatement();
			stmt6 = connexion.createStatement();
			stmt7 = connexion.createStatement();
			
			if (connexion != null)
			{
			 rs = stmt7.executeQuery("select id from PlaylistPerso where nom ='"+nomPlaylist+"'");
			 rs.next();
			 int id = rs.getInt("id");
			 stmt3.executeUpdate("delete from PlaylistPerso where id ="+id+"");
			 stmt4.executeUpdate("delete from lienPlaylistPersoTitres where idPlaylistPerso ="+id+"");
			 stmt5.executeUpdate("delete from lienPlaylistPersoAlbums where idPlaylistPerso = "+id+"");
			 stmt6.executeUpdate("delete from lienPlaylistPersoMembre where idPlaylistPerso = "+id+"");
			}
			
			stmt3.close();
			stmt4.close();
			stmt5.close();
			stmt6.close();
			stmt7.close();
			connexion.close(); 
		
		}catch(SQLException e) {
		System.out.println("rrrooooooooh"+e);
		
		}
	}	
	
	
	public void deleteTitreFromPlaylistPerso(int idMembre,String titre, String titrePlaylistPerso)
	{
		Statement stmt2, stmt4;
		try {
			
			connexion = DBManager.getInstance().getConnection();
			
			stmt = connexion.createStatement();
			stmt2 = connexion.createStatement();
			stmt3 = connexion.createStatement();
			stmt4 = connexion.createStatement();
		
			if (connexion != null)
			{
				 rs = stmt.executeQuery("select PlaylistPerso.id from PlaylistPerso,lienPlaylistPersoMembre, membres where PlaylistPerso.id = lienPlaylistPersoMembre.idPlaylistPerso  AND lienPlaylistPersoMembre.idMembre = membres.id AND membres.id ="+idMembre+" AND PlaylistPerso.nom = '"+titrePlaylistPerso+"'");
				 rs2 = stmt2.executeQuery("select id from Titres where titre ='"+titre+"'");
				 rs3 = stmt3.executeQuery("select id from Albums where titre ='"+titre+"'");
				 
				 rs.next();
				
				 int idPlaylistPerso = rs.getInt("id");
				 System.out.println("je suis avant ?");
				 
				 if(rs2.next())
				 {
					 //rs2.beforeFirst();
					 int idTitre= rs2.getInt("id");
					 System.out.println("ok l'id de titre dans Titres vaut:"+idTitre);
					 stmt4.executeUpdate("delete from lienPlaylistPersoTitres where lienPlaylistPersoTitres.idPlaylistPerso = "+idPlaylistPerso+" and lienPlaylistPersoTitres.idTitres ="+idTitre+"");
					 
				 }else if(rs3.next())
				 {
					 //rs3.beforeFirst();
					 int idAlbum= rs3.getInt("id");
					 System.out.println("ok l'id de titre dans album vaut:"+idAlbum);
					 stmt4.executeUpdate("delete from lienPlaylistPersoAlbums where lienPlaylistPersoAlbums.idPlaylistPerso = "+idPlaylistPerso+" and lienPlaylistPersoAlbums.idAlbums ="+idAlbum+"");
				 }
			}
			
			stmt.close();
			stmt2.close();
			stmt3.close();
			stmt4.close();
			connexion.close(); 
		
		}catch(SQLException e) {
		System.out.println("rrrooooooooh"+e);
		
		}
	}
	
}	
	
