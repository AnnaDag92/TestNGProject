import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest_Exercise1 {
    WebDriver driver = null;

    @BeforeMethod
    public void beforeMethod() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void login() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/login");
        Thread.sleep(2000);
        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        username.sendKeys("tomsmith");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        password.sendKeys("SuperSecretPassword!");

        WebElement login = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button/i"));
        login.click();
        Thread.sleep(2000);

        WebElement message = driver.findElement(By.xpath("//*[@id=\"flash\"]"));

        Assert.assertTrue(message.isDisplayed());
        String actualMessage = message.getText().trim();
        if (actualMessage.contains("Your username is invalid!")) {
            Assert.assertTrue(actualMessage.contains("Your username is invalid!"));
            System.out.println(actualMessage);
        } else if(actualMessage.contains("You logged into a secure area!")) {
            Assert.assertTrue(actualMessage.contains("You logged into a secure area!"));
            System.out.println(actualMessage);
        }

        Thread.sleep(2000);
        driver.quit();
    }
}