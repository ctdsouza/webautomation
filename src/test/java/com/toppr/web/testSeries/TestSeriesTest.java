package com.toppr.web.testSeries;

import com.toppr.web.TestBase;
import com.toppr.web.dashboard.Dashboard;
import com.toppr.web.homePage.HomePage;
import com.toppr.web.login.Login;
import com.toppr.web.settings.Settings;
import com.toppr.web.signUp.SignUp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static com.toppr.utils.PropertyReader.getProperties;
import static com.toppr.utils.XMLReader.getTestData;

/**
 * Created by cliforddsouza on 02-Mar-2016.
 */
public class TestSeriesTest extends TestBase
{
    Login login = new Login();
    HomePage homePage = new HomePage();
    Dashboard dashboard = new Dashboard();
    Settings settings = new Settings();
    SignUp signUp = new SignUp();
    TestSeries testSeries = new TestSeries();
    private HashMap<String, String> testData = getTestData(getProperties().get("TESTSERIES_TESTDATA_FILE"));

    /**
     * Testcase - Test series for JEE 2017 Student
     */
    @Test(groups = {"acceptance", "productionsanity"}, sequential = true)
    public void validateTestSeriesForJEE2017Student()
    {
        System.out.println("Testcase - Test series for JEE 2017 Student");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfIIT(testData.get("iitTestYear")), "Test year for IIT isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForIIT(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validatePastTestDateCount(), "Past Test dates aren't proper");
        Assert.assertTrue(testSeries.validateUpcomingTestDateCount(), "Upcoming Test dates aren't proper");
    }

    /**
     * Testcase - Test series for JEE 2016 Student
     */
    @Test(sequential = true)
    public void validateTestSeriesForJEE2016Student()
    {
        System.out.println("Testcase - Test series for JEE 2016 Student");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("iit2016UserEmail"), testData.get("iit2016UserPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfIIT(testData.get("iit2016TestYear")), "Test year for IIT isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForIIT(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validatePastTestDateCount(), "Past Test dates aren't proper");
        Assert.assertTrue(testSeries.validateUpcomingTestDateCount(), "Upcoming Test dates aren't proper");
    }

    /**
     * Testcase - Test series for PMT 2017 Student
     */
    @Test(sequential = true)
    public void validateTestSeriesForPMT2017Student()
    {
        System.out.println("Testcase - Test series for PMT 2017 Student");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("bio2017UserEmail"), testData.get("bio2017UserPassword"));
        Assert.assertTrue(dashboard.validatePreMedicalDashBoard(testData.get("bioFirstSubject"), testData.get("bioSecondSubject"), testData.get("bioThirdSubject")), "Login with PMT user failed");
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfPMT(testData.get("bio2017TestYear")), "Test year for PMT isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForPMT(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validatePastTestDateCount(), "Past Test dates aren't proper");
        Assert.assertTrue(testSeries.validateUpcomingTestDateCount(), "Upcoming Test dates aren't proper");
    }

