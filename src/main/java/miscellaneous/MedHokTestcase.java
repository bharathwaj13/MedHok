package miscellaneous;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class MedHokTestcase {
	@Test
	public void medHok() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "G:/Selenium/Revision/chromedriver.exe");
		ChromeOptions op=new ChromeOptions();
		op.addArguments("disable-infobars");
		op.addArguments("--disable-notifications");
		ChromeDriver driver=new ChromeDriver(op);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to("http://www.medhok.com/");
		boolean isLogoPresent=driver.findElementByXPath("//a[@class='logo']/img").isDisplayed();
		if(isLogoPresent)
			System.out.println("Pass - The logo is present");
		else
			System.out.println("Fail - The Logo is not present");
		boolean isAboutMedHoc = driver.findElementByXPath("//a[text()='About MedHOK']").isDisplayed();
		if(isAboutMedHoc)
			System.out.println("Pass - About MedHoc Link is present");
		else
			System.out.println("Fail - About MedHoc Link is not present");

		List<String> options=new ArrayList<String>();
		Actions builder=new Actions(driver);
		String[] allOptions= {"Management Team","Certifications","Culture","Careers","Contact Us","Service Terms"};
		builder.moveToElement(driver.findElementByXPath("//a[text()='About MedHOK']")).perform();
		for(int i=1;i<=6;i++)
		{
			options.add(driver.findElementByXPath("//a[text()='About MedHOK']/following-sibling::ul[@class='hs-menu-children-wrapper']/li["+i+"]/a").getText());
		}
		int counter=0;
		boolean bFlag=true;
		for (String each : options) {
			if (!each.equalsIgnoreCase(allOptions[counter++]))
			{
				bFlag=false;
				break;
			}

		}
		if(bFlag)
			System.out.println("Pass - All Options are present in Correct Order");
		else
			System.out.println("Fail - All Options are not present in Correct Order");
		for(int i=1;i<=6;i++)
		{
			String linkName="";
			Actions builder1=new Actions(driver);
			builder1.moveToElement(driver.findElementByXPath("//a[text()='About MedHOK']")).perform();
//			Thread.sleep(4000);
			linkName=driver.findElementByXPath("//a[text()='About MedHOK']/following-sibling::ul/li["+i+"]/a").getText();
			driver.findElementByXPath("//a[text()='About MedHOK']/following-sibling::ul/li["+i+"]/a").click();
			switch(i)
			{
			case 1:
				if(driver.findElementByXPath("//div[@class='headline-left headline-light']//span[text()='Meet Our Team']").isDisplayed())
					System.out.println("Pass - "+linkName+" landed in the correct page");
				else
					System.out.println("Fail - "+linkName+" did not land in the correct page");
				break;
			case 2:
				if(driver.findElementByXPath("//div[@class='headline-left headline-light']//span[text()='Compliant & Secure']").isDisplayed())
					System.out.println("Pass - "+linkName+" landed in the correct page");
				else
					System.out.println("Fail - "+linkName+" did not land in the correct page");
				break;
				
			case 3:
				if(driver.findElementByXPath("//div[@class='cell-wrapper layout-widget-wrapper']//span[text()='Culture']").isDisplayed())
					System.out.println("Pass - "+linkName+" landed in the correct page");
				else
					System.out.println("Fail - "+linkName+" did not land in the correct page");
				break;
				
			case 4:
				if(driver.findElementByXPath("//div[@class='span8 widget-span widget-type-raw_jinja ']//h1[text()='Careers']").isDisplayed())
					System.out.println("Pass - "+linkName+" landed in the correct page");
				else
					System.out.println("Fail - "+linkName+" did not land in the correct page");
				break;
				
			case 5:
				if(driver.findElementByXPath("//div[@class='headline-left headline-light']//h1[2]/span[text()[contains(.,'Contact')]]").isDisplayed())
					System.out.println("Pass - "+linkName+" landed in the correct page");
				else
					System.out.println("Fail - "+linkName+" did not land in the correct page");
				
				if(driver.findElementByXPath("//i[@class='fa fa-home']/..").getText().contains("5550 W. Idlewild Avenue, Suite 150 Tampa, FL 33634"))
					System.out.println("Pass - Correct Address is present");
				else
					System.out.println("Fail - Address is incorrect");
				
				if(driver.findElementByXPath("//i[@class='fa fa-envelope']/..//a").getText().equalsIgnoreCase("Hello@medhok.com"))
					System.out.println("Pass - Correct Email ID is present");
				else
					System.out.println("Fail - Email ID is incorrect");
				break;
			case 6:
				if(driver.findElementByXPath("//span[@class='hs_cos_wrapper hs_cos_wrapper_widget hs_cos_wrapper_type_rich_text']//h2[text()[contains(.,'Service Terms & Policies')]]").isDisplayed())
					System.out.println("Pass - "+linkName+" landed in the correct page");
				else
					System.out.println("Fail - "+linkName+" did not land in the correct page");
				break;
			}
		}
		driver.close();
	}

}
