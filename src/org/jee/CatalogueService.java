package org.jee;

import java.util.List;

public interface CatalogueService {

	
	// Récupère tout les titres musicaux
	public List<ElementDeCatalogue> getAllTitres();
	
	// Récupère tout les albums
	public List<ElementDeCatalogue> getAllAlbums();
	
	// rechercher un titre
	public List<ElementDeCatalogue> getElementByTitle(String search);	
	
	
	
	
	// Récupérer des titres par catégories 
	
	public List<ElementDeCatalogue> getAllElementsOfCategorie(String table, String categorie);
	
	// Récupérer les titres d'un album 
	//( fictif dans la bdd ce n'est pas vraiment le cas ,
	// je check juste que ça soit le même artiste mais un titre peut ne pas faire partie de l'album normalement .. "
	
	public List<ElementDeCatalogue> titreBelongingToAnAlbum(String inter);
	
	
	public List<Playlist> getTitresAlbumPersoMembre(int idMembre);
	
	
	public void newPlaylistMembre(String nomPlaylist,int idMembre);
	
	
	public void newElementInPrivatePlaylist(String nomPlaylistPerso, String section, String nomChanteur);
	//public List<ElementDeCatalogue> getTitreRapRnb();
	
	
	public List<ElementDeCatalogue> getAllElementsFromPlaylistPerso(int idMembre, String nomPlaylistPerso);
	
	
	public void deletePlaylistMembre(String nomPlaylistPerso);
	
	
	public void deleteTitreFromPlaylistPerso(int idMembre,String titre, String titrePlaylistPerso);
	
	//public List<ElementDeCatalogue> getTitreClassique();
	
	//public List<ElementDeCatalogue> getTitreRock();
		

}
