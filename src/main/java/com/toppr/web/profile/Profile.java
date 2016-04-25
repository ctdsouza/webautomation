package com.toppr.web.profile;

import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 04-Dec-2015.
 */
public class Profile extends PageBase
{
    private static final String domFile = getProperties().get("PROFILE_DOM_FILE");
    public Profile()
    {
        super(domFile);
    }

    /**
     * Function for clicking 'Save Changes' button
     */
    public void clickSaveChanges()
    {
        Mapper.find(domFile, "saveChangesButton").click();
    }

    /**
     * Function for validation of Blank profile name set
     */
    public boolean validateBlankNameSave(String nameErrorMessage, String saveChangesErrorMessage)
    {
        Mapper.waitForElementToBeVisible(domFile, "nameField");
        Mapper.find(domFile, "nameField").clear();
        clickSaveChanges();
        if(!(Mapper.find(domFile, "nameErrorMessage").getText().equals(nameErrorMessage)) || !(Mapper.find(domFile, "saveChangesResponse").getText().equals(saveChangesErrorMessage)))
            return false;
        return true;
    }

    /**
     * Function for setting new Name
     */
    public void setNewName(String newName)
    {
        newName = newName + System.currentTimeMillis();
        Mapper.waitForElementToBeVisible(domFile, "nameField");
        Mapper.find(domFile, "nameField").clear();
        Mapper.find(domFile, "nameField").sendKeys(newName);
        clickSaveChanges();
    }

    /**
     * Function for validation of Save Profile
     */
    public boolean validateSaveProfile(String nameErrorMessage, String saveChangesErrorMessage, String saveChangesSuccessMessage)
    {
        Boolean status = false;
        try
        {
            if (Mapper.find(domFile, "nameErrorMessage").getText().equals(nameErrorMessage) || Mapper.find(domFile, "saveChangesResponse").getText().equals(saveChangesErrorMessage))
                status = false;
        }
        catch (Exception e) {
            if(!(Mapper.find(domFile, "saveChangeSuccessMessage").getText().equals(saveChangesSuccessMessage)))
                status = false;
            status = true;
        }
        return status;
    }

    /**
     * Function for validation locked phonenumber field
     */
    public boolean validatePhoneNumberLocked()
    {
        Mapper.waitForElementToBeVisible(domFile, "phoneNumberField");
        if(!(Mapper.find(domFile, "phoneNumberField").isEnabled()))
            return true;
        return false;
    }

    /**
     * Function for validation of Verify Phone number message
     */
    public boolean validateVerifyPhoneNumberMessage(String errorMessage)
    {
        Mapper.waitForElementToBeVisible(domFile, "phoneNumberField");
        if(!(Mapper.find(domFile, "phoneNumberVerifyMessage").getText().equals(errorMessage)))
            return false;
        return true;
    }
}
