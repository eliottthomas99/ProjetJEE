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
 * Servlet implementation class ConnectionTemporaire
 */
@WebServlet("/ConnectionTemporaire")
public class ConnectionTemporaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionTemporaire() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageName = "/visiteur.jsp"; // on affiche la page qui concerne les visiteurs
		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//on récupère les données entrées par l'utilisateur
		String nom = request.getParameter( "nom" );
        String prenom = request.getParameter( "prenom" );
		
        
        Boolean redo = true; // à priori il va falloir repaser par là
        
        if(nom != null && prenom != null) { //si l'utilisateur a bien rentré un nom et un prenom
        	
            Visiteur visiteurConnecte = new Visiteur(nom, prenom); //on crée un objet visiteur avec ces caractéristiques, que l'on va ensuite transmettre à la session
        	redo =false; // on ne doit pas re afficher cette page après puisque l'on ouvre l'application a proprement parler
            HttpSession maSession = request.getSession(); // on récupère la session
            maSession.setAttribute("visiteurConnecte", visiteurConnecte); // on transmet le visiteur à travers la session
			maSession.setAttribute("membreConnecte", null); // on transmet un membre nul pour que l'applicatio sache que c'est un visiteur
			RequestDispatcher rd = request.getRequestDispatcher("/MainServlet"); // on cahnge de page
			rd.forward(request,response);
        	
        }
        
		if(redo) {
			doGet(request, response);

		}
	}

}
