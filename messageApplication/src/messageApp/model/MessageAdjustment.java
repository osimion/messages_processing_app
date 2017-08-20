package messageApp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="class")
public class MessageAdjustment extends Message{
	@JsonProperty
	public String adjustment;
	
	public MessageAdjustment() {}
	
	@JsonCreator
    public MessageAdjustment(@JsonProperty("adjustment") String adjustment) { this.adjustment = adjustment; }
	
	
	public MessageAdjustment(String productType, double value, String adjustment) {
		super(productType, value);
		this.adjustment = adjustment;
	}

	public String getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(String adjustment) {
		this.adjustment = adjustment;
	}

	
	
	
}
