package Client;
import java.io.*;
import java.net.*;

import Server.ServerDAOCheck;
import Server.ServerDAOLogin;
import Server.ServerDAORegistration;

public class Client 
{
	Socket socket;
	DataOutputStream OutputStream;
	BufferedReader InputStream;
	BufferedReader stdIn;
	String query;
	
	public void start() 
	{
		try{
		 socket = new Socket ("127.0.0.1",6001);
		 //socket.close();
		}catch(Exception e){
			System.out.println(e.getMessage()+"------>"+socket);
		}
	}
	
	public static boolean requestChange(int id, String RegNome, String RegCognome, String RegUsername, String RegPassword, String RegEmail, String RegDataDiNascita, 
			int RegSex, String RegWeight, String RegHeight){
		boolean login = ServerDAORegistration.ChangeCheck(id, RegNome, RegCognome, RegUsername, RegPassword, RegEmail, RegDataDiNascita, RegSex, RegWeight, RegHeight);
		return login;
	}
	
	public static boolean requestChangeUser(int id, String Username, String Password){
		boolean login = ServerDAORegistration.ChangeUser(id, Username, Password);
		return login;
	}
	
	public static boolean requestLogin(String Username, String Password){
		boolean login = ServerDAOLogin.LoginCheck(Username, Password);
		return login;
	}

	public static boolean requestRegistration(String regNome, String regCognome, String regUsername, String regPassword,
			String regEmail, String regDataDiNascita, int regSex, String regWeight, String regHeight) {
		boolean Registration = ServerDAORegistration.RegistrationCheck(regNome, regCognome, regUsername, regPassword, regEmail, 
				regDataDiNascita, regSex, regWeight, regHeight);
		return Registration;
	}
	
	public static String findFood(String nome, int gr){
		String find = ServerDAOCheck.FindCheck(nome, gr);
		return find;
	}

	public static boolean requestDeleteUser(int id) {
		boolean ok = true;
		ok = ServerDAORegistration.deleteUser(id);
		return ok;	
	}
	
	public static boolean requestField(int id, StringBuilder regNome, StringBuilder regCognome, StringBuilder regUsername, StringBuilder regPassword, StringBuilder regEmail, StringBuilder regDataDiNascita, StringBuilder regSex, StringBuilder regHeight, StringBuilder regWeight){
		boolean ok = true;
		ok = ServerDAORegistration.requestField(id, regNome, regCognome, regUsername, regPassword, regEmail, regDataDiNascita, regSex, regHeight, regWeight);
		System.out.println("prova sex Client: "+regSex);
		return ok;
	}
	
	public static boolean newDiet(int id, int lifestyle, int typediet, String date, int all){
		return ServerDAORegistration.registerNewDiet(id, lifestyle, typediet, date, all);
	}

	public static boolean algorithminformations(int id, StringBuilder l, StringBuilder s, StringBuilder h, StringBuilder w, StringBuilder a) {
		return ServerDAORegistration.requestInformation(id, l, s, h, w, a);	
	}

	public static boolean readDiet(StringBuilder dieta, StringBuilder data) {
		return ServerDAORegistration.readDiet(dieta, data);		
	}

	public static void initializeHistoryButton(StringBuilder num, StringBuilder name, StringBuilder id) {
		ServerDAORegistration.initializeHistoryButton(num, name, id);
		
	}

	public static String findDiet(int string) {
		return ServerDAORegistration.findDiet(string);
	}

	public static int checkLife(int id) {
		int ok=9;
		ok=ServerDAORegistration.checkLife(id);
		return ok;
	}
}
