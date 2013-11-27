package models;

import java.util.List;
import views.formdata.ContactFormData;

/**
 * Database for Contacts.
 *
 * @author taylorak
 *
 */
public class ContactDB {

  /**
   * Adds a Contact to the database.
   *
   * @param email The user this contact belongs to.
   * @param formData FormData
   */
  public static void addContact(String email, ContactFormData formData) {
    boolean isNewContact = (formData.id == -1);
    if (isNewContact) {
      Contact contact = new Contact(formData.firstName, formData.lastName, formData.digits, formData.telephoneType);
      UserInfo userInfo = UserInfo.find().where().eq("email", email).findUnique();
      
      if (userInfo == null) {
        throw new RuntimeException("could nof find user: " + email);
      }
      
      userInfo.addContact(contact);
      contact.setUserInfo(userInfo);
      contact.save();
      userInfo.save();
    }
    else {
      System.out.println(formData.id);
      Contact contact = Contact.find().byId(formData.id);
      contact.setFirstName(formData.firstName);
      contact.setLastName(formData.lastName);
      contact.setDigits(formData.digits);
      contact.setTelephoneType(formData.telephoneType);
      contact.save();
    }
  }

  /**
   * Gets the list of contacts.
   *
   * @param email The user this contact belongs to.
   * @return List of Contacts.
   */
  public static List<Contact> getContacts(String email) {
    System.out.println(email);
    UserInfo userInfo = UserInfo.find().where().eq("email", email).findUnique();
    return userInfo.getContacts();
  }

  /**
   * Returns true if the suer is defined in the contacts DB.
   *
   * @param email The user email.
   * @return True if the user is defined.
   */
  public static boolean isUser(String email) {
    return (UserInfo.find().where().eq("email", email).findUnique() != null);
  }

  /**
   * Gets the Contact with the given id.
   *
   * @param email The user this contact belongs to.
   * @param id Id of the Contact.
   * @return The Contact with the given id.
   */
  public static Contact getContact(String email, long id) {
    Contact contact = Contact.find().byId(id);
    if (contact == null) {
      throw new RuntimeException("Contact ID not found: " + id);
    }
    UserInfo userInfo = contact.getUserInfo();
    if (email != userInfo.getEmail()) {
      throw new RuntimeException("User not the same on stored with the contact.");
    }
    return contact;
  }
}