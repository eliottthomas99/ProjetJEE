package org.jee;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class modifAvanceeCompte
 */
@WebServlet("/ModifAvanceeCompte")
public class modifAvanceeCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public modifAvanceeCompte() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageName = "/modifCompte.jsp"; // on affiche la page de modification de compte personel
		this.getServletContext().getRequestDispatcher(pageName).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codeRetourModifAvancee = null;

		HttpSession maSession = request.getSession(); // on récupère la session
		Membre membre = (Membre) maSession.getAttribute("membreConnecte"); // on récupère l'objet membre qui correspond à la personne connectée

		// On recupere les infos du formulaire
		String ancienMotDePasse = request.getParameter("ancienMotDePasse");
		String nouveauEmail = request.getParameter("nouveauEmail");
		String nouveauMotDePasse = request.getParameter("nouveauMotDePasse");
		String nouveauMotDePasseConfirmation = request.getParameter("nouveauMotDePasseConfirmation");
		Boolean valid = false;

		// on verifie si les nouveaux mdp correspondent
		if (nouveauMotDePasse.equals(nouveauMotDePasseConfirmation)) {
			// si oui
			// on recupere l ancien email
			try {
				String ancienEmail = membre.getEmail();

				// on verifie que le mdp entre soit le bon
				valid = Membre.validerAuthentification(ancienEmail, ancienMotDePasse);

				if (valid) {
					// si oui
					
					// on verifie qu'en cas de changement d'email le nouvel email soit valide
					Boolean emailDispo = AlgorithmeDeVerification.emailDispo(nouveauEmail)|| nouveauEmail.equals(ancienEmail);
					
					if (emailDispo) {
						// Si oui
						// on met a jour les donnees du client
						String nouveauMotDePasseHash = JavaMD5Hash.md5(nouveauMotDePasse);

						membre.setEmail(nouveauEmail);
						membre.setPassword(nouveauMotDePasseHash);
						maSession.setAttribute("membreModifie", membre);

						Boolean retour = Membre.modifAvanceeCompte(ancienEmail, nouveauEmail, nouveauMotDePasseHash);

						// on affiche que tout s est bien passe
						codeRetourModifAvancee = "modifications enregistrées";
					}
					// sinon
					// l email n'est pas dispo
					else {
						// on affiche que l'email n'est pas disponible
						codeRetourModifAvancee = "email non disponible";
					}
				}
				// sinon
				// l ancien mdp n'est pas bon
				else {
					// on affiche que le mdp est incorrect
					codeRetourModifAvancee = "mot de passe actuel incorrect";
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}
		}
		// sinon
		// les mdps ne correspondent pas
		else {
			// on affiche que les mdps ne correspondent pas
			codeRetourModifAvancee = "les mots de passe de passe ne correspondent pas";
		}

		// on envoie le code retour
		maSession.setAttribute("codeRetour", codeRetourModifAvancee);

		doGet(request, response);
	}

}
