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
		  MADAME,
		  MONSIEUR,
		  NE_SE_PRONONCE_PAS,
		  NON_BINAIRE
		}

	
	public enum preference {
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


		HttpSession maSession = request.getSession();
		Membre elMembre = (Membre)maSession.getAttribute("membreConnecte");
		elMembre = Membre.getMembre(elMembre.getEmail());
		int idMembreActuel = elMembre.getId();
		
		CatalogueService catalogueElements = new CatalogueServiceImpl();
		//System.out.println(elMembre.getCivilite());
		
		int res = catalogueElements.isAnAdminCompte(idMembreActuel);
		request.setAttribute("res",res);
		
		
		String param1 = request.getParameter("nomElement");
		String param2 = request.getParameter("categorie");
		String param3 = request.getParameter("interprete");
		String param4 = request.getParameter("mesPlaylists"); // Parcourir ses playlists 
		String param5 = request.getParameter("nomPlaylist"); // Creation d'une nouvelle playlist personelle
		
		// Ses deux paramètre sont utiles pour garder sous le coude quel element il faut ajouter dans la playlist 
		String param6 = request.getParameter("section"); // Nom de la section ( titres , albums ... ) 
		String param7 = request.getParameter("chanteur"); // Nom de l'auteur du titre ici 
		
		String param8 = request.getParameter("titresFromPlaylistPerso"); // A pour but d'aller chercher toutes les musiques relatives à une playlist Perso 
		
		
		if(param1 != null && param2 == null && param3 == null) {
			
				int i = 0;
				if(param1.equals("Titres")) {
	
					
					List<ElementDeCatalogue> listElements = catalogueElements.getAllTitres();
					
					response.getWriter().write("<table border=\"0\">"
							+ "<tr>\n"
							+ "	            <th>Titre</th>\n"
							+ "	            <th>Auteur</th>\n"
							+ "</tr>");
					
					for (ElementDeCatalogue cata:listElements) {
			     		 String title = cata.getTitre();
			      		 String author = cata.getInterprete();
			      		 
	
						response.getWriter().write("<tr>\n"
									+ "<td>"+title+"</td>\n"
									+ "<td>"+author+"</td>\n"
									+ "<td><button hidden = 'true' id='jouer"+i+"' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button style='color : red' id='"+param1+" "+title+" "+idMembreActuel+"' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>"
									+ "</tr>");
						
						i = i + 1;
					}
					
					response.getWriter().write("</table>");
					
				}else if(param1.equals("Albums")) {
					
					System.out.println("okkk");
					
					List<ElementDeCatalogue> listElements = catalogueElements.getAllAlbums();
					
					response.getWriter().write("<table border=\"0\">"
							+ "<tr>\n"
							+ "	            <th>Titre</th>\n"
							+ "	            <th>Auteur</th>\n"
							+ "</tr>");
					
					for (ElementDeCatalogue cata:listElements) {
			     		 String title = cata.getTitre();
			      		 String author = cata.getInterprete();
			      		 
			      		System.out.println(title);
						response.getWriter().write("<tr>\n"
									+ "<td>"+title+"</td>\n"
									+ "<td>"+author+"</td>\n"
									+ "<td><button id= '"+title+"'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td><td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button class='buttonElements' id='"+param1+" "+title+"' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>"
									+ "</tr>");
					}
					
					response.getWriter().write("</table>");
					
				}else { // On sait qu'on est dans la barre de recherche 
			
				System.out.println("okkkPodcast la ");

				
				System.out.println("recherche:"+param1);
				
				List<ElementDeCatalogue> listElements = catalogueElements.getElementByresearch(param1);
				
				// Si la recherche n'a rien donné : 
				
				if(listElements.isEmpty())
				{
					response.getWriter().write("<h1 color='red'> Aucun titre n'a ete trouve"
							+ "</h1>");
				}else {
				
					 response.getWriter().write("<table border=\"0\">"
								+ "<tr>\n"
								+ "	            <th>Titre</th>\n"
								+ "	            <th>Type</th>\n"
								+ "</tr>");
						
						for (ElementDeCatalogue cata:listElements) {
				     		 String title = cata.getTitre();
				      		 
				      		if(cata instanceof Album)
				      		{
				      			String author = cata.getInterprete();
				      			response.getWriter().write("<tr>\n"
										+ "<td>"+title+"</td>\n"
										+ "<td>Album</td>\n"
										+ "<td><button id='jouer' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"+title+"'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td>"
										+ "</tr>");
				      		}else {
							response.getWriter().write("<tr>\n"
										+ "<td>"+title+"</td>\n"
										+ "<td>Titre musical</td>\n"
										+ "<td class='buttonElements'><button class='buttonElements'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td>"
										+ "</tr>");
				      		}
						}
						
						response.getWriter().write("</table>");
				}
			}
		}else if(param2 != null) {

			List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsOfCategorie(param1,param2);
			
			// Si la recherche n'a rien donné : 
			
			if(listElements.isEmpty())
			{
				response.getWriter().write("<h1 color='red'> Aucun titre n'a ete trouve"
						+ "</h1>");
				
			}else if(param1.equals("Albums")) {
			
				response.getWriter().write("<table border=\"0\">"
						+ "<tr border ='1'>\n"
						+ "	            <th>Titre</th>\n"
						+ "	            <th>Auteur</th>\n"
						+ "</tr>");
				
				for (ElementDeCatalogue cata:listElements) {
		     		 String title = cata.getTitre();
		      		 String author = cata.getInterprete();
		      		 
		      		System.out.println(title);
					response.getWriter().write("<tr>\n"
								+ "<td>"+title+"</td>\n"
								+ "	<td id='Auteur'>"+author+"</td>\n"
								+ " <td><button id='"+title+"'onclick= 'goTitresAlbum(this)'>Parcourir l'album</button></td> <td><button  class='buttonElements' id='"+param1+" "+title+"' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td>"
								+ "</tr>");
				}
				response.getWriter().write("</table>");
			
			
			}else {
			
				response.getWriter().write("<table border=\"0\">"
						+ "<tr border ='1'>\n"
						+ "	            <th>Titre</th>\n"
						+ "	            <th>Auteur</th>\n"
						+ "</tr>");
				
				for (ElementDeCatalogue cata:listElements) {
		     		 String title = cata.getTitre();
		      		 String author = cata.getInterprete();
		      		 
		      		System.out.println(title);
					response.getWriter().write("<tr>\n"
								+ "<td>"+title+"</td>\n"
								+ "	<td id='Auteur'>"+author+"</td>\n"
								+ " <td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td>"
								+ "</tr>");
				}
				response.getWriter().write("</table>");
			}
		}else if(param3 != null) {
			
			List<ElementDeCatalogue> listElements = catalogueElements.titreBelongingToAnAlbum(param3);
			System.out.println("Titre de l'album ->"+param3);
			response.getWriter().write("<table border=\"0\">"
					+ "<tr>\n"
					+ "	            <th>Titre</th>\n"
					+ "	            <th>Auteur</th>\n"
					+ "</tr>");
			
			for (ElementDeCatalogue cata:listElements) {
	     		 String title = cata.getTitre();
	      		 String author = cata.getInterprete();
	      		 
	      		System.out.println(title);
				response.getWriter().write("<tr>\n"
							+ "<td>"+title+"</td>\n"
							+ "	<td>"+author+"</td>\n"
							+ "<td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td><td><button class='buttonElements' id='Titres "+title+"' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>"
							+ "</tr>");
			}
			response.getWriter().write("</table>");
			
			
		}else if(param4!=null ) {
			
		
			  // execution de la requête 
				  List<Playlist> listPlaylist = catalogueElements.getTitresAlbumPersoMembre(Integer.parseInt(param4));
				  
				  
				  response.getWriter().write("<input name=\"search\" id= \"barre2\" type='text' placeholder='Nom de la playlist a creer ..'>\n"
				  		+ "		  <button class='buttonElements' onclick =\"newPlaylistMembre(this)\">Creer</button>");
				  
				  response.getWriter().write(""
				  		+ "<br>"
				  		+ "<br>");
				  
				  if(listPlaylist.isEmpty())
				  {
					  response.getWriter().write("<h1> Aucune playlist de cree .. </h1> ");
				  }else 
				  {
				  
					  response.getWriter().write("<table border=\"0\">"
								+ "<tr>\n"
								+ "	            <th>Titre de la playlist</th>\n"
								+ "</tr>");
						
						for (Playlist cata:listPlaylist) {
							
							
				     		 String title = cata.getNom();
				      
							 response.getWriter().write("<tr>\n"
										+ "<td>"+title+"</td>\n"
										+ "<td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button class='buttonElements' id='"+title+"'onclick ='titresFromPlaylistPerso(this)'>parcourir</button></td> <td><button id='"+title+"'onclick ='deletePlaylistPerso(this)'>supprimer</button></td>"
										+ "</tr>");
						}
						
						response.getWriter().write("</table>");
				  
				  }
			
			
		}else if(param5!=null ) {
			
			  // execution de la requête :

			  
			  catalogueElements.newPlaylistMembre(param5,idMembreActuel);
			  
			  System.out.println("nnnoo"+idMembreActuel);
			  
			  List<Playlist> listPlaylist = catalogueElements.getTitresAlbumPersoMembre(idMembreActuel);
			  
			  
			  response.getWriter().write("<input id= \"barre2\" type='text' placeholder='Nom de la playlist a creer ..'>\n"
			  		+ "		  <button class='buttonElements' onclick =\"newPlaylistMembre(this)\">Creer</button>");
			  
			  response.getWriter().write(""
			  		+ "<br>"
			  		+ "<br>");
			  
			  response.getWriter().write("<table border=\"0\">"
						+ "<tr>\n"
						+ "<th>Titre</th>\n"
						+ "</tr>");
				
				for (Playlist cata:listPlaylist) {
					
					
		     		 String title = cata.getNom();
		      
					 response.getWriter().write("<tr>\n"
								+ "<td>"+title+"</td>\n"
								+ "<td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td>  <td><button class='buttonElements' id='"+title+"' onclick ='titresFromPlaylistPerso(this)'>parcourir</button></td> <td><button id='"+title+"'onclick ='deletePlaylistPerso(this)'>supprimer</button></td> "
								+ "</tr>");
				}
				
				response.getWriter().write("</table>");
			  
		}else if(param6!=null && param7 != null) {
				
				  // execution de la requête :

				  
				  System.out.println(idMembreActuel);
				  
				  List<Playlist> listPlaylist = catalogueElements.getTitresAlbumPersoMembre(idMembreActuel);
				  
				  response.getWriter().write("<h3> Choissisez la playlist dans laquel ajouter l'element .. </h3> ");
				  
				  response.getWriter().write("<table border=\"0\">");
				  
					
					for (Playlist cata:listPlaylist) {
						
			     		 String title = cata.getNom();
			      
						 response.getWriter().write("<tr>\n"
									+ "<td>"+title+"</td>\n"
									+ "<td><button class='buttonElements' id='"+title+" "+param6+" "+param7+"' onclick= 'goInsert(this)' >Ajouter dans cette playlist</button></td>"
									+ "</tr>");
					}
					
					response.getWriter().write("</table>");
					
		}else if(param8 != null) {
				
				  // execution de la requête :
				  System.out.println("id membre actu:"+idMembreActuel);
				  List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsFromPlaylistPerso(idMembreActuel,param8);
				 
				  System.out.println(idMembreActuel);
				  
				  String nomPlaylistPerso = param8;
				  
				  for (ElementDeCatalogue cata:listElements) {
			     		 String title = cata.getTitre();
			     		 System.out.println(title);
			     		 System.out.println("OUAIIIIIIIIIIIIIIS");
				  }
				  
				  if(listElements.isEmpty() || listElements == null)
				  {
					  response.getWriter().write("<h1> Playlist vide .. </h1> ");
					  response.getWriter().write("<button id="+idMembreActuel+"onclick ='mesPlaylists(this)'>Retour</button>");
				  }else 
				  {
					  for (ElementDeCatalogue cata:listElements) {
				     		 String title = cata.getTitre();
				     		 System.out.println(title);
					  }
					  
					 response.getWriter().write("<table border=\"0\">"
								+ "<tr>\n"
								+ "	            <th>Titre</th>\n"
								+ "	            <th>Type</th>\n"
								+ "</tr>");
						
						for (ElementDeCatalogue cata:listElements) {
				     		 String title = cata.getTitre();
				      		 
				      		if(cata instanceof Album)
				      		{
				      			String author = cata.getInterprete();
				      			response.getWriter().write("<tr>\n"
										+ "<td>"+title+"</td>\n"
										+ "<td>Album</td>\n"
										+ "<td><button id='jouer' onclick='goSwitch(this)'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"+title+"'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td> <td><button class='buttonElements' id='"+title+" "+nomPlaylistPerso+"'onclick ='deleteTitreFromPlaylistPerso(this)'>supprimer</button></td>"
										+ "</tr>");
				      		}else {
							response.getWriter().write("<tr>\n"
										+ "<td>"+title+"</td>\n"
										+ "<td>Titre musical</td>\n"
										+ "<td class='buttonElements'><button class='buttonElements'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button  id='"+title+" "+nomPlaylistPerso+"'onclick ='deleteTitreFromPlaylistPerso(this)'>supprimer</button></td>"
										+ "</tr>");
				      		}
						}
						
						response.getWriter().write("</table>");
				  }
				  
		}else { // La page d'acceuil avec la playlist public en quelque sorte 
				
			System.out.println("okkkMusique");
	
			
			//request.getSize
			
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		Membre alex = new Membre(1,"THOMAS","Eliott");
		Membre kevin = new Membre(13,"Dupond","Charles");
		
		//nt idMembreActuel = alex.getId();
		//int idMembreActuel = kevin.getId();
		
		
		String param1 = request.getParameter("nomPlaylist");
		String param2 = request.getParameter("section");
		String param3 = request.getParameter("titre");
		String param4 = request.getParameter("TitreFromPlaylistPerso");
		String param5 = request.getParameter("PlaylistPerso");
		//String param6 = request.getParameter("nomPlaylistPerso");
		
		
		if(param1 != null && param2 != null && param3 != null && param4 ==null && param5 == null )
		{
			CatalogueService catalogueElements = new CatalogueServiceImpl();
			catalogueElements.newElementInPrivatePlaylist(param1,param2,param3);
			response.getWriter().write("<h3> Votre element a bien ete insere dans la playlist :"+param1+" </h3> ");
			
		}else if (param5 != null && param2 == null && param3 == null && param4 == null  && param1 == null)
		{
			CatalogueService catalogueElements = new CatalogueServiceImpl();
			  catalogueElements.deletePlaylistMembre(param5);
			  response.getWriter().write("<h3> Votre playlist :"+param5+" a bien ete supprime</h3> ");
			  response.getWriter().write("<button onclick ='mesPlaylists(this)'>Retour</button>");
			  
		}else if(param4 != null && param5 !=null && param1 == null && param2 == null && param3 == null)
		{
			CatalogueService catalogueElements = new CatalogueServiceImpl();
			HttpSession maSession = request.getSession();
			Membre elMembre = (Membre)maSession.getAttribute("leMembre");
			int idMembreActuel = elMembre.getId();
			catalogueElements.deleteTitreFromPlaylistPerso(idMembreActuel, param4,param5);
			response.getWriter().write("<h3> Le titre '"+param4+"' a bien ete supprime</h3> ");
			response.getWriter().write("<button onclick ='mesPlaylists(this)'>Retour</button>");
		}else {
			
			this.doGet(request, response);
		}
	}
}
