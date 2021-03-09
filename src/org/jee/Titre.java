package org.jee;

import java.sql.Date;

public class Titre extends ElementDeCatalogue {
	
	
	/* Pourquoi public ? aucune classe en a besoin et c'est plus safe */
	private int dureeTotale;
	private int id;
	private int idElement;
	private String genre;
	/* Les varaibles toujours en minuscule la première lettre */
	private int nbEcoutePeriode;
	/* Je rajoute les attributs que tu n'as pas ajouté */
	private Date anneeCreation;
	//boolean refAlbum;
	
	
	
	// 2 constructeurs utiles lors de l'afichage de tout les elements du catalogue.
	// Le  deuxième constructeur sera utile quand on voudra affichage des elements sepcifiques 
	
	public Titre(String titre)
	{
		super(titre);
	}
	
	public Titre(String titre, String interprete)
	{
		super(titre,interprete);
	}
	
	public Titre(String titre, String interprete, int nombreDecoute, int id)
	{
		super(titre, interprete, nombreDecoute,id);
	}
	
	public Titre(String titre, String interprete,int nbEcoutePeriode, int idElement,String genre, int dureeTotale, Date anneeCreationm) {
		super(titre, interprete, nbEcoutePeriode,0);
		
		/* Tu as oublié les attributs propre à la classe fille */
		this.dureeTotale = dureeTotale;
		this.genre = genre;
		this.anneeCreation = anneeCreation;
		this.nbEcoutePeriode = nbEcoutePeriode;
		this.idElement = idElement;
		//this.refAlbum = refAlbum;
			// TODO Auto-generated constructor stub
	}

	public int getDureeTotale() {
		return dureeTotale;
	}

	public void setDureeTotale(int dureeTotale) {
		this.dureeTotale = dureeTotale;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdElement() {
		return idElement;
	}

	public void setIdElement(int idElement) {
		this.idElement = idElement;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getNbEcoutePeriode() {
		return nbEcoutePeriode;
	}

	public void setNbEcoutePeriode(int nbEcoutePeriode) {
		this.nbEcoutePeriode = nbEcoutePeriode;
	}

	public Date getAnneeCreation() {
		return anneeCreation;
	}

	public void setAnneeCreation(Date anneeCreation) {
		this.anneeCreation = anneeCreation;
	}

	

	


	
}
