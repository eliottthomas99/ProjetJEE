package org.jee;

public class ElementDeCatalogue {
	public String titre;
	public String interprete;
	public int nombreDecoute;
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
	public ElementDeCatalogue(String titre, String interprete, int nombreDecoute) {
		super();
		this.titre = titre;
		this.interprete = interprete;
		this.nombreDecoute = nombreDecoute;
	}
	public int getNombreDecoute() {
		return nombreDecoute;
	}
	public void setNombreDecoute(int nombreDecoute) {
		this.nombreDecoute = nombreDecoute;
	} 

}
