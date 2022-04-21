package pageObjects;

import org.openqa.selenium.By;
import org.testng.Assert;

import testBase.DriverFactory;
import testBase.TestBase;

public class ReconPageObjects extends TestBase {
	
	By userExist = By.xpath("//span[@class=\"ng-binding\"]");
	By logoutLink = By.xpath("//a[@title=\"Log out\"]");
	By logoutConfBtn = By.xpath("//button[@class=\"btn btn-primary pull-right\"]");
	//private By cashDonutClick = By.xpath("//div[@data-k-options=\"vm.optionsCash\"]//*[name()=\"svg\"][1]/*[name()=\"text\"][1]");
	By cashDonut = By.xpath("//div[@data-k-options=\"vm.optionsCash\"]//*[name()=\"svg\"][1]/*[name()=\"g\"][1]");
	By cashDonutClick = By.xpath("//div[@data-k-options=\"vm.optionsCash\"]//*[name()=\"svg\"][1]/*[name()=\"text\"][1]");
	

	public void checkIfUserExistInDashboard() {
		Assert.assertTrue(isElementPresent_custom(DriverFactory.getInstance().getDriver().findElement(userExist), "User Exist in Dashboard"));
	}

	public void checkIfCashDonutExistInDashboard() throws InterruptedException {
		DriverFactory.getInstance().getDriver().switchTo().frame("rmFrame");
		Thread.sleep(2000);
		Assert.assertTrue(isElementPresent_custom(DriverFactory.getInstance().getDriver().findElement(cashDonut), "Cash Donut Exist in Dashboard"));
		String value = getText_custom(DriverFactory.getInstance().getDriver().findElement(cashDonutClick), "Get Value from Cashdonut");
		System.out.println("Value from cash donut :" +value);
		click_custom(DriverFactory.getInstance().getDriver().findElement(cashDonutClick), "CashDonut clicked");
		DriverFactory.getInstance().getDriver().switchTo().defaultContent();
	}
	
	

	public void logoutFromRecon() {
		click_custom(DriverFactory.getInstance().getDriver().findElement(logoutLink), "Logout link");
		click_custom(DriverFactory.getInstance().getDriver().findElement(logoutConfBtn), "Logout confirmation link");
	}
}
