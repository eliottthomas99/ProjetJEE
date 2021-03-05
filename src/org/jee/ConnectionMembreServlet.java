package org.jee;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
        
        try {
			Boolean valide = Membre.validerAuthentification(email, motDePasse);
			
			if (valide) {
				valideStr = "c'est bon";
				request.setAttribute("valideStr", valideStr);
			} else {
				valideStr = "email ou mot de passe incorrect";
				request.setAttribute("valideStr", valideStr);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		doGet(request, response);
	}

}
