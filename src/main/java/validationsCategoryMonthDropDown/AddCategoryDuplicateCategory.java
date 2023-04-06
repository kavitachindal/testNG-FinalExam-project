package validationsCategoryMonthDropDown;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddCategoryDuplicateCategory {

	WebDriver driver;
	String browser;
	String url;

	Random rnd = new Random();
	int generatedNum = rnd.nextInt(999);

//Test 1: Validate a user is able to add a category and once the category is added it should display.
	By Add_Category_BlankBoxSpace_Field = By.cssSelector("input[name='categorydata']");
	By Add_Category_Button_Field = By.cssSelector("input[value='Add category']");
	By Added_Category_Display_Field = By.xpath("/html/body/div[3]/a[68]/span");

	String inputName = "Kavita";

//Test 2: Validate a user is not able to add a duplicated category. If it does then the following message will display: 
	// "The category you want to add already exists: <duplicated category name>."
	By Add_Duplicate_Category_Field = By.cssSelector("input[name='categorydata']");
	By Add_Duplicate_Category_Button_Field = By.cssSelector("input[value='Add category']");
	By Duplicate_Category_Message_Field = By.xpath("/html/body");
	By Duplicate_Category_Name = By.xpath("/html/body/span[1]");

//Test 3: Validate the month drop down has all the months (jan, feb, mar ...) in the Due Date dropdown section.
	By Month_Dropdown_Field = By.xpath("//*[@id=\"extra\"]/select[3]");

	@BeforeMethod
	public void init() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("https://techfios.com/test/104/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void AddCategoryTest() {

		driver.findElement(Add_Category_BlankBoxSpace_Field).sendKeys(inputName + generatedNum);
		driver.findElement(Add_Category_Button_Field).click();
		driver.findElement(Duplicate_Category_Message_Field).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Added_Category_Display_Field));
		Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/a[68]/span")).isDisplayed());
		System.out.println(driver.findElement(By.xpath("/html/body/div[3]/a[68]/span")).isDisplayed());
	}

	public void selectFromDrowdown(By locator, String visibleText) {
		Select sel = null;
		sel.selectByVisibleText(visibleText);

	}

	@Test(priority = 1)
	public void DuplicateCategoryTest() {

		driver.findElement(Add_Duplicate_Category_Field).sendKeys(inputName + generatedNum);
		driver.findElement(Add_Duplicate_Category_Button_Field).click();

		Assert.assertTrue(driver.findElement(By.xpath("/html/body")).isDisplayed());
		System.out.println(driver.findElement(By.xpath("/html/body")).isDisplayed());
		System.out.println(driver.findElement(By.xpath("/html/body/span[1]")).isDisplayed());

	}

	@Test(priority = 2)
	public void Monthdropdown() {

		Select sel = new Select(driver.findElement(Month_Dropdown_Field));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> allelements = sel.getOptions();

		System.out.println(allelements.size());

		for (int i = 0; i < allelements.size(); i++) {
			System.out.println(allelements.get(i).getText());
		}
	}

	//@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();
	}

}
