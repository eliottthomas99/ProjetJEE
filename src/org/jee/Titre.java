package org.jee;

import java.sql.Date;

public class Titre extends ElementDeCatalogue {
	public Date AnneDeCreation;
	public Date getAnneDeCreation() {
		return AnneDeCreation;
	}
	public void setAnneDeCreation(Date anneDeCreation) {
		AnneDeCreation = anneDeCreation;
	}
	public int getDureeTotale() {
		return dureeTotale;
	}
	public void setDureeTotale(int dureeTotale) {
		this.dureeTotale = dureeTotale;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getNbEcoutePeriode() {
		return NbEcoutePeriode;
	}
	public void setNbEcoutePeriode(int nbEcoutePeriode) {
		NbEcoutePeriode = nbEcoutePeriode;
	}
	public int dureeTotale;
	public String genre;
	public int NbEcoutePeriode;
}
