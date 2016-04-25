package com.toppr.utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 03-Dec-2015.
 */
public class InitiateDriver
{
    private static RemoteWebDriver driver;

    /**
     * initiate driver depend upon BROWSER and URL value in config.properties file
     */
    private InitiateDriver()
    {
        try
        {
            driver = new RemoteWebDriver(new URL(getProperties().get("URL")), getBrowserCapabilities(getProperties().get("BROWSER")));
            //driver = new RemoteWebDriver(new URL("https://cliforddsouza1:c2Ks976pypeeTfLUTmSE@hub.browserstack.com/wd/hub"), getBrowserCapabilities(getProperties().get("BROWSER")));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
        catch (Exception e)
        {

        }
    }

    /**
     * get driver based upon BROWSER value in config.properties file, at present supporting IE, Chrome, Firefox
     * @return RemoteWebDriver
     */
    public static RemoteWebDriver getDriver()
    {
        if(driver == null || driver.toString().contains("(null)"))
        {
            new InitiateDriver().getDriver();
        }

        return driver;
    }

    /**
     * get desired capabilities of browser
     * @param browser
     * @return DesiredCapabilities
     */
    private DesiredCapabilities getBrowserCapabilities(String browser)
    {
        DesiredCapabilities capabilities = null;

        if(browser.equals("Firefox"))
        {
            capabilities = DesiredCapabilities.firefox();
            capabilities.setBrowserName("firefox");
            capabilities.setPlatform(Platform.ANY);
        }
        else if(browser.equals("IE"))
        {
            //InternetExplorerDriverManager.getInstance().setup();
            System.setProperty("webdriver.ie.driver","../../../../resources/IEDriverServer.exe");
            capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setBrowserName("iexplore");
            capabilities.setPlatform(Platform.WINDOWS);
        }
        else if(browser.equals("Chrome"))
        {
            //ChromeDriverManager.getInstance().setup();
            System.setProperty("webdriver.chrome.driver","../../../../resources/chromedriver");
            capabilities = DesiredCapabilities.chrome();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.ANY);
        }
        else
        {
//            // default is firefox
//            capabilities = DesiredCapabilities.firefox();
//            capabilities.setBrowserName("firefox");
//            capabilities.setPlatform(Platform.ANY);

            System.setProperty("webdriver.chrome.driver","../../../../resources/chromedriver");
            capabilities = DesiredCapabilities.chrome();
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.ANY);

//            //BrowesrStack Capabilities
//            capabilities = DesiredCapabilities.chrome();
//            capabilities.setCapability("browser", "Chrome");
//            capabilities.setCapability("browser_version", "49.0");
//            capabilities.setCapability("os", "Windows");
//            capabilities.setCapability("os_version", "10");
//            capabilities.setCapability("resolution", "1024x768");
        }

        return capabilities;
    }
}
