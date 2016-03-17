package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MDiet implements Serializable {

	private int id;
	private int id_typediet;
	private int id_lifestyle;
	private int id_allergy;
	private int kcal;
	private int gr;
	private String nome;
	private String dieta;
	private String dataInizioDieta;
	private String dataFineDieta;
	
	public MDiet(int id, String dieta){
		this.id = id;
		this.dieta = dieta;
	}

	public MDiet(int id_typediet, int id_lifestyle, String dataInizioDieta, int id_allergy) {
		this.id_typediet = id_typediet;
		this.id_lifestyle = id_lifestyle;
		this.id_allergy=id_allergy;
		this.dataInizioDieta = dataInizioDieta;
	}
	
	public MDiet(int lifestyle, int typediet, int kcal, String dieta) {
		this.id = id;
		this.id_typediet = typediet;
		this.id_lifestyle = lifestyle;
		this.kcal = kcal;
		this.dieta = dieta;
	}
	
	public MDiet(String nome, int gr)
	{
		this.nome=nome;
		this.gr=gr;
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getGr() {
		return gr;
	}

	public void setGr(int gr) {
		this.gr = gr;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getId_lifestyle() {
		return id_lifestyle;
	}

	public void setId_lifestyle(int id_lifestyle) {
		this.id_lifestyle = id_lifestyle;
	}

	public int getId_allergy() {
		return id_allergy;
	}

	public void setId_allergy(int id_allergy) {
		this.id_allergy = id_allergy;
	}

	public String getDataInizioDieta() {
		return dataInizioDieta;
	}

	public void setDataInizioDieta(String dataInizioDieta) {
		this.dataInizioDieta = dataInizioDieta;
	}

	public String getDataFineDieta() {
		return dataFineDieta;
	}

	public void setDataFineDieta(String dataFineDieta) {
		this.dataFineDieta = dataFineDieta;
	}

	public int getId_typediet() {
		return id_typediet;
	}
	public void setId_typediet(int id_typediet) {
		this.id_typediet = id_typediet;
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	public String getDieta() {
		return dieta;
	}
	public void setDieta(String dieta) {
		this.dieta = dieta;
	}
	
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.writeObject(id);
		aOutputStream.writeObject(id_typediet);
		aOutputStream.writeObject(kcal);
		aOutputStream.writeObject(gr);
		aOutputStream.writeObject(nome);
		aOutputStream.writeObject(dieta);
		aOutputStream.writeObject(id_lifestyle);
		aOutputStream.writeObject(id_allergy);
		aOutputStream.writeObject(dataInizioDieta);
		aOutputStream.writeObject(dataFineDieta);
	}
	
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		id = (int) aInputStream.readObject();
		id_typediet = (int) aInputStream.readObject();
		kcal = (int) aInputStream.readObject();
		gr = (int) aInputStream.readObject();
		nome = (String) aInputStream.readObject();
		dieta = (String) aInputStream.readObject();
		id_lifestyle = (int) aInputStream.readObject();
		id_allergy = (int) aInputStream.readObject();
		dataInizioDieta = (String) aInputStream.readObject();
		dataFineDieta = (String) aInputStream.readObject();
	}

}
