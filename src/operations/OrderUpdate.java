package operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

import classes.SQLController;
/**
 * this class help to update the customer table
 * @author yan
 *
 */
public class OrderUpdate extends operations {
	private double price;
	
	public OrderUpdate(){}
	
	public OrderUpdate(SQLController sqlMngr,Scanner sc){
		super(sqlMngr,sc);
		doc = "update the order information";
	}
	public void update(){

		if (sc != null && sqlMngr != null) {
			System.out.println("");
			System.out.println("***************************");

			String input = "";
			int choice = -1;
			do {
				menu(); //Print Menu
				input = sc.nextLine();
				try {
					choice = Integer.parseInt(input);
					switch (choice) { //Activate the desired functionality
					case 1:
						
						this.setupDilivery();
						break;
					case 2:
						this.deleteOrder();
					}
				}catch (NumberFormatException e) {
					input = "-1";
				}
			} while (input.compareTo("0") != 0);
		}
	}
	
	
	public void setupDilivery(){
		System.out.println("enter orderId:");
		String oid = sc.nextLine();
		System.out.println("enter diliveryPerson Id:");
		String eid = sc.nextLine();
		
		sql = "update Ordered set pkOrdy=\"dilivery\", eid=" + eid + " where orderId=" + oid;
		try{
			(sqlMngr.getSt()).executeUpdate(sql);		
		}catch(SQLException e){
			System.out.println("error on updateDistance");
		}
	}
	
	public void updateAccount(double[] accountAndPrice){
			String getBalance = "select balance from Account where acctId=" + accountAndPrice[1];
			
			try{
				double price = (sqlMngr.getSt()).executeUpdate(getBalance);
				price += accountAndPrice[1];
				sql = "update Account set balance=" + price + " where AccountId=" + accountAndPrice[0];
				(sqlMngr.getSt()).executeUpdate(sql);
			}catch(Exception e){
				System.out.println("update balance error");
				
			}
		}

	private double[] deleteOrder(){
		double[] accountAndPrice = new double[2];
		System.out.println("enter Order Id:");
		String oid = sc.nextLine();
		sql = "delete from Ordered where orderId=" + oid;
		String getAcctID = "select acctId, price from Ordered,Customers where Customers.cid=Ordered.cid";
		try{
			(sqlMngr.getSt()).executeUpdate(sql);
			ResultSet orderInfo = (sqlMngr.getSt()).executeQuery(getAcctID);
			while(orderInfo.next()){
				accountAndPrice[0] = orderInfo.getInt("acctId");
				accountAndPrice[1] = orderInfo.getDouble("price");
				return accountAndPrice;
			}
			
		}catch(SQLException e){
			System.out.println("error on delete order");
		}
		return accountAndPrice;
	}

	
	private static void menu() {
		System.out.println("=========UPDATE=========");
		System.out.println("0. Exit.");
		System.out.println("1. serupDilivery");
		System.out.println("2. delete order");

		System.out.print("Choose one of the previous options [0-2]: ");
	}
			
	
}
