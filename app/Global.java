import play.GlobalSettings;
import models.ContactDB;
import play.Application;
import views.formdata.ContactFormData;

/**
 * Initialize the contact table.
 * @author taylorak
 * @param Application app
 * @returns the application
 */
public class Global extends GlobalSettings {

  public void onStart(Application app) {
    ContactDB.addContact(new ContactFormData("Taylor", "Kennedy", "111-111-1111", "Home"));
    ContactDB.addContact(new ContactFormData("Courtney", "Kennedy", "111-111-1111", "Mobile"));
    ContactDB.addContact(new ContactFormData("Nicky", "Kennedy", "111-111-1111", "Mobile"));
    ContactDB.addContact(new ContactFormData("Paul", "Kennedy", "111-111-1111", "Work")); 

  }
}
