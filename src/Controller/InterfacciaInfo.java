package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class InterfacciaInfo {

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


	public void initializePage(ObjectOutputStream versoServer, ObjectInputStream dalServer) {
		this.dalServer=dalServer;
		this.versoServer=versoServer;

	}

}
