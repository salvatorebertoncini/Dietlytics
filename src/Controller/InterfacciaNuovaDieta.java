package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

import client.*;
import model.*;
import comunicazione.*;

public class InterfacciaNuovaDieta {

	ObjectOutputStream versoServer;
	ObjectInputStream dalServer;
	Scene scene;

	@FXML
	ComboBox allergy;

	@FXML
	ComboBox diet;

	@FXML
	ComboBox lifestyle;

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

	// Pulsante "START NUOVA DIETA"
	@FXML
	public void startNewDiet(ActionEvent e) {
		boolean ok = true;
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		System.out.println("Data Attuale: " + date);
		String RegDataInizioDieta = date;
		String RegDataFineDieta = date;
		int dieta = 9, style = 9, all = 9;
		String RegDiet = (String) diet.getValue();
		if (RegDiet.equals(""))
			dieta = 9;
		switch (RegDiet) {
		case "Normale":
			dieta = 1;
			break;
		case "Vegetariana":
			dieta = 2;
			break;
		case "Vegana":
			dieta = 3;
			break;
		case "Iperproteica":
			dieta = 4;
			break;
		case "Dimagrante":
			dieta = 5;
			break;
		default:
			dieta = 9;
			break;
		}
		String RegLifestyle = (String) lifestyle.getValue();
		if (RegLifestyle.equals(""))
			style = 9;
		switch (RegLifestyle) {
		case "Sedentario":
			style = 0;
			break;
		case "Poco Movimento":
			style = 1;
			break;
		case "2-3 volte la settimana":
			style = 2;
			break;
		case "Quasi tutti i giorni":
			style = 3;
			break;
		case "Atleta Agonista":
			style = 4;
			break;
		default:
			style = 9;
			break;
		}
		String RegAllergy = (String) allergy.getValue();
		if (RegAllergy.equals(""))
			all = 9;
		switch (RegAllergy) {
		case "Nessuna Allergia/Intolleranza":
			all = 1;
			break;
		case "Glutine":
			all = 2;
			break;
		case "Nichel":
			all = 3;
			break;
		case "Diabete":
			all = 4;
			break;
		case "Lattosio":
			all = 5;
			break;
		default:
			all = 9;
			break;
		}

		try {

			MUser a = new MUser();
			MDiet md = new MDiet(style, dieta, RegDataInizioDieta, all);
			Richiesta richiesta = new Richiesta(TipiRichieste.NuovaDieta, md);

			versoServer.writeObject(richiesta);
			Risposta risposta = (Risposta) dalServer.readObject();

			boolean verifica = (boolean) risposta.Oggetto;

			System.out.println(all + " " + dieta + " " + style);

			if ((TipiRisposte.RichiestaNuovaDietaSuccesso==risposta.Tipo)&&verifica) {

				System.out.println("NUOVA DIETA SELEZIONATA CON SUCCESSO");
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
			} else
				System.out.println("ERRORE INSERIMENTO NUOVA DIETA");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}



	public void initializePage(ObjectOutputStream versoServer, ObjectInputStream dalServer) {
		this.dalServer=dalServer;
		this.versoServer=versoServer;

	}

}
