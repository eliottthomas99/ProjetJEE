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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String pageName = "/adminModifCompte.jsp";

		this.getServletContext().getRequestDispatcher(pageName).forward(request, response);

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession maSession = request.getSession();
		String emailModif = (String) maSession.getAttribute("mailModif");

		// pour que le .jsp ne soit pas perdu dans ses affichages
		Membre membre = Membre.getMembre(emailModif);
		request.setAttribute("leMembre", membre);
		request.setAttribute("lemail", emailModif); // pour l'affichage de lemail à choisir, c'est plus propre

		Boolean retour = Membre.supprimerCompte(emailModif);

		if (retour) {
			maSession.setAttribute("codeRetour", "Suppression prise en compte !");
		} else {
			maSession.setAttribute("codeRetour", "la suppression a échouée :'-(");
		}

		doGet(request, response);
	}

}
