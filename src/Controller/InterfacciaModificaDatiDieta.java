package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.Client;
import Dietlytics.Algorithm;
import Dietlytics.Dietlytics;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class InterfacciaModificaDatiDieta implements Initializable{
	
	@FXML
	ComboBox spun11, spun12, spun13, spun21, spun22, spun23;
	
	@FXML
	ComboBox col1, col2, col3, cen1, cen2, cen3, pran1, pran2, pran3;
	
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
	
	//LEGGI ABITUDINI
	public void leggimatrix(){
		
	}
	
	
	//STAMPA MATRICE SU FILE TXT
	public void stampamatrix(int colazione1, int colazione2, int colazione3, int spuntino11, int spuntino12, int spuntino13, int pranzo1, int pranzo2, int pranzo3, int spuntino21, int spuntino22, int spuntino23, int cena1, int cena2, int cena3){
		PrintWriter writer;
		try {
			writer = new PrintWriter("matrici/"+Dietlytics.user.getId()+".txt", "UTF-8");
			writer.println(colazione1+" "+colazione2+" "+colazione3);
			writer.println(spuntino11+" "+spuntino12+" "+spuntino13);
			writer.println(pranzo1+" "+pranzo2+" "+pranzo3);
			writer.println(spuntino21+" "+spuntino22+" "+spuntino23);
			writer.println(cena1+" "+cena2+" "+cena3);
			writer.close();
			System.out.println("stampa matrice avvenuta");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Pulsante "SALVA ABITUDINI"
	@FXML
	public void savechangehabit(ActionEvent Event){
		boolean ok = true;
		int colazione1, colazione2, colazione3, spuntino11, spuntino12, spuntino13, spuntino21, spuntino22, spuntino23, pranzo1, pranzo2, pranzo3, cena1, cena2, cena3;
		
		
		//ACQUISISCI NUOVE ABITUDINI
		switch ((String)col1.getValue()) {
	        case "Acqua":  colazione1 = 1; break;
	        case "Latte":  colazione1 = 2; break;
	        case "Caffè":  colazione1 = 3; break;
	        case "Cappuccino":  colazione1 = 4; break;
	        case "Tè":  colazione1 = 5; break;
	        case "Spremuta/Succo":  colazione1 = 6; break;
	        default: colazione1= 0; break;
		}		
		switch ((String)col2.getValue()) {
	        case "Cereali":  colazione2 = 1; break;
	        case "Frumento/Avena":  colazione2 = 2; break;
	        case "Brioches":  colazione2 = 3; break;
	        case "Cornetto/Sfoglia":  colazione2 = 4; break;
	        case "Torta/Crostata":  colazione2 = 5; break;
	        default: colazione2= 0; break;
		}
		switch ((String)col3.getValue()) {
	        case "Cereali":  colazione3 = 1; break;
	        case "Frumento/Avena":  colazione3 = 2; break;
	        case "Brioches":  colazione3 = 3; break;
	        case "Cornetto/Sfoglia":  colazione3 = 4; break;
	        case "Torta/Crostata":  colazione3 = 5; break;
	        default: colazione3= 0; break;
		}
		switch ((String)spun11.getValue()) {
	        case "Acqua":  spuntino11 = 1; break;
	        case "Coca-Cola":  spuntino11 = 2; break;
	        case "Vino":  spuntino11 = 3; break;
	        case "Birra":  spuntino11 = 4; break;
	        case "Aranciata":  spuntino11 = 5; break;
	        case "Sprite":  spuntino11 = 6; break;
	        case "Spremuta/Succo":  spuntino11 = 7; break;
	        default: spuntino11= 0; break;
		}
		switch ((String)spun12.getValue()) {
	        case "Crackers":  spuntino12 = 1; break;
	        case "Tramezzino":  spuntino12 = 2; break;
	        case "Biscotti/grissini":  spuntino12 = 3; break;
	        case "Merendina":  spuntino12 = 4; break;
	        case "Yogurt":  spuntino12 = 5; break;
	        case "Frutta":  spuntino12 = 6; break;
	        default: spuntino12= 0; break;
		}
		switch ((String)spun13.getValue()) {
	        case "Crackers":  spuntino13 = 1; break;
	        case "Tramezzino":  spuntino13 = 2; break;
	        case "Biscotti/grissini":  spuntino13 = 3; break;
	        case "Merendina":  spuntino13 = 4; break;
	        case "Yogurt":  spuntino13 = 5; break;
	        case "Frutta":  spuntino13 = 6; break;
	        default: spuntino13= 0; break;
		}
		switch ((String)pran1.getValue()) {
	        case "Acqua":  pranzo1 = 1; break;
	        case "Coca-Cola":  pranzo1 = 2; break;
	        case "Vino":  pranzo1 = 3; break;
	        case "Birra":  pranzo1 = 4; break;
	        case "Aranciata":  pranzo1 = 5; break;
	        case "Sprite":  pranzo1 = 6; break;
	        case "Spremuta/Succo":  pranzo1 = 7; break;
	        default: pranzo1= 0; break;
		}
		switch ((String)pran2.getValue()) {
	        case "Primo":  pranzo2 = 1; break;
	        case "Secondo":  pranzo2 = 2; break;
	        case "Insalata":  pranzo2 = 3; break;
	        case "Contorno":  pranzo2 = 4; break;
	        case "Panino":  pranzo2 = 5; break;
	        default: pranzo2= 0; break;
		}
		switch ((String)pran3.getValue()) {
	        case "Primo":  pranzo3 = 1; break;
	        case "Secondo":  pranzo3 = 2; break;
	        case "Insalata":  pranzo3 = 3; break;
	        case "Contorno":  pranzo3 = 4; break;
	        case "Panino":  pranzo3 = 5; break;
	        default: pranzo3 = 0; break;
		}
		switch ((String)spun21.getValue()) {
	        case "Acqua":  spuntino21 = 1; break;
	        case "Coca-Cola":  spuntino21 = 2; break;
	        case "Vino":  spuntino21 = 3; break;
	        case "Birra":  spuntino21 = 4; break;
	        case "Aranciata":  spuntino21 = 5; break;
	        case "Sprite":  spuntino21 = 6; break;
	        case "Spremuta/Succo":  spuntino21 = 7; break;
	        default: spuntino21= 0; break;
		}
		switch ((String)spun22.getValue()) {
	        case "Panino":  spuntino22 = 1; break;
	        case "Torta/crostata":  spuntino22 = 2; break;
	        case "Cracker":  spuntino22 = 3; break;
	        case "Frutta/frullato":  spuntino22 = 4; break;
	        default: spuntino22 = 0; break;
		}
		switch ((String)spun23.getValue()) {
	        case "Panino":  spuntino23 = 1; break;
	        case "Torta/crostata":  spuntino23 = 2; break;
	        case "Cracker":  spuntino23 = 3; break;
	        case "Frutta/frullato":  spuntino23 = 4; break;
	        default: spuntino23 = 0; break;
		}
		switch ((String)cen1.getValue()) {
	        case "Acqua":  cena1 = 1; break;
	        case "Coca-Cola":  cena1 = 2; break;
	        case "Vino":  cena1 = 3; break;
	        case "Birra":  cena1 = 4; break;
	        case "Aranciata":  cena1 = 5; break;
	        case "Sprite":  cena1 = 6; break;
	        case "Spremuta/Succo":  cena1 = 7; break;
	        default: cena1= 0; break;
		}
		switch ((String)cen2.getValue()) {
	        case "Primo":  cena2 = 1; break;
	        case "Secondo":  cena2 = 2; break;
	        case "Insalata":  cena2 = 3; break;
	        case "Contorno":  cena2 = 4; break;
	        case "Panino":  cena2 = 5; break;
	        default: cena2 = 0; break;
		}
		switch ((String)cen3.getValue()) {
	        case "Primo":  cena3 = 1; break;
	        case "Secondo":  cena3 = 2; break;
	        case "Insalata":  cena3 = 3; break;
	        case "Contorno":  cena3 = 4; break;
	        case "Panino":  cena3 = 5; break;
	        default: cena3 = 0; break;
		}
		

		System.out.println(colazione1+""+colazione2+""+colazione3);
		System.out.println(spuntino11+""+spuntino12+""+spuntino13);
		System.out.println(pranzo1+""+pranzo2+""+pranzo3);
		System.out.println(spuntino21+""+spuntino22+""+spuntino23);
		System.out.println(cena1+""+cena2+""+cena3);
		
		//ok = Client.cambioabitudini();
		try {
			//INSERISCE LA MATRICE 5X3 DENTRO UN FILE DI TESTO CHE HA PER NOME NUMERO_ID.TXT
			stampamatrix(colazione1, colazione2, colazione3, spuntino11, spuntino12, spuntino13, pranzo1, pranzo2, pranzo3, spuntino21, spuntino22, spuntino23, cena1, cena2, cena3); 
			//LEGGE I VALORI E LI INSERISCE DENTRO UNA MATRICE MOMENTANEA, DENTRO DIETLYTICS
			//Algorithm.readmatrix();
			Parent root = FXMLLoader.load(getClass().getResource("../view/newdiet.fxml"));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(Client.checkLife(Dietlytics.user.getId())<8){
			
			Algorithm.readmatrix();
			
			switch (Dietlytics.user.getAbitudini(0, 0)) {
		        case 1: col1.setValue("Acqua"); break;
		        case 2: col1.setValue("Latte"); break;
		        case 3: col1.setValue("Caffè"); break;
		        case 4: col1.setValue("Cappuccino"); break;
		        case 5: col1.setValue("Tè"); break;
		        case 6: col1.setValue("Spremuta/Succo"); break;
			}		
			switch (Dietlytics.user.getAbitudini(0, 1)) {
		        case 1: col2.setValue("Cereali"); break;
		        case 2: col2.setValue("Frumento/Avena"); break;
		        case 3: col2.setValue("Brioches"); break;
		        case 4: col2.setValue("Cornetto/Sfoglia"); break;
		        case 5: col2.setValue("Torta/Crostata"); break;
			}
			switch (Dietlytics.user.getAbitudini(0, 2)) {
		        case 1: col3.setValue("Cereali"); break;
		        case 2: col3.setValue("Frumento/Avena"); break;
		        case 3: col3.setValue("Brioches"); break;
		        case 4: col3.setValue("Cornetto/Sfoglia"); break;
		        case 5: col3.setValue("Torta/Crostata"); break;
			}
			switch (Dietlytics.user.getAbitudini(1, 0)) {
		        case 1: spun11.setValue("Acqua"); break;
		        case 2: spun11.setValue("Coca-Cola"); break;
		        case 3: spun11.setValue("Vino"); break;
		        case 4: spun11.setValue("Birra"); break;
		        case 5: spun11.setValue("Aranciata"); break;
		        case 6: spun11.setValue("Sprite"); break;
		        case 7: spun11.setValue("Spremuta/Succo"); break;
			}
			switch (Dietlytics.user.getAbitudini(1,1)) {
		        case 1: spun12.setValue("Crackers"); break;
		        case 2: spun12.setValue("Tramezzino"); break;
		        case 3: spun12.setValue("Biscotti/grissini"); break;
		        case 4: spun12.setValue("Merendina"); break;
		        case 5: spun12.setValue("Yogurt"); break;
		        case 6: spun12.setValue("Frutta"); break;
			}
			switch (Dietlytics.user.getAbitudini(1,2)) {
		        case 1: spun13.setValue("Crackers"); break;
		        case 2: spun13.setValue("Tramezzino"); break;
		        case 3: spun13.setValue("Biscotti/grissini"); break;
		        case 4: spun13.setValue("Merendina"); break;
		        case 5: spun13.setValue("Yogurt"); break;
		        case 6: spun13.setValue("Frutta"); break;
			}
			switch (Dietlytics.user.getAbitudini(2, 0)) {
		        case 1: pran1.setValue("Acqua"); break;
		        case 2: pran1.setValue("Coca-Cola"); break;
		        case 3: pran1.setValue("Vino"); break;
		        case 4: pran1.setValue("Birra"); break;
		        case 5: pran1.setValue("Aranciata"); break;
		        case 6: pran1.setValue("Sprite"); break;
		        case 7: pran1.setValue("Spremuta/Succo"); break;
			}
			switch (Dietlytics.user.getAbitudini(2,1)) {
		        case 1: pran2.setValue("Primo"); break;
		        case 2: pran2.setValue("Secondo"); break;
		        case 3: pran2.setValue("Insalata"); break;
		        case 4: pran2.setValue("Contorno"); break;
		        case 5: pran2.setValue("Panino"); break;
			}
			switch (Dietlytics.user.getAbitudini(2,2)) {
		        case 1: pran3.setValue("Primo"); break;
		        case 2: pran3.setValue("Secondo"); break;
		        case 3: pran3.setValue("Insalata"); break;
		        case 4: pran3.setValue("Contorno"); break;
		        case 5: pran3.setValue("Panino"); break;
			}
			switch (Dietlytics.user.getAbitudini(3, 0)) {
		        case 1: spun21.setValue("Acqua"); break;
		        case 2: spun21.setValue("Coca-Cola"); break;
		        case 3: spun21.setValue("Vino"); break;
		        case 4: spun21.setValue("Birra"); break;
		        case 5: spun21.setValue("Aranciata"); break;
		        case 6: spun21.setValue("Sprite"); break;
		        case 7: spun21.setValue("Spremuta/Succo"); break;
			}
			switch (Dietlytics.user.getAbitudini(3,1)) {
		        case 1: spun22.setValue("Panino"); break;
		        case 2: spun22.setValue("Torta/crostata"); break;
		        case 3: spun22.setValue("Cracker"); break;
		        case 4: spun22.setValue("Frutta/frullato"); break;
			}
			switch (Dietlytics.user.getAbitudini(3,2)) {
		        case 1: spun23.setValue("Panino"); break;
		        case 2: spun23.setValue("Torta/crostata"); break;
		        case 3: spun23.setValue("Cracker"); break;
		        case 4: spun23.setValue("Frutta/frullato"); break;
			}
			switch (Dietlytics.user.getAbitudini(4, 0)) {
		        case 1: cen1.setValue("Acqua"); break;
		        case 2: cen1.setValue("Coca-Cola"); break;
		        case 3: cen1.setValue("Vino"); break;
		        case 4: cen1.setValue("Birra"); break;
		        case 5: cen1.setValue("Aranciata"); break;
		        case 6: cen1.setValue("Sprite"); break;
		        case 7: cen1.setValue("Spremuta/Succo"); break;
			}
			switch (Dietlytics.user.getAbitudini(4,1)) {
		        case 1: cen2.setValue("Primo"); break;
		        case 2: cen2.setValue("Secondo"); break;
		        case 3: cen2.setValue("Insalata"); break;
		        case 4: cen2.setValue("Contorno"); break;
		        case 5: cen2.setValue("Panino"); break;
			}
			switch (Dietlytics.user.getAbitudini(4,2)) {
		        case 1: cen3.setValue("Primo"); break;
		        case 2: cen3.setValue("Secondo"); break;
		        case 3: cen3.setValue("Insalata"); break;
		        case 4: cen3.setValue("Contorno"); break;
		        case 5: cen3.setValue("Panino"); break;
			}
		}
	}
}
