package Server;

import java.sql.SQLException;
import Dietlytics.Dietlytics;
import Dietlytics.Algorithm;
import com.mysql.jdbc.ResultSet;

import DB.DB;

public class ServerDAORegistration {
   //REGISTRAZIONE
   public static boolean RegistrationCheck(String Nome,String Cognome,String Username,String Password,String Email,String Nascita, int Sex,String Altezza, String Peso)
   {
	   String query= "INSERT INTO user (id_user,name_user,surname_user,username_user,password_user,email_user,date_user"
	   		+ ",id_sex,height_user, weight_user, id_lifestyle, id_typediet) VALUES (NULL,"+"\""+Nome+"\""+","+"\""+Cognome
	   		+"\""+","+"\""+Username+"\""+","+"\""+Password+"\""+","+"\""+Email+"\""+","+"\""+Nascita+"\""+","+Sex+","+"\""
	   		+Altezza+"\""+","+"\""+Peso+"\""+","+9+", "+9+")";
	   try{
		   DB.Connetti();
		   DB.cmd.executeUpdate(query);
	   } catch (SQLException e){
		   e.printStackTrace();
	   }
	   query = "SELECT * FROM `user`";
	   try{
		   DB.Connetti();
		   DB.cmd.executeQuery(query);
		   ResultSet rs = (ResultSet) DB.cmd.getResultSet();
		   while(rs.next()){
			   Dietlytics.user.setId(rs.getInt("id_user"));
		   }
	   } catch (SQLException e){
		   e.printStackTrace();
	   }
	   return true;
   }

public static boolean ChangeCheck(int id, String regNome, String regCognome, String regUsername, String regPassword, String regEmail, String regDataDiNascita,
		int regSex, String regWeight, String regHeight) {
	String query= "UPDATE `user` SET `name_user`="+"\""+regNome+"\""+", `surname_user`="+"\""+regCognome+"\""+", `username_user`= "+"\""+regUsername+"\""+", `password_user`= "+"\""+regPassword+"\""+", `email_user`="+"\""+regEmail+"\""+", `date_user`="+"\""+regDataDiNascita+"\""+", `id_sex`="+"\""+regSex+"\""+", `height_user`="+"\""+regHeight+"\""+", `weight_user`="+"\""+regWeight+"\""+" WHERE `id_user`="+id+";";
	System.out.println(query);   
	try{
		   DB.Connetti();
		   DB.cmd.executeUpdate(query);
	   } catch (SQLException e){
		   e.printStackTrace();
	   }
	   
	   return true;
   }

public static boolean ChangeUser(int id, String regUsername, String regPassword){
	String query="UPDATE `user` SET `id_user`="+id+",`usernamename_user`="+regUsername+",`password_user`="+regPassword+";";
	   
	try{
		   DB.Connetti();
		   DB.cmd.executeUpdate(query);
	   } catch (SQLException e){
		   e.printStackTrace();
	   }
	   
	   return true;
   }

public static boolean deleteUser(int id){
	String query="DELETE FROM `user` WHERE `id_user`="+id;
	try{
		   DB.Connetti();
		   DB.cmd.executeUpdate(query);
	   } catch (SQLException e){
		   e.printStackTrace();
	   }
	   
	   return true;
}

public static boolean newDiet(int id_user, int id_diet, String regDataInizioDieta, String regDataFineDieta) {
	String query = "INSERT INTO `storico`(`id_user`, `id_diet`, `startdiet_storico`, `enddiet_storico`) VALUES "
			+ "("+id_user+","+id_diet+","+regDataInizioDieta+","+regDataFineDieta+")";
	try{
		   DB.Connetti();
		   DB.cmd.executeUpdate(query);
	   } catch (SQLException e){
		   e.printStackTrace();
	   }
	   
	   return true;
}

public static boolean requestField(int id, StringBuilder regNome, StringBuilder regCognome, StringBuilder regUsername, StringBuilder regPassword, StringBuilder regEmail, StringBuilder regDataDiNascita, StringBuilder regSex, StringBuilder regHeight, StringBuilder regWeight) {
    
	String query = "SELECT * FROM `user` WHERE `id_user` ="+id;
	try{
		   DB.Connetti();
		   DB.cmd.executeQuery(query);
		   ResultSet rs = (ResultSet) DB.cmd.getResultSet();
		   while(rs.next()){
			   regNome.append(rs.getString("name_user"));
			   regCognome.append(rs.getString("surname_user"));
			   regUsername.append(rs.getString("username_user"));
			   regPassword.append(rs.getString("password_user"));
			   regEmail.append(rs.getString("email_user"));
			   regDataDiNascita.append(rs.getString("date_user"));
			   regSex.append(rs.getString("id_sex")); System.out.println("prova sex DAO: "+regSex);
			   System.out.println(regDataDiNascita);
			   regHeight.append(rs.getString("height_user"));
			   regWeight.append(rs.getString("weight_user"));
		   }
	   } catch (SQLException e){
		   e.printStackTrace();
	   }
	   
	   return true;
}

