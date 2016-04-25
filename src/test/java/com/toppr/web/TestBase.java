package com.toppr.web;

import com.toppr.utils.InitiateDriver;
import com.toppr.utils.Mapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.toppr.utils.InitiateDriver.getDriver;

/**
 * Created by cliforddsouza on 03-Dec-2015.
 */
public class TestBase
{
    private RemoteWebDriver driver;
    protected String classname = this.getClass().getSimpleName();
    DateFormat dateFormatday = new SimpleDateFormat("dd_MMM_yyyy");
    DateFormat dateFormathour = new SimpleDateFormat("HH");
    //DateFormat dayFormattime = new SimpleDateFormat("hh-mm");
    DateFormat dayFormatseconds = new SimpleDateFormat("hh-mm-ss");
    public static String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static int RANDOM_STRING_LENGTH = 10;


    protected String destDir;

    public TestBase() {
        // set homepath for logger to use
        if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
            System.setProperty("homePath", "/home/toppr");
        } else {
            System.setProperty("homePath", "/Users/apple");
        }
    }

    @BeforeMethod(groups = {"acceptance", "productionsanity"})
    public void setup() {
        //logger = LogFactory.getLog(this.getClass().getName());
        driver = getDriver();
        try {
            if (System.getProperty("env").equalsIgnoreCase("staging"))
                driver.get("http://demo:bookfree@staging.toppr.com");
            else if (System.getProperty("env").equalsIgnoreCase("production"))
                driver.get("http://www.toppr.com");
            else if (System.getProperty("env").equalsIgnoreCase("preproduction"))
                driver.get("http://new.toppr.com");
            driver.manage().window().maximize();
        }
        catch (Exception e)
        {
            //driver.get("http://demo:bookfree@new.toppr.com");
            driver.get("http://demo:bookfree@staging.toppr.com");
            driver.manage().window().maximize();
        }
    }

    @AfterMethod(groups = {"acceptance", "productionsanity"})
    public void tearDownandTakeScreenshot(ITestResult testResult) {
        try {

            if (testResult.getStatus() == ITestResult.FAILURE)// Here ITestResult is an interface
            {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                destDir = "/var/tmp/WebSiteAutomation/Screenshots/" + classname + "/" + dateFormatday.format(new Date()) + "/" + dateFormathour.format(new Date()) + " " + "hour" + "/";
                if (!new File(destDir).exists())
                {
                    new File(destDir).mkdirs();
                }
                String destFile = dayFormatseconds.format(new Date()) + " " + testResult.getName() + ".jpg";
                try {
                    FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            System.out.println("No Directory found to store screenshot");
        }

        driver.quit();
    }

    /*
    Extracts the current url and checks if it contains the PartialUrlString passed as parameter.
    @param PartialUrlString : the string that Url should contain
    */
    public boolean VerifyUrl(String PartialUrlString) {
        String urlExtracted = driver.getCurrentUrl();
        return urlExtracted.contains(PartialUrlString);
    }


    /**
     * Generic method to verify if google ad iframe is present or not.
     *
     * @param iFrameId
     * @return
     */

    public boolean verifyGoogleAds(String iFrameId) {
        Mapper.scrollVerticallWithCords(0, 500);
        return InitiateDriver.getDriver().getPageSource().contains(iFrameId);
    }


    /**
     * Generic method to Switch to Pop Up, enter element as parameter by which clicking on it pop up opens
     *
     * @param popup
     */
    public String switchtoPopup(WebElement popup) {
        String mainWindowHandle = driver.getWindowHandle();
        popup.click();
        Set s = driver.getWindowHandles();
        Iterator ite = s.iterator();
        while (ite.hasNext()) {
            String popupHandle = ite.next().toString();
            if (!popupHandle.contains(mainWindowHandle)) {
                driver.switchTo().window(popupHandle);
            }
        }

        return mainWindowHandle;
    }

    /**
     * Switch to Parent window with parameter value of Parent window
     *
     * @param Parentwindow
     */
    public void switchtoParentfromPopUp(String Parentwindow) {
        driver.switchTo().window(Parentwindow);
    }

    /**
     * This function is used to return currentwindow
     *
     * @return
     */
    public String returnCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    /**
     * This function is used to edit the cookie by cookiename and value of that cookie
     *
     * @param cookiename
     * @param cookievalue
     */
    public void createorEditCookieValue(String cookiename, String cookievalue) {
        Cookie cookie = driver.manage().getCookieNamed(cookiename);
        driver.manage().deleteCookie(cookie);
        Cookie name = new Cookie(cookiename, cookievalue);
        driver.manage().addCookie(name);
    }

    /**This function is used to generate the String of random length
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        final String charset = "abcdefghijklmnopqrstuvwxyz";
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int pos = rand.nextInt(charset.length());
            sb.append(charset.charAt(pos));
        }
        return sb.toString();
    }

    /**This function is used to generate Integer of random length
     *
     * @param length
     * @return
     */
    public static String getRandomInteger(int length) {
        final String charset = "12345678910111213";
        Random rand = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int pos = rand.nextInt(charset.length());
            sb.append(charset.charAt(pos));
        }
        return sb.toString();
    }

    /**This function is used to navigate directly to a particular URL
     *
     * @param URL
     */
    public void navigatethirdparty(String URL) {
        driver.navigate().to(URL);
    }


    /**This function is used to select the date from the calendar with parameter days , here days will be added to current date i.e. date will be future date
     *
     * @param days
     */
    public void selectdate(int days){

        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, days);
        int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);
        driver.findElement(By.xpath("//span[starts-with(.,\"" + dayOfMonth + "\")]")).click();
    }

    public void attributechange(final String xpath, final String attribute){
        WebDriverWait wait = new WebDriverWait(driver,4);

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                WebElement button = driver.findElement(By.xpath(xpath));
                String enabled = button.getAttribute(attribute);
                if (enabled.equals("true"))
                    return true;
                else
                    return false;
            }
        });
    }

}
