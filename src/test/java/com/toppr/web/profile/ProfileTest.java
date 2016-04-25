package com.toppr.web.profile;

import com.toppr.web.TestBase;
import com.toppr.web.dashboard.Dashboard;
import com.toppr.web.homePage.HomePage;
import com.toppr.web.login.Login;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.toppr.utils.PropertyReader.getProperties;
import static com.toppr.utils.XMLReader.getTestData;

/**
 * Created by cliforddsouza on 07-Dec-2015.
 */
public class ProfileTest extends TestBase
{
    Login login = new Login();
    HomePage homePage = new HomePage();
    Dashboard dashboard = new Dashboard();
    Profile profile = new Profile();
    private HashMap<String, String> testData = getTestData(getProperties().get("PROFILE_TESTDATA_FILE"));

    /**
     * Testcase - name is blank
     */
    @Test
    public void setNameBlankInProfile()
    {
        System.out.println("Testcase - name is blank");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.loginWithoutPassword(testData.get("noPasswordLogin"));
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "Login with no password user failed");
        dashboard.clickProfileIcon();
        dashboard.clickSettingOption();
        Assert.assertTrue(profile.validateBlankNameSave(testData.get("nameErrorMessage"), testData.get("saveChangesErrorMessage")), "Set Name as Blank failed");
    }

    /**
     * Testcase - manually set name
     */
    @Test
    public void manuallySetNewNameInProfile()
    {
        System.out.println("Testcase - manually set name");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickProfileIcon();
        dashboard.clickSettingOption();
        profile.setNewName(testData.get("newName"));
        Assert.assertTrue(profile.validateSaveProfile(testData.get("nameErrorMessage"), testData.get("saveChangesErrorMessage"), testData.get("saveChangesSuccessMessage")), "Manually Set Name failed");
    }

    /**
     * Testcase - new user phone number
     */
    @Test(enabled = false)
    public void manuallySetNewPhoneNumber()
    {
        System.out.println("Testcase - new user phone number");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateDashBoard(), "Login with Topper id and password failed");
        dashboard.clickProfileIcon();
        dashboard.clickSettingOption();
        Assert.assertTrue(profile.validatePhoneNumberLocked(), "Phone number isn't lock or non-editable");
    }

    /**
     * Testcase - existing user with unverified phone number
     */
    @Test(enabled = false)
    public void verifyPhoneNumberMessageForExistingUser()
    {
        System.out.println("Testcase - existing user with unverified phone number");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("unlockUserLogin"), testData.get("unlockUserPassword"));
        dashboard.clickProfileIcon();
        dashboard.clickSettingOption();
        Assert.assertTrue(profile.validateVerifyPhoneNumberMessage(testData.get("verifyPhoneNumberMessage")), "Verify Phone number message not shown to User");
    }
}
