package seleniumwithmukul.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait
				 (driver, Duration.ofSeconds(7));
		String productName = "ZARA COAT 3";
		driver.get("https://rahulshettyacademy.com/client");
		//gmail - devEngineer@test.com, firstname = dev engineer, lastname = shyam, 
		//phone.no - 8080808080, password - Engineer@18
		
		driver.findElement(By.id("userEmail"))
				.sendKeys("devEngineer@test.com");
		driver.findElement(By.id("userPassword"))
				.sendKeys("Engineer@18");
		driver.findElement(By.id("login"))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(
						By.cssSelector(".mb-3")));
		List<WebElement> products = driver
				.findElements(By.cssSelector(".mb-3"));
		WebElement productZara = products.stream().filter(product->
				product.findElement(By.cssSelector("b"))
				.getText().equals(productName))
				.findFirst().orElse(null);
		productZara.findElement(
				By.cssSelector(".card-body button:last-of-type"))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(
						By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions
				.invisibilityOf(driver.findElement
						(By.cssSelector(".ng-animating"))));
		driver.findElement(
				By.cssSelector("[routerlink*='cart']"))
				.click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div.cart h3"));
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equals(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
//		driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' India']/parent::button")));
		driver.findElement(By.cssSelector("button.ta-item:nth-of-type(2)")).click();
//		driver.findElement(By.xpath("//span[text()=' India']/parent::button")).click();
//		Thread.sleep(5000);
		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("action__submit")));
//		driver.findElement(By.className("action__submit")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", submitButton);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", submitButton);
//		driver.findElement(By.cssSelector("a.action__submit")).click();
		String confirmMessage = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
//		Assert.assertEquals(confirmMessage, " Thankyou for the order. ");
	}

}
