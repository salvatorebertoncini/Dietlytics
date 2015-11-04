package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.Client;
import Dietlytics.Dietlytics;
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

public class InterfacciaModificaDatiPersonali implements Initializable {
	
	@FXML
	TextField name;
	
	@FXML
	TextField surname;
	
	@FXML
	TextField email;
	
	@FXML
	TextField log;
	
	@FXML
	PasswordField pass;
	
	@FXML
	TextField DDbirthday;
	
	@FXML
	TextField MMbirthday;
	
	@FXML
	TextField YYYYbirthday;
	
	@FXML
	TextField height;
	
	@FXML
	TextField weight;
	
	@FXML
	ComboBox sex;
	
	@FXML
	Text message;
	
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
	
	//AGGIORNAMENTO CAMPI
		@FXML
		public void Init(){
			message.setVisible(false);
			message.setText("");
			StringBuilder RegNome = new StringBuilder("");
			StringBuilder RegCognome = new StringBuilder("");
			StringBuilder RegUsername = new StringBuilder("");
			StringBuilder RegPassword = new StringBuilder("");
			StringBuilder RegEmail = new StringBuilder("");
			StringBuilder RegDataDiNascita = new StringBuilder("");
			StringBuilder RegWeight = new StringBuilder("");
			StringBuilder RegHeight = new StringBuilder("");
			StringBuilder RegSex= new StringBuilder("");
			if(Client.requestField(Dietlytics.user.getId(), RegNome, RegCognome, RegUsername, RegPassword, RegEmail, RegDataDiNascita, RegSex, RegHeight, RegWeight))
			{
				name.setText(RegNome.toString());
				surname.setText(RegCognome.toString());
				log.setText(RegUsername.toString());
				pass.setText(RegPassword.toString());
				email.setText(RegEmail.toString());
				height.setText(RegHeight.toString());
				weight.setText(RegWeight.toString());
				System.out.println("prova sex Interfaccia: "+RegSex);
				if(RegSex.toString().equals("1")) 
					sex.setValue((String)"M");
				else 
					sex.setValue((String)"F");
				String split = RegDataDiNascita.toString();
				System.out.println(split);
				String[] items = split.split("-");
				YYYYbirthday.setText(items[0]);
				MMbirthday.setText(items[1]);
				DDbirthday.setText(items[2]);
			}
			
		}
	
	//Pulsante "MODIFICA DATI INIZIALI"
	@FXML
	public void Change(ActionEvent event){
		
		Boolean ok=true;
		
		String RegNome = name.getText();
		String RegCognome = surname.getText();
		String RegUsername = log.getText();
		String RegPassword = pass.getText();
		String RegEmail = email.getText();
		String RegDataDiNascita = YYYYbirthday.getText()+"-"+MMbirthday.getText()+"-"+DDbirthday.getText();
		int RegSex;
		if (sex.getValue().equals("M")) RegSex = 1; else RegSex = 2; 
		System.out.println("SESSO: "+(String) sex.getValue()+" "+RegSex);
		String RegWeight = weight.getText();
		String RegHeight = height.getText();
		Dietlytics.user.setWelcome(RegNome);
		
		if(Integer.parseInt(YYYYbirthday.getText())<1900||Integer.parseInt(YYYYbirthday.getText())>2015){
			System.out.println("ANNO DI NASCITA ERRATO, RIDIGITA");
			message.setVisible(true);
			message.setText("ANNO DI NASCITA ERRATO, RIDIGITA");
			ok=false;
		}
		if(Integer.parseInt(MMbirthday.getText())<1||Integer.parseInt(MMbirthday.getText())>12){
			System.out.println("MESE DI NASCITA ERRATO, RIDIGITA");
			message.setVisible(true);
			message.setText("MESE DI NASCITA ERRATO, RIDIGITA");
			ok=false;
		}
		if(Integer.parseInt(DDbirthday.getText())<1||Integer.parseInt(DDbirthday.getText())>31){
			System.out.println("GIORNO DI NASCITA ERRATO, RIDIGITA");
			message.setVisible(true);
			message.setText("GIORNO DI NASCITA ERRATO, RIDIGITA");
			ok=false;
		}
		
		if(ok!=false && Client.requestChange(Dietlytics.user.getId(), RegNome, RegCognome, RegUsername, RegPassword, RegEmail, RegDataDiNascita, RegSex, RegWeight, RegHeight)){
			Dietlytics.user.setWelcome(RegNome);
			System.out.println("DATI MODIFICATI CON SUCCESSO!!");
			message.setVisible(true);
			message.setText("DATI MODIFICATI CON SUCCESSO!!");
		}
	}
	
	//INIZIALIZZAZIONE
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Init();
	}

}
