package org.jee;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CHAMP_CIVILITE = "civilite";
	public static final String CHAMP_NOM = "nomInscription";
	public static final String CHAMP_PRENOM = "prenomInscription";
	public static final String CHAMP_EMAIL = "email";
	public static final String CHAMP_PASS = "motdepasse";
	public static final String CHAMP_CONF_PASS = "confirmationMotdepasse";
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
	public Inscription() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pageName = "/visiteur.jsp";
		this.getServletContext().getRequestDispatcher(pageName).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperation des champs du formulaire
		int civiliteInt = Integer.parseInt(request.getParameter(CHAMP_CIVILITE));
		MainServlet.civilite civilite = MainServlet.civilite.values()[civiliteInt];

		String nom = request.getParameter(CHAMP_NOM);
		String prenom = request.getParameter(CHAMP_PRENOM);
		String email = request.getParameter(CHAMP_EMAIL);
		String password = request.getParameter(CHAMP_PASS);
		String passwordConf = request.getParameter(CHAMP_CONF_PASS);
		String naissance = request.getParameter(CHAMP_NAISSANCE);
		String rue = request.getParameter(CHAMP_RUE);
		String complement = request.getParameter(CHAMP_COMPLEMENT);
		int codePostal = Integer.parseInt(request.getParameter(CHAMP_CODEPOSTAL));
		String ville = request.getParameter(CHAMP_VILLE);
		String pays = request.getParameter(CHAMP_PAYS);

		int preferenceInt = Integer.parseInt(request.getParameter(CHAMP_PREFERENCE));
		MainServlet.preference preference = MainServlet.preference.values()[preferenceInt];

		Visiteur.returnStatement retour = null;
		try {
			// insertion dans la base de donnees
			retour = Visiteur.CreerCompte(civilite, nom, prenom, email, password, passwordConf, naissance, rue,
					complement, codePostal, ville, pays, preference);
			
			HttpSession maSession = request.getSession();
			if(retour==Visiteur.returnStatement.OK) {
				maSession.setAttribute("codeRetour", "Creation de compte valide"); // pour afficher une pop up informative

				
			}else if(retour==Visiteur.returnStatement.EMAIL_NON_DISPO) {
				maSession.setAttribute("codeRetour", "email non disponible"); // pour afficher une pop up informative
			}else {
				maSession.setAttribute("codeRetour", "La creation de compte a échoué"); // pour afficher une pop up informative

			}
			
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}

		doGet(request, response);
	}

}
