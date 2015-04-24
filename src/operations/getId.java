package operations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import classes.SQLController;

public class getId extends operations {
	public getId(){}
	public getId(SQLController sqlMngr,Scanner sc){
		super(sqlMngr, sc);
	}
	
	public int getTableId(String id, String table){
		int max=0;
		String sql = "select max(" +id +")" + "as max from " + table;
		try {
			 ResultSet rs = (sqlMngr.getSt()).executeQuery(sql);
			 while(rs.next()){
				 max = rs.getInt("max");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("get max error, table " + table);
		}
		return max;
	}
}
