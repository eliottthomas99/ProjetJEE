package org.jee;

import java.sql.SQLException;
import java.util.List;

public interface CatalogueService {

	
	// Récupère tout les titres musicaux
	public List<ElementDeCatalogue> getAllTitres() throws SQLException;
	
	// Récupère tout les albums
	public List<ElementDeCatalogue> getAllAlbums() throws SQLException;
	
	// rechercher un titre
	//public List<ElementDeCatalogue> getElementByTitle(String search);	
	
	
	
	
	// Récupérer des titres par catégories 
	
	public List<ElementDeCatalogue> getAllElementsOfCategorie(String table, String categorie) throws SQLException;
	
	// Récupérer les titres d'un album 
	//( fictif dans la bdd ce n'est pas vraiment le cas ,
	// je check juste que ça soit le même artiste mais un titre peut ne pas faire partie de l'album normalement .. "
	
	public List<ElementDeCatalogue> titreBelongingToAnAlbum(String inter) throws SQLException;
	
	
	public List<Playlist> getTitresAlbumPersoMembre(int idMembre) throws SQLException;
	
	
	public void newPlaylistMembre(String nomPlaylist,int idMembre) throws SQLException;
	
	
	public void newElementInPrivatePlaylist(String nomPlaylistPerso, String section, String nomChanteur) throws SQLException;
	//public List<ElementDeCatalogue> getTitreRapRnb();
	
	
	public List<ElementDeCatalogue> getAllElementsFromPlaylistPerso(int idMembre, String nomPlaylistPerso) throws SQLException;
	public List<String>  getAllElementsFromAllPlaylistPerso(int idMembre) throws SQLException;
	
	
	public void deletePlaylistMembre(String nomPlaylistPerso) throws SQLException;
	
	
	public void deleteTitreFromPlaylistPerso(int idMembre,String titre, String titrePlaylistPerso) throws SQLException;
	
	
	public List<ElementDeCatalogue> getAllElementsFromPlaylistPublic() throws SQLException;
	
	
	public List<ElementDeCatalogue> getElementByresearch(String search) throws SQLException;
	//public List<ElementDeCatalogue> getTitreClassique();
	
	//public List<ElementDeCatalogue> getTitreRock();
	
	public void addElementPlaylistPublic(String nomTitre,String interprete,String type) throws SQLException;
	
	public int isAdminCompte(int id) throws SQLException;
	
	public int isAdminMusique(int id) throws SQLException;
	
	public void addNewTitre(String nomTitre,String interprete) throws SQLException;
	
	public void addNewAlbum(String nomTitre,String interprete) throws SQLException;
	
	public void suppElementByAdmin(String categorie,String titre) throws SQLException;
	
	public void addNewTitreToAnAlbum(String titre,String interprete, String playlistActuelle) throws SQLException;
	
	public List<ElementDeCatalogue> getElementByresearchVisiteur(String search) throws SQLException;

}
