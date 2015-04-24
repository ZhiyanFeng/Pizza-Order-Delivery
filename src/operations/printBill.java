package operations;

import java.util.Scanner;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import classes.SQLController;

public class printBill extends operations {
	private String PizzaSql = "select PizzaID, name, size, price " +
			"from PizzaOrder where ORDER_NUM=";
	private String InfoSql= "select distinct first_Name, last_Name," +
			"addrId, orderDate, diliverTime, DiliveryPerson from OrderInformation where ORDER_NUM=";
	private String ToppingSql = "select name, num, price from OrderTopping where Pizza=";
	private String DrinkSql = "select indexID,name, size,num, Ordered.price as price from Ordered,DrinkOrder "
			+ "where ORDER_NUM=";
	private double total;


	public printBill(){}
	
	public printBill(SQLController sqlMngr, Scanner sc){
		super(sqlMngr, sc);
		doc = "this class print the bill detail";
	}

	
	public void printDetail(String orderNum){
		try {
			InfoSql = InfoSql + orderNum;
			ResultSet orderInfo = (sqlMngr.getSt()).executeQuery(InfoSql);
			while(orderInfo.next()){
				String firstName = orderInfo.getString("first_Name");
				String lastName = orderInfo.getString("last_Name");
				String addr = orderInfo.getString("addrId");
				Date dlvDate = orderInfo.getDate("orderDate");
				Time dlvTime = orderInfo.getTime("diliverTime");
				String diliveryPerson = orderInfo.getString("DiliveryPerson");
				//print them out on console
				System.out.println("Order Information:" + orderNum + "\t" + "Date:" + dlvDate);
				System.out.println("--------------------------------------");
				System.out.println("Name:" + firstName +" " + lastName);
				System.out.println("address:" + addr); 
				System.out.println("Dilivery:" + diliveryPerson + "		dilivery time: " + dlvTime);
			}
			
			System.out.println("");
			System.out.println("-------------------------------------------");
			String getPizzaInfo = PizzaSql + orderNum;
			ResultSet PizzaInfo = sqlMngr.getConn().createStatement().executeQuery(getPizzaInfo);
			while(PizzaInfo.next()){
				String Pizza = PizzaInfo.getString("PizzaID");
				String PizzaName = PizzaInfo.getString("name");
				String PizzaSize = PizzaInfo.getString("size");
				double PizzaPrice = PizzaInfo.getDouble("price");
				total +=PizzaPrice;
				//print them out

				System.out.println("Pizza:" + "\t"  + PizzaName + "\t" + "size:" + PizzaSize +
						"\t" + "price:" + PizzaPrice);
				
				//print topping
				String ToppingSqlnew = ToppingSql + Pizza;
				ResultSet ToppingInfo = (sqlMngr.getConn().createStatement()).executeQuery(ToppingSqlnew);
				while(ToppingInfo.next()){
					String ToppingName = ToppingInfo.getString("name");
					int ToppingNum = ToppingInfo.getInt("num");
					double ToppingPrice = ToppingInfo.getDouble("price");
					total += ToppingNum*ToppingPrice;
					System.out.println("\t"  + ToppingName + "\t" + "num:" + ToppingNum +
							"\t" + "price:" + ToppingNum*ToppingPrice);
				}
			}
			//print out drinks
			String getDrinkInfo = DrinkSql + orderNum;
			ResultSet DrinkInfo = (sqlMngr.getSt()).executeQuery(getDrinkInfo);
			while(DrinkInfo.next()){
				int ind = DrinkInfo.getInt("indexID");
				String name = DrinkInfo.getString("name");
				String vol = DrinkInfo.getString("size");
				int num = DrinkInfo.getInt("num");
				double price = DrinkInfo.getDouble("price");
				total += num*price;
				//print
				System.out.println("Drink:" + "\t"  + name + "\t" + "vol:" + vol +
						"\t" + "price:" + num*price);
				
			}
			System.out.println("");
			System.out.println("-----------------------------------------------");
			System.out.println("\t\t\ttotal:" + total);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
