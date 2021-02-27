package org.jee;

public class Podcast extends ElementDeCatalogue {
	
	
	private int duree;
	private String categorie;
	private int nbEcoutePeriode;
	private int id;
	private int idElement;
	
	
	public Podcast(String titre, String interprete, int nombreEcoute,int id)
	{
		super(titre, interprete, nombreEcoute, id);
	}
	
	public Podcast(String titre, String interprete, int nombreEcoute ,int duree,String categorie,int nbEcoutePeriode, int idElement) {
		super(titre, interprete, nombreEcoute,0);
		// TODO Auto-generated constructor stub
		this.duree = duree;
		this.categorie = categorie;
		this.nbEcoutePeriode = nbEcoutePeriode;
		this.idElement = idElement;
	}

	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	public int getNbEcoutePeriode() {
		return nbEcoutePeriode;
	}


	public void setNbEcoutePeriode(int nbEcoutePeriode) {
		this.nbEcoutePeriode = nbEcoutePeriode;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
