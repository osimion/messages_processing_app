package messageApp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="class")
public class MessageDetails extends Message{
	@JsonProperty
	private String details;
	
	public MessageDetails(String productType, Double value, String details) {
        super(productType, value);
        this.details = details;
    }
	public MessageDetails(String details) {
        this.details = details;
    }
	public MessageDetails() {
    }

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
}
