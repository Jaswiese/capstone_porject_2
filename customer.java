
public class customer {
	//attributes 
	private int orderNumber;
	private String customerName;
	private String contactNum;
	private String email;
	private int streetNum;
	private String streetName;
	private String suburb;
	private String city;
	//methods 
	//constructor method for customer
	public customer(int orderNumber, String customerName, String contactNum, String email, int streetNum, String streetName, String suburb, String city) {
		this.orderNumber = orderNumber;
		this.customerName = customerName;
		this.contactNum = contactNum;
		this.email = email;
		this.streetNum = streetNum;
		this.streetName = streetName;
		this.suburb = suburb;
		this.city = city;
	}
	// get & set methods 
	//get order number
	public int getOrderNumber() {
		return orderNumber;
	}
	//set order number
	public void setOrderNumber(int newOrderNumber) {
		this.orderNumber = newOrderNumber;
	}
	//get customer name
	public String getCustomerName() {
		return customerName;
	}
	//set customer name
	public void setCustomerName(String newCustomerName) {
		this.customerName = newCustomerName;
	}
	//get contactNum
	public String getContactNum() {
		return contactNum;
	}
	//set contactNum
	public void setContactNum(String newContactNum) {
		this.contactNum = newContactNum;
	}
	//get email
	public String getEmail() {
		return email;
	}
	//set email
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	//get streetNum
	public int getStreetNum() {
		return streetNum;
	}	
	//set streetNum-	
	public void setStreetNum(int newStreetNum) {
		this.streetNum = newStreetNum;
	}
	//get streetNum
	public String getStreetName() {
		return streetName;
	}		
	//set streetNum		
	public void setStreetName(String newStreetName) {
		this.streetName = newStreetName;
	}
	//get suburb
	public String getSuburb() {
		return suburb;
	}			
	//set suburb			
	public void setSuburb(String newSuburb) {
		this.suburb = newSuburb;
	}
	//get suburb
	public String getCity() {
		return city;
	}				
	//set suburb					
	public void setCity(String newCity) {
		this.city = newCity;
	}
	
	
	
	
}