    /**
     * Testcase - Test series for PMT 2016 Student
     */
    @Test(sequential = true)
    public void validateTestSeriesForPMT2016Student()
    {
        System.out.println("Testcase - Test series for JEE 2016 Student");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("bio2016UserEmail"), testData.get("bio2016UserPassword"));
        Assert.assertTrue(dashboard.validatePreMedicalDashBoard(testData.get("bioFirstSubject"), testData.get("bioSecondSubject"), testData.get("bioThirdSubject")), "Login with PMT user failed");
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfPMT(testData.get("bio2016TestYear")), "Test year for PMT isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForPMT(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validatePastTestDateCount(), "Past Test dates aren't proper");
        Assert.assertTrue(testSeries.validateUpcomingTestDateCount(), "Upcoming Test dates aren't proper");
    }

    /**
     * Testcase - Test series for Foundation 10th user
     */
    @Test(sequential = true)
    public void validateTestSeriesForFoundationUser()
    {
        System.out.println("Testcase - Test series for Foundation 10th user");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("foundationLogin"), testData.get("foundationPassword"));
        Assert.assertTrue(dashboard.validateFoundationDashBoard(testData.get("foundationFirstSubject"), testData.get("foundationSecondSubject"), testData.get("foundationThirdSubject"), testData.get("foundationForthSubject")), "Login with Foundation user failed");
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfFoundation(testData.get("foundationTestYear")), "Test year for Foundation isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForFoundation(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validatePastTestDateCount(), "Past Test dates aren't proper");
        Assert.assertTrue(testSeries.validateUpcomingTestDateCount(), "Upcoming Test dates aren't proper");
    }

    /**
     * Testcase - Test series validation on Switch Class
     */
    @Test(sequential = true)
    public void validateTestSeriesOnSwitchClass() throws Exception {
        System.out.println("Testcase - Test series validation on Switch Class");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        //signUp.selectState();
        signUp.confirmSignUpDetails();
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "SignUp with new user failed");
        dashboard.closeCoachmark();
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfIIT(testData.get("iit2016TestYear")), "Test year for IIT isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForIIT(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validatePastTestDateCount(), "Past Test dates aren't proper");
        Assert.assertTrue(testSeries.validateUpcomingTestDateCount(), "Upcoming Test dates aren't proper");
        dashboard.clickProfileIcon();
        dashboard.clickSettingOption();
        settings.setNameField(testData.get("profileName"));
        settings.changeClassTo12();
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfPMT(testData.get("bio2016TestYear")), "Test year for PMT isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForPMT(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validatePastTestDateCount(), "Past Test dates aren't proper");
        Assert.assertTrue(testSeries.validateUpcomingTestDateCount(), "Upcoming Test dates aren't proper");
    }

    /**
     * Testcase - Attempt Test series for 11th standard IIT-JEE student
     */
    @Test(groups = "acceptance", sequential = true)
    public void validateAttemptIIT11TestSeries() throws Exception {
        System.out.println("Testcase - Attempt Test series for 11th standard IIT-JEE student");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        //signUp.selectState();
        signUp.confirmSignUpDetails();
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.closeCoachmark();
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfIIT(testData.get("iit2016TestYear")), "Test year for IIT isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForIIT(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validatePastTestDateCount(), "Past Test dates aren't proper");
        Assert.assertTrue(testSeries.validateUpcomingTestDateCount(), "Upcoming Test dates aren't proper");
        testSeries.startTestSeries();
        Assert.assertTrue(testSeries.validateTestSeriesLoaded(), "Test series isn't loaded Properly");
        testSeries.answerQuestion();
        testSeries.finishTest();
        Assert.assertTrue(testSeries.validateReportOverviewPage(), "Details shown on Report Overview page isn't proper");
    }

    /**
     * Testcase - View Report for Upgraded User
     */
    @Test(sequential = true)
    public void viewTestReportForUpgradedUser()
    {
        System.out.println("Testcase - View Report for Upgraded User");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("unlockUserLogin"), testData.get("unlockUserPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickTestSeriesFromLHS();
        testSeries.clickViewReport();
        Assert.assertTrue(testSeries.validateReportOverviewPage(), "Validation of Report overview page is failed");
        testSeries.clickViewFullReport();
        Assert.assertTrue(testSeries.validateFullReportShowedToUpgradedUser(), "Full Report isn't shown to Upgraded user");
    }

    /**
     * Testcase - View Report for Non-upgraded User
     */
    @Test(sequential = true)
    public void viewTestReportForNonupgradedUser()
    {
        System.out.println("Testcase - View Report for Non-upgraded User");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickTestSeriesFromLHS();
        testSeries.clickViewReport();
        Assert.assertTrue(testSeries.validateReportOverviewPage(), "Validation of Report overview page is failed");
        testSeries.clickViewFullReport();
        Assert.assertTrue(testSeries.validateUpgradePromptDisplayed(), "Full report is shown to non-upgraded user");
    }

    /**
     * Testcase - validation of Live Test Details
     */
    @Test(sequential = true)
    public void validateLiveTestDetails() throws Exception {
        System.out.println("Testcase - validation of Live Test Details");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        //signUp.selectState();
        signUp.confirmSignUpDetails();
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "SignUp with new user failed");
        dashboard.closeCoachmark();
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfIIT(testData.get("iit2016TestYear")), "Test year for IIT isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForIIT(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validateLiveTestDetails(), "Live Test details aren't proper");
    }

    /**
     * Testcase - Attempt past test
     */
    @Test(sequential = true)
    public void attemptPastTest() throws Exception {
        System.out.println("Testcase - Attempt past test");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        //signUp.selectState();
        signUp.confirmSignUpDetails();
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "SignUp with new user failed");
        dashboard.closeCoachmark();
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfIIT(testData.get("iit2016TestYear")), "Test year for IIT isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForIIT(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validateLiveTestDetails(), "Live Test details aren't proper");
        testSeries.clickPastTest();
        testSeries.startPastTestSeries();
        testSeries.answerQuestion();
        testSeries.finishTest();
        Assert.assertTrue(testSeries.validateReportOverviewPage(), "Details shown on Report Overview page isn't proper");
    }

    /**
     * Testcase - Click on upcoming test
     */
    @Test(sequential = true)
    public void clickOnUpcomingTest()
    {
        System.out.println("Testcase - Click on upcoming test");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickTestSeriesFromLHS();
        testSeries.clickUpcomingTest();
        Assert.assertTrue(testSeries.validateUpcomingTestPrompt(), "Upcoming Test prompt doesn't have proper details");
    }

    /**
     * Testcase - Click on Attempted test
     */
    @Test(sequential = true)
    public void clickOnAttemptedTest()
    {
        System.out.println("Testcase - Click on Attempted test");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("bio2017UserEmail"), testData.get("bio2017UserPassword"));
        Assert.assertTrue(dashboard.validatePreMedicalDashBoard(testData.get("bioFirstSubject"), testData.get("bioSecondSubject"), testData.get("bioThirdSubject")), "Login with PMT user failed");
        dashboard.clickTestSeriesFromLHS();
        testSeries.clickAttemptedTest();
        Assert.assertTrue(testSeries.validateAttemptedTestPrompt(), "Attempted Test prompt doesn't have proper details");
    }

    /**
     * Testcase - Click on Resume Test
     */
    @Test(sequential = true)
    public void clickResumeTest() throws Exception {
        System.out.println("Testcase - Click on Resume Test");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        //signUp.selectState();
        signUp.confirmSignUpDetails();
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "SignUp with new user failed");
        dashboard.closeCoachmark();
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfIIT(testData.get("iit2016TestYear")), "Test year for IIT isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForIIT(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validatePastTestDateCount(), "Past Test dates aren't proper");
        Assert.assertTrue(testSeries.validateUpcomingTestDateCount(), "Upcoming Test dates aren't proper");
        testSeries.startTestSeries();
        Assert.assertTrue(testSeries.validateTestSeriesLoaded(), "Test series isn't loaded Properly");
        testSeries.answerQuestion();
        testSeries.resumeTest();
        Assert.assertTrue(testSeries.validateTestSeriesLoaded(), "Test series isn't loaded Properly after clicking Resume Button");
        testSeries.answerQuestion();
        testSeries.finishTest();
        Assert.assertTrue(testSeries.validateReportOverviewPage(), "Details shown on Report Overview page isn't proper");
    }

    /**
     * Testcase - Click on Submit Test
     */
    @Test(sequential = true)
    public void clickSubmitTest() throws Exception
    {
        System.out.println("Testcase - Click on Submit Test");
        homePage.clickSignUp();
        Assert.assertTrue(signUp.validateSignUpPage(), "Validation of SignUp page failed");
        signUp.enterSignUpDetails(testData.get("emailText"), testData.get("phoneNumber"));
        signUp.selectClass();
        signUp.selectState();
        signUp.selectStream();
        //signUp.selectState();
        signUp.confirmSignUpDetails();
        Assert.assertTrue(dashboard.validateNoPasswordDashBoard(testData.get("setPasswordMessage")), "SignUp with new user failed");
        dashboard.closeCoachmark();
        dashboard.clickTestSeriesFromLHS();
        Assert.assertTrue(testSeries.vaildateTestYearTitleOfIIT(testData.get("iit2016TestYear")), "Test year for IIT isn't proper");
        Assert.assertTrue(testSeries.validateTestSeriesPage(), "Test series page isn't loaded properly");
        Assert.assertTrue(testSeries.validateTestSyllabusSubjectsForIIT(), "Test Syllabus subjects aren't proper");
        Assert.assertTrue(testSeries.validatePastTestDateCount(), "Past Test dates aren't proper");
        Assert.assertTrue(testSeries.validateUpcomingTestDateCount(), "Upcoming Test dates aren't proper");
        testSeries.startTestSeries();
        Assert.assertTrue(testSeries.validateTestSeriesLoaded(), "Test series isn't loaded Properly");
        testSeries.answerQuestion();
        testSeries.finishTest();
        Assert.assertTrue(testSeries.validateReportOverviewPage(), "Details shown on Report Overview page isn't proper");
    }

    /**
     * Testcase - Answer key for upgraded user
     */
    @Test(sequential = true)
    public void answerKeyForUpgradedUser()
    {
        System.out.println("Testcase - Answer key for upgraded user");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("unlockUserLogin"), testData.get("unlockUserPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickTestSeriesFromLHS();
        testSeries.clickViewReport();
        testSeries.clickOnAnswerKey();
        Assert.assertTrue(testSeries.validateAnswerKeyForUpgradedUser(), "Answer key shown properly to upgraded user");
    }

    /**
     * Testcase - Answer key for non-upgraded user
     */
    @Test(sequential = true)
    public void answerKeyForNonUpgradedUser()
    {
        System.out.println("Testcase - Answer key for non-upgraded user");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("validEmail"), testData.get("validPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickTestSeriesFromLHS();
        testSeries.clickViewReport();
        testSeries.clickOnAnswerKey();
        Assert.assertTrue(testSeries.validateUpgradePromptDisplayed(), "Answer Key is shown to non-upgraded user");
    }

    /**
     * Testcase - Filter questions on Answer Key
     */
    @Test(sequential = true)
    public void filterQuestionsOnAnswerKey()
    {
        System.out.println("Testcase - Filter questions on Answer Key");
        homePage.clickLogin();
        Assert.assertTrue(login.validateLoginPage(), "Validation of Login page failed");
        login.enterLoginDetails(testData.get("unlockUserLogin"), testData.get("unlockUserPassword"));
        Assert.assertTrue(dashboard.validateIITDashBoard(testData.get("iitFirstSubject"), testData.get("iitSecondSubject"), testData.get("iitThirdSubject")), "Login with IIT JEE user failed");
        dashboard.clickTestSeriesFromLHS();
        testSeries.clickViewReport();
        testSeries.clickOnAnswerKey();
        testSeries.applyChemistryFilter();
        Assert.assertTrue(testSeries.validateAnswerKeyForUpgradedUser(), "Answer key shown properly to upgraded user");
    }
}
