package com.toppr.web.login;

import com.toppr.web.TestBase;
import com.toppr.web.dashboard.Dashboard;
import com.toppr.web.homePage.HomePage;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.toppr.utils.PropertyReader.getProperties;
import static com.toppr.utils.XMLReader.getTestData;

/**
 * Created by cliforddsouza on 03-Dec-2015.
 */
public class LoginTest extends TestBase
{
    Login login = new Login();
    HomePage homePage = new HomePage();
    Dashboard dashboard = new Dashboard();
    private HashMap<String, String> testData = getTestData(getProperties().get("LOGIN_TESTDATA_FILE"));

    /**
     * Testcase - login with valid toppr id, password set
     */
    @Test
    public void loginWithValidTopprIdAndPassword()
    {
        System.out.println("Testcase - login with valid toppr id, password set");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
    }

    /**
     * Testcase - login with IIT JEE user
     */
    @Test(groups = {"acceptance", "productionsanity"})
    public void loginWithIITJEEUser()
    {
        System.out.println("Testcase - login with IIT JEE user");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
    }

    /**
     * Testcase - login with Pre medical user
     */
    @Test(groups = {"acceptance", "productionsanity"})
    public void loginWithPreMedicalUser()
    {
        System.out.println("Testcase - login with Pre medical user");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("preMedicalLogin"), testData.get("premedicalPassword"));
        Assert.assertTrue(dashboard.validatePreMedicalDashBoard(testData.get("preMedicalFirstSubject"), testData.get("preMedicalSecondSubject"), testData.get("preMedicalThirdSubject")), "Login with Pre medical user failed");
    }

    /**
     * Testcase - login with foundation user
     */
    @Test(groups = {"acceptance", "productionsanity"})
    public void loginWithFoundationUser()
    {
        System.out.println("Testcase - login with Foundation user");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("foundationLogin"), testData.get("foundationPassword"));
        Assert.assertTrue(dashboard.validateFoundationDashBoard(testData.get("foundationFirstSubject"), testData.get("foundationSecondSubject"), testData.get("foundationThirdSubject"), testData.get("foundationForthSubject")), "Login with Foundation user failed");
    }

    /**
     * Testcase - login with no password user
     */
    @Test
    public void loginWithNoPasswordUser()
    {
        System.out.println("Testcase - login with no password user");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.loginWithoutPassword(testData.get("noPasswordLogin"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
    }

    /**
     * Testcase - login with invalid format email
     */
    @Test
    public void loginWithInvalidFormatEmail()
    {
        System.out.println("Testcase - login with invalid format email");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        Assert.assertTrue(login.validateLoginWithInvalidFormatEmail(testData.get("invalidFormatEmail"), testData.get("emailError")), "Login with invalid format email failed");
    }

    /**
     * Testcase - login with invalid password
     */
    @Test
    public void loginWithInvalidPassword()
    {
        System.out.println("Testcase - login with invalid password");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        Assert.assertTrue(login.validateLoginWithInvalidPassword(testData.get("validEmail"), testData.get("invalidPassword"), testData.get("passwordError")), "Login with invalid password failed");
    }

    /**
     * Testcase - login with facebook
     */
    @Test
    public void loginWithFacebook()
    {
        System.out.println("Testcase - login with facebook");
        homePage.clickLogin();
        login.loginWithFacebook(testData.get("fbLoginEmail"), testData.get("fbLoginPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
    }

    /**
     * Testcase - login with Google
     */
    @Test
    public void loginWithGoogle()
    {
        System.out.println("Testcase - login with Google");
        homePage.clickLogin();
        login.loginWithGoogle(testData.get("googleLoginEmail"), testData.get("googleLoginPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
    }
}
