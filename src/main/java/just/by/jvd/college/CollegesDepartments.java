package just.by.jvd.college;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import just.by.jvd.models.CollegesModel;
import just.by.jvd.models.DepartmentsModel;
import just.by.jvd.utilities.DatabaseUtils;

@Path("/c&d")
public class CollegesDepartments {

	DatabaseUtils databaseUtils;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CollegesModel> getColleges() {
		
		List<CollegesModel> collegesModelList;
		
		try
	    {
			databaseUtils = new DatabaseUtils();
			collegesModelList = new ArrayList<>();
			Connection conn = databaseUtils.connect2DB();		
		    String query = "SELECT sr_no, college_name FROM colleges";

		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		 // iterate through the java resultset
		      while (rs.next())
		      {
		    	  collegesModelList.add(new CollegesModel(rs.getString("sr_no"), rs.getString("college_name")));	    	
		      }
		      st.close();

		      return collegesModelList;
		      
		    } catch (Exception e) {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		return null;		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{collegeId}")
	public List<DepartmentsModel> getDepartments(@PathParam("collegeId") int id) {
		
		List<DepartmentsModel> departmentsModelsList;
		
		try
	    {
			databaseUtils = new DatabaseUtils();
			departmentsModelsList = new ArrayList<>();
			Connection conn = databaseUtils.connect2DB();		
		    String query = "SELECT sr_no, department_name FROM university.departments where college_id = " + id;

		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		 // iterate through the java resultset
		      while (rs.next())
		      {
		    	  departmentsModelsList.add(new DepartmentsModel(rs.getInt("sr_no"), rs.getString("department_name")));
		      }
		      st.close();

		      return departmentsModelsList;
		      
		    } catch (Exception e) {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		return null;		
	}
	
}
