package com.toppr.web.settings;

import com.toppr.utils.Mapper;
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
 * Created by cliforddsouza on 02-Mar-2016.
 */
public class SettingsTest extends TestBase
{
    Login login = new Login();
    HomePage homePage = new HomePage();
    Dashboard dashboard = new Dashboard();
    Settings settings = new Settings();
    private HashMap<String, String> testData = getTestData(getProperties().get("SETTINGS_TESTDATA_FILE"));

    /**
     * Testcase - User update profile name with blank value
     */
    @Test(groups = {"acceptance", "productionsanity"})
    public void validateUpdateProfileNameWithBlankValue()
    {
        System.out.println("Testcase - User update profile name with blank value");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickSettingOption();
        settings.setNameFieldBlank();
        settings.clickSaveButton();
        Assert.assertTrue(settings.validateBlankNameSave(), "Error message isn't shown on saving blank name");
    }
}
