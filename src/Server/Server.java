package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.sql.SQLException;
import com.mysql.jdbc.ResultSet;
import Communication.Request;
import DB.DB;
import Diet.Dieta;
import Dietlytics.Algorithm;
import Dietlytics.Dietlytics;
import Model.Utente;

public class Server implements Runnable 
{
	Socket socket;
	ObjectInputStream dalClient;
	ObjectOutputStream versoClient;
	static ServerSocket ssock;

	Server(Socket csocket) 
	{
		this.socket = csocket;
	}

	@Override
	public void run()
	{
		try
		{
			dalClient = new ObjectInputStream(socket.getInputStream());
			versoClient = new ObjectOutputStream(socket.getOutputStream());

			while(true)
			{
				try 
				{
					Request r = (Request) dalClient.readObject();
					System.out.println(r.getUsername());
					System.out.println(r.getPassword());
					System.out.println(r.getTipoRichiesta());

					if(r.getTipoRichiesta().equals("LoginCheck"))
					{
						String username = r.getUsername();
						String password = r.getPassword();
						String query = "SELECT * FROM user WHERE username_user =\""+username+"\" AND password_user=\""+password+"\"";
						String dbUsername, dbPassword, dbNome;
						int dbId;
						try {
							DB.Connetti();
							DB.cmd.executeQuery(query);
							ResultSet rs = (ResultSet) DB.cmd.getResultSet();
							if(rs.next()){
								dbUsername = rs.getString("username_user"); System.out.println("User: "+dbUsername);
								dbPassword = rs.getString("password_user"); System.out.println("Password: "+dbPassword);
								dbNome = rs.getString("name_user")+" "+rs.getString("surname_user"); 
								dbId = rs.getInt("id_user");
								if(dbPassword.equals(password)){
									Utente utente = new Utente(dbNome, dbId, true);
									versoClient.writeObject(utente);
								}
							}
							else
							{
								Utente utente = new Utente(false);
								versoClient.writeObject(utente);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					else if(r.getTipoRichiesta().equals("RegistrationCheck")) {
						boolean userAggiunto=true;
						int Sex = r.getSex();
						int Altezza = r.getAltezza();
						int Peso = r.getPeso();
						String Nome = r.getNome();
						String Cognome = r.getCognome();
						String Email = r.getEmail();
						String Nascita = r.getNascita();
						String Username = r.getUsername();
						String Password = r.getPassword();
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
						versoClient.writeObject(userAggiunto);
					}

					else if(r.getTipoRichiesta().equals("ChangeCheck")) {
						boolean userAggiunto=true;
						int id = r.getIdLoggato();
						int Sex = r.getSex();
						int Altezza = r.getAltezza();
						int Peso = r.getPeso();
						String Nome = r.getNome();
						String Cognome = r.getCognome();
						String Email = r.getEmail();
						String Nascita = r.getNascita();
						String Username = r.getUsername();
						String Password = r.getPassword();
						String query= "UPDATE `user` SET `name_user`="+"\""+Nome+"\""+", `surname_user`="+"\""+Cognome+"\""+", `username_user`= "+"\""+Username+"\""+", `password_user`= "+"\""+Password+"\""+", `email_user`="+"\""+Email+"\""+", `date_user`="+"\""+Nascita+"\""+", `id_sex`="+"\""+Sex+"\""+", `height_user`="+"\""+Altezza+"\""+", `weight_user`="+"\""+Peso+"\""+" WHERE `id_user`="+id+";";
						System.out.println(query);   
						try{
							DB.Connetti();
							DB.cmd.executeUpdate(query);
						} catch (SQLException e){
							e.printStackTrace();
						}

						versoClient.writeObject(userAggiunto);
					}

					else if(r.getTipoRichiesta().equals("ChangeUser")) {
						boolean userAggiunto=true;
						int id = r.getIdLoggato();
						String Username = r.getUsername();
						String Password = r.getPassword();
						String query="UPDATE `user` SET `id_user`="+id+",`usernamename_user`="+Username+",`password_user`="+Password+";";

						try{
							DB.Connetti();
							DB.cmd.executeUpdate(query);
						} catch (SQLException e){
							e.printStackTrace();
						}

						versoClient.writeObject(userAggiunto);
					}

					else if(r.getTipoRichiesta().equals("deleteUser")) {
						boolean userAggiunto=true;
						int id = r.getIdLoggato();
						String query="DELETE FROM `user` WHERE `id_user`="+id;
						try{
							DB.Connetti();
							DB.cmd.executeUpdate(query);
						} catch (SQLException e){
							e.printStackTrace();
						}
						versoClient.writeObject(userAggiunto);
					}

					else if(r.getTipoRichiesta().equals("newDiet")) {
						boolean userAggiunto=true;
						int id = r.getIdLoggato();
						int id_diet = r.getIdDieta();
						String regDataInizioDieta = r.getInizioDieta();
						String regDataFineDieta = r.getFineDieta();
						String query = "INSERT INTO `storico`(`id_user`, `id_diet`, `startdiet_storico`, `enddiet_storico`) VALUES "
								+ "("+id+","+id_diet+","+regDataInizioDieta+","+regDataFineDieta+")";
						try{
							DB.Connetti();
							DB.cmd.executeUpdate(query);
						} catch (SQLException e){
							e.printStackTrace();
						}

						versoClient.writeObject(userAggiunto);
					}

					else if(r.getTipoRichiesta().equals("requestField")) {
						boolean userAggiunto=true;
						int id = r.getIdLoggato();
						int Sex = r.getSex();
						int Altezza = r.getAltezza();
						int Peso = r.getPeso();
						String Nome = r.getNome();
						String Cognome = r.getCognome();
						String Email = r.getEmail();
						String Nascita = r.getNascita();
						String Username = r.getUsername();
						String Password = r.getPassword();
						String query = "SELECT * FROM `user` WHERE `id_user` ="+id;
						try{
							DB.Connetti();
							DB.cmd.executeQuery(query);
							ResultSet rs = (ResultSet) DB.cmd.getResultSet();
							while(rs.next()){
								Nome=(rs.getString("name_user"));
								Cognome=(rs.getString("surname_user"));
								Username=(rs.getString("username_user"));
								Password=(rs.getString("password_user"));
								Email=(rs.getString("email_user"));
								Nascita=(rs.getString("date_user"));
								Sex=(rs.getInt("id_sex"));
								System.out.println(Nascita);
								Altezza=(rs.getInt("height_user"));
								Peso=(rs.getInt("weight_user"));
							}
						} catch (SQLException e){
							e.printStackTrace();
						}
						Utente utente = new Utente(Nome, Cognome, Username, Password, Email, Nascita, Sex, Altezza, Peso, id, true);
						versoClient.writeObject(utente);
					}

					else if(r.getTipoRichiesta().equals("registerNewDiet")) {
						boolean userAggiunto=true;
						int id = r.getIdLoggato();
						int lifestyle = r.getLifestyle();
						int all = r.getAllergia();
						int typediet = r.getTypediet();
						String date = r.getDate();
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
						versoClient.writeObject(userAggiunto);
					}

					else if(r.getTipoRichiesta().equals("requestInformation")) {
						boolean userAggiunto=true;
						int id = r.getIdLoggato();
						int Sex = r.getSex();
						int Altezza = r.getAltezza();
						int Peso = r.getPeso();
						String Nome = r.getNome();
						String Cognome = r.getCognome();
						String Email = r.getEmail();
						String Nascita = r.getNascita();
						String Username = r.getUsername();
						String Password = r.getPassword();
						String Date = r.getDate();
						int Lifestyle = r.getLifestyle();
						float flifestyle=0;
						String query = "SELECT * FROM `user` WHERE `id_user` ="+id;
						StringBuilder x = new StringBuilder("");
						try{
							DB.Connetti();
							DB.cmd.executeQuery(query);
							ResultSet rs = (ResultSet) DB.cmd.getResultSet();
							while(rs.next()){

								Altezza=rs.getInt("date_user");
								Sex=rs.getInt("id_sex");
								Altezza=rs.getInt("height_user");
								Peso=rs.getInt("weight_user");
								Lifestyle=rs.getInt("id_lifestyle");
							}
						} catch (SQLException e){
							e.printStackTrace();
						}
						switch (Lifestyle) {
						case 0:  flifestyle=(float) 1.25; break;
						case 1:  flifestyle=(float) 1.45; break;
						case 2:  flifestyle=(float) 1.45; break;
						case 3:  flifestyle=(float) 1.65; break;
						case 4:  flifestyle=(float) 1.85; break;
						}
						Utente utente = new Utente (Date, Sex, Altezza, Peso, flifestyle, id);
						versoClient.writeObject(utente);
					}

					else if(r.getTipoRichiesta().equals("readDiet")) {
						String InizioDieta = r.getInizioDieta();
						String Date = r.getDate();
						String Dieta = r.getDieta();
						int Lifestyle = r.getLifestyle();
						float flifestyle=0;
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
						Dieta=d;
						InizioDieta=x;
						Dieta dieta = new Dieta(Dieta, InizioDieta, true);
						versoClient.writeObject(dieta);
					}

					else if(r.getTipoRichiesta().equals("initializeHistoryButton")) {
						boolean userAggiunto=true;
						int num_pane;
						int id = r.getIdLoggato();
						int Sex = r.getSex();
						int Altezza = r.getAltezza();
						int Peso = r.getPeso();
						String Nome = r.getNome();
						String Cognome = r.getCognome();
						String Email = r.getEmail();
						String Nascita = r.getNascita();
						String Username = r.getUsername();
						String Password = r.getPassword();
						String Date = r.getDate();
						int Lifestyle = r.getLifestyle();
						float flifestyle=0;
						String query = "SELECT storico.id_storico, storico.startdiet_storico, diet.dieta_diet FROM storico, diet WHERE storico.id_diet = diet.id_diet AND id_user="+id;
						String x="", k="", idk="";
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
						idk=k;
						Nome=x;
						num_pane=d;
						Dieta dieta = new Dieta(num_pane,Nome,idk,true);
						versoClient.writeObject(dieta);

					}

					else if(r.getTipoRichiesta().equals("findDiet")) {
						int id=r.getIdDieta();
						String query = "SELECT storico.id_storico, diet.dieta_diet FROM storico, diet WHERE storico.id_diet = diet.id_diet AND storico.id_storico = "+id, x="";
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
						Dieta dieta = new Dieta(x, true);
						versoClient.writeObject(dieta);
					}

					else if(r.getTipoRichiesta().equals("checkLife")) {
						int id=r.getIdLoggato();
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

						Dieta dieta = new Dieta(x, true);
						versoClient.writeObject(dieta);
					}

					else if(r.getTipoRichiesta().equals("FindCheck")) {
						String Nome=r.getNome();
						float gr=r.getGrammi();
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
						Dieta dieta = new Dieta(result, true);
						versoClient.writeObject(dieta);
					}



				} catch(Exception e){} 
			}
		}catch(Exception e){}	    
	}  

	public static void main(String args[])
	{
		try
		{
			ssock = new ServerSocket(6001);
			System.out.println("In attesa del client...");

			while (true) 
			{
				Socket sock = ssock.accept();
				System.out.println("Effettuata nuova connessione al Server");
			}
		}catch(Exception e){
			System.out.println(e.getMessage()+"->"+ssock);
		}
		finally
		{
			if (ssock != null) 
			{
				try 
				{
					ssock.close();
				} 
				catch (Exception e) {}
			}
		}
	}   

}

