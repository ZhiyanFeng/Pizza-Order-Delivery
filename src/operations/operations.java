package operations;

import java.sql.ResultSet;
import java.util.Scanner;

import classes.SQLController;

/**
 * Super class operations.
 * 
 * Super class for all operations classes.
 * 
 * @author Yan
 */
public class operations {
	protected String doc;
	protected SQLController sqlMngr;
	protected String sql;
	protected ResultSet rs;
	protected Scanner sc;
//	default constructor
	
	public operations(){}
	
	public operations(SQLController sqlMngr, Scanner sc) {
		this.sqlMngr = sqlMngr;
		this.sc = sc;
	}


}

