package just.by.jvd.college;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import just.by.jvd.models.EventsModel;
import just.by.jvd.utilities.DatabaseUtils;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class Events {

	DatabaseUtils databaseUtils;

	@POST
	public void registerEvent(EventsModel eventsModel) {
		
		databaseUtils = new DatabaseUtils();
		Connection conn = databaseUtils.connect2DB();		
		String query = " insert into events (event_for_college, event_for_dept, event_pic, event_heading, event_description, event_issued_date, event_bar_date)"
		        + " values (?, ?, ?, ?, ?, ?, ?)";
	
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = conn.prepareStatement(query);
			
		    preparedStmt.setInt (1, eventsModel.getEvent_for_college());
		    preparedStmt.setInt (2, eventsModel.getEvent_for_dept());
		    preparedStmt.setString (3, eventsModel.getEvent_pic());
		    preparedStmt.setString (4, eventsModel.getEvent_heading());
		    preparedStmt.setString (5, eventsModel.getEvent_description());
		    preparedStmt.setString (6, eventsModel.getEvent_issued_date().toString());
		    preparedStmt.setString (7, eventsModel.getEvent_bar_date().toString());
		
		      // execute the preparedstatement
		    preparedStmt.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	}
	
	@GET
	public List<EventsModel> getEventsFor(@QueryParam("event_for_college") int college_id,
											@QueryParam("event_for_dept") int dept_id) {

		List<EventsModel> eventsModelsList;		
		try
	    {
			databaseUtils = new DatabaseUtils();
			eventsModelsList = new ArrayList<>();
			Connection conn = databaseUtils.connect2DB();
			String query = "SELECT * FROM university.events where (event_for_college = " + college_id + " and event_for_dept = " + dept_id +") or (event_for_college = " + 0 + " and event_for_dept = " + 0 +")";
//		    String query = "SELECT * FROM university.events where event_for = " + " \"" + event_4 + "\"";

		    System.out.println("Query " + query);
		    
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		 // iterate through the java resultset
		      while (rs.next())
		      {
		    	  eventsModelsList.add(new EventsModel(rs.getInt("event_for_college"), rs.getInt("event_for_dept"), rs.getString("event_pic"), rs.getString("event_heading"), rs.getString("event_description")));
	    	  }

		      st.close();

		      return eventsModelsList;
		      
		    } catch (Exception e) {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		return null;		
	}	
}
