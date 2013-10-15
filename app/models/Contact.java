package models;

/**
 * Contact class for holding data submitted by form.
 * @author taylorak
 *
 */
public class Contact {

  private String firstName;
  private String lastName;
  private String digits;
  
  /**
   * Creates a new contact.
   * @param firstName
   * @param lastName
   * @param digits
   */
  public Contact(String firstName, String lastName, String digits) {
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setDigits(digits);
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
  


}
