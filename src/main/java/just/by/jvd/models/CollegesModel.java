package just.by.jvd.models;

public class CollegesModel {

	String college_id, college_name;
	
	public CollegesModel() {
		
	}
	
	public CollegesModel(String college_id, String college_name) {
		
		this.college_id = college_id;
		this.college_name = college_name;
	}

	public String getCollege_id() {
		return college_id;
	}

	public void setCollege_id(String college_id) {
		this.college_id = college_id;
	}

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}
	
}
