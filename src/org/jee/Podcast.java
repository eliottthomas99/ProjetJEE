package org.jee;

public class Podcast extends ElementDeCatalogue {
	public int duree;
	public String categorie;
	public int NbEcoutePeriode;
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
		return NbEcoutePeriode;
	}
	public void setNbEcoutePeriode(int nbEcoutePeriode) {
		NbEcoutePeriode = nbEcoutePeriode;
	}
}
