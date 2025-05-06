package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//if you want to run specific test case use tags = "@Regression"

@CucumberOptions(features="src/test/java/cucumber", glue="seleniumwithmukul.stepDefinitions",
monochrome=true,tags = "@Regression",plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	
	
}
