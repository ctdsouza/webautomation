package com.toppr.web.homePage;

import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 03-Dec-2015.
 */
public class HomePage extends PageBase
{
    public HomePage()
    {
        super(domFile);
    }
    private static final String domFile = getProperties().get("HOMEPAGE_DOM_FILE");

    /**
     * Function for clicking Login Button
     */
    public void clickLogin()
    {
        Mapper.find(domFile, "loginButton").click();
    }

    /**
     * Function for clicking SignUP Button
     */
    public void clickSignUp()
    {
        Mapper.find(domFile, "signUpButton").click();
    }
}
