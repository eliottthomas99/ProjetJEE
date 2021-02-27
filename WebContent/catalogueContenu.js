/**
 * 
 */


var xhr = new XMLHttpRequest();
var object = "";
var search = "";

function goAccueil(object)
{	
	if(object.textContent == "Recherche")
	{
		search = document.getElementById("barre").value;
		console.log(search);
		xhr.open('GET',"MainServlet?name="+search,true);
		xhr.onreadystatechange = majCatalogue;
		xhr.send(); // requête pret à étre envoyé 
	}else{
		xhr.open('GET',"MainServlet?name="+object.textContent,true);
		xhr.onreadystatechange = majCatalogue;
		xhr.send(); // requête pret à étre envoyé 
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