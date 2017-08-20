package messageApp.model;

public class Product {

	private String productType;
	private double value;
	
	public Product() { }
	public Product(String productType, double value) {
		super();
		this.productType = productType;
		this.value = value;
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
