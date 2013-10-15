package views.formdata;

import java.util.ArrayList;
import java.util.List;
import models.Contact;
import play.data.validation.ValidationError;

/**
 * The backing class for form data.
 * @author taylorak
 *
 */
public class ContactFormData {
  private static final int NUM_TELEPHONE_DIGITS = 12;
  /**The first name form field.**/
  public String firstName = "";
  
  /**The last name form field.**/
  public String lastName = "";
  
  /**The digits form field.**/
  public String digits = "";
  
  /**The id form field.**/
  public long id;
  
  /**
   * The default constructor.
   */
  public ContactFormData() {
    // default no arg constructor
  }
  
  /**
   * Create a contact form data object based on contact.
   * @param contact
   */
  public ContactFormData(Contact contact) {
    this.id = contact.getId();
    this.firstName = contact.getFirstName();
    this.lastName = contact.getLastName();
    this.digits = contact.getDigits();
  }
  
  /** Checks if form is valid.
   * @return null if no errors, List of errors if there are.
   */
  public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<>();
    
    if (firstName == null || firstName.length() == 0) {
      errors.add(new ValidationError("firstName", "First name is Required."));
    }
    if (lastName == null || lastName.length() == 0) {
      errors.add(new ValidationError("lastName", "Last name is Required."));
    }
    
    if (digits == null || digits.length() == 0) {
      errors.add(new ValidationError("digits", "Telephone number is Required."));
    }
    
    if (digits.length() != NUM_TELEPHONE_DIGITS) {
      errors.add(new ValidationError("digits", "Telephone must be xxx-xxx-xxxx."));
    }
    return errors.isEmpty() ? null : errors;
  }


}
