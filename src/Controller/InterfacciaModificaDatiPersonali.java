package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import client.*;
import model.*;
import comunicazione.*;
import dieta.*;

public class InterfacciaModificaDatiPersonali {

	ObjectOutputStream versoServer;
	ObjectInputStream dalServer;
	Scene scene;

	@FXML
	TextField name, surname, email, log, DDbirthday, MMbirthday, YYYYbirthday, height, weight;

	@FXML
	PasswordField pass;

	@FXML
	ComboBox sex;

	@FXML
	Text message;

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

	// Pulsante "MODIFICA DATI INIZIALI"
	@FXML
	public void Change(ActionEvent event) {

		Boolean ok = true;

		String RegNome = name.getText();
		String RegCognome = surname.getText();
		String RegUsername = log.getText();
		String RegPassword = pass.getText();
		String RegEmail = email.getText();
		String RegDataDiNascita = YYYYbirthday.getText() + "-" + MMbirthday.getText() + "-" + DDbirthday.getText();
		int RegSex;
		if (sex.getValue().equals("M"))
			RegSex = 1;
		else
			RegSex = 2;
		System.out.println("SESSO: " + (String) sex.getValue() + " " + RegSex);
		String RegWeight = weight.getText();
		String RegHeight = height.getText();
		
		MCredenziali utenteLoggato = new MCredenziali(RegNome, RegCognome, RegUsername, RegPassword, RegEmail,
				RegDataDiNascita, YYYYbirthday.getText(), MMbirthday.getText(), DDbirthday.getText(), RegSex, Integer.parseInt(RegHeight), Integer.parseInt(RegWeight));
		Richiesta richiesta = new Richiesta(TipiRichieste.UtenteModifica, utenteLoggato);

		try {
			versoServer.writeObject(richiesta);
			Risposta risposta = (Risposta) dalServer.readObject();

			if (risposta.Tipo==TipiRisposte.ModificaUtenteSuccesso) {
				System.out.println("DATI MODIFICATI CON SUCCESSO!!");
				message.setVisible(true);
				message.setText("DATI MODIFICATI CON SUCCESSO!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initializePage(ObjectOutputStream versoServer, ObjectInputStream dalServer) {
		this.dalServer=dalServer;
		this.versoServer=versoServer;
		message.setVisible(false);
		message.setText("");
		
		MUser ok;
		Richiesta richiesta = new Richiesta(TipiRichieste.InizializzaUtente, null);
		try {
			versoServer.writeObject(richiesta);
			Risposta risposta = (Risposta) dalServer.readObject();
			
			ok = (MUser) risposta.Oggetto;
			
			name.setText(ok.getName());
			surname.setText(ok.getSurname());
			log.setText(ok.getUsername());
			pass.setText(ok.getPassword());
			email.setText(ok.getEmail());
			height.setText("" + ok.getAltezza());
			weight.setText("" + ok.getPeso());
			if (ok.getSesso() == 1)
				sex.setValue((String) "M");
			else
				sex.setValue((String) "F");
			String split = ok.getNascita();
			System.out.println("test: " + split);
			String[] items = split.split("-");
			YYYYbirthday.setText(items[0]);
			MMbirthday.setText(items[1]);
			DDbirthday.setText(items[2]);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
