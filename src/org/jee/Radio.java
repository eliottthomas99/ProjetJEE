package org.jee;

public class Radio extends ElementDeCatalogue{
	
	public String genre;
	private int id;
	public int nbEcoutePeriode;
	private int idElement;
	
	
	public Radio(String titre, String interprete, int nombreDecoute,int id)
	{
		super(titre, interprete, nombreDecoute, id);
	}
	
	public Radio(String titre, String interprete, int nombreDecoute, String genre, int nbEcoutePeriode,int idElement) {
		
		super(titre, interprete, nombreDecoute,0);
		this.genre = genre ;
		this.nbEcoutePeriode = nbEcoutePeriode;
		this.idElement = idElement;
		// TODO Auto-generated constructor stub
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbEcoutePeriode() {
		return nbEcoutePeriode;
	}

	public void setNbEcoutePeriode(int nbEcoutePeriode) {
		this.nbEcoutePeriode = nbEcoutePeriode;
	}

	public int getIdElement() {
		return idElement;
	}

	public void setIdElement(int idElement) {
		this.idElement = idElement;
	}



	
	
}
