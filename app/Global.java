import play.GlobalSettings;
import models.ContactDB;
import models.UserInfoDB;
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
    
    UserInfoDB.addUserInfo("John Smith", "smith@example.com", "password");
    UserInfoDB.addUserInfo("Smith John", "john@example.com", "password");

    
    ContactDB.addContact("smith@example.com", new ContactFormData("Taylor", "Kennedy", "111-111-1111", "Home"));
    ContactDB.addContact("smith@example.com", new ContactFormData("Courtney", "Kennedy", "111-111-1111", "Mobile"));
    ContactDB.addContact("john@example.com", new ContactFormData("Nicky", "Kennedy", "111-111-1111", "Mobile"));
    ContactDB.addContact("john@example.com", new ContactFormData("Paul", "Kennedy", "111-111-1111", "Work")); 

  }
}
