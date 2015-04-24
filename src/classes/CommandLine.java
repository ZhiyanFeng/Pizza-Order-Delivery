package classes;

import operations.*;

import java.util.ArrayList;
import java.util.Scanner;

import source.Menus;

public class CommandLine {

    // 'sqlMngr' is the object which interacts directly with MySQL
	private SQLController sqlMngr = null;
    // 'sc' is needed in order to scan the inputs provided by the user
	private Scanner sc = null;

	
	//Public functions - CommandLine State Functions
	
    /* Function used for initializing an istance of current
     * class
     */
	public boolean startSession() {
		boolean success = true;
		if (sc == null) {
			sc = new Scanner(System.in);
		}
		if (sqlMngr == null) {
			sqlMngr = new SQLController();
		}
		try {
			success = sqlMngr.connect(this.getCredentials());
		} catch (ClassNotFoundException e) {
			success = false;
			System.err.println("Establishing connection triggered an exception!");
			e.printStackTrace();
			sc = null;
			sqlMngr = null;
		}
		return success;
	}
	
    /* Function that acts as destructor of an instance of this class.
     * Performs some housekeeping setting instance's private field
     * to null
     */
	public void endSession() {
		if (sqlMngr != null)
			sqlMngr.disconnect();
		if (sc != null) {
			sc.close();
		}
		sqlMngr = null;
		sc = null;
	}

    /* Function that executes an infinite loop and activates the respective 
     * functionality according to user's choice. At each time it also outputs
     * the menu of core functionalities supported from our application.
     */
	public boolean execute() {
		if (sc != null && sqlMngr != null) {
			System.out.println("");
			System.out.println("***************************");
			System.out.println("******ACCESS GRANTED*******");
			System.out.println("***************************");
			System.out.println("");
			
			String input = "";
			int choice = -1;
			do {
				Menus.OpMenu(); //Print Menu
				input = sc.nextLine();
				try {
					choice = Integer.parseInt(input);
					switch (choice) { //Activate the desired functionality
					case 1:
						this.insertOperator();
						break;
					case 2:
						this.selectOperator();
						break;
					case 3:
						this.printSchema();
						break;
					case 4:
						this.printColSchema();
						break;
					case 5:{
						CustomerSetup cs = new CustomerSetup(sqlMngr,sc);
						cs.setup();
						break;
					}
					case 6:
					{
						CustomerUPD upd = new CustomerUPD(sqlMngr,sc);
						upd.update();
						break;
					}
					case 7:
					{
						Order order = new Order(sqlMngr,sc);
						order.placeOrder();
						break;
					}
					case 8:
					{
						OrderUpdate ou = new OrderUpdate(sqlMngr, sc);
						ou.update();
						break;
					}
					case 9:
					{
						System.out.println("enter orderNumber:");
						String num = sc.nextLine();
						printBill bill = new printBill(sqlMngr,sc);
						bill.printDetail(num);;
						break;
					}
					
					default:
						break;
					}
				} catch (NumberFormatException e) {
					input = "-1";
				}
			} while (input.compareTo("0") != 0);
			
			return true;
		} else {
			System.out.println("");
			System.out.println("Connection could not been established! Bye!");
			System.out.println("");
			return false;
		}
	}
	
	//Private functions
	

	
    // Called during the initialization of an instance of the current class
    // in order to retrieve from the user the credentials with which our program
    // is going to establish a connection with MySQL
	private String[] getCredentials() {
		String[] cred = new String[3];
		System.out.print("Username: ");
		cred[0] = sc.nextLine();
		System.out.print("Password: ");
		cred[1] = sc.nextLine();
		System.out.print("Database: ");
		cred[2] = sc.nextLine();
		return cred;
	}
	
    // Function that handles the feature: "3. Print schema."
	private void printSchema() {
		ArrayList<String> schema = sqlMngr.getSchema();
		
		System.out.println("");
		System.out.println("------------");
		System.out.println("Total number of tables: " + schema.size());
		for (int i = 0; i < schema.size(); i++) {
			System.out.println("Table: " + schema.get(i));
		}
		System.out.println("------------");
		System.out.println("");
	}
	
    // Function that handles the feature: "4. Print table schema."
	private void printColSchema() {
		System.out.print("Table Name: ");
		String tableName = sc.nextLine();
		ArrayList<String> result = sqlMngr.colSchema(tableName);
		System.out.println("");
		System.out.println("------------");
		System.out.println("Total number of fields: " + result.size()/2);
		for (int i = 0; i < result.size(); i+=2) {
			System.out.println("-");
			System.out.println("Field Name: " + result.get(i));
			System.out.println("Field Type: " + result.get(i+1));
		}
		System.out.println("------------");
		System.out.println("");
	}
	
    // Function that handles the feature: "2. Select a record."
	private void selectOperator() {
		String query = "";
		System.out.print("Issue the Select Query: ");
		query = sc.nextLine();
		query.trim();
		if (query.substring(0, 6).compareToIgnoreCase("select") == 0)
			sqlMngr.selectOp(query);
		else
			System.err.println("No select statement provided!");
	}

    // Function that handles the feature: "1. Insert a record."
	private void insertOperator() {
		int rowsAff = 0;
		int counter = 0;
		String query = "";
		System.out.print("Table: ");
		String table = sc.nextLine();
		System.out.print("Comma Separated Columns: ");
		String cols = sc.nextLine();
		System.out.print("Comma Separated Values: ");
		String[] vals = sc.nextLine().split(",");
        //transform the user input into a valid SQL insert statement
		query = "INSERT INTO " + table + " (" + cols + ") VALUES("; 
		for (counter = 0; counter < vals.length - 1; counter++) {
			query = query.concat("'" + vals[counter] + "',");
		}
		query = query.concat("'" + vals[counter] + "');");
		System.out.println(query);
		rowsAff = sqlMngr.insertOp(query);
		System.out.println("");
		System.out.println("Rows affected: " + rowsAff);
		System.out.println("");
	}

}