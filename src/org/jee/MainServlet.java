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
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	
	
	enum civilite {
		  MADAME,
		  MONSIEUR,
		  NE_SE_PRONONCE_PAS,
		  NON_BINAIRE
		}

	
	enum preference {
		  CLASSIQUE,
		  HOUSE,
		  JAZZ,
		  METAL,
		  POP,
		  ROCK
		}
	
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TESTS Interface

		String pageName = "/connectionMembre.jsp";

		RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);

		String connect = request.getParameter("connect");

		
		// TESTS CREATION COMPTE

		Visiteur dupond = new Visiteur("Dupond", "Charles");

		dupond.CreerCompte(MainServlet.civilite.MONSIEUR, "THOMAS", "alex", "athomas@enssat.fr", "1234","1234", "1999-08-14",
				"Les Ursu", "euuu", 22300, "Lannion", "France", MainServlet.preference.HOUSE);

		// RequestDispatcher rd =
		// getServletContext().getRequestDispatcher("/accueil.jsp");

		ClientService clientService = new ClientServiceImpl();

		List<Membre> listMembre = clientService.getAllMembre();

		Membre eliott = listMembre.get(0);
		// System.out.println("ELIOTT email:"+eliott.getEmail()+" psw:
		// "+eliott.getPassword());
		Boolean valid = null;
		try {
			valid = eliott.validerAuthentification("ethomas@enssat.fr", "azerty");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (valid) {
			//response.getWriter().append("Connection success !");
			connect = "Connection success !";
			System.out.println("success");
			request.setAttribute("connect", connect);

			// response.getWriter().append("Served at:
			// ").append(request.getContextPath()).append("\n").append(listMembre.toString());
		} else {
			//response.getWriter().append("Connection failure !");
			connect = "Connection failure !";
			System.out.println("fail");
			request.setAttribute("connect", connect);

		}

		try {

			rd.forward(request, response);

		} catch (ServletException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
