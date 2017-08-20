package messageApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import messageApp.model.Adjustment;
import messageApp.model.Message;
import messageApp.model.MessageAdjustment;
import messageApp.model.MessageDetails;
import messageApp.model.Product;

public class Register {
	
	Map<String, List<Double>> productSales;
	private static final int PROCESS_MESSAGE_CAPACITY = 50;
	private static final String ADD = "add";
	private static final String MULTIPLY = "multiply";
	private static final String SUBSTRACT = "substract";
	List<Adjustment> adjustments = new ArrayList<Adjustment>();
	
	public Register() {
        this.productSales = new HashMap<>();
    }

    public Register(Map<String, List<Double>> records) {
        this.productSales = records;
    }

    public Map<String, List<Double>> getRecords() {
        return productSales;
    }
	
	public void adjustSales(Message message){
		String operationType = ((MessageAdjustment) message).getAdjustment();
		List<Double> transactions = productSales.get(message.getProductType());
		
		//Operations can be add, subtract, or multiply
		//e.g Add 20p apples would instruct your application to add 20p to each sale of apples you have recorded.
		if(transactions != null){
			switch(operationType) {
			case ADD:
				for(Double transactionValue : transactions) {
					transactionValue += message.getValue();
					adjustments.add(new Adjustment(message.getProductType(), ADD, message.getValue()));
				}
				break;
			case MULTIPLY:
				for(Double transactionValue : transactions) {
					transactionValue = transactionValue * message.getValue();
					adjustments.add(new Adjustment(message.getProductType(), MULTIPLY, message.getValue()));
				}
				break;
			case SUBSTRACT:
				for(Double transactionValue : transactions) {
					if(transactionValue < message.getValue()){
						System.out.println("The transaction to be substracted has a selling price smaller than the substracted value");
					}
					transactionValue -= message.getValue();
					adjustments.add(new Adjustment(message.getProductType(), SUBSTRACT, message.getValue()));
				}
				break;
			default:
				System.out.println("Unsupported operation!");
				break;
			}
		}else{
			System.out.println("Adjustment is not possible because product is not available!");
		}
		 
	}
	
	public void processSales(List<Message> messages){
		int processedMessagesNo = 0; 
		for(Message message : messages){
			if(message instanceof MessageAdjustment){
				// contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type.
				adjustSales(message);
			}else{
				if(message instanceof MessageDetails){
					// contains the details of a sale and the number of occurrences of	that sale. E.g 20 sales of apples at 10p each.
					int saleCount = Integer.parseInt(((MessageDetails)message).getDetails());
					for(int i=0; i< saleCount; i++){
						addTransaction(message);
						logMessage(message);
						
					}
				}else{
					addTransaction(message);
					logMessage(message);
				}
				processedMessagesNo++;
				//After every 10th message received your application should log a report detailing the number of sales of each product and their total value.
				if(processedMessagesNo % 10 == 0){
					System.out.println("-- Intermediate Sales Report --");
					logReport();
				}
				
			}

			//After 50 messages your application should log that it is pausing, stop accepting new
			//messages and log a report of the adjustments that have been made to each sale type while
			//the application was running.
			if(processedMessagesNo == PROCESS_MESSAGE_CAPACITY){
				System.out.println("Processing new messages stopped because the capacity of processing the messages is full.");
				logAdjustments();
				break;
			}
		}
	}
	
	public void addTransaction(Message message){
		//Product messageProduct = new Product(message.getProductType(),message.getValue());
		List<Double> productTransactionsSum = new ArrayList<Double>();
		if(productSales.get(new Product(message.getProductType(),message.getValue()))!= null){
			productTransactionsSum = productSales.get(message.getProductType());
			productTransactionsSum.add(message.getValue());
			productSales.put(message.getProductType(), productTransactionsSum);
		}else{
			productTransactionsSum.add(message.getValue());
			productSales.put(message.getProductType(), productTransactionsSum);
		}
	}
	
	public void logReport(){
		for (Map.Entry<String, List<Double>> entry : productSales.entrySet()){
			double productSalesSum = 0;
			for(Double d : entry.getValue()){
				productSalesSum += d;
			}
			System.out.println("Product "+ entry.getKey()+ " had " + entry.getValue().size()  +" sales worth "+ productSalesSum);
		}
	}
	
	public void logAdjustments(){
		for(Adjustment adjustment : adjustments){
			System.out.println("For the Product "+ adjustment.getProductType()+ " it was made a "+ adjustment.getType()+ " adjustment of " + adjustment.getValue());
		}
	}

	
	public void logMessage (Message message){
		System.out.println("Sale of Product Type "+ message.getProductType() + " at price :"+ message.getValue());
	}
}
