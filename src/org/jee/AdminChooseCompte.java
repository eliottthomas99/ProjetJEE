package org.jee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ModifCompte
 */
@WebServlet("/AdminChooseCompte")
public class AdminChooseCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminChooseCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//on affiche la page administrateur de modification de compte
		String pageName = "/adminModifCompte.jsp";

		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String emailModif = request.getParameter( "email" ); //l'email du compte a modifier
		Membre membreModif = Membre.getMembre(emailModif); // l'objet membre correspondant au compte à modifier
		HttpSession maSession = request.getSession(); // on récupère la session
		
		if(membreModif!=null) { //cas général
			request.setAttribute("leMembre", membreModif);// pour l'affichage dans jsp
			request.setAttribute("lemail", emailModif); //pour l'affichage dans jsp
			maSession.setAttribute("membreModifie", membreModif); //pour un usage ultérieur dans java
			maSession.setAttribute("mailModif", emailModif); //pour un usage ultérieur dans java
			
		}
		else { //cas ou l'administrateur a rentré un email qui n'existe pas dans la base de données
			Membre membreVide = new Membre(); // on cree un memebre "vide" et on remplie les champs qui vont devoir apparaitre dans le formulaire
			membreVide.setCivilite(MainServlet.civilite.NE_SE_PRONONCE_PAS);
			membreVide.setNom("");
			membreVide.setPrenom("");
			membreVide.setNaissance("1111-11-11");
			membreVide.setAddr_rue("");
			membreVide.setAddr_complement("");
			membreVide.setAddr_code_postal(0);
			membreVide.setVille("");
			membreVide.setPays("");
			membreVide.setPreference(MainServlet.preference.CLASSIQUE);
			
			
			maSession.setAttribute("membreModifie", membreVide); //pour un usage ultérieur dans java
			maSession.setAttribute("mailModif", emailModif); //pour un usage ultérieur dans java
			request.setAttribute("lemail", emailModif); //pour l'affichage
		}
		
 
		
		
		doGet(request, response); // on affiche la page
	}

}
