package just.by.jvd.models;

public class CallResultModel {

	boolean Failed, Successful;
	
	public CallResultModel() {
		
	}
	
	public CallResultModel(boolean Failed, boolean Successful) {
		
		this.Failed = Failed;
		this.Successful = Successful;		
	}

	public boolean isFailed() {
		return Failed;
	}

	public void setFailed(boolean failed) {
		Failed = failed;
	}

	public boolean isSuccessful() {
		return Successful;
	}

	public void setSuccessful(boolean successful) {
		Successful = successful;
	}

}
