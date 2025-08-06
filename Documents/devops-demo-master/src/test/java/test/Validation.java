package test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Validation {

    public static WebDriver driver;
    String url = "https://www.facebook.com";

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080");
        // optional: remove headless for debugging
        // options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
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
        WebElement welcomeElement = driver.findElement(By.xpath("//h2[contains(@class,'_8eso')]"));
        String actualResult = welcomeElement.getText();

        // Print and log
        System.out.println("Welcome Message Found: " + actualResult);
        Reporter.log("Actual Welcome Message: " + actualResult);

        assertTrue(actualResult.toLowerCase().contains("connect"), 
            "Mismatch in the welcome message - partial match expected");
    }

    @Test
    public void emailTextBoxPlaceholderValidation() {
        WebElement emailField = driver.findElement(By.name("email")); // safer than ID
        String actualResult = emailField.getAttribute("placeholder");

        // Print and log
        System.out.println("Email Placeholder Found: " + actualResult);
        Reporter.log("Email Placeholder: " + actualResult);

        // Facebook might change wording slightly, so use contains
        assertTrue(actualResult.toLowerCase().contains("email"), 
            "Mismatch in the email placeholder - partial match expected");
    }

    @Test
    public void passwordBoxPlaceholderValidation() {
        WebElement passField = driver.findElement(By.name("pass")); // safer than ID
        String actualResult = passField.getAttribute("placeholder");

        // Print and log
        System.out.println("Password Placeholder Found: " + actualResult);
        Reporter.log("Password Placeholder: " + actualResult);

        assertEquals(actualResult, "Password", "Mismatch in the password placeholder");
    }
}
