<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List,org.jee.ElementDeCatalogue"%>
<%@page import="java.util.List,org.jee.CatalogueServiceImpl"%>
<%@page import="java.util.List,org.jee.Membre"%>
<%@page import="java.util.List,org.jee.Album"%>
<%@page import="java.util.List,org.jee.Visiteur"%>
<%
List<ElementDeCatalogue> listElements = (List<ElementDeCatalogue>) request.getAttribute("listElements");


int i = 0;

HttpSession maSession = request.getSession();
Visiteur visiteur = (Visiteur)maSession.getAttribute("visiteurConnecte");

int resAdminCompteOrNo = 0;
int resAdminMusiqueOrNo = 0;
int id = 0;
String prenom = "";
String nom = "";
if(visiteur == null){
	
	resAdminCompteOrNo = (int) request.getAttribute("resAdminCompteOrNo");
	resAdminMusiqueOrNo = (int) request.getAttribute("resAdminMusiqueOrNo");
	Membre elMembre = (Membre) maSession.getAttribute("membreConnecte");
	id = elMembre.getId();
	System.out.println("id membre" + id);
	prenom = elMembre.getPrenom();
	nom = elMembre.getNom();
}else{
	prenom = visiteur.getPrenom();
	nom = visiteur.getNom();
}

//System.out.println("visiteur"+visiteur.getPrenom());
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Catalogue</title>

<link type="text/css" rel="stylesheet" href="catalogue.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
	<h1 id="musical">Catalogue Musical</h1>
	<h6 id="musicalPetit">Bienvenue <%=nom%> <%= prenom%></h6>

	<%
	if (visiteur == null) {
	%>
	<nav id="primary_nav_wrap">
		<ul>

			<li class="current-menu-item"><a href="MainServlet">La
					playlist du moment</a></li>
			<li>
			<li><a id='<%=id%>' onclick="mesPlaylists(this)">Mes
					playlists</a></li>

			<li><a href="#"  onclick="goAccueil(this)">Titres</a>
				<ul id="Titres">
					<li><a  onclick="goAccueil(this)">Rock</a></li>
					<li><a  onclick="goAccueil(this)">Rap
							Francais</a></li>
					<li><a  onclick="goAccueil(this)">Rap US</a></li>
					<li><a  onclick="goAccueil(this)">Classique</a></li>
				</ul></li>
			<li id="album"><a onclick="goAccueil(this)">Albums</a>
				<ul id="Albums">
					<li><a  onclick="goAccueil(this)">Rock</a></li>
					<li><a  onclick="goAccueil(this)">Rap
							Francais</a></li>
					<li><a  onclick="goAccueil(this)">Rap US</a></li>
					<li><a  onclick="goAccueil(this)">Classique</a></li>
				</ul></li>

			<li><a href='modifCompte.jsp'>Mon profil</a></li>
			<%
			if (resAdminCompteOrNo == 1) {
			%>

			<li id="album"><a href='adminModifCompte.jsp'>Gestion
					Clients</a></li>

			<%
			} else {
			}
			%>
			<li><input name="search" id="barre" type='text'
				placeholder='Rechercher un element avec son titre ..'></input>
				<button onclick="goAccueil(this)">Recherche</button></li>
						
			<li><a href="Deconnection" id="deco">Deconnection</a></li>
			
		</ul>
	</nav>


	<br>
	<br>
	<br>

	<div id="titreElem">

		<div class="form">
		<%
		if (resAdminMusiqueOrNo == 1) {
		%>
		<h3>Modifier la playlist publique</h3>

		<h5>Titre :</h5>
		<input name='search' id='TitreBarre1' type='text'
			placeholder='Saisir un titre'>
		<h5>Interprete :</h5>
		<input name='search' id='InterpreteBarre2' type='text'
			placeholder='Saisir un interprete'>
		<h5>Type :</h5>
		<select id="type">
			<option value=1>Album</option>
			<option value=2>Titre</option>
		</select> <br> <br>
		<button onclick='ajoutElementPlaylistPublique()'>Ajouter</button>
		<%
		}
		%>
		<br/>
		</div>
		<h3 class="title">Decouvrez notre playlist du moment</h3>
		<br>
		<table border="0">
			<tr>
				<th>Titre</th>
				<th>Auteur</th>
				<th>Type</th>
			</tr>
			<%
			if (listElements.isEmpty()) {
			%>
			<tr>
				<td>vide</td>
				<td>vide</td>
				<td>vide</td>
			</tr>

			<%
			}
			%>


			<%
			for (ElementDeCatalogue cata : listElements) {
				String title = cata.getTitre();
				String author = cata.getInterprete();
			%>

			<%
			if (cata instanceof Album) {
			%>
			<tr>
				<td><%=title%></td>
				<td><%=author%></td>
				<td>Album</td>

				<%
				if (resAdminMusiqueOrNo == 1) {
				%>
				<td><button>
						Jouer <i style='font-size: 10px' class='fa'>&#xf04b;</i>
					</button>
					<button id="<%=title%>" onclick="goTitresAlbum(this)">Parcourir
						l'album</button></td>
				<td><button id="playlistMomentAlbum <%=title%>"
						onclick='deleteElement(this)'>supprimer</button></td>
				<%
				} else {
				%>
				<td><button id='jouer"<%=i%>"' onclick='goSwitch(this)'>Jouer <i style='font-size:10px' class='fa'>&#xf04b;</i></button>
					<button id="<%=title%>" onclick="goTitresAlbum(this)">Parcourir
						l'album</button></td>
				<%
				}
				%>
			</tr>
			<%
			} else {
			%>
			<tr>
				<td><%=title%></td>
				<td><%=author%></td>
				<td>Titre muscial</td>
				<%
				if (resAdminMusiqueOrNo == 1) {
				%>
				<td><button id='jouer"<%=i%>"' onclick='goSwitch(this)'>Jouer <i style='font-size:10px' class='fa'>&#xf04b;</i></button></td>
				<td><button id="playlistMomentTitre <%=title%>"
						onclick='deleteElement(this)'>supprimer</button></td>
				<%
				} else {
				%>
				<td><button>
						Jouer <i style='font-size: 10px' class='fa'>&#xf04b;</i>
					</button></td>
				<%
				}
				%>
			</tr>
			<%
			}
			%>
			<%
			i = i + 1;
			}
			%>
		</table>
	</div>

	<%
	} else {
	%>
	<nav id="primary_nav_wrap">
		<ul>
			<li class="current-menu-item"><a href="MainServlet">La
					playlist du moment</a></li>
			<li><a href="Deconnection">m'inscrire</a></li>
			<li><input name="search" id="barre" type='text'
				placeholder='Rechercher un element avec son titre ..'></input>
				<button id="visiteur" onclick="searchByVisiteur(this)">Recherche</button></li>
		</ul>
	</nav>
	<br>
	<br>
	<br>

	<div id="titreElem" >

		<h3 >Decouvrez notre playlist du moment</h3>
		
		<table border="0">
			<tr>
				<th>Titre</th>
				<th>Auteur</th>
				<th>Type</th>
			</tr>
			<%
			if (listElements.isEmpty()) {
			%>
			<tr>
				<td>vide</td>
				<td>vide</td>
				<td>vide</td>
			</tr>

			<%
			}
			%>


			<%
			for (ElementDeCatalogue cata : listElements) {
				String title = cata.getTitre();
				String author = cata.getInterprete();
			%>

			<%
			if (cata instanceof Album) {
			%>
			<tr>
				<td><%=title%></td>
				<td><%=author%></td>
				<td>Album</td>
				<td><button id='jouer"<%=i%>"' onclick='goSwitch(this)'>Jouer <i style='font-size:10px' class='fa'>&#xf04b;</i></button>
					<button id="<%=title%>" onclick="goTitresAlbum(this)">Parcourir
						l'album</button></td>
			</tr>
			<%
			} else {
			%>
			<tr>
				<td><%=title%></td>
				<td><%=author%></td>
				<td>Titre muscial</td>
				<td><button id='jouer"<%=i%>"' onclick='goSwitch(this)'>Jouer <i style='font-size:10px' class='fa'>&#xf04b;</i></button></td>
			</tr>
			<%
			}
			%>
			<%
			i = i + 1;
			}
			%>
			<%
			}
			%>

		</table>
	</div>







	<script>
	
		var xhr = new XMLHttpRequest();
		var object = '';
		var search = '';
		var titre = '';
		var interprete = '';

		var interpreteAlbum;
		var inStop = 0;

		function goSwitch(object) {
			if (!inStop) {
				console.log(object.textContent);
				var tag = document.getElementById(object.id);
				console.log(tag);
				tag.innerHTML = 'Pause <i style=\"font-size:8px\" class=\"fa\">&#xf04c;</i>'
				inStop = 1;
			} else {
				console.log(object.textContent);
				var tag = document.getElementById(object.id);
				console.log(tag);
				tag.innerHTML = 'Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i>'
				inStop = 0;
			}
		}

		function goAccueil(object) {
			// Pour récupérer l'id de la balise ul
			var elem = object.parentElement.parentElement.id;

			if (object.textContent == 'Recherche') {
				search = document.getElementById('barre').value;
				console.log(search);
				xhr.open('GET', 'MainServlet?nomElement=' + search, true);
				xhr.onreadystatechange = majCatalogue;
				xhr.send(); // requête pret à étre envoyé
			} else {
				if (elem == '') // On clique directement sur un element present sur la
				// barre ( albums / titres / podcasts .. )
				{
					xhr.open('GET', 'MainServlet?nomElement='
							+ object.textContent, true);
					xhr.onreadystatechange = majCatalogue;
					xhr.send(); // requête pret à étre envoyé

				} else {
					// Plusieurs paramètres
					xhr.open('GET', 'MainServlet?nomElement=' + elem
							+ '&categorie=' + object.textContent, true);
					xhr.onreadystatechange = majCatalogue;
					xhr.send(); // requête pret à étre envoyé*/

					console.log('hey y\'a 2 paramètre\'');
				}
			}
		}

		function searchByVisiteur(object) {
			search = document.getElementById('barre').value;
			console.log(search);
			xhr.open('GET', 'MainServlet?nomElement=' + search + "Visiteur",
					true);
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}

		function goTitresAlbum(object) {
			console.log('hhhhhhh----->' + object.id)
			// console.log("yews ->"+document.getElementById("Auteur").textContent);
			xhr.open('GET', 'MainServlet?interprete=' + object.id, true);
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}

		function mesPlaylists(object) {
			console.log(object);
			idMembre = object.id
			console.log(idMembre)
			xhr.open('GET', 'MainServlet?mesPlaylists=' + idMembre, true); // Potentiellement récupérer les données stockées dans le
			// navigateur pour un utilisateur (à la place de aChanger) à ce
			// moment là? (idée julien)
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}

		function choosePlaylist(object) {
			var str = (object.id).toString();
			console.log((object.id).toString());
			var wordsArray = str.split(' ');

			// wordsArray[0] -> Dans quelle section je suis ? Titre , albums ...
			// wordsArray[1] -> le nom de l'auteur
			// id du membre actuel wordsArray[2]

			console.log('premier mot:', wordsArray[0], 'deuxième mot:',
					wordsArray[1], " et l'id du membre:", wordsArray[2]);
			console.log(object.id);
			xhr.open('GET', 'MainServlet?section=' + wordsArray[0]
					+ '&chanteur=' + wordsArray[1], true); // Potentiellement récupérer les données stockées dans le
			// navigateur pour un utilisateur (à la place de aChanger) à ce
			// moment là? (idée julien)
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}

		function goInsert(object) {

			var str = (object.id).toString();
			console.log((object.id).toString());
			var wordsArray = str.split(' ');

			// wordsArray[0] -> Dans quelle section je suis ? Titre , albums ...
			// wordsArray[1] -> le nom de l'auteur

			console.log('premier mot:', wordsArray[0], 'deuxième mot:',
					wordsArray[1], 'troisième mot:', wordsArray[2]);

			// Je suis censé aussi récup l'id du MEMBRE!!!!
			xhr.open('POST', 'MainServlet?nomPlaylist=' + wordsArray[0]
					+ '&section=' + wordsArray[1] + '&titre=' + wordsArray[2],
					true); // Potentiellement récupérer les données stockées dans le
			// navigateur pour un utilisateur (à la place de aChanger) à ce
			// moment là? (idée julien)
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}

		function newPlaylistMembre() {
			search = document.getElementById('barre2').value;
			xhr.open('GET', 'MainServlet?nomPlaylist=' + search, true);
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}

		function titresFromPlaylistPerso(object) {
			var str = (object.id).toString();
			console.log((object.id).toString());
			// Pour au final afficher toutes les musiques liée à une playlistPerso
			// particulière
			console.log(object.id);
			xhr.open('GET', 'MainServlet?titresFromPlaylistPerso=' + object.id,
					true);
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}

		function deletePlaylistPerso(object) {
			console.log('a supp');
			// Je suis censé aussi récup l'id du MEMBRE!!!!
			xhr.open('POST', 'MainServlet?PlaylistPerso=' + object.id, true); // Potentiellement récupérer les données stockées dans le
			// navigateur pour un utilisateur (à la place de aChanger) à ce
			// moment là? (idée julien)
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}

		function deleteTitreFromPlaylistPerso(object) {

			console.log('a supp');
			var str = (object.id).toString();
			console.log((object.id).toString());
			var wordsArray = str.split(' ');
			console.log('premier mot:', wordsArray[0], 'deuxième mot:',
					wordsArray[1]);
			// Je suis censé aussi récup l'id du MEMBRE!!!!

			// Ok c'est un mot composé
			var taille = 0;

			if (typeof wordsArray[2] != 'undefined') {
				for (var k = 0; k < wordsArray.length; k++) {
					taille = taille + 1;
				}

				console.log("heeeeeh oooooooo" + wordsArray[taille - 1]);
				// Je récupère le dernier mot
				//var i = 0;

				/*do {
				  i ++;
				}while (wordsArray[i] ! = 'undefined');
				 */
				var nomPlaylist = wordsArray[taille - 1]

				var titre = "";

				for (var k = 0; k < taille - 1; k++) {
					titre = titre + wordsArray[k]
					titre = titre + " "
				}

				console.log("titre ->" + titre);
				xhr.open('POST', 'MainServlet?TitreFromPlaylistPerso=' + titre
						+ '&PlaylistPerso=' + nomPlaylist, true); // Potentiellement récupérer les données stockées dans le
				// navigateur pour un utilisateur (à la place de aChanger) à ce
				// moment là? (idée julien)
				xhr.onreadystatechange = majCatalogue;
				xhr.send(); // requête pret à étre envoyé
			} else {
				xhr.open('POST', 'MainServlet?TitreFromPlaylistPerso='
						+ wordsArray[0] + '&PlaylistPerso=' + wordsArray[1],
						true); // Potentiellement récupérer les données stockées dans le
				// navigateur pour un utilisateur (à la place de aChanger) à ce
				// moment là? (idée julien)
				xhr.onreadystatechange = majCatalogue;
				xhr.send(); // requête pret à étre envoyé
			}
		}

		function majCatalogue() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					document.getElementById('titreElem').innerHTML = xhr.responseText;
				} else {
					alert('Une erreur est survenue lors de la mise à jour de la page.'
							+ '\n\nCode retour = ' + xhr.statusText);
				}
			}
		}

		function ajoutTitres() {
			console.log('GO ajouter du contenu !');
			titre = document.getElementById('barreTitre1').value;
			interprete = document.getElementById('barreTitre2').value;
			xhr.open('GET', 'MainServlet?titreAjout=' + titre
					+ '&interpreteAjout=' + interprete, true);
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}

		function ajoutAlbum() {
			console.log('GO ajouter du contenu !');
			titre = document.getElementById('barreAlbum1').value;
			interprete = document.getElementById('barreAlbum2').value;
			xhr.open('GET', 'MainServlet?AlbumAjout=' + titre
					+ '&interpreteAjout=' + interprete, true);
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}

		function ajoutElementPlaylistPublique() {
			var element = '';
			titre = document.getElementById('TitreBarre1').value;
			interprete = document.getElementById('InterpreteBarre2').value;
			type = document.getElementById('type').value;
			console.log("Type ->", type); // Album = 1 | titre = 2
			if (type == 1) {
				element = 'Albums'
			} else {
				element = 'Titres'
			}
			xhr.open('GET', 'MainServlet?titrePlaylistPublicAjout=' + titre
					+ '&interpretePlaylistPublicAjout=' + interprete
					+ '&typeAjout=' + element, true);
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}

		function deleteElement(object) {
			var str = (object.id).toString();
			console.log((object.id).toString());
			var wordsArray = str.split(' ');

			// wordsArray[0] -> Dans quelle section je suis ? Titre , albums ...
			// wordsArray[1] -> le nom de l'auteur
			console.log('premier mot:', wordsArray[0], 'deuxième mot:',
					wordsArray[1], 'troisième mot:', wordsArray[2]);

			if (typeof wordsArray[2] == 'undefined') {
				console.log('Undefined, premier mot:', wordsArray[0],
						'deuxième mot:', wordsArray[1], 'troisième mot:',
						wordsArray[2]);

				xhr.open('GET', 'MainServlet?categorieBis=' + wordsArray[0]
						+ '&titreElement=' + wordsArray[1], true);
				xhr.onreadystatechange = majCatalogue;
				xhr.send(); // requête pret à étre envoyé
			} else {

				xhr.open('GET', 'MainServlet?categorieBis=' + wordsArray[0]
						+ '&titreElement=' + wordsArray[1] + '&catElement='
						+ wordsArray[2], true);
				xhr.onreadystatechange = majCatalogue;
				xhr.send(); // requête pret à étre envoyé
			}
		}

		function ajoutTitresToAnAlbum(object) {

			// Recup le titre / interprete du titre a rajouter dans l'album 

			// 
			titre = document.getElementById('barreTitreAlbum1').value;
			interprete = document.getElementById('barreTitreAlbum2').value;
			titreAlbum = object.id;
			// Besoin aussi du titre de l'album en cours

			console.log("Titre :" + titre + "interprete qui va être rajouté : "
					+ interprete + " titre de l'album en cours:" + titreAlbum);

			xhr.open('GET', 'MainServlet?titreOfAnAlbum=' + titre
					+ '&InterpreteOfAnAlbum=' + interprete + '&ActualPlaylist='
					+ titreAlbum, true);
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé
		}
	</script>


</body>
</html>
