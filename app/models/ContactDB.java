package models;

import java.util.ArrayList;
import java.util.List;
import views.formdata.ContactFormData;

/**
 * Database for holding a list of contacts in memory.
 * @author taylorak
 *
 */
public class ContactDB {
  
  private static List<Contact> contacts = new ArrayList<>();
  
  /**
   * Creates a new contact and adds it to in memory database.
   * @param formData
   * @return
   */
  public static Contact addContact(ContactFormData formData) {
    Contact contact = new Contact(formData.firstName, formData.lastName, formData.digits);
    contacts.add(contact);
    return contact;
  }
  
  /**
   * Return in memory database.
   * @return contacts
   */
  public static List<Contact> getContacts() {
    return contacts;
  }

}
