package Controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.text.Text;

public class InterfacciaIniziale implements Initializable {
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	Scene scene;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	int id= 1;
	
	@FXML
	Text loginerror, registrationerror, changeerror;
	
	@FXML
	TextField log, name, surname, email, username;;
	
	@FXML
	PasswordField pass, password;
	
	@FXML
	TextField DDbirthday, MMbirthday,YYYYbirthday, DDNewDiet, MMNewDiet, YYYYNewDiet, height, weight;
	
	@FXML
	ComboBox sex;
	
	//SVUOTAMENTO REGISTRAZIONE
	@FXML
	public void Clear(){
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
	
	//Pulsante "LOGIN"
	@FXML
	public void Login(ActionEvent event){
		String usera = log.getText();
		String passa = pass.getText();
		int id;
		if(Client.requestLogin(usera, passa))
			{
			System.out.println("Login effettuato con successo!! ID: "+Dietlytics.id);
			loginerror.setVisible(false);
			Clear();
			
			try {
				Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));
				Scene scene1 = new Scene(root);
	        	Dietlytics.Stage.setScene(scene1);
	        	Dietlytics.Stage.show();
			} catch (IOException e) {
				e.printStackTrace();
				}
			}
			else
			{
			System.out.println("ERRORE LOGIN");
			loginerror.setVisible(true);
			Clear();
			}
	}
	
	//Pulsante "REGISTRATI"
	@FXML
	public void Registration(ActionEvent event){
		Boolean ok=true;
		String RegNome = name.getText();
		String RegCognome = surname.getText();
		String RegEmail = email.getText();
		String RegDataDiNascita = YYYYbirthday.getText()+"-"+MMbirthday.getText()+"-"+DDbirthday.getText();
		String RegUsername = username.getText();
		String RegPassword = password.getText();
		String RegSex = (String)sex.getValue();
		int sexy=1;
		if(RegSex.equals("F")) sexy=2;
		String RegWeight = weight.getText();
		String RegHeight = height.getText();
		Dietlytics.welcome=RegNome;
		/////////////////////////////////////////////////////
		
		if(Integer.parseInt(YYYYbirthday.getText())<1900||Integer.parseInt(YYYYbirthday.getText())>2015){
			System.out.println("ANNO DI NASCITA ERRATO, RIDIGITA");
			registrationerror.setVisible(true);
			registrationerror.setText("ANNO DI NASCITA ERRATO, RIDIGITA");
			ok=false;
		}
		if(Integer.parseInt(MMbirthday.getText())<1||Integer.parseInt(MMbirthday.getText())>12){
			System.out.println("MESE DI NASCITA ERRATO, RIDIGITA");
			registrationerror.setVisible(true);
			registrationerror.setText("MESE DI NASCITA ERRATO, RIDIGITA");
			ok=false;
		}
		if(Integer.parseInt(DDbirthday.getText())<1||Integer.parseInt(DDbirthday.getText())>31){
			System.out.println("GIORNO DI NASCITA ERRATO, RIDIGITA");
			registrationerror.setVisible(true);
			registrationerror.setText("GIORNO DI NASCITA ERRATO, RIDIGITA");
			ok=false;
		}
		
		if(Debugger.DebugRegistration.UsernameCheck(RegUsername)){
			System.out.println("USERNAME GIà UTILIZZATO, INSERIRE USERNAME DIVERSO");
			registrationerror.setVisible(true);
			registrationerror.setText("USERNAME GIA' UTILIZZATO, INSERIRE USERNAME DIVERSO");
			ok=false;
		}
		
		if(Debugger.DebugRegistration.EmailCheck(RegEmail)){
			System.out.println("EMAIL GIà UTILIZZATA, INSERIRE EMAIL DIVERSA");
			registrationerror.setVisible(true);
			registrationerror.setText("EMAIL GIA' UTILIZZATA, INSERIRE EMAIL DIVERSA");
			ok=false;
		}
		
		if(Debugger.DebugRegistration.EmailOk(RegEmail)){
			System.out.println("EMAIL NON VALIDA, INSERIRE EMAIL VALIDA");
			registrationerror.setVisible(true);
			registrationerror.setText("EMAIL NON VALIDA, INSERIRE EMAIL VALIDA");
			ok=false;
		}
		
		if(ok!=false && Client.requestRegistration(RegNome, RegCognome, RegUsername, RegPassword, RegEmail, RegDataDiNascita, sexy, RegHeight, RegWeight)){
			System.out.println("REGISTRAZIONE EFFETTUATA CON SUCCESSO!!");
			registrationerror.setVisible(false);
			Clear();
			try {
				Parent root = FXMLLoader.load(getClass().getResource("../view/select.fxml"));
				Scene scene1 = new Scene(root);
	        	Dietlytics.Stage.setScene(scene1);
	        	Dietlytics.Stage.show();
			} catch (IOException e) {
				e.printStackTrace();
				}
			
		}
		
		
	}
	
}
