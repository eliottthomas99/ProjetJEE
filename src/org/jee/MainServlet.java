package org.jee;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jee.ClientService;
import org.jee.ClientServiceImpl;



/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

	public enum civilite {
		MADAME, MONSIEUR, NE_SE_PRONONCE_PAS, NON_BINAIRE
	}

	public enum preference {
		CLASSIQUE, HOUSE, JAZZ, METAL, POP, ROCK
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

		HttpSession maSession = request.getSession();
		Membre elMembre = (Membre) maSession.getAttribute("membreConnecte");

		Visiteur visiteur = (Visiteur) maSession.getAttribute("visiteurConnecte");

		System.out.println("visiteur ->" + visiteur);

		/*
		 * if(visiteur != null) { request.setAttribute("visiteurCo", 1); }else {
		 * request.setAttribute("visiteurCo", 0); }
		 */
		
		if(visiteur != null)
		{
			CatalogueService catalogueElements = new CatalogueServiceImpl();
			List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsFromPlaylistPublic();

			request.setAttribute("listElements", listElements);

			String pageName = "/Catalogue.jsp";

			RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);

			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			
		elMembre = Membre.getMembre(elMembre.getEmail());
		int idMembreActuel = elMembre.getId();

		CatalogueService catalogueElements = new CatalogueServiceImpl();
		// System.out.println(elMembre.getCivilite());
		
		int resAdminCompteOrNo = catalogueElements.isAdminCompte(idMembreActuel);
		int resAdminMusiqueOrNo = catalogueElements.isAdminMusique(idMembreActuel);
		request.setAttribute("resAdminMusiqueOrNo", resAdminMusiqueOrNo);
		request.setAttribute("resAdminCompteOrNo", resAdminCompteOrNo);

		String param1 = request.getParameter("nomElement");
		String param2 = request.getParameter("categorie");
		String param3 = request.getParameter("interprete");
		String param4 = request.getParameter("mesPlaylists"); // Parcourir ses playlists
		String param5 = request.getParameter("nomPlaylist"); // Creation d'une nouvelle playlist personelle

		// Ses deux paramètre sont utiles pour garder sous le coude quel element il faut
		// ajouter dans la playlist
		String param6 = request.getParameter("section"); // Nom de la section ( titres , albums ... )
		String param7 = request.getParameter("chanteur"); // Nom de l'auteur du titre ici

		String param8 = request.getParameter("titresFromPlaylistPerso"); // A pour but d'aller chercher toutes les
																			// musiques relatives à une playlist Perso

		// Paramètre AdminMusique

		// Manipulation des titres / Albums
		String paramAdminMusique9 = request.getParameter("titreAjout");
		String paramAdminMusique11 = request.getParameter("AlbumAjout");

		String paramAdminMusique10 = request.getParameter("interpreteAjout");

		// Manipulation des types
		String paramAdminMusique12 = request.getParameter("titrePlaylistPublicAjout");
		String paramAdminMusique13 = request.getParameter("interpretePlaylistPublicAjout");
		String paramAdminMusique14 = request.getParameter("typeAjout");

		String paramAdminMusique15 = request.getParameter("titreElement");
		String paramAdminMusique16 = request.getParameter("categorieBis");

		String paramAdminMusique17 = request.getParameter("catElement");

		String paramAdminMusique18 = request.getParameter("titreOfAnAlbum");
		String paramAdminMusique19 = request.getParameter("InterpreteOfAnAlbum");
		String paramAdminMusique20 = request.getParameter("ActualPlaylist");

		if (param1 != null && param2 == null && param3 == null) {

			
			if (param1.equals("Titres")) {
				
				int i = 0;
				if (resAdminMusiqueOrNo == 1) {

					response.getWriter().write("<h3>Ajouter des Titres</h3>");
					response.getWriter().write(
							"<h4>Titre</h4><input name='search' id= 'barreTitre1' type='text' placeholder='titre de la musique..'>");
					response.getWriter().write(
							"<h4>Interprete</h4><input name='search' id= 'barreTitre2' type='text' placeholder='nom de l'interprete..'>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
					response.getWriter().write("<button onclick ='ajoutTitres()'>Ajouter</button>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
				}

				List<ElementDeCatalogue> listElements = catalogueElements.getAllTitres();

				response.getWriter().write("<table border=\"0\">" + "<tr>\n" + "	            <th>Titre</th>\n"

						+ "	            <th>Auteur</th>\n" + "</tr>");

				if (listElements.isEmpty()) {
					response.getWriter().write("<tr>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "</tr>");
				}

				for (ElementDeCatalogue cata : listElements) {
					String title = cata.getTitre();
					String author = cata.getInterprete();

					if (resAdminMusiqueOrNo == 1) {
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>" + author + "</td>\n"
								+ "<td><button id='jouer" + i
								+ "' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
								+ param1 + " " + title + " " + idMembreActuel
								+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button id='Titres "
								+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>");

						i = i + 1;
					} else {
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>" + author + "</td>\n"
								+ "<td><button id='jouer" + i
								+ "' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
								+ param1 + " " + title + " " + idMembreActuel
								+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>" + "</tr>");

						i = i + 1;
					}
				}

				response.getWriter().write("</table>");

			} else if (param1.equals("Albums")) {
				
				int i = 0;
				
				if (resAdminMusiqueOrNo == 1) {
					response.getWriter().write("<h3>Ajouter des Albums</h3>");
					response.getWriter().write(
							"<h4>Titre</h4><input name='search' id= 'barreAlbum1' type='text' placeholder='titre album..'>");
					response.getWriter().write(
							"<h4>Interprete</h4><input name='search' id= 'barreAlbum2' type='text' placeholder='nom de l'interprete..'>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
					response.getWriter().write("<button onclick ='ajoutAlbum()'>Ajouter</button>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
				}

				List<ElementDeCatalogue> listElements = catalogueElements.getAllAlbums();

				response.getWriter().write("<table border='0'><tr><th>Titre</th>\n"
						+ "<th>Auteur</th>\"</tr>");
				if (listElements.isEmpty()) {
					response.getWriter().write("<tr>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "</tr>");
				}

				for (ElementDeCatalogue cata : listElements) {
					String title = cata.getTitre();
					String author = cata.getInterprete();

					System.out.println(title);

					if (resAdminMusiqueOrNo == 1) {
						response.getWriter().write("<tr><td>" + title + "</td><td>" + author + "</td>\n"
								+ "<td><button id= '" + title
								+ "'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td><td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
								+ param1 + " " + title
								+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button id='Albums "
								+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>");
					} else {
						response.getWriter().write("<tr><td>" + title + "</td><td>" + author + "</td>\n"
								+ "<td><button id= '" + title
								+ "'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td>"
								+ "\"<td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
								+ param1 + " " + title
								+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>" + "</tr>");
					}
					
					i = i + 1;
				}

				response.getWriter().write("</table>");

			} else { // On sait qu'on est dans la barre de recherche
				
				int i = 0;
				List<ElementDeCatalogue> listElements;
				System.out.println("recherche:" + param1);

				if (param1.contains("Visiteur")) {
					System.out.println("du visiteur ->" + param1);
					String[] parts = param1.split("V");
					String part1 = parts[0];// param1
					System.out.println("la première partie du mot:" + part1);
					listElements = catalogueElements.getElementByresearchVisiteur(part1);

				} else {
					listElements = catalogueElements.getElementByresearch(param1);
				}

				// Si la recherche n'a rien donné :

				if (listElements.isEmpty()) {
					response.getWriter().write("<h1 color='red'> Aucun titre n'a ete trouve" + "</h1>");
				} else {

					response.getWriter().write("<table border=\"0\">" + "<tr>\n" + "	            <th>Titre</th>\n"
							+ "	            <th>Type</th>\n" + "</tr>");

					for (ElementDeCatalogue cata : listElements) {
						String title = cata.getTitre();

						if (cata instanceof Album) {
							String author = cata.getInterprete();
							response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>Album</td>\n"
									+ "<td><button id='jouer' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
									+ title + "'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td>"
									+ "</tr>");
						} else {
							response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n"
									+ "<td>Titre musical</td>\n"
									+ "<td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td>"
									+ "</tr>");
						}
						i = i + 1;
					}

					response.getWriter().write("</table>");
				}
			}
		} else if (param2 != null) {

			System.out.println(param1 + "et" + param2);
			List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsOfCategorie(param1, param2);

			// Si la recherche n'a rien donné :
			int i = 0;
			if (listElements.isEmpty()) {
				response.getWriter().write("<h1 color='red'> Aucun titre n'a ete trouve" + "</h1>");

			} else if (param1.equals("Albums")) {

				if (resAdminMusiqueOrNo == 1) {
					response.getWriter().write("<h3>Ajouter des Albums</h3>");
					response.getWriter().write(
							"<h4>Titre</h4><input name='search' id= 'barreAlbum1' type='text' placeholder='titre album..'>");
					response.getWriter().write(
							"<h4>Interprete</h4><input name='search' id= 'barreAlbum2' type='text' placeholder='nom de l'interprete..'>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
					response.getWriter().write("<button onclick ='ajoutAlbum()'>Ajouter</button>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
				}

				response.getWriter().write("<table border=\"0\">" + "<tr>\n" + "	            <th>Titre</th>\n"
						+ "	            <th>Auteur</th>\n" + "</tr>");

				for (ElementDeCatalogue cata : listElements) {
					String title = cata.getTitre();
					String author = cata.getInterprete();

					System.out.println(title);

					if (resAdminMusiqueOrNo == 1) {
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>" + author + "</td>\n"
								+ "<td><button id= '" + title
								+ "'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td><td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
								+ param1 + " " + title
								+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button id='Albums "
								+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>");
					} else {
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>" + author + "</td>\n"
								+ "<td><button id= '" + title
								+ "'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td><td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td><button id='"
								+ param1 + " " + title
								+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>" + "</tr>");
					}
				}

			} else {

				if (resAdminMusiqueOrNo == 1) {

					response.getWriter().write("<h3>Ajouter des Titres</h3>");
					response.getWriter().write(
							"<h4>Titre</h4><input name='search' id= 'barreTitre1' type='text' placeholder='titre de la musique..'>");
					response.getWriter().write(
							"<h4>Interprete</h4><input name='search' id= 'barreTitre2' type='text' placeholder='nom de l'interprete..'>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
					response.getWriter().write("<button onclick ='ajoutTitres()'>Ajouter</button>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
				}

				response.getWriter().write("<table border=\"0\">" + "<tr border ='1'>\n"
						+ "	            <th>Titre</th>\n" + "	            <th>Auteur</th>\n" + "</tr>");

				if (resAdminMusiqueOrNo == 1) {
					for (ElementDeCatalogue cata : listElements) {
						String title = cata.getTitre();
						String author = cata.getInterprete();

						System.out.println(title);
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "	<td id='Auteur'>"
								+ author + "</td>\n"
								+ " <td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
								+ param1 + " " + title + " " + idMembreActuel
								+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button id='catTitres "
								+ title + " " + param2 + "' onclick ='deleteElement(this)'>supprimer</button></td>"
								+ "</tr>");
					}
					response.getWriter().write("</table>");
				} else {

					for (ElementDeCatalogue cata : listElements) {
						String title = cata.getTitre();
						String author = cata.getInterprete();

						System.out.println(title);
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "	<td id='Auteur'>"
								+ author + "</td>\n"
								+ " <td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td>"
								+ "</tr>");
					}
					response.getWriter().write("</table>");
				}
			}

		} else if (param3 != null) {

			if (resAdminMusiqueOrNo == 1) {

				System.out.println("param3: " + param3);
				response.getWriter().write("<h3>Ajouter des titres a cet album</h3>");
				response.getWriter().write(
						"<h4>Titre</h4><input name='search' id= 'barreTitreAlbum1' type='text' placeholder='titre de la musique..'>");
				response.getWriter().write(
						"<h4>Interprete</h4><input name='search' id= 'barreTitreAlbum2' type='text' placeholder='nom de l'interprete..'>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
				response.getWriter()
						.write("<button id='" + param3 + "' onclick ='ajoutTitresToAnAlbum(this)'>Ajouter</button>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
			}

			List<ElementDeCatalogue> listElements = catalogueElements.titreBelongingToAnAlbum(param3);
			System.out.println("Titre de l'album ->" + param3);

			if (listElements.isEmpty()) {
				response.getWriter().write("<h1> Album vide .. </h1> ");
				response.getWriter()
						.write("<button id=" + idMembreActuel + "onclick ='mesPlaylists(this)'>Retour</button>");
			}

			response.getWriter().write("<br>");
			response.getWriter().write("<br>");
			response.getWriter().write("<br>");

			response.getWriter().write("<table border=\"0\">" + "<tr>\n" + "	            <th>Titre</th>\n"
					+ "	            <th>Auteur</th>\n" + "</tr>");

			for (ElementDeCatalogue cata : listElements) {
				String title = cata.getTitre();
				String author = cata.getInterprete();

				System.out.println(title);
				response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "	<td>" + author + "</td>\n"
						+ "<td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td><td><button class='buttonElements' id='Titres "
						+ title + "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>" + "</tr>");
			}
			response.getWriter().write("</table>");

		} else if (param4 != null) {
			
			int i = 0;
			// execution de la requête
			List<Playlist> listPlaylist = catalogueElements.getTitresAlbumPersoMembre(Integer.parseInt(param4));

			response.getWriter().write(
					"<input name=\"search\" id= \"barre2\" type='text' placeholder='Nom de la playlist a creer ..'>\n"
							+ "		  <button class='buttonElements' onclick =\"newPlaylistMembre(this)\">Creer</button>");

			response.getWriter().write("" + "<br>" + "<br>");

			if (listPlaylist.isEmpty()) {
				response.getWriter().write("<h1> Aucune playlist de cree .. </h1> ");
			} else {

				response.getWriter().write(
						"<table border=\"0\">" + "<tr>\n" + "	            <th>Titre de la playlist</th>\n" + "</tr>");

				for (Playlist cata : listPlaylist) {

					String title = cata.getNom();

					response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n"
							+ "<td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\\\" class=\"fa\">&#xf04b;</i></button></td> <td><button class='buttonElements' id='"
							+ title
							+ "'onclick ='titresFromPlaylistPerso(this)'>parcourir</button></td> <td><button id='"
							+ title + "'onclick ='deletePlaylistPerso(this)'>supprimer</button></td>" + "</tr>");
					i = i + 1;
				}

				response.getWriter().write("</table>");

			}

		} else if (param5 != null) {

			// execution de la requête :
			
			int i = 0;
			
			catalogueElements.newPlaylistMembre(param5, idMembreActuel);

			System.out.println("nnnoo" + idMembreActuel);

			List<Playlist> listPlaylist = catalogueElements.getTitresAlbumPersoMembre(idMembreActuel);

			response.getWriter()
					.write("<input id= \"barre2\" type='text' placeholder='Nom de la playlist a creer ..'>\n"
							+ "		  <button class='buttonElements' onclick =\"newPlaylistMembre(this)\">Creer</button>");

			response.getWriter().write("" + "<br>" + "<br>");

			response.getWriter().write("<table border=\"0\">" + "<tr>\n" + "<th>Titre</th>\n" + "</tr>");

			for (Playlist cata : listPlaylist) {

				String title = cata.getNom();

				response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n"
						+ "<td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td>  <td><button class='buttonElements' id='"
						+ title + "' onclick ='titresFromPlaylistPerso(this)'>parcourir</button></td> <td><button id='"
						+ title + "'onclick ='deletePlaylistPerso(this)'>supprimer</button></td> " + "</tr>");
				i = i + 1;
			}

			response.getWriter().write("</table>");

		} else if (param6 != null && param7 != null) {

			// execution de la requête :

			System.out.println(idMembreActuel);

			List<Playlist> listPlaylist = catalogueElements.getTitresAlbumPersoMembre(idMembreActuel);

			response.getWriter().write("<h3> Choissisez la playlist dans laquel ajouter l'element .. </h3> ");

			response.getWriter().write("<table border=\"0\">");

			for (Playlist cata : listPlaylist) {

				String title = cata.getNom();

				response.getWriter()
						.write("<tr>\n" + "<td>" + title + "</td>\n" + "<td><button id='" + title
								+ " " + param6 + " " + param7
								+ "' onclick= 'goInsert(this)' >Ajouter dans cette playlist</button></td>" + "</tr>");
			}

			response.getWriter().write("</table>");

		} else if (param8 != null) {
			
			int i = 0;
			System.out.println("ID MEMBRE:" + idMembreActuel + "ET PARAM8:" + param8);

			List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsFromPlaylistPerso(idMembreActuel,
					param8);
			String nomPlaylistPerso = param8;

			response.getWriter().write("<table border=\"0\">" + "<tr>\n" + "	            <th>Titre</th>\n"
					+ "	            <th>Type</th>\n" + "</tr>");

			if (listElements.isEmpty()) {
				response.getWriter().write("<tr>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "</tr>");
			} else {
				for (ElementDeCatalogue cata : listElements) {
					String title = cata.getTitre();

					if (cata instanceof Album) {
						String author = cata.getInterprete();
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>Album</td>\n"
								+ "<td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
								+ title
								+ "'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td> <td><button class='buttonElements' id='"
								+ title + " " + nomPlaylistPerso
								+ "'onclick ='deleteTitreFromPlaylistPerso(this)'>supprimer</button></td>" + "</tr>");
					} else {
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>Titre musical</td>\n"
								+ "<td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td><td><button  id='"
								+ title + " " + nomPlaylistPerso
								+ "'onclick ='deleteTitreFromPlaylistPerso(this)'>supprimer</button></td>" + "</tr>");
					}
					i = i + 1;
				}

				response.getWriter().write("</table>");
			}

		} else if (paramAdminMusique9 != null && paramAdminMusique10 != null) {

			int i = 0;

			// Insertion du nouveau titre
			catalogueElements.addNewTitre(paramAdminMusique9, paramAdminMusique10);

			if (resAdminMusiqueOrNo == 1) {

				response.getWriter().write("<h3>Ajouter des Titres</h3>");
				response.getWriter().write(
						"<h4>Titre</h4><input name='search' id= 'barreTitre1' type='text' placeholder='titre de la musique..'>");
				response.getWriter().write(
						"<h4>Interprete</h4><input name='search' id= 'barreTitre2' type='text' placeholder='nom de l'interprete..'>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
				response.getWriter().write("<button onclick ='ajoutTitres()'>Ajouter</button>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
			}

			List<ElementDeCatalogue> listElements = catalogueElements.getAllTitres();

			if (listElements.isEmpty()) {
				response.getWriter().write("<tr>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "</tr>");
			}

			response.getWriter().write("<table border=\"0\">" + "<tr>\n" + "	            <th>Titre</th>\n"
					+ "	            <th>Auteur</th>\n" + "</tr>");

			for (ElementDeCatalogue cata : listElements) {
				String title = cata.getTitre();
				String author = cata.getInterprete();

				if (resAdminMusiqueOrNo == 1) {
					response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>" + author + "</td>\n"
							+ "<td><button hidden = 'true' id='jouer" + i
							+ "' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
							+ param1 + " " + title + " " + idMembreActuel
							+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button id='Titres "
							+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>");

					i = i + 1;
				} else {
					response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>" + author + "</td>\n"
							+ "<td><button hidden = 'true' id='jouer" + i
							+ "' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
							+ param1 + " " + title + " " + idMembreActuel
							+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>" + "</tr>");

					i = i + 1;
				}
			}

			response.getWriter().write("</table>");

		} else if (paramAdminMusique10 != null && paramAdminMusique11 != null) {

			int i = 0;
			
			catalogueElements.addNewAlbum(paramAdminMusique11, paramAdminMusique10);

			if (resAdminMusiqueOrNo == 1) {
				response.getWriter().write("<h3>Ajouter des Albums</h3>");
				response.getWriter().write(
						"<h4>Titre</h4><input name='search' id= 'barreAlbum1' type='text' placeholder='titre album..'>");
				response.getWriter().write(
						"<h4>Interprete</h4><input name='search' id= 'barreAlbum2' type='text' placeholder='nom de l'interprete..'>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
				response.getWriter().write("<button onclick ='ajoutAlbum()'>Ajouter</button>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
			}

			List<ElementDeCatalogue> listElements = catalogueElements.getAllAlbums();

			response.getWriter().write("<table border=\"0\">" + "<tr>\n" + "	            <th>Titre</th>\n"
					+ "	            <th>Auteur</th>\n" + "</tr>");

			if (listElements.isEmpty()) {
				response.getWriter().write("<tr>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "</tr>");
			}

			for (ElementDeCatalogue cata : listElements) {
				String title = cata.getTitre();
				String author = cata.getInterprete();

				System.out.println(title);

				if (resAdminMusiqueOrNo == 1) {
					response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>" + author + "</td>\n"
							+ "<td><button id= '" + title
							+ "'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td><td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
							+ param1 + " " + title
							+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button id='Albums "
							+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>");
				} else {
					response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>" + author + "</td>\n"
							+ "<td><button id= '" + title
							+ "'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td><td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
							+ param1 + " " + title
							+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>" + "</tr>");
				}
				
				i = i + 1;
			}

			response.getWriter().write("</table>");

		} else if (paramAdminMusique12 != null && paramAdminMusique13 != null && paramAdminMusique14 != null) {
			
			int i = 0;
			System.out.println("je rente ici ou pas");
			catalogueElements.addElementPlaylistPublic(paramAdminMusique12, paramAdminMusique13, paramAdminMusique14);
			List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsFromPlaylistPublic();

			response.getWriter()
					.write(" <h3 style = 'font-family:'comic sans ms' '>Modifier la playlist publique</h3>");

			response.getWriter().write("<h5>Titre :</h5>");
			response.getWriter()
					.write("<input name='search' id= 'TitreBarre1' type='text' placeholder='Saisir un titre'>");
			response.getWriter().write("<h5>Interprete :</h5>");
			response.getWriter().write(
					"<input name='search' id= 'InterpreteBarre2' type='text' placeholder='Saisir un interprete'>");
			response.getWriter().write("<h5>Type :</h5>");
			response.getWriter().write("<select id ='type'>");
			response.getWriter().write("<option value=1>Album</option>");
			response.getWriter().write("<option value=2>Titre</option>");
			response.getWriter().write("</select>");
			response.getWriter().write(" <br>");
			response.getWriter().write("<br>");

			response.getWriter().write("<button onclick ='ajoutElementPlaylistPublique()'>Ajouter</button>");

			response.getWriter().write("<h3> Decouvrez notre playlist du moment </h3>");
			response.getWriter().write("<br>");
			response.getWriter().write("<table border='0'>");
			response.getWriter().write(" <tr>" + "<th>Titre</th>" + "<th>Auteur</th>" + "<th>Type</th>" + "</tr>");

			for (ElementDeCatalogue cata : listElements) {
				String title = cata.getTitre();
				String author = cata.getInterprete();
				System.out.println("ahhhhhhhhhh ->" + title);

				if (cata instanceof Album) {

					response.getWriter().write("<tr>" + "<td>" + title + "</td>" + "<td>" + author + "</td>"
							+ "<td>Album</td>"
							+ "<td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <button id='"
							+ title
							+ "' onclick='goTitresAlbum(this)'>Parcourir l'album</button></td> <td><button id='playlistMomentAlbum "
							+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>" + "</tr>");
				} else {

					response.getWriter().write(" <tr>" + "<td>" + title + "</td>" + "<td>" + author + "</td>"
							+ "<td>Titre muscial</td>"
							+ "<td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='playlistMomentTitre "
							+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>");
				}
				
				i = i + 1;
			}

		} else if (paramAdminMusique15 != null && paramAdminMusique16 != null) {
			// Check le param Titres ou Albums
			if (paramAdminMusique16.equals("Titres")) {
				// Supprimer le titre puis re afficher
				catalogueElements.suppElementByAdmin(paramAdminMusique16, paramAdminMusique15);
				int i = 0;
				if (resAdminMusiqueOrNo == 1) {

					response.getWriter().write("<h3>Ajouter des Titres</h3>");
					response.getWriter().write(
							"<h4>Titre</h4><input name='search' id= 'barreTitre1' type='text' placeholder='titre de la musique..'>");
					response.getWriter().write(
							"<h4>Interprete</h4><input name='search' id= 'barreTitre2' type='text' placeholder='nom de l'interprete..'>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
					response.getWriter().write("<button onclick ='ajoutTitres()'>Ajouter</button>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
					response.getWriter().write("<br>");
				}

				List<ElementDeCatalogue> listElements = catalogueElements.getAllTitres();

				response.getWriter()
						.write("<table border=\"0\">" + "<tr>\n" + "	            <th>Titre</th>\n"
								+ "	            <th>Auteur</th>\n"

								+ "</tr>");

				if (listElements.isEmpty()) {
					response.getWriter().write("<tr>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "</tr>");
				}

				for (ElementDeCatalogue cata : listElements) {
					String title = cata.getTitre();
					String author = cata.getInterprete();

					if (resAdminMusiqueOrNo == 1) {
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>" + author + "</td>\n"
								+ "<td><button hidden = 'true' id='jouer" + i
								+ "' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
								+ param1 + " " + title + " " + idMembreActuel
								+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button id='Titres "
								+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>");

						i = i + 1;
					} else {
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>" + author + "</td>\n"
								+ "<td><button hidden = 'true' id='jouer" + i
								+ "' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
								+ param1 + " " + title + " " + idMembreActuel
								+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>" + "</tr>");

						i = i + 1;
					}
				}

				response.getWriter().write("</table>");

			} else if (paramAdminMusique16.equals("Albums")) {
				
				int i = 0;
				catalogueElements.suppElementByAdmin(paramAdminMusique16, paramAdminMusique15);

				response.getWriter().write("<h3>Ajouter des Albums</h3>");
				response.getWriter().write(
						"<h4>Titre</h4><input name='search' id= 'barreAlbum1' type='text' placeholder='titre album..'>");
				response.getWriter().write(
						"<h4>Interprete</h4><input name='search' id= 'barreAlbum2' type='text' placeholder='nom de l'interprete..'>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
				response.getWriter().write("<button onclick ='ajoutAlbum()'>Ajouter</button>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");

				List<ElementDeCatalogue> listElements = catalogueElements.getAllAlbums();

				response.getWriter().write("<table border=\"0\">" + "<tr>\n" + "	            <th>Titre</th>\n"
						+ "	            <th>Auteur</th>\n" + "</tr>");

				if (listElements.isEmpty()) {
					response.getWriter().write("<tr>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "</tr>");
				}

				for (ElementDeCatalogue cata : listElements) {
					String title = cata.getTitre();
					String author = cata.getInterprete();

					response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "<td>" + author + "</td>\n"
							+ "<td><button id= '" + title
							+ "'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td><td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"
							+ param1 + " " + title
							+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button id='Albums "
							+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>");
					
					i = i + 1;
				}

				response.getWriter().write("</table>");

			} else if (paramAdminMusique16.equals("playlistMomentTitre")
					|| paramAdminMusique16.equals("playlistMomentAlbum")) {
				
				int i = 0 ;
				
				System.out.println("je dois aller la param15 :" + paramAdminMusique15);
				catalogueElements.suppElementByAdmin(paramAdminMusique16, paramAdminMusique15);
				List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsFromPlaylistPublic();

				response.getWriter()
						.write(" <h3 style = 'font-family:'comic sans ms' '>Modifier la playlist publique</h3>");

				response.getWriter().write("<h5>Titre :</h5>");
				response.getWriter()
						.write("<input name='search' id= 'TitreBarre1' type='text' placeholder='Saisir un titre'>");
				response.getWriter().write("<h5>Interprete :</h5>");
				response.getWriter().write(
						"<input name='search' id= 'InterpreteBarre2' type='text' placeholder='Saisir un interprete'>");
				response.getWriter().write("<h5>Type :</h5>");
				response.getWriter().write("<select id ='type'>");
				response.getWriter().write("<option value=1>Album</option>");
				response.getWriter().write("<option value=2>Titre</option>");
				response.getWriter().write("</select>");
				response.getWriter().write(" <br>");
				response.getWriter().write("<br>");
				response.getWriter().write("<button onclick ='ajoutElementPlaylistPublique()'>Ajouter</button>");
				response.getWriter().write("<h3> Decouvrez notre playlist du moment </h3>");
				response.getWriter().write("<br>");
				response.getWriter().write("<table border='0'>");
				response.getWriter().write(" <tr>" + "<th>Titre</th>" + "<th>Auteur</th>" + "<th>Type</th>" + "</tr>");

				if (listElements.isEmpty()) {
					response.getWriter()
							.write("<tr>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "</tr>");
				}

				for (ElementDeCatalogue cata : listElements) {
					String title = cata.getTitre();
					String author = cata.getInterprete();
					System.out.println("ahhhhhhhhhh ->" + title);

					if (cata instanceof Album) {

						response.getWriter().write("<tr>" + "<td>" + title + "</td>" + "<td>" + author + "</td>"
								+ "<td>Album</td>"
								+ "<td><button>Jouer <i style= 'font-size:10px' class='fa'>&#xf04b;</i></button> <button id='"
								+ title
								+ "' onclick='goTitresAlbum(this)'>Parcourir l'album</button></td> <td><button id='playlistMomentAlbum "
								+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>"
								+ "</tr>");
					} else {

						response.getWriter().write(" <tr>" + "<td>" + title + "</td>" + "<td>" + author + "</td>"
								+ "<td>Titre muscial</td>"
								+ "<td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='playlistMomentTitre "
								+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>");
					}
					
					i = i + 1;
				}
			} else if (paramAdminMusique16.equals("catTitres") && paramAdminMusique17 != null) {
				
				int i = 0 ;
				
				catalogueElements.suppElementByAdmin(paramAdminMusique16, paramAdminMusique15);

				List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsOfCategorie("Titres",
						paramAdminMusique17);

				response.getWriter().write("<table border=\"0\">" + "<tr border ='1'>\n"
						+ "	            <th>Titre</th>\n" + "	            <th>Auteur</th>\n" + "</tr>");

				if (listElements.isEmpty()) {
					response.getWriter().write("<tr>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "</tr>");
				}

				if (resAdminMusiqueOrNo == 1) {
					for (ElementDeCatalogue cata : listElements) {
						String title = cata.getTitre();
						String author = cata.getInterprete();

						System.out.println(title);
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "	<td id='Auteur'>"
								+ author + "</td>\n"
								+ " <td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='catTitres "
								+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>");
						i = i + 1;
					}
					response.getWriter().write("</table>");
				} else {

					for (ElementDeCatalogue cata : listElements) {
						String title = cata.getTitre();
						String author = cata.getInterprete();

						System.out.println(title);
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "	<td id='Auteur'>"
								+ author + "</td>\n"
								+ " <td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td>"
								+ "</tr>");
						i = i +1;
					}
					response.getWriter().write("</table>");
				}

			} else if (paramAdminMusique16.equals("catAlbums") && paramAdminMusique17 != null) {
				
				int i = 0;
				List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsOfCategorie("Albums",
						paramAdminMusique17);

				catalogueElements.suppElementByAdmin(paramAdminMusique16, paramAdminMusique15);

				response.getWriter().write("<table border=\"0\">" + "<tr border ='1'>\n"
						+ "	            <th>Titre</th>\n" + "	            <th>Auteur</th>\n" + "</tr>");

				if (listElements.isEmpty()) {
					response.getWriter().write("<tr>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "</tr>");
				}

				if (resAdminMusiqueOrNo == 1) {
					for (ElementDeCatalogue cata : listElements) {
						String title = cata.getTitre();
						String author = cata.getInterprete();

						System.out.println(title);
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "	<td id='Auteur'>"
								+ author + "</td>\n" + " <td><button id='" + title
								+ "'onclick= 'goTitresAlbum(this)'>Parcourir l'album</button></td> <td><button  class='buttonElements' id='"
								+ param1 + " " + title
								+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='catAlbums "
								+ title + "'onclick ='deleteElement(this)'>supprimer</button></td>" + "</tr>");
						response.getWriter().write("</table>");
						
						i = i + 1;
					}
				} else {

					for (ElementDeCatalogue cata : listElements) {
						String title = cata.getTitre();
						String author = cata.getInterprete();

						System.out.println(title);
						response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "	<td id='Auteur'>"
								+ author + "</td>\n" + " <td><button id='" + title
								+ "'onclick= 'goTitresAlbum(this)'>Parcourir l'album</button></td> <td><button  class='buttonElements' id='"
								+ param1 + " " + title
								+ "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\\\" class=\"fa\">&#xf04b;</i></button></td>"
								+ "</tr>");
						response.getWriter().write("</table>");
						
						i = i + 1;
					}
				}
			}
		} else if (paramAdminMusique18 != null && paramAdminMusique19 != null && paramAdminMusique20 != null) {
			// Ajouter le titre en question :
			// 18 -> titre
			// 19 -> interprete
			// 20 -> actualPlaylist
			int i = 0;
			catalogueElements.addNewTitreToAnAlbum(paramAdminMusique18, paramAdminMusique19, paramAdminMusique20);

			List<ElementDeCatalogue> listElements = catalogueElements.titreBelongingToAnAlbum(paramAdminMusique20);

			if (resAdminMusiqueOrNo == 1) {

				System.out.println("param3: " + param3);
				response.getWriter().write("<h3>Ajouter des titres a cet album</h3>");
				response.getWriter().write(
						"<h4>Titre</h4><input name='search' id= 'barreTitreAlbum1' type='text' placeholder='titre de la musique..'>");
				response.getWriter().write(
						"<h4>Interprete</h4><input name='search' id= 'barreTitreAlbum2' type='text' placeholder='nom de l'interprete..'>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
				response.getWriter()
						.write("<button id='" + param3 + "' onclick ='ajoutTitresToAnAlbum(this)'>Ajouter</button>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
				response.getWriter().write("<br>");
			}

			System.out.println("Titre de l'album ->" + param3);

			if (listElements.isEmpty()) {
				response.getWriter()
						.write("<tr>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "<td>vide</td>\n" + "</tr>");
			} else {
				response.getWriter().write("<table border=\"0\">" + "<tr>\n" + "	            <th>Titre</th>\n"
						+ "	            <th>Auteur</th>\n" + "</tr>");
			}

			response.getWriter().write("<br>");
			response.getWriter().write("<br>");
			response.getWriter().write("<br>");

			for (ElementDeCatalogue cata : listElements) {
				String title = cata.getTitre();
				String author = cata.getInterprete();

				System.out.println(title);
				response.getWriter().write("<tr>\n" + "<td>" + title + "</td>\n" + "	<td>" + author + "</td>\n"
						+ "<td><button id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td><td><button id='Titres "
						+ title + "' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>" + "</tr>");
				i = i + 1;
			}
			response.getWriter().write("</table>");

		} else { // La page d'acceuil avec la playlist public en quelque sorte

			List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsFromPlaylistPublic();

			request.setAttribute("listElements", listElements);

			String pageName = "/Catalogue.jsp";

			RequestDispatcher rd = getServletContext().getRequestDispatcher(pageName);

			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		CatalogueService catalogueElements = new CatalogueServiceImpl();
		HttpSession maSession = request.getSession();
		Membre elMembre = (Membre) maSession.getAttribute("membreConnecte");
		Visiteur elVisiteur = (Visiteur)maSession.getAttribute("visiteurConnecte");
		
		if(elMembre != null) {
			int idMembreActuel = elMembre.getId();
		
			
	
			// nt idMembreActuel = alex.getId();
			// int idMembreActuel = kevin.getId();
	
			String param1 = request.getParameter("nomPlaylist");
			String param2 = request.getParameter("section");
			String param3 = request.getParameter("titre");
			String param4 = request.getParameter("TitreFromPlaylistPerso");
			String param5 = request.getParameter("PlaylistPerso");
			// String param6 = request.getParameter("nomPlaylistPerso");
	
			if (param1 != null && param2 != null && param3 != null && param4 == null && param5 == null) {
			    catalogueElements = new CatalogueServiceImpl();
				catalogueElements.newElementInPrivatePlaylist(param1, param2, param3);
				response.getWriter().write("<h3> Votre element a bien ete insere dans la playlist :" + param1 + " </h3> ");
				//response.getWriter().write("<button id="+idMembreActuel+" onclick ='MainServlet'>Acceuil</button>");
				response.getWriter().write("<form action = 'MainServlet'>"
						+ "<button <input type='submit'value='Go to Google'/>Accueil</button>"
						+ " </form>");
			    
	
			} else if (param5 != null && param2 == null && param3 == null && param4 == null && param1 == null) {
			    catalogueElements = new CatalogueServiceImpl();
				catalogueElements.deletePlaylistMembre(param5);
				response.getWriter().write("<h3> Votre playlist :" + param5 + " a bien ete supprime</h3> ");
				response.getWriter().write("<button id="+idMembreActuel+" onclick ='mesPlaylists(this)'>Retour</button>");
	
			} else if (param4 != null && param5 != null && param1 == null && param2 == null && param3 == null) {
				
				/*CatalogueService catalogueElements = new CatalogueServiceImpl();
				HttpSession maSession = request.getSession();
				Membre elMembre = (Membre) maSession.getAttribute("membreConnecte");
				int idMembreActuel = elMembre.getId();
				System.out.println("valeur param" + param5);*/
				catalogueElements.deleteTitreFromPlaylistPerso(idMembreActuel, param4, param5);
				response.getWriter().write("<h3> Le titre '" + param4 + "' a bien ete supprime</h3> ");
				response.getWriter().write("<button id="+idMembreActuel+" onclick ='mesPlaylists(this)'>Retour</button>");
			} else {
	
				this.doGet(request, response);
			}
		}else {
			
			this.doGet(request, response);
		}
	}
}
