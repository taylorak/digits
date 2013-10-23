package controllers;
// 37:48

import java.util.HashMap;
import java.util.Map;
import models.ContactDB;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.Index;
import views.html.NewContact;
import views.formdata.ContactFormData;
import views.formdata.TelephoneTypes;

/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  public static Result index() {
    return ok(Index.render(ContactDB.getContacts()));
  }
  
  /**
   * Returns newContact, a form for adding contacts.
   * @param id
   * @return The newContact.
   */
  public static Result newContact(long id) {
    ContactFormData data = (id == 0) ? new ContactFormData() : new ContactFormData(ContactDB.getContact(id));
    Form<ContactFormData> formData = Form.form(ContactFormData.class).fill(data);
    Map<String, Boolean> typeMap = TelephoneTypes.getTypes(data.telephoneType);
    return ok(NewContact.render(formData, typeMap));
    
  }
  
  /**
   * Deletes a Contact.
   * @param id
   */
  public static Result deleteContact(long id) {
    ContactDB.deleteContact(id);
    return ok(Index.render(ContactDB.getContacts()));
  }
  
  /**
   * Returns postContact, handles post for form.
   * @return The postContact.
   */
  public static Result postContact() {
    Form<ContactFormData> formData = Form.form(ContactFormData.class).bindFromRequest();
    if (formData.hasErrors()) {
      Map<String, Boolean> typeMap = TelephoneTypes.getTypes();
      return badRequest(NewContact.render(formData, typeMap));
    } 
    else {
      ContactFormData data = formData.get();
      Map<String, Boolean> typeMap = TelephoneTypes.getTypes(data.telephoneType);
      ContactDB.addContact(data);
      //return ok(NewContact.render(formData));
      return redirect("/");
    }
    
  }
}
