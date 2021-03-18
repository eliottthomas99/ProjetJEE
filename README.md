# ProjetJEE - Plateforme de streaming musical
Equipe 10, composée de Alex Fulbert, Anthony Ingels, Eliott Thomas et Julien Thomas.

## Installation
Pour pouvoir installer le projet il vous faudra les outils suivant :
- tomcat 9
- jdk 15

Il suffit ensuite de déployer le fichier ProjetJEE.war sur Tomcat. Si votre Tomcat n'est pas configuré vous pouvez suivre ce tutoriel https://www.baeldung.com/tomcat-deploy-war.

**Attention !** Il est nécessaire que le fichier ProjetJEE.war conserve ce nom, la navigation sur le site utilise les URL et changer le nom du .war change les URLs.


## Utilisation
Pour vous rendre sur notre site il vous faut ensuite aller à l'adresse /ProjetJEE/Accueil, dans notre cas l'adresse est : http://localhost:8080/ProjetJEE/Accueil.


Pour tester notre projet nous avons mis à votre disposition 3 comptes :

| identifiant (email) 	| mot de passe 	| droits                      	|
|---------------------	|--------------	|-----------------------------	|
| utilisateur         	| 12345        	| aucun                       	|
| adminCompte         	| 12345        	| droits édition utilisateurs 	|
| adminMusique        	| 12345        	| droits édition musiques     	|


## Autre
Suite aux problèmes liés à la base de donnée nous avons utilisé un autre hébergeur gratuit, vous trouverez les logs dans le fichier /src/config.properties si vous voulez consulter la base de données.