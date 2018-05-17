package Jdbc1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main5CallableStatement {
	public static void main(String[] args) throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","dell"); 
		Statement st=con.createStatement();
		st.executeUpdate("create or replace procedure insertr(id in number,name in varchar2(20) is begin insert into user420 values(id,name))");
		st=con.createStatement();
		st.executeUpdate("create table user420(id number(10),name varchar2(20))");
		CallableStatement cs=con.prepareCall("{call insertr(?,?)}");
		cs.setString(2,"nishant");
		cs.setInt(1, 101);
		cs.execute();
		con.close();
		
	}
}
