package pojo;

import java.sql.Date;

public class User {
/*
 *  id       | int         | NO   | PRI | NULL    | auto_increment |
| name     | varchar(20) | YES  |     | NULL    |                |
| email    | varchar(20) | YES  | UNI | NULL    |                |
| password | varchar(20) | YES  |     | NULL    |                |
| reg_amt  | double      | YES  |     | NULL    |                |
| reg_date | date        | YES  |     | NULL    |                |
| role     | varchar(10) | YES  |     | NULL    |                |
 */
	private int userId;
	private String name;
	private String email;
	private String password;
	private double regAmount;
	private Date regDate;
	private String role;
	public User() {
		
	}
	public User(int id, String name, String email, String password, double regAmount, Date regDate, String role) {
		super();
		this.userId = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.regAmount = regAmount;
		this.regDate = regDate;
		this.role = role;
	}
	public int getId() {
		return userId;
	}
	public void setId(int id) {
		this.userId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getRegAmount() {
		return regAmount;
	}
	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", regAmount="
				+ regAmount + ", regDate=" + regDate + ", role=" + role + "]";
	}
	
	
}
