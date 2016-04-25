package com.toppr.web.assessment;

import com.toppr.web.TestBase;
import com.toppr.web.chapter.Chapter;
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
public class AssessmentTest extends TestBase
{
    private HashMap<String, String> testData = getTestData(getProperties().get("ASSESSMENT_TESTDATA_FILE"));
    Assessment assessment = new Assessment();
    Chapter chapter = new Chapter();
    Login login = new Login();
    HomePage homePage = new HomePage();
    Dashboard dashboard = new Dashboard();

    /**
     * Testcase - Practise 5 questions from Assessment
     */
    @Test(groups = {"acceptance", "productionsanity"})
    public void practise5QuestionsFromAssessment()
    {
        System.out.println("Testcase - Practise 5 questions from Assessment");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickFirstChapterName();
        chapter.clickFirstGoal();
        for (int i = 1; i <= 5; i++) {
            assessment.attemptQuestion();
        }
        assessment.clickPauseButton();
        Assert.assertTrue(assessment.validateTestPausedPopupMessage(), "Test paused popup message not shown");
    }
}
