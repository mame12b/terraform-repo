package test;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicValidation1 {

    public static WebDriver driver;
    String url = "https://www.facebook.com";

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
            "--headless",
            "--no-sandbox",
            "--disable-dev-shm-usage",
            "--window-size=1920,1080"
        );
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void welcomeMessageValidation() {
        String expectedResult = "Facebook helps you connect and share with the people in your life.";
        String actualResult = driver.findElement(By.xpath("//h2[contains(@class,'_8eso')]")).getText();

        Reporter.log("Expected Result = " + expectedResult);
        Reporter.log("Actual Result = " + actualResult);

        assertTrue(actualResult.equals(expectedResult), "Mismatch in the welcome message");
    }

    @Test
    public void emailTextBoxPlaceholderValidation() {
        String expectedResult = "Email address or phone number";
        String actualResult = driver.findElement(By.id("email")).getAttribute("placeholder");

        Reporter.log("Expected Result = " + expectedResult);
        Reporter.log("Actual Result = " + actualResult);

        assertTrue(actualResult.equals(expectedResult), "Mismatch in the email placeholder");
    }

    @Test
    public void passwordBoxPlaceholderValidation() {
        String expectedResult = "Password";
        String actualResult = driver.findElement(By.id("pass")).getAttribute("placeholder");

        Reporter.log("Expected Result = " + expectedResult);
        Reporter.log("Actual Result = " + actualResult);

        assertTrue(actualResult.equals(expectedResult), "Mismatch in the password placeholder");
    }
}