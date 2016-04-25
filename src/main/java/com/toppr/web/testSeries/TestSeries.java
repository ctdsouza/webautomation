package com.toppr.web.testSeries;

import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 02-Mar-2016.
 */
public class TestSeries extends PageBase
{
    private static final String domFile = getProperties().get("TESTSERIES_DOM_FILE");
    public TestSeries()
    {
        super(domFile);
    }

    /**
     * Function for Validation of Testseries Title
     */
    public boolean vaildateTestYearTitleOfIIT(String year)
    {
        Mapper.waitForElementToBeVisible(domFile, "testDay");
        year = "IIT JEE "+year+" Test Series";
        System.out.println("Year:"+Mapper.find(domFile, "testSeriesTitle").getText());
        if(!(Mapper.find(domFile, "testSeriesTitle").getText().equals(year)))
            return false;
        return true;
    }

    /**
     * Function for validation of Test year Title of PMT
     */
    public boolean vaildateTestYearTitleOfPMT(String year)
    {
        Mapper.waitForElementToBeVisible(domFile, "testDay");
        year = "Pre Medical "+year+" Test Series";
        if(!(Mapper.find(domFile, "testSeriesTitle").getText().equals(year)))
            return false;
        return true;
    }

    /**
     * Function for validation of Test year Title of Foundation
     */
    public boolean vaildateTestYearTitleOfFoundation(String year)
    {
        Mapper.waitForElementToBeVisible(domFile, "testDay");
        year = "NTSE "+year+" Test Series";
        if(!(Mapper.find(domFile, "testSeriesTitle").getText().equals(year)))
            return false;
        return true;
    }

