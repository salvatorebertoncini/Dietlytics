package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import comunicazione.*;
import client.*;
import model.*;

public class InterfacciaRicercaCibo {

	ObjectOutputStream versoServer;
	ObjectInputStream dalServer;
	Scene scene;	

	@FXML
	TextField nomecercacibo;

	@FXML
	TextField grcercacibo;

	@FXML
	TextArea visualizzacibo;

	public void initializePage(ObjectOutputStream versoServer, ObjectInputStream dalServer) {
		this.dalServer=dalServer;
		this.versoServer=versoServer;
	}

	// BACK
	@FXML
	public void backhome() {
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

	// Pulsante "CERCA CIBO"
	public void findfood(ActionEvent event) {
		visualizzacibo.clear();
		String nomecerca = nomecercacibo.getText();
		String newline = System.getProperty("line.separator");
		String grcerca = grcercacibo.getText();
		System.out.println("grcerca: " + grcerca);
		if (grcerca.equals("") || grcerca.equals(null))
			grcerca = "100";
		System.out.println(nomecerca + " " + grcerca);

		MDiet ricerca = new MDiet(nomecerca, Integer.parseInt(grcerca));

		Richiesta richiesta = new Richiesta(TipiRichieste.RicercaCibo, ricerca);
		try {
			versoServer.writeObject(richiesta);
			versoServer.flush();

			Risposta risposta = (Risposta) dalServer.readObject();
			if (risposta.Tipo==TipiRisposte.RicercaCiboSuccesso)
				visualizzacibo.setText(((MUtility)risposta.Oggetto).getWelcome());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
