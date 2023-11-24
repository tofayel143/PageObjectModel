package pom.pagedrivers;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import pom.basedriver.PageDriver;
import pom.utililies.GetScreenShot;

public class LoginPage {
	
	ExtentTest test;
	public LoginPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getCurrentDriver(), this);
		this.test = test;
	}
	
	@FindBys({
		@FindBy(xpath="/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input"),
//		@FindBy(className="oxd-input oxd-input--active")
	})
	WebElement username;
	
	@FindBy(xpath="/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")
	WebElement password;
	
	@FindBy(xpath="/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")
	WebElement button;
	
	public void passCase(String massage) {
		test.pass("<p style=\"color:#85BC63; font-size: 13px\"><b>"+massage+"</b></p>");
	}
	 public void passCaseWirhSC(String massage, String screenShotName) throws IOException {
		 test.pass("<p style=\"color:#85BC63; font-size: 13px\"><b>"+massage+"</b></p>");
		 @SuppressWarnings("unused")
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), ""+screenShotName+"");
		 String dest = System.getProperty("user.dir") + "\\screenshots\\"+""+screenShotName+".png";
		 test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	 }
	
	public void failCase(String massage, String screenShotName) throws IOException {
		test.fail("<p style=\"color:#FFU5353; font-size: 13px\"><b>"+massage+"</b></p>");
		Throwable t = new InterruptedException("Exception");
		test.fail(t);
		@SuppressWarnings("unused")
		String screenShotPath = GetScreenShot.capture(PageDriver.getCurrentDriver(), ""+screenShotName+"");
		String dest = System.getProperty("user.dir") + "\\screenshots\\"+""+screenShotName+".png";
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		PageDriver.getCurrentDriver().quit();
	}
	
	public void login() throws InterruptedException, IOException {
		
		try {
			test.info("Please Enter username");
			if(username.isDisplayed()) {
				username.sendKeys("Admin");
				passCase("User Name send");
			}
		} catch(Exception e) {
			failCase("username is not locatable. please check the error massage", "usernameFail");
		}
		try {
			test.info("Please, Enter Password");
			if(password.isDisplayed()) {
				password.sendKeys("admin123");
				passCase("password send");
			}
		}catch (Exception e) {
			failCase("password wrong", "passwordFail");
		}
		try {
			test.info("Click on login button");
			if(button.isDisplayed()) {
				button.click();
				Thread.sleep(10000);
				passCaseWirhSC("Button click", "loginpass");
			}
		}catch (Exception e) {
			failCase("Login button is not clickable. Please check the error massage", "loginFail");
		}
	}

}
