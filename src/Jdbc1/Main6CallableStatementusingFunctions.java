package Jdbc1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Types;

public class Main6CallableStatementusingFunctions {
	public static void main(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","dell");  
		Statement st=con.createStatement();
		st.executeUpdate("create or replace function sum4(n1 in number,n2 in number) return number is temp number; begin temp :=n1+n2; return temp; end;");
		CallableStatement cs=con.prepareCall("{?=call sum4(?,?)}");
	    cs.setInt(2, 100);
	    cs.setInt(3, 110);
	    cs.registerOutParameter(1,Types.INTEGER);
	    cs.execute();
	    System.out.println(cs.getInt(1));
	}
}
