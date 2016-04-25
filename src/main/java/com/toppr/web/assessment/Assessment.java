package com.toppr.web.assessment;

import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 01-Mar-2016.
 */
public class Assessment extends PageBase
{
    private static final String domFile = getProperties().get("ASSESSMENT_DOM_FILE");
    public Assessment()
    {
        super(domFile);
    }

    /**
     * Function for selection option A in assessment
     */
    public void selectOptionA()
    {
        Mapper.find(domFile, "optionA").click();
    }

    /**
     * Function for clicking Skip Button
     */
    public void clickSkipButton()
    {
        Mapper.find(domFile, "skipButton").click();
    }

    /**
     * Function for clicking Submit button
     */
    public void clickSubmitButton()
    {
        Mapper.find(domFile, "submitButton").click();
    }

    /**
     * Function for clicking Pause Button
     */
    public void clickPauseButton()
    {
        Mapper.find(domFile, "pauseButton").click();
    }

    /**
     * Function for validation of Timer shown on Assessment screen
     */
    public boolean validateTimerOnAssessment()
    {
        if(!(Mapper.find(domFile,"timer").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for validation for Test Paused Popup message
     */
    public boolean validateTestPausedPopupMessage()
    {
        Mapper.waitForElementToBeVisible(domFile, "testPasuedPopUp");
        if(!(Mapper.find(domFile,"testPasuedPopUp").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for attempting question
     */
    public void attemptQuestion()
    {
        try {
            Mapper.find(domFile, "optionA").click();
            Thread.sleep(5000);
            Mapper.find(domFile, "submitButton").click();
            Thread.sleep(5000);
            Mapper.find(domFile, "submitButton").click();
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            Mapper.find(domFile, "submitButton").click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Mapper.find(domFile, "submitButton").click();
        }
    }
}
