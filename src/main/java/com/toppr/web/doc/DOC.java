package com.toppr.web.doc;

import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;

import org.openqa.selenium.Keys;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 02-Mar-2016.
 */
public class DOC extends PageBase
{
    private static final String domFile = getProperties().get("DOC_DOM_FILE");
    public DOC()
    {
        super(domFile);
    }

    /**
     * Function for validation of freemium doc message
     */
    public boolean validateFreemiumMessageDisplayed()
    {
        System.out.println("Message: "+Mapper.find(domFile, "freemiumMessage").getText());
        if(!(Mapper.find(domFile, "freemiumMessage").isDisplayed()) || !(Mapper.find(domFile, "freemiumMessage").getText().equals("You can ask 5 doubts for free. Upgrade to ask more doubts.")))
            return false;
        return true;
    }

    /**
     * Function for validation of freemium doc message not Displayed
     */
    public boolean validateFreemiumMessageNotDisplayed()
    {
        try {
            if (Mapper.find(domFile, "freemiumMessage").getText().equals("*You have 5 free doubts sessions. Upgrade to get more."))
                return false;
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * Function for Starting Physics Doubt Session
     */
    public boolean startPhysicsDoubtSession()
    {
        Mapper.find(domFile, "startPhysicsChat").click();
        //Mapper.find(domFile, "askADoubtButton").click();
        Mapper.waitForElementToBeVisible(domFile, "docTextBox");
        Mapper.find(domFile, "docTextBox").sendKeys("This is test doubt session, kindly ignore", Keys.RETURN);
        //Mapper.find(domFile, "sendButton").click();
        if(!(Mapper.find(domFile, "sentMessageText").getText().equals("This is test doubt session, kindly ignore")))
            return false;
        return true;
    }
}
