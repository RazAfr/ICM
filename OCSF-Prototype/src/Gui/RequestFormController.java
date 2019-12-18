package Gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.validation.RequiredFieldValidator;

import Utilities.MessageObject;
import Utilities.RequestType;
import client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RequestFormController implements Initializable {

	@FXML
	JFXTextArea requestid;
	@FXML
	JFXTextArea initatorName;
	@FXML
	JFXTextArea handlerName;
	@FXML
	JFXComboBox<String> status;
	@FXML
	JFXTextArea currentStage;
	@FXML
	JFXTextArea descreaption;
	@FXML
	JFXButton Back;
	@FXML
	JFXButton home;
	@FXML
	JFXButton save;
	@FXML
	Text erorStatus;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		status.getItems().add("Active");
		status.getItems().add("Suspended");
		status.getItems().add("Frezze");
		
	}

	@FXML
	public void loadRequest(Object requestData) {
		MessageObject data = (MessageObject) requestData;
		ArrayList<Object> dataList = data.getArgs();
		initatorName.setText((String) dataList.get(1));
		requestid.setText((String) dataList.get(2));
		currentStage.setText((String) dataList.get(3));
		descreaption.setText((String) dataList.get(4));
		status.setPromptText((String) dataList.get(5));
		handlerName.setText((String) dataList.get(6));
	}

	@FXML
	public void backWasPressed(ActionEvent event) {
		ClientUI.switchScene("SearchRequest");
		SearchRequestController controller = (SearchRequestController) ClientUI.getCurrentController();
		controller.clearFields();
	}

	@FXML
	public void homeWasPressed(ActionEvent event) {
		ClientUI.switchScene("AcademicUserPanel");
		
	}

	@FXML
	public void editWasPressed(ActionEvent event) {

		ArrayList<Object> list = new ArrayList<Object>();
		list.add(requestid.getText());
		list.add(status.getSelectionModel().getSelectedItem());
		MessageObject changeStatus = new MessageObject(RequestType.change_Status, list);
		sendMessage(changeStatus, ClientUI.getClient());
	}

	/**
	 * function to check if the status changed in the DB number greater then zero is
	 * good otherwise no
	 * 
	 */
	public void handleChangeStatus(MessageObject massage) {
	
		if (!(boolean) massage.getArgs().get(0)) {
			erorStatus.setText("The status hasn't been changed / already is the selected value " );
			erorStatus.setFill(Color.RED);
		
		} else
			erorStatus.setText("");
		
		{

		}
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
