package HomeWork;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import Utility.*;
import ExtentReport.*;



public class ExerciseExcelRead {
	WebDriver driver;
	JSONReader json = new JSONReader();
	ExtentReport extentreport = new ExtentReport();
	 
@BeforeTest
	public void setup() throws IOException, ParseException
	{
	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(json.ReadJSONFile("URL",".\\Data\\data.json"));
		extentreport.setUpReport();
		
	}
@Test
public void testAdd() throws IOException, ParseException, InterruptedException
	{
		extentreport.startTestCase("Customer Adding");
		driver.findElement(By.name("uid")).sendKeys(json.ReadJSONFile("Username",".\\Data\\data.json"));
		driver.findElement(By.name("password")).sendKeys(json.ReadJSONFile("Password",".\\Data\\data.json"));
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
		driver.findElement(By.name("name")).sendKeys(json.ReadJSONFile("Name",".\\Data\\data.json"));	
		driver.findElement(By.cssSelector("input[value='m']")).click();
		driver.findElement(By.id("dob")).sendKeys(json.ReadJSONFile("DOB",".\\Data\\data.json"));
		driver.findElement(By.name("addr")).sendKeys(json.ReadJSONFile("Address",".\\Data\\data.json"));
		driver.findElement(By.name("city")).sendKeys(json.ReadJSONFile("City",".\\Data\\data.json"));
		driver.findElement(By.name("state")).sendKeys(json.ReadJSONFile("State",".\\Data\\data.json"));
		driver.findElement(By.name("pinno")).sendKeys(json.ReadJSONFile("Pin",".\\Data\\data.json"));
		driver.findElement(By.name("telephoneno")).sendKeys(json.ReadJSONFile("Mobile",".\\Data\\data.json"));
		driver.findElement(By.name("emailid")).sendKeys(json.ReadJSONFile("Email",".\\Data\\data.json"));
		driver.findElement(By.name("password")).sendKeys(json.ReadJSONFile("Password",".\\Data\\data.json"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
		Thread.sleep(2000);
		String webTitle = driver.getTitle();
		
	if (webTitle.equals("Guru99 Bank Customer Registration Page"))
		{
			extentreport.logEventsPass("My titile is passing");			
		//Assert.assertEquals(webTitle, "Guru99 Bank New Customer Entry Page");
	
		}
		else 
		{
			extentreport.logEventsFail("This is failing");
			//Assert.assertEquals(webTitle, "Guru99 Bank Customer Registration Page");
		}
		
		extentreport.createFinalReport();
	}


@AfterTest
	public void tearDown() throws IOException
	{
	driver.close();
	driver.quit();
	}

}
