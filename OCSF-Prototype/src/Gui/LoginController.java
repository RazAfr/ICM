package Gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import Utilities.MessageObject;
import Utilities.RequestType;
import client.Client;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ocsf.server.ConnectionToClient;

public class LoginController implements Initializable {

	// Class Buttons ***************************************************
	@FXML
	private Button login;

	@FXML
	private JFXTextField id;

	@FXML
	private ImageView eror1;
	@FXML
	private ImageView eror2;
	@FXML
	private Text wrongPassword;
	@FXML
	private JFXPasswordField password;

	@FXML
	private Text date;
	// Class variables *************************************************
	private String IDText;
	private String passwordText;

	// Class methods ***************************************************

	/**
	 * This method initializes parameters for: login button to be able to be pressed
	 * by pressing enter in the keyboard, also allows the visibility of the no input
	 * given text
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		login.setDefaultButton(true);
		eror1.setVisible(false);
		eror2.setVisible(false);
		
	}

	/**
	 * Event handler for the search button receives the text that was written into
	 * the text field and sends the request id as a MessageObject to the server
	 * 
	 * @param event
	 */
	@FXML
	public void loginWasPressed(ActionEvent event) {

		setValdiator();

		// Getting the strings from the gui
		IDText = id.getText();
		passwordText = password.getText();

		// checking if any fields are empty
		// and alerting if they are
		if (IDText.isEmpty() && passwordText.isEmpty()) {
			password.validate();
			id.validate();
		} else if (!IDText.isEmpty() && passwordText.isEmpty())
			password.validate();
		else if (IDText.isEmpty() && !passwordText.isEmpty()) {
			id.validate();

			// if everything is ok send a MessageObject to
			// the server
		} else {
			ArrayList<Object> args = new ArrayList<>();
			args.add(IDText);
			args.add(passwordText);
			MessageObject msg = new MessageObject(RequestType.Login, args);
			sendMessage(msg, ClientUI.getClient());

		}
	}

	/**
	 * this method handles the information that returned from the server after login
	 * was pressed, by checking the boolean argument for true or false the method
	 * knows if the user and password was in the db or not
	 * 
	 * @param msg
	 */

	public void loginHandle(MessageObject msg) {
		MessageObject message = (MessageObject) msg;

		if ((Boolean) message.getArgs().get(0)) { // check if the user exist or not [True|False]

			ClientUI.switchScene("AcademicUserPanel");

		} else {
			wrongPassword.setText("Username/Password is Wrong");

		}

	}

	/**
	 * a method for clearing the id and password fields
	 */
	@FXML
	public void clearFields() {
		id.setText("");
		password.setText("");
	}

	/**
	 * a method that shows a Massage when no input is set in the UserName or
	 * Password
	 * 
	 */
	public void setValdiator() {
		RequiredFieldValidator validatorId = new RequiredFieldValidator();

		RequiredFieldValidator validatorPassword = new RequiredFieldValidator();
		password.getValidators().add(validatorPassword);
		validatorPassword.setMessage("No Password Given");
		id.getValidators().add(validatorId);
		validatorId.setMessage("No Input Given");
		id.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					id.validate();
					eror1.setVisible(true);
					wrongPassword.setText("");
				}

			}
		});

		validatorId.setIcon(eror1);

		password.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					password.validate();
					eror2.setVisible(true);
					wrongPassword.setText("");
				}

			}
		});

		validatorPassword.setIcon(eror2);
	}

	/**
	 * A function that sends a MessageObject to the Server and notify's if it was
	 * successfully sent via the console
	 * 
	 * @param response
	 * @param client
	 */
	private void sendMessage(MessageObject response, Client client) {

		try {
			client.sendToServer((Object) response);
			System.out.println("Message sent: " + response.getTypeRequest().toString() + " | "
					+ response.getArgs().toString() + " from Client");
		} catch (IOException e) {
			System.out.println("An Error occurd while trying to send: " + response.getTypeRequest().toString() + " | "
					+ response.getArgs().toString() + "to Client");
			e.printStackTrace();
		}
	}
}
