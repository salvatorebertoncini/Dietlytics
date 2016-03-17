package controller;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import model.*;
import client.*;
import comunicazione.*;


public class InterfacciaStorico{

	ObjectOutputStream versoServer;
	ObjectInputStream dalServer;	
	Scene scene;

	public String totdiet;

	@FXML
	TextArea visualizzadieta;

	@FXML
	AnchorPane apsx;

	@FXML
	Button stampa;

	int iddietaxml, iduserxml;
	String nomedietaxml;

	// INIZIALIZZAZIONE
	public void initializePage(ObjectOutputStream versoServer, ObjectInputStream dalServer) {
		this.dalServer=dalServer;
		this.versoServer=versoServer;
		stampa.setVisible(false);
		pane();
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

	// CREAZIONE DINAMICA PANE E VISUALIZZAZIONE DIETA
	public void pane() {
		StringBuilder num = new StringBuilder("");
		int i, N;
		System.out.println("INIZIALIZZAZIONE TITLED PANE E TEXTAREA");

		Richiesta richiesta = new Richiesta(TipiRichieste.InizializzaStorico, null);
		try {

			versoServer.writeObject(richiesta);
			Risposta risposta = (Risposta) dalServer.readObject();

			if (TipiRisposte.RichiestaInizializzaTpDietaSuccesso==risposta.Tipo) {

				ArrayList<MDiet> elencoDieta = ((ArrayList<MDiet>) risposta.Oggetto);
				
				int numeroDiete = elencoDieta.size();
				System.out.println("numero elementi presenti nella lista della dieta " +numeroDiete);
				TitledPane[] tps = new TitledPane[numeroDiete];

				int a = 0;
				for (i = 0; i < numeroDiete; i++) {
					tps[i] = new TitledPane(elencoDieta.get(i).getDieta(), null);
					tps[i].setLayoutY(a);
					tps[i].setPrefWidth(240);
					tps[i].setId("TP" + i);
					String passdata = elencoDieta.get(i).getDieta();
					int passid = elencoDieta.get(i).getId();
					tps[i].setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent t) {

							Richiesta richiesta = new Richiesta(TipiRichieste.StampaDieta, passid);
							
							try {

								versoServer.writeObject(richiesta);
								Risposta risposta = (Risposta) dalServer.readObject();

								totdiet = ((String) risposta.Oggetto);
								System.out.println("Visualizza dieta: " + passdata + " con ID: " + passid);
								visualizzadieta.setText(passdata + "\n" + totdiet);
								stampa.setVisible(true);
								stampa.setText("Stampa Dieta: " + passdata);
								iddietaxml = passid;
								nomedietaxml = passdata;
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
					});
					a += 25;
					apsx.getChildren().add(tps[i]);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void print() throws TransformerException {
		System.out.println("STAMPA DIETA " + iddietaxml + " DA BOTTONE...\n");
		stampaXML();
	}

	public void stampaXML() throws TransformerException {

		String dietaPer = totdiet.substring(0, totdiet.indexOf("COLAZIONE"));
		String colazione = totdiet.substring(totdiet.indexOf("COLAZIONE"), totdiet.indexOf("PRIMOSPUNTINO"));
		String primoSpuntino = totdiet.substring(totdiet.indexOf("PRIMOSPUNTINO"), totdiet.indexOf("PRANZO"));
		String pranzo = totdiet.substring(totdiet.indexOf("PRANZO"), totdiet.indexOf("SECONDOSPUNTINO"));
		String secondoSpuntino = totdiet.substring(totdiet.indexOf("SECONDOSPUNTINO"), totdiet.indexOf("CENA"));
		String cena = totdiet.substring(totdiet.indexOf("CENA"), totdiet.length() - 1);

		System.out.println(dietaPer);
		System.out.println(colazione);
		Document document = creaDocumentoXml();

		Element item1 = document.createElement("Cliente");
		item1.setAttribute("nome", dietaPer);

		Element item2 = document.createElement("colazione");
		item2.setAttribute("colazione", colazione);

		Element item3 = document.createElement("primoSpuntino");
		item3.setAttribute("primoSpuntino", primoSpuntino);

		Element item4 = document.createElement("pranzo");
		item4.setAttribute("pranzo", pranzo);

		Element item5 = document.createElement("secondoSpuntino");
		item5.setAttribute("secondoSpuntino", secondoSpuntino);

		Element item6 = document.createElement("cena");
		item6.setAttribute("cena", cena);

		document.getFirstChild().appendChild(item1);
		document.getFirstChild().appendChild(item2);
		document.getFirstChild().appendChild(item3);
		document.getFirstChild().appendChild(item4);
		document.getFirstChild().appendChild(item5);
		document.getFirstChild().appendChild(item6);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(document);

		StreamResult result = new StreamResult(new File("dieteXML/dieta_id" + iddietaxml+ "_nome" + nomedietaxml +".xml"));
		transformer.transform(source, result);

	}

	public Document creaDocumentoXml() {
		org.w3c.dom.Document document = null;

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();

			Element root = document.createElement("root");
			document.appendChild(root);
		} catch (Exception exc) {
			System.out.println("Errore InitializeDocument: " + exc.getMessage());
			exc.printStackTrace();
		}

		return (Document) document;
	}

}
