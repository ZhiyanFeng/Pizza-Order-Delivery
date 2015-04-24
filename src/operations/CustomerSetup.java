package operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.SQLController;

public class CustomerSetup extends operations {
	private static Insertion ins;
	private static List<String> info= new ArrayList<String>(5);
	private static String AccountInfo, CustomerInfo;
	
	public CustomerSetup(){
		
	}
	public CustomerSetup(SQLController sqlMngr, Scanner sc){
		super(sqlMngr, sc);
		doc = "insert customer info and check his balance and"
				+ "and calculate the distance of dilivery";
		
	}
	
	public void setup(){
		ins = new Insertion(sqlMngr,sc);
		int aid;
//		get initial information from console
		while(sc!=null && (info.size())!=7){
			System.out.println("you must enter each following field!");
			info = getInfo();
		}
		
		//get balance and payment method from info
		AccountInfo = info.get(3) + "," +  "\"" + info.get(4) + "\"";
		ins.insert("Account(balance, payment)",AccountInfo);
		//get aid from Account
		aid = getAccount();
		//get first name,last name and address id from info
		CustomerInfo = "\"" +info.get(0)+ "\"" +
				"," + "\"" + info.get(1) + "\"" + "," + "\"" + info.get(2)  
				+ "\"" + "," + info.get(5) + ","+ info.get(6) + ","  
				+ Integer.toString(aid);
		ins.insert("Customers(fname,lname,gender,addrId,age,acctId)",
				CustomerInfo);
		info = new ArrayList<String>(5);
		
	}
	
	public int getAccount(){
		int max=0;
		String sql = "select max(acctId) as max from Account";
		try {
			 ResultSet rs = (sqlMngr.getSt()).executeQuery(sql);
			 while(rs.next()){
				 max = rs.getInt("max");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("get max error, table account");
		}
		return max;
	}
	
	
	private List<String> getInfo() {
		System.out.print("First name: ");
		info.add(sc.nextLine());
		System.out.print("Last name: ");
		info.add(sc.nextLine());
		System.out.print("Gender: ");
		info.add(sc.nextLine());
		System.out.print("Balance: ");
		info.add(sc.nextLine());
		System.out.print("Payment method: ");
		info.add(sc.nextLine());
		System.out.print("Address");
		info.add(sc.nextLine());
		System.out.print("Age: ");
		info.add(sc.nextLine());
		return info;
	}

}
