package Generic_Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base_Class {
	
	public Process process;
	public  AppiumDriver driver;
	
	Utility_Class c1= new Utility_Class();
	//start the server
	public void Start_Server() throws IOException, InterruptedException
	{
		String Start_server="C:\\appium_server\\Appium\\node.exe  C:\\appium_server\\Appium\\node_modules\\appium\\bin\\appium.js";
		
		process = Runtime.getRuntime().exec(Start_server);
		
		if(process!= null)
		{
			System.out.println("Started Appium Server");
		}
		else
		{
			System.out.println("NOT Started the Appium Server");
		}
		
		Thread.sleep(12000);	
		
		
	}
	
	//Init app
	public void Init_app() throws InterruptedException, IOException
	{
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		capabilities.setCapability("deviceName","GT-I9300I");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("platformVersion","4.4.4");
		
		capabilities.setCapability("appPackage",c1.reading_properties("Package_name"));
		capabilities.setCapability("appActivity",c1.reading_properties("Activity_name"));
		
		
		driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(4000);
		
	}
	
	
	//Explicit wait
	
	public void Explicit_wait(WebElement ele,long T1)
	{
		WebDriverWait wait = new WebDriverWait(driver, T1);
		wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
		
		
	}
	
	//screenshot
	
	public void Capture_Screenshot1(String TC_ID, String Order) throws IOException
	{
		Date date= new Date();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		File file= new File(df.format(date) +".png");
		
		
		TakesScreenshot screenshot= (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(screenshotAs, new File("D:\\Aug_BB_Project\\Screenshot\\" +TC_ID+"-"+Order+"-"+file));
		
	}
	
	
	//Stop server
	
	public void Stop_Server() throws InterruptedException
	{

		if(process!=null)
		{
			process.destroy();
			Thread.sleep(6000);
			System.out.println("Stopped the appium Server");
		}
	}
	
	

}