    /**
     * Function for validation of Testseries Page
     */
    public boolean validateTestSeriesPage()
    {
        List<WebElement> testList = Mapper.finds(domFile,"testList");
        if(!(Mapper.find(domFile, "liveNowText").isDisplayed())
                || !(testList.get(0).isDisplayed())
                || !(testList.get(1).isDisplayed())
                || !(Mapper.find(domFile, "howItWorksOption").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for validation of Testseries loaded
     */
    public boolean validateTestSeriesLoaded()
    {
        if(!(Mapper.find(domFile,"countdownTimer").isDisplayed()) || !(Mapper.find(domFile, "stopTestButton").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for Start test series
     */
    public void startTestSeries()
    {
        Mapper.waitForElementToBeVisible(domFile, "startTestButton");
        try
        {
            Thread.sleep(3000);
            Mapper.find(domFile, "startTestButton").click();
            Thread.sleep(3000);
            Mapper.find(domFile, "startTestOnConfirm").click();
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Function for Start past test series
     */
    public void startPastTestSeries()
    {
        Mapper.waitForElementToBeVisible(domFile, "startPastTest");
        try
        {
            Thread.sleep(3000);
            Mapper.find(domFile, "startPastTest").click();
            Thread.sleep(3000);
            Mapper.find(domFile, "startTestOnConfirm").click();
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Function for validation of subjects for test series syllabus of IIT
     */
    public boolean validateTestSyllabusSubjectsForIIT()
    {
        List<WebElement> subjectList = Mapper.finds(domFile, "syllabusSubjects");
        if (!(subjectList.size()==3)
                || !(subjectList.get(0).getText().equals("Physics"))
                || !(subjectList.get(1).getText().equals("Chemistry"))
                || !(subjectList.get(2).getText().equals("Maths")))
            return false;
        return true;
    }

    /**
     * Function for validation of subjects for test series syllabus of PMT
     */
    public boolean validateTestSyllabusSubjectsForPMT()
    {
        List<WebElement> subjectList = Mapper.finds(domFile, "syllabusSubjects");
        if (!(subjectList.size()==3)
                || !(subjectList.get(0).getText().equals("Physics"))
                || !(subjectList.get(1).getText().equals("Chemistry"))
                || !(subjectList.get(2).getText().equals("Biology")))
            return false;
        return true;
    }

    /**
     * Function for validation of subjects for test series syllabus of Foundation
     */
    public boolean validateTestSyllabusSubjectsForFoundation()
    {
        List<WebElement> subjectList = Mapper.finds(domFile, "syllabusSubjects");
        if (!(subjectList.size()==4)
                || !(subjectList.get(0).getText().equals("Physics"))
                || !(subjectList.get(1).getText().equals("Chemistry"))
                || !(subjectList.get(2).getText().equals("Maths"))
                || !(subjectList.get(3).getText().equals("Biology")))
            return false;
        return true;
    }

    /**
     * Function for validation past test date count
     */
    public boolean validatePastTestDateCount()
    {
        List<WebElement> pastTestList = Mapper.finds(domFile, "testDates");
        List<WebElement> testList = Mapper.finds(domFile,"testList");
        if(!(testList.get(0).isDisplayed())
                || !(pastTestList.size()>0))
            return false;
        return true;
    }

    /**
     * Function for validation upcoming test date count
     */
    public boolean validateUpcomingTestDateCount()
    {
        List<WebElement> pastTestList = Mapper.finds(domFile, "testDates");
        List<WebElement> testList = Mapper.finds(domFile,"testList");
        if(!(testList.get(1).isDisplayed())
                || !(pastTestList.size()>0))
            return false;
        return true;
    }

    /**
     * Function for answer question
     */
    public void answerQuestion()
    {
        try
        {
            Thread.sleep(4000);
            Mapper.find(domFile, "answerOptionA").click();
            Thread.sleep(2000);
            Mapper.find(domFile, "skipButton").click();
            Thread.sleep(2000);
            Mapper.find(domFile, "saveAndNextButton").click();
        }
        catch (Exception e)
        {
            Mapper.find(domFile, "skipButton").click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Mapper.find(domFile, "saveAndNextButton").click();
        }
    }

    /**
     * Function for finishing test
     */
    public void finishTest()
    {
        try {
            Mapper.find(domFile, "stopTestButton").click();
            Thread.sleep(2000);
            Mapper.find(domFile, "submitTestButton").click();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Function for Resuming test
     */
    public void resumeTest()
    {
        try {
            Mapper.find(domFile, "stopTestButton").click();
            Thread.sleep(2000);
            Mapper.find(domFile, "resumeTestButton").click();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Function for validation of Report Overview Page
     */
    public boolean validateReportOverviewPage()
    {
        Mapper.find(domFile, "overviewTab").click();
        Mapper.waitForElementToBeVisible(domFile, "reportStats");
        if(!(Mapper.find(domFile, "reportStats").isDisplayed())
                || !(Mapper.find(domFile, "viewFullReport").isDisplayed())
                || !(Mapper.find(domFile, "quickInfo").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function to click View Report of Live test
     */
    public void clickViewReport()
    {
        try
        {
            if(Mapper.find(domFile, "startTestButton").getText().equals("Start Test"))
            startTestSeries();
            answerQuestion();
            finishTest();
            Mapper.find(domFile, "testSeriesOnLHS").click();
            Mapper.find(domFile, "viewReportButton").click();
        }
        catch (Exception e) {
            Mapper.find(domFile, "viewReportButton").click();
        }
    }

    /**
     * Function for clicking Full Report
     */
    public void clickViewFullReport()
    {
        Mapper.find(domFile, "viewFullReport").click();
    }

    /**
     * Function for validation of Upgrade prompt displayed
     */
    public boolean validateUpgradePromptDisplayed()
    {
        Mapper.waitForElementToBeVisible(domFile, "upgradeTitle");
        if(!(Mapper.find(domFile, "upgradeTitle").isDisplayed())
                || !(Mapper.find(domFile, "upgradeDescription").isDisplayed())
                || !(Mapper.find(domFile, "upgradeDescriptionBox").isDisplayed())
                || !(Mapper.find(domFile, "upgradeNowButton")).isDisplayed())
            return false;
        return true;
    }

    /**
     * Function for validation of Full report displayed to upgraded user
     */
    public boolean validateFullReportShowedToUpgradedUser()
    {
        List<WebElement> chapterwiseReportItems = Mapper.finds(domFile, "chapterwiseReportItems");
        if(!(Mapper.find(domFile, "reportStats").isDisplayed())
                || !(Mapper.find(domFile, "scoreDistribution").isDisplayed())
                || !(Mapper.find(domFile, "detailedReport").isDisplayed())
                || !(Mapper.find(domFile, "chapterwiseReport").isDisplayed())
                || !(Mapper.find(domFile, "accuracyGraph").isDisplayed())
                || !(chapterwiseReportItems.size() > 0))
            return false;
        return true;
    }

    /**
     * Function for validation of live test details
     */
    public boolean validateLiveTestDetails()
    {
        if(!(Mapper.find(domFile, "liveTestDetails").isDisplayed())
                || !(Mapper.find(domFile, "liveTestName").isDisplayed())
                || !(Mapper.find(domFile, "liveTestStartButton").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for clicking past test
     */
    public void clickPastTest()
    {
        List<WebElement> pastTestList = Mapper.finds(domFile, "pastTest");
        pastTestList.get(1).click();
    }

    /**
     * Function for clicking upcoming test
     */
    public void clickUpcomingTest()
    {
        List<WebElement> upcomingTestList = Mapper.finds(domFile, "upcomingTest");
        upcomingTestList.get(1).click();
    }

    /**
     * Function for clicking Attempted test
     */
    public void clickAttemptedTest()
    {
        Mapper.find(domFile, "attemptedTestTick").click();
    }

    /**
     * Function for validation of upcoming tests
     */
    public boolean validateUpcomingTestPrompt()
    {
        Mapper.waitForElementToBeVisible(domFile, "upcomingTestName");
        if(!(Mapper.find(domFile, "upcomingTestName").isDisplayed())
                || !(Mapper.find(domFile, "upcomingTestDate").isDisplayed())
                || !(Mapper.find(domFile, "upcomingTestDuration").isDisplayed())
                || !(Mapper.find(domFile, "upcomingTestSyllabus").isDisplayed())
                || !(Mapper.find(domFile, "upcomingTestStartTime").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for validation of upcoming tests
     */
    public boolean validateAttemptedTestPrompt()
    {
        Mapper.waitForElementToBeVisible(domFile, "attemptedTestName");
        if(!(Mapper.find(domFile, "attemptedTestName").isDisplayed())
                || !(Mapper.find(domFile, "attemptedTestDate").isDisplayed())
                || !(Mapper.find(domFile, "attemptedTestRightSideTick").isDisplayed())
                || !(Mapper.find(domFile, "attemptedTestCloseButton").isDisplayed())
                || !(Mapper.find(domFile, "attemptedTestViewReportButton").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for clicking Answer Tab
     */
    public void clickOnAnswerKey()
    {
        Mapper.find(domFile, "answerKeyTab").click();
    }

    /**
     * Function for validations of Answer key for Upgraded User
     */
    public boolean validateAnswerKeyForUpgradedUser()
    {
        Mapper.waitForElementToBeVisible(domFile, "answerKeySubjectName");
        if(!(Mapper.find(domFile, "answerKeySubjectName").isDisplayed())
                || !(Mapper.find(domFile, "answerKeyQuestionSolution").isDisplayed())
                || !(Mapper.find(domFile, "askADoubt").isDisplayed())
                || !(Mapper.find(domFile, "solutionOption").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for applying Chemistry Subject Filter
     */
    public void applyChemistryFilter()
    {
        List<WebElement> subjectFilterList = Mapper.finds(domFile, "chemistrySubjectFilter");
        subjectFilterList.get(1).click();
    }

    /**
     * Function for validations of Answer key after filter application
     */
    public boolean validateAnswerKeyAfterFilter()
    {
        if(!(Mapper.find(domFile, "answerKeySubjectName").isDisplayed())
                || !(Mapper.find(domFile, "answerKeyQuestionSolution").isDisplayed())
                || !(Mapper.find(domFile, "askADoubt").isDisplayed())
                || !(Mapper.find(domFile, "hintOption").isDisplayed())
                || !(Mapper.find(domFile, "solutionOption").isDisplayed()))
            return false;
        return true;
    }
}
