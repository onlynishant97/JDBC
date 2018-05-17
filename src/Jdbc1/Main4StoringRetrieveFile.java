package Jdbc1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main4StoringRetrieveFile {
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","dell");
		Statement st=con.createStatement();
		//int tablesCreated=st.executeUpdate("create table fileStore2(name varchar2(10),filestore clob)");
		PreparedStatement ps=con.prepareStatement("insert into fileStore2 values(?,?)");
		ps.setString(1,"nishant");
		File file=new File("E:\\Eclipse\\Workspace\\JDBC\\files\\Views.txt");
		FileReader fr=new FileReader(file);
		ps.setCharacterStream(2,fr,(int)file.length());
		ps.executeUpdate();
		ps=con.prepareStatement("select * from fileStore");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString(1));
			Clob c=rs.getClob(2);
			Reader r=c.getCharacterStream();
			FileWriter fw=new FileWriter("E:\\Eclipse\\Workspace\\JDBC\\files\\ViewsRetrieved.txt");
			int i;
			while((i=r.read())!=-1) {
				fw.write((char)i);
			}
			fw.close();
		}
		con.close();
	}
}
