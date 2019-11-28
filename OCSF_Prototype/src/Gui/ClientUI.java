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
 * This class constructs the UI for a chat client. It implements the chat
 * interface in order to activate the display() method. Warning: Some of the
 * code here is cloned in ServerConsole
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
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
	Client client;

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

//Instance methods ************************************************

	/**
	 * This method waits for input from the console. Once it is received, it sends
	 * it to the client's message handler.
	 */
	public void accept() {
		try {
			BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
			String message;

			while (true) {
				message = fromConsole.readLine();
				client.handleMessageFromClientUI(message);
			}
		} catch (Exception ex) {
			System.out.println("Unexpected error while reading from console!");
		}
	}

	/**
	 * This method overrides the method in the ChatIF interface. It displays a
	 * message onto the screen.
	 *
	 * @param message The string to be displayed.
	 */
	public void display(String message) {
		System.out.println("> " + message);
	}

//Class methods ***************************************************

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent vbox;

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("RequestTracking.fxml"));
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
		// chat.accept(); // Wait for console data

		launch(args);
	}

}

//End of ConsoleChat class