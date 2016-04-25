package com.toppr.web.productionSanity;

import com.toppr.utils.Mapper;
import com.toppr.web.TestBase;
import com.toppr.web.dashboard.Dashboard;
import com.toppr.web.homePage.HomePage;
import com.toppr.web.login.Login;
import com.toppr.web.profile.Profile;
import com.toppr.web.signUp.SignUp;
import org.apache.xerces.util.SynchronizedSymbolTable;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.toppr.utils.PropertyReader.getProperties;
import static com.toppr.utils.XMLReader.getTestData;

/**
 * Created by cliforddsouza on 07-Dec-2015.
 */
public class ProductionSanityTest extends TestBase
{
    Login login = new Login();
    HomePage homePage = new HomePage();
    Dashboard dashboard = new Dashboard();
    Profile profile = new Profile();
    SignUp signUp = new SignUp();
    private HashMap<String, String> testData = getTestData(getProperties().get("PRODUCTIONSANITY_TESTDATA_FILE"));

    /**
     * Testcase - Perform actions like signup, login, upgrade, switch course on Topper website
     */
    @Test
    public void performProductionSanityTest() throws Exception {
        System.out.println("Executing Production Sanity");

        //Signup with new user
        System.out.println("Now Executing - Signup with New User");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectStream();
        signUp.selectState();
        signUp.confirmSignUpDetails();
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "SignUp with new user failed");
        dashboard.performLogout();

        //Login with with IIT JEE user
        System.out.println("Now Executing - login with IIT JEE user");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.performLogout();

        //login with Pre medical user
        System.out.println("Now Executing - login with Pre medical user");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("preMedicalLogin"), testData.get("premedicalPassword"));
        Assert.assertTrue(dashboard.validatePreMedicalDashBoard(testData.get("preMedicalFirstSubject"), testData.get("preMedicalSecondSubject"), testData.get("preMedicalThirdSubject")), "Login with Pre medical user failed");
        dashboard.performLogout();

        //login with foundation user
        System.out.println("Now Executing - login with Foundation user");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("foundationLogin"), testData.get("foundationPassword"));
        Assert.assertTrue(dashboard.validateFoundationDashBoard(testData.get("foundationFirstSubject"), testData.get("foundationSecondSubject"), testData.get("foundationThirdSubject"), testData.get("foundationForthSubject")), "Login with Foundation user failed");
        dashboard.performLogout();

        //login with facebook
        System.out.println("Now Executing - login with facebook");
        homePage.clickLogin();
        login.loginWithFacebook(testData.get("fbLoginEmail"), testData.get("fbLoginPassword"));
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "Login with Facebook failed");
        dashboard.performLogout();

        //login with Google
        System.out.println("Now Executing - login with Google");
        homePage.clickLogin();
        login.loginWithGoogle(testData.get("googleLoginEmail"), testData.get("googleLoginPassword"));
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "Login with Google failed");
        dashboard.performLogout();
    }
}
