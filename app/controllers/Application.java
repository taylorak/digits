package controllers;
// 18: 24

import java.util.Map;
import models.Contact;
import models.ContactDB;
import models.UserInfo;
import models.UserInfoDB;
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
    String user = Secured.getUser(ctx());
    return ok(Index.render("Home", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), ContactDB.getContacts(user)));
  }

  /**
   * Returns newContact, a form for adding contacts.
   * @param id
   * @return The newContact.
   */
  @Security.Authenticated(Secured.class)
  public static Result newContact(long id) {
    UserInfo userInfo = UserInfoDB.getUser(request().username());
    String user = userInfo.getEmail();
    ContactFormData data = (id == -1) ? new ContactFormData() : new ContactFormData(ContactDB.getContact(user, id));
    Form<ContactFormData> formData = Form.form(ContactFormData.class).fill(data);
    Map<String, Boolean> telephoneTypeMap = TelephoneTypes.getTypes(data.telephoneType);
    return ok(NewContact.render("New", Secured.isLoggedIn(ctx()),
                                 Secured.getUserInfo(ctx()), formData, telephoneTypeMap));    
  }

  
  /**
   * Deletes a Contact.
   * @param id
   */
  @Security.Authenticated(Secured.class)
  public static Result deleteContact(long id) {
    UserInfo userInfo = UserInfoDB.getUser(request().username());
    String user = userInfo.getEmail();
    Contact.find().ref(id).delete();
    return ok(Index.render("Home", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), ContactDB.getContacts(user)));
  }
  
  /**
   * Returns postContact, handles post for form.
   * @return The postContact.
   */
  @Security.Authenticated(Secured.class)
  public static Result postContact() {

    Form<ContactFormData> formData = Form.form(ContactFormData.class).bindFromRequest();
    
    if (formData.hasErrors()) {
      //Map<String, Boolean> typeMap = TelephoneTypes.getTypes();
      return badRequest(NewContact.render("New", Secured.isLoggedIn(ctx()),
                        Secured.getUserInfo(ctx()), formData, TelephoneTypes.getTypes()));
    } 
    else {
      ContactFormData data = formData.get();
      String user = Secured.getUser(ctx());
      ContactDB.addContact(user, data);
      //return ok(NewContact.render("New", Secured.isLoggedIn(ctx()),
      //    Secured.getUserInfo(ctx()), formData, typeMap));
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
  
  /**
   * Processes a login form submission from an unauthenticated user. 
   * First we bind the HTTP POST data to an instance of LoginFormData.
   * The binding process will invoke the RegistrationFormData.validate() method.
   * If errors are found, re-render the page, displaying the error data. 
   * If errors not found, render the page with the good data. 
   * @return The index page with the results of validation. 
   */
  public static Result postRegistration() {
    Form<LoginFormData> loginFormData = Form.form(LoginFormData.class);
    Form<RegistrationFormData> registrationFormData = Form.form(RegistrationFormData.class).bindFromRequest();

    if (registrationFormData.hasErrors()) {
      return badRequest(Login.render("Login", Secured.isLoggedIn(ctx()), Secured.getUserInfo(ctx()), loginFormData,
          registrationFormData));
    }
    else {
      UserInfoDB.addUserInfo(registrationFormData.get().name, registrationFormData.get().email,
          registrationFormData.get().password);
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
