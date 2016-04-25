package com.toppr.utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;

import static com.toppr.utils.InitiateDriver.getDriver;

/**
 * Created by cliforddsouza on 03-Dec-2015.
 */
public class Mapper
{
    private final static int TIMEOUT = 20   ;
    private static WebDriverWait wait;
    private static HashMap<String, String> map;

    /**
     * find and return WebElement corresponding to element
     * @param fileName
     * @param element
     * @return WebElement
     */
    public static WebElement find(String fileName, String element)
    {
        WebElement webElement = null;
        map = XMLReader.getDOMElements(fileName, element);

        while(!map.isEmpty())
        {
            try
            {
                webElement = getDriver().findElement(getBy());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }

        return webElement;
    }


    /**
     * find and return list of WebElement corresponding to element
     * @param fileName
     * @param element
     * @return list of WebElement
     */
    public static List<WebElement> finds(String fileName, String element)
    {
        List<WebElement> webElements = null;
        map = XMLReader.getDOMElements(fileName, element);

        while(!map.isEmpty())
        {
            try {


                webElements = getDriver().findElements(getBy());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }

        return webElements;
    }

    public static WebElement findAndReplaceInXpath(String fileName, String element, int i)
    {
        WebElement webElement = null;
        map = XMLReader.getDOMElements(fileName, element);

        while(!map.isEmpty())
        {
            try {
                String str = map.get("xpath").replace("-i-", "" + i + "");
                System.out.println("String"+str);
                map.put("xpath",str);
                webElement = getDriver().findElement(getBy());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }
        return webElement;
    }


    public static WebElement findAndReplaceStringInXpath(String fileName, String element, String parameter)
    {
        WebElement webElement = null;
        map = XMLReader.getDOMElements(fileName, element);

        while(!map.isEmpty())
        {
            try {
                String str = map.get("xpath").replace("-i-", "" + parameter + "");
                System.out.println("String"+str);
                map.put("xpath",str);
                webElement = getDriver().findElement(getBy());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }
        return webElement;
    }

    /**
     * wait for element to be visible and enabled in order to be clickable
     * @param fileName
     * @param element
     * @return boolean
     */
    public static boolean waitForElementToBeClickable(String fileName, String element)
    {
        try
        {
            wait  = new WebDriverWait(getDriver(), TIMEOUT);
            wait.until(ExpectedConditions.elementToBeClickable(find(fileName, element)));
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * wait for element to disappear from page
     * @param fileName
     * @param element
     * @return boolean
     */
    public static boolean waitForElementToBeInvisible(String fileName, String element)
    {
        map = XMLReader.getDOMElements(fileName, element);

        while(!map.isEmpty())
        {
            try
            {
                wait  = new WebDriverWait(getDriver(), TIMEOUT);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy()));
            }
            catch (NoSuchElementException e)
            {
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * wait for element to present and visible in page
     * @param fileName
     * @param element
     * @return boolean
     */
    public static boolean waitForElementToBeVisible(String fileName, String element)
    {
        try
        {
            wait  = new WebDriverWait(getDriver(), TIMEOUT);
            wait.until(ExpectedConditions.visibilityOf(find(fileName, element)));
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * wait for element to be visible in page
     * @param element
     * @return boolean
     */
    public static boolean waitForElementToBeVisible(WebElement element)
    {
        try
        {
            wait  = new WebDriverWait(getDriver(), TIMEOUT);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * wait for element to present and visible in page for given time
     * @param fileName
     * @param element
     * @return boolean
     */
    public static boolean waitForElementToBeVisible(String fileName, String element, int timeOut)
    {
        try
        {
            wait  = new WebDriverWait(getDriver(), timeOut);
            wait.until(ExpectedConditions.visibilityOf(find(fileName, element)));
        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * scroll element to bring in view, scroll down the page
     * @param fileName
     * @param element
     */
    public static void scrollElementIntoView(String fileName, String element)
    {
        try
        {
            WebElement webElement = Mapper.find(fileName, element);
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(false);", webElement);
            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void scrollVerticallWithCords(int startValue, int endValue)
    {
        try
        {
            ((JavascriptExecutor) getDriver()).executeScript("scroll("+startValue+","+endValue+" );");
            Thread.sleep(2000);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * get child elements of given web element
     * @param element
     * @return list of child elements
     */
    public static List<WebElement> findChilds(WebElement element)
    {
        String path = ".//*";
        return element.findElements(By.xpath(path));
    }

    /**
     * whether given element has child elements or not
     * @param element
     * @return boolean
     */
    public static boolean hasChilds(WebElement element)
    {
        if(findChilds(element).size() != 0)
        {
            return true;
        }

        return false;
    }

    /**
     * return child element of parent element that matches passed parameters condition
     * it check for attribute value of given tag
     * @param domFile
     * @param value
     * @param parentElement
     * @param tagNameToSearch
     * @param attributeNameToSearch
     * @return WebElement
     */
    public static WebElement findChildElement(String domFile, String value, String parentElement, String tagNameToSearch, String attributeNameToSearch)
    {
        waitForElementToBeVisible(domFile, parentElement);
        WebElement element = find(domFile, parentElement);
        List<WebElement> childElements = findChilds(element);

        for(int i=0; i<childElements.size(); i++)
        {
            if(childElements.get(i).getTagName().equalsIgnoreCase(tagNameToSearch))
            {
                String attribute = childElements.get(i).getAttribute(attributeNameToSearch).trim();
                if(attribute.equalsIgnoreCase(value))
                {
                    return childElements.get(i);
                }
            }
        }

        return null;
    }

    /**
     * return child element of parent element that matches passed parameters condition
     * it check for text content of given tag
     * @param domFile
     * @param value
     * @param parentElement
     * @param tagNameToSearch
     * @param exactMatch
     * @return
     */
    public static WebElement findChildElement(String domFile, String value, String parentElement, String tagNameToSearch, boolean exactMatch)
    {
        waitForElementToBeVisible(domFile, parentElement);
        WebElement element = find(domFile, parentElement);
        List<WebElement> childElements = findChilds(element);

        for(int i=0; i<childElements.size(); i++)
        {
            if(childElements.get(i).getTagName().equalsIgnoreCase(tagNameToSearch)) {
                String textContent = childElements.get(i).getText().trim();
                if(exactMatch)
                {
                    if (textContent.equalsIgnoreCase(value))
                    {
                        return childElements.get(i);
                    }
                }
                else
                {
                    if (textContent.contains(value))
                    {
                        return childElements.get(i);
                    }
                }
            }
        }

        return null;
    }

    /**
     * Function for getting font color element
     * @param element
     * @return
     */
    public static String getFontColor(String fileName, String element)
    {
        WebElement webElement = null;
        map = XMLReader.getDOMElements(fileName, element);

        while(!map.isEmpty())
        {
            try
            {
                webElement = getDriver().findElement(getBy());
            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }
        String color = webElement.getCssValue("color");
        String s1 = color.substring(5);
        StringTokenizer st = new StringTokenizer(s1);
        int r = Integer.parseInt(st.nextToken(",").trim());
        int g = Integer.parseInt(st.nextToken(",").trim());
        int b = Integer.parseInt(st.nextToken(",").trim());
        Color c = new Color(r, g, b);
        String hex = "#"+Integer.toHexString(c.getRGB()).substring(2);
        return hex;
    }

    /**
     * get By
     * @return By
     */
    private static By getBy()
    {
        By by = null;

        if (map.containsKey("id"))
        {
            by = By.id(map.get("id"));
            map.remove("id");
        }
        else if (map.containsKey("css"))
        {
            by = By.cssSelector(map.get("css"));
            map.remove("css");
        }
        else if (map.containsKey("name"))
        {
            by = By.name(map.get("name"));
            map.remove("name");
        }
        else if (map.containsKey("class"))
        {
            by = By.className(map.get("class"));
            map.remove("class");
        }
        else if (map.containsKey("tag"))
        {
            by = By.tagName(map.get("tag"));
            map.remove("tag");
        }
        else if (map.containsKey("linkText"))
        {
            by = By.linkText(map.get("linkText"));
            map.remove("linkText");
        }
        else if (map.containsKey("partialLinkText"))
        {
            by = By.partialLinkText(map.get("partialLinkText"));
            map.remove("partialLinkText");
        }
        else if (map.containsKey("xpath"))
        {
            by = By.xpath(map.get("xpath"));
            map.remove("xpath");
        }

        return by;
    }


    /**
     * Function for Taking screenshot
     */
    public static void takeScreenshot()
    {
        String classname = "SignupScreen";
        DateFormat dateFormatday = new SimpleDateFormat("dd_MMM_yyyy");
        DateFormat dateFormathour = new SimpleDateFormat("HH");
        DateFormat dayFormatseconds = new SimpleDateFormat("hh-mm-ss");
        String destDir;

        try {
                File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
                destDir = "/var/tmp/WebSiteAutomation/Screenshots/" + classname + "/" + dateFormatday.format(new Date()) + "/" + dateFormathour.format(new Date()) + " " + "hour" + "/";
                if (!new File(destDir).exists())

                {
                    new File(destDir).mkdirs();
                }
                String destFile = dayFormatseconds.format(new Date()) + ".jpg";
                try {
                    FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }


        } catch (Exception e) {
            System.out.println("No Directory found to store screenshot");
        }
    }
}
