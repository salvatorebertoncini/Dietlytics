package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;

import com.mysql.jdbc.ResultSet;

import client.*;
import dieta.*;
import comunicazione.*;
import controller.*;
import database.*;
import model.*;

/*public class Query
{
	public static string Login(String user, String pass)
	{
		return "SELECT DIU from " + user + "porco dio";
	}
}*/

class ServerThread extends Thread {
	public boolean Attivo = true;

	MUser utenteLoggato;

	Socket socket;
	ObjectInputStream dalClient;
	ObjectOutputStream versoClient;

	ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void gestisciUtenteCrea(MUser m){

		String dbUsername=m.getSurname(), dbPassword=m.getPassword(), dbName=m.getName(), dbSurname=m.getSurname(), dbEmail=m.getEmail(),  dbDate=m.getNascita();
		int dbId = m.getId(), dbSex = m.getSesso(), dbHeight = m.getAltezza(), dbWeight = m.getPeso(), dbLifestyle = m.getStiledivita(), dbTypediet = m.getTipodieta();
		try {
			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeQuery(Query.RichiestaUtente(utenteLoggato.getId()));
			ResultSet rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
			while (rs.next()) {
				dbId = rs.getInt("id_user");
				dbUsername = rs.getString("username_user");
				dbPassword = rs.getString("password_user");
				dbName = rs.getString("name_user");
				dbSurname = rs.getString("surname_user");
				dbEmail = rs.getString("email_user");
				dbDate = rs.getString("date_user");
				dbSex = rs.getInt("id_sex");
				dbHeight = rs.getInt("height_user");
				dbWeight = rs.getInt("weight_user");
				dbLifestyle = rs.getInt("id_lifestyle");
				dbTypediet = rs.getInt("id_typediet");
			}

			utenteLoggato = new MUser(dbId,dbName,dbSurname,dbUsername,dbPassword,dbEmail,dbDate,
					dbHeight,dbWeight,dbSex,dbLifestyle,dbTypediet, 0f,dbName);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void gestisciLogin(MCredenziali m)
	{
		Risposta risposta;
		String Username = m.getUsername();
		String Password = m.getPassword();
		boolean login = false;
		String dbUsername="", dbPassword="", dbName="", dbSurname="", dbEmail="",  dbDate="";
		int dbId = 0, dbSex = 0, dbHeight = 0, dbWeight = 0, dbLifestyle = 0, dbTypediet = 0;
		try {
			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeQuery(Query.RicercaLogin(Username));
			ResultSet rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
			while (rs.next()) {
				dbId = rs.getInt("id_user");
				dbUsername = rs.getString("username_user");
				dbPassword = rs.getString("password_user");
				dbName = rs.getString("name_user");
				dbSurname = rs.getString("surname_user");
				dbEmail = rs.getString("email_user");
				dbDate = rs.getString("date_user");
				dbSex = rs.getInt("id_sex");
				dbHeight = rs.getInt("height_user");
				dbWeight = rs.getInt("weight_user");
				dbLifestyle = rs.getInt("id_lifestyle");
				dbTypediet = rs.getInt("id_typediet");
				if (dbPassword.equals(Password)) {
					login = true;
				}
			}

			utenteLoggato = new MUser(dbId,dbName,dbSurname,dbUsername,dbPassword,dbEmail,dbDate,
					dbHeight,dbWeight,dbSex,dbLifestyle,dbTypediet, 0f,dbName);

			gestisciUtenteCrea(utenteLoggato);


			if(login)
				risposta = new Risposta(TipiRisposte.LoginSuccesso, null);
			else
				risposta = new Risposta(TipiRisposte.LoginFallimento, null);
			versoClient.writeObject(risposta);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gestisciRegistrazione(MCredenziali r)
	{
		Risposta risposta;
		String messaggio = "";
		String Nome = r.getName();
		String Cognome = r.getSurname();
		String Username = r.getUsername();
		String Password = r.getPassword();
		String Email = r.getEmail();
		String Nascita = r.getNascita();
		int Sex = r.getSex();
		int Altezza = r.getHeight();
		int Peso = r.getWeight();
		int id = 0;
		boolean ok = true;
		String YYYYbirthday = r.getYyyy();
		String MMbirthday = r.getMm();
		String DDbirthday = r.getDd();
		String query = "INSERT INTO user (id_user,name_user,surname_user,username_user,password_user,email_user,date_user"
				+ ",id_sex,height_user, weight_user, id_lifestyle, id_typediet) VALUES (NULL," + "\""
				+ Nome + "\"" + "," + "\"" + Cognome + "\"" + "," + "\"" + Username + "\"" + "," + "\""
				+ Password + "\"" + "," + "\"" + Email + "\"" + "," + "\"" + Nascita + "\"" + "," + Sex
				+ "," + "\"" + Altezza + "\"" + "," + "\"" + Peso + "\"" + "," + 9 + ", " + 9 + ")";

		try{
			messaggio = debugger.DebugRegistration.DataCheck(YYYYbirthday, MMbirthday, DDbirthday);
			if (messaggio.equals("anno")) {
				messaggio = "ANNO DI NASCITA ERRATO, RIDIGITA";
				ok = false;
			} 
			else if (messaggio.equals("mese")) {
				messaggio = "MESE DI NASCITA ERRATO, RIDIGITA";
				ok = false;
			}
			else if (messaggio.equals("giorno")) {
				messaggio = "GIORNO DI NASCITA ERRATO, RIDIGITA";
				ok = false;
			}else if (debugger.DebugRegistration.UsernameCheck(Username)) {
				messaggio = "USERNAME GIà UTILIZZATO, INSERIRE USERNAME DIVERSO";
				ok = false;
			} else if (debugger.DebugRegistration.EmailCheck(Email)) {
				messaggio = "EMAIL GIà UTILIZZATA, INSERIRE EMAIL DIVERSA";
				ok = false;
			} else if (debugger.DebugRegistration.EmailOk(Email)) {
				messaggio = "EMAIL NON VALIDA, INSERIRE EMAIL VALIDA";
				ok = false;
			} else if (ok) {

				try {
					ConnessioneDatabase.Connetti();
					ConnessioneDatabase.cmd.executeUpdate(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				query = "SELECT * FROM `user`";
				try {
					ConnessioneDatabase.Connetti();
					ConnessioneDatabase.cmd.executeQuery(query);
					ResultSet rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
					while (rs.next()) {
						id = rs.getInt("id_user");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			utenteLoggato = new MUser(id,Nome,Cognome,Username,Password,Email,Nascita,
					Altezza,Peso,Sex,9,9, 0f,Nome);
			gestisciUtenteCrea(utenteLoggato);

			utenteLoggato.setWelcome(messaggio);

			MUtility welcome = new MUtility(messaggio);
			if(ok)
				risposta = new Risposta(TipiRisposte.RegistrazioneSuccesso, welcome);
			else
				risposta = new Risposta(TipiRisposte.RegistrazioneFallimento, welcome);
			versoClient.writeObject(risposta);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void gestisciInizializzazioneMenu()
	{
		String messaggio = utenteLoggato.getWelcome();
		try {
			MUtility utility = new MUtility(messaggio);
			Risposta risposta = new Risposta(TipiRisposte.WelcomeSuccesso, utility);
			versoClient.writeObject(risposta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inizializzaUtente()
	{
		try
		{
			int id = utenteLoggato.getId();
			String query = "SELECT * FROM user WHERE id_user = "+id;

			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeQuery(query);
			ResultSet rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
			MUser utenteModificato = new MUser();
			while (rs.next()) {
				utenteModificato.setId(rs.getInt("id_user"));
				utenteModificato.setWelcome(rs.getString("name_user"));
				utenteModificato.setName(rs.getString("name_user"));
				utenteModificato.setSurname(rs.getString("surname_user"));
				utenteModificato.setNascita(rs.getString("date_user"));
				utenteModificato.setEmail(rs.getString("email_user"));
				utenteModificato.setUsername(rs.getString("username_user"));
				utenteModificato.setPassword(rs.getString("password_user"));
				utenteModificato.setSesso(rs.getInt("id_sex"));
				utenteModificato.setAltezza(rs.getInt("height_user"));
				utenteModificato.setPeso(rs.getInt("weight_user"));

				utenteModificato.setAbitudini(utenteLoggato.getAbitudini());
				utenteModificato.setElencoCibi(utenteLoggato.getElencoCibi());
			}
			utenteLoggato=utenteModificato;

			Risposta risposta = new Risposta(TipiRisposte.RichiestaInizializzaUtenteSuccesso, (MUser) utenteLoggato);
			versoClient.writeObject(risposta);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void readMatrix()
	{
		FileReader f;
		BufferedReader b = null;
		String s, split;
		MUser utenteAbitudineModificate = new MUser();
		int i;
		try {
			f = new FileReader("matrici/" + utenteLoggato.getId() + ".txt");
			b = new BufferedReader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			System.out.println("STAMPA LETTURA DA FILE");
			System.out.println(s = b.readLine());
			split = s.toString();
			String[] items = split.split(" ");
			for (i = 0; i < items.length; i++)
				utenteLoggato.setAbitudini(0, i, Integer.parseInt(items[i]));
			System.out.println(s = b.readLine());
			split = s.toString();
			items = split.split(" ");
			for (i = 0; i < items.length; i++)
				utenteLoggato.setAbitudini(1, i, Integer.parseInt(items[i]));
			System.out.println(s = b.readLine());
			split = s.toString();
			items = split.split(" ");
			for (i = 0; i < items.length; i++)
				utenteLoggato.setAbitudini(2, i, Integer.parseInt(items[i]));
			System.out.println(s = b.readLine());
			split = s.toString();
			items = split.split(" ");
			for (i = 0; i < items.length; i++)
				utenteLoggato.setAbitudini(3, i, Integer.parseInt(items[i]));
			System.out.println(s = b.readLine());
			split = s.toString();
			items = split.split(" ");
			for (i = 0; i < items.length; i++)
				utenteLoggato.setAbitudini(4, i, Integer.parseInt(items[i]));

			System.out.println("STAMPA VALORI DALLA MATRICE MOMENTANEA");
			for (i = 0; i < 5; i++) {
				for (int j = 0; j < 3; j++)
					System.out.print(utenteLoggato.getAbitudini(i, j) + " ");
				System.out.println("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gestisciAbitudiniUtente()
	{
		int id = utenteLoggato.getId();
		System.out.println(id);
		ArrayList<MFood> elencoCibi = new ArrayList<MFood>();
		int idFood;
		String nomeFood;
		Risposta answer;
		String query = "SELECT id_food,name_food FROM food";
		String queryy = "SELECT id_typediet FROM user WHERE id_user = " + id;
		System.out.println(queryy);
		try {
			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeQuery(query);
			ResultSet rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
			while (rs.next()) {
				idFood = rs.getInt("id_food");
				nomeFood = rs.getString("name_food");
				MFood cibo = new MFood(idFood, nomeFood);
				elencoCibi.add(cibo);
			}
			ConnessioneDatabase.cmd.executeQuery(queryy);
			System.out.println(queryy);
			ResultSet rs1 = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
			while (rs1.next()) {
				int idDieta = rs1.getInt("id_typediet");
				if (idDieta == 9) 
				{
					utenteLoggato.setElencoCibi(elencoCibi);
					answer = new Risposta(TipiRisposte.RichiestaAbitudiniFallimento, utenteLoggato);
				}
				else
				{
					readMatrix();
				}

			}
			MUser temp = new MUser();
			temp.setId(utenteLoggato.getId());
			temp.setAbitudini(utenteLoggato.getAbitudini());
			temp.setAltezza(utenteLoggato.getAltezza());
			temp.setElencoCibi(elencoCibi);
			temp.setEmail(utenteLoggato.getEmail());
			temp.setFattore(utenteLoggato.getFattore());
			temp.setName(utenteLoggato.getName());
			temp.setNascita(utenteLoggato.getNascita());
			temp.setPassword(utenteLoggato.getPassword());
			temp.setPeso(utenteLoggato.getPeso());
			temp.setSesso(utenteLoggato.getSesso());
			temp.setStiledivita(utenteLoggato.getStiledivita());
			temp.setSurname(utenteLoggato.getSurname());
			temp.setTipodieta(utenteLoggato.getTipodieta());
			temp.setUsername(utenteLoggato.getUsername());
			temp.setWelcome(utenteLoggato.getWelcome());
			utenteLoggato = null;
			utenteLoggato = new MUser();
			utenteLoggato = temp;
			answer = null;
			answer = new Risposta(TipiRisposte.RichiestaAbitudiniSuccesso, utenteLoggato);
			versoClient.reset();
			versoClient.writeObject(answer);	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static double calcolaKcal(int userid) {

		double MBR = 0;
		double TID = 0;
		float lifestyle = 0;
		int sex = 0, height = 0, weight = 0, age;
		String nascita="";

		try {
			String query = "SELECT * FROM user WHERE id_user = " + userid;
			ConnessioneDatabase.Connetti();
			ResultSet rs = (ResultSet) ConnessioneDatabase.cmd.executeQuery(query);
			while (rs.next()) {
				nascita = rs.getString("date_user");
				sex = rs.getInt("id_sex");
				height = rs.getInt("height_user");
				weight = rs.getInt("weight_user");
				lifestyle = rs.getInt("id_lifestyle");
			}

			// prelevo l'anno di nascita dalla data di nascita
			String split = nascita;
			System.out.println(split);
			String[] items = split.split("-");
			String YYYYbirthday = items[0];
			// calcolo l'età
			age = 2016 - Integer.parseInt(YYYYbirthday);

			if (sex == 1)
				MBR = (9.99 * weight) + (4.92 * height) + (4.92 * age) + 5;
			else
				MBR = (9.99 * weight) + (4.92 * height) + (4.92 * age) - 161;

			TID = MBR * 0.10;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return MBR * lifestyle + TID;
	}


	public void gestisciNuovaDieta(MDiet r)
	{
		String nomeUtente = utenteLoggato.getWelcome();
		int lifestyle = r.getId_lifestyle();
		int typediet = r.getId_lifestyle();
		int all = r.getId_allergy();
		String date = r.getDataInizioDieta();
		int id = utenteLoggato.getId();

		// INSERISCO LO STILE DI VITA ALL'UTENTE CORRENTE
		String query = "UPDATE `user` SET `id_lifestyle`=" + lifestyle + " WHERE `id_user` =" + id
				+ ";";
		System.out.println(query);
		String dieta = "";
		try {
			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// VERIFICO SE SONO GIà STATE INIZIATE DIETE
		String query1 = "SELECT * FROM `storico` WHERE `id_user`=" + id + ";";
		System.out.println(query1);
		int storico = 0;
		ResultSet rs;
		double kcal;
		try {
			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeQuery(query1);
			rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
			while (rs.next())
				storico = Integer.parseInt(rs.getString("id_storico"));

			kcal = calcolaKcal(id);

			if (storico < 1) {
				System.out.println("Prima dieta per l'utente con ID: " + id);
				dieta = ComposizioneDieta.composizioneDieta(nomeUtente, (int) kcal, typediet, all);
				// apro nuova dieta
				query = "INSERT INTO `diet`(`id_typediet`, `kcal_diet`, `dieta_diet`) " + "VALUES ("
						+ typediet + "," + kcal + ",'" + dieta + "')";
				try {
					ConnessioneDatabase.Connetti();
					ConnessioneDatabase.cmd.executeUpdate(query);

				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println(query);
				// cerco la dieta per associarla allo storico
				query = "SELECT * FROM `diet`";
				int iddieta = 0;
				try {
					ConnessioneDatabase.Connetti();
					ConnessioneDatabase.cmd.executeQuery(query);
					rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
					while (rs.next())
						iddieta = rs.getInt("id_diet");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// associo dieta a storico e id
				query1 = "INSERT INTO `storico`(`id_user`, `id_diet`, `startdiet_storico`, `enddiet_storico`)"
						+ " VALUES (" + id + "," + iddieta + ",'" + date + "',\"\")";
				try {
					ConnessioneDatabase.Connetti();
					ConnessioneDatabase.cmd.executeUpdate(query1); /////////////

				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println(query1);
			} else {
				System.out.println("Dieta numero: " + (storico + 1) + " per l'utente con ID: " + id);
				dieta = ComposizioneDieta.composizioneDieta(nomeUtente, (int) kcal, typediet, all);
				// chiudo vecchia dieta
				query = "UPDATE `storico` SET `enddiet_storico`='" + date + "' WHERE `id_storico` ="
						+ storico + ";";
				try {
					ConnessioneDatabase.Connetti();
					ConnessioneDatabase.cmd.executeUpdate(query);

				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println(query);
				// apro nuova dieta
				query = "INSERT INTO `diet`(`id_typediet`, `kcal_diet`, `dieta_diet`) " + "VALUES ("
						+ typediet + "," + kcal + ",'" + dieta + "')";
				try {
					ConnessioneDatabase.Connetti();
					ConnessioneDatabase.cmd.setEscapeProcessing(true);
					ConnessioneDatabase.cmd.executeUpdate(query);

				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println(query);
				// cerco la dieta per associarla allo storico
				query = "SELECT * FROM `diet`";
				int iddieta = 0;
				try {
					ConnessioneDatabase.Connetti();
					ConnessioneDatabase.cmd.executeQuery(query);
					rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
					while (rs.next())
						iddieta = rs.getInt("id_diet");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// associo dieta a storico e id
				query = "INSERT INTO `storico`(`id_user`, `id_diet`, `startdiet_storico`)" + " VALUES ("
						+ id + "," + iddieta + ",'" + date + "')";
				try {
					ConnessioneDatabase.Connetti();
					ConnessioneDatabase.cmd.executeUpdate(query);

				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println(query);
			}
			Risposta risposta = new Risposta(TipiRisposte.RichiestaNuovaDietaSuccesso, true);
			versoClient.writeObject(risposta);
		} catch (Exception e1) {
			e1.printStackTrace();
		}


	}

	public void gestisciRicercaCibo(MDiet dieta)
	{
		String Nome = dieta.getNome();
		float gr = dieta.getGr();
		String result = "";
		String query = "SELECT * FROM `food` WHERE `name_food` LIKE \"%" + Nome + "%\"";

		String name;
		float kcal;
		float kj;
		float protot;
		float colest;
		float fiber;
		float alcool;
		float ferro;

		try {
			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeQuery(query);
			ResultSet rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
			while (rs.next()) {
				name = rs.getString("name_food");
				kcal = (float) ((gr / 100.00) * Float.parseFloat(rs.getString("kcal_food")));
				kj = (float) ((gr / 100.00) * Float.parseFloat(rs.getString("kj_food")));
				protot = (float) ((gr / 100.00) * Float.parseFloat(rs.getString("protot_food")));
				colest = (float) ((gr / 100.00) * Float.parseFloat(rs.getString("colest_food")));
				fiber = (float) ((gr / 100.00) * Float.parseFloat(rs.getString("fiber_food")));
				alcool = (float) ((gr / 100.00) * Float.parseFloat(rs.getString("alcool_food")));
				ferro = (float) ((gr / 100.00) * Float.parseFloat(rs.getString("ferro_food")));

				if (name != null) {
					result = result + "Nome: " + name + " KCAL: " + Float.toString(kcal) + " KJ: "
							+ Float.toString(kj) + " proteine: " + Float.toString(protot)
							+ " colesterolo: " + Float.toString(colest) + " fibre: "
							+ Float.toString(fiber) + " alcool: " + Float.toString(alcool) + " ferro: "
							+ Float.toString(ferro) + "\n";
				}

				else
					result = "CIBO NON TROVATO NEL DATABASE, PROVA A REINSERIRE";

			}

			MUtility risultato = new MUtility(result);
			Risposta risposta = new Risposta(TipiRisposte.RicercaCiboSuccesso, risultato);
			versoClient.writeObject(risposta);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void gestisciUtenteCancella() 
	{
		String query = "DELETE FROM `user` WHERE `id_user`=" + utenteLoggato.getId();
		try {
			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeUpdate(query);

			Risposta risposta = new Risposta(TipiRisposte.EliminaUtenteSuccesso, null);
			versoClient.writeObject(risposta);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void gestisciUtenteModifica(MCredenziali r)
	{
		String regNome = r.getName();
		String regCognome = r.getSurname();
		String regUsername = r.getUsername();
		String regPassword = r.getPassword();
		String regEmail = r.getEmail();
		String regDataDiNascita = r.getNascita();
		String YYYYbirthday = r.getYyyy();
		String MMbirthday = r.getMm();
		String DDbirthday = r.getDd();
		int regSex = r.getSex();
		int regAltezza = r.getHeight();
		int regPeso = r.getWeight();
		int id = utenteLoggato.getId();
		boolean ok=true;
		String messaggio;

		String query = "UPDATE `user` SET `name_user`=" + "\"" + regNome + "\"" + ", `surname_user`="
				+ "\"" + regCognome + "\"" + ", `username_user`= " + "\"" + regUsername + "\""
				+ ", `password_user`= " + "\"" + regPassword + "\"" + ", `email_user`=" + "\""
				+ regEmail + "\"" + ", `date_user`=" + "\"" + regDataDiNascita + "\"" + ", `id_sex`="
				+ "\"" + regSex + "\"" + ", `height_user`=" + "\"" + regAltezza + "\""
				+ ", `weight_user`=" + "\"" + regPeso + "\"" + " WHERE `id_user`=" + id + ";";
		System.out.println(query);
		messaggio = debugger.DebugRegistration.DataCheck(YYYYbirthday, MMbirthday, DDbirthday);
		if (messaggio.equals("anno")) {
			messaggio = "ANNO DI NASCITA ERRATO, RIDIGITA";
			System.out.println(messaggio);
			ok = false;
		} 
		else if (messaggio.equals("mese")) {
			messaggio = "MESE DI NASCITA ERRATO, RIDIGITA";
			System.out.println(messaggio);
			ok = false;
		}
		else if (messaggio.equals("giorno")) {
			messaggio = "GIORNO DI NASCITA ERRATO, RIDIGITA";
			System.out.println(messaggio);
			ok = false;
		}else 
			if (ok) {
				try {

					ConnessioneDatabase.Connetti();
					ConnessioneDatabase.cmd.executeUpdate(query);
					utenteLoggato.setWelcome(regNome);
					utenteLoggato.setName(regNome);
					utenteLoggato.setSurname(regCognome);
					utenteLoggato.setNascita(regDataDiNascita);
					utenteLoggato.setEmail(regEmail);
					utenteLoggato.setUsername(regUsername);
					utenteLoggato.setPassword(regPassword);
					utenteLoggato.setSesso(regSex);
					utenteLoggato.setAltezza(regAltezza);
					utenteLoggato.setPeso(regPeso);
					Risposta risposta = new Risposta(TipiRisposte.ModificaUtenteSuccesso, null);
					versoClient.writeObject(risposta);		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else
				try {
					ConnessioneDatabase.Connetti();
					ConnessioneDatabase.cmd.executeUpdate(query);
					Risposta risposta = new Risposta(TipiRisposte.ModificaUtenteFallimento, null);
					versoClient.writeObject(risposta);		
				} catch (Exception e) {
					e.printStackTrace();
				}
	}

	public void gestisciInizializzaDieta()
	{
		int id = utenteLoggato.getId();

		String query = "SELECT storico.id_storico, storico.startdiet_storico, diet.dieta_diet FROM storico, diet WHERE storico.id_diet = diet.id_diet AND id_user="
				+ id;

		int idDieta = 0;
		String nomeDieta = null;
		ArrayList<MDiet> elencoDieta = new ArrayList<MDiet>();
		System.out.println("CERCO NEL DB SE CI SONO DIETE NELLO STORICO INIZIATE QUEL GIORNO");
		try {
			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeQuery(query);
			ResultSet rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
			while (rs.next()) {
				idDieta = rs.getInt("id_storico");
				nomeDieta = rs.getString("startdiet_storico");
				MDiet dietaStorico = new MDiet(idDieta, nomeDieta);
				elencoDieta.add(dietaStorico);
			}

			Risposta risposta = new Risposta(TipiRisposte.RichiestaInizializzaTpDietaSuccesso, elencoDieta);
			versoClient.writeObject(risposta);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gestisciStampaDieta(int r){
		int id = r;

		String query = "SELECT storico.id_storico, diet.dieta_diet FROM storico, diet WHERE storico.id_diet = diet.id_diet AND storico.id_storico = "
				+ id;
		String dieta = "";
		try {
			ConnessioneDatabase.Connetti();
			ConnessioneDatabase.cmd.executeQuery(query);
			ResultSet rs = (ResultSet) ConnessioneDatabase.cmd.getResultSet();
			while (rs.next()) {
				dieta = rs.getString("dieta_diet");
			}
			Risposta risposta = new Risposta(TipiRisposte.RichiestaInizializzaDietaSuccesso, dieta);
			versoClient.writeObject(risposta);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void gestisciInformazioniUtenteLoggato(MUser utenteTemp)
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("matrici/" + utenteLoggato.getId()  + ".txt", "UTF-8");
			writer.println(utenteTemp.getAbitudini(0, 0) + " " + utenteTemp.getAbitudini(0, 1) + " " + utenteTemp.getAbitudini(0, 2));
			writer.println(utenteTemp.getAbitudini(1, 0) + " " + utenteTemp.getAbitudini(1, 1) + " " + utenteTemp.getAbitudini(1, 2));
			writer.println(utenteTemp.getAbitudini(2, 0) + " " + utenteTemp.getAbitudini(2, 1) + " " + utenteTemp.getAbitudini(2, 2));
			writer.println(utenteTemp.getAbitudini(3, 0) + " " + utenteTemp.getAbitudini(3, 1) + " " + utenteTemp.getAbitudini(3, 2));
			writer.println(utenteTemp.getAbitudini(4, 0) + " " + utenteTemp.getAbitudini(4, 1) + " " + utenteTemp.getAbitudini(4, 2));
			writer.close();
			Risposta risposta = new Risposta(TipiRisposte.RichiestaInformazioniUtenteLoggato, utenteLoggato);			
			versoClient.writeObject(risposta);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public void run()
	{
		try {
			dalClient = new ObjectInputStream(socket.getInputStream());
			versoClient = new ObjectOutputStream(socket.getOutputStream());


			while(Attivo)
			{
				Richiesta richiesta;

				richiesta = (Richiesta) dalClient.readObject();

				switch(richiesta.getTipo())
				{
				case TipiRichieste.Login:
					gestisciLogin((MCredenziali) richiesta.Oggetto);
					break;

				case TipiRichieste.Registrazione:
					gestisciRegistrazione((MCredenziali) richiesta.Oggetto);
					break;

				case TipiRichieste.InizializzaMenu:
					gestisciInizializzazioneMenu();
					break;

				case TipiRichieste.UtenteCrea:
					// gestisciUtenteCrea((MUtente) richiesta.Oggetto);
					break;

				case TipiRichieste.UtenteCancella:
					gestisciUtenteCancella();
					break;

				case TipiRichieste.RicercaCibo:
					gestisciRicercaCibo((MDiet) richiesta.Oggetto);
					break;

				case TipiRichieste.AbitudiniUtente:
					gestisciAbitudiniUtente();
					break;

				case TipiRichieste.InizializzaUtente:
					inizializzaUtente();
					break;

				case TipiRichieste.UtenteModifica:
					gestisciUtenteModifica((MCredenziali) richiesta.Oggetto);
					break;

				case TipiRichieste.NuovaDieta:
					gestisciNuovaDieta((MDiet) richiesta.Oggetto);
					break;

				case TipiRichieste.InizializzaStorico:
					gestisciInizializzaDieta();
					break;

				case TipiRichieste.StampaDieta:
					gestisciStampaDieta((int) richiesta.Oggetto);
					break;

				case TipiRichieste.InformazioniUtenteLoggato:
					gestisciInformazioniUtenteLoggato((MUser) richiesta.Oggetto);
					break;

				default:
					System.out.println("Errore Richiesta");
					break;
				}

			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    
}

public class Server{


	public static void main(String[] args) {
		Document document = Server.ReadXML("server_config.xml");

        final int port = Integer.parseInt(document.getElementsByTagName("Port_socket").item(0).getTextContent());

		ServerSocket ssock = null;
		Socket sock = null;
		try {
			ssock = new ServerSocket(port);
			System.out.println("In attesa del client...");

			while (true) {
				System.out.println("Server Startato: while true threads in ascolto...");
				sock = ssock.accept();

				ServerThread clientThread = new ServerThread(sock);
				clientThread.start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage() + "->" + ssock);
		} finally {
			if (ssock != null) {
				try {
					ssock.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	public static Document ReadXML(String path) {
        Document document = null;
        try {
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(file);
            document.getDocumentElement().normalize();
        } catch(Exception exc) {
            System.out.println("Errore ReadXML: " + exc.getMessage());
            exc.printStackTrace();
        }

        return document;
    }
}
