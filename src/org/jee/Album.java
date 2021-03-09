package org.jee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Album extends ElementDeCatalogue {
	
	
	private List<Titre> listeTitre;
	//private String interprete; // on en a besoin dans album ça ? il est defini dans la classe mère mais je lui donne un autre nom alors
	String interpreteBis;
	private Date annee;
	private int duree;
	private String type;
	private int id;
	private int idElement;
	
	public Album(String titre) 
	{
		super(titre);
	}
	
	
	public Album(String titre,String interprete) 
	{
		super(titre,interprete);
	}
	// Constructeur utile à l'affichage de tout le catalogue 
	public Album(String titre, String interprete, int nombreDecoute, int id)
	{
		super(titre, interprete, nombreDecoute, id);
	}
	
	// Constructeur utile à l'affichage de l'élement propre en question 
	public Album(String titre, String interprete, int nombreDecoute,String type, Date annee, int duree, int idElement ) {
		super(titre, interprete, nombreDecoute,0);
		this.annee = annee;
		this.duree = duree;
		this.idElement = idElement;
		// TODO Auto-generated constructor stub
	}

	public List<Titre> getListeTitre() {
		return listeTitre;
	}

	public void setListeTitre(List<Titre> listeTitre) {
		this.listeTitre = listeTitre;
	}

	public String getInterpreteBis() {
		return interpreteBis;
	}

	public void setInterpreteBis(String interpreteBis) {
		this.interpreteBis = interpreteBis;
	}

	public Date getAnnee() {
		return annee;
	}

	public void setAnnee(Date annee) {
		this.annee = annee;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	

}
