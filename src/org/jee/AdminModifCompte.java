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
@WebServlet("/AdminModifCompte")
public class AdminModifCompte extends HttpServlet {
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
	public AdminModifCompte() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pageName = "/adminModifCompte.jsp"; // on affiche la page administrateur de modification de compte
		this.getServletContext().getRequestDispatcher(pageName).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession maSession = request.getSession(); // on récupère la session
		String emailModif = (String) maSession.getAttribute("mailModif"); // on recupère lemail du compte à modifier
		Membre membreModif = (Membre) maSession.getAttribute("membreModifie"); // on récupère l'objet membre qui correspond au compte à modifier

		if(Membre.getMembre(emailModif)==null) { // si l'email a été mal renseigné
			
			maSession.setAttribute("codeRetour", "email mal renseigné");
			
		}else {
		
			// on récupère tous les champs qui ont été entrés dans le formulaire
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
	
			// on modifie l'objet car on va devoir le transmettre 
			membreModif.setCivilite(civilite);
			membreModif.setNom(nom);
			membreModif.setPrenom(prenom);
			membreModif.setNaissance(naissance);
			membreModif.setAddr_rue(rue);
			membreModif.setAddr_complement(complement);
			membreModif.setAddr_code_postal(codePostal);
			membreModif.setVille(ville);
			membreModif.setPays(pays);
			membreModif.setPreference(preference);
	
			//on modifie le compte
			Boolean retour = Membre.modifCompte(civilite, nom, prenom, naissance, rue, complement, codePostal, ville, pays,
					preference, emailModif);
			
			if (retour) {
				maSession.setAttribute("codeRetour", "modification prise en compte :-)");
			} else {
				maSession.setAttribute("codeRetour", "la modification a échouée :-(");
			}
		
		}
		
		maSession.setAttribute("membreModifie", membreModif); // on transmet l'information du membre modifie

		request.setAttribute("lemail", emailModif); // pour laffichage

		doGet(request, response); // on affiche la page
	}

}
