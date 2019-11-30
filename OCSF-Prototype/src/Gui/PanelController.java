package Gui;

import java.awt.Paint;
import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import client.MessageObject;
import client.TypeRequest;
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

	@FXML
	private JFXButton viewRequestDetails;

	//@FXML
	//private JFXTextField requestID;
	
	@FXML
	private Text firstName;

	/**
	 * event handler for the search button receives the text that was written into
	 * the text field
	 * 
	 * @param event
	 */

	/** On Load
	 * 
	 */

	@FXML
	public void initialize() {
		firstName.setText("Welcome, " + getFirstNameFromSQL());
	}
	
	// THIS FUNCTION WILL BE DELETED AND REPLACED WITH AN ACTUAL GET FUNCTION FROM SQL CONTROLLER STATIC CLASS
	private String getFirstNameFromSQL() {
		return "David";
	}
	
	@FXML
	public void viewRequestDetailsWasPressed(ActionEvent event) {
		ClientUI.openNewWindow("RequestTracking", getClass());
	}
	
}
