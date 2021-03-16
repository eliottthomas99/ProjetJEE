package org.jee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class VisiteurServlet
 */
@WebServlet("/ConnectionTemporaire")
public class VisiteurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisiteurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String pageName = "/visiteur.jsp";

		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String nom = request.getParameter( "nom" );
        String prenom = request.getParameter( "prenom" );
		
        Visiteur visiteurConnecte = new Visiteur(nom, prenom);
        
        Boolean redo = true;
        
        if(nom != null && prenom != null) {
        	redo =false;
            HttpSession maSession = request.getSession();
            maSession.setAttribute("visiteurConnecte", visiteurConnecte);
			maSession.setAttribute("membreConnecte", null);
			RequestDispatcher rd = request.getRequestDispatcher("/MainServlet");
			rd.forward(request,response);
        	
        }
        
		if(redo) {
			doGet(request, response);

		}
	}

}
