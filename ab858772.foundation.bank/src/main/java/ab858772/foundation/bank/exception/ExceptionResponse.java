package ab858772.foundation.bank.exception;

public class ExceptionResponse {

	private String errorMsg;
	private String requestedUri;
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getRequestedUri() {
		return requestedUri;
	}
	public void setRequestedUri(String requestedUri) {
		this.requestedUri = requestedUri;
	}
	
}
