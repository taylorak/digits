package models;

/**
 * Contact class for holding data submitted by form.
 * @author taylorak
 *
 */
public class Contact {

  private long id;
  private String firstName;
  private String lastName;
  private String digits;
  private String telephoneType;
  
  /**
   * Creates a new contact.
   * @param id
   * @param firstName
   * @param lastName
   * @param digits
   * @param telephoneType
   */
  public Contact(long id, String firstName, String lastName, String digits, String telephoneType) {
    this.id = id;
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setDigits(digits);
    this.setTelephoneType(telephoneType);
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
