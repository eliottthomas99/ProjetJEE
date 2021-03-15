package org.jee;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	public static final String CHAMP_CIVILITE = "civilite";
    public static final String CHAMP_NOM = "nomInscription";
    public static final String CHAMP_PRENOM = "prenomInscription";
    public static final String CHAMP_EMAIL = "email";
    public static final String CHAMP_PASS = "motdepasse";
    public static final String CHAMP_CONF_PASS = "confirmationMotdepasse";
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
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String pageName = "/visiteur.jsp";

		this.getServletContext().getRequestDispatcher( pageName ).forward( request, response );
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		/* Récupération des champs du formulaire. */
		
		int civiliteInt = Integer.parseInt(request.getParameter( CHAMP_CIVILITE ));
		MainServlet.civilite civilite = MainServlet.civilite.values()[civiliteInt] ;
		
        String nom = request.getParameter( CHAMP_NOM );
        String prenom = request.getParameter( CHAMP_PRENOM );
        String email = request.getParameter( CHAMP_EMAIL );
        String password = request.getParameter( CHAMP_PASS );
        String passwordConf = request.getParameter( CHAMP_CONF_PASS );
        String naissance = request.getParameter( CHAMP_NAISSANCE );
        String rue = request.getParameter( CHAMP_RUE );
        String complement = request.getParameter( CHAMP_COMPLEMENT );
        int codePostal = Integer.parseInt( request.getParameter( CHAMP_CODEPOSTAL ));
        String ville = request.getParameter( CHAMP_VILLE );
        String pays = request.getParameter( CHAMP_PAYS );
        
        int preferenceInt = Integer.parseInt(request.getParameter( CHAMP_PREFERENCE ));
        MainServlet.preference preference = MainServlet.preference.values()[preferenceInt] ;
		
        System.out.println("mdp : "+password);
        System.out.println("mdpConf : "+passwordConf);

        
        
        Visiteur.returnStatement retour = null;
		try {
			retour = Visiteur.CreerCompte(civilite, nom, prenom,email, password, passwordConf, 
																	naissance, rue, complement, codePostal, ville, pays, preference);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println(retour);
		doGet(request, response);
	}

}
