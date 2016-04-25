package com.toppr.web;

import com.toppr.utils.Mapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import static com.toppr.utils.InitiateDriver.getDriver;

/**
 * Created by cliforddsouza on 03-Dec-2015.
 */
public class PageBase
{
    private String fileName;
    protected Log logger;

    public PageBase(String fileName)
    {
        // set homepath for logger to use
        if(System.getProperty("os.name").equalsIgnoreCase("Linux"))
        {
            System.setProperty("homePath","/home/toppr");
        }
        else
        {
            System.setProperty("homePath","/Users/apple");
        }

        //logger = LogFactory.getLog(this.getClass().getName());
        this.fileName =  fileName;
    }

    /*
        verify if element is present or not
     */
    protected boolean isElementPresent(String element)
    {
        try
        {
            if(Mapper.find(fileName,element) == null)
            {
                return false;
            };

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * get current location of the browser
     * @return
     */
    public String getCurrentLocation()
    {
        return getDriver().getCurrentUrl();
    }

    /**
     * execute java script
     * @param script
     * @return
     */
    public Object executeScript(String script)
    {
        return getDriver().executeScript(script);
    }

    /**
     * get page source
     * @return
     */
    public String getPageSource()
    {
        return getDriver().getPageSource();
    }

    /**
     * return current window handle
     */
    protected String getWindowHandle()
    {
        return getDriver().getWindowHandle();
    }

    /**
     * return targert locator
     * @return
     */
    protected WebDriver.TargetLocator switchTo()
    {
        return getDriver().switchTo();
    }

    /**
     * navigate to new page
     * @return
     */
    protected WebDriver.Navigation navigateTo()
    {
        return getDriver().navigate();
    }

    /**
     * get title of page
     * @return
     */
    public String getTitle()
    {
        return getDriver().getTitle();
    }

    /**
     * opens passed Url
     * @param url
     */
    public void openUrl(String url){
        getDriver().get(url);
    }
}
