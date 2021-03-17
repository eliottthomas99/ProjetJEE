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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String pageName = "/connectionMembre.jsp";

		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        
		String valideStr = "";
        
        
        /* Récupération des champs du formulaire. */
        String email = request.getParameter( CHAMP_EMAIL );
        String motDePasse = request.getParameter( CHAMP_PASS );
        
        Boolean redo = true;
        HttpSession maSession = request.getSession();
        try {
			Boolean valide = Membre.validerAuthentification(email, motDePasse);
			
			if (valide) {
				valideStr = "c'est bon";
				request.setAttribute("valideStr", valideStr);
				
				redo = false;
				
				//creer un objet membre
				Membre membre  =  Membre.getMembre(email);
				
				// creation session http
				
				maSession.setAttribute("membre", membre);
				
				
				//System.out.println(membre);
				// le passer en paramètre à la page suivante
				
				//request.setAttribute("leMembre", membre);

				//On va sur une page membre ou admin en fonction 
				int adminCompte = membre.getAdminCompte();
				
	
				if(adminCompte==1) { 
					//String pageName = "/adminModifCompte.jsp"
					Membre membreAdmin = Membre.getMembre(email);
					
					//Pour que l'application sache qu'elle a affaire à un membre
					
					//Visiteur visiteur = new Visiteur("FULBERT","ALEX");
					maSession.setAttribute("visiteurConnecte",null );

					maSession.setAttribute("membreConnecte", membreAdmin);
					maSession.setAttribute("membreModifie", membreAdmin);
					maSession.setAttribute("mailModif", email);
					RequestDispatcher rd = request.getRequestDispatcher("/MainServlet");
					rd.forward(request,response);
					
				}
				else {
					
					//Pour que l'application sache qu'elle a affaire à un membre
					maSession.setAttribute("visiteurConnecte", null);
					Membre membreNormal = Membre.getMembre(email);
					maSession.setAttribute("membreConnecte", membreNormal);
					RequestDispatcher rd = request.getRequestDispatcher("/MainServlet");
					rd.forward(request,response);	
				}
					
			} else {
				
				
				Membre membreTente = Membre.getMembre(email);
				//vérifier si l'email existe
				
				
				if(membreTente!=null &&  membreTente.getBloque()==1) {
					valideStr = "compte bloque retente dans 1H";
					
				}
				else {
					valideStr = "Mauvaise combinaison identifiant mot de passe";
					
				}
				
				

				maSession.setAttribute("codeRetour", valideStr);
				
				
				
				request.setAttribute("valideStr", valideStr);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(redo) {
		doGet(request, response);
        }
	}

}
