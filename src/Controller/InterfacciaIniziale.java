package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.scene.text.Text;

import comunicazione.*;
import client.*;
import model.*;

public class InterfacciaIniziale implements Initializable {

	ObjectOutputStream versoServer;
	ObjectInputStream dalServer;
	Scene scene;

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	Text loginerror, registrationerror, changeerror;

	@FXML
	TextField log, name, surname, email, username;

	@FXML
	PasswordField pass, password;

	@FXML
	TextField DDbirthday, MMbirthday, YYYYbirthday, DDNewDiet, MMNewDiet, YYYYNewDiet, height, weight;

	@FXML
	ComboBox sex;

	// SVUOTAMENTO REGISTRAZIONE
	@FXML
	public void Clear() {
		log.clear();
		pass.clear();
		name.clear();
		surname.clear();
		email.clear();
		username.clear();
		password.clear();
		DDbirthday.clear();
		MMbirthday.clear();
		YYYYbirthday.clear();
		height.clear();
		weight.clear();
	}

	// Pulsante "LOGIN"
	@FXML
	public void Login(ActionEvent event) {

		MCredenziali mc = new MCredenziali(log.getText(), pass.getText());

		Richiesta requestLogin = new Richiesta(TipiRichieste.Login, mc);
		try {
			versoServer.writeObject(requestLogin);
			versoServer.flush();

			Risposta risposta = (Risposta) dalServer.readObject();
			if (risposta.Tipo==TipiRisposte.LoginSuccesso) {
				loginerror.setVisible(false);
				Clear();
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
			} else {
				System.out.println("ERRORE LOGIN");
				loginerror.setVisible(true);
				Clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Pulsante "REGISTRATI"
	@FXML
	public void Registration(ActionEvent event) {
		String RegNome = name.getText();
		String RegCognome = surname.getText();
		String RegEmail = email.getText();
		String RegDd = DDbirthday.getText();
		String RegMm = MMbirthday.getText();
		String RegAaaa = YYYYbirthday.getText();
		String RegDataDiNascita = RegAaaa + "-" + RegMm + "-" + RegDd;
		String RegUsername = username.getText();
		String RegPassword = password.getText();
		String RegSex = (String) sex.getValue();
		int sexy = 1;
		if (RegSex.equals("F"))
			sexy = 2;
		String RegWeight = weight.getText();
		String RegHeight = height.getText();

		MCredenziali utente = new MCredenziali(RegNome, RegCognome, RegUsername, RegPassword, RegEmail,
				RegDataDiNascita, RegAaaa, RegMm, RegDd, sexy, Integer.parseInt(RegHeight), Integer.parseInt(RegWeight));

		Richiesta requestRegistration = new Richiesta(TipiRichieste.Registrazione, utente);
		try {
			versoServer.writeObject(requestRegistration);
			Risposta risposta = (Risposta) dalServer.readObject();

			if (risposta.Tipo==TipiRisposte.RegistrazioneFallimento) {
				String messaggio = ((MUtility) risposta.getOggetto()).getWelcome();
				System.out.println(messaggio);
				registrationerror.setVisible(true);
				registrationerror.setText(messaggio);
			} else {
				String messaggio = ((MUtility) risposta.getOggetto()).getWelcome();
				System.out.println(messaggio);
				registrationerror.setVisible(false);
				Clear();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initializePage(ObjectOutputStream versoServer, ObjectInputStream dalServer) {
		this.dalServer=dalServer;
		this.versoServer=versoServer;

	}
}
