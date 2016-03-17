package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import javafx.scene.input.MouseEvent;


import client.*;
import model.*;
import comunicazione.*;
import dieta.*;


public class InterfacciaModificaDatiDieta{

	ObjectOutputStream versoServer;
	ObjectInputStream dalServer;
	Scene scene;

	@FXML
	ComboBox spun11, spun12, spun13, spun21, spun22, spun23,col1, col2, col3, cen1, cen2, cen3, pran1, pran2, pran3;

	ArrayList<MFood> elencoAllCibi;
	
	public void initializePage(ObjectOutputStream versoServer, ObjectInputStream dalServer) {
		this.dalServer=dalServer;
		this.versoServer=versoServer;
		
		ArrayList<MFood> elencoAbitudini = new ArrayList<MFood>();
		Richiesta richiesta = new Richiesta(TipiRichieste.AbitudiniUtente, null);
		try {
			versoServer.writeObject(richiesta);
			Risposta risposta = (Risposta) dalServer.readObject();

			elencoAllCibi = ((MUser) risposta.Oggetto).getElencoCibi();
			for (int k = 0; k < ((MUser) risposta.Oggetto).getElencoCibi().size(); k++) {
				spun11.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				spun12.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				spun13.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				spun21.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				spun22.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				spun23.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				col1.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				col2.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				col3.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				cen1.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				cen2.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				cen3.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				pran1.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				pran2.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
				pran3.getItems().addAll(((MUser) risposta.Oggetto).getElencoCibi().get(k).getName());
			}
			if(TipiRisposte.RichiestaAbitudiniSuccesso==risposta.Tipo)
			{
				
				for (int i = 0; i < 5; i++) {
	
					for (int j = 0; j < 3; j++) {
						String nomeProdotto = prendoProdottoDaId(((MUser) risposta.Oggetto).getAbitudini(i, j));
						MFood prodotto = new MFood(nomeProdotto);
						elencoAbitudini.add(prodotto);
					}
				}
				col1.setValue(elencoAbitudini.get(0).getName());
				col2.setValue(elencoAbitudini.get(1).getName());
				col3.setValue(elencoAbitudini.get(2).getName());
				spun11.setValue(elencoAbitudini.get(3).getName());
				spun12.setValue(elencoAbitudini.get(4).getName());
				spun13.setValue(elencoAbitudini.get(5).getName());
				pran1.setValue(elencoAbitudini.get(6).getName());
				pran2.setValue(elencoAbitudini.get(7).getName());
				pran3.setValue(elencoAbitudini.get(8).getName());
				spun21.setValue(elencoAbitudini.get(9).getName());
				spun22.setValue(elencoAbitudini.get(10).getName());
				spun23.setValue(elencoAbitudini.get(11).getName());
				cen1.setValue(elencoAbitudini.get(12).getName());
				cen2.setValue(elencoAbitudini.get(13).getName());
				cen3.setValue(elencoAbitudini.get(14).getName());
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
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

	// STAMPA MATRICE SU FILE TXT
	public void stampamatrix(int colazione1, int colazione2, int colazione3, int spuntino11, int spuntino12,
			int spuntino13, int pranzo1, int pranzo2, int pranzo3, int spuntino21, int spuntino22, int spuntino23,
			int cena1, int cena2, int cena3) {
		MUser utenteTemp = new MUser();
		utenteTemp.setAbitudini(0, 0, colazione1);
		utenteTemp.setAbitudini(0, 1, colazione2);
		utenteTemp.setAbitudini(0, 2, colazione3);
		utenteTemp.setAbitudini(1, 0, spuntino11);
		utenteTemp.setAbitudini(1, 1, spuntino12);
		utenteTemp.setAbitudini(1, 2, spuntino13);
		utenteTemp.setAbitudini(2, 0, pranzo1);
		utenteTemp.setAbitudini(2, 1, pranzo2);
		utenteTemp.setAbitudini(2, 2, pranzo3);
		utenteTemp.setAbitudini(3, 0, spuntino21);
		utenteTemp.setAbitudini(3, 1, spuntino22);
		utenteTemp.setAbitudini(3, 2, spuntino23);
		utenteTemp.setAbitudini(4, 0, cena1);
		utenteTemp.setAbitudini(4, 1, cena2);
		utenteTemp.setAbitudini(4, 2, cena3);
		
		Richiesta richiesta = new Richiesta(TipiRichieste.InformazioniUtenteLoggato, utenteTemp);
		try {
			versoServer.writeObject(richiesta);
			Risposta risposta = (Risposta) dalServer.readObject();
			System.out.println(" matrice modificata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String prendoProdottoDaId(int id) {
		String nomeProdotto = null;

		for (int z = 0; z < elencoAllCibi.size(); z++) {
			if (elencoAllCibi.get(z).getId() == id) {
				nomeProdotto = elencoAllCibi.get(z).getName();
			}
		}
		return nomeProdotto;
	}

	public int prendoIdDaProdotto(String nomeCibo) {
		int idCibo = 0;

		for (int z = 0; z < elencoAllCibi.size(); z++) {
			if (elencoAllCibi.get(z).getName().equals(nomeCibo)) {
				idCibo = elencoAllCibi.get(z).getId();
			}
		}
		return idCibo;
	}

	// Pulsante "SALVA ABITUDINI"
	@FXML
	public void savechangehabit(ActionEvent Event) {

		int aspun11 = prendoIdDaProdotto((String) spun11.getValue());
		int aspun12 = prendoIdDaProdotto((String) spun12.getValue());
		int aspun13 = prendoIdDaProdotto((String) spun13.getValue());
		int aspun21 = prendoIdDaProdotto((String) spun21.getValue());
		int aspun22 = prendoIdDaProdotto((String) spun22.getValue());
		int aspun23 = prendoIdDaProdotto((String) spun23.getValue());
		int acol1 = prendoIdDaProdotto((String) col1.getValue());
		int acol2 = prendoIdDaProdotto((String) col2.getValue());
		int acol3 = prendoIdDaProdotto((String) col3.getValue());
		int acen1 = prendoIdDaProdotto((String) cen1.getValue());
		int acen2 = prendoIdDaProdotto((String) cen2.getValue());
		int acen3 = prendoIdDaProdotto((String) cen3.getValue());
		int apran1 = prendoIdDaProdotto((String) pran1.getValue());
		int apran2 = prendoIdDaProdotto((String) pran2.getValue());
		int apran3 = prendoIdDaProdotto((String) pran3.getValue());

		stampamatrix(acol1, acol2, acol3, aspun11, aspun12, aspun13, apran1, apran2, apran3, aspun21, aspun22, aspun23,
				acen1, acen2, acen3);
	}
	
}
