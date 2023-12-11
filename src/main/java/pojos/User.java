package pojos;

import java.sql.Date;

public class User {

//	 id | first_name | last_name | email             | password | dob        | status | role
	private int id;
	private String fName,lName,eMail,password;
	private Date dob;
	private boolean status;
	private String  role;
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", fName=" + fName + ", lName=" + lName + ", eMail=" + eMail + ", password="
				+ password + ", dob=" + dob + ", status=" + status + ", role=" + role + "]";
	}
	
	public User(String fName, String lName, String eMail, String password, Date dob) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.eMail = eMail;
		this.password = password;
		this.dob = dob;
		
	}

	public User(int id, String fName, String lName, String eMail, String password, Date dob, boolean status,
			String role) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.eMail = eMail;
		this.password = password;
		this.dob = dob;
		this.status = status;
		this.role = role;
	}
	public User() {
		}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
