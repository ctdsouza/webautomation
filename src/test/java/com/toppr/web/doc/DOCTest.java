package com.toppr.web.doc;

import com.toppr.web.TestBase;
import com.toppr.web.dashboard.Dashboard;
import com.toppr.web.homePage.HomePage;
import com.toppr.web.login.Login;
import com.toppr.web.settings.Settings;
import com.toppr.web.signUp.SignUp;
import com.toppr.web.testSeries.TestSeries;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.toppr.utils.PropertyReader.getProperties;
import static com.toppr.utils.XMLReader.getTestData;

/**
 * Created by cliforddsouza on 02-Mar-2016.
 */
public class DOCTest extends TestBase
{
    Login login = new Login();
    HomePage homePage = new HomePage();
    Dashboard dashboard = new Dashboard();
    Settings settings = new Settings();
    SignUp signUp = new SignUp();
    TestSeries testSeries = new TestSeries();
    DOC doc = new DOC();
    private HashMap<String, String> testData = getTestData(getProperties().get("DOC_TESTDATA_FILE"));

    /**
     * Testcase - freemium doc to new user
     */
    @Test(groups = "acceptance")
    public void validateFreemiumDocForNewUser() throws Exception {
        System.out.println("Testcase - freemium doc to new user");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        signUp.confirmSignUpDetails();
        dashboard.closeCoachmark();
        Assert.assertTrue(dashboard.validateDashBoardofNewUser(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickDoubtsFromLHS();
        Assert.assertTrue(doc.validateFreemiumMessageDisplayed(), "Freemium Doc not displayed for new user");
    }

    /**
     * Testcase - Ask a Doubt For new user
     */
    @Test(groups = "acceptance")
    public void validateAskADoubtForNewUser() throws Exception {
        System.out.println("Testcase - Ask a Doubt For new user");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        signUp.confirmSignUpDetails();
        dashboard.closeCoachmark();
        Assert.assertTrue(dashboard.validateDashBoardofNewUser(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickDoubtsFromLHS();
        Assert.assertTrue(doc.validateFreemiumMessageDisplayed(), "Freemium Doc not displayed for new user");
        Assert.assertTrue(doc.startPhysicsDoubtSession(), "Start new doubt session failed");
    }

    /**
     * Testcase - DOC for Upgraded User
     */
    @Test(groups = {"acceptance", "productionsanity"})
    public void validateDOCForUpgradedUser()
    {
        System.out.println("Testcase - DOC for Upgraded User");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("unlockUserLogin"), testData.get("unlockUserPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickDoubtsFromLHS();
        Assert.assertTrue(doc.validateFreemiumMessageNotDisplayed(), "Freemium Doc displayed for upgraded user");
    }
}
