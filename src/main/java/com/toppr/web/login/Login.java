package com.toppr.web.login;

import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 03-Dec-2015.
 */
public class Login extends PageBase
{
    private static final String domFile = getProperties().get("LOGIN_DOM_FILE");
    public Login()
    {
        super(domFile);
    }

    /**
     * Function for validation of Login screen
     */
    public boolean validateLoginPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "emailTextBox");
        if(!(Mapper.find(domFile, "emailTextBox").isDisplayed()) || !(Mapper.find(domFile, "loginFacebookOption").isDisplayed()) || !(Mapper.find(domFile, "loginGoogleOption").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for validation of Password screen
     */
    public boolean validatePasswordPage()
    {
        if(!(Mapper.find(domFile, "forgotPassword").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for entering login details and click login button
     */
    public void enterLoginDetails(String email, String password)
    {
        //Mapper.waitForElementToBeVisible(domFile, "screenTitle");
        Mapper.find(domFile, "emailTextBox").clear();
        Mapper.find(domFile, "emailTextBox").sendKeys(email);
        Mapper.find(domFile, "continueButton").click();
        Mapper.waitForElementToBeVisible(domFile, "userEmail");
        Mapper.waitForElementToBeVisible(domFile, "enteredEmailOnLogin");
//        Mapper.find(domFile, "passwordTestBox").click();
        Mapper.find(domFile, "passwordTestBox").clear();
        Mapper.find(domFile, "passwordTestBox").sendKeys(password);
        Mapper.find(domFile, "loginButton").click();
    }

    /**
     * Function for login without password user
     */
    public void loginWithoutPassword(String email)
    {
        //Mapper.waitForElementToBeVisible(domFile, "screenTitle");
        Mapper.find(domFile, "emailTextBox").clear();
        Mapper.find(domFile, "emailTextBox").sendKeys(email);
        Mapper.find(domFile, "continueButton").click();
    }

    /**
     * Function for validation of invalid format email
     */
    public boolean validateLoginWithInvalidFormatEmail(String email, String emailError)
    {
        Mapper.find(domFile, "emailTextBox").clear();
        Mapper.find(domFile, "emailTextBox").sendKeys(email);
        Mapper.find(domFile, "continueButton").click();
        if(!(Mapper.find(domFile, "emailError").getText().contains(emailError)))
            return false;
        return true;
    }

    /**
     * Function for validation of invalid password
     */
    public boolean validateLoginWithInvalidPassword(String email, String invalidPassword, String passwordError)
    {
        Mapper.find(domFile, "emailTextBox").clear();
        Mapper.find(domFile, "emailTextBox").sendKeys(email);
        Mapper.find(domFile, "continueButton").click();
        Mapper.waitForElementToBeVisible(domFile, "enteredEmailOnLogin");
        Mapper.waitForElementToBeVisible(domFile, "enteredEmailOnLogin");
        Mapper.find(domFile, "passwordTestBox").clear();
        Mapper.find(domFile, "passwordTestBox").sendKeys(invalidPassword);
        Mapper.find(domFile, "loginButton").click();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Error Message:"+Mapper.find(domFile, "passwordError").getText());
        if(!(Mapper.find(domFile, "passwordError").getText().contains(passwordError)))
            return false;
        return true;
    }

    /**
     * Function for login with Facebook
     */
    public void loginWithFacebook(String email, String password)
    {
        Mapper.find(domFile, "loginFacebookOption").click();
        Mapper.find(domFile, "fbEmail").clear();
        Mapper.find(domFile, "fbEmail").sendKeys(email);
        Mapper.find(domFile, "fbPassword").clear();
        Mapper.find(domFile, "fbPassword").sendKeys(password);
        Mapper.find(domFile, "fbLoginButton").click();
    }

    /**
     * Function for login with Google
     */
    public void loginWithGoogle(String email, String password)
    {
        Mapper.find(domFile, "loginGoogleOption").click();
        Mapper.find(domFile, "googleEmail").clear();
        Mapper.find(domFile, "googleEmail").sendKeys(email);
        Mapper.find(domFile, "googleNextButton").click();
        Mapper.waitForElementToBeVisible(domFile, "googlePassword");
        Mapper.find(domFile, "googlePassword").clear();
        Mapper.find(domFile, "googlePassword").sendKeys(password);
        Mapper.find(domFile, "googleSignIn").click();
    }
}
