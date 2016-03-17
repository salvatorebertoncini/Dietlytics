package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

import comunicazione.*;
import client.*;
import model.*;

public class InterfacciaEliminaUtente {
	ObjectOutputStream versoServer;
	ObjectInputStream dalServer;
	Scene scene;
	
	// Immagine "BACK"
	@FXML
	public void backhome(MouseEvent Event) {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/menu.fxml"));
			scene = new Scene(loader.load());
			Client.Stage.setScene(scene);
			Client.Stage.show();
			InterfacciaMenu controller = loader.<InterfacciaMenu>getController();
			controller.initializePage(versoServer,dalServer);
		} catch(Exception exc) {
			System.out.println("Errore-InitialieDefaultCartController: " + exc.getMessage());
			exc.printStackTrace();
		}
	}

	// Pulsante "ELIMINA UTENTE"
	@FXML
	public void removeUser(ActionEvent Event) {
		Richiesta eliminaUtente = new Richiesta(TipiRichieste.UtenteCancella, null);
		try {
			versoServer.writeObject(eliminaUtente);
			versoServer.flush();
			Risposta risposta = (Risposta) dalServer.readObject();
			if (risposta.Tipo==TipiRisposte.EliminaUtenteSuccesso) {
				System.out.println("UTENTE ELIMINATO CON SUCCESSO");
				try{
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/loginregistration.fxml"));
					scene = new Scene(loader.load());
					Client.Stage.setScene(scene);
					Client.Stage.show();
					InterfacciaIniziale controller = loader.<InterfacciaIniziale>getController();
					controller.initializePage(versoServer,dalServer);
				} catch(Exception exc) {
					System.out.println("Errore-InitialieDefaultCartController: " + exc.getMessage());
					exc.printStackTrace();
				}
			} else
				System.out.println("DELETE USER ERROR!");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void initializePage(ObjectOutputStream versoServer, ObjectInputStream dalServer) {
		this.dalServer=dalServer;
		this.versoServer=versoServer;

	}
}
