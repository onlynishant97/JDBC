package Jdbc1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main3StoringRetrieveImage {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","dell");
		Statement st=con.createStatement();
	//	int tablesCreated=st.executeUpdate("create table imageStore(name varchar2(10),image blob)");
		/*PreparedStatement ps=con.prepareStatement("insert into imageStore values(?,?)");
		ps.setString(1,"nishant");
		
		FileInputStream fs=new FileInputStream("E:\\Eclipse\\Workspace\\JDBC\\Image\\car.jpg");
		ps.setBinaryStream(2, fs, fs.available());
		int rowsAffected=ps.executeUpdate();
		System.out.println(rowsAffected);
		
		FileInputStream fs1=new FileInputStream("E:\\Eclipse\\Workspace\\JDBC\\Image\\download.jpg");
		ps.setBinaryStream(2, fs1, fs1.available());
		int rowsAffected1=ps.executeUpdate();
		System.out.println(rowsAffected1);*/
		
		PreparedStatement ps=con.prepareStatement("select * from imageStore");
		ResultSet rs=ps.executeQuery();
		int counter=0;
		while(rs.next()) {
			Blob b=rs.getBlob(2);
			byte barr[]=b.getBytes(1,(int)b.length());
			String name="retrieved";
			name+=String.valueOf(counter);
			FileOutputStream fos=new FileOutputStream("E:\\Eclipse\\Workspace\\JDBC\\Image\\"+name+".jpg");
			System.out.println(name);
			fos.write(barr);
			fos.close();
			counter++;
		}
		System.out.println(counter);
		con.close();
	}
}
