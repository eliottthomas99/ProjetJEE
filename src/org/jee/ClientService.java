package org.jee;

import java.util.List;

import org.jee.Membre;

public interface ClientService {
	public List<Membre>  getAllMembre();
	
	//public List<Membre> getMembreByTitle(String searchText);
}
