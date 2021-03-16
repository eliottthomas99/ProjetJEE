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
@WebServlet("/AdminModifAvanceeCompte")
public class AdminModifAvanceeCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminModifAvanceeCompte() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageName = "/adminModifCompte.jsp"; // on affiche la page qui correspond à la modification des comptes par un administrateur
		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String codeRetourModifAvancee = null; // initilialisation de la chaine de caractères
		HttpSession maSession = request.getSession(); // on récupère la session
    	String emailModif = (String)maSession.getAttribute("mailModif"); // on recupère dans la session l'email du compte qui doit être modifié
		Membre membre = Membre.getMembre(emailModif); // on récupère l'objet membre qui correspond à cet email
		
        String nouveauEmail = request.getParameter( "nouveauEmail" ); // on récupère le nouvel email renseigné
        String nouveauMotDePasse = request.getParameter( "nouveauMotDePasse" ); // et le nouveau mot de passe
		
		
		if(membre==null) { // si le membre est nul c'est qu'un mauvais email a été renseigné
			
			codeRetourModifAvancee = "email mal renseigné";

			
		}else {
			
		
			String ancienEmail = membre.getEmail(); // l'email du compte a modifier
			Connection connexion = DBManager.getInstance().getConnection();	// on se connecte à la BDD				
			Boolean emailDispo = AlgorithmeDeVerification.emailDispo(nouveauEmail) || nouveauEmail.equals(ancienEmail); // on regarde si l'email est disponible 
			if (emailDispo) {
				
					// si tout est ok on met à jour les informations
		    		String nouveauMotDePasseHash = JavaMD5Hash.md5(nouveauMotDePasse); // on HASH le mot de passe
		    	
				 	membre.setEmail(nouveauEmail); //on met à jour les données du membre pour l'affichage
			        membre.setEmail(nouveauEmail); // idem

				 	
			        Boolean retour = Membre.modifAvanceeCompte(ancienEmail, nouveauEmail,  nouveauMotDePasseHash); // on modifie les données dans la BDD
			        
					request.setAttribute("leMembre", membre);	// on envoie vers le jsp	
					request.setAttribute("lemail", emailModif); //idem
			        
			        // on affiche que tout s est bien passe
			        codeRetourModifAvancee = "modifications enregistrées";
			}
			// sinon si l email n'est pas dispo
			else {
				//  message email non disponible
				codeRetourModifAvancee = "email non disponible";
			}
		
				
		}
		
		

			
		maSession.setAttribute("codeRetour", codeRetourModifAvancee); //Affiche un message d'information pour l'utilisateur
		
		
		
		
		doGet(request, response);
	}

}
