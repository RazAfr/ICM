package Gui;

import java.io.*;
import java.util.ArrayList;

import Utilities.Alert;
import Utilities.ScreenManager;
import client.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jdbc.mysqlConnection;

/**
 * This class constructs the UI for a JavaFX client.
 * 
 * @author Team16 Braude
 * @version Nov2019
 */
public class ClientUI extends Application {
//Class variables *************************************************

	/**
	 * The First scene to load can be changed if the creation of new client failed
	 */
	public static String FirstScene;
	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	final public static String DEFAULT_SERVER = "localhost";

//Instance variables **********************************************

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	private static Client client;

	/**
	 * Instance of screenManager witch saves all scenes and the stage with this
	 * screenManager you can add new scenes and switch between them
	 */
	public static ScreenManager screenManager;

	// Getter's ****************************************************
	/**
	 * returns the instance of the client
	 * 
	 * @return
	 */
	public static Client getClient() {
		return client;
	}

	/**
	 * set Client manuall
	 * 
	 * 
	 */

	/**
	 * returns the instance of the current controller that is being used by a scene
	 * it is important in order to use the controllers methods when you switch
	 * between new scene's
	 * 
	 * @return
	 */
	public static Object getCurrentController() {
		return screenManager.getCurrentController();
	}

//Constructors ****************************************************

	/**
	 * there must be a constructor without param's because of the extension to
	 * application
	 */
	public ClientUI()
	{

	}

	/**
	 * Constructs an instance of the ClientConsole UI The First scene to load can be
	 * changed if the creation of new client failed
	 * 
	 * @param host The host to connect to.
	 * @param port The port to connect on.
	 */
	public ClientUI(String host, int port) {
		try {
			client = new Client(host, port);
			FirstScene = "AcademicUserPanel.fxml";
		} catch (IOException exception) {
			FirstScene = "ConnectServerManualy.fxml";
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			// System.exit(1); /// not need
		}
	}

//Class methods ***************************************************

	/**
	 * this method loads and runs the stage and first scene, after it loads you can
	 * switch scenes by using the switchScene methos
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root;
		FXMLLoader loader = new FXMLLoader();

		try {
			loader = new FXMLLoader();

			loader.setLocation(getClass().getResource(FirstScene));

			root = loader.load();

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		Scene scene = new Scene(root);
		scene.setUserData(loader);
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		primaryStage.show();
		screenManager = new ScreenManager(scene, primaryStage, FirstScene.substring(0, FirstScene.length() - 5));
	}

	/**
	 * A method to set client manually in case you connect to another server
	 * 
	 * 
	 * @param String
	 * @return
	 */

	public static Boolean setClient(String ip) {
		try {
			client = new Client(ip, DEFAULT_PORT);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * A method to switch Scene's on the main stage method needs the fxml file name
	 * (without .fxml)
	 * 
	 * @param fxml
	 */
	public static void switchScene(String fxml) {
		try {
			screenManager.switchScene(fxml);
		} catch (Exception e) {
			System.out.println("Error occured while trying to switch to  scene: " + fxml);
			e.printStackTrace();
		}
	}

	/**
	 * This method is responsible for the creation of the Client UI.
	 *
	 * @param args[0] The host to connect to.
	 */
	public static void main(String[] args) {

		ClientUI gui = new ClientUI(DEFAULT_SERVER, DEFAULT_PORT);

		launch(args);
	}

}
