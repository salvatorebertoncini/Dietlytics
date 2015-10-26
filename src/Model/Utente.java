package Model;

public class Utente {
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
