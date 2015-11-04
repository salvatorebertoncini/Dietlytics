package Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.swing.text.Document;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

import Client.Client;
import Dietlytics.Dietlytics;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class InterfacciaStorico  implements Initializable {
	
	@FXML
	TextArea visualizzadieta;
	
	@FXML
	AnchorPane apsx;
	
	//Immagine "BACK"
	@FXML
	public void backhome(MouseEvent Event)
	{
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
			Scene scene1 = new Scene(root);
	    	Dietlytics.Stage.setScene(scene1);
	    	Dietlytics.Stage.show();
	    	System.out.println("BACK TO MENU HOMEPAGE");
		} 
		catch (IOException ev)
		{
			ev.printStackTrace();
		}
	}
		
	//CREAZIONE DINAMICA PANE E VISUALIZZAZIONE DIETA
	public void pane(){
		StringBuilder num = new StringBuilder(""), name = new StringBuilder(""), id = new StringBuilder("");
		int i,N; 
		System.out.println("INIZIALIZZAZIONE TITLED PANE E TEXTAREA");
		Client.initializeHistoryButton(num, name, id);
		N=Integer.parseInt(num.toString());
		String data[]= new String[N];
		Integer d[] = new Integer[N];
		
		String split = name.toString();
		String[] items = split.split("#");
		for(i=0;i<items.length;i++)
			data[i]=items[i];
		split = id.toString();
		items = split.split("#");
		for(i=0;i<items.length;i++)
			d[i]=Integer.parseInt(items[i]);
		//inverto i valori dell'array
		for(i = 0; i < data.length / 2; i++){
		    String temp = data[i];
		    data[i] = data[data.length - i - 1];
		    data[data.length - i - 1] = temp;
		}
		for(i = 0; i < d.length / 2; i++){
		    int temp = d[i];
		    d[i] = d[d.length - i - 1];
		    d[d.length - i - 1] = temp;
		}
		
		TitledPane[] tps = new TitledPane[N];
		
		int a=0;
		for (i=0; i<N; i++){
			tps[i] = new TitledPane(data[i], null);
			tps[i].setLayoutY(a);
			tps[i].setPrefWidth(240);
			tps[i].setId("TP"+i);
			String passdata=data[i];
			int passid=d[i];
			tps[i].setOnMouseClicked(new EventHandler<MouseEvent>() {

		        @Override
		        public void handle(MouseEvent t) {
		            String totdiet = Client.findDiet(passid);
		            System.out.println("Visualizza dieta: "+passdata+" con ID: "+passid);
		            visualizzadieta.setText(passdata+"\n"+totdiet);
		            Dietlytics.user.setDietxml(totdiet);
		        }
		    });
			a+=25;
		    apsx.getChildren().add(tps[i]);   
		}
		
	}
	
	public void PrintXML(){
		System.out.println("STAMPA DIETA DA BOTTONE...\n"+Dietlytics.user.getDietxml());
		
	}
	
	//INIZIALIZZAZIONE
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pane();
	}
	
}
