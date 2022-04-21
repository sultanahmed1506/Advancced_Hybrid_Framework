package pageObjects;

import org.openqa.selenium.By;

import org.testng.Assert;
import testBase.DriverFactory;
import testBase.TestBase;

public class LoginPageObjects extends TestBase {
	By EMAIL = By.id("txtUsername");
	By PASSWORD = By.id("txtPassword");
	By LOGIN_BTN = By.xpath("//input[@id=\"btnLogin\"]");
	private By LINK1 = By.id("details-button");
    private By LINK2 = By.id("proceed-link");
	private By pnlError = By.xpath("//span[@id=\"pnlError\"]");


	//Valid login to Recon App
	public void Dologin(String email, String password) {
		click_custom(DriverFactory.getInstance().getDriver().findElement(LINK1), "details-button");
		click_custom(DriverFactory.getInstance().getDriver().findElement(LINK2), "proceed-link");
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(EMAIL), "LoginEmailFIeld", email);
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(PASSWORD), "LoginPasswordFIeld", password);
		click_custom(DriverFactory.getInstance().getDriver().findElement(LOGIN_BTN), "LoginButton");
	}

	public void ErrorLoginMessageExist() {
		Assert.assertTrue(isElementPresent_custom(DriverFactory.getInstance().getDriver().findElement(pnlError),
				"Error Message for Invalid username and password is displayed"));
	}

	public void getLoginPageTitle(){
		Assert.assertEquals(DriverFactory.getInstance().getDriver().getTitle(),"Unified Wealth Platform");
	}

}