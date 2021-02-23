package org.jee;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jee.Membre;
import org.jee.ClientService;
import org.jee.ClientServiceImpl;

/**
 * Servlet implementation class MainServlet
 */
//@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		//RequestDispatcher rd = getServletContext().getRequestDispatcher("/accueil.jsp");
		
		ClientService clientService = new ClientServiceImpl();
		
		List<Membre> listMembre = clientService.getAllMembre();
		
		Membre eliott = listMembre.get(0);
		//System.out.println("ELIOTT email:"+eliott.getEmail()+" psw: "+eliott.getPassword());
		Boolean valid = eliott.validerAuthentification("ethomas@enssat.fr", "azerty");
		if (valid) {
			response.getWriter().append("Connection success !");
			//response.getWriter().append("Served at: ").append(request.getContextPath()).append("\n").append(listMembre.toString());
		}
		else {
			response.getWriter().append("Connection failure !");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
