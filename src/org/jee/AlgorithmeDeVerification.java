package org.jee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlgorithmeDeVerification {

	public static Boolean emailDispo(String email) {
		//vérifie si un email est disponible

		Connection connexion = DBManager.getInstance().getConnection(); // on se connecte à la BDD
		int rowCount = 0; // on compte le nombre de lignes de la requete

		try (Statement stmt = connexion.createStatement()) {

			ResultSet rs = stmt.executeQuery("select * from membres where email ='" + email + "';");
			while (rs.next()) {
				rowCount++;

			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		switch (rowCount) {
		
		case 0:
			return true; //l'email est disponible
		case 1:
			return false; // l'email n'est pas disponible
			

		}
		return null;
	}
}
