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
@WebServlet("/AdminSupprimerCompte")
public class AdminSupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminSupprimerCompte() {
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
		String emailModif = (String) maSession.getAttribute("mailModif");  // on récupère l'email du compte à supprimer
		Membre membre = Membre.getMembre(emailModif); // on récupère l'objet membre qui correspond à l'email fourni
		
		if(membre==null) {
			
			 
			maSession.setAttribute("codeRetour", "email mal renseigné");
			
		}else {
		
		
		
			request.setAttribute("leMembre", membre);
			request.setAttribute("lemail", emailModif); // pour l'affichage de lemail à choisir, c'est plus propre
	
			Boolean retour = Membre.supprimerCompte(emailModif);
	
			if (retour) {
				maSession.setAttribute("codeRetour", "Suppression prise en compte !");
			} else {
				maSession.setAttribute("codeRetour", "la suppression a échouée :'-(");
			}
			
		}

		doGet(request, response);
	}

}
