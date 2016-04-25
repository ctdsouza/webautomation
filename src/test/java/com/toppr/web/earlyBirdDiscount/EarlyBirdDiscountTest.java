package com.toppr.web.earlyBirdDiscount;

import com.toppr.web.TestBase;
import com.toppr.web.dashboard.Dashboard;
import com.toppr.web.homePage.HomePage;
import com.toppr.web.login.Login;
import com.toppr.web.profile.Profile;
import com.toppr.web.signUp.SignUp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.toppr.utils.PropertyReader.getProperties;
import static com.toppr.utils.XMLReader.getTestData;

/**
 * Created by cliforddsouza on 07-Dec-2015.
 */
public class EarlyBirdDiscountTest extends TestBase
{
    Login login = new Login();
    HomePage homePage = new HomePage();
    Dashboard dashboard = new Dashboard();
    Profile profile = new Profile();
    SignUp signUp = new SignUp();
    private HashMap<String, String> testData = getTestData(getProperties().get("EARLYBIRDDISCOUNT_TESTDATA_FILE"));

    /**
     * Testcase - Validation of EBD on user signup
     */
    @Test
    public void validateEBDOnSignup() throws Exception {
        System.out.println("Testcase - Validation of EBD on user signup");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        //signUp.selectState();
        signUp.confirmSignUpDetails();
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "SignUp with new user failed");
        Assert.assertTrue(dashboard.validateEarlyBirdCoupons(), "Validation of EBD on User Signup failed");
    }

    /**
     * Testcase - Dashborad display of EBD Coupons
     */
    @Test
    public void validateDashboardDisplayOfEBD() throws Exception {
        System.out.println("Testcase - Dashborad display of EBD Coupons");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        //signUp.selectState();
        signUp.confirmSignUpDetails();
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "SignUp with new user failed");
        Assert.assertTrue(dashboard.validateEarlyBirdCoupons(), "Dashborad display of EBD Coupons failed");
    }

    /**
     * Testcase - Existing user login
     */
    @Test
    public void validateNoEBDForExistingUser()
    {
        System.out.println("Testcase - Existing user login");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        Assert.assertTrue(dashboard.validateEarlyBirdCouponsNotShown(), "Early Bird Discount shown for existing user");
    }

    /**
     * Testcase - Validation of details of EBD Coupons
     */
    @Test
    public void validateDetailsOfEBD() throws Exception {
        System.out.println("Testcase - Validation of details of EBD Coupons");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        //signUp.selectState();
        signUp.confirmSignUpDetails();
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "SignUp with new user failed");
        dashboard.getColorOfMostOfferableCoupon();
        Assert.assertTrue(dashboard.validateEachCoupon(), "Validation of details of EBD Coupons failed");
    }

}
