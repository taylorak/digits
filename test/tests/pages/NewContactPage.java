package tests.pages;

import org.fluentlenium.core.FluentPage;
import org.openqa.selenium.WebDriver;
import play.Play;
// Although Eclipse marks the following two methods as deprecated, 
// the no-arg versions of the methods used here are not deprecated.  (as of May, 2013).
import static org.fluentlenium.core.filter.FilterConstructor.withText; 
import static org.fluentlenium.core.filter.FilterConstructor.withId;  
import static org.fest.assertions.Assertions.assertThat;

/**
 * Illustration of the Page Object Pattern in Fluentlenium.  
 * @author Taylor Kennedy
 */
public class NewContactPage extends FluentPage {
  private String url;
  
  /**
   * Create the IndexPage.
   * @param webDriver The driver.
   * @param port The port.
   */
  public NewContactPage(WebDriver webDriver, int port) {
    super(webDriver);
    this.url = "http://localhost:" + port + "newcontact";
  }
  
  @Override
  public String getUrl() {
    return this.url;
  }
  
  @Override
  public void isAt() {
    assertThat(title()).isEqualTo("New");
  }
  
  /**
   * return true if is logged in.
   * @return boolean.
   */
  public boolean isLoggedIn() {
    return !find("#logout").isEmpty();
  }

  /**
   * return true if is logged in.
   *
   */
  public void logout() {
    find("#logout").click();
  }
  
  /**
* Logs into page.
*/
  public void makeContact(String first, String last, String digits, String telephoneType) {
    fill("#FirstName").with(first);
    fill("#LastName").with(last);
    fill("#digits").with(digits);
    find("select", withId().equalTo("telephoneType")).find("option", withText().equalTo(telephoneType)).click();
    submit("#submit");
  }

}
