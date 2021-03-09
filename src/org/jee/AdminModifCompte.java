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
@WebServlet("/AdminModifCompte")
public class AdminModifCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String CHAMP_CIVILITE = "civilite";
    public static final String CHAMP_NOM = "nom";
    public static final String CHAMP_PRENOM = "prenom";
    public static final String CHAMP_NAISSANCE = "dateDeNaissance";
    public static final String CHAMP_RUE = "Rue";
    public static final String CHAMP_COMPLEMENT = "Complement";
    public static final String CHAMP_CODEPOSTAL= "CodePostal";
    public static final String CHAMP_VILLE = "Ville";
    public static final String CHAMP_PAYS = "Pays";
    public static final String CHAMP_PREFERENCE= "preference";
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminModifCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String pageName = "/adminModifCompte.jsp";

		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String emailModif = request.getParameter( "email" );
		Membre membreModif = Membre.getMembre(emailModif);
		request.setAttribute("leMembre", membreModif);
		
		/*
		int civiliteInt = Integer.parseInt(request.getParameter( CHAMP_CIVILITE ));
		MainServlet.civilite civilite = MainServlet.civilite.values()[civiliteInt] ;
		
        String nom = request.getParameter( CHAMP_NOM );
        String prenom = request.getParameter( CHAMP_PRENOM );
        String naissance = request.getParameter( CHAMP_NAISSANCE );
        String rue = request.getParameter( CHAMP_RUE );
        String complement = request.getParameter( CHAMP_COMPLEMENT );
        int codePostal = Integer.parseInt( request.getParameter( CHAMP_CODEPOSTAL ));
        String ville = request.getParameter( CHAMP_VILLE );
        String pays = request.getParameter( CHAMP_PAYS );
        
        int preferenceInt = Integer.parseInt(request.getParameter( CHAMP_PREFERENCE ));
        MainServlet.preference preference = MainServlet.preference.values()[preferenceInt] ;
		
        // String email = request.getParameter( "onFaitCommeOnPeut" );

        HttpSession maSession = request.getSession();
    	Membre membre = (Membre)maSession.getAttribute("membre");
    	String email = membre.getEmail();
 
    	membre.setCivilite(civilite);
    	membre.setNom(nom);
    	membre.setPrenom(prenom);
    	membre.setNaissance(naissance);
    	membre.setAddr_rue(rue);
    	membre.setAddr_complement(complement);
    	membre.setAddr_code_postal(codePostal);
    	membre.setVille(ville);
    	membre.setPays(pays);
    	membre.setPreference(preference);
    	
        
    	System.out.println("nom : " + nom + "\nprenom : " + prenom);
        Boolean retour = Membre.modifCompte(civilite, nom, prenom, naissance, rue, complement,
        												codePostal, ville, pays, preference, email);
        		
        		
        maSession.setAttribute("membre", membre);
        		
        System.out.println(retour);
		*/
		
		
		doGet(request, response);
	}

}
