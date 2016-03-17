package client;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import controller.InterfacciaIniziale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{

	public static Scene scene;
	public static Stage Stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Socket clientSocket;
		ObjectOutputStream versoServer;
		ObjectInputStream dalServer;
		try
		{	
			Document document = Client.ReadXML("client_config.xml");
			String ipAddress = document.getElementsByTagName("Socket").item(0).getAttributes().item(0).getTextContent();
            int portSocket = Integer.parseInt(document.getElementsByTagName("Socket").item(0).getAttributes().item(1).getTextContent());
			clientSocket = new Socket (ipAddress, portSocket);
			versoServer = new ObjectOutputStream(clientSocket.getOutputStream());
			dalServer = new ObjectInputStream(clientSocket.getInputStream());

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/loginregistration.fxml"));
			scene = new Scene(loader.load());
			Stage = primaryStage;
			Stage.setScene(scene);
			Stage.show();
			InterfacciaIniziale controller = loader.<InterfacciaIniziale>getController();
			controller.initializePage(versoServer,dalServer);
			} catch(Exception exc) {
			System.out.println("Errore-InitialieDefaultCartController: " + exc.getMessage());
			exc.printStackTrace();
			}
	}
	
	public static Document ReadXML(String path) {
        Document document = null;
        try {
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(file);
            document.getDocumentElement().normalize();
        } catch(Exception exc) {
            System.out.println("Errore: " + exc.getMessage());
            exc.printStackTrace();
        }

        return document;
    }

	public static void main (String[] args) throws Exception {
		
		Application.launch(Client.class, (java.lang.String[])null);	

	}
}
