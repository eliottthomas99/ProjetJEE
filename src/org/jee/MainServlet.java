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

		// param1S Interface
		
		// Test : on va supposer qu'on connait déjà 2 membres 
		
		
		// Récupérer les paramètre dans jsp d'un des deux ou les deux 
		Membre alex = new Membre(1,"THOMAS","Eliott");
		Membre kevin = new Membre(13,"Dupond","Charles");
		
		int idAlex = alex.getId();
		//int idAlex = kevin.getId();
		
		request.setAttribute("alex", alex);
		request.setAttribute("kevin", kevin);
		
		
		String param1 = request.getParameter("nomElement");
		String param2 = request.getParameter("categorie");
		String param3 = request.getParameter("interprete");
		String param4 = request.getParameter("mesPlaylists"); // Parcourir ses playlists 
		String param5 = request.getParameter("nomPlaylist"); // Creation d'une nouvelle playlist personelle
		
		// Ses deux paramètre sont utiles pour garder sous le coude quel element il faut ajouter dans la playlist 
		String param6 = request.getParameter("section"); // Nom de la section ( titres , albums ... ) 
		String param7 = request.getParameter("chanteur"); // Nom de l'auteur du titre ici 
		
		String param8 = request.getParameter("titresFromPlaylistPerso"); // A pour but d'aller chercher toutes les musiques relatives à une playlist Perso 
		
		System.out.println("param1---->"+param1);
		System.out.println("param2---->"+param2);
		
		if(param1 != null && param2 == null && param3 == null) {
			
				if(param1.equals("Titres")) {
					
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
									+ "<td>"+title+"</td>\n"
									+ "<td>"+author+"</td>\n"
									+ "<td><button onmouseover ='Visuelle()'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button class='buttonElements' id='"+param1+" "+title+"' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>"
									+ "</tr>");
					}
					
					response.getWriter().write("</table>");
					
				}else if(param1.equals("Albums")) {
					
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
									+ "<td>"+title+"</td>\n"
									+ "<td>"+author+"</td>\n"
									+ "<td><button id= '"+title+"'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td><td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button class='buttonElements' id='"+param1+" "+title+"' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>"
									+ "</tr>");
					}
					
					response.getWriter().write("</table>");
					
				}else { // On sait qu'on est dans la barre de recherche 
			
				System.out.println("okkkPodcast la ");
			
				CatalogueService catalogueElements = new CatalogueServiceImpl();
				
				System.out.println("recherche:"+param1);
				
				List<ElementDeCatalogue> listElements = catalogueElements.getElementByTitle(param1);
				
				// Si la recherche n'a rien donné : 
				
				if(listElements.isEmpty())
				{
					response.getWriter().write("<h1 color='red'> Aucun titre n'a ete trouve"
							+ "</h1>");
				}else {
				
					response.getWriter().write("<table border=\"1\">"
							+ "<tr>\n"
							+ "	            <td>Titre</td>\n"
							+ "	            <td>Auteur</td>\n"
							+ "</tr>");
					
					for (ElementDeCatalogue cata:listElements) {
			     		 String title = cata.getTitre();
			      		 String author = cata.getInterprete();
			      		 
			      		System.out.println(title);
						response.getWriter().write("<tr>\n"
									+ "<td>"+title+"</td>\n"
									+ "<td id='Auteur'>"+author+"</td>\n"
									+ "<td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td>"
									+ "</tr>");
					}
					response.getWriter().write("</table>");
				}
			}
		}else if(param2 != null) {
			
			System.out.println("okk DEUXIEME PARAM la ");
			
			CatalogueService catalogueElements = new CatalogueServiceImpl();
			
			
			List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsOfCategorie(param1,param2);
			
			// Si la recherche n'a rien donné : 
			
			if(listElements.isEmpty())
			{
				response.getWriter().write("<h1 color='red'> Aucun titre n'a ete trouve"
						+ "</h1>");
				
			}else if(param1.equals("Albums")) {
			
				response.getWriter().write("<table border=\"1\">"
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
								+ " <td><button id='"+title +" onclick= 'goTitresAlbum(this)'>Parcourir l'album</button></td> <td><button  class='buttonElements' id='"+param1+" "+title+"' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td> <td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td>"
								+ "</tr>");
				}
				response.getWriter().write("</table>");
			
			
			}else {
			
				response.getWriter().write("<table border=\"1\">"
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
			
			CatalogueService catalogueElements = new CatalogueServiceImpl();
			List<ElementDeCatalogue> listElements = catalogueElements.titreBelongingToAnAlbum(param3);
			System.out.println("Titre de l'album ->"+param3);
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
							+ "<td>"+title+"</td>\n"
							+ "	<td>"+author+"</td>\n"
							+ "<td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td><td><button class='buttonElements' id='Titres "+title+"' onclick ='choosePlaylist(this)'>Ajouter a une playlist</button></td>"
							+ "</tr>");
			}
			response.getWriter().write("</table>");
			
			
		}else if(param4!=null ) {
			
		
			if(param4.equals("aChanger"))
			{
			 
			  // execution de la requête :
				  CatalogueService catalogueElements = new CatalogueServiceImpl();
				  List<Playlist> listPlaylist = catalogueElements.getTitresAlbumPersoMembre(idAlex);
				  
				  
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
				  
					  response.getWriter().write("<table border=\"1\">"
								+ "<tr>\n"
								+ "	            <th>Titre</th>\n"
								+ "</tr>");
						
						for (Playlist cata:listPlaylist) {
							
							
				     		 String title = cata.getNom();
				      
							 response.getWriter().write("<tr>\n"
										+ "<td>"+title+"</td>\n"
										+ "<td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button class='buttonElements' id='"+title+"'onclick ='titresFromPlaylistPerso(this)'>parcourir</button></td> <td><button class='buttonElements' id='"+title+"'onclick ='deletePlaylistPerso(this)'>supprimer</button></td>"
										+ "</tr>");
						}
						
						response.getWriter().write("</table>");
				  
				}
			}
		}else if(param5!=null ) {
			
			  // execution de la requête :
			  CatalogueService catalogueElements = new CatalogueServiceImpl();
			  
			  catalogueElements.newPlaylistMembre(param5,idAlex);
			  
			  System.out.println(idAlex);
			  
			  List<Playlist> listPlaylist = catalogueElements.getTitresAlbumPersoMembre(idAlex);
			  
			  
			  response.getWriter().write("<input id= \"barre2\" type='text' placeholder='Nom de la playlist a creer ..'>\n"
			  		+ "		  <button class='buttonElements' onclick =\"newPlaylistMembre(this)\">Creer</button>");
			  
			  response.getWriter().write(""
			  		+ "<br>"
			  		+ "<br>");
			  
			  response.getWriter().write("<table border=\"1\">"
						+ "<tr>\n"
						+ "<th>Titre</th>\n"
						+ "</tr>");
				
				for (Playlist cata:listPlaylist) {
					
					
		     		 String title = cata.getNom();
		      
					 response.getWriter().write("<tr>\n"
								+ "<td>"+title+"</td>\n"
								+ "<td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td>  <td><button class='buttonElements' id='"+title+"'onclick ='titresFromPlaylistPerso(this)'>parcourir</button></td> <td><button class='buttonElements' id='"+title+"'onclick ='deletePlaylistPerso(this)'>supprimer</button></td> "
								+ "</tr>");
				}
				
				response.getWriter().write("</table>");
			  
		}else if(param6!=null && param7 != null) {
				
				  // execution de la requête :
				  CatalogueService catalogueElements = new CatalogueServiceImpl();
				  
				  System.out.println(idAlex);
				  
				  List<Playlist> listPlaylist = catalogueElements.getTitresAlbumPersoMembre(idAlex);
				  
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
				  CatalogueService catalogueElements = new CatalogueServiceImpl();
				  
				  List<ElementDeCatalogue> listElements = catalogueElements.getAllElementsFromPlaylistPerso(idAlex,param8);
				 
				  System.out.println(idAlex);
				  
				  String nomPlaylistPerso = param8;
				  
				  if(listElements.isEmpty() || listElements == null)
				  {
					  response.getWriter().write("<h1> Playlist vide .. </h1> ");
					  response.getWriter().write("<button onclick ='mesPlaylists(this)'>Retour</button>");
				  }else 
				  {
					  for (ElementDeCatalogue cata:listElements) {
				     		 String title = cata.getTitre();
				     		 System.out.println(title);
					  }
					  
					 response.getWriter().write("<table border=\"1\">"
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
										+ "<td><button>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button id='"+title+"'onclick='goTitresAlbum(this)'>Parcourir l'album</button></td> <td><button class='buttonElements' id='"+title+" "+nomPlaylistPerso+"'onclick ='deleteTitreFromPlaylistPerso(this)'>supprimer</button></td>"
										+ "</tr>");
				      		}else {
							response.getWriter().write("<tr>\n"
										+ "<td>"+title+"</td>\n"
										+ "<td>Titre musical</td>\n"
										+ "<td class='buttonElements'><button class='buttonElements'>Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i></button></td> <td><button class='buttonElements' id='"+title+" "+nomPlaylistPerso+"'onclick ='deleteTitreFromPlaylistPerso(this)'>supprimer</button></td>"
										+ "</tr>");
				      		}
						}
						
						response.getWriter().write("</table>");
				  }
				  
			}else {
		
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
		
		int idAlex = alex.getId();
		//int idAlex = kevin.getId();
		
		
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
			catalogueElements.deleteTitreFromPlaylistPerso(idAlex, param4,param5);
			response.getWriter().write("<h3> Le titre '"+param4+"' a bien ete supprime</h3> ");
			response.getWriter().write("<button onclick ='mesPlaylists(this)'>Retour</button>");
		}
	}
}
