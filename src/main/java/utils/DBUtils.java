package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
private static Connection connection;
public static Connection fetchDBCOnnection() throws SQLException {
	if(connection==null) {
		String url="jdbc:mysql://localhost:3306/practice2?useSSL=false&allowPublicKeyRetrieval=true";
		connection=DriverManager.getConnection(url, "root","root123");
	}
	return connection;
}
//add a static method to close db connectyion	
public static void closeConnection() throws SQLException
{
	System.out.println("Connection closed");
	if(connection!=null)
		connection.close();
}


}
