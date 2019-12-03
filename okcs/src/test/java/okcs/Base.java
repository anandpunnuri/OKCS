package okcs;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static XMLConfiguration config;
	public static XMLConfiguration objectconfig;
	public static XMLConfiguration testdata;
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public Base()
	{
		try
		{
			config=new XMLConfiguration(new File("./src/test/resources/config.xml"));
			objectconfig = new XMLConfiguration(new File("./src/test/resources/objectRepository.xml"));
			testdata = new XMLConfiguration(new File("./src/test/resources/TestData.xml"));
		}
		catch(ConfigurationException ce)
		{
			System.out.println("Configuration Exception in Base class: "+ce.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Exception in Base class: "+e.getMessage());
		}
	}
	
	public static WebDriver getDriver()
	{
		if(driver==null || driver.toString().contains("(null)"))
		{
			if(config.getString("browser").equalsIgnoreCase("googlechrome"))
			{
//				WebDriverManager.chromedriver().setup();
				System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.setAcceptInsecureCerts(true);
				driver = new ChromeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				wait = new WebDriverWait(driver, 60);
			}
			
			if(config.getString("browser").equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
//				System.setProperty("webdriver.gecko.driver", "./okcs/src/test/resources/geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();
				options.setAcceptInsecureCerts(true);
				driver = new FirefoxDriver(options);
				driver.manage().window().maximize();
				wait = new WebDriverWait(driver, 60);
			}
		}
		return driver;
	}
}
