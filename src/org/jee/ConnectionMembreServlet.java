package org.jee;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jee.MainServlet;

import java.util.List;


/**
 * Servlet implementation class ConnectionMembreServlet
 */
@WebServlet("/Connection")
public class ConnectionMembreServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public static final String CHAMP_EMAIL = "email";
    public static final String CHAMP_PASS = "motdepasse";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionMembreServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageName = "/connectionMembre.jsp"; // on se connecte à la page de connection à son compte
		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String valideStr = "";
        
        /* Récupération des champs du formulaire. */
        String email = request.getParameter( CHAMP_EMAIL );
        String motDePasse = request.getParameter( CHAMP_PASS );
        
        Boolean redo = true; // faudra-t-il refaire ce bout de code suivant ?
        HttpSession maSession = request.getSession(); // on recupère la session
        try {
			Boolean valide = Membre.validerAuthentification(email, motDePasse); // on vérifie que la personne tente de s'identifier avec une combinaison email/motDePasse correcte
			
			if (valide) { // si la combinaison est vaidée
				valideStr = "c'est bon";
				request.setAttribute("valideStr", valideStr);
				
				redo = false; // pour ne pas afficher la même page après, puisque l'on se connecte
				
				Membre membre  =  Membre.getMembre(email); //creer un objet membre basé sur l'email et les données de la BDD


				//On va sur une page membre ou admin en fonction 
				int adminCompte = membre.getAdminCompte();
				
	
				if(adminCompte==1) { 
					Membre membreAdmin = membre;
					
					maSession.setAttribute("visiteurConnecte",null ); //Pour que l'application sache qu'elle a affaire à un membre
					maSession.setAttribute("membreConnecte", membreAdmin);
					maSession.setAttribute("membreModifie", membreAdmin);
					maSession.setAttribute("mailModif", email);
					RequestDispatcher rd = request.getRequestDispatcher("/MainServlet");
					rd.forward(request,response);
					
				}
				else {
					
					maSession.setAttribute("visiteurConnecte", null); //Pour que l'application sache qu'elle a affaire à un membre
					Membre membreNormal = membre;
					maSession.setAttribute("membreConnecte", membreNormal);
					RequestDispatcher rd = request.getRequestDispatcher("/MainServlet");
					rd.forward(request,response);	
				}
					
			} else { // la combinaison est mauvaise
				
				Membre membreTente = Membre.getMembre(email); //vérifier si l'email existe

				if(membreTente!=null &&  membreTente.getBloque()==1) {
					valideStr = "compte bloque retente dans 1H";
					
				}
				else {
					valideStr = "Mauvaise combinaison identifiant mot de passe";
					
				}
				
				maSession.setAttribute("codeRetour", valideStr); // pour afficher une pop up informative
				request.setAttribute("valideStr", valideStr);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
        
        if(redo) { // s'il y a eu une erreur on relance la même page
		doGet(request, response);
        }
	}

}
