package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MCredenziali implements Serializable{
	public String Username;
	public String Password;
	public String Name;
	public String Surname;
	public String Email;
	public String Nascita;
	public String yyyy;
	public String mm;
	public String dd;
	public int Sex;
	public int Height;
	public int Weight;
	
	public MCredenziali(){}
	
	public MCredenziali(String pName, String pSurname, String pUsername, String pPassword, String pEmail, String pNascita, String aaaa, String mm, String dd, int pSex, int pHeight, int pWeight)
	{
		this.Name = pName;
		this.Surname = pSurname;
		this.Username = pUsername;
		this.Password = pPassword;
		this.Email = pEmail;
		this.Nascita = pNascita;
		this.yyyy = aaaa;
		this.mm = mm;
		this.dd = dd;
		this.Sex = pSex;
		this.Height = pHeight;
		this.Weight = pWeight;
	}
	
	public MCredenziali(String pUsername, String pPassword) {
		this.Username = pUsername;
		this.Password = pPassword;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getNascita() {
		return Nascita;
	}

	public void setNascita(String nascita) {
		Nascita = nascita;
	}

	public String getYyyy() {
		return yyyy;
	}

	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getDd() {
		return dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}

	public int getSex() {
		return Sex;
	}

	public void setSex(int sex) {
		Sex = sex;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public int getWeight() {
		return Weight;
	}

	public void setWeight(int weight) {
		Weight = weight;
	}

	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.writeObject(Username);
		aOutputStream.writeObject(Password);
		aOutputStream.writeObject(Name);
		aOutputStream.writeObject(Surname);
		aOutputStream.writeObject(Email);
		aOutputStream.writeObject(Nascita);
		aOutputStream.writeObject(yyyy);
		aOutputStream.writeObject(mm);
		aOutputStream.writeObject(dd);
		aOutputStream.writeObject(Sex);
		aOutputStream.writeObject(Height);
		aOutputStream.writeObject(Weight);
	}
	
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		Username = (String) aInputStream.readObject();
		Password = (String) aInputStream.readObject();
		Name = (String) aInputStream.readObject();
		Surname = (String) aInputStream.readObject();
		Email = (String) aInputStream.readObject();
		Nascita = (String) aInputStream.readObject();
		yyyy = (String) aInputStream.readObject();
		mm = (String) aInputStream.readObject();
		dd = (String) aInputStream.readObject();
		Sex = (int) aInputStream.readObject();
		Height = (int) aInputStream.readObject();
		Weight = (int) aInputStream.readObject();
	}
}
