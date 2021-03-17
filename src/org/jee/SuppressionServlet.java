package org.jee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SuppressionServlet
 */
@WebServlet("/suppression")
public class SuppressionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuppressionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageName = "/suppressionCompte.jsp"; // affichage de la page de suppression de compte
		this.getServletContext().getRequestDispatcher(pageName).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// on recupere l email du client
		HttpSession maSession = request.getSession(); // on récupère la session
		Membre membre = (Membre) maSession.getAttribute("membre");
		String emailMembre = membre.getEmail();

		Connection connexion = DBManager.getInstance().getConnection(); // connection a la BDD
		try (Statement stmt = connexion.createStatement()) {
			// suppression de la table du membre en question
			String insert_query = String.format("DELETE FROM membres " + "where email='%s';", emailMembre);

			int rs = stmt.executeUpdate(insert_query);
		} catch (SQLException e) {
			System.out.println(e);
		}

		// mise a jour du "cookie" du client
		maSession.setAttribute("membre", null);

		// redirection vers l'accueil (le choix memebre ou visiteur)
		String pageName = "/accueil.jsp";
		this.getServletContext().getRequestDispatcher(pageName).forward(request, response);
	}

}
