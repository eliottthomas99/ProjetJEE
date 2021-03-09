package org.jee;

import java.util.List;

import javax.lang.model.element.Element;

public class PlaylistPerso extends Playlist {

	protected int id;
	protected List<Titre> titre;
	
	
	public PlaylistPerso(int id, int duree, List<Element> elem, String nom,List<Titre> titre ) {
		super(id, duree, elem, nom);
		this.titre = titre;
		// TODO Auto-generated constructor stub
	}
	
	public PlaylistPerso(int id,String nom)
	{
		super(id,nom);
	}

}
