package pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users_tbl") // specifies table name
public class User {
	/*
	 * userId (PK) ,name,email,password,role(enum),confirmPassword, regAmount;
	 * LocalDate/Date regDate; byte[] image;
	 */
	// PK-->Serializable
	@Id // mandatory:PK constraint
    //Hib chooses default DB specific strategy for auto PK generation
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment suitable for mysql
	@Column(name="user_id")
	private Integer userId;
	@Column(length = 20,name = "name")//varchar(20) name-->name
	private String name;
	@Column(unique = true,length = 30)//varchar(30) unique constraint
	private String email;
	@Column(length = 15,nullable = false)//varchar(15),not_null 
	private String password;
	@Column(name = "user_role",length = 20)
	@Enumerated(EnumType.STRING)
	private UserRole role;
	@Transient//skips from persistence(no corresponding column)
	private String confirmPassword;
	@Column(name="reg_amount")
	private double regAmount;
	@Column(name="reg_date")
	private LocalDate reqDate;
	@Lob//column type blob:longblob
	private byte[] image;

//must supply def constructor
	public User() {
		System.out.println("in user ctor");
	}

	public User(String name, String email, String password, UserRole role, String confirmPassword, double regAmount,
			LocalDate reqDate) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.confirmPassword = confirmPassword;
		this.regAmount = regAmount;
		this.reqDate = reqDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}

	public LocalDate getReqDate() {
		return reqDate;
	}

	public void setReqDate(LocalDate reqDate) {
		this.reqDate = reqDate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

//Debugging purpose
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + ", confirmPassword=" + confirmPassword + ", regAmount=" + regAmount + ", reqDate=" + reqDate
				+ "]" ;
	}

}
