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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pageName = "/suppressionCompte.jsp";

		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// on recupere l email du client
		 HttpSession maSession = request.getSession();
		 Membre membre = (Membre)maSession.getAttribute("membre");
		 String emailMembre = membre.getEmail();
		 
		 // connection BDD
		 Connection connexion = DBManager.getInstance().getConnection();
		 try (Statement stmt = connexion.createStatement()) {

		 
		 // suppression de la table du membre en question
				String insert_query = String.format(
						"DELETE FROM membres "
								+ "where email='%s';",
						emailMembre);
				
				System.out.println(insert_query);
				
				int rs = stmt.executeUpdate(insert_query);
			} catch (SQLException e) {
				
				System.out.println(e);
			}
		 
		
		doGet(request, response);
	}

}
