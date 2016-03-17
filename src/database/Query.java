package database;


public class Query {
	
	
	public static String InizializzaAbitudini()
	{
		return "SELECT DISTINCT `id_food`,`name_food` FROM `food` WHERE `name_food` LIKE \"%"+2+"%\" ";
	}
	
	public static String Registrazione(String Nome, String Cognome, String Username, String Password, String Email, String Nascita, String Sex, String Altezza, String Peso)
	{	
		return "INSERT INTO user (id_user,name_user,surname_user,username_user,password_user,email_user,date_user"
	   		+ ",id_sex,height_user, weight_user, id_lifestyle, id_typediet) VALUES (NULL,"+"\""+Nome+"\""+","+"\""+Cognome
	   		+"\""+","+"\""+Username+"\""+","+"\""+Password+"\""+","+"\""+Email+"\""+","+"\""+Nascita+"\""+","+Sex+","+"\""
	   		+Altezza+"\""+","+"\""+Peso+"\""+","+9+", "+9+")";
	}
	
	public static String ModificaDatiPersonali(String regNome, String regCognome, String regUsername, String regPassword, String regEmail, String regDataDiNascita, String regSex, String regHeight, String regWeight, int id)
	{
		return "UPDATE `user` SET `name_user`="+"\""+regNome+"\""+", `surname_user`="+"\""+regCognome+"\""+", `username_user`= "+"\""+regUsername+"\""+", `password_user`= "+"\""+regPassword+"\""+", `email_user`="+"\""+regEmail+"\""+", `date_user`="+"\""+regDataDiNascita+"\""+", `id_sex`="+"\""+regSex+"\""+", `height_user`="+"\""+regHeight+"\""+", `weight_user`="+"\""+regWeight+"\""+" WHERE `id_user`="+id+";";
	}
	
	public static String ModificaDatiUtente(int id, String regUsername, String regPassword)
	{
		return "UPDATE `user` SET `id_user`="+id+",`usernamename_user`="+regUsername+",`password_user`="+regPassword+";";
	}
	
	public static String EliminaUtente(int id)
	{
		return "DELETE FROM `user` WHERE `id_user`="+id;
	}
	
	public static String NuovaDieta(int id_user, int id_diet, String regDataInizioDieta, String regDataFineDieta)
	{
		return "INSERT INTO `storico`(`id_user`, `id_diet`, `startdiet_storico`, `enddiet_storico`) VALUES "
			+ "("+id_user+","+id_diet+","+regDataInizioDieta+","+regDataFineDieta+")";
	}
	
	public static String RichiestaUtente(int id)
	{
		return "SELECT * FROM `user` WHERE `id_user` ="+id;
	}
	
	public static String StileDiVita(String lifestyle, int id)
	{
		return "UPDATE `user` SET `id_lifestyle`="+lifestyle+" WHERE `id_user` ="+id+";";
	}
	
	public static String VerificaDieteIniziate(int id)
	{
		return "SELECT * FROM `storico` WHERE `id_user`="+id+";";
	}
	
	public static String NuovaDieta(String typediet, int kcal, String dieta)
	{
		return "INSERT INTO `diet`(`id_typediet`, `kcal_diet`, `dieta_diet`) "+ "VALUES ("+typediet+","+kcal+",'"+dieta+"')";
	}
	
	public static String RicercaDietaDaAssociareStrorico()
	{
		return "SELECT * FROM `diet`";
	}
	
	public static String AssocioDietaStorico(int id, int iddieta, String date)
	{
		return "INSERT INTO `storico`(`id_user`, `id_diet`, `startdiet_storico`, `enddiet_storico`)"
	   	+ " VALUES ("+id+","+iddieta+",'"+date+"',\"\")";
	}
	
	public static String ChiusuraVecchiaDieta(String date, int storico)
	{
		return "UPDATE `storico` SET `enddiet_storico`='"+date+"' WHERE `id_storico` ="+storico+";";
	}
	
	public static String RichiestaInformazioni(int id)
	{
		return  "SELECT * FROM `user` WHERE `id_user` ="+id;
	}
	
	public static String LeggiDieta()
	{
		return "SELECT storico.startdiet_storico, diet.dieta_diet FROM storico, diet WHERE storico.id_diet = diet.id_diet";
	}
	
	public static String InizializzaPulsanteStorico(int id)
	{
		return  "SELECT storico.id_storico, storico.startdiet_storico, diet.dieta_diet FROM storico, diet WHERE storico.id_diet = diet.id_diet AND id_user="+id;
	}
	
	public static String TrovaDieta(int id)
	{
		return "SELECT storico.id_storico, diet.dieta_diet FROM storico, diet WHERE storico.id_diet = diet.id_diet AND storico.id_storico = "+id;
	}
	
	public static String RicercaStileVita(int id)
	{
		return "SELECT id_user, id_lifestyle FROM user WHERE id_user="+id;
	}
	
	public static String RicercaLogin(String Username)
	{
		return "SELECT * FROM user WHERE username_user=\""+Username+"\";";
	}
	
	public static String RicercaTrovata(String Nome)
	{
		return "SELECT * FROM `food` WHERE `name_food` LIKE \"%"+Nome+"%\"";
	}
}	
	
	