package tests;

import org.junit.Test;
import play.test.TestBrowser;
import play.libs.F.Callback;
import tests.pages.IndexPage;
import tests.pages.LoginPage;
import tests.pages.NewContactPage;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.testServer;
import static play.test.Helpers.running;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Integration tests running on an instance of the application.
 */
public class IntegrationTest {
  /** The port number for the integration test. */
  private static final int PORT = 3333;

  /**
   * Check to see that the index page can be displayed.
   
  @Test
  public void testBasicRetrieval() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        browser.goTo("http://localhost:" + PORT);
        assertThat(browser.pageSource()).contains("Home");
      }
    });
  }
  */
  
  /**
   * Check to see login page logs the user in.
   */
  @Test
  public void testLogin() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        browser.goTo("http://localhost:" + PORT + "/login");
        LoginPage loginpage = new LoginPage(browser.getDriver(), PORT);
        browser.goTo(loginpage);
        loginpage.isAt();
        loginpage.login();
        IndexPage indexpage = new IndexPage(browser.getDriver(), PORT);
        indexpage.isAt();
        assertThat(indexpage.isLoggedIn()).isTrue();
        assertThat(browser.pageSource()).contains("Logout");
        indexpage.logout();
        //assertThat(indexpage.isLoggedIn()).isFalse();
      }
    });
  }
  
  /**
   * Check to see that the a new contact is added.
   */
  @Test
  public void testNewContact() {
    running(testServer(PORT, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
      public void invoke(TestBrowser browser) {
        browser.goTo("http://localhost:" + PORT + "/login");
        LoginPage loginpage = new LoginPage(browser.getDriver(), PORT);
        browser.goTo(loginpage);
        loginpage.isAt();
        loginpage.login();
        IndexPage indexpage = new IndexPage(browser.getDriver(), PORT);
        indexpage.isAt();
        browser.goTo("http://localhost:" + PORT + "/newcontact");
        //indexpage.goToNewContact();
        NewContactPage newcontact = new NewContactPage(browser.getDriver(), PORT);
        newcontact.isAt();
        newcontact.makeContact("John", "Smith", "111-111-1111", "Work");
        assertThat(browser.pageSource()).contains("John");
        assertThat(browser.pageSource()).contains("Smith");


      }
    });
  }
  
}
