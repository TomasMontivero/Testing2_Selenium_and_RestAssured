package com.selenium.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage extends BasePage{

    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    By newAccount = By.xpath("//a[contains(text(), \"Open New Account\")]");
    By accountsOverview = By.xpath("//a[contains(text(), \"Accounts Overview\")]");
    By transferFunds = By.xpath("//a[contains(text(), \"Transfer Funds\")]");
    By disclaimerText = By.xpath("//*[@id='accountTable']//*[contains(text(), \"*Balance includes deposits that may be subject to holds\")]");
    By accountLink = By.xpath("//*[@id='accountTable']//a");
    By accountDetailsText = By.className("title");
    By activityPeriodMenu = By.id("month");
    By getActivityPeriodOption = By.xpath("//*[@id='month']//option[@value='All']");
    By transactionTypeMenu = By.id("transactionType");
    By getTransactionTypeOption = By.xpath("//*[@id='transactionType']//option[@value='All']");
    By submitButton = By.xpath("//input[@value='Go']");



    public void goToNewAccount() {
        click(newAccount);
    }

    public void goToTransferFunds() {
        click(transferFunds);
    }

    public void goToAccountsOverview() {
        click(accountsOverview);
    }

    public String getDisclaimer() {
        return getText(disclaimerText);
    }

    public void selectAccount() {
        click(accountLink);
    }
    public String getAccountDetailsText() {
        return getText(accountDetailsText);
    }

    public void selectActivityPeriod() {
        click(activityPeriodMenu);
        click(getActivityPeriodOption);
    }

    public void selectTransactionType() {
        click(transactionTypeMenu);
        click(getTransactionTypeOption);
    }

    public void submit() {
        waitTime();
        click(submitButton);
    }

}
