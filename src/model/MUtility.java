package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MUtility implements Serializable{

	public boolean ok;
	public String Welcome;
	
	public MUtility(String welcome) {
		this.Welcome = welcome;
	}
	
	public MUtility(boolean ok){
		this.ok=ok;
	}

	public String getWelcome() {
		return Welcome;
	}

	public void setWelcome(String welcome) {
		Welcome = welcome;
	}
	
	public boolean getOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.writeObject(Welcome);
		aOutputStream.writeObject(ok);
	}
	
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		Welcome = (String) aInputStream.readObject();
		ok = (boolean) aInputStream.readObject();
	}

}
