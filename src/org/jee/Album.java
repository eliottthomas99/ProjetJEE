package org.jee;

import java.util.ArrayList;

public class Album extends ElementDeCatalogue {
	public Album(String titre, String interprete, int nombreDecoute) {
		super(titre, interprete, nombreDecoute);
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Titre> listeTitre;
	public String interprete;
	public int annee;
	public int duree;

}
