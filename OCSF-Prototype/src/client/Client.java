package client;

import ocsf.client.*;

import java.io.*;

import Gui.ClientUI;
import Gui.LoginController;
import Gui.RequestFormController;
import Gui.SearchRequestController;
import Utilities.MessageObject;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 *
 */
public class Client extends AbstractClient {
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public Client(String host, int port) throws IOException {
		super(host, port); // Call the superclass constructor

		openConnection();
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server. messages from
	 * server should only come as MessageObjects. this method checks what kind of
	 * Request was handled by the server and calls the right controller into action
	 * 
	 * @param msg The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {

		MessageObject message = (MessageObject) msg;
		printMessageRecieved(message);
		Object currentController = ClientUI.getCurrentController();

		switch (message.getTypeRequest()) {
		case Login: {
			if (currentController instanceof LoginController) {

				((LoginController) currentController).loginHandle(message);
			} else
				System.out.println("Controller instance is NOT LoginController!");
			break;
		}
		case View_Req_Details: {

			if (currentController instanceof SearchRequestController) {

				((SearchRequestController) currentController).handleSearchRequest(message);

			} else {
				System.out.println("Controller instance is NOT SearchRequestController!");
			}

			break;
		}
		case change_Status: {
			if (currentController instanceof RequestFormController) {

				((RequestFormController) currentController).handleChangeStatus(message);

			} else {
				System.out.println("Controller instance is NOT RequestFormController!");

			}
		}
		default:
			break;
		}

	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */
	public void handleMessageFromClientUI(String message) {
		try {
			sendToServer(message);

			System.out.println("Message sent: " + message + "from Client");

		} catch (IOException e) {
			System.out.println("Error Message wasnt sent to the server");
			e.printStackTrace();
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	/**
	 * this method prints the message that was received from the server
	 * 
	 * @param message
	 */
	public void printMessageRecieved(MessageObject message) {
		System.out.println("Message recieved: " + (message).getTypeRequest().toString() + " | "
				+ (message).getArgs().toString() + " from server");

	}
}
//End of ChatClient class
