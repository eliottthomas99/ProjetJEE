package org.jee;

import java.io.IOException;
import java.sql.Connection;

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
		String codeRetourModifAvancee = null;
		

		 HttpSession maSession = request.getSession();
		 Membre membre = (Membre)maSession.getAttribute("membreConnecte");
		
		String toto = null;
		toto = "toto";
		System.out.println(toto);
		
		String ancienMotDePasse = request.getParameter( "ancienMotDePasse" );
        String nouveauEmail = request.getParameter( "nouveauEmail" );
        String nouveauMotDePasse = request.getParameter( "nouveauMotDePasse" );
        String nouveauMotDePasseConfirmation = request.getParameter( "nouveauMotDePasseConfirmation" );
		Boolean valid = false;
		
		// les nouveaux mdp correspondent
		if (nouveauMotDePasse.equals(nouveauMotDePasseConfirmation)) {
			
			// on recupere l ancien email
			try {
		    	 String ancienEmail = membre.getEmail();
		    	 
		    	 // on verifie que le mdp entre soit le bon
				 valid = Membre.validerAuthentification(ancienEmail, ancienMotDePasse);
				 
				if (valid) {

					Connection connexion = DBManager.getInstance().getConnection();					
					Boolean emailDispo = AlgorithmeDeVerification.emailDispo(nouveauEmail) || nouveauEmail.equals(ancienEmail);
					// on verifie que l email choisit soit : sois l ancien soit un nouveau email disponible
					if (emailDispo) {
						
							// si tout est ok on met à jour les informations
						 	membre.setEmail(nouveauEmail);
						 	membre.setPassword(nouveauMotDePasse);
					        maSession.setAttribute("membreModifie", membre);
					        Boolean retour = Membre.modifAvanceeCompte(ancienEmail, nouveauEmail,  nouveauMotDePasse);
					        
					        // on affiche que tout s est bien passe
					        codeRetourModifAvancee = "modifications enregistrées";
					}
					// sinon si l email n'est pas dispo
					else {
						// TODO : message email non disponible
						codeRetourModifAvancee = "email non disponible";
					}
				} 
				// sinon si l ancien mdp n'est pas bon
				else {
					// TODO : message mdp incorrect
					codeRetourModifAvancee = "mot de passe actuel incorrect";
				}
		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// sinon si les mdps ne correspondent pas
		else {
			// TODO : message les mdps ne correspondent pas
			codeRetourModifAvancee = "les mots de passe de passe ne correspondent pas";
		}
		
		
		

				
			System.out.println(codeRetourModifAvancee);
			maSession.setAttribute("codeRetour", codeRetourModifAvancee);
		
		
		
		doGet(request, response);
	}

}
