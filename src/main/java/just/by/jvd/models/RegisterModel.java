package just.by.jvd.models;

public class RegisterModel {

	int id, college_selection, department;
	String name, address, city, phone_no, profession, email_id, password;
	
	public RegisterModel() {
		
	}
		
	public RegisterModel(int id, int college_selection, String name, int department, String address, String city, String phone_no, String profession, String email_id, String password) {
		
		this.id = id;
		this.college_selection = college_selection;
		this.name = name;
		this.department = department;
		this.address = address;
		this.city = city;
		this.phone_no = phone_no;
		this.profession = profession;
		this.email_id = email_id;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCollege_selection() {
		return college_selection;
	}

	public void setCollege_selection(int college_selection) {
		this.college_selection = college_selection;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
