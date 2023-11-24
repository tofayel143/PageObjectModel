package pom.basedriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseDriver {
	
	protected static String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

	WebDriver driver;

	@BeforeSuite
	public void startBrowser() {
		String BrowserName = System.getProperty("browser", "firefox");

		if (BrowserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();

		} else if (BrowserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}
		PageDriver.getInstance().setDriver(driver);
	}

	@AfterSuite
	public void close() {
		driver.close();
	}

}
