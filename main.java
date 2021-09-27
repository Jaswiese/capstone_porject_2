
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		/*
		 * treeMaps are used as they automatically sort on the natural ordering of the keys i.e integer numerically ascending and string alphabetically
		 * with regards to the location, this would then automatically cluster the names of the locations - i.e sorting the list by location.*/
		//OrdersCustomer treeMap initialised outside of the do while loop, to store all of the  information of the customer.
		TreeMap <Integer, customer> customerDetails = new TreeMap<>();
		//treeMap created to sort customer name alphabetically 
		TreeMap <String, Integer> customerAlphabetical = new TreeMap<>();
		//treeMap sort by location
		TreeMap <String, String> customerLocation = new TreeMap<>();
		//conditional variable added to be used by the do while loop
		boolean addCustomer = true;
		/*LowLoadDriver variable added to be used within the switch case of delivery assignment & in the load updates (hence declaration outside of the do while loop)
		 * assignedDrivers arrayList declared outside of the do while for the same reason as explained above.
		 * */
		String lowLoadDriver = "";
		ArrayList<String> assignedDrivers = new ArrayList<String>();
		
		// do while loop used to continually run the code, while the user wants to add more orders.
		do {
			// Customer class attributes & input 
			//attributes
			int orderNumber;
			String customerName;
			String contactNum;
			String email;
			int streetNum;
			String streetName;
			String suburb;
			String city;
			//scanner creation - customerInfo
			Scanner customerInfo = new Scanner(System.in);
			//order number user input
			System.out.println("Please enter the order number: ");
			orderNumber = customerInfo.nextInt();
			//to consume nextInt
			customerInfo.nextLine();
			//Customer name user input
			System.out.println("Please enter the customer name: ");
			customerName = customerInfo.nextLine().toLowerCase();
			//Contact number user input
			System.out.println("Please enter the customer's contact number: ");
			contactNum = customerInfo.nextLine();
			//email user input
			System.out.println("Please enter the customer's email: ");
			email = customerInfo.nextLine();
			//street number user input
			System.out.println("Please enter the customer's street number: ");
			streetNum = customerInfo.nextInt();
			//to consume nextInt
			customerInfo.nextLine();
			//Street name user input
			System.out.println("Please enter the customer's street name: ");
			streetName = customerInfo.nextLine();
			//suburb user input
			System.out.println("Please enter the customer's suburb: ");
			suburb = customerInfo.nextLine();
			//suburb user input
			System.out.println("Please enter the customer's city: ");
			city = customerInfo.nextLine().toLowerCase();
			//customer constructor called 
			customer orderCustomer = new customer(orderNumber, customerName, contactNum, email, streetNum, streetName, suburb, city);
			//customer order details added to the customerDetails treeMap.
			customerDetails.put(orderNumber, orderCustomer);
			//customer name & order number added to the customerAlphabetical treeMap.
			customerAlphabetical.put(customerName, orderNumber);
			// customer name & location added to the customerLocation
			customerLocation.put(city, customerName);
			
			//Restaurant class attributes
			String restaurantName;
			String cityRestaurant;
			String restaurantNum;
			HashMap<String, ArrayList<Integer>> order = new HashMap<String, ArrayList<Integer>>();
			String specialPrep;
			int total;
			//restaurant class inputs
			//Restaurant name user input
			System.out.println("Please enter the restaurant name: ");
			restaurantName = customerInfo.nextLine();
			//City of restaurant user input
			System.out.println("Please enter the restaurant city: ");
			cityRestaurant = customerInfo.nextLine().toLowerCase();
			//Restaurant number user input
			System.out.println("Please enter the restaurants contact number: ");
			restaurantNum = customerInfo.nextLine();
			//Special prep user input
			System.out.println("Please enter any special requests the user had: ");
			specialPrep = customerInfo.nextLine();
			//hashmap input loop - order
			//hashmap variables
			String foodItem;
			int price;
			int amountOrdered;
			//loop conditional set to true for initial run
			boolean addFoodItem = true;
			do {
			//food item user input
			System.out.println("Please enter the food item you would like to add to the order: ");
			foodItem = customerInfo.nextLine();
			//order number user input
			System.out.printf("Please enter the price of %s: ", foodItem);
			price = customerInfo.nextInt();
			//order number user input
			System.out.printf("Please enter the amount of %s you want to order: ", foodItem);
			amountOrdered = customerInfo.nextInt();
			//consume nextInt
			customerInfo.nextLine();
			//ArrayList
			ArrayList<Integer> priceAndAmount = new ArrayList<Integer>();
			//index 0 - price & index 1 - amount;
			priceAndAmount.add(price);
			priceAndAmount.add(amountOrdered);
			//HashMap parameters are added to the HashMap
			order.put(foodItem, priceAndAmount);
			//user is prompted if they would like to add another item - if true the loop executes again if false the loop stops. (some user input filters are added such as uppercase & char position to account for user error)
			System.out.println("Would you like to add another food item? (Y - YES : N - NO): ");
			addFoodItem = customerInfo.nextLine().toUpperCase().charAt(0) == 'Y';
			} while (addFoodItem);
			//total calculation 
			/*Iterator class is used to iterate over the HashMap
			 * Logic - loop executes while there is still a next entry in the HashMap
			 * the cost variable is initialised on each loop as the result of multiplying the price with the amount (at index 0 & 1).
			 * the cost is then appended to the subtotal variable on each loop
			 * the total variable is then set to the subtotal variable.*/
			int subtotal = 0;
			  Iterator<Entry<String, ArrayList<Integer>>> iOrder = order.entrySet().iterator();
			  while (iOrder.hasNext()) {
				  Entry<String, ArrayList<Integer>> orderEntered = iOrder.next();
				 int cost = orderEntered.getValue().get(0)*orderEntered.getValue().get(1);
				 subtotal += cost;
			  }
			total = subtotal;
			
			//restaurant constructor called 
			restaurant orderRestaurant = new restaurant(restaurantName, cityRestaurant, restaurantNum, order, specialPrep, total);
			//driver methods
			// HashMaps for drivers created by area serviced.
			/*
			 * The String represents the drivers name. 
			 * The integer value represents the load of the driver.*/
			Map<String, Integer> capeTown = new HashMap<String, Integer>();
			Map<String, Integer> durban = new HashMap<String, Integer>();
			Map<String, Integer> johannesburg = new HashMap<String, Integer>();
			Map<String, Integer> potchefstroom = new HashMap<String, Integer>();
			Map<String, Integer> springbok = new HashMap<String, Integer>();
			Map<String, Integer> bloemfontein = new HashMap<String, Integer>();
			Map<String, Integer> witbank = new HashMap<String, Integer>();
			Map<String, Integer> portElizabeth = new HashMap<String, Integer>();
			//try and catch block used to help in error identification. 
			//File & scanner objects initialised
			File drivers;
			Scanner scFile = null;
			Scanner scLine = null;
			try { 
				//file reading & scanner created
				drivers = new File("./drivers.txt");
				scFile = new Scanner (drivers);	
				//reading each line and sorting the data
				/*While loop executes whilst the scFile has another line. 
				 * another scanner initialised to 'scan' the individual line
				 * the "," that separates the text entries are used to separate the data
				 * variable are initialised to accept each data value
				 * On each data entry the whitespace are removed in an effort to clean the data
				 * On the integer variable the deliveryLoad this is important as it is parsed to the integer value.*/
				while (scFile.hasNextLine()) {
					String driverDetails = scFile.nextLine();
					scLine = new Scanner(driverDetails);
					scLine.useDelimiter(",");
					String driverName, driverCity;
					int deliveryLoad;
					driverName = scLine.next().replaceAll("\\s+","");
					driverCity = scLine.next().toLowerCase().replaceAll("\\s+","");
					deliveryLoad = Integer.parseInt(scLine.next().replaceAll("\\s+",""));
					//switch statement using the driverCity variable.
					/*Switch statement is used to due to the repetive logic being used on the same variable.
					 * each respective case will add the appropriate data to the corresponding geographic location.
					 * if a hashMap doesn't exist for the driver entry the user is told that the location is not served.*/
					switch (driverCity) {
						case "capetown":
							capeTown.put(driverName, deliveryLoad);
							break;
						case "johannesburg":
							johannesburg.put(driverName, deliveryLoad);
							break;
						case "durban":
							durban.put(driverName, deliveryLoad);
							break;
						case "potchefstroom":
							potchefstroom.put(driverName, deliveryLoad);
							break;
						case "springbok":
							springbok.put(driverName, deliveryLoad);
							break;
						case "bloemfontein":
							bloemfontein.put(driverName, deliveryLoad);
							break;
						case "witbank":
							witbank.put(driverName, deliveryLoad);
							break;
						case "portelizabeth":
							portElizabeth.put(driverName, deliveryLoad);
							break;
						default:
							System.out.printf("drivers location is not yet served -- %s", driverCity);
					}	
				}
			/*
			 * If an error occurs with accessing the drivers.txt file an error is printed to the console
			 * In the finally block the scanner objects used to access the file and each line is closed.*/
			} catch (FileNotFoundException e) {
				System.out.println("Error in reading the driver's file");
				System.out.println("");
				System.out.println(e.getMessage());
				System.out.println("");
				e.printStackTrace();
			} finally {
				scLine.close();	
				scFile.close();
			}
			//new
			//access hashmaps with switch based on users city.
			/*Another switch statement is used due to the similar logic and persistent variable used of city.
			 * minValue, lowLoadDriver & driverCity variable are initialised outside of the switch scope as to be used later in the display section.
			 * Each case has the exact same logic so it will only be explained once in detail - the only difference is the hashmaps accessed and the named iterators.
			 * The iterator is set to access the geographically corresponding HashMap i.e cape town = capeTown.
			 * a while loop executes whilst there are entries in the hashmap. 
			 * each value of an entry is checked with an if conditional if it is less than the initial minValue. 
			 * If true;
			 * the minValue is then set to the current entry minValue & the lowLoadDriver is set to the Key of the value*/
			//minValue is set to the max value in the whole data set (as an upper limit load).
			int minValue = 15;
			
			String driverCity = "";
			switch (city) {
			case "cape town":
					Iterator<Entry<String, Integer>> iDrivercpt = capeTown.entrySet().iterator();
					while (iDrivercpt.hasNext()) {
					  Entry<String, Integer> driverLine = iDrivercpt.next();
					  if (driverLine.getValue() < minValue) {
						minValue = driverLine.getValue();
						lowLoadDriver = driverLine.getKey();
						assignedDrivers.add(lowLoadDriver);
						driverCity = "cape town";
					}			 
				  }
				break;
			case "johannesburg":
				Iterator<Entry<String, Integer>> iDriverjhb = johannesburg.entrySet().iterator();
				while (iDriverjhb.hasNext()) {
				  Entry<String, Integer> driverLine = iDriverjhb.next();
				  if (driverLine.getValue() < minValue) {
					minValue = driverLine.getValue();
					lowLoadDriver = driverLine.getKey();
					assignedDrivers.add(lowLoadDriver);
					driverCity = "johannesburg";
				}			 
			  }
				break;
			case "durban":
				Iterator<Entry<String, Integer>> iDriverdb = durban.entrySet().iterator();
				while (iDriverdb.hasNext()) {
				  Entry<String, Integer> driverLine = iDriverdb.next();
				  if (driverLine.getValue() < minValue) {
					minValue = driverLine.getValue();
					lowLoadDriver = driverLine.getKey();
					assignedDrivers.add(lowLoadDriver);
					driverCity = "durban";
				}			 
			  }
				break;
			case "potchefstroom":
				Iterator<Entry<String, Integer>> iDriverphs = potchefstroom.entrySet().iterator();
				while (iDriverphs.hasNext()) {
				  Entry<String, Integer> driverLine = iDriverphs.next();
				  if (driverLine.getValue() < minValue) {
					minValue = driverLine.getValue();
					lowLoadDriver = driverLine.getKey();
					assignedDrivers.add(lowLoadDriver);
					driverCity = "potchefstroom";
				}			 
			  }
				break;
			case "springbok":
				Iterator<Entry<String, Integer>> iDriversb = springbok.entrySet().iterator();
				while (iDriversb.hasNext()) {
				  Entry<String, Integer> driverLine = iDriversb.next();
				  if (driverLine.getValue() < minValue) {
					minValue = driverLine.getValue();
					lowLoadDriver = driverLine.getKey();
					assignedDrivers.add(lowLoadDriver);
					driverCity = "springbok";
				}			 
			  }
				break;
			case "bloemfontein":
				Iterator<Entry<String, Integer>> iDriverbf = bloemfontein.entrySet().iterator();
				while (iDriverbf.hasNext()) {
				  Entry<String, Integer> driverLine = iDriverbf.next();
				  if (driverLine.getValue() < minValue) {
					minValue = driverLine.getValue();
					lowLoadDriver = driverLine.getKey();
					assignedDrivers.add(lowLoadDriver);
					driverCity = "bloemfontein";
				}			 
			  }
				break;
			case "witbank":
				Iterator<Entry<String, Integer>> iDriverwb = witbank.entrySet().iterator();
				while (iDriverwb.hasNext()) {
				  Entry<String, Integer> driverLine = iDriverwb.next();
				  if (driverLine.getValue() < minValue) {
					minValue = driverLine.getValue();
					lowLoadDriver = driverLine.getKey();
					assignedDrivers.add(lowLoadDriver);
					driverCity = "witbank";
				}			 
			  }
				break;
			case "port elizabeth":
				Iterator<Entry<String, Integer>> iDriverpe = portElizabeth.entrySet().iterator();
				while (iDriverpe.hasNext()) {
				  Entry<String, Integer> driverLine = iDriverpe.next();
				  if (driverLine.getValue() < minValue) {
					minValue = driverLine.getValue();
					lowLoadDriver = driverLine.getKey();
					assignedDrivers.add(lowLoadDriver);
					driverCity = "port elizabeth";
				}			 
			  }
				break;
				/*The default case is used if the customer lives in a location that does not match any drivers location.*/
			default:
				//creating file 
				File nodriver = new File("invoice.txt");
				//creating file writer
				FileWriter writer;
				PrintWriter printw = null;
				//try & catch block used if an error occurs in this section - for ease of troubleshooting.
				try {
				/*FileWriter and PrintWriter initialised to write to the invoice.txt*/
					writer = new FileWriter(nodriver);
					printw = new PrintWriter(writer);
					printw.println("Sorry! Our drivers are too far away from you to be able to deliver to your location.");
					
				} catch (IOException e) {
					System.out.println("");
					System.out.println(e.getMessage());
					System.out.println("");
					e.printStackTrace();
				//finally block added and the printwriter is closed within it.
				} finally {
					printw.close();
				}
				break;
			}
			//display section
			/*orderNumber converted to string
			 * invoiceName initialised as the invoice string with the appended order number  - so that each individual customers invoice is correctly generated.*/
			String parsedOrderNumber = Integer.toString(orderNumber);
			String invoiceName = "invoice-" + parsedOrderNumber + ".txt";
			//creating file
			File driverFound = new File(invoiceName);
			FileWriter foundWriter;
			PrintWriter printIt = null;
			/*
			 * Try and catch block used to help with troubleshooting if there's a problem writing to the invoice file.
			 * FileWriter & PrintWriter initialised.
			 * printf used for ease of format and concise argument statement
			 * All attributes of the customer class & restaurant class is retrieved using their respective get methods;
			 * Where there is an integer variable it is then parsed to String, so that it could be displayed
			 * The layout is managed using the \n for linebreaks and spacing.
			 * When accessing the order details i.e price and amount ordered an iterator and while loop is used.
			 * Their respective index positions in the ArrayList is called and printed for each entry.*/
			try {
			foundWriter = new FileWriter(driverFound);
			printIt = new PrintWriter(foundWriter);
			printIt.printf("Order number %s \n", Integer.toString(orderCustomer.getOrderNumber()));
			printIt.printf("Customer: %s \n", orderCustomer.getCustomerName());
			printIt.printf("Phone number: %s \n", orderCustomer.getContactNum());
			printIt.printf("Location: %s \n",orderCustomer.getCity());
			printIt.println("\n");
			printIt.printf("You have ordered the following from %s in %s: \n", orderRestaurant.getRestaurantName(), orderRestaurant.getCityRestaurant());
			/**/
			Iterator<Entry<String, ArrayList<Integer>>> printOrder = order.entrySet().iterator();
			 while (printOrder.hasNext()) {
				  Entry<String, ArrayList<Integer>> orderItem = printOrder.next();
			printIt.printf("\n %s x %s R%S.00 \n",Integer.toString(orderItem.getValue().get(1)), orderItem.getKey(), Integer.toString(orderItem.getValue().get(0)) );
			 
			 }
			printIt.printf("\nSpecial instructions: %s \n", orderRestaurant.getSpecialPrep());
			printIt.printf("\nTotal: %s.00\n", Integer.toString(orderRestaurant.getTotal()));
			printIt.printf("\n%s is nearest to the restaurant and so he will be delivering your order to you at: \n", lowLoadDriver);
			printIt.printf("\n%s %s\n", Integer.toString(orderCustomer.getStreetNum()), orderCustomer.getStreetName());
			printIt.printf("%s\n", orderCustomer.getSuburb());
			printIt.printf("\nIf you need to contact the restaurant, their number is %s.", orderRestaurant.getContactNum());
			} catch (IOException e) {
				System.out.println("There was an error in writing to the invoice.txt file");
				System.out.println("");
				System.out.println(e.getMessage());
				System.out.println("");
				e.printStackTrace();
			//printwriter is closed in the finally block.
			} finally {
				printIt.close();
			}
			//user prompted if they would like to add another customer order.
			System.out.println("Would you like to add another customer? (Y - YES : N - NO): ");
			addCustomer = customerInfo.nextLine().toUpperCase().charAt(0) == 'Y';
			
		} while (addCustomer);	
		//output text file with the list of customers names & their order numbers alphabetically
		/*The logic explained for the alpabetical sort of the customers is duplicated in the location grouping, bar variable name changes - hence the location section will only have a sparse explanation.
		 * Logic conditional is used to check if the file customersAlphabetical.txt exists/ if not the else logic will execute. 
		 * File, Scanner, FileWriter and Printwriter objects are intialised outside the try catch block, so that it could be closed within the finally block.
		 * Variables used within the try block are initialised outside.
		 * TRY:
		 * All objects are assigned their values
		 * While loop is used with a conditional that will execute whilst there is still a line within the txt file being accessed.
		 * Scanner alphaline is initialised and assigned the delimiter of ","
		 * The variables are then assigned their values.
		 * Regex is used to remove the whitespace from the rawOrderNumm variable and the variable is then parsed from String to Integer, value is assigned to corresOrderNum.
		 * Both variables are then added to the customerAlphabetical treeMap.
		 * An iterator is used to loop over the treeMap and then to print each entry to the temporary file
		 * FINALLY:
		 * All open scanners and 'writers' are closed, the original file is then deleted, whilst the temp file is renamed to the originals name.
		 * ELSE:
		 * File object customerAlpha created
		 * FileWriter alpabeticalCustomer initialised outside of the try catch block
		 * PrintWriter initialised outside of the try catch block
		 * These initialisations have been done so that they could be closed in the finally block
		 * TRY block: 
		 * FileWriterObject set to the file object
		 * PrinterWriter object set to the filewriter object
		 * Entry Iterator declared using the customerAlpabetical treeMap
		 * While loop used to execute the logic if there is still a next entry in the treeMap
		 * each entry is declared as the nextCustomerEntry on each loop
		 * using the printwriter object the customer name and customer order number is printed to the file
		 * As explained at the declaration of the treeMap the set Key will already be in alphabetical order, as that is the natural order of a String type in a tree map.
		 * CATCH block:
		 * A unique error message is printed to the console for trouble shooting
		 * the .message is then printed as well as the StackTrace to ensure the relevant error information is displayed.
		 * FINALLY block:
		 * In the finally block the printWriter is closed.*/
		File dirCheck =  new File("customersAlphabetical.txt");
		if (dirCheck.isFile()) {
			File alphaTemp = null;
			Scanner alphaScan = null;
			Scanner alphaLine = null;
			FileWriter alphaExists;
			PrintWriter updateAlpha = null;
			String existingCustomer, rawOrderNum;
			int  corresOrderNum;
			try {
				alphaTemp = new File("alphaTemp.txt");
				alphaScan = new Scanner(dirCheck);
				alphaExists = new FileWriter(alphaTemp);
				updateAlpha = new PrintWriter(alphaExists);
				while (alphaScan.hasNext()) {
					alphaLine = new Scanner(alphaScan.nextLine());
					alphaLine.useDelimiter("[,]");
					existingCustomer = alphaLine.next();
					rawOrderNum = alphaLine.next();
					corresOrderNum = Integer.parseInt(rawOrderNum.replaceAll("\\s+",""));
					customerAlphabetical.put(existingCustomer, corresOrderNum);
				}
				Iterator<Entry<String, Integer>> printAlphaOrder = customerAlphabetical.entrySet().iterator();
				while(printAlphaOrder.hasNext()) {
					Entry<String, Integer> nextCustomerEntry = printAlphaOrder.next();
					updateAlpha.printf("%s, %s \n", nextCustomerEntry.getKey(), nextCustomerEntry.getValue());
				}
				
				
			} catch (IOException e) {
				System.out.println("There was an error in writing to the customersAlpabetical.txt file");
				System.out.println("");
				System.out.println(e.getMessage());
				System.out.println("");
				e.printStackTrace();
			} finally {
				alphaScan.close();
				updateAlpha.close();
				dirCheck.delete();
				File changedAlpha = new File("customersAlphabetical.txt");
				alphaTemp.renameTo(changedAlpha);
			}
			
		} else {
			File customerAlpha = new File("customersAlphabetical.txt");
			FileWriter alpabeticalCustomer;
			PrintWriter printAlpha = null;
			try {
				alpabeticalCustomer = new FileWriter(customerAlpha);
				printAlpha = new PrintWriter(alpabeticalCustomer);
				Iterator<Entry<String, Integer>> printAlphaOrder = customerAlphabetical.entrySet().iterator();
				while(printAlphaOrder.hasNext()) {
					Entry<String, Integer> nextCustomerEntry = printAlphaOrder.next();
					printAlpha.printf("%s, %s \n", nextCustomerEntry.getKey(), nextCustomerEntry.getValue());
				}
				
			} catch (IOException e) {
				System.out.println("There was an error in writing to the customersAlpabetical.txt file");
				System.out.println("");
				System.out.println(e.getMessage());
				System.out.println("");
				e.printStackTrace();
			} finally {
				printAlpha.close();
			}
		}

		//output text file with the list of customers names grouped by location
		File dirLocationCheck = new File("customersLocation.txt");
		
	if (dirLocationCheck.isFile()) {
		File locationTemp = null;
		Scanner locationScan = null;
		Scanner locationLine = null;
		FileWriter locationExists;
		PrintWriter updateLocation = null;
		String readCustomer, readLocation;
		try {
			locationTemp = new File ("tempLocationData.txt");
			locationScan = new Scanner(dirLocationCheck);
			locationExists = new FileWriter(locationTemp);
			updateLocation = new PrintWriter(locationExists);
			while(locationScan.hasNext()) {
				locationLine = new Scanner (locationScan.nextLine());
				locationLine.useDelimiter("[,]");
				readLocation = locationLine.next();
				readCustomer = locationLine.next();
				customerLocation.put(readLocation, readCustomer);
			}
			Iterator<Entry<String, String>> printLocationOrder = customerLocation.entrySet().iterator();
			while(printLocationOrder.hasNext()) {
				Entry<String, String> nextLocationEntry = printLocationOrder.next();
				updateLocation.printf("%s, %s \n", nextLocationEntry.getKey(), nextLocationEntry.getValue());
			}
				
		} catch (IOException e) {
			System.out.println("There was an error in writing to the customersLocation.txt file");
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println("");
			e.printStackTrace();
		} finally {
			locationScan.close();
			updateLocation.close();
			dirLocationCheck.delete();
			File changedLoca = new File("customersLocation.txt");
			locationTemp.renameTo(changedLoca);
		}
		
	} else {
		File customerLocationGroup = new File("customersLocation.txt");
		FileWriter locationCustomer;
		PrintWriter printLocation = null;
		try {
			locationCustomer = new FileWriter(customerLocationGroup);
			printLocation = new PrintWriter(locationCustomer);
			Iterator<Entry<String, String>> printLocationOrder = customerLocation.entrySet().iterator();
			while(printLocationOrder.hasNext()) {
				Entry<String, String> nextLocationEntry = printLocationOrder.next();
				printLocation.printf("%s, %s \n", nextLocationEntry.getKey(), nextLocationEntry.getValue());
			}
			
		} catch (IOException e) {
			System.out.println("There was an error in writing to the customersLocation.txt file");
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println("");
			e.printStackTrace();
		} finally {
			printLocation.close();
		}
	}
		//update original drivers.txt with delivery load changes.
		/*For readability the comments on this section have been spread around logic blocks
		 * Objects and variables declared outside of the try catch block.*/
		//create temporary file 
		File tempFile = null;
		File originalFile = null;
		//scanner object initialised 
		Scanner scEditFile = null;
		Scanner scEditLine = null;
		//FileWriter &  PrintWriter initialised
		FileWriter fwEditDriverRecords;
		PrintWriter printDriverRecords = null;
		//variables initialised
		String editCheckDriverName;
		String originalDriverName, originalDriverLocation, originalDeliveryLoad;
		int editCheckDeliveryLoad;
		/*TRYBLOCK:
		 * tempFile object declared as desired path
		 * orginalFile object declared as the original driver file path
		 * Scanner object declared as the originalFile object
		 * FileWriter object declared as the temporary file object
		 * PrintWriter object declared on the FileWriter
		 * While loop is executed whilst the drivers file has lines of entries
		 * Scanner declared for each line
		 * Delimiter declared as a ,
		 * Variables corresponding with the orignal driver file values as declared
		 * Edited values are declared for comparison using regex for both and the Deliveryload variable is parsed to integer for calculation
		 *  if conditional is used to check the arrayList assignedDrivers for the edited variable driver name
		 *  IF TRUE
		 *  the editCheckDeliveryLoad is incremented to reflect the assignment of a delivery to the driver
		 *  using the printwriter object, the line is printed with the drivers name, location and then the updated load
		 *  IF false (else) 
		 *  using the printwriter object, the line is printed to reflect the drivers name, location and unchanged load*/
		try {
			tempFile = new File("tempFile.txt");
			originalFile = new File("./drivers.txt");
			scEditFile = new Scanner(originalFile);
			fwEditDriverRecords = new FileWriter (tempFile);
			printDriverRecords = new PrintWriter(fwEditDriverRecords);
			
			while(scEditFile.hasNext()) {
				scEditLine = new Scanner(scEditFile.nextLine());
				scEditLine.useDelimiter("[,]");
				originalDriverName = scEditLine.next();
				originalDriverLocation = scEditLine.next();
				originalDeliveryLoad = scEditLine.next();
				editCheckDriverName = originalDriverName.replaceAll("\\s+","");
				editCheckDeliveryLoad = Integer.parseInt(originalDeliveryLoad.replaceAll("\\s+",""));
				
				if (assignedDrivers.contains(editCheckDriverName)) {
					editCheckDeliveryLoad++;
					printDriverRecords.printf("%s,%s, %s\n", originalDriverName, originalDriverLocation, editCheckDeliveryLoad);
				} else {
					printDriverRecords.printf("%s,%s,%s\n", originalDriverName, originalDriverLocation, originalDeliveryLoad);
				}
			}
		/*CATCH:
		 * If an error occurred a custom error message is printed to the console,
		 * as well as the generated error message and error Stack*/
		} catch (IOException e) {
			System.out.println("There was an error in writing to the drivers.txt file");
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println("");
			e.printStackTrace();
		/*FINALLY:
		 * Both scanner objects are closed
		 * printWriter is also closed
		 * original driver.txt file is deleted
		 * file created at the original driver.txt location
		 * tempFile object is renamed to reflect that location*/
		} finally {
			scEditFile.close();
			scEditLine.close();
			printDriverRecords.close();
			originalFile.delete();
			File changedFile = new File("./drivers.txt");
			tempFile.renameTo(changedFile);
		}
	}

}
