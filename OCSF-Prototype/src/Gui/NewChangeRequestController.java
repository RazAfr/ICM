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


public class NewChangeRequestController implements Initializable
{

	@FXML
	private JFXTextField jfxdate;
	@FXML
	private JFXTextField jfxname;
	@FXML
	private JFXTextField jfxemail;
	@FXML
	private JFXTextField jfxposition;
	@FXML
	private JFXTextField jfxinfSys;
	@FXML
	private JFXTextField jfxsituation;
	@FXML
	private JFXTextField jfxwantedChange;
	@FXML
	private JFXTextField jfxnotes;
	
	@FXML
	private Button attach;
	@FXML
	private Button submit;
	
	private String date,name,email,position,infSys,situation,wantedChange,notes;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		attach.setDefaultButton(true);
		submit.setDefaultButton(true);
	}
	
	@FXML
	public void submitWasPressed(ActionEvent event)
	{
		date = jfxdate.getText();
		name = jfxname.getText();
		email = jfxemail.getText();
		position = jfxposition.getText();
		infSys = jfxinfSys.getText();
		situation = jfxsituation.getText();
		wantedChange = jfxwantedChange.getText();
		notes = jfxnotes.getText();

		
		jfxdate.validate();
		jfxname.validate();
		jfxemail.validate();
		jfxposition.validate();
		jfxinfSys.validate();
		jfxsituation.validate();
		jfxwantedChange.validate();
		
		ArrayList<Object> args = new ArrayList<>();
		args.add(date);
		args.add(name);
		args.add(email);
		args.add(position);
		args.add(infSys);
		args.add(situation);
		args.add(wantedChange);
		args.add(notes);

		MessageObject msg = new MessageObject(RequestType.NewChangeRequest, args);
		sendMessage(msg, ClientUI.getClient());


	}
	public void newCRHandler(MessageObject msg) {
		MessageObject message = (MessageObject) msg;

		if ((Boolean) message.getArgs().get(0))//  [True|False]
			ClientUI.switchScene("AcademicUserPanel");
	}

	
	private void sendMessage(MessageObject response, Client client)
	{
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
	
	
	@FXML
	public void attachWasPressed(ActionEvent event)
	{
		
	}
	
	
	
	
	
	
	
	public void setValdiator()
	{
		RequiredFieldValidator validator=new RequiredFieldValidator();

		jfxdate.getValidators().add(validator);
		jfxname.getValidators().add(validator);
		jfxposition.getValidators().add(validator);
		jfxsituation.getValidators().add(validator);
		jfxwantedChange.getValidators().add(validator);
		jfxinfSys.getValidators().add(validator);

		validator.setMessage("Please fill all required fields");
	}

}
