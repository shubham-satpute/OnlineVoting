package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	private static Connection connection;
	
	public static void openConnection(String url ,String userName,String pass) throws SQLException {
		
	connection = DriverManager.getConnection(url, userName, pass);
	
		
	}

	public static Connection getConnection() {
		return connection;
	}
	
	public static void closeConnection() throws SQLException {
		if(connection!=null) {
			connection.close();
		}
	}
	

}
