package controllers;
// 18: 24

import java.util.Map;
import models.Contact;
import models.UserInfo;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.Index;
import views.html.Login;
import views.html.NewContact;
//import views.html.Profile;
import views.formdata.ContactFormData;
import views.formdata.LoginFormData;
import views.formdata.RegistrationFormData;
import views.formdata.TelephoneTypes;

/**
 * Implements the controllers for this application.
 */
public class Application extends Controller {

  /**
   * Returns the home page. 
   * @return The resulting home page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result index() {
    UserInfo userInfo = UserInfo.find().where().eq("email", request().username()).findUnique();
    return ok(Index.render("Home", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), userInfo.getContacts()));
  }
  
  /**
   * Returns newContact, a form for adding contacts.
   * @param id
   * @return The newContact.
   */
  @Security.Authenticated(Secured.class)
  public static Result newContact(long id) {
    Contact contact = Contact.find().where().eq("id", id).findUnique();
    ContactFormData data = (contact == null) ?  new ContactFormData() : new ContactFormData(contact);
    Form<ContactFormData> formData = Form.form(ContactFormData.class).fill(data);
    Map<String, Boolean> typeMap = TelephoneTypes.getTypes(data.telephoneType);
    return ok(NewContact.render("New", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData, typeMap));
    
  }
  
  /**
   * Deletes a Contact.
   * @param id
   */
  @Security.Authenticated(Secured.class)
  public static Result deleteContact(long id) {
    UserInfo userInfo = UserInfo.find().where().eq("email", request().username()).findUnique();
    Contact.find().ref(id).delete();
    return ok(Index.render("Home", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), userInfo.getContacts()));
  }
  
  /**
   * Returns postContact, handles post for form.
   * @return The postContact.
   */
  @Security.Authenticated(Secured.class)
  public static Result postContact() {
    Form<ContactFormData> formData = Form.form(ContactFormData.class).bindFromRequest();
    UserInfo userInfo = UserInfo.find().where().eq("email", request().username()).findUnique();
    if (formData.hasErrors()) {
      return badRequest(NewContact.render("New", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData, TelephoneTypes.getTypes()));
    } 
    else {
      ContactFormData data = formData.get();
      userInfo.addContact(data.firstName, data.lastName, data.digits, data.telephoneType);
      return redirect("/");
    }
  }
  
  /**
   * Provides the Login page (only to unauthenticated users). 
   * @return The Login page. 
   */
  public static Result login() {
    Form<LoginFormData> loginFormData = Form.form(LoginFormData.class);
    Form<RegistrationFormData> registrationFormData = Form.form(RegistrationFormData.class);
    return ok(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), loginFormData, registrationFormData));
  }

  /**
   * Processes a login form submission from an unauthenticated user. 
   * First we bind the HTTP POST data to an instance of LoginFormData.
   * The binding process will invoke the LoginFormData.validate() method.
   * If errors are found, re-render the page, displaying the error data. 
   * If errors not found, render the page with the good data. 
   * @return The index page with the results of validation. 
   */
  public static Result postLogin() {

    // Get the submitted form data from the request object, and run validation.
    Form<LoginFormData> formData = Form.form(LoginFormData.class).bindFromRequest();
    Form<RegistrationFormData> registrationFormData = Form.form(RegistrationFormData.class);


    if (formData.hasErrors()) {
      flash("error", "Login credentials not valid.");
      return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), formData, registrationFormData));
    }
    else {
      // email/password OK, so now we set the session variable and only go to authenticated pages.
      session().clear();
      session("email", formData.get().email);
      return redirect(routes.Application.index());
    }
  }
  
  public static Result postRegistration() {
    Form<LoginFormData> loginFormData = Form.form(LoginFormData.class);
    Form<RegistrationFormData> registrationFormData = Form.form(RegistrationFormData.class).bindFromRequest();

    if (registrationFormData.hasErrors()) {
      flash("error", "Registration form not valid.");
      return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), loginFormData,
          registrationFormData));
    }
    else {
      UserInfo userInfo = new UserInfo(registrationFormData.get().name, registrationFormData.get().email,
          registrationFormData.get().password);
      userInfo.save();
      session().clear();
      session("email", registrationFormData.get().email);
      return redirect(routes.Application.index());
    }
  }
  /**
   * Logs out (only for authenticated users) and returns them to the Index page. 
   * @return A redirect to the Index page. 
   */
  @Security.Authenticated(Secured.class)
  public static Result logout() {
    session().clear();
    return redirect(routes.Application.index());
  }
  
}
