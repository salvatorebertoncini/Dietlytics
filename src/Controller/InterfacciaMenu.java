package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.*;
import client.*;
import comunicazione.*;

public class InterfacciaMenu{

	ObjectOutputStream versoServer;
	ObjectInputStream dalServer;	
	Scene scene;

	@FXML
	TextField DDNewDiet;

	@FXML
	TextField MMNewDiet;

	@FXML
	TextField YYYYNewDiet;

	@FXML
	Text welcometext;

	public void initializePage(ObjectOutputStream versoServer, ObjectInputStream dalServer) {
		this.dalServer=dalServer;
		this.versoServer=versoServer;
		try{
			Richiesta richiestaInizializzaWelcome = new Richiesta(TipiRichieste.InizializzaMenu, null);

			versoServer.writeObject(richiestaInizializzaWelcome);
			versoServer.flush();

			Risposta risposta = (Risposta) dalServer.readObject();
			if (risposta.Tipo==TipiRisposte.WelcomeSuccesso) 
				welcometext.setText("Benvenuto, " + ((MUtility) risposta.Oggetto).getWelcome() + "!");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	// LOGOUT
	@FXML
	public void logout() {
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
	}

	// SWITCH "Nuova Dieta"
	@FXML
	public void openNewDiet() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/newdiet.fxml"));
			scene = new Scene(loader.load());
			Client.Stage.setScene(scene);
			Client.Stage.show();
			InterfacciaNuovaDieta controller = loader.<InterfacciaNuovaDieta>getController();
			controller.initializePage(versoServer,dalServer);
		} catch(Exception exc) {
			System.out.println("Errore-InitialieDefaultCartController: " + exc.getMessage());
			exc.printStackTrace();
		}
	}

	// SWITCH "Storico"
	@FXML
	public void history() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/history.fxml"));
			scene = new Scene(loader.load());
			Client.Stage.setScene(scene);
			Client.Stage.show();
			InterfacciaStorico controller = loader.<InterfacciaStorico>getController();
			controller.initializePage(versoServer,dalServer);
		} catch(Exception exc) {
			System.out.println("Errore-InitialieDefaultCartController: " + exc.getMessage());
			exc.printStackTrace();
		}
	}

	// SWITCH "Ricerca Cibo"
	@FXML
	public void ricercacibo() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/findfood.fxml"));
			scene = new Scene(loader.load());
			Client.Stage.setScene(scene);
			Client.Stage.show();
			InterfacciaRicercaCibo controller = loader.<InterfacciaRicercaCibo>getController();
			controller.initializePage(versoServer,dalServer);
		} catch(Exception exc) {
			System.out.println("Errore-InitialieDefaultCartController: " + exc.getMessage());
			exc.printStackTrace();
		}
	}

	// SWITCH "Modifica dati iniziali"
	@FXML
	public void openChangePersonal() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/changepersonal.fxml"));
			scene = new Scene(loader.load());
			Client.Stage.setScene(scene);
			Client.Stage.show();
			InterfacciaModificaDatiPersonali controller = loader.<InterfacciaModificaDatiPersonali>getController();
			controller.initializePage(versoServer,dalServer);
		} catch(Exception exc) {
			System.out.println("Errore-InitialieDefaultCartController: " + exc.getMessage());
			exc.printStackTrace();
		}
	}

	// SWITCH "Modifica Abitudini"
	@FXML
	public void openChangeHabit() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/select.fxml"));
			scene = new Scene(loader.load());
			Client.Stage.setScene(scene);
			Client.Stage.show();
			InterfacciaModificaDatiDieta controller = loader.<InterfacciaModificaDatiDieta>getController();
			controller.initializePage(versoServer,dalServer);
		} catch(Exception exc) {
			System.out.println("Errore-InitialieDefaultCartController: " + exc.getMessage());
			exc.printStackTrace();
		}
	}

	// SWITCH "Elimina Utente"
	@FXML
	public void openDeleteUser() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/removeuser.fxml"));
			scene = new Scene(loader.load());
			Client.Stage.setScene(scene);
			Client.Stage.show();
			InterfacciaEliminaUtente controller = loader.<InterfacciaEliminaUtente>getController();
			controller.initializePage(versoServer,dalServer);
		} catch(Exception exc) {
			System.out.println("Errore-InitialieDefaultCartController: " + exc.getMessage());
			exc.printStackTrace();
		}
	}

	// SWITCH "About -> Info"
	@FXML
	public void openabout() {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/info.fxml"));
			scene = new Scene(loader.load());
			Client.Stage.setScene(scene);
			Client.Stage.show();
			InterfacciaInfo controller = loader.<InterfacciaInfo>getController();
			controller.initializePage(versoServer,dalServer);
		} catch(Exception exc) {
			System.out.println("Errore-InitialieDefaultCartController: " + exc.getMessage());
			exc.printStackTrace();
		}
	}

}
