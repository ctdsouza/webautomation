package com.toppr.web.upgrade;

import com.toppr.utils.Mapper;
import com.toppr.web.PageBase;

import org.openqa.selenium.WebElement;

import java.util.List;

import static com.toppr.utils.PropertyReader.getProperties;

/**
 * Created by cliforddsouza on 01-Mar-2016.
 */
public class Upgrade extends PageBase
{
    private static final String domFile = getProperties().get("UPGRADE_DOM_FILE");
    public Upgrade()
    {
        super(domFile);
    }


    /**
     * Function for selecting Advanced Package
     */
    public void selectAdvancedPackage()
    {
        Mapper.waitForElementToBeVisible(domFile, "packageList");
        //Mapper.scrollElementIntoView(domFile, "advancedPackageSelect");
        //Mapper.waitForElementToBeVisible(domFile, "advancedPackageSelect");
        List<WebElement> packages = Mapper.finds(domFile, "packageList");
        packages.get(2).click();
        //Mapper.find(domFile, "advancedPackageSelect").click();
    }

    /**
     * Function for selecting Class X and JEE package
     */
    public void selectClassXAndJeePackage()
    {
        Mapper.waitForElementToBeVisible(domFile, "packageList");
        List<WebElement> packages = Mapper.finds(domFile, "packageList");
        packages.get(6).click();
        //Mapper.find(domFile, "classXAndJeePackage").click();
    }

    /**
     * Function for selecting Ultimate Package
     */
    public void selectUltimatePackage()
    {
        Mapper.find(domFile, "ultimatePackageSelect").click();
    }


    /**
     * Function for selecting Pay Online
     */
    public void clickPayOnline()
    {
        List<WebElement> paymentOptions = Mapper.finds(domFile, "packageList");
        paymentOptions.get(0).click();
        //Mapper.find(domFile, "paymentOptions").click();
    }

    /**
     * Function for selecting PayTm Wallet
     */
    public void clickPayTmWallet()
    {
        List<WebElement> paymentOptions = Mapper.finds(domFile, "packageList");
        paymentOptions.get(2).click();
        //Mapper.find(domFile, "paytmWalletOption").click();
    }

    /**
     * Function for selecting EMI Option
     */
    public void clickEMIOption()
    {
        List<WebElement> paymentOptions = Mapper.finds(domFile, "packageList");
        paymentOptions.get(3).click();
        //Mapper.find(domFile, "payEMIOption").click();
    }

    /**
     * Function for selecting cash pickup
     */
    public void clickCashPickUp()
    {
        List<WebElement> paymentOptions = Mapper.finds(domFile, "packageList");
        paymentOptions.get(4).click();
        //Mapper.find(domFile, "cashPickUpOption").click();
    }

    /**
     * Function for clicking PAY button
     */
    public void clickPayButton()
    {
        try{
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Mapper.waitForElementToBeVisible(domFile, "payButton");
        Mapper.find(domFile, "payButton").click();
    }

    /**
     * function for validation of Order Summary
     */
    public boolean validateOrderSummary(String packageName, String validity, String packagePrice)
    {
        Mapper.waitForElementToBeVisible(domFile, "orderSummary");
        System.out.println(Mapper.find(domFile, "orderSummaryPackageName").getText());
        System.out.println(Mapper.find(domFile, "orderSummaryPackagePrice").getText());
        System.out.println(Mapper.find(domFile, "orderSummaryPackageValidity").getText());
        if(!(Mapper.find(domFile, "orderSummary").isDisplayed())
                || !(Mapper.find(domFile, "orderSummaryPackageName").getText().equals(packageName))
                || !(Mapper.find(domFile, "orderSummaryPackagePrice").getText().equals("Rs. "+packagePrice))
                || !(Mapper.find(domFile, "orderSummaryPackageValidity").getText().equals("Upto June "+validity)))
            return false;
        return true;
    }

    /**
     * Function for validation of Billing details values
     */
    public boolean validateBillingDetailsValues(String name, String email, String phone)
    {
        Mapper.find(domFile,"viewBillingDetails").click();
        List<WebElement> billingDetails = Mapper.finds(domFile, "billingDetailsList");

        if(!(billingDetails.get(0).getText().contains(name))
                || !(billingDetails.get(1).getText().equals(email))
                || !(billingDetails.get(2).getText().equals(phone)))
            return false;
        Mapper.find(domFile,"closeBillingDetails").click();
        return true;
    }

    /**
     * Function for validation for PayU payment gateway page
     */
    public boolean validatePayUPaymentPage()
    {
        Mapper.waitForElementToBeVisible(domFile, "payUPageErrorMessage");
        if(!(Mapper.find(domFile, "payUPageErrorMessage").isDisplayed()))
            return false;
        return true;
    }

    /**
     * Function for clicking Proceed Button
     */
    public void clickProceedButton()
    {
        Mapper.find(domFile, "proceedButton").click();
    }

    /**
     * Function for validation of Upgrade page for IIT student
     */
    public boolean validateUpgradePageForJEE()
    {
        Mapper.waitForElementToBeVisible(domFile, "targetYear");
        List<WebElement> packageBanner = Mapper.finds(domFile, "packageBanner");
        if(!(Mapper.find(domFile, "targetYear").isDisplayed())
                || (packageBanner.size() != 12))
            return false;
        return true;
    }

    /**
     * Function for validation of Upgrade page for PMT student
     */
    public boolean validateUpgradePageForPMT()
    {
        Mapper.waitForElementToBeVisible(domFile, "targetYear");
        List<WebElement> packageBanner = Mapper.finds(domFile, "packageBanner");
        if(!(Mapper.find(domFile, "targetYear").isDisplayed())
                || (packageBanner.size() != 12))
            return false;
        return true;
    }

    /**
     * Function for validation of JEE packages sell prices
     */
    public boolean validateJEEPackageSellPrices(String litePackageSellPrice,
                                                String basicPackageSellPrice,
                                                String advancedPackageSellPrice,
                                                String masterPackageSellPrice,
                                                String ultimatePackageSellPrice)
    {
        List<WebElement> packageSellPrice = Mapper.finds(domFile, "packageSellPrice");
        if(!(packageSellPrice.get(0).getText().equals(litePackageSellPrice))
                || !(packageSellPrice.get(1).getText().equals(basicPackageSellPrice))
                || !(packageSellPrice.get(2).getText().equals(advancedPackageSellPrice))
                || !(packageSellPrice.get(3).getText().equals(ultimatePackageSellPrice)))
            return false;
        return true;
    }
}
