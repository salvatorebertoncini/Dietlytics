package DB;

import java.sql.*;

public class DB 
{
	static public Statement cmd;
	static public Connection connessione;
	static String db_name="Dietlytics";
	static String user_name="admin";
	static String user_pass="admin";
	static String driver= "com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://localhost/";
	
	public static void Connetti()
	{
		try
		{
			Class.forName(driver);
			connessione = DriverManager.getConnection(url + db_name,user_name,user_pass);
			cmd = connessione.createStatement();
		}
		catch(SQLException ex1)
		{
			ex1.printStackTrace();
		}
		catch(ClassNotFoundException ex1)
		{
			ex1.printStackTrace();
		}
	}	
	
	public static void Disconnetti(){
		
	}

}


