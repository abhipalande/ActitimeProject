package com.Actitime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Actitime.ObjectRepository.HomePage;
import com.Actitime.ObjectRepository.LoginPage;

public class BaseClass {
	public static WebDriver driver;
	@BeforeSuite
	public void databaseconnection() {
		Reporter.log("database connected",true);
	}
	@BeforeClass
	public void launchrowser() {
		 driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demo.actitime.com");
		Reporter.log("Browser launched successfully",true);
	}
	@BeforeMethod
	public void loginToActitime() throws IOException {
		LoginPage lp=new LoginPage(driver);
		FileLibrary f=new FileLibrary();
		String un = f.readDataFromProperty("username");
		lp.getUntbx().sendKeys(un);
		String pw = f.readDataFromProperty("password");
		lp.getPwtbx().sendKeys(pw);
		lp.getLgbtn().click();
		Reporter.log("logged in successfully",true);
	}
	
	@AfterMethod
	public void logoutFromActitime() {
		HomePage hp=new HomePage(driver);
		hp.getLogoutlink().click();
		Reporter.log("logged out successfully",true);
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
		Reporter.log("Browser Closed Successfully",true);
	}
	
	@AfterSuite
	public void databaseDisconnect() {
		Reporter.log("database disconnected",true);
	}

}
