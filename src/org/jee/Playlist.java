package org.jee;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Element;

public class Playlist {
	
	
	protected List <Element> titre;
	
	protected String nom;
	
	protected int duree;
	
	protected int id;
	
	
	// Constructeur 
	
	public Playlist(int id,int duree, List <Element> titre, String nom)
	{
		this.id = id;
		this.duree = duree;
		this.titre = titre;
		this.nom = nom;
	}
	
	public Playlist(int id, String nom)
	{
		this.id = id;
		this.nom = nom;
	}


	public List<Element> getTitre() {
		return titre;
	}


	public void setTitre(List<Element> titre) {
		this.titre = titre;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getDuree() {
		return duree;
	}


	public void setDuree(int duree) {
		this.duree = duree;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
