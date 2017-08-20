package messageApp.model;

public class Adjustment extends Product{
	
	private String adjustmentType;
	private double adjustmentValue;
		
	
	
	public Adjustment(String productType, double value, String adjustmentType, double adjustmentValue) {
		super(productType, value);
		this.adjustmentType = adjustmentType;
		this.adjustmentValue = adjustmentValue;
	}
	
	public Adjustment() {}
	
	public Adjustment(String adjustmentType, double adjustmentValue) {
		this.adjustmentType = adjustmentType;
		this.adjustmentValue = adjustmentValue;
	}
	
	public Adjustment(String productType,  String adjustmentType, double adjustmentValue) {
		super(productType, 0);
		this.adjustmentType = adjustmentType;
		this.adjustmentValue = adjustmentValue;
	}
	public String getType() {
		return adjustmentType;
	}
	public void setType(String type) {
		this.adjustmentType = type;
	}
	public double getValue() {
		return adjustmentValue;
	}
	public void setValue(double value) {
		this.adjustmentValue = value;
	}
	
	
	
	

}
