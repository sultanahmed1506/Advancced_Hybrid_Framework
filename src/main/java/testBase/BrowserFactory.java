package testBase;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	//create webdriver object for given browser and return the webdriver instance
		public WebDriver createBrowserInstance(String browser) throws MalformedURLException {

		
			//RemoteWebDriver driver = null;
			WebDriver driver = null;

			if(browser.equalsIgnoreCase("Chrome")) {

				WebDriverManager.chromedriver().setup();
				System.setProperty("webdriver.chrome.silentOutput", "true");


				ChromeOptions options = new ChromeOptions();
				options.addArguments("--incognito");
				driver = new ChromeDriver(options);

			}else if (browser.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
			//	FirefoxOptions options = new FirefoxOptions();
			//	foptions.addArguments("-private");
				
				driver = new RemoteWebDriver(new URL("http:ValidIpAddress:4444/wd/hub"), DesiredCapabilities.firefox());		        

				
				//driver = new FirefoxDriver(options);

			} if (browser.equalsIgnoreCase("ie")) {

				WebDriverManager.iedriver().setup();
				InternetExplorerOptions iOptions = new InternetExplorerOptions();
				iOptions.addCommandSwitches("-private");

				driver = new InternetExplorerDriver(iOptions);
			}
			return driver;
		}

}
