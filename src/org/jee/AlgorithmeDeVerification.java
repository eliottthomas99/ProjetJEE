package org.jee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlgorithmeDeVerification {

	public static Boolean emailDispo(String email) {
		

		Connection connexion = DBManager.getInstance().getConnection();

		int rowCount = 0;

		try (Statement stmt = connexion.createStatement()) {

			ResultSet rs = stmt.executeQuery("select * from membres where email ='" + email + "';");

			while (rs.next()) {
				rowCount++;

			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		switch (rowCount) {
		//case -1:
			//System.out.println("ERREUR");
			//break;
		case 0:
			System.out.println("dispo");
			return true;
		case 1:
			System.out.println("pas dispo!!");
			return false;
			

		}
		return null;
	}
}
