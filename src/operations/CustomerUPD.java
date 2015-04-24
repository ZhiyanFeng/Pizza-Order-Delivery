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
public class CustomerUPD extends operations {
	
	public CustomerUPD(){}
	
	public CustomerUPD(SQLController sqlMngr,Scanner sc){
		super(sqlMngr,sc);
		doc = "update the customer information";
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
						
						this.updateBalance();
						break;
					case 2:
						this.updateAddress();
						break;
					case 3:
						this.updateAge();;
						break;
					case 4:
						this.updateGender();;
						break;
//					case 5:
//						this.updateAge();
//						break;
//					case 6:
//						this.updateGender();
//						break;
					}
				}catch (NumberFormatException e) {
					input = "-1";
				}
			} while (input.compareTo("0") != 0);
		}
	}
//		update balance 
	public void updateBalance(){
		System.out.println("enter Costomer Id:");
		String cid = sc.nextLine();
		System.out.println("enter deposit:");
		String deposit = sc.nextLine();
		String acctId = getAcctId(cid);
		sql = "update Account set balance=" + deposit
				+ " where acctId =" + acctId;
		try{
			(sqlMngr.getSt()).executeUpdate(sql);		
		}catch(SQLException e){
			System.out.println("error on updateBalance");
		}
	}
	
	public void updateAddress(){
		System.out.println("enter Costomer Id:");
		String cid = sc.nextLine();
		System.out.println("enter address:");
		String address = sc.nextLine();
//		String customerId = getAcctId(cid);
		sql = "update Customers set addrId=" + address
				+ " where cid =" + cid;
		try{
			(sqlMngr.getSt()).executeUpdate(sql);		
		}catch(SQLException e){
			System.out.println("error on address");
		}
	}

	public void updateGender(){
		System.out.println("enter Costomer Id:");
		String cid = sc.nextLine();
		System.out.println("enter gender:");
		String gender = sc.nextLine();
		sql = "update Customers set gender=" + "\"" + gender + "\""
				+ " where cid =" + cid;
		try{
			(sqlMngr.getSt()).executeUpdate(sql);		
		}catch(SQLException e){
			System.out.println("error on updateDistance");
		}
	}
	
	public void updateAge(){
		System.out.println("enter Costomer Id:");
		String cid = sc.nextLine();
		System.out.println("enter age:");
		String age = sc.nextLine();
		sql = "update Customers set age=" + age
				+ " where cid =" + cid;
		try{
			(sqlMngr.getSt()).executeUpdate(sql);		
		}catch(SQLException e){
			System.out.println("error on updateDistance");
		}
	}
//	update distance
	public void updateDistance(){
		System.out.println("enter Costomer Id:");
		String cid = sc.nextLine();
		System.out.println("enter distance:");
		String dist = sc.nextLine();
		sql = "update Customers set dist=" + dist
				+ " where cid =" + cid;
		try{
			(sqlMngr.getSt()).executeUpdate(sql);		
		}catch(SQLException e){
			System.out.println("error on updateDistance");
		}
	}
	private static void menu() {
		System.out.println("=========UPDATE=========");
		System.out.println("0. Exit.");
		System.out.println("1. update Account.");
		System.out.println("2. update address");
//		System.out.println("3. activate.");
//		System.out.println("4. deactivate.");
		System.out.println("3. update age.");
		System.out.println("4. update gender.");

		System.out.print("Choose one of the previous options [0-6]: ");
	}
	
	public void activate(){
		System.out.println("enter Costomer Id:");
		String cid = sc.nextLine();
		sql = "update Customers set active_status='a' where cid=" + cid;
		try{
			(sqlMngr.getSt()).executeUpdate(sql);		
		}catch(SQLException e){
			System.out.println("error on activate");
		}
	}
	
	public void deactivate(){
		System.out.println("enter Costomer Id:");
		String cid = sc.nextLine();
		sql = "update Customers set active_status='d' where cid=" + cid;
		try{
			(sqlMngr.getSt()).executeUpdate(sql);		
		}catch(SQLException e){
			System.out.println("error on deactivate");
		}
	}
	public String getAcctId(String cid){
		sql = "select acctId from Customers where cid=" + cid;
		String s="";
		try {
			rs = (sqlMngr.getSt()).executeQuery(sql);
			while(rs.next()){
				s = rs.getString("acctId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error on get acctId from Customers");;
		}
		return s;
	}
			
	
}
