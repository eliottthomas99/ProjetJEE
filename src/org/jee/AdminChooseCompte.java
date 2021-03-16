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
		// TODO Auto-generated method stub
		
		//on affiche la page administrateur de modification de compte
		String pageName = "/adminModifCompte.jsp";

		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String emailModif = request.getParameter( "email" ); //l'email du compte a modifier
		Membre membreModif = Membre.getMembre(emailModif); // l'objet membre correspondant au compte à modifier
		HttpSession maSession = request.getSession(); // on récupère la session
		
		if(membreModif!=null) { //cas général
			request.setAttribute("leMembre", membreModif);
			
			
			maSession.setAttribute("membreModifie", membreModif);
			maSession.setAttribute("mailModif", emailModif);
			request.setAttribute("lemail", emailModif); //pour l'affichage
		}
		else {
			Membre membreVide = new Membre();
			membreVide.setCivilite(MainServlet.civilite.MONSIEUR);
			membreVide.setNom("");
			membreVide.setPrenom("");
			membreVide.setNaissance("1111-11-11");
			membreVide.setAddr_rue("");
			membreVide.setAddr_complement("");
			membreVide.setAddr_code_postal(0);
			membreVide.setVille("");
			membreVide.setPays("");
			membreVide.setPreference(MainServlet.preference.CLASSIQUE);
			//request.setAttribute("leMembre", membreVide);
			//maSession.setAttribute("leMembre",membreVide);
			maSession.setAttribute("membreModifie", membreVide);
			maSession.setAttribute("mailModif", emailModif);
			request.setAttribute("lemail", emailModif); //pour l'affichage
		}
		
		//System.out.println("chosed" +emailModif);
 
		
		
		doGet(request, response);
	}

}
