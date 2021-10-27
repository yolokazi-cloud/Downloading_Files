package acsse.csc2b.client;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * ImagePane will add the features for the stage for the user interface(client)
 * @author Nogantsho Y 201602466
 * @version P04
 */
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class ImagePane extends StackPane{
        //Attributes
	//storage filenames
	String[] Files =null;
	int fileId=1;
	//client connections;
	Socket connection =null;
	//Streams
	PrintWriter pw =null;
		BufferedReader br= null;
		DataInputStream dis=null;
		DataOutputStream dos=null;
		InputStream is =null;;
		OutputStream os=null;
		//gui
		Button btnDownload;
		Button btnUpload;
		Button btnConnect;
		TextArea result;
		TextField txtID;
		Label lblID;
		
	public ImagePane(){
		
		//creating the menu and menuBar for uploading
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("File");
		menuBar.getMenus().add(menu);
		MenuItem mi = new MenuItem("Open");
		MenuItem readfile = new MenuItem("Read Text File");
		menu.getItems().add(mi);
		menu.getItems().add(readfile);
		
		//adding action using event listener
		
		mi.setOnAction(e->{
			FileChooser fc = new FileChooser();
			fc.setTitle("Choose Image File");
			fc.setInitialDirectory(new File("data"));
			File txtFile = fc.showOpenDialog(null);
			
			if(txtFile !=null) {
				try {
					Scanner scIn = new Scanner(txtFile);
					String line =" ";
					while(scIn.hasNext()) {
						line =scIn.nextLine();
						StringTokenizer st = new StringTokenizer(line);
						line = st.nextToken();
						System.out.println(line);
					}
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		
		//add button actions
		//adding vbox layout
		VBox layout = new VBox();
		layout.getChildren().add(menuBar);
		
		
	}
}
