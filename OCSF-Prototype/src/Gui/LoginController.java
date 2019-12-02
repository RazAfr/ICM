package Gui;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import client.MessageObject;
import client.TypeRequest;
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
		ArrayList<Object> args = new ArrayList<>();
		args.add(IDText);
		args.add(passwordText);
		MessageObject msg = new MessageObject(TypeRequest.Login, args);
		try {
			ClientUI.getClient().sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void handleMessageFromServer(Object msg) {
		System.out.println("Reached me! Inside Login Controller.");
		  if (msg instanceof MessageObject) {
			  MessageObject message = (MessageObject)msg;
			  if (!ClientUI.isMessageMine(message)) {
				  System.out.println("Message NOT yours!");
				  return;
			  }
			  System.out.println("Message recieved: " + (message).getTypeRequest().toString() + " | " + (message).getArgs().toString() + " from server");
			  if (message.getTypeRequest() == TypeRequest.Login) {
				  if ((Boolean)message.getArgs().get(1))
					  ClientUI.openNewWindow("AcademicUserPanel", getClass());
				  else
					  login.setText(login.getText() + "\nWrong ID/PW!");
			  }
		  }
		  else
			  System.out.println(msg.toString());
	}
}
