package Dietlytics;
import Client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Dietlytics extends Application
{
	static Client myClient;
	static Scene scene;
	public static Stage Stage;
	public static int id;
	public static String welcome, dietxml="";
	public static int abitudini[][] = new int[5][3];
	
	@Override
	 public void start(Stage primaryStage) 
	 {
	        try 
	        {
	        	Parent root = FXMLLoader.load(getClass().getResource("../view/loginregistration.fxml"));
	        	scene = new Scene(root);
	        	Stage = primaryStage;
	        	Stage.setScene(scene);
	        	Stage.show();
	        } 
	        catch (Exception e) 
	        {
	        	System.out.println("Error: " + e.getMessage());
	            e.printStackTrace();
	        }
	    } 
	
	public static void main(String[] args) throws Exception 
	{
		myClient = new Client();
		myClient.start();	
		Application.launch(Dietlytics.class, (java.lang.String[])null);	
	}
	
}
