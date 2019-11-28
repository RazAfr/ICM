package Gui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RequestTrackingController  {

	@FXML
	private JFXButton search;

	@FXML
	private JFXTextField requestID;

	String requestIDText;
	/**
	 * event handler for the search button
	 * receives the text that was written into 
	 * the text field
	 * @param event
	 */

	@FXML
	public void searchWasPressed(ActionEvent event) {

		requestIDText = requestID.getText();
		System.out.println(requestIDText);
	}


}
