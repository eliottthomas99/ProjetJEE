package org.jee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ModifCompte
 */
@WebServlet("/ModifCompte")
public class ModifCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CHAMP_CIVILITE = "civilite";
	public static final String CHAMP_NOM = "nom";
	public static final String CHAMP_PRENOM = "prenom";
	public static final String CHAMP_NAISSANCE = "dateDeNaissance";
	public static final String CHAMP_RUE = "Rue";
	public static final String CHAMP_COMPLEMENT = "Complement";
	public static final String CHAMP_CODEPOSTAL = "CodePostal";
	public static final String CHAMP_VILLE = "Ville";
	public static final String CHAMP_PAYS = "Pays";
	public static final String CHAMP_PREFERENCE = "preference";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifCompte() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pageName = "/modifCompte.jsp"; // on affiche la page de modification de compte personnel
		this.getServletContext().getRequestDispatcher(pageName).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// recuperation du formulaire
		int civiliteInt = Integer.parseInt(request.getParameter(CHAMP_CIVILITE));
		MainServlet.civilite civilite = MainServlet.civilite.values()[civiliteInt];

		String nom = request.getParameter(CHAMP_NOM);
		String prenom = request.getParameter(CHAMP_PRENOM);
		String naissance = request.getParameter(CHAMP_NAISSANCE);
		String rue = request.getParameter(CHAMP_RUE);
		String complement = request.getParameter(CHAMP_COMPLEMENT);
		int codePostal = Integer.parseInt(request.getParameter(CHAMP_CODEPOSTAL));
		String ville = request.getParameter(CHAMP_VILLE);
		String pays = request.getParameter(CHAMP_PAYS);

		int preferenceInt = Integer.parseInt(request.getParameter(CHAMP_PREFERENCE));
		MainServlet.preference preference = MainServlet.preference.values()[preferenceInt];

		// recuperation du "cookie" client
		// (pour savoir a qui on a faire etant donne qu'on ne lui redemande pas son email)
		HttpSession maSession = request.getSession();
		Membre membre = (Membre) maSession.getAttribute("membreConnecte");
		String email = membre.getEmail();

		// on met a jour le cookie du client mais on ne le "push" pas encore
		membre.setCivilite(civilite);
		membre.setNom(nom);
		membre.setPrenom(prenom);
		membre.setNaissance(naissance);
		membre.setAddr_rue(rue);
		membre.setAddr_complement(complement);
		membre.setAddr_code_postal(codePostal);
		membre.setVille(ville);
		membre.setPays(pays);
		membre.setPreference(preference);

		// on fait les modifs dans la BDD
		Boolean retour = Membre.modifCompte(civilite, nom, prenom, naissance, rue, complement, codePostal, ville, pays,
				preference, email);

		// si on a pu faire les modifs
		if (retour) {
			// si oui
			// on met a jour le cookie du client
			maSession.setAttribute("membreConnecte", membre);
			// on met un message pour dire que les modifs ont ete prisent en compte
			maSession.setAttribute("codeRetour", "modifications prisent en compte :-)");
		} else {
			// sinon
			// on affiche un message d'erreur pour le client
			maSession.setAttribute("codeRetour", "la modification a échouée :-(");
		}

		doGet(request, response);
	}
}
