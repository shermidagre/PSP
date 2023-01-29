package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage=primaryStage;
		 mainWindow();
	}
	
	public void  mainWindow(){
	
		try {	
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindowView.fxml"));    			
			AnchorPane root = (AnchorPane) fxmlLoader.load(); 
			Scene scene = new Scene(root,300,400);
			scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
			MainWindowController mainWindowController = fxmlLoader.getController();
		    mainWindowController.setNetClient(new NetClient ( "127.0.0.1", 5555));//przypisanie kontrolera klasie NetClient
		    primaryStage.setMinWidth(300.00);
			primaryStage.setMinHeight(400.00);
			primaryStage.setTitle("Calculator Client-Server");
			primaryStage.setResizable(false);
		    primaryStage.setScene(scene);
			primaryStage.show();		
		}catch(Exception e) {
			e.printStackTrace();}		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
