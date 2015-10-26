package Controller;

import java.io.IOException;

import Client.Client;
import Dietlytics.Dietlytics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class InterfacciaRicercaCibo {
	
	@FXML
	TextField nomecercacibo;
	
	@FXML
	TextField grcercacibo;
	
	@FXML
	TextArea visualizzacibo;
	
	//BACK
	@FXML
	public void backhome(MouseEvent Event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
			Scene scene1 = new Scene(root);
        	Dietlytics.Stage.setScene(scene1);
        	Dietlytics.Stage.show();
        	System.out.println("BACK TO MENU HOMEPAGE");
		} catch (IOException ev) {
			ev.printStackTrace();
		}
	}
	
	//Pulsante "CERCA CIBO"
	public void findfood(ActionEvent event){
		visualizzacibo.clear();
		String nomecerca = nomecercacibo.getText();
		String newline = System.getProperty("line.separator");
		String grcerca = grcercacibo.getText();
		System.out.println("grcerca: "+grcerca); if(grcerca.equals("")||grcerca.equals(null)) grcerca="100";
		System.out.println(nomecerca+" "+grcerca);
		visualizzacibo.setText(Client.findFood(nomecerca.toUpperCase(), Integer.parseInt(grcerca)));
	}
	
}
