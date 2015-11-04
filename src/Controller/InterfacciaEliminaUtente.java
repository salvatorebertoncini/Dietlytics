package Controller;

import java.io.IOException;

import Client.Client;
import Dietlytics.Dietlytics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class InterfacciaEliminaUtente {
	
	static int id = Dietlytics.user.getId();
	
	//Immagine "BACK"
	@FXML
	public void backhome(MouseEvent Event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
			Scene scene1 = new Scene(root);
        	Dietlytics.Stage.setScene(scene1);
        	Dietlytics.Stage.show();
        	System.out.println("BACK TO MENU HOMEPAGE");
		} catch (IOException ev) {
			// TODO Auto-generated catch block
			ev.printStackTrace();
		}
	}
	
	//Pulsante "ELIMINA UTENTE"
	@FXML
	public void removeUser(ActionEvent Event){
		boolean ok = true;
		System.out.println(Dietlytics.user.getId());
		ok=Client.requestDeleteUser(Dietlytics.user.getId());
		if(ok){
			System.out.println("UTENTE ELIMINATO CON SUCCESSO");
			try {
				Parent root = FXMLLoader.load(getClass().getResource("../view/loginregistration.fxml"));
				Scene scene1 = new Scene(root);
	        	Dietlytics.Stage.setScene(scene1);
	        	Dietlytics.Stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}
		else System.out.println("DELETE USER ERROR!");
	}
}
