package okcs;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import junit.framework.Assert;
import utilities.ElementFactory;
import utilities.genericMethods;

public class AddArticleTests extends Base {
	
	public AddArticleTests()
	{
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
	}
	
	@Test
	public void addNewArticle() throws IOException, InterruptedException
	{
		Base.getDriver().get(Base.config.getString("IMURL"));
		ElementFactory.getElement("LoginPage.userName").sendKeys(Base.config.getString("accountUser"));
		ElementFactory.getElement("LoginPage.password").sendKeys(Base.config.getString("accountUserPwd"));
		ElementFactory.getElement("LoginPage.loginBtn").click();
		ElementFactory.getElement("AuthoringPage.ContentTab").click();
		Base.getDriver().findElement(By.xpath("//div[.='FAQ']/following-sibling::a[contains(text(),'Add')]")).click();
		genericMethods.selectdropdown("AddContentPage.selectLocale", "en-US");
		ElementFactory.getElement("AddContentPage.textField").sendKeys(Base.testdata.getString("AddNewArticlePage.SUMMARY"));
		Base.getDriver().switchTo().frame(ElementFactory.getElement("AddContentPage.RTAIframe"));
		Base.getDriver().findElement(By.tagName("body")).sendKeys(Base.testdata.getString("AddNewArticlePage.QUESTION"));
		//ElementFactory.getElement("AddContentPage.RTA").sendKeys(Base.testdata.getString("AddNewArticlePage.QUESTION"));
		
		ElementFactory.getElement("AddContentPage.date").sendKeys(genericMethods.getDateinMMDDYYYY());
		ElementFactory.getElement("AddContentPage.dateTime").sendKeys(genericMethods.getDateinMMDDYYYY());
		ElementFactory.getElement("AddContentPage.dateTimeTime").sendKeys(genericMethods.getTimeinHHMM());
		
		ElementFactory.getElement("AddContentPage.float").sendKeys(Base.testdata.getString("AddNewArticlePage.FLOAT"));
		ElementFactory.getElement("AddContentPage.integer").sendKeys(Base.testdata.getString("AddNewArticlePage.INT"));
		ElementFactory.getElement("AddContentPage.time").sendKeys(genericMethods.getTimeinHHMM());
		
		ElementFactory.getElement("AddContentPage.EventStartDate").sendKeys(genericMethods.getDateinMMDDYYYY());
		ElementFactory.getElement("AddContentPage.EventStarTime").sendKeys(genericMethods.getTimeinHHMM());
		ElementFactory.getElement("AddContentPage.EventEndDate").sendKeys(genericMethods.getDateinMMDDYYYY());
		ElementFactory.getElement("AddContentPage.EventEndTime").sendKeys(genericMethods.getTimeinHHMM());
		
		ElementFactory.getElement("AddContentPage.Expandviews").click();
		Base.getDriver().findElement(By.xpath("//span[.='"+Base.testdata.getString("AddNewArticlePage.VIEWS")+"']/preceding-sibling::input")).click();
		
		genericMethods.addItems("usergroups", Base.testdata.getString("AddNewArticlePage.USERGROUPS"));
		genericMethods.addItems("products", Base.testdata.getString("AddNewArticlePage.PRODUCTS"));
		genericMethods.addItems("categories", Base.testdata.getString("AddNewArticlePage.CATEGORIES"));
		
		ElementFactory.getElement("AddContentPage.DisplayOnDate").sendKeys(genericMethods.getDateinMMDDYYYY());
		ElementFactory.getElement("AddContentPage.DisplayOnTime").sendKeys(genericMethods.getTimeinHHMM());
		ElementFactory.getElement("AddContentPage.RemoveAfterDate").sendKeys(genericMethods.getDateinMMDDYYYY());
		ElementFactory.getElement("AddContentPage.RemoveAfterTime").sendKeys(genericMethods.getTimeinHHMM());
		genericMethods.selectdropdown("AddContentPage.RemoveAfterAMPM", "PM");
		ElementFactory.getElement("AddContentPage.ReviewDate").sendKeys(genericMethods.getFutureDateinMMDDYYYY(5,5,5));
		ElementFactory.getElement("AddContentPage.ReviewTime").sendKeys(genericMethods.getTimeinHHMM());
		
		genericMethods.selectdropdown("AddContentPage.Priority", Base.testdata.getString("AddNewArticlePage.PRIORITYORDER"));
		genericMethods.selectdropdown("AddContentPage.DisplayPosition", Base.testdata.getString("AddNewArticlePage.DISPLAYPOSITION"));
		ElementFactory.getElement("AddContentPage.ReasonForEdit").sendKeys(Base.testdata.getString("AddNewArticlePage.REASONFOREDIT"));
		ElementFactory.getElement("AddContentPage.checkBox").click();
//		ElementFactory.getElement("AddContentPage.file").click();
		JavascriptExecutor js = (JavascriptExecutor)Base.getDriver();
		js.executeScript("arguments[0].click();",ElementFactory.getElement("AddContentPage.file"));
		Thread.sleep(5000);
		Runtime.getRuntime().exec("C:\\backup\\resources\\okcs\\src\\test\\java\\autoITScripts\\selectFile.exe");
		Thread.sleep(31000);
//		ElementFactory.getElement("AddContentPage.SaveAndPublish").click();
		Base.getDriver().quit();
		Base.getDriver().get(Base.config.getString("IMURL"));
		ElementFactory.getElement("LoginPage.userName").sendKeys(Base.config.getString("accountUser"));
		ElementFactory.getElement("LoginPage.password").sendKeys(Base.config.getString("accountUserPwd"));
		ElementFactory.getElement("LoginPage.loginBtn").click();
		ElementFactory.getElement("AuthoringPage.GoToAutoSavedContent").click();
		ElementFactory.getElement("AuthoringPage.ContentTab.AutoSavedContentLink").click();
		System.out.println("TF text is: "+ElementFactory.getElement("AddContentPage.textField").getAttribute("value"));
		Assert.assertTrue("Text Field content is not auto saved", ElementFactory.getElement("AddContentPage.textField").getAttribute("value").equals(Base.testdata.getString("AddNewArticlePage.SUMMARY")));
		
		
	}
	
	@AfterMethod
	public void teardown()
	{
		Base.getDriver().close();
	}
	

}
