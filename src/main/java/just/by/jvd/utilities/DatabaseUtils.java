package just.by.jvd.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {

	public Connection connect2DB() {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university","root","");  
			return con;			
		}
			catch(Exception e) { 
				System.out.println(e);
			}  
	
		return null;		
	} 
		
}
