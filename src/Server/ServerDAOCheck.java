package Server;

import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import DB.DB;

public class ServerDAOCheck {

	   public static String FindCheck(String Nome, int gr){
		   String result = "";
		   String query = "SELECT * FROM `food` WHERE `name_food` LIKE \"%"+Nome+"%\"";
	       String name;
	       
	       float kcal;
	       float kj;
	       float protot;
	       float colest;
	       float fiber;
	       float alcool;
	       float ferro;
	       
	       try {
	    	DB.Connetti();
			DB.cmd.executeQuery(query);
			ResultSet rs = (ResultSet) DB.cmd.getResultSet();
			while(rs.next()){
	            name = rs.getString("name_food");
	            kcal = (float) ((gr/100.00)*Float.parseFloat(rs.getString("kcal_food")));
	            kj = (float) ((gr/100.00)*Float.parseFloat(rs.getString("kj_food")));
	            protot = (float) ((gr/100.00)*Float.parseFloat(rs.getString("protot_food")));
	            colest = (float) ((gr/100.00)*Float.parseFloat(rs.getString("colest_food")));
	            fiber = (float) ((gr/100.00)*Float.parseFloat(rs.getString("fiber_food")));
	            alcool = (float) ((gr/100.00)*Float.parseFloat(rs.getString("alcool_food")));
	            ferro = (float) ((gr/100.00)*Float.parseFloat(rs.getString("ferro_food")));
	            
	            if(name!=null){
	                result=result+"Nome: "+name+" KCAL: "+Float.toString(kcal)+" KJ: "+Float.toString(kj)+" proteine: "+Float.toString(protot)+
	                		" colesterolo: "+Float.toString(colest)+" fibre: "+Float.toString(fiber)+
	                		" alcool: "+Float.toString(alcool)+" ferro: "+Float.toString(ferro)+"\n";
	            }
	            
	            else result ="CIBO NON TROVATO NEL DATABASE, PROVA A REINSERIRE";
	            
	        }
	    } catch (SQLException e) {
			e.printStackTrace();
		}
		   return result;
	   }
}