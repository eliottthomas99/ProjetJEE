package org.jee;

import java.util.List;

public class ElementDeCatalogue {
	
	// Pourquoi public ? protected suffit seules les classes filles en ont besoin 
	protected String titre;
	protected String interprete;
	protected int nombreDecoute;
	protected int id;
	
	
	public ElementDeCatalogue(String titre, String interprete, int nombreDecoute, int id) {
		
		//super(); ?? Je comprends pas de qui hérite cette classe ... 
		this.titre = titre;
		this.interprete = interprete;
		this.nombreDecoute = nombreDecoute;
		this.id = id;
	}
	
	public ElementDeCatalogue(String titre, String interprete) {
		
		//super(); ?? Je comprends pas de qui hérite cette classe ... 
		this.titre = titre;
		this.interprete = interprete;
	}
	
	public ElementDeCatalogue(String titre) {
		
		//super(); ?? Je comprends pas de qui hérite cette classe ... 
		this.titre = titre;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getInterprete() {
		return interprete;
	}
	
	public void setInterprete(String interprete) {
		this.interprete = interprete;
	}
	
	public int getNombreDecoute() {
		return nombreDecoute;
	}
	
	public void setNombreDecoute(int nombreDecoute) {
		this.nombreDecoute = nombreDecoute;
	} 
	
}
