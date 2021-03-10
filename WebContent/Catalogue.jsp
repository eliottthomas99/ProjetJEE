<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.List,org.jee.ElementDeCatalogue"%>
<%@page import="java.util.List,org.jee.CatalogueServiceImpl"%>
<%@page import="java.util.List,org.jee.Membre"%>
<%@page import="java.util.List,org.jee.Album"%>
<%
List<ElementDeCatalogue> listElements = (List<ElementDeCatalogue>)request.getAttribute("listElements");


int res = (int)request.getAttribute("res");

// On récupère les données des membres 
//Membre alex = (Membre)request.getAttribute("alex");

HttpSession maSession = request.getSession();
Membre elMembre = (Membre)maSession.getAttribute("leMembre");

System.out.println(elMembre);
%>  

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalogue</title>
<script src= "catalogueContenu.js"></script>
 <link type="text/css" rel="stylesheet" href="catalogue.css" />
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<h1 id="musical">Catalogue Musical</h1>
	<nav id="primary_nav_wrap">
		<ul>
		  <li class="current-menu-item"><a href="MainServlet">La playlist du moment</a></li>
		  
		  <li><li id = "album"><a onclick ="mesPlaylists(this)">Mes playlists</a></li>
		  
		  <li><a href="#" id ="Titremusical" onclick ="goAccueil(this)">Titres</a>
		    <ul id = "Titres">
		      <li>
		      	<a href="#" onclick ="goAccueil(this)">Rock</a>
		      </li>
		      <li>
		      	<a href="#" onclick ="goAccueil(this)">Rap Francais</a></li>
		      <li>
		      	<a href="#" onclick ="goAccueil(this)">Rap US</a></li>
		      <li>
		      	<a href="#" onclick ="goAccueil(this)">Classique</a></li>
		    </ul>
		  </li>
		  <li id = "album"><a  onclick ="goAccueil(this)">Albums</a>
		    <ul id = "Albums">
		      <li>
		      	<a href="#" onclick ="goAccueil(this)">Rock</a></li>
		      <li>
		      	<a href="#" onclick ="goAccueil(this)">Rap Francais</a></li>
		      <li>
		      	<a href="#" onclick ="goAccueil(this)">Rap US</a></li>
		      <li>
		      	<a href="#" onclick ="goAccueil(this)">Classique</a></li>
		    </ul>
		  </li>
		  
		  <li id = "album"><a onclick ="mesPlaylists(this)">Mon profil</a></li>
		  <%
				if (res == 1)
				{
		  %>
		  
		  	<li id = "album"><a onclick ="mesPlaylists(this)">Gestion Clients</a></li>
		  
		  <%
				}else{}
		  %>
		  <li><input name="search" id= "barre" type='text' placeholder='Rechercher un element avec son titre ..'></input>
		  <button onclick ="goAccueil(this)">Recherche</button></li>
		  
		</ul>
		 
	</nav>
	
	
	<br>
	<br>
	<br>
	
	<div id = "titreElem">
	
		<h3> Decouvrez notre playlist du moment </h3>
		<br>
		<table border="0">
	      <tr>
	            <th>Titre</th>
	            <th>Auteur</th>
	            <th>Type</th>
	      </tr>
				<%
				for (ElementDeCatalogue cata:listElements) {
	    		 String title = cata.getTitre();
	     		 String author = cata.getInterprete();
		      	%>
		      	
		      	<%
		      	if(cata instanceof Album)
		      		{
		      	%>
			      	 <tr>
					      <td><%=title%></td>
					      <td><%=author %></td>
					      <td>Album</td>
					      <td><button>Jouer <i style= 'font-size:10px' class='fa'>&#xf04b;</i></button> <button id="<%=title%>" onclick="goTitresAlbum(this)">Parcourir l'album</button></td>
					 </tr>
	      		<%
				}else{
				%>
					  <tr>
					      <td><%=title %></td>
					      <td><%=author%></td>
					      <td>Titre muscial</td>
					      <td><button>Jouer <i style='font-size:10px'class='fa'>&#xf04b;</i></button></td>
					 </tr>
				<%
				}
				%>
			<%
			}
			%>
		</table>	
</div>
	

	
</body>
</html>
