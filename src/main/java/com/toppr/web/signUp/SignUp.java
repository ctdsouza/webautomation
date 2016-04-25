package com.toppr.web.signUp;

import com.toppr.utils.Authentication;
import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 03-Dec-2015.
 */
public class SignUp extends PageBase
{
    private static final String domFile = getProperties().get("SIGNUP_DOM_FILE");
    public SignUp()
    {
        super(domFile);
    }

    /**
     * Function for validation of Signup screen
     */
    public boolean validateSignUpPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "emailTextBox");
        if(!(Mapper.find(domFile, "emailTextBox").isDisplayed()) || !(Mapper.find(domFile, "signUpFacebookOption").isDisplayed()) || !(Mapper.find(domFile, "signUpGoogleOption").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for entering signup email and click Signup
     */
    public void enterSignUpEmail(String email)
    {
        Mapper.find(domFile,"emailTextBox").clear();
        Mapper.find(domFile,"emailTextBox").sendKeys(email);
        Mapper.find(domFile, "signUpButton").click();
    }

    /**
     * Function for entering all signup details
     */
    public void enterSignUpDetails(String email, String phoneNumber) throws Exception {
        try {
            email = email + System.currentTimeMillis() + "@toppr.com";
            Mapper.find(domFile, "emailTextBox").clear();
            Mapper.find(domFile, "emailTextBox").sendKeys(email);
            Mapper.find(domFile, "signUpButton").click();
            Mapper.waitForElementToBeVisible(domFile, "phoneNumberTextBox");

            Random r = new Random();
            int Low = 1500000000;
            int High = 1500010000;
            int Result = r.nextInt(High - Low) + Low;
            phoneNumber = String.valueOf(Result);

            Mapper.find(domFile, "phoneNumberTextBox").sendKeys("1500010000");
            Mapper.takeScreenshot();
            Mapper.find(domFile, "continueButton").click();
            Mapper.find(domFile, "otpTextBox").click();
            Mapper.find(domFile, "otpTextBox").clear();
            Mapper.find(domFile, "otpTextBox").sendKeys(Authentication.getOTPCode("1500010000"));
            Mapper.find(domFile, "otpContinueButton").click();
            System.out.println("Phonenumber validation completed using OTP");
        }
        catch (Exception e)
        {
            Mapper.find(domFile,"continueThisNumber").click();
            Mapper.find(domFile, "otpTextBox").click();
            Mapper.find(domFile, "otpTextBox").clear();
            Mapper.find(domFile, "otpTextBox").sendKeys(Authentication.getOTPCode("1500010000"));
            Mapper.find(domFile, "otpContinueButton").click();
            System.out.println("Phonenumber validation completed using OTP");
        }
    }

    /**
     * Function for selecting class
     */
    public void selectClass()
    {
        try
        {
            Thread.sleep(5000);
            List<WebElement> classList = Mapper.finds(domFile, "classOptionList");
            classList.get(3).click();
            //Mapper.find(domFile, "classOption").click();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Function for selecting stream
     */
    public void selectStream()
    {
        try {
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        List<WebElement> streamList = Mapper.finds(domFile, "streamOptionList");
        streamList.get(42).click();
        //Mapper.find(domFile, "streamOption").click();
    }

    /**
     * Function for selecting state
     */
    public void selectState()
    {
        try
        {
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        List<WebElement> stateList = Mapper.finds(domFile, "stateOptionList");
        stateList.get(40).click();
        //Mapper.find(domFile, "stateOption").click();
    }

    /**
     * Function for confirming all details
     */
    public void confirmSignUpDetails()
    {
        try
        {
            Thread.sleep(3000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Mapper.find(domFile, "confirmButton").click();
    }

    /**
     * Function for validation of invalid format email
     */
    public boolean validateLoginWithInvalidFormatEmail(String email, String emailError)
    {
        Mapper.find(domFile, "emailTextBox").clear();
        Mapper.find(domFile, "emailTextBox").sendKeys(email);
        Mapper.find(domFile, "signUpButton").click();
        if(!(Mapper.find(domFile, "emailError").getText().contains(emailError)))
            return false;
        return true;
    }

    /**
     * Function for signup with Facebook
     */
    public void signUpWithFacebook(String email, String password)
    {
        Mapper.find(domFile, "signUpFacebookOption").click();
        Mapper.find(domFile, "fbEmail").clear();
        Mapper.find(domFile, "fbEmail").sendKeys(email);
        Mapper.find(domFile, "fbPassword").clear();
        Mapper.find(domFile, "fbPassword").sendKeys(password);
        Mapper.find(domFile, "fbLoginButton").click();
    }

    /**
     * Function for signup with Google
     */
    public void signupWithGoogle(String email, String password)
    {
        Mapper.find(domFile, "signUpGoogleOption").click();
        Mapper.find(domFile, "googleEmail").clear();
        Mapper.find(domFile, "googleEmail").sendKeys(email);
        Mapper.find(domFile, "googleNextButton").click();
        Mapper.waitForElementToBeVisible(domFile, "googlePassword");
        Mapper.find(domFile, "googlePassword").clear();
        Mapper.find(domFile, "googlePassword").sendKeys(password);
        Mapper.find(domFile, "googleSignIn").click();
    }
}
