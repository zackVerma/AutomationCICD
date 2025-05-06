package seleniumwithmukul.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumwithmukul.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+"//src//main//java//seleniumwithmukul//resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		Boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless"));
		if(browserName.equalsIgnoreCase("CHROME")) {
			ChromeOptions options = new ChromeOptions();
			if(isHeadless)options.addArguments("headless");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}else if(browserName.equalsIgnoreCase("EDGE")) {
			EdgeOptions options = new EdgeOptions();
			if(isHeadless)options.addArguments("headless");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
		}
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap (String filePath) throws IOException {
		//Json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8);
		//String to HashMap Jacksone Databind
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String filePath = System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		File file = new File(filePath);
		FileUtils.copyFile(source, file);
		return filePath;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		//Landing Page Object
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
}
