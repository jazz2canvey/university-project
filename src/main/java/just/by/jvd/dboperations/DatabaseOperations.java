package just.by.jvd.dboperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseOperations {

	public Connection dbConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection databaseConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "");
			if (databaseConn.getMetaData().getDatabaseProductName() != null) {
				return databaseConn;
			} else {
				return null;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
}
