package org.jee;

public class Radio extends ElementDeCatalogue{
	public String genre;
	public int NbEcoutePeriode;
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
}
