package messageApp.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="class")

@JsonSubTypes({
        @JsonSubTypes.Type(value = MessageDetails.class),
        @JsonSubTypes.Type(value = MessageAdjustment.class)
})

public class Message{
	
	private String productType;
	private double value;
	
	public Message(String productType, double value) {
		super();
		this.productType = productType;
		this.value = value;
	}
	
	public Message() {
	}

	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
	
	
}
