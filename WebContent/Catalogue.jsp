<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.List,org.jee.ElementDeCatalogue"%>
<%@page import="java.util.List,org.jee.Membre"%>
<%
List<ElementDeCatalogue> listElements = (List<ElementDeCatalogue>)request.getAttribute("listElements");

// On récupère les données des membres 
Membre alex = (Membre)request.getAttribute("alex");

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
		  <li class="current-menu-item"><a href="accueil.jsp">Accueil</a></li>
		  
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
		  
		  <li></li><input name="search" id= "barre" type='text' placeholder='Rechercher un element avec son titre ..'>
		  <button class = onclick ="goAccueil(this)">Recherche</button>
		</ul>
	</nav>
	
	
	<br>
	<br>
	<br>
	
	<div id = "titreElem"></div>
	

	
</body>
</html>
