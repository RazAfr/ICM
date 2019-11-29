package Gui;

import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import client.MessageObject;
import client.TypeRequest;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RequestTrackingController {

	@FXML
	private JFXButton search;

	@FXML
	private JFXTextField requestID;
	
	private Text alertMessage;
	
	String requestIDText;

	/**
	 * event handler for the search button receives the text that was written into
	 * the text field
	 * 
	 * @param event
	 */

	@FXML
	public void searchWasPressed(ActionEvent event) {

		requestIDText = requestID.getText();
		if(requestIDText.equals(""))
			alertMessage.setText("Empty ID is NOT allowed!");
		
		ArrayList<Object> arrlist = new ArrayList<>();
		arrlist.add(requestID.getText());
		
		MessageObject viewRequest = new MessageObject(TypeRequest.View_Req_Details, arrlist);
		try {
			ClientUI.getClient().sendToServer(viewRequest);
			alertMessage.setText("Search request sent successfully!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(requestIDText);
	}
	
}
