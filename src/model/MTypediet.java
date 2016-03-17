package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MTypediet implements Serializable {
	
	private int id;
	private String name;
	
	
	public MTypediet(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
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
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.writeObject(id);
		aOutputStream.writeObject(name);
	}
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		id = (int) aInputStream.readObject();
		name = (String) aInputStream.readObject();
	}

}
