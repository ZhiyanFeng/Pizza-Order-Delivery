package operations;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import classes.SQLController;

public class Insertion extends operations {
	
	public Insertion(){}
	
	public Insertion(SQLController sqlMngr, Scanner sc){
		super(sqlMngr,sc);
		doc = "insert a record to a table";
	}
	
	public void insert(String table, String value){
		sql = "insert into " + table + " value" + "("+ value + ")";
		try {
			Statement st = sqlMngr.getSt();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("insert error table:" + table);
		}
	}
	
}
