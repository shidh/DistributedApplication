package Client;

/**
 * DictionaryCallbackHandler Callback class, Users can extend this class and
 * implement their own receiveResult and receiveError methods.
 */
public abstract class DictionaryCallbackHandler {

	protected Object clientData;

	/**
	 * User can pass in any object that needs to be accessed once the
	 * NonBlocking Web service call is finished and appropriate method of this
	 * CallBack is called.
	 * 
	 * @param clientData
	 *            Object mechanism by which the user can pass in user data that
	 *            will be avilable at the time this callback is called.
	 */
	public DictionaryCallbackHandler(Object clientData) {
		this.clientData = clientData;
	}

	/**
	 * Please use this constructor if you don't want to set any clientData
	 */
	public DictionaryCallbackHandler() {
		this.clientData = null;
	}

	/**
	 * Get the client data
	 */

	public Object getClientData() {
		return clientData;
	}

	/**
	 * auto generated Axis2 call back method for getWordInfo method override
	 * this method for handling normal response from getWordInfo operation
	 */
	public void receiveResultgetWordInfo(
			Client.DictionaryStub.GetWordInfoResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from getWordInfo operation
	 */
	public void receiveErrorgetWordInfo(java.lang.Exception e) {
	}

	/**
	 * auto generated Axis2 call back method for addWord method override this
	 * method for handling normal response from addWord operation
	 */
	public void receiveResultaddWord(
			Client.DictionaryStub.AddWordResponse result) {
	}

	/**
	 * auto generated Axis2 Error handler override this method for handling
	 * error response from addWord operation
	 */
	public void receiveErroraddWord(java.lang.Exception e) {
	}

}
