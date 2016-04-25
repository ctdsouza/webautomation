package com.toppr.web.settings;

import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 02-Mar-2016.
 */
public class Settings extends PageBase
{
    private static final String domFile = getProperties().get("SETTINGS_DOM_FILE");
    public Settings()
    {
        super(domFile);
    }

    /**
     * Function for setting value for Name field
     */
    public void setNameField(String name)
    {
        Mapper.find(domFile, "nameField").clear();
        Mapper.find(domFile, "nameField").sendKeys(name);
    }

    /**
     * Function for setting blank value for Name field
     */
    public void setNameFieldBlank()
    {
        Mapper.find(domFile, "nameField").clear();
    }

    /**
     * Function for clicking Save Button
     */
    public void clickSaveButton()
    {
        Mapper.scrollElementIntoView(domFile, "saveButton");
        Mapper.find(domFile, "saveButton").click();
    }

    /**
     * Function for validation of blank name save
     */
    public boolean validateBlankNameSave()
    {
        Mapper.waitForElementToBeVisible(domFile, "nameFieldBlankError");
        if(!(Mapper.find(domFile, "nameFieldBlankError").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for Changing Class to 12
     */
    public void changeClassTo12()
    {
        Mapper.find(domFile, "syallbusTab").click();
        Mapper.find(domFile, "classDropDown").click();
        List<WebElement> classList = Mapper.finds(domFile, "class12Value");
        classList.get(4).click();
        try{
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Mapper.find(domFile, "streamDropDown").click();
        List<WebElement> streamList = Mapper.finds(domFile, "pcbValue");
        streamList.get(1).click();
        try{
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        clickSaveButton();
    }
}
