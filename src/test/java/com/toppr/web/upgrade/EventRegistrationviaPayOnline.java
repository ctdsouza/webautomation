package com.toppr.web.upgrade;

/**
 * Created by cliforddsouza on 14/04/16.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

public class EventRegistrationviaPayOnline
{
    String baseUrl = null;
    WebDriver driver = null;

    @BeforeMethod
    public void setUp()
    {
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        baseUrl = "https://www.turtlemint.com/";
        //expectedTitle = WebConstants.expectedHomePageTitle;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testGetDataforCar()
    {
        driver.get(baseUrl + "/car-insurance");
        driver.findElement(By.linkText("Car")).click();
        driver.findElement(By.id("regno1")).clear();
        driver.findElement(By.id("regno1")).sendKeys("MH");
        driver.findElement(By.id("regno2")).clear();
        driver.findElement(By.id("regno2")).sendKeys("01");
        driver.findElement(By.id("regno3")).clear();
        driver.findElement(By.id("regno3")).sendKeys("AA");
        driver.findElement(By.id("regno4")).clear();
        driver.findElement(By.id("regno4")).sendKeys("1047");
        driver.findElement(By.id("findHelthPlanBtn")).click();
        driver.findElement(By.id("makemodellist")).clear();
        driver.findElement(By.id("makemodellist")).sendKeys("Volkswagen Cross Polo");
        driver.findElement(By.linkText("Volkswagen Cross Polo")).click();
        driver.findElement(By.id("regYearSel")).click();
        driver.findElement(By.linkText("2016")).click();
        driver.findElement(By.xpath("//div[2]/div/button")).click();
        driver.findElement(By.linkText("Diesel")).click();
        driver.findElement(By.xpath("//div[3]/div/button")).click();
        driver.findElement(By.linkText("TDI TDI (1199 cc)")).click();
        driver.findElement(By.xpath("//button[@type='button']")).click();
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
