package debugger;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import database.*;

public class DebugRegistration {
	// CONTROLLO USERNAME
	public static boolean UsernameCheck(String Username) {
		boolean login = false;
		String query = "SELECT username_user, password_user FROM user;";
		String dbUsername;
		try {
			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeQuery(query);
			ResultSet rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
			while (rs.next()) {
				dbUsername = rs.getString("username_user");
				if (dbUsername.equals(Username)) {
					login = true;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}

	// CONTROLLO EMAIL
	public static boolean EmailCheck(String Email) {
		boolean login = false;
		String query = "SELECT email_user FROM user;";
		String dbEmail;
		try {
			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeQuery(query);
			ResultSet rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
			while (rs.next()) {
				dbEmail = rs.getString("email_user");
				if (dbEmail.equals(Email)) {
					login = true;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return login;
	}

	public static boolean EmailOk(String regEmail) {
		boolean login = false;
		int a = regEmail.indexOf('@');
		if (a < 0) {
			System.out.println("numero che esce fuori da indexOf: " + regEmail.indexOf('@'));
			login = true;
		}
		if (regEmail.lastIndexOf('@', a) < 0) {
			System.out.println("numero che esce fuori da indexOf: " + regEmail.indexOf('@'));
			login = true;
		}
		return login;
	}

	public static String DataCheck(String YYYYbirthday, String MMbirthday, String DDbirthday)
	{
		String messaggio = "ok";
		if (Integer.parseInt(YYYYbirthday) < 1900 || Integer.parseInt(YYYYbirthday) > 2015)
			messaggio = "anno";
		else 
			if (Integer.parseInt(MMbirthday) < 1 || Integer.parseInt(MMbirthday) > 12) 
				messaggio = "mese";
			else 
				if (Integer.parseInt(DDbirthday) < 1 || Integer.parseInt(DDbirthday) > 31) 
					messaggio = "giorno";

		return messaggio;
	}
}
