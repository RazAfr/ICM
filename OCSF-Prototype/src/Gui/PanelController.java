package Gui;

import java.awt.Paint;
import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Utilities.MessageObject;
import Utilities.RequestType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PanelController {
	// Class Buttons ***************************************************
	@FXML
	private JFXButton viewRequestDetails;

	@FXML
	private JFXButton submitNewRequest;

	@FXML
	private JFXButton logOut;

	@FXML
	private Text firstName;

	// Class methods ***************************************************

	/**
	 * a method that intializes text On Load
	 * 
	 */

	@FXML
	public void initialize() {
		firstName.setText("Welcome, " + getFirstNameFromSQL());
	}

	// THIS FUNCTION WILL BE DELETED AND REPLACED WITH AN ACTUAL GET FUNCTION FROM
	// SQL CONTROLLER STATIC CLASS
	private String getFirstNameFromSQL() {
		return "David";
	}

	/**
	 * This event handler switches scene to SearchReaquest
	 * 
	 * @param event
	 */
	@FXML
	public void viewRequestDetailsWasPressed(ActionEvent event) {

		ClientUI.switchScene("SearchRequest");
		SearchRequestController controller = (SearchRequestController) ClientUI.getCurrentController();
		controller.clearFields();

	}

	/**
	 * This event handler switches scenes back to the login page
	 * 
	 * @param event
	 */
	@FXML
	public void logOutWasPressed(ActionEvent event) {
		ClientUI.switchScene("LoginPage");
		LoginController controller = (LoginController) ClientUI.getCurrentController();
		controller.clearFields();
	}
	@FXML
	public void submitNewRequestWasPressed(ActionEvent event) {

		ClientUI.switchScene("submitNewRequestGui");
		//SearchRequestController controller = (SearchRequestController) ClientUI.getCurrentController();
		//controller.clearFields();

	}

}
