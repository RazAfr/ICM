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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button login;

	@FXML
	private TextField id;

	@FXML
	private PasswordField password;
	
	@FXML
	private Text date;

	private String IDText;
	private String passwordText;

	/**
	 * event handler for the search button receives the text that was written into
	 * the text field
	 * 
	 * @param event
	 */

	@FXML
	public void loginWasPressed(ActionEvent event) {
		IDText = id.getText();
		passwordText = password.getText();
		// LOGIN LOGIC HERE
		System.out.println(IDText + " : " + passwordText);
	}
}
