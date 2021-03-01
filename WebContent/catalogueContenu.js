/**
 * 
 */


var xhr = new XMLHttpRequest();
var object = "";
var search = "";

function goAccueil(object)
{	
	var elem = object.parentElement.parentElement.id;
	

	if(elem == "") // Je ne vais pas dans le sous menu
	{
		console.log("ohw");
	}else { // Cela signifie que je vais chercher dans le ""sous menu"
		console.log("yes"+elem);
	}


	if(object.textContent == "Recherche")
	{
		search = document.getElementById("barre").value;
		console.log(search);
		xhr.open('GET',"MainServlet?nomElement="+search,true);
		xhr.onreadystatechange = majCatalogue;
		xhr.send(); // requête pret à étre envoyé 
	}else{
		
		if(elem == "") // On clique directement sur un element present sur la barre ( albums / titres / podcasts .. ) 
		{
			xhr.open('GET',"MainServlet?nomElement="+object.textContent,true);
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé 
	 
		}else {
			
			xhr.open('GET',"MainServlet?nomElement="+elem+"&categorie="+object.textContent,true);
			xhr.onreadystatechange = majCatalogue;
			xhr.send(); // requête pret à étre envoyé*/

			console.log("hey y'a 2 paramètre'");
		}
	}	
}


function majCatalogue()
{
		if (xhr.readyState == 4) {
	    if (xhr.status == 200) {
	      document.getElementById("titreElem").innerHTML = xhr.responseText;
	    } else {
	      alert('Une erreur est survenue lors de la mise à jour de la page.'+
		        '\n\nCode retour = '+xhr.statusText);    
	  	}
	   }
}




// Albums - Titres musicals