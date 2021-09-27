
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
public class restaurant {
	//attributes
	private String restaurantName;
	private String cityRestaurant;
	private String contactNum;
	private HashMap<String, ArrayList<Integer>> order;
	private String specialPrep;
	private int total;
	//methods
	//constructor
	restaurant (String restaurantName, String cityRestaurant, String contactNum, HashMap <String, ArrayList<Integer>> order, String specialPrep, int total) {
		this.restaurantName = restaurantName;
		this.cityRestaurant = cityRestaurant;
		this.contactNum = contactNum;
		this.setOrder(order);
		this.specialPrep = specialPrep;
		this.total = total;
	}
	//get & set methods 
		//get restaurant name
		public String getRestaurantName() {
			return restaurantName;
		}
		//set restaurant name
		public void setRestaurantNamer(String newRestaurantName) {
			this.restaurantName = newRestaurantName;
		}
		//get city of the restaurant
		public String getCityRestaurant() {
			return cityRestaurant;
		}
		//set city of the restaurant
		public void setCityRestaurant(String newCityRestaurant) {
			this.cityRestaurant = newCityRestaurant;
		}
		//get contactNum 
		public String getContactNum() {
			return contactNum;
		}
		//set contactNum
		public void setContactNum (String newContactNum) {
			this.contactNum = newContactNum;
		}
		//get SpecialPrep 
		public String getSpecialPrep() {
			return specialPrep;
		}
		//set SpecialPrep	
		public void setSpecialPrep (String newSpecialPrep) {
			this.specialPrep = newSpecialPrep;
		}
		//get SpecialPrep 
		public int getTotal() {
			return total;
			}
		//set SpecialPrep			
		public void setTotal (int newTotal) {
			this.total = newTotal;
			}
		//get order
		public HashMap<String, ArrayList<Integer>> getOrder() {
			return order;
		}
		//set order
		public void setOrder(HashMap<String, ArrayList<Integer>> order) {
			this.order = order;
		}
		
		
}
