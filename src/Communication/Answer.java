package Communication;

import java.io.Serializable;

public class Answer implements Serializable{

	boolean login=false;
	private int idLoggato;
	private String valcombobox, oggettoRicerca, nome, cognome, username, password, dieta,tipoRichiesta;

	public boolean getLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	public int getIdLoggato() {
		return idLoggato;
	}
	public void setIdLoggato(int idLoggato) {
		this.idLoggato = idLoggato;
	}
	public String getValcombobox() {
		return valcombobox;
	}
	public void setValcombobox(String valcombobox) {
		this.valcombobox = valcombobox;
	}
	public String getOggettoRicerca() {
		return oggettoRicerca;
	}
	public void setOggettoRicerca(String oggettoRicerca) {
		this.oggettoRicerca = oggettoRicerca;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
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
	public String getDieta() {
		return dieta;
	}
	public void setDieta(String dieta) {
		this.dieta = dieta;
	}
	public String getTipoRichiesta() {
		return tipoRichiesta;
	}
	public void setTipoRichiesta(String tipoRichiesta) {
		this.tipoRichiesta = tipoRichiesta;
	}

	public Answer(){}


}
