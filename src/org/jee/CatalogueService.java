package org.jee;

import java.util.List;

public interface CatalogueService {

	
	// Récupère tout les titres musicaux
	public List<ElementDeCatalogue> getAllTitres();
	
	
	// Récupère tout les albums
	public List<ElementDeCatalogue> getAllAlbums();
	
	
	public List<ElementDeCatalogue> getAllPodcasts();
	
	
	public List<ElementDeCatalogue> getAllRadios();
	
	
	public List<ElementDeCatalogue> getElementByTitle(String search);	
		

}
