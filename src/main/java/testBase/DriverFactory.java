package testBase;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
		

		//Singleton design Pattern - only one instance exist ever, provide global access to that --
		//--instance by creating getInstance method
		//private constructor so that no one else can create object of this class
		private DriverFactory() {
			
		}
		//Create instance of the current class (DriverFactory)
		private static DriverFactory instance  = new DriverFactory();
		
		//Private constructor so that no one else  can create object of this class
		public static DriverFactory getInstance() {
			return instance;
		}
		
		
		//factory design pattern --> define separate factory methods for creating objects and create objects by calling that methods
		ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
		
		public WebDriver getDriver() {
			return driver.get();
		}
		
		public void setDriver(WebDriver driverParm) {
			driver.set(driverParm);
		}
		
		
		public void closeBrowser() {
			driver.get().quit();
			driver.remove();
		}
}
