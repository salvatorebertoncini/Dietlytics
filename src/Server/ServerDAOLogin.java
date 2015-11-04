package Server;

import java.sql.SQLException;
import com.mysql.jdbc.ResultSet;
import Dietlytics.Dietlytics;
import DB.DB;

public class ServerDAOLogin {
	// LOGIN
	
	   public static boolean LoginCheck(String Username, String Password){
		   boolean login = false;
		   String query = "SELECT * FROM user WHERE username_user=\""+Username+"\";";
	       String dbUsername, dbPassword;
	       try {
	    	DB.Connetti();
			DB.cmd.executeQuery(query);
			ResultSet rs = (ResultSet) DB.cmd.getResultSet();
			while(rs.next()){
	            dbUsername = rs.getString("username_user"); System.out.println("User: "+dbUsername);
	            dbPassword = rs.getString("password_user"); System.out.println("Password: "+dbPassword);
	            Dietlytics.user.setWelcome(rs.getString("name_user")); System.out.println("Nome: "+Dietlytics.user.getWelcome());
	            Dietlytics.user.setId(rs.getInt("id_user"));
	            if(dbPassword.equals(Password)){
	                login = true;
	            }
	            
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		   return login;
	   }
}