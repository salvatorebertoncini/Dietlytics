package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class MUser implements Serializable {
	
	private int id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private String email;
	private String nascita;
	private String welcome;
	private int altezza;
	private int peso;
	private int sesso;
	private int stiledivita;
	private int tipodieta;
	private float fattore;
	private int abitudini[][];
	private ArrayList<MFood> elencoCibi;
	
	public MUser(){

		this.abitudini = new int[5][3];
	}

	public MUser(int id, String name, String surname, String username, String password, String email, String nascita,
			int altezza, int peso, int sesso, int stiledivita, int tipodieta, float fattore, String welcome) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.nascita = nascita;
		this.altezza = altezza;
		this.peso = peso;
		this.sesso = sesso;
		this.stiledivita = stiledivita;
		this.tipodieta = tipodieta;
		this.fattore = fattore;
		this.welcome = welcome;
		this.abitudini = new int[5][3];
		this.elencoCibi = new ArrayList<MFood>();
	}

	
	public MUser(String regNome, String regCognome, String regUsername, String regPassword, String regEmail,
			String regDataDiNascita, String regDd, String regMm, String regAaaa, int sex, int RegHeight,
			int RegWeight) {
		this.name = regNome;
		this.surname = regCognome;
		this.username = regUsername;
		this.password = regPassword;
		this.email = regEmail;
		this.nascita = regDataDiNascita;
		this.sesso = sex;
		this.altezza = RegHeight;
		this.peso = RegWeight;
		this.abitudini = new int[5][3];
		this.elencoCibi = new ArrayList<MFood>();
	}
	
	public MUser(String regNome, String regCognome, String regUsername, String regPassword, String regEmail,
			String regDataDiNascita, int sex, int RegHeight, int RegWeight) {
		this.name = regNome;
		this.surname = regCognome;
		this.username = regUsername;
		this.password = regPassword;
		this.email = regEmail;
		this.nascita = regDataDiNascita;
		this.sesso = sex;
		this.altezza = RegHeight;
		this.peso = RegWeight;
		this.abitudini = new int[5][3];
		this.elencoCibi = new ArrayList<MFood>();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWelcome() {
		return welcome;
	}
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNascita() {
		return nascita;
	}
	public void setNascita(String nascita) {
		this.nascita = nascita;
	}
	public int getAltezza() {
		return altezza;
	}
	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getSesso() {
		return sesso;
	}
	public void setSesso(int sesso) {
		this.sesso = sesso;
	}
	public int getStiledivita() {
		return stiledivita;
	}
	public void setStiledivita(int stiledivita) {
		this.stiledivita = stiledivita;
	}
	public int getTipodieta() {
		return tipodieta;
	}
	public void setTipodieta(int tipodieta) {
		this.tipodieta = tipodieta;
	}
	public float getFattore() {
		return fattore;
	}
	public void setFattore(float fattore) {
		this.fattore = fattore;
	}	

	public int getAbitudini(int i, int j) {
		return abitudini[i][j];
	}

	public void setAbitudini(int i, int j, int x) {
		this.abitudini[i][j] = x;
	}
	

	public int[][] getAbitudini() {
		return abitudini;
	}

	public void setAbitudini(int[][] abitudini) {
		this.abitudini = abitudini;
	}

	public ArrayList<MFood> getElencoCibi() {
		return elencoCibi;
	}

	public void setElencoCibi(ArrayList<MFood> elencoCibi) {
		this.elencoCibi = elencoCibi;
	}

	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.writeObject(id);
		aOutputStream.writeObject(name);
		aOutputStream.writeObject(surname);
		aOutputStream.writeObject(username);
		aOutputStream.writeObject(password);
		aOutputStream.writeObject(email);
		aOutputStream.writeObject(nascita);
		aOutputStream.writeObject(welcome);
		aOutputStream.writeObject(altezza);
		aOutputStream.writeObject(peso);
		aOutputStream.writeObject(sesso);
		aOutputStream.writeObject(stiledivita);
		aOutputStream.writeObject(tipodieta);
		aOutputStream.writeObject(fattore);
		aOutputStream.writeObject(abitudini);
		aOutputStream.writeObject(elencoCibi);
	}
	
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		id = (int) aInputStream.readObject();
		name = (String) aInputStream.readObject();
		surname = (String) aInputStream.readObject();
		username = (String) aInputStream.readObject();
		password = (String) aInputStream.readObject();
		email = (String) aInputStream.readObject();
		nascita = (String) aInputStream.readObject();
		welcome = (String) aInputStream.readObject();
		altezza = (int) aInputStream.readObject();
		peso = (int) aInputStream.readObject();
		sesso = (int) aInputStream.readObject();
		stiledivita = (int) aInputStream.readObject();
		tipodieta = (int) aInputStream.readObject();
		fattore = (float) aInputStream.readObject();
		abitudini = (int[][]) aInputStream.readObject();
		elencoCibi = (ArrayList<MFood>) aInputStream.readObject();

	}


}
