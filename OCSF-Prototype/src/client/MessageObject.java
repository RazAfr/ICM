package client;

import java.io.Serializable;

//LOGIN
//TYPE_REQUEST = LOGIN
//ARRAYLIST = { "DIMAXER", 123456 }

//VIEW REQUEST DETAILS
//TYPE_REQUEST = VIEW_REQ_DETAILS
//ARRAYLIST = { REQCODE_123 }
//(SERVER SIDE WILL HAVE PRE-MADE SQL SELECT QUERY)

import java.util.ArrayList;

/** This class is the universal-type object send to the server via sendToServer(object) method.
 * It is necessarily serializable as required by sendToServer;
 */
public class MessageObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private TypeRequest typeRequest;
	private ArrayList<Object> args;
	
	/**
	 * @param typeRequest Item of enumerator that includes possible request types.
	 * @param args ArrayList that includes the arguments required for the request.
	 */
	public MessageObject(TypeRequest typeRequest, ArrayList<Object> args) {
		this.typeRequest = typeRequest;
		this.args = args;
	}
	
	public TypeRequest getTypeRequest() {
		return typeRequest;
	}
	
	public ArrayList<Object> getArgs() {
		return args;
	}
}