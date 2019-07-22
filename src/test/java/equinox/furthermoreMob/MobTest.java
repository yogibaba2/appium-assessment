package equinox.furthermoreMob;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


public class MobTest {
	DesiredCapabilities caps;
	AndroidDriver driver;
	
	@BeforeSuite
	public void beforeSuite() throws IOException, InterruptedException {
		
	}
	
	@Parameters({ "deviceName_","UDID_", "URL_", "WDALOCALPORT_", "platformName_","platformVersion_"})
	@BeforeTest
	public void beforeTest(String deviceName_,String UDID_,String URL_, String WDALOCALPORT_, String platformName_, String platformVersion_) {
		caps = new DesiredCapabilities();
		caps.setCapability("deviceName", deviceName_);
		caps.setCapability("udid", UDID_); //Give Device ID of your mobile phone
		caps.setCapability("platformName", platformName_);
		caps.setCapability("platformVersion", platformVersion_);
//		caps.setCapability(MobileCapabilityType.BROWSER_NAME, browserName_);
//		caps.setCapability("WDALOCALPORT", WDALOCALPORT_);
		/*caps.setCapability("appPackage", "com.android.chrome");
		caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");*/
		//caps.setCapability("noReset", "true");
		System.out.println(System.getProperty("user.dir"));
		caps.setCapability("app", System.getProperty("user.dir")+"//VodQA.apk");
		caps.setCapability("appPackage", "com.vodqareactnative");
		caps.setCapability("appActivity", "com.vodqareactnative.MainActivity");
		caps.setCapability("automationName", "uiautomator2");
		
		//Instantiate Appium Driver
		try {
			driver = new AndroidDriver(new URL("http://"+URL_), caps);
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	@Parameters({"deviceType_"})
	@Test(testName="Test1")
	public void VerifyHomePage(String deviceType_) throws Exception {  
	
		driver.findElementByAccessibilityId("login").click();
		WebDriverWait wait = new WebDriverWait(driver, 5000, 500);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ScrollView[@content-desc='scrollView']")));
		
		Dimension size = driver.manage().window().getSize();
		int x1 = size.width/2;
		int y1 = size.height/2;
		int Y_start = (int) ((size.height) * 0.2);
		int Y_end = (int) ((size.height) * 0.8);
		
		TouchAction action = new TouchAction(driver);
		action.longPress(x1, Y_end).moveTo(x1, Y_start).release();
		action.perform();
		
		driver.findElementByXPath("//android.view.ViewGroup[@content-desc='carousel']").click();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@class = 'android.widget.HorizontalScrollView']")));
		size = driver.manage().window().getSize();
		int x = size.width/2;
		int y = size.height/2;
		int x_start = (int) ((size.width) * 0.2);
		int x_end = (int) ((size.width) * 0.8);
		
		TouchAction action1 = new TouchAction(driver);
		action1.longPress(x_end, y).moveTo(x_start, y).release();
		action1.perform();
		
	}
	
	
}
