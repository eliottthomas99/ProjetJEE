package org.jee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Deconnection
 */
@WebServlet("/Deconnection")
public class Deconnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deconnection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession maSession = request.getSession(); // on récupère la session
		Membre membre = (Membre) maSession.getAttribute("membreConnecte");
		String pageName = "";
		if(membre==null) { // un visiteur est connecté
			pageName = "/visiteur.jsp";

		}
		else {
			pageName = "/accueil.jsp";

		}

		maSession.setAttribute("membreConnecte", null); //pour un usage ultérieur dans java
		maSession.setAttribute("membreModifie", null); //pour un usage ultérieur dans java
		maSession.setAttribute("visiteurConnecte", null); //pour un usage ultérieur dans java
		
		
		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
