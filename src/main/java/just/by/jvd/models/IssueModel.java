package just.by.jvd.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class IssueModel {

	String issuer_id, college_id, dept_id, priority, subject, issued_date, issue_status, issue;
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	public IssueModel() {
		
	}
	
	public IssueModel(String issuer_id, String college_id, String dept_id, String priority, String subject, String issued_date, String issue_status, String issue) throws ParseException {
		
		this.issuer_id = issuer_id;
		this.college_id = college_id;
		this.dept_id = dept_id;
		this.priority = priority;
		this.subject = subject;
		this.issued_date = issued_date;
		this.issue_status = issue_status;
		this.issue = issue;
	}

	public String getIssuer_id() {
		return issuer_id;
	}

	public void setIssuer_id(String issuer_id) {
		this.issuer_id = issuer_id;
	}
	
	public String getCollege_id() {
		return college_id;
	}

	public void setCollege_id(String college_id) {
		this.college_id = college_id;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getIssued_date() {
		return issued_date;
	}

	public void setIssued_date(String issued_date) {
		this.issued_date = issued_date;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getIssue_status() {
		return issue_status;
	}

	public void setIssue_status(String issue_status) {
		this.issue_status = issue_status;
	}
	
}
