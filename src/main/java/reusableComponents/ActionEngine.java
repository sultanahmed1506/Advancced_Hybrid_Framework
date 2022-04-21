package reusableComponents;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import testBase.DriverFactory;
import testBase.ExtentFactory;

public class ActionEngine {
	

	//Customized sendkeys method-> To log sendkeys message for every occ.
		public void sendKeys_custom(WebElement element, String fieldName, String valueToBeSent) {
			try {
				flash(element);
				element.sendKeys(valueToBeSent);
				//log success message in extent report
				if(fieldName.equalsIgnoreCase("LoginPasswordFIeld")) {
					ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"--> Ented value as: "+ "**********");
				}
				else {
					ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"--> Ented value as: "+valueToBeSent);
				}
			} catch (Exception e) {
				//log failure in extent
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Value enter in field: "+fieldName + " is failed due to exception: "+e);
			}
		}


		//custom click method to log evey click action in to extent report
		public void click_custom(WebElement element, String fieldName) {
			try {
				flash(element);
				element.click();
				//log success message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"--> Clicked Successfully! ");
			} catch (Exception e) {
				//log failure in extent
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field: " +fieldName +" due to exception: "+e);
			}
		}


		//clear data from field
		public void clear_custom(WebElement element,String fieldName) {
			try {
				element.clear();
				Thread.sleep(250);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"--> Data Cleared Successfully! ");
			} catch (Exception e) {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to clear Data on field: " +fieldName +" due to exception: "+e);

			} 
		}

		//custom mouseHover 
		public void moveToElement_custom(WebElement element,String fieldName){
			try{
				JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
				executor.executeScript("arguments[0].scrollIntoView(true);", element);
				Actions actions = new Actions(DriverFactory.getInstance().getDriver());		
				actions.moveToElement(element).build().perform();
				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"--> Mouse hovered Successfully! ");
				Thread.sleep(1000);
			}catch(Exception e){
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to hover mouse on field: " +fieldName +" due to exception: "+e);

			}
		}

		// Flash element using Javascript Executor
		public void flash(WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
	        String bgcolor = element.getCssValue("backgroundColor");
	        for (int i = 0; i < 15; i++) {
	            changeColor("rgb(0,200,0)", element);// 1
	            changeColor(bgcolor, element);// 2
	        }
	    }

		// Change element color using Javascript Executor
		private void changeColor(String color, WebElement element) {
			JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
	        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

	        try {
	            Thread.sleep(20);
	        } catch (InterruptedException e) {
	        }
	    }

		//check if element is Present
		public boolean isElementPresent_custom(WebElement element,String fieldName){
			boolean flag = false;
			try {
				flash(element);
				flag = element.isDisplayed();
				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"--> Presence of field is: "+ flag);
				return flag;
			} catch (Exception e) {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Checking for presence of field: " +fieldName +" not tested due to exception: "+e);
				return flag;
			}
		}


		//Select dropdown value value by visibleText
		public void selectDropDownByVisibleText_custom(WebElement element, String fieldName, String ddVisibleText) throws Throwable {
			try {
				Select s = new Select(element);
				s.selectByVisibleText(ddVisibleText);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"--> Dropdown Value Selected by visible text: "+ ddVisibleText);
			} catch (Exception e) {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
			}
		}

		//Select dropdown value value by value
		public void selectDropDownByValue_custom(WebElement element, String fieldName, String ddValue) throws Throwable {
			try {
				Select s = new Select(element);
				s.selectByValue(ddValue);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"--> Dropdown Value Selected by visible text: "+ ddValue);
			} catch (Exception e) {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
			}
		}

		//String Asserts
		public void assertEqualsString_custom(String expvalue, String actualValue, String locatorName) throws Throwable {
			try {
				if(actualValue.equals(expvalue)) {
					ExtentFactory.getInstance().getExtent().log(Status.PASS, "String Assertion is successful on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
				}else {
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, "String Assertion FAILED on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
					Assert.assertTrue(false);
				}
			} catch (Exception e) {
				Assert.assertTrue(false, e.toString());
			}
		}

		//Get text from webelement
		public String getText_custom(WebElement element, String fieldName) {
			String text = "";
			try {
				text = element.getText();
				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"--> Text retried is: "+ text);
				return text;
			} catch (Exception e) {		
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, fieldName+"--> Text not retried due to exception: "+ e);

			}
			return text;
		}
}
