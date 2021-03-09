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
		
		HttpSession maSession = request.getSession();
		maSession.setAttribute("lemail", emailModif);
		
		
		//System.out.println("chosed" +emailModif);
 
		
		
		doGet(request, response);
	}

}
