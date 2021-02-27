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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TESTS Interface
		
		
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++ PUSH D'ALEX ATTTTTENTIOOOON  ++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		String test = request.getParameter("name");
		
		if(test != null) {
			
			if(test.equals("Titres")) {
				
				System.out.println("okkkMusique");
				CatalogueService catalogueElements = new CatalogueServiceImpl();
				
				List<ElementDeCatalogue> listElements = catalogueElements.getAllTitres();
				
				response.getWriter().write("<table border=\"1\">"
						+ "<tr>\n"
						+ "	            <th>Titre</th>\n"
						+ "	            <th>Auteur</th>\n"
						+ "</tr>");
				
				for (ElementDeCatalogue cata:listElements) {
		     		 String title = cata.getTitre();
		      		 String author = cata.getInterprete();
		      		 

					response.getWriter().write("<tr>\n"
								+ "<th>"+title+"</th>\n"
								+ "	<th>"+author+"</th>\n"
								+ "</tr>");
				}
				
				response.getWriter().write("</table>");
				
			}else if(test.equals("Albums")) {
				
				System.out.println("okkk");
			
				CatalogueService catalogueElements = new CatalogueServiceImpl();
				
				List<ElementDeCatalogue> listElements = catalogueElements.getAllAlbums();
				
				response.getWriter().write("<table border=\"1\">"
						+ "<tr>\n"
						+ "	            <th>Titre</th>\n"
						+ "	            <th>Auteur</th>\n"
						+ "</tr>");
				
				for (ElementDeCatalogue cata:listElements) {
		     		 String title = cata.getTitre();
		      		 String author = cata.getInterprete();
		      		 
		      		System.out.println(title);
					response.getWriter().write("<tr>\n"
								+ "<th>"+title+"</th>\n"
								+ "	<th>"+author+"</th>\n"
								+ "</tr>");
				}
				
				response.getWriter().write("</table>");
				
			}else if(test.equals("Podcasts")) {
				
				System.out.println("okkkPodcast la ");
			
				CatalogueService catalogueElements = new CatalogueServiceImpl();
				
				List<ElementDeCatalogue> listElements = catalogueElements.getAllPodcasts();
				
				response.getWriter().write("<table border=\"1\">"
						+ "<tr>\n"
						+ "	            <th>Titre</th>\n"
						+ "	            <th>Auteur</th>\n"
						+ "</tr>");
				
				for (ElementDeCatalogue cata:listElements) {
		     		 String title = cata.getTitre();
		      		 String author = cata.getInterprete();
		      		 
		      		System.out.println(title);
					response.getWriter().write("<tr>\n"
								+ "<th>"+title+"</th>\n"
								+ "	<th>"+author+"</th>\n"
								+ "</tr>");
				}
				response.getWriter().write("</table>");
				
			}else if(test.equals("Radios")) {
			
				System.out.println("okkkPodcast la ");
			
				CatalogueService catalogueElements = new CatalogueServiceImpl();
				
				List<ElementDeCatalogue> listElements = catalogueElements.getAllRadios();
				
				response.getWriter().write("<table border=\"1\">"
						+ "<tr>\n"
						+ "	            <th>Titre</th>\n"
						+ "	            <th>Auteur</th>\n"
						+ "</tr>");
				
				for (ElementDeCatalogue cata:listElements) {
		     		 String title = cata.getTitre();
		      		 String author = cata.getInterprete();
		      		 
		      		System.out.println(title);
					response.getWriter().write("<tr>\n"
								+ "<th>"+title+"</th>\n"
								+ "	<th>"+author+"</th>\n"
								+ "</tr>");
				}
				response.getWriter().write("</table>");
			}else { // On sait qu'on est dans la barre de recherche 
			
				System.out.println("okkkPodcast la ");
			
				CatalogueService catalogueElements = new CatalogueServiceImpl();
				
				System.out.println("recherche:"+test);
				
				List<ElementDeCatalogue> listElements = catalogueElements.getElementByTitle(test);
				
				response.getWriter().write("<table border=\"1\">"
						+ "<tr>\n"
						+ "	            <th>Titre</th>\n"
						+ "	            <th>Auteur</th>\n"
						+ "</tr>");
				
				for (ElementDeCatalogue cata:listElements) {
		     		 String title = cata.getTitre();
		      		 String author = cata.getInterprete();
		      		 
		      		System.out.println(title);
					response.getWriter().write("<tr>\n"
								+ "<th>"+title+"</th>\n"
								+ "	<th>"+author+"</th>\n"
								+ "</tr>");
				}
				response.getWriter().write("</table>");
			}
		}else {
				String pageName = "/Catalogue.jsp";
				/*
				response.setContentType("text/html");
				
				//System.out.println("okokoko");
				
				if(request.getParameter("name") == "accueil")
				{
					 pageName = "/accueil.jsp";
				}else
				{
					 pageName = "/Catalogue.jsp";
				}
				*/
				
		        RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);
		        
		        try {
		              rd.forward(request, response);
		        } catch (ServletException e) {
		              e.printStackTrace();
		        } catch (IOException e) {
		              e.printStackTrace();
		        }
			  }
		
		//System.out.println("okokoko");
		
		
		
		
		// ++++++++++++++++++++++++++++++++++++++++++++++++++++++ Ancien code d'eliott ++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		
        /*
		String pageName = "/accueil.jsp";

		RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);

		String connect = request.getParameter("connect");

		
		// TESTS CREATION COMPTE

		Visiteur dupond = new Visiteur("Dupond", "Charles");

		dupond.CreerCompte(0, "Dupond", "Charles", "cdupond@enssat.fr", "1234","1234", "1970-08-14",
				"9 place du general de gaule", "interphone blanc", 75012, "Paris", "France", 1);

		// RequestDispatcher rd =
		// getServletContext().getRequestDispatcher("/accueil.jsp");

		ClientService clientService = new ClientServiceImpl();

		List<Membre> listMembre = clientService.getAllMembre();

		Membre eliott = listMembre.get(0);
		// System.out.println("ELIOTT email:"+eliott.getEmail()+" psw:
		// "+eliott.getPassword());
		Boolean valid = eliott.validerAuthentification("ethomas@enssat.fr", "azerty");

		if (valid) {
			//response.getWriter().append("Connection success !");
			connect = "Connection success !";
			request.setAttribute("connect", connect);

			// response.getWriter().append("Served at:
			// ").append(request.getContextPath()).append("\n").append(listMembre.toString());
		} else {
			//response.getWriter().append("Connection failure !");
			connect = "Connection failure !";
			request.setAttribute("connect", connect);

		}

		try {

			rd.forward(request, response);

		} catch (ServletException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
			String reponse  = request.getParameter("param");
			
			System.out.println(reponse);
			
			if(reponse.equals(reponse)) {
		
				CatalogueService elem = new CatalogueServiceImpl();
				
				List<ElementDeCatalogue> listElements = elem.getAllTitres();
				
				request.setAttribute("listElements", listElements);
				
				System.out.println("ok");
				
		        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Catalogue.jsp");
		        try {
		              rd.forward(request, response);
		        } catch (ServletException e) {
		              e.printStackTrace();
		        } catch (IOException e) {
		              e.printStackTrace();
		        }
			}else {
				System.out.println("faux");
			}*/
		
		
		
	}
}
