package com.toppr.web.dashboard;

import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;

import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 03-Dec-2015.
 */
public class Dashboard extends PageBase
{
    private static final String domFile = getProperties().get("DASHBOARD_DOM_FILE");
    public Dashboard()
    {
        super(domFile);
    }
    public boolean status;
    /**
     * Function to validate dashboard
     */
    public boolean validateDashBoard()
    {
        Mapper.waitForElementToBeVisible(domFile, "firstSubjectName");
        if(!(Mapper.find(domFile, "firstSubjectName").isDisplayed()) || !(Mapper.find(domFile, "secondSubjectName").isDisplayed()) || !(Mapper.find(domFile, "thirdSubject").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function to validate dashboard
     */
    public boolean validateDashBoardofNewUser(String firstSubject, String secondSubject, String thirdSubject)
    {
        List<String> expectedSubjectList = Arrays.asList(firstSubject+"\n"+secondSubject+"\n"+thirdSubject);
        Mapper.waitForElementToBeVisible(domFile, "subjectList");
        List<WebElement> subjectList = Mapper.finds(domFile, "subjectList");
        try {
            for (int i=0 ; i< subjectList.size(); i++) {
                if (!(subjectList.get(i).getText().contains(expectedSubjectList.get(i)))) {
                    status = false;
                    break;
                }
                status = true;
            }
            return status;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Function to validate iit jee dashboard
     */
    public boolean validateIITDashBoard(String firstSubject, String secondSubject, String thirdSubject)
    {
        List<String> expectedSubjectList = Arrays.asList(firstSubject+"\n"+secondSubject+"\n"+thirdSubject);
        Mapper.waitForElementToBeVisible(domFile, "subjectList");
        List<WebElement> subjectList = Mapper.finds(domFile, "subjectList");
        try {
            for (int i=0 ; i< subjectList.size(); i++) {
                if (!(subjectList.get(i).getText().contains(expectedSubjectList.get(i)))) {
                  status = false;
                  break;
              }
              status = true;
            }
            return status;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Function to validate pre-medical dashboard
     */
    public boolean validatePreMedicalDashBoard(String firstSubject, String secondSubject, String thirdSubject)
    {
        List<String> expectedSubjectList = Arrays.asList(firstSubject+"\n"+secondSubject+"\n"+thirdSubject);
        Mapper.waitForElementToBeVisible(domFile, "subjectList");
        List<WebElement> subjectList = Mapper.finds(domFile, "subjectList");
        try {
            for (int i=0 ; i< subjectList.size(); i++) {
                if (!(subjectList.get(i).getText().contains(expectedSubjectList.get(i)))) {
                    status = false;
                    break;
                }
                status = true;
            }
            return status;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Function to validate foundation dashboard
     */
    public boolean validateFoundationDashBoard(String firstSubject, String secondSubject, String thirdSubject, String forthSubject)
    {
        List<String> expectedSubjectList = Arrays.asList(firstSubject+"\n"+secondSubject+"\n"+thirdSubject+"\n"+forthSubject);
        Mapper.waitForElementToBeVisible(domFile, "subjectList");
        List<WebElement> subjectList = Mapper.finds(domFile, "subjectList");
        try {
            for (int i=0 ; i< subjectList.size(); i++) {
                if (!(subjectList.get(i).getText().contains(expectedSubjectList.get(i)))) {
                    status = false;
                    break;
                }
                status = true;
            }
            return status;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Function to validate non-password dashboard
     */
    public boolean validateNoPasswordDashBoard(String setPasswordMessage)
    {
        if(!(Mapper.find(domFile, "header").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for clicking profile icon
     */
    public void clickProfileIcon()
    {
        Mapper.find(domFile, "profileIcon").click();
    }

    /**
     * Function for clicking Setting Option
     */
    public void clickSettingOption()
    {
        clickProfileIcon();
        Mapper.find(domFile, "settingOption").click();
    }

    /**
     * Function for clicking logout option
     */
    public void clickLogout()
    {
        clickProfileIcon();
        Mapper.find(domFile, "logoutOption").click();
    }

    /**
     * Function for performing logout
     */
    public void performLogout()
    {
        clickProfileIcon();
        clickLogout();
    }

    /**
     * Function for validation of Early Bird Discount coupons on dashboard
     */
    public boolean validateEarlyBirdCoupons()
    {
        if(!(Mapper.find(domFile, "earlyBirdFirstCoupon").isDisplayed()) || !(Mapper.find(domFile, "earlyBirdSecondCoupon").isDisplayed()) || !(Mapper.find(domFile, "earlyBirdThirdCoupon").isDisplayed()) || !(Mapper.find(domFile, "upgradeButton").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for validation of Early Bird Discount not shown for existing user
     */
    public boolean validateEarlyBirdCouponsNotShown()
    {
        Boolean status = false;
        try
        {
            if ((Mapper.find(domFile, "earlyBirdFirstCoupon").isDisplayed()) || (Mapper.find(domFile, "earlyBirdSecondCoupon").isDisplayed()) || (Mapper.find(domFile, "earlyBirdThirdCoupon").isDisplayed()) || (Mapper.find(domFile, "upgradeButton").isDisplayed()))
               status = false;
        }
        catch (Exception e)
        {
            status = true;
        }
        return status;
    }

    /**
     * Function for validation of each coupon details
     */
    public boolean validateEachCoupon()
    {
        if (!(Mapper.find(domFile, "firstCouponValidity").isDisplayed()) || !(Mapper.find(domFile,"firstCouponCode").isDisplayed()) || !(Mapper.find(domFile,"secondCouponValidity").isDisplayed()) || !(Mapper.find(domFile,"secondCouponCode").isDisplayed()) || !(Mapper.find(domFile, "thirdCouponValidity").isDisplayed()) || !(Mapper.find(domFile, "thirdCouponCode").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for validation for color of most offerable coupons
     */
    public void getColorOfMostOfferableCoupon()
    {
        System.out.println("Color is : "+Mapper.getFontColor(domFile, "mostOfferableCoupon"));
    }

    /**
     * Function for clicking First Chapter from Dashboard
     */
    public void clickFirstChapterName()
    {
        Mapper.find(domFile, "chapterName").click();
    }

    /**
     * Function for clicking Upgrade option
     */
    public void clickOnUpgradeOption()
    {
        Mapper.find(domFile, "upgradeOption").click();
    }

    /**
     * Function for validation of upgrade option not displayed
     */
    public boolean validateUpgradeOptionNotDisplayed()
    {
        Boolean status = false;
        try {
            Thread.sleep(5000);
            if (Mapper.find(domFile, "upgradeOption").isDisplayed())
                status = true;
            else
                status = false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            status = true;
        }
        return status;
    }

    /**
     * Function for clicking My Profile option
     */
    public void clickMyProfileOption()
    {
        Mapper.find(domFile, "profileIcon").click();
        Mapper.find(domFile, "myProfileOption").click();
    }

    /**
     * Function for clicking Testseries from LHS
     */
    public void clickTestSeriesFromLHS()
    {
        try{
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Mapper.waitForElementToBeVisible(domFile, "testSeriesOnLHS");
        Mapper.find(domFile, "testSeriesOnLHS").click();
    }

    /**
     * Function for clicking Doubts from LHS
     */
    public void clickDoubtsFromLHS()
    {
        try{
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Mapper.waitForElementToBeVisible(domFile, "doubtsOnLHS");
        Mapper.find(domFile, "doubtsOnLHS").click();
    }

    /**
     * Function for clicking Doubts from LHS
     */
    public void clickPreviousPapersFromLHS()
    {
        Mapper.find(domFile, "previousPaperOnLHS").click();
    }

    /**
     * Function for closing coachmarks
     */
    public void closeCoachmark()
    {
        Mapper.waitForElementToBeVisible(domFile, "closeCoachMark");
        Mapper.find(domFile, "closeCoachMark").click();
        Mapper.waitForElementToBeInvisible(domFile, "closeCoachMark");
    }
}