	public static boolean registerNewDiet(int id, int lifestyle, int typediet, String date, int all){
		//INSERISCO LO STILE DI VITA ALL'UTENTE CORRENTE
		String query="UPDATE `user` SET `id_lifestyle`="+lifestyle+" WHERE `id_user` ="+id+";";
		System.out.println(query);
		String dieta="";
		try{
			   DB.Connetti();
			   DB.cmd.executeUpdate(query);

		   } catch (SQLException e){
			   e.printStackTrace();
		   }
		//VERIFICO SE SONO GIà STATE INIZIATE DIETE
		String query1="SELECT * FROM `storico` WHERE `id_user`="+id+";";
		System.out.println(query1);
		int storico=0;
		ResultSet rs;
		double kcal;
		try {
			   DB.Connetti();
			   DB.cmd.executeQuery(query1);
			   rs = (ResultSet) DB.cmd.getResultSet();
			   while(rs.next())
				   storico = Integer.parseInt(rs.getString("id_storico"));
			   
			   kcal=Algorithm.algorithm();
			   
			   if(storico<1){
				   System.out.println("Prima dieta per l'utente con ID: "+id);
				   dieta=Diet.ComposizioneDieta.composizioneDieta((int)kcal, typediet, all);
				   //apro nuova dieta
				   query="INSERT INTO `diet`(`id_typediet`, `kcal_diet`, `dieta_diet`) "
				   		+ "VALUES ("+typediet+","+kcal+",'"+dieta+"')";
				   try{
					   DB.Connetti();
					   DB.cmd.executeUpdate(query);

				   } catch (SQLException e){
					   e.printStackTrace();
				   }
				   System.out.println(query);
				   //cerco la dieta per associarla allo storico
				   query="SELECT * FROM `diet`";
				   int iddieta=0;
				   try {
					   DB.Connetti();
					   DB.cmd.executeQuery(query);
					   rs = (ResultSet) DB.cmd.getResultSet();
					while(rs.next())
						   iddieta = rs.getInt("id_diet");
				   } catch (SQLException e){
					   e.printStackTrace();
				   }
				   //associo dieta a storico e id
				   query1="INSERT INTO `storico`(`id_user`, `id_diet`, `startdiet_storico`, `enddiet_storico`)"
				   		+ " VALUES ("+id+","+iddieta+",'"+date+"',\"\")"; 
				   try{
					   DB.Connetti();
					   DB.cmd.executeUpdate(query1); /////////////

				   } catch (SQLException e){
					   e.printStackTrace();
				   }
				   System.out.println(query1);
			   }
			   else{
				   System.out.println("Dieta numero: "+(storico+1)+" per l'utente con ID: "+id);
				   dieta=Diet.ComposizioneDieta.composizioneDieta((int)kcal, typediet, all);
				   //chiudo vecchia dieta
				   query="UPDATE `storico` SET `enddiet_storico`='"+date+"' WHERE `id_storico` ="+storico+";";
				   try{
					   DB.Connetti();
					   DB.cmd.executeUpdate(query);

				   } catch (SQLException e){
					   e.printStackTrace();
				   }
				   System.out.println(query);
				   //apro nuova dieta
				   query="INSERT INTO `diet`(`id_typediet`, `kcal_diet`, `dieta_diet`) "
				   		+ "VALUES ("+typediet+","+kcal+",'"+dieta+"')";
				   try{
					   DB.Connetti();
					   DB.cmd.setEscapeProcessing(true);
					   DB.cmd.executeUpdate(query);

				   } catch (SQLException e){
					   e.printStackTrace();
				   }
				   System.out.println(query);
				   //cerco la dieta per associarla allo storico
				   query="SELECT * FROM `diet`";
				   int iddieta=0;
				   try {
					   DB.Connetti();
					   DB.cmd.executeQuery(query);
					   rs = (ResultSet) DB.cmd.getResultSet();
					while(rs.next())
						   iddieta = rs.getInt("id_diet");
				   } catch (SQLException e){
					   e.printStackTrace();
				   }
				   //associo dieta a storico e id
				   query="INSERT INTO `storico`(`id_user`, `id_diet`, `startdiet_storico`)"
					   		+ " VALUES ("+id+","+iddieta+",'"+date+"')"; 
				   try{
					   DB.Connetti();
					   DB.cmd.executeUpdate(query);

				   } catch (SQLException e){
					   e.printStackTrace();
				   }
				   System.out.println(query);
			   }
				   
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		return true;
	}

	public static boolean requestInformation(int id, StringBuilder l, StringBuilder s, StringBuilder h, StringBuilder w, StringBuilder a) {
		String query = "SELECT * FROM `user` WHERE `id_user` ="+id;
		StringBuilder x = new StringBuilder("");
		try{
			   DB.Connetti();
			   DB.cmd.executeQuery(query);
			   ResultSet rs = (ResultSet) DB.cmd.getResultSet();
			   while(rs.next()){

				   a.append(rs.getString("date_user"));
				   s.append(rs.getString("id_sex"));
				   h.append(rs.getString("height_user"));
				   w.append(rs.getString("weight_user"));
				   x.append(rs.getString("id_lifestyle"));
			   }
		   } catch (SQLException e){
			   e.printStackTrace();
		   }
		switch (x.toString()) {
	        case "0":  l.append("1.25"); break;
	        case "1":  l.append("1.45"); break;
	        case "2":  l.append("1.45"); break;
	        case "3":  l.append("1.65"); break;
	        case "4":  l.append("1.85"); break;
		}
		
        return true;
		
	}

	public static boolean readDiet(StringBuilder dieta, StringBuilder data) {
		String query = "SELECT storico.startdiet_storico, diet.dieta_diet FROM storico, diet WHERE storico.id_diet = diet.id_diet", d="", x="";
		try{
			   DB.Connetti();
			   DB.cmd.executeQuery(query);
			   ResultSet rs = (ResultSet) DB.cmd.getResultSet();
			   while(rs.next()){
				   d+=rs.getString("dieta_diet");
				   d+="\n";
				   x+=rs.getString("startdiet_storico");
			   }
		   } catch (SQLException e){
			   e.printStackTrace();
		   }
		dieta.append(d);
		data.append(x);
		return true;
	}

	public static void initializeHistoryButton(StringBuilder num, StringBuilder name, StringBuilder id) {
		String query = "SELECT storico.id_storico, storico.startdiet_storico, diet.dieta_diet FROM storico, diet WHERE storico.id_diet = diet.id_diet AND id_user="+Dietlytics.user.getId(), x="", k="";
		int d=0;
		System.out.println("CERCO NEL DB SE CI SONO DIETE NELLO STORICO INIZIATE QUEL GIORNO");
		try{
			   DB.Connetti();
			   DB.cmd.executeQuery(query);
			   ResultSet rs = (ResultSet) DB.cmd.getResultSet();
			   while(rs.next()){
				   k+=rs.getString("id_storico")+"#"; System.out.println(rs.getString("id_storico"));
				   x+=rs.getString("startdiet_storico")+"#"; System.out.println(rs.getString("startdiet_storico"));
				   d++;
			   }
		   } catch (SQLException e){
			   e.printStackTrace();
		   }
		id.append(k);
		name.append(x);
		num.append(d);
	}

	public static String findDiet(int string) {
		String query = "SELECT storico.id_storico, diet.dieta_diet FROM storico, diet WHERE storico.id_diet = diet.id_diet AND storico.id_storico = "+string, x="";
		try{
			   DB.Connetti();
			   DB.cmd.executeQuery(query);
			   ResultSet rs = (ResultSet) DB.cmd.getResultSet();
			   while(rs.next()){
				   x+=rs.getString("dieta_diet");
			   }
		   } catch (SQLException e){
			   e.printStackTrace();
		   }
		
		return x;
	}

	public static int checkLife(int id) {
		String query = "SELECT id_user, id_lifestyle FROM user WHERE id_user="+id;
		int x=10;
		try{
			   DB.Connetti();
			   DB.cmd.executeQuery(query);
			   ResultSet rs = (ResultSet) DB.cmd.getResultSet();
			   while(rs.next()){
				   x=rs.getInt("id_lifestyle");
			   }
		   } catch (SQLException e){
			   e.printStackTrace();
		   }
		
		return x;
	}

}