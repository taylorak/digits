package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import play.db.ebean.Model;

/**
 * A simple representation of a user. 
 * @author Philip Johnson
 */
@Entity
public class UserInfo extends Model {
 
  private static final long serialVersionUID = 1650423460863711958L;
  
  @Id
  private String email;
  private String name;
  private String password;
  private boolean admin;
  
  @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
  private List<Contact> contacts = new ArrayList<>();
  
  /**
   * Creates a new UserInfo instance.
   * @param name The name.
   * @param email The email.
   * @param password The password.
   */
  public UserInfo(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.admin = false;
  }
  
  /**
  public void defineAdmin(String email, String name, String password) {
    UserInfo userInfo = new UserInfo(name, email, password);
    userInfo.setAdmin(true);
    userInfo.save();
  }
  
  public UserInfo addUserInfo(String email, String name, String password) {
    UserInfo userInfo = new UserInfo(name, email, password);
    userInfo.save();
    return userInfo;
  }
  **/
  
  public UserInfo addContact(String firstName, String lastName, String digits, String telephoneType){
    Contact newContact = new Contact(firstName, lastName, digits, telephoneType, this);
    newContact.save();
    this.contacts.add(newContact);
    this.save();
    return this;
  }
  
  public List<Contact> getContacts() {
    return this.contacts;
  }
  
  /**
   * finds a contact.
   * @return a contact
   */
  public static Finder<Long, UserInfo> find() {
    return new Finder<Long, UserInfo>(Long.class, UserInfo.class);
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }
  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }
  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the admin
   */
  public boolean isAdmin() {
    return admin;
  }

  /**
   * @param admin the admin to set
   */
  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  /**
   * Returns true if the email represents a known user.
   *
   * @param email The email.
   * @return True if known user.
   */
  public static boolean isUser(String email) {
    if(UserInfo.find().where().eq("email", email).findUnique() == null) {
      return false;
    } else {
      return true;
    }
  } 

  /**
   * Returns true if email and password are valid credentials.
   *
   * @param email The email.
   * @param password The password.
   * @return True if email is a valid user email and password is valid for that email.
   */
  public static boolean isValid(String email, String password) {
    return ((email != null) 
        && (password != null) 
        && isUser(email) && UserInfo.find().where().eq("email", email).findUnique().getPassword().equals(password));
  }
}
