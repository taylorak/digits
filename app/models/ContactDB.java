package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import views.formdata.ContactFormData;


/**
 * Database for holding a list of contacts in memory.
 * @author taylorak
 *
 */
public class ContactDB {
  
  private static Map<Long, Contact> contacts = new HashMap<>();
  
  /**
   * Creates a new contact and adds it to in memory database.
   * @param formData
   * @return contact
   */
  public static Contact addContact(ContactFormData formData) {
    long idVal = (formData.id == 0) ? contacts.size() + 1 : formData.id;
    Contact contact = new Contact(idVal, formData.firstName, formData.lastName, formData.digits, formData.telephoneType);
    contacts.put(idVal, contact);
    return contact;
  }
  
  /**
   * Deletes a contact from in memory database.
   * @param id
   */
  public static void deleteContact(long id) {
    contacts.remove(id);
  }
  
  /**
   * Return in memory database containing all contacts.
   * @return contacts
   */
  public static List<Contact> getContacts() {
    return new ArrayList<>(contacts.values());
  }

  /**
   * Returns contact at specified id.
   * @param id
   * @return contact
   */
  public static Contact getContact(long id) {
    Contact contact = contacts.get(id);
    if (contact == null) {
      throw new RuntimeException("Passed a bogus id " + id);
    }
    return contact;
  }
}
