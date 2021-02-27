<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.List,org.jee.ElementDeCatalogue"%>
<%
List<ElementDeCatalogue> listElements = (List<ElementDeCatalogue>)request.getAttribute("listElements");
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalogue</title>
<script src= "catalogueContenu.js"></script>
 <link type="text/css" rel="stylesheet" href="catalogue.css" />
</head>
<body>
	<h1 id="musical">Catalogue Musical</h1>
	<nav id="primary_nav_wrap">
		<ul>
		  <li class="current-menu-item"><a href="accueil.jsp">Accueil</a></li>
		  <li><a href="#"></a>
		    <ul>
		      <li><a href="#">Sub Menu 1</a></li>
		      <li><a href="#">Sub Menu 2</a></li>
		      <li><a href="#">Sub Menu 3</a></li>
		      <li><a href="#">Sub Menu 4</a></li>
		      <li><a href="#">Sub Menu 5</a></li>
		    </ul> 
		 </li>
		  <li><a href="#" id ="Titremusical" onclick ="goAccueil(this)">Titres</a>
		    <ul>
		      <li><a href="#">Rock</a></li>
		      <li><a href="#">Rap Français</a></li>
		      <li><a href="#">Rap US</a></li>
		      <li><a href="#">Classique</a></li>
		    </ul>
		  </li>
		  <li><a href="#" id = "album" onclick ="goAccueil(this)">Albums</a>
		    <ul>
		      <li><a href="#">Rock</a></li>
		      <li><a href="#">Rap Français</a></li>
		      <li><a href="#">Rap US</a></li>
		      <li><a href="#">Classique</a></li>
		    </ul>
		  </li>
		  <li><a href="#" id = "Podcasts" onclick ="goAccueil(this)">Podcasts</a></li>
		  <li><a href="#" onclick ="goAccueil(this)">Radios</a></li>
		  
		  <li></li><input id= "barre" type='text' placeholder='Rechercher un element avec son titre ..'>
		  <button onclick ="goAccueil(this)">Recherche</button>
		</ul>
	</nav>
	
	
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<div id = "titreElem"></div>
</body>
</html>
