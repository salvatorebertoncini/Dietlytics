package comunicazione;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Risposta implements Serializable{
	public int Tipo;
	public Object Oggetto;
	
	public Risposta(int pTipo, Object pOggetto)
	{
		this.Tipo = pTipo;
		this.Oggetto = pOggetto;
	}

	public int getTipo() {
		return Tipo;
	}

	public void setTipo(int tipo) {
		Tipo = tipo;
	}

	public Object getOggetto() {
		return Oggetto;
	}

	public void setOggetto(Object oggetto) {
		Oggetto = oggetto;
	}
	
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.writeObject(Tipo);
		aOutputStream.writeObject(Oggetto);
	}
	
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		Tipo = (int) aInputStream.readObject();
		Oggetto = (Object) aInputStream.readObject();
	}
}
