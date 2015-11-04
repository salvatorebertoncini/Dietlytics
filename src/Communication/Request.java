package Communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Request {

	private int idLoggato, idDieta, sex, altezza, peso, lifestyle, allergia, typediet;
	private String date, valcombobox, email, oggettoRicerca, nome, cognome, username, password, nascita,inizioDieta, fineDieta, dieta,tipoRichiesta;
	private float grammi;

	public float getGrammi() {
		return grammi;
	}
	public void setGrammi(float grammi) {
		this.grammi = grammi;
	}
	public int getLifestyle() {
		return lifestyle;
	}
	public void setLifestyle(int lifestyle) {
		this.lifestyle = lifestyle;
	}
	public int getAllergia() {
		return allergia;
	}
	public void setAllergia(int allergia) {
		this.allergia = allergia;
	}
	public int getTypediet() {
		return typediet;
	}
	public void setTypediet(int typediet) {
		this.typediet = typediet;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSex() {
		return sex;
	}
	public int getIdDieta() {
		return idDieta;
	}
	public void setIdDieta(int idDieta) {
		this.idDieta = idDieta;
	}
	public String getInizioDieta() {
		return inizioDieta;
	}
	public void setInizioDieta(String inizioDieta) {
		this.inizioDieta = inizioDieta;
	}
	public String getFineDieta() {
		return fineDieta;
	}
	public void setFineDieta(String fineDieta) {
		this.fineDieta = fineDieta;
	}
	public void setSex(int sex) {
		this.sex = sex;
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

	public Request(String username, String password, String tipoRichiesta) {
		this.username = username;
		this.password = password;
		this.tipoRichiesta = tipoRichiesta;
	}

	private void writeObject(ObjectOutputStream aOutputStream) throws IOException 
	{
		aOutputStream.writeObject(idLoggato);
		aOutputStream.writeObject(idDieta);
		aOutputStream.writeObject(sex);
		aOutputStream.writeObject(altezza);
		aOutputStream.writeObject(peso);
		aOutputStream.writeObject(lifestyle);
		aOutputStream.writeObject(allergia);
		aOutputStream.writeObject(typediet);
		aOutputStream.writeObject(date);
		aOutputStream.writeObject(valcombobox);
		aOutputStream.writeObject(email);
		aOutputStream.writeObject(oggettoRicerca);
		aOutputStream.writeObject(nome);
		aOutputStream.writeObject(cognome);
		aOutputStream.writeObject(username);
		aOutputStream.writeObject(password);
		aOutputStream.writeObject(nascita);
		aOutputStream.writeObject(inizioDieta);
		aOutputStream.writeObject(fineDieta);
		aOutputStream.writeObject(dieta);
		aOutputStream.writeObject(tipoRichiesta);
		aOutputStream.writeObject(grammi);
	}

	private void readObject( ObjectInputStream aInputStream) throws ClassNotFoundException, IOException 
	{
		idLoggato = (int)aInputStream.readObject();
		idDieta = (int)aInputStream.readObject();
		sex = (int)aInputStream.readObject();
		altezza = (int)aInputStream.readObject();
		peso = (int)aInputStream.readObject();
		lifestyle = (int)aInputStream.readObject();   
		allergia = (int)aInputStream.readObject();   
		typediet = (int)aInputStream.readObject();   
		date = (String)aInputStream.readObject();
		valcombobox = (String)aInputStream.readObject();
		email = (String)aInputStream.readObject();
		oggettoRicerca = (String)aInputStream.readObject();
		nome = (String)aInputStream.readObject();   
		cognome = (String)aInputStream.readObject();   
		username = (String)aInputStream.readObject();   
		password = (String)aInputStream.readObject();   
		nascita = (String)aInputStream.readObject();   
		inizioDieta = (String)aInputStream.readObject();   
		fineDieta = (String)aInputStream.readObject();   
		dieta = (String)aInputStream.readObject();   
		tipoRichiesta = (String)aInputStream.readObject();   
		grammi = (float)aInputStream.readObject();   

	}


}
