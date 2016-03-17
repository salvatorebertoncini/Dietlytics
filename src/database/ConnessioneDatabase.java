package database;

import java.sql.*;

import org.w3c.dom.Document;

import server.Server;

public class ConnessioneDatabase {
	static public Statement cmd;
	static public Connection connessione;
	static String driver = "com.mysql.jdbc.Driver";

	public static void Connetti() {
		try {
			Document document = Server.ReadXML("server_config.xml");
			String ip = document.getElementsByTagName("Database").item(0).getAttributes().item(0).getTextContent();
	        String port = document.getElementsByTagName("Database").item(0).getAttributes().item(1).getTextContent();
	        String dbname = document.getElementsByTagName("Database").item(0).getChildNodes().item(0).getTextContent();
	        String user = document.getElementsByTagName("Database").item(0).getChildNodes().item(1).getTextContent();
	        String pass = document.getElementsByTagName("Database").item(0).getChildNodes().item(2).getTextContent();
			Class.forName(driver);
			connessione = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + dbname, user, pass);
			cmd = connessione.createStatement();
		} catch (SQLException ex1) {
			ex1.printStackTrace();
		} catch (ClassNotFoundException ex1) {
			ex1.printStackTrace();
		}
	}

	public static void Disconnetti() {

	}

}
