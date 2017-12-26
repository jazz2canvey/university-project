package just.by.jvd.college;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import just.by.jvd.models.CallResultModel;
import just.by.jvd.models.DetailIssueModel;
import just.by.jvd.models.IssueModel;
import just.by.jvd.utilities.DatabaseUtils;

@Path("/issue")
public class RaiseIssue {

	DatabaseUtils databaseUtils;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<CallResultModel> registerUser(IssueModel issueModel) throws ParseException  {
		
		databaseUtils = new DatabaseUtils();
		List<CallResultModel> callResultModelList = new ArrayList<>();
		Connection conn = databaseUtils.connect2DB();		
		String query = " insert into issues (issuer_id, college_id, dept_id, priority, subject, issued_date, issue_status, issue)"
		        + " values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = conn.prepareStatement(query);
			
		    preparedStmt.setString (1, issueModel.getIssuer_id());
		    preparedStmt.setString (2, issueModel.getCollege_id());
		    preparedStmt.setString (3, issueModel.getDept_id());
		    preparedStmt.setString (4, issueModel.getPriority());
		    preparedStmt.setString (5, issueModel.getSubject());
		    preparedStmt.setString (6, issueModel.getIssued_date().toString());
		    preparedStmt.setString (7, issueModel.getIssue_status());
		    preparedStmt.setString (8, issueModel.getIssue());
		
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
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{college_id}/{dept_id}/{issue_status}/{issuer_id}")
	public List<DetailIssueModel> getRaisedIssues(@PathParam("college_id") Integer college_id,
											@PathParam("dept_id") Integer dept_id,
											@PathParam("issue_status") String issue_status,
											@PathParam("issuer_id") Integer issuer_id) {
		
		List<DetailIssueModel> issueModelsList;
		String query = null;

		try
	    {
			databaseUtils = new DatabaseUtils();
			issueModelsList = new ArrayList<>();
			Connection conn = databaseUtils.connect2DB();		
		    
/*			if (issue_status == null || issue_status.equals("") || issue_status.equals("All")) {
			
				query = "SELECT * FROM issues";
				
			} 
			else {
				
				query = "SELECT * FROM issues WHERE issue_status = " + " \"" + issue_status + "\"";				
			}		*/
			
			if (issue_status != null && issuer_id > 0) {
				query = "SELECT * FROM issues WHERE issue_status = " + " \"" + issue_status + "\" and issuer_id = " + issuer_id;
			} else {
				query = "SELECT * FROM issues WHERE issue_status = " + " \"" + issue_status + "\" and college_id = " + college_id + " and dept_id = " + dept_id;
			}

			System.out.println("Query: " + query); 
			
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		 // iterate through the java resultset
		      while (rs.next())
		      {
		    	  issueModelsList.add(new DetailIssueModel(rs.getString("sr_id"), rs.getString("issuer_id"), rs.getString("college_id"), rs.getString("dept_id"), rs.getString("priority"), rs.getString("subject"), rs.getString("issued_date"), rs.getString("issue_status"), rs.getString("issue"), rs.getString("remarks")));
		      }
		      st.close();

		      return issueModelsList;
		      
		    } catch (Exception e) {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		
		return null;
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{issue_id}/{remark}")
	public List<CallResultModel> getRaisedIssues(@PathParam("issue_id") Integer issue_id,
												@PathParam("remark") String remark) {
		
		List<CallResultModel> callResultModelList = new ArrayList<>();
		try
	    {
			databaseUtils = new DatabaseUtils();

			Connection conn = databaseUtils.connect2DB();
			
			String query = "UPDATE issues SET issue_status = 'Closed', remarks = " + "\"" + remark + "\" WHERE sr_id = " + issue_id;

			System.out.println("Query: " + query);
			
		    Statement st = conn.createStatement();
		    st.executeUpdate(query);

			callResultModelList.add(new CallResultModel(false, true));	    
		    		      
		    } catch (Exception e) {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		      callResultModelList.add(new CallResultModel(true, false));	    
		    }
		
		return callResultModelList;
	}
	
}
