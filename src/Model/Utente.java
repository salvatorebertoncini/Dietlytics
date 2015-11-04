package Model;

import java.io.Serializable;

public class Utente implements Serializable {
	//ATTRIBUTI
	private int id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private String email;
	private String nascita;
	private int altezza;
	private int peso;
	private int sesso;
	private int stiledivita;
	private int tipodieta;
	private float fattore;
	
	public float getFattore() {
		return fattore;
	}

	public void setFattore(float fattore) {
		this.fattore = fattore;
	}
	private String welcome, dietxml;
	private int abitudini[][];
	private boolean login;

	//COSTRUTTORI
	public Utente(String welcome, int id, boolean login) {
		this.welcome = welcome;
		this.dietxml = "";
		this.id = id;
		this.abitudini = new int[5][3];
		this.login = login;
	}

	public Utente(boolean login) {
		this.login = login;
	}

	public Utente() {}

	public Utente(String nome, String cognome, String username2, String password2, String email2, String nascita2,
			int sex, int altezza2, int peso2, int id2, boolean b) {
		this.name=nome;
		this.surname=cognome;
		this.username=username2;
		this.password=password2;
		this.nascita=nascita2;
		this.sesso=sex;
		this.email=email2;
		this.altezza=altezza2;
		this.id=id2;
		this.login=b;
	}

	public Utente(String nascita, int sex, int altezza, int peso, float fattore, int id) {
		this.nascita=nascita;
		this.sesso=sex;
		this.id=id;
		this.altezza=altezza;
		this.peso=peso;
		this.fattore=fattore;
	}

	//GETTERS E SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public String getWelcome() {
		return welcome;
	}
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}
	public String getDietxml() {
		return dietxml;
	}
	public void setDietxml(String dietxml) {
		this.dietxml = dietxml;
	}
	public int getAbitudini(int i, int j) {
		return abitudini[i][j];
	}
	public void setAbitudini(int i, int j, int value) {
		this.abitudini[i][j] = value;
	}
	public boolean getLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
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
}
