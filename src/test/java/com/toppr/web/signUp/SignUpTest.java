package com.toppr.web.signUp;

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
 * Created by cliforddsouza on 04-Dec-2015.
 */
public class SignUpTest extends TestBase
{
    SignUp signUp = new SignUp();
    Login login = new Login();
    HomePage homePage = new HomePage();
    Dashboard dashboard = new Dashboard();
    private HashMap<String, String> testData = getTestData(getProperties().get("SIGNUP_TESTDATA_FILE"));

    /**
     * Testcase - New user toppr signup with valid inputs
     */
    @Test(groups = "acceptance")
    public void signUpWithNewEmail() throws Exception {
        System.out.println("Testcase - New user toppr signup with valid inputs");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        signUp.confirmSignUpDetails();
        dashboard.closeCoachmark();
        Assert.assertTrue(dashboard.validateDashBoardofNewUser(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
    }

    /**
     * Testcase - Signup with invalid email id
     */
    @Test(groups = "acceptance")
    public void signUpWithInvalidFormatEmail()
    {
        System.out.println("Testcase - login with invalid format email");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        Assert.assertTrue(signUp.validateLoginWithInvalidFormatEmail(testData.get("invalidFormatEmail"), testData.get("emailError")), "SignUp with invalid format email failed");
    }

    /**
     * Testcase - Signup with existing email id
     */
    @Test(groups = "acceptance")
    public void signUpWithExistingEmail() {
        System.out.println("Testcase - login with invalid format email");
        homePage.clickSignUp();
        signUp.enterSignUpEmail(testData.get("validEmail"));
        Assert.assertTrue(login.validatePasswordPage(), "Signup with existing email id is failed");
    }

    /**
     * Testcase - SignUp with existing facebook id
     */
    @Test
    public void signUpWithExistingFacebook()
    {
        System.out.println("Testcase - SignUp with existing facebook id");
        homePage.clickSignUp();
        signUp.signUpWithFacebook(testData.get("fbLoginEmail"), testData.get("fbLoginPassword"));
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "Signup with existing Facebook failed");
    }

    /**
     * Testcase - SignUp with existing Google id
     */
    @Test
    public void signUpWithExistingGoogle()
    {
        System.out.println("Testcase - SignUp with existing Google id");
        homePage.clickSignUp();
        signUp.signupWithGoogle(testData.get("googleLoginEmail"), testData.get("googleLoginPassword"));
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "SignUp with existing Google failed");
    }
}