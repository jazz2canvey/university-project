package just.by.jvd.models;

import java.util.Date;

public class EventsModel {
	
	int event_for_college, event_for_dept;
	String event_pic, event_heading, event_description;
	Date event_issued_date, event_bar_date;
	
	public EventsModel() {
		
	}
	
	public EventsModel(int event_for_college, int event_for_dept, String event_pic, String event_heading, String event_description) {

		this.event_for_college = event_for_college;
		this.event_for_dept = event_for_dept;
		this.event_pic = event_pic;
		this.event_heading = event_heading;
		this.event_description = event_description;		
	}
	
	public int getEvent_for_college() {
		return event_for_college;
	}

	public void setEvent_for_college(int event_for_college) {
		this.event_for_college = event_for_college;
	}

	public int getEvent_for_dept() {
		return event_for_dept;
	}

	public void setEvent_for_dept(int event_for_dept) {
		this.event_for_dept = event_for_dept;
	}

	public void setEvent_issued_date(Date event_issued_date) {
		this.event_issued_date = event_issued_date;
	}

	public void setEvent_bar_date(Date event_bar_date) {
		this.event_bar_date = event_bar_date;
	}

	public String getEvent_pic() {
		return event_pic;
	}

	public void setEvent_pic(String event_pic) {
		this.event_pic = event_pic;
	}

	public String getEvent_heading() {
		return event_heading;
	}

	public void setEvent_heading(String event_heading) {
		this.event_heading = event_heading;
	}

	public String getEvent_description() {
		return event_description;
	}

	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}

	public Date getEvent_issued_date() {
		return new Date();
	}

	public void setEvent_issued_date(String event_issued_date) {
		this.event_issued_date = new Date();
	}

	public Date getEvent_bar_date() {
		return new Date();
	}

	public void setEvent_bar_date(String event_bar_date) {
		this.event_bar_date = new Date();
	}
	
}
