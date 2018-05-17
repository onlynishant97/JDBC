package Jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main2 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "dell");
		Statement st=con.createStatement();
	/*	int tablesCreated=st.executeUpdate("create table temp(id number(10),name varchar2(20))");
		System.out.println(tablesCreated);*/
		PreparedStatement ps=con.prepareStatement("insert into temp values(?,?)");
		int records=3;
		int rowsInserted=0;
		while(records!=0) {
			records--;
			ps.setInt(1,101);
			ps.setString(2,"nishant");
			rowsInserted=ps.executeUpdate();
		}
	//	System.out.println(rowsInserted);
		ps=con.prepareStatement("select * from temp");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2));
		}
		con.close();
	}
}
