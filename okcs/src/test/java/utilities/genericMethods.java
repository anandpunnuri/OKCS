package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import okcs.Base;

public class genericMethods {
	static WebDriverWait wait = new WebDriverWait(Base.getDriver(),60);
	
	public static void selectdropdown(String locator, String value)
	{
		String textToBeSelected=null;
		Select select = new Select(ElementFactory.getElement(locator));
		for(WebElement ele:select.getOptions())
		{
			if(ele.getText().contains(value))
			{
				textToBeSelected=ele.getText();
			}
		}
		select.selectByVisibleText(textToBeSelected);
	}
	
	public static String getDateinMMDDYYYY()
	{
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println("Date in MM/DD/YYYY format : "+dateformat.format(date));
		return dateformat.format(date);
	}
	
	public static String getFutureDateinMMDDYYYY(int days, int months, int years)
	{
		SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR,years);
		c.add(Calendar.MONTH, months);
		c.add(Calendar.DATE, days);
		return dateformat.format(c.getTime());
	}
	
	
	public static String getTimeinHHMM()
	{
		return (new SimpleDateFormat("hh:mm").format(new Date()));
	}
	
	public static void addItems(String WhatTypeOfItems, String items)
	{
		String[] itemlist = items.split(",");
		int framenum = Base.getDriver().findElements(By.tagName("iframe")).size();
		System.out.println("number of iframes: "+framenum);
		
		if(WhatTypeOfItems.contains("usergroups"))
		{
			for(String item:itemlist)
			{
			System.out.println("ug to be selected: "+item);
			for(int i=0;i<framenum;i++)
			{
				Base.getDriver().switchTo().defaultContent();
				Base.getDriver().switchTo().frame(i);
				if(Base.getDriver().findElements(By.xpath("//span[.='Available User Groups']/../../../following-sibling::tr//span[.='"+item+"']")).size() > 0)
				{
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Available User Groups']/../../../following-sibling::tr//span[.='"+item+"']"))).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Available User Groups')]/../../../following-sibling::tr//a[contains(text(),'Add')]"))).click();
					//					ElementFactory.getElement("AddContentPage.addUserGroup").click();
					break;
				}
			}
			Base.getDriver().switchTo().defaultContent();
			}
		}
		
		if(WhatTypeOfItems.contains("products"))
		{
			for(String item:itemlist)
			{
				System.out.println("products to be selected: "+item);
				for(int i=0;i<framenum;i++)
				{
					Base.getDriver().switchTo().defaultContent();
					Base.getDriver().switchTo().frame(i);
					if(Base.getDriver().findElements(By.xpath("//span[.='Available Products']/../../../following-sibling::tr//span[.='"+item+"']")).size() > 0)
					{
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Available Products']/../../../following-sibling::tr//span[.='"+item+"']"))).click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Available Products')]/../../../following-sibling::tr//a[contains(text(),'Add')]"))).click();
						//ElementFactory.getElement("AddContentPage.addProduct").click();
						break;
					}
				}
				Base.getDriver().switchTo().defaultContent();
			}	
		}
		
		if(WhatTypeOfItems.contains("categories"))
		{
			for(String item:itemlist)
			{
				System.out.println("categories to be selected: "+item);
				for(int i=0;i<framenum;i++)
				{
					Base.getDriver().switchTo().defaultContent();
					Base.getDriver().switchTo().frame(i);
					if(Base.getDriver().findElements(By.xpath("//span[.='Available Categories']/../../../following-sibling::tr//span[.='"+item+"']")).size() > 0)
					{
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[.='Available Categories']/../../../following-sibling::tr//span[.='"+item+"']"))).click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Available Categories')]/../../../following-sibling::tr//a[contains(text(),'Add')]"))).click();						
						//ElementFactory.getElement("AddContentPage.addCategory").click();
						break;
					}
				}
				Base.getDriver().switchTo().defaultContent();
			}
		}
		
	}
}
