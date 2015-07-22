package com.jorgefigueiredo.demos.java_jdbc;

import java.sql.*;

public class App 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/employees";
	
	static final String USER = "root";
	static final String PASS = "";
	
    public static void main( String[] args )
    {
    	
    	Connection conn = null;
    	Statement stmt = null;
    	
    	try {
    		
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		conn = DriverManager.getConnection(DB_URL, USER, PASS);
    		
    		stmt = conn.createStatement();
    		
    		String sql = "SELECT * FROM employees LIMIT 5 OFFSET 5;";
    		
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()) {
    			
    			int id = rs.getInt("emp_no");
    			
    			System.out.println(id);
    			
    		}
    		
    		rs.close();
    		conn.close();
    		stmt.close();
    	}
    	catch (Exception ex) {
    	
    		System.out.println(ex.getMessage());
    		
    	}
    	
    }
}
