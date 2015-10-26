package Debugger;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import DB.DB;

public class DebugRegistration {
	//CONTROLLO USERNAME
	   public static boolean UsernameCheck(String Username){
		   boolean login = false;
		   String query = "SELECT username_user, password_user FROM user;";
	       String dbUsername;
	       try {
	    	DB.Connetti();
			DB.cmd.executeQuery(query);
			ResultSet rs = (ResultSet) DB.cmd.getResultSet();
			while(rs.next()){
	            dbUsername = rs.getString("username_user");
	            if(dbUsername.equals(Username)){
	                login = true;
	            }
	            
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		   return login;
	   }
		   
	   //CONTROLLO EMAIL
	   public static boolean EmailCheck(String Email){
		   boolean login = false;
		   String query = "SELECT email_user FROM user;";
	       String dbEmail;
	       try {
	    	DB.Connetti();
			DB.cmd.executeQuery(query);
			ResultSet rs = (ResultSet) DB.cmd.getResultSet();
			while(rs.next()){
	            dbEmail = rs.getString("email_user");
	            if(dbEmail.equals(Email)){
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
		int a=regEmail.indexOf('@');
		if(a<0) 
		{
			System.out.println("numero che esce fuori da indexOf: "+regEmail.indexOf('@')); 
			login=true;
		}
		if(regEmail.lastIndexOf('@', a)<0){
			System.out.println("numero che esce fuori da indexOf: "+regEmail.indexOf('@')); 
			login=true;
		}
		return login;
	   }
}
