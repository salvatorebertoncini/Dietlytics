package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Dietlytics.Dietlytics;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class InterfacciaMenu implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		welcometext.setText("Benvenuto, "+Dietlytics.user.getWelcome()+"!");
		
	}
	
	@FXML
	TextField DDNewDiet;
	
	@FXML
	TextField MMNewDiet;
	
	@FXML
	TextField YYYYNewDiet;
	
	@FXML
	Text welcometext;
	
	//LOGOUT
	@FXML
	public void logout(){
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/loginregistration.fxml"));
			Scene scene1 = new Scene(root);
        	Dietlytics.Stage.setScene(scene1);
        	Dietlytics.Stage.show();
        	System.out.println("LOGOUT EFFETTUATO");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	//SWITCH "Nuova Dieta"
	@FXML
	public void openNewDiet(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/newdiet.fxml"));
			Scene scene1 = new Scene(root);
        	Dietlytics.Stage.setScene(scene1);
        	Dietlytics.Stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	//SWITCH "Storico"
	@FXML
	public void history(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/history.fxml"));
			Scene scene1 = new Scene(root);
        	Dietlytics.Stage.setScene(scene1);
        	Dietlytics.Stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	//SWITCH "Ricerca Cibo"
	@FXML
	public void ricercacibo(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/findfood.fxml"));
			Scene scene1 = new Scene(root);
        	Dietlytics.Stage.setScene(scene1);
        	Dietlytics.Stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	//SWITCH "Modifica dati iniziali"
	@FXML
	public void openChangePersonal(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/changepersonal.fxml"));
			Scene scene1 = new Scene(root);
        	Dietlytics.Stage.setScene(scene1);
        	Dietlytics.Stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	//SWITCH "Modifica Abitudini"
	@FXML
	public void openChangeHabit(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/select.fxml"));
			Scene scene1 = new Scene(root);
        	Dietlytics.Stage.setScene(scene1);
        	Dietlytics.Stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	//SWITCH "Elimina Utente"
	@FXML
	public void openDeleteUser(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/removeuser.fxml"));
			Scene scene1 = new Scene(root);
        	Dietlytics.Stage.setScene(scene1);
        	Dietlytics.Stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	//SWITCH "About -> Info"
	@FXML
	public void openabout(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/info.fxml"));
			Scene scene1 = new Scene(root);
        	Dietlytics.Stage.setScene(scene1);
        	Dietlytics.Stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}

}
