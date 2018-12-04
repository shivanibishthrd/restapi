package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.GetRequest.BaseClass;


public class TestsDao extends BaseClass{
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet resultSet = null;
    
	public static void intDB() {
		
		try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        }
        catch(ClassNotFoundException cnfex) {
            System.out.println("Problem in loading"
                    + " MS Access JDBC driver");
            cnfex.printStackTrace();
        }
		try {
			 
            String msAccessDBName = "C:\\Users\\bisht_s\\Documents\\APITesting.accdb";
            String dbURL = "jdbc:odbc:Driver="
                    + "{Microsoft Access Driver (*.mdb, *.accdb)};"
                    + "DBQ="
                    + msAccessDBName 
                    + ";DriverID=22;READONLY=true";
            connection = DriverManager.getConnection(dbURL);
            statement = connection.createStatement();          
	} catch(SQLException sqlex){
        sqlex.printStackTrace();
    }		
	}	
	
	
	public void writetoDatabase(String data) throws SQLException {
		String url=prop2.getProperty("insert");
		statement.executeUpdate(url+data+")");
		System.out.println("Inserted records into the table...");
	}
}
