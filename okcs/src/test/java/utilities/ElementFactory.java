package utilities;

import org.apache.commons.configuration.XMLConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import okcs.Base;

public class ElementFactory extends Base {

//	private static WebDriver driver = Base.getDriver();
	@SuppressWarnings("unused")
	private static XMLConfiguration config = Base.config;
	private static XMLConfiguration objectconfig = Base.objectconfig;
//	public static WebDriverWait wait;
//	public static WebDriverWait wait = new WebDriverWait(Base.getDriver(), 60);

	public static WebElement getElement(String locator) {
		// wait = new WebDriverWait(Base.driver, 60);
		String type = objectconfig.getString(locator + "[@type]");
		WebElement ele = null;		
		boolean elementFound = false;
//		if(Base.getDriver()==null || Base.getDriver().toString().contains("(null)"))
//		{
//		wait = new WebDriverWait(Base.getDriver(), 60);
//		}
		switch (type) {
		case "xpath":
			Base.getDriver().switchTo().defaultContent();
			if (Base.getDriver().findElements(By.tagName("iframe")).size() > 0) {
				for (int i = 0; i < Base.getDriver().findElements(By.tagName("iframe")).size(); i++) {
					Base.getDriver().switchTo().defaultContent();
					Base.getDriver().switchTo().frame(i);
					if (Base.getDriver().findElements(By.xpath(objectconfig.getString(locator))).size() > 0) {
						ele = wait.until(ExpectedConditions
								.visibilityOfElementLocated(By.xpath(objectconfig.getString(locator))));
						elementFound = true;
						break;
					}
				}

				if (!elementFound) {
					Base.getDriver().switchTo().defaultContent();
					ele = wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(objectconfig.getString(locator))));
				}
			} else {
				ele = wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath(objectconfig.getString(locator))));
			}
			break;

		case "id":
			Base.getDriver().switchTo().defaultContent();
			if (Base.getDriver().findElements(By.tagName("iframe")).size() > 0) {
				for (int i = 0; i < Base.getDriver().findElements(By.tagName("iframe")).size(); i++) {
					Base.getDriver().switchTo().defaultContent();
					Base.getDriver().switchTo().frame(i);
					if (Base.getDriver().findElements(By.id(objectconfig.getString(locator))).size() > 0) {
						ele = wait.until(
								ExpectedConditions.visibilityOfElementLocated(By.id(objectconfig.getString(locator))));
						elementFound = true;
						break;
					}
				}
				if (!elementFound) {
					Base.getDriver().switchTo().defaultContent();
					ele = wait.until(
							ExpectedConditions.visibilityOfElementLocated(By.id(objectconfig.getString(locator))));
				}
			} else {
				ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objectconfig.getString(locator))));
			}

			break;
		}
		return ele;
	}
}
