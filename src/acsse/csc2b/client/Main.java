package acsse.csc2b.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
   
	//declaring ImgClientPane attribute
	ImagePane imgPane = null;

	@Override
	public void start(Stage primaryStage) throws Exception {
		imgPane = new ImagePane();
		Scene scene = new Scene(imgPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Innovate Network System");
		primaryStage.setWidth(450);
		primaryStage.setHeight(600);
		primaryStage.show();
		
	}
}



