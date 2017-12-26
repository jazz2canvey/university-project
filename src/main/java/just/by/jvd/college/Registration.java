package just.by.jvd.college;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import just.by.jvd.models.CallResultModel;
import just.by.jvd.models.RegisterModel;
import just.by.jvd.utilities.DatabaseUtils;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
public class Registration {

	DatabaseUtils databaseUtils;

	@POST
	public List<CallResultModel> registerUser(RegisterModel registerModel) {
		
		List<CallResultModel> callResultModelList = new ArrayList<>();
		databaseUtils = new DatabaseUtils();
		Connection conn = databaseUtils.connect2DB();		
		String query = " insert into registration (college_selection, name, department, address, city, phone_no, profession, email_id, password)"
		        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = conn.prepareStatement(query);
			
		    preparedStmt.setInt (1, registerModel.getCollege_selection());
		    preparedStmt.setString (2, registerModel.getName());
		    preparedStmt.setInt (3, registerModel.getDepartment());
		    preparedStmt.setString (4, registerModel.getAddress());
		    preparedStmt.setString (5, registerModel.getCity());
		    preparedStmt.setString (6, registerModel.getPhone_no());
		    preparedStmt.setString (7, registerModel.getProfession());
		    preparedStmt.setString (8, registerModel.getEmail_id());
		    preparedStmt.setString (9, registerModel.getPassword());
				    
		    // execute the preparedstatement
		    preparedStmt.execute();
		    
			callResultModelList.add(new CallResultModel(false, true));
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			callResultModelList.add(new CallResultModel(true, false));
		}
		
		return callResultModelList;
	}
	
	@GET
	@Path("{registrations}")
	public List<RegisterModel> getRegistrations(@QueryParam("college_selection") String college_id, 
												@QueryParam("department") String dept_id) {
		
		List<RegisterModel> registerModelsList;
		try
	    {
			databaseUtils = new DatabaseUtils();
			registerModelsList = new ArrayList<>();
			Connection conn = databaseUtils.connect2DB();	

		    String query = "SELECT * FROM registration WHERE college_selection = " + college_id + " and department = " + dept_id + " and profession = " + "\"" + "Student" + "\"";

		    System.out.println("Query: " + query);
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		 // iterate through the java resultset
		      while (rs.next())
		      {
		    	registerModelsList.add(new RegisterModel(rs.getInt("sr_id"), rs.getInt("college_selection"), rs.getString("name"), rs.getInt("department"), rs.getString("address"), rs.getString("city"), rs.getString("phone_no"), rs.getString("profession"), rs.getString("email_id"), rs.getString("password")));
		      }
		      st.close();

		      return registerModelsList;
		      
		    } catch (Exception e) {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		    
		
		return null;
	}
	
	@GET
	public List<RegisterModel> verifyLogin(@QueryParam("email_id") String user,
			@QueryParam("password") String pass) {
		
		List<RegisterModel> registerModelsList;
		registerModelsList = new ArrayList<>();
		databaseUtils = new DatabaseUtils();
		Connection connection = databaseUtils.connect2DB();
		String query = "SELECT * FROM university.registration WHERE email_id = '" + user + "' and password='" + pass + "'";

		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next())
		      {
		    	registerModelsList.add(new RegisterModel(rs.getInt("sr_id"), rs.getInt("college_selection"), rs.getString("name"), rs.getInt("department"), rs.getString("address"), rs.getString("city"), rs.getString("phone_no"), rs.getString("profession"), rs.getString("email_id"), rs.getString("password")));
		      }
		      statement.close();
			
	      return registerModelsList;
		      
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return null;
	}
	
	@GET
	@Path("password/{email_id}")
	public List<CallResultModel> forgotPassword(@PathParam("email_id") String emailId) {

		System.out.println("Email id: " + emailId);
		
		String name = null, userName = null, password = null;
		
		List<CallResultModel> callResultModelList = new ArrayList<>();
		databaseUtils = new DatabaseUtils();
		Connection connection = databaseUtils.connect2DB();
		String query = "SELECT name, email_id, password FROM university.registration WHERE email_id = " + " \"" + emailId + "\"";
		
		System.out.println("Query Executed " + query);
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			System.out.println("Query Executed " + query);
			
			while (rs.next())
		      {
		    	name = rs.getString("name");
		    	userName = rs.getString("email_id");
				password = rs.getString("password");		
		      }
			
	      statement.close();
	      connection.close();
	
	      System.out.println(name + " " + userName + " " + password);
	      
	      if (name != null && userName != null && password != null) {
	    	  if (!name.equals("") && !userName.equals("") && !password.equals("")) {
	    		
	    		  if (sendMail(emailId, name, userName, password)) {
    				callResultModelList.add(new CallResultModel(false, true));
	    		  }
	    	  }
	      }

		} catch (Exception e) {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      callResultModelList.add(new CallResultModel(true, false));
	    }
		
		return callResultModelList;
	}	
	
	private boolean sendMail(String emailAddress, String name, String userName, String password) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "mail.hduda.in");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "25");
		
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("passwordmail@hduda.in", "just4University");
			}
		});
		
		Message message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress("passwordmail@hduda.in"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
			message.setSubject("University App Your Account Password");
			message.setContent("<p> Subject : " + "Your Account Password" + "</p>"
					+"<p>Name : " + name + "</p>"
					+"<p>Username : "+ userName +"</p>"
					+"<p>Password : "+ password +"</p>", "text/html;Charset=UTF-8");

			Transport.send(message);
			
			return true;
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
