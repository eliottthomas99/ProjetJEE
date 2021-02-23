package org.jee;

import java.util.ArrayList;

public class Playlist {
	protected ArrayList<ElementDeCatalogue> list;
	protected String nom;
	protected int duree;
	protected int id;
	protected ArrayList<Titre> listTitre;
	public ArrayList<ElementDeCatalogue> getList() {
		return list;
	}
	public void setList(ArrayList<ElementDeCatalogue> list) {
		this.list = list;
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
}
