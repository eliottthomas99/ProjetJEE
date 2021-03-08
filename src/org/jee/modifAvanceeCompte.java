package org.jee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class modifAvanceeCompte
 */
@WebServlet("/ModifAvanceeCompte")
public class modifAvanceeCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifAvanceeCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String pageName = "/modifCompte.jsp";

		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String ancienMotDePasse = request.getParameter( "ancienMotDePasse" );
        String nouveauEmail = request.getParameter( "nouveauEmail" );
        String nouveauMotDePasse = request.getParameter( "nouveauMotDePasse" );
		Boolean valid = false;
		
		
		try {
			
			
			 HttpSession maSession = request.getSession();
	    	 Membre membre = (Membre)maSession.getAttribute("membre");
	    	 String ancienEmail = membre.getEmail();
			 valid = Membre.validerAuthentification(ancienEmail, ancienMotDePasse);
			 
			 if (valid) {
					
					
				 	membre.setEmail(nouveauEmail);
				 	membre.setPassword(nouveauMotDePasse);
			        maSession.setAttribute("membre", membre);

					
					Boolean retour = Membre.modifAvanceeCompte(ancienEmail, nouveauEmail,  nouveauMotDePasse);

				}
			 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		doGet(request, response);
	}

}
