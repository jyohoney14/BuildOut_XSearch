
package apTests;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
///

public class TestCases {
    WebDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.amazon.in/");
        String title = driver.getCurrentUrl();
        if (title.contains("amazon")) {
            System.out.println("url contains expected title");
        } else {
            System.out.println("url not contains expected title");
        }

        System.out.println("end Test case: testCase01");
    }

    public void testCase02() {
        System.out.println("Start Test case: testCase02");
        driver.get("https://www.amazon.in/");
        WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        searchBar.sendKeys("laptop");
        searchBar.submit();
        List<WebElement> laptopTextElements = driver.findElements(By.xpath("//span[contains(text(),'Laptop')]"));
        boolean searchItemFound = false;
        for (WebElement product : laptopTextElements) {
            if (product.getText().toLowerCase().contains("laptop")) {
                searchItemFound = true;
                break;
            }
        }
        if (searchItemFound) {
            System.out.println("Search Term Found in Product Titles  >> Test Case Passed ");
        } else {
            System.out.println("Search Term Not Found in Product Titles >>Test Case Failed ");
        }

        System.out.println("end Test case: testCase02");
    }

    public void testCase03() {
        System.out.println("Start Test case: testCase03");
        driver.get("https://www.amazon.in/");
        driver.findElement(By.xpath("//a[text()=' Electronics ']")).click();
        String url = driver.getCurrentUrl();
        if (url.toLowerCase().contains("electronics")) {
            System.out.println("URl contains Electronics word  >> Test Case Passed");
        } else {
            System.out.println("URl not contains Electronics word >> Test Case Failed");
        }
        System.out.println("end Test case: testCase03");

    }
}