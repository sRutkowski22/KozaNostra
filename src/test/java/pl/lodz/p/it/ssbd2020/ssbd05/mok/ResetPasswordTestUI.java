package pl.lodz.p.it.ssbd2020.ssbd05.mok;// Generated by Selenium IDE

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ResetPasswordTestUI {
    private WebDriver driver;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        ChromeOptions options = new ChromeOptions();
 //       options.setHeadless(true);
        options.addArguments("--lang=en");
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        js = (JavascriptExecutor) driver;
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void resetPassword() throws InterruptedException {
        driver.get("https://localhost:8181/ssbd05/");
        driver.manage().window().setSize(new Dimension(1800, 1020));
        driver.findElement(By.id("loginButton")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("I forgot password")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//form[@id=\'changePassword\']/div[2]/div[2]/input")).click();
        driver.findElement(By.xpath("//form[@id=\'changePassword\']/div[2]/div[2]/input")).sendKeys("ekg45asddee331@eoopy.com");
        driver.findElement(By.xpath("//span[contains(.,\'Confirm\')]")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector(".ui-growl-title")).getText(),"An email has been sent to the address provided");
    }
}
