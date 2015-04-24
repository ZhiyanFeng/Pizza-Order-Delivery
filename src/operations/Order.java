package operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import source.Menus;
import classes.SQLController;

/**
 * this class deal with ordering
 * @author yan
 *
 */
public class Order extends operations {
//	private List<String> PizzaOrder = new ArrayList<String>();
	private String[] Drink = new String[2];
	private boolean pizzaConfirm;
	private boolean orderConfirm;
	private boolean sizeConfirm;
	private getId gi;
	private String table;
	private String Topping;
	private String info;
	private String cid;
	private Insertion ins;
	private int orderNum;
	private int orderId;
	private int pid;
	private int PizzaType;
	private int size;
	private int orderPizzaId;
	private int toppingId;
	private int drinkId;
	private int toppingNum;
	private int drinkNum;
	private double price;
	
	
	public Order(){}
	
	public Order(SQLController sqlMngr, Scanner sc){
		super(sqlMngr,sc);
		gi = new getId(sqlMngr,sc);
		doc = "deal with ordering";
	}
	
	public void placeOrder(){
		ins = new Insertion(sqlMngr, sc);
		
		if (sc != null && sqlMngr != null) {
			System.out.println("");
			System.out.println("***************************");

			String item = "";
			int choice = -1;
			do{
				Menus.startMenu();
				item = sc.nextLine();
				try {
					choice = Integer.parseInt(item);
					System.out.println("please enter customer id:");
					cid = sc.nextLine();
					switch (choice) { //Activate the desired functionality
					case 1:
						//create a new order in Ordered table here
						//and save the orderId
						info = cid + "," + "\""+"p" + "\"" + ",curtime()"+ ",now()";
						table = "Ordered(cid,pkOrdy,orderTime,orderDate)";
						ins.insert(table, info);
						orderId = gi.getTableId("orderId","Ordered");
						this.loadMenu();
						break;
					case 2:
						//create a new order in Ordered table
						//and save the orderId
						info = cid + "," + "\""+"d" + "\"" + ",now()" + ",now()";
						table = "Ordered(cid,pkOrdy, orderTime,orderDate)";
						ins.insert(table, info);
						orderId = gi.getTableId("orderId","Ordered");
						this.loadMenu();
						break;
					}
				}catch (NumberFormatException e) {
					item = "-1";
				}
			}while (item.compareTo("1") != 0 && item.compareTo("2") !=0 
					&& item.compareTo("0") !=0);
		}
//		print the oreder bill
		
		
	}
	
	
	private void loadMenu(){
		System.out.println("");
		System.out.println("***************************");

		String item = "";
		int choice = -1;
		do {
			Menus.Menu(); //Print Menu
			item = sc.nextLine();
			try {
				choice = Integer.parseInt(item);
				switch (choice) { //Activate the desired functionality
				case 1:{
					this.loadPizzaMenu();
					if(!pizzaConfirm){
						break;
					}
					else{
						this.loadSizeMenu();
						if(!sizeConfirm){
							break;
						}else{
							//insert a piazza record into OrderedPizza
							table= "Pizza(orderId,pid)";
							info = Integer.toString(orderId)
									+ "," + Integer.toString(pid);
							ins.insert(table, info);
							orderPizzaId = gi.getTableId("indexID", "Pizza");
							this.loadToppingMenu();
							break;
						}
					}
				}
				case 2:
					this.loadDrinkMenu();
					break;
				}
			}catch (NumberFormatException e) {
				item = "-1";
			}
		} while (item.compareTo("0") != 0);
	}
	

	private void loadSizeMenu(){
		System.out.println("");
		System.out.println("***************************");
		
		String item = "";
		int choice = -1;
		do {
			Menus.SizeMenu(); //Print Menu
			item = sc.nextLine();
			try {
				choice = Integer.parseInt(item);
				switch (choice) { //Activate the desired functionality
				case 1:{
					if(PizzaType == 1){
						pid = 11;
						}else{
							pid = 14;
						}
					sizeConfirm = true;
					break;
				}
				case 2:{
					if(PizzaType == 1){
						pid = 12;
						}else{
							pid = 15;
						}
					sizeConfirm = true;
					break;
				}
				case 3:{
					if(PizzaType == 1){
						pid = 13;
						}else{
							pid = 16;
						}
					sizeConfirm = true;
					break;
				}
				}
			}
			catch (NumberFormatException e) {
				item = "-1";
			}
		} while (item.compareTo("0") != 0 && item.compareTo("1") != 0
				&& item.compareTo("2") != 0 && item.compareTo("3") != 0);
	}
	
	private void loadPizzaMenu(){
		System.out.println("");
		System.out.println("***************************");

		String item = "";
		int choice = -1;
		do {
			Menus.PizzaMenu(); //Print Menu
			item = sc.nextLine();
			try {
				choice = Integer.parseInt(item);
				switch (choice) { //Activate the desired functionality
				case 1:{
					pizzaConfirm = true;
					break;
				}
				case 2:{
					pizzaConfirm = true;
					break;
				}
				}
			}catch (NumberFormatException e) {
				item = "-1";
			}
		} while (item.compareTo("1") != 0 && item.compareTo("2") != 0);
		}
		

	private void loadToppingMenu(){
//		table= "Pizza(pid,orderId,ToppingID)";
		table= "Topping(pizzaIndexID,pid,num)";
		System.out.println("");
		System.out.println("***************************");

		String item = "";
		int choice = -1;
		do {
			Menus.ToppingMenu(); //Print Menu
			item = sc.nextLine();
			try {
				choice = Integer.parseInt(item);
				switch (choice) { //Activate the desired functionality
				case 1:{
					toppingId = 7;
					System.out.println("please type in Number:");
					toppingNum = sc.nextInt();
					info = Integer.toString(orderPizzaId) 
							+ "," + Integer.toString(toppingId)
							+ "," + Integer.toString(toppingNum);
					ins.insert(table, info);
					break;
				}
				case 2:
					toppingId = 8;
					System.out.println("please type in Number:");
					toppingNum = sc.nextInt();
					info = Integer.toString(orderPizzaId) 
							+ "," + Integer.toString(toppingId)
							+ "," + Integer.toString(toppingNum);
					ins.insert(table, info);
					break;
				case 3:{
					toppingId = 9;
					System.out.println("please type in Number:");
					toppingNum = sc.nextInt();
					info = Integer.toString(orderPizzaId) 
							+ "," + Integer.toString(toppingId)
							+ "," + Integer.toString(toppingNum);
					ins.insert(table, info);
					break;
				}
				case 4:{
					toppingId = 10;
					System.out.println("please type in Number:");
					toppingNum = sc.nextInt();
					info = Integer.toString(orderPizzaId) 
							+ "," + Integer.toString(toppingId)
							+ "," + Integer.toString(toppingNum);
					ins.insert(table, info);
					break;
				}
				}
			}catch (NumberFormatException e) {
				item = "-1";
			}
		} while (item.compareTo("0") != 0);

	}
		
	
	private void loadDrinkMenu(){
		table= "Drink(orderID,pid,num)";
		
		System.out.println("");
		System.out.println("***************************");

		String item = "";
		int choice = -1;
		do {
			Menus.DrinkMenu(); //Print Menu
			item = sc.nextLine();
			try {
				choice = Integer.parseInt(item);
				switch (choice) { //Activate the desired functionality
				case 1:{

					drinkId = 1;
					System.out.println("please type in Number:");
					drinkNum = sc.nextInt();
					info = Integer.toString(orderId)+ "," +
							Integer.toString(drinkId) 
							+ "," + Integer.toString(drinkNum);
					ins.insert(table, info);
					break;
				}
				case 2:{
					drinkId = 2;
					System.out.println("please type in Number:");
					drinkNum = sc.nextInt();
					info = Integer.toString(orderId)
							+ "," + Integer.toString(drinkId) 
							+ "," + Integer.toString(drinkNum);
					ins.insert(table, info);
					break;
				}
				case 3:{
					drinkId = 3;
					System.out.println("please type in Number:");
					drinkNum = sc.nextInt();
					info = Integer.toString(orderId)
							+ "," + Integer.toString(drinkId) 
							+ "," + Integer.toString(drinkNum);
					ins.insert(table, info);
					break;
				}
				case 4:{
					drinkId = 4;
					System.out.println("please type in Number:");
					drinkNum = sc.nextInt();
					info = Integer.toString(orderId)
							+ "," + Integer.toString(drinkId) 
							+ "," + Integer.toString(drinkNum);
					ins.insert(table, info);
					break;
				}
				case 5:{
					drinkId = 5;
					System.out.println("please type in Number:");
					drinkNum = sc.nextInt();
					info = Integer.toString(orderId)
							+ "," + Integer.toString(drinkId) 
							+ "," + Integer.toString(drinkNum);
					ins.insert(table, info);
					break;
				}
				case 6:{
					drinkId = 6;
					System.out.println("please type in Number:");
					drinkNum = sc.nextInt();
					info = Integer.toString(orderId)
							+ "," + Integer.toString(drinkId) 
							+ "," + Integer.toString(drinkNum);
					ins.insert(table, info);
					break;
				}
				}
			}catch (NumberFormatException e) {
				item = "-1";
			}
		} while (item.compareTo("0") != 0);
	}
	
}
	
	
	

