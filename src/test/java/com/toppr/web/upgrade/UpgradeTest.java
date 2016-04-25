package com.toppr.web.upgrade;

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
 * Created by cliforddsouza on 01-Mar-2016.
 */
public class UpgradeTest extends TestBase
{
    Login login = new Login();
    HomePage homePage = new HomePage();
    Dashboard dashboard = new Dashboard();
    Upgrade upgrade = new Upgrade();
    private HashMap<String, String> testData = getTestData(getProperties().get("UPGRADE_TESTDATA_FILE"));

    /**
     * Testcase - JEE Student Upgrade to Advanced Package
     */
    @Test(groups = {"acceptance", "productionsanity"})
    public void jeeStudentUpgradeToAdvancedPackage()
    {
        System.out.println("Testcase - JEE Student Upgrade to Advanced Package");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickOnUpgradeOption();
        upgrade.selectAdvancedPackage();
        //Assert.assertTrue(upgrade.validateOrderSummary(testData.get("packageName"), testData.get("packageValidity"), testData.get("packagePrice")), "Order details isn't proper");
        //upgrade.clickProceedButton();
        Assert.assertTrue(upgrade.validateOrderSummary(testData.get("packageName"), testData.get("packageValidity"), testData.get("packagePrice")), "Order details isn't proper on Confirm Details");
        Assert.assertTrue(upgrade.validateBillingDetailsValues(testData.get("billingUserName"), testData.get("billingEmail"), testData.get("billingPhone")), "Billing details isn't proper");
        upgrade.clickPayButton();
        Assert.assertTrue(upgrade.validatePayUPaymentPage(), "PayU payment gateway page isn't shown to User");
    }

    /**
     * Testcase - Foundation Student Upgrade to Advanced Package
     */
    @Test(groups = {"acceptance", "productionsanity"})
    public void foundationStudentUpgradeToAdvancedPackage()
    {
        System.out.println("Testcase - Foundation Student Upgrade to Advanced Package");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("foundationLogin"), testData.get("foundationPassword"));
        Assert.assertTrue(dashboard.validateFoundationDashBoard(testData.get("foundationFirstSubject"), testData.get("foundationSecondSubject"), testData.get("foundationThirdSubject"), testData.get("foundationForthSubject")), "Login with Foundation user failed");
        dashboard.clickOnUpgradeOption();
        upgrade.selectClassXAndJeePackage();
        //Assert.assertTrue(upgrade.validateOrderSummary(testData.get("foundationPackageName"), testData.get("foundationPackageValidity"), testData.get("foundationPackagePrice")), "Order details isn't proper");
        //upgrade.clickProceedButton();
        Assert.assertTrue(upgrade.validateOrderSummary(testData.get("foundationPackageName"), testData.get("foundationPackageValidity"), testData.get("foundationPackagePrice")), "Order details isn't proper on Confirm Details");
        Assert.assertTrue(upgrade.validateBillingDetailsValues(testData.get("foundationBillingUserName"), testData.get("foundationBillingEmail"), testData.get("foundationBillingPhone")), "Billing details isn't proper");
        upgrade.clickPayButton();
        Assert.assertTrue(upgrade.validatePayUPaymentPage(), "PayU payment gateway page isn't shown to User");
    }

    /**
     * Testcase - Validation of Upgrade option for Ultimate Package Upgraded User
     */
    @Test(groups = {"acceptance", "productionsanity"})
    public void validateUpgradeOptionForUltimatePackageUpgradedUser() {
        System.out.println("Testcase - Validation of Upgrade option for Ultimate Package Upgraded User");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("ultimateUserLogin"), testData.get("ultimateUserPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        Assert.assertTrue(dashboard.validateUpgradeOptionNotDisplayed(), "Upgrade option is displayed for Ultimate Package upgraded user");
    }

    /**
     * Testcase - Upgrade page for JEE student
     */
    @Test
    public void upgradePageForJEEStudent() {
        System.out.println("Testcase - Upgrade page for JEE student");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickOnUpgradeOption();
        Assert.assertTrue(upgrade.validateUpgradePageForJEE(), "Validation of JEE upgrade page failed");
        Assert.assertTrue(upgrade.validateJEEPackageSellPrices(testData.get("jee2017LitePackageSellPrice"),
                testData.get("jee2017BasicPackageSellPrice"),
                testData.get("jee2017AdvancedPackageSellPrice"),
                testData.get("jee2017MasterPackageSellPrice"),
                testData.get("jee2017UltimatePackageSellPrice")),
            "All package sell prices not proper");
    }

    /**
     * Testcase - Upgrade page for PMT student
     */
    @Test
    public void upgradePageForPMTStudent() {
        System.out.println("Testcase - Upgrade page for PMT student");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("preMedicalLogin"), testData.get("premedicalPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("preMedicalFirstSubject"), testData.get("preMedicalSecondSubject"), testData.get("preMedicalThirdSubject")), "Login with PMT user failed");
        dashboard.clickOnUpgradeOption();
        Assert.assertTrue(upgrade.validateUpgradePageForPMT(), "Validation of PMT upgrade page failed");
    }
}
