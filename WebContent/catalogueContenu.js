
var xhr = new XMLHttpRequest();
var object = '';
var search = '';
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
    tag.innerHTML =
        'Jouer <i style=\"font-size:10px\" class=\"fa\">&#xf04b;</i>'
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
    xhr.send();  // requête pret à étre envoyé
  } else {
    if (elem == '')  // On clique directement sur un element present sur la
                     // barre ( albums / titres / podcasts .. )
    {
      xhr.open('GET', 'MainServlet?nomElement=' + object.textContent, true);
      xhr.onreadystatechange = majCatalogue;
      xhr.send();  // requête pret à étre envoyé

    } else {
      // Plusieurs paramètres
      xhr.open(
          'GET',
          'MainServlet?nomElement=' + elem + '&categorie=' + object.textContent,
          true);
      xhr.onreadystatechange = majCatalogue;
      xhr.send();  // requête pret à étre envoyé*/

      console.log('hey y\'a 2 paramètre\'');
    }
  }
}


function goTitresAlbum(object) {
  console.log('hhhhhhh----->' + object.id)
  // console.log("yews ->"+document.getElementById("Auteur").textContent);
  xhr.open('GET', 'MainServlet?interprete=' + object.id, true);
  xhr.onreadystatechange = majCatalogue;
  xhr.send();  // requête pret à étre envoyé
}



function mesPlaylists(object) {
  idMembre = object.id
  console.log(idMembre)
  xhr.open(
      'GET', 'MainServlet?mesPlaylists=' + idMembre,
      true);  // Potentiellement récupérer les données stockées dans le
              // navigateur pour un utilisateur (à la place de aChanger) à ce
              // moment là? (idée julien)
  xhr.onreadystatechange = majCatalogue;
  xhr.send();  // requête pret à étre envoyé
}

function choosePlaylist(object) {
  var str = (object.id).toString();
  console.log((object.id).toString());
  var wordsArray = str.split(' ');

  // wordsArray[0] -> Dans quelle section je suis ? Titre , albums ...
  // wordsArray[1] -> le nom de l'auteur


  console.log('premier mot:', wordsArray[0], 'deuxième mot:', wordsArray[1]);
  console.log(object.id);
  xhr.open(
      'GET',
      'MainServlet?section=' + wordsArray[0] + '&chanteur=' + wordsArray[1],
      true);  // Potentiellement récupérer les données stockées dans le
              // navigateur pour un utilisateur (à la place de aChanger) à ce
              // moment là? (idée julien)
  xhr.onreadystatechange = majCatalogue;
  xhr.send();  // requête pret à étre envoyé
}

function goInsert(object) {
  var str = (object.id).toString();
  console.log((object.id).toString());
  var wordsArray = str.split(' ');

  // wordsArray[0] -> Dans quelle section je suis ? Titre , albums ...
  // wordsArray[1] -> le nom de l'auteur

  console.log(
      'premier mot:', wordsArray[0], 'deuxième mot:', wordsArray[1],
      'troisième mot:', wordsArray[2]);

  // Je suis censé aussi récup l'id du MEMBRE!!!!
  xhr.open(
      'POST',
      'MainServlet?nomPlaylist=' + wordsArray[0] + '&section=' + wordsArray[1] +
          '&titre=' + wordsArray[2],
      true);  // Potentiellement récupérer les données stockées dans le
              // navigateur pour un utilisateur (à la place de aChanger) à ce
              // moment là? (idée julien)
  xhr.onreadystatechange = majCatalogue;
  xhr.send();  // requête pret à étre envoyé
}


function newPlaylistMembre() {
  search = document.getElementById('barre2').value;
  xhr.open('GET', 'MainServlet?nomPlaylist=' + search, true);
  xhr.onreadystatechange = majCatalogue;
  xhr.send();  // requête pret à étre envoyé
}


function titresFromPlaylistPerso(object) {
  var str = (object.id).toString();
  console.log((object.id).toString());
  // Pour au final afficher toutes les musiques liée à une playlistPerso
  // particulière
  console.log(object.id);
  xhr.open('GET', 'MainServlet?titresFromPlaylistPerso=' + object.id, true);
  xhr.onreadystatechange = majCatalogue;
  xhr.send();  // requête pret à étre envoyé
}

function deletePlaylistPerso(object) {
  console.log('a supp');
  // Je suis censé aussi récup l'id du MEMBRE!!!!
  xhr.open(
      'POST', 'MainServlet?PlaylistPerso=' + object.id,
      true);  // Potentiellement récupérer les données stockées dans le
              // navigateur pour un utilisateur (à la place de aChanger) à ce
              // moment là? (idée julien)
  xhr.onreadystatechange = majCatalogue;
  xhr.send();  // requête pret à étre envoyé
}

function deleteTitreFromPlaylistPerso(object) {
  console.log('a supp');
  var str = (object.id).toString();
  console.log((object.id).toString());
  var wordsArray = str.split(' ');
  console.log('premier mot:', wordsArray[0], 'deuxième mot:', wordsArray[1]);
  // Je suis censé aussi récup l'id du MEMBRE!!!!
  xhr.open(
      'POST',
      'MainServlet?TitreFromPlaylistPerso=' + wordsArray[0] +
          '&PlaylistPerso=' + wordsArray[1],
      true);  // Potentiellement récupérer les données stockées dans le
              // navigateur pour un utilisateur (à la place de aChanger) à ce
              // moment là? (idée julien)
  xhr.onreadystatechange = majCatalogue;
  xhr.send();  // requête pret à étre envoyé
}


function majCatalogue() {
  if (xhr.readyState == 4) {
    if (xhr.status == 200) {
      document.getElementById('titreElem').innerHTML = xhr.responseText;
    } else {
      alert(
          'Une erreur est survenue lors de la mise à jour de la page.' +
          '\n\nCode retour = ' + xhr.statusText);
    }
  }
}
