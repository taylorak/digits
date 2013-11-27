import play.GlobalSettings;
import play.Play;
import models.ContactDB;
import models.UserInfo;
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
    
    String adminEmail = Play.application().configuration().getString("digits.admin.email");
    String adminPassword = Play.application().configuration().getString("digits.admin.password");

    UserInfo admin = UserInfoDB.getUser(adminEmail);

    if (admin == null) {
      UserInfoDB.defineAdmin("Administrator", adminEmail, adminPassword);
      if (UserInfoDB.adminDefined() && UserInfoDB.getUser(adminEmail).getContacts().isEmpty()) {
        ContactDB.addContact(adminEmail, new ContactFormData("Taylor", "Kennedy", "111-111-1111", "Home"));
        ContactDB.addContact(adminEmail, new ContactFormData("Courtney", "Kennedy", "111-111-1111", "Mobile"));
      }
    }
  }
}
