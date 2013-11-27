package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.db.ebean.Model;

/**
 * Contact class for holding data submitted by form.
 * @author taylorak
 *
 */
@Entity
public class Contact extends Model{

  private static final long serialVersionUID = -5714056881888452346L;
  
  @Id
  private long id;
  private String firstName;
  private String lastName;
  private String digits;
  private String telephoneType;
  

  @ManyToOne
  private UserInfo userInfo;
  
  /**
   * Creates a new contact.
   * @param id
   * @param firstName
   * @param lastName
   * @param digits
   * @param telephoneType
   */
  public Contact(String firstName, String lastName, String digits, String telephoneType, UserInfo userInfo) {
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setDigits(digits);
    this.setTelephoneType(telephoneType);
    this.userInfo = userInfo;
  }

  /**
   * finds a contact.
   * @return a contact
   */
  public static Finder<Long, Contact> find() {
    return new Finder<Long, Contact>(Long.class, Contact.class);
  }
  
  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the digits
   */
  public String getDigits() {
    return digits;
  }

  /**
   * @param digits the digits to set
   */
  public void setDigits(String digits) {
    this.digits = digits;
  }

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @return the telephoneType
   */
  public String getTelephoneType() {
    return telephoneType;
  }

  /**
   * @param telephoneType the telephoneType to set
   */
  public void setTelephoneType(String telephoneType) {
    this.telephoneType = telephoneType;
  }
  


}
