package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE employee (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  age INT NOT NULL,
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "MyTable1012Fuelberth")
public class MyEmployeeFuelberth {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id") // specify the column name. Without it, it will use method name
   private Integer id;

   @Column(name = "FIRST_NAME")
   private String firstName;

   @Column(name = "LAST_NAME")
   private String lastName;

   @Column(name = "PHONE")
   private String phone;

   @Column(name = "EMAIL")
   private String email;

   public MyEmployeeFuelberth() {
   }
   
   public MyEmployeeFuelberth(String firstName, String lastName, String phone, String email)
   {
	   this.firstName = firstName;
	   this.lastName = lastName;
	   this.phone = phone;
	   this.email = email;
   }

   public Integer getId() {
	return id;
}



   public void setId(Integer id) {
	this.id = id;
   }



public String getFirstName() {
	return firstName;
}



public void setFirstName(String firstName) {
	this.firstName = firstName;
}



public String getLastName() {
	return lastName;
}



public void setLastName(String lastName) {
	this.lastName = lastName;
}



public String getPhone() {
	return phone;
}



public void setPhone(String phone) {
	this.phone = phone;
}



public String getEmail() {
	return email;
}



public void setEmail(String email) {
	this.email = email;
}

@Override
public String toString() {
	return "MyEmployeeFuelberth [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
			+ ", email=" + email + "]";
}



}