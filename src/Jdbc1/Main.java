package Jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
Steps to Connect:-
Register the driver class
Creating connection
Creating statement
Executing queries
Closing connection
*/
public class Main {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
		//The forName() method of Class class is used to register the driver class. This method is used to dynamically load the driver class.
		} catch (ClassNotFoundException e) {
			System.out.println("Error in class.forName");
			e.printStackTrace();
		}
		
		Connection con=null; //its an interface
		try {
			con=DriverManager.getConnection("java:oracle:thin:@localhost:1521:xe","nishant","dell");
		//	The getConnection() method of DriverManager class is used to establish connection with the database.
		} catch (SQLException e) {
			System.out.println("error in getConnection");
			e.printStackTrace();
		}
		
		Statement st=null; 
		try {
			st=con.createStatement();
		//	The createStatement() method of Connection interface is used to create statement. The object of statement is responsible to execute queries with the database.
		} catch (SQLException e) {
			System.out.println("error in createStatement");
			e.printStackTrace();
		}
		
		int effect=0;
		try {
			effect=st.executeUpdate("create table emp1000(id number(10),name varchar2(40),age number(3))");
			System.out.println(effect);
		} catch (SQLException e1) {
			System.out.println("error in execUpdate");
			e1.printStackTrace();
		}
		
		int rowsInserted=0;
		try {
			rowsInserted=st.executeUpdate("insert into emp10 values(1,'nishant',20)");
			System.out.println("no. of rows inserted "+rowsInserted);
		} catch (SQLException e1) {
			System.out.println("error in insertion");
			e1.printStackTrace();
		}
		
		ResultSet rs=null;
		try {
			rs=st.executeQuery("select * from emp");
		//	The executeQuery() method of Statement interface is used to execute queries to the database. This method returns the object of ResultSet that can be used to get all the records of a table.
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println("error in executeQuey");
			e.printStackTrace();
		}
		
		try {
			con.close();
		//	By closing connection object statement and ResultSet will be closed automatically. The close() method of Connection interface is used to close the connection.
		} catch (SQLException e) {
			System.out.println("error in connectionClose");
			e.printStackTrace();
		}
		
	}
}
