package interfaces;

public interface Operations {
	
	public void listMed();
	
	public void searchMed(String medName);
	
	public void buyMed();
	
	public void addCustomer(String Medicine_ID,int Amount);
	
	public void updateOrders(String Customer_ID,String Medicine_ID, int Quantity , int Amount );
	
	public int randomNum();
	

}
