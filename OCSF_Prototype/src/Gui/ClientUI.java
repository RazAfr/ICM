package Gui;

//This file contains material supporting section 3.7 of the textbook:
//"Object Oriented Software Engineering" and is issued under the open-source
//license found at www.lloseng.com 

import java.io.*;

import client.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class constructs the UI for a JavaFX client.
 * 
 * @author Team16 Braude
 * @version Nov2019
 */
public class ClientUI extends Application {
//Class variables *************************************************

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

//Instance variables **********************************************

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	private static Client client;
	
	public static Client getClient() {
		return client;
	}

//Constructors ****************************************************

	public ClientUI()

	{

	}

	/**
	 * Constructs an instance of the ClientConsole UI.
	 *
	 * @param host The host to connect to.
	 * @param port The port to connect on.
	 */
	public ClientUI(String host, int port) {
		try {
			client = new Client(host, port);
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}

//Class methods ***************************************************

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent vbox;

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("RequestTracking.fxml"));
			//loader.setLocation(getClass().getResource("LoginPage.fxml"));
			vbox = loader.load();

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		Scene s = new Scene(vbox);

		primaryStage.setScene(s);
		primaryStage.show();
	}

	/**
	 * This method is responsible for the creation of the Client UI.
	 *
	 * @param args[0] The host to connect to.
	 */
	public static void main(String[] args) {

		String host = "";
		int port = 0; // The port number

		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			host = "192.168.1.19";
		}
		ClientUI gui = new ClientUI(host, DEFAULT_PORT);

		launch(args);
	}

}