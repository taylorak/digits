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
  
  private static Map<String, Map<Long, Contact>> contacts = new HashMap<String, Map<Long, Contact>>();
  
  public static boolean isUser(String user) {
    return contacts.containsKey(user);
  }
  
  /**
   * Creates a new contact and adds it to in memory database.
   * @param user
   * @param formData
   * @return contact
   */
  public static Contact addContact(String user, ContactFormData formData) {
    long idVal = (formData.id == 0) ? contacts.size() + 1 : formData.id;
    Contact contact = new Contact(idVal, formData.firstName, formData.lastName, formData.digits, formData.telephoneType);
    if(!isUser(user)){
      contacts.put(user, new HashMap<Long, Contact>());
    }
    contacts.get(user).put(idVal, contact);
    return contact;
  }
  
  /**
   * Deletes a contact from in memory database.
   * @param user
   * @param id
   */
  public static void deleteContact(String user, long id) {
    if(isUser(user)){
      contacts.get(user).remove(id);      
    }
  }
  
  /**
   * Return in memory database containing all contacts.
   * @param user
   * @return contacts
   */
  public static List<Contact> getContacts(String user) {
    if(!isUser(user)){
      return null;
    }
    return new ArrayList<>(contacts.get(user).values());
  }

  /**
   * Returns contact at specified id.
   * @param user
   * @param id
   * @return contact
   */
  public static Contact getContact(String user, long id) {
    Contact contact = contacts.get(user).get(id);
    if (!isUser(user)) {
      throw new RuntimeException("Passed a bogus user " + user);
    }
    if (contact == null) {
      throw new RuntimeException("Passed a bogus id " + id);
    }
    return contact;
  }
}
