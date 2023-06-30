package com.selenium.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransferFundsPage extends BasePage{

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    By ammount = By.id("amount");
    By transferFundsText = By.className("title");
    By fromAccountMenu = By.id("fromAccountId");
    By fromAccountOption = By.xpath("//*[@id='fromAccountId']//option");
    By toAccountMenu = By.id("toAccountId");
    By toAccountOption = By.xpath("(//*[@id='fromAccountId']//option)[2]");
    By transferButton = By.xpath("//*[@value='Transfer']");



    public String getTransferFundsText() {
        return getText(transferFundsText);
    }

    public void fillAmmount() {
        sendKeys(ammount, "100");
    }

    public void selectFromAccount() {
        click(fromAccountMenu);
        click(fromAccountOption);
    }

    public void selectToAccount() {
        click(toAccountMenu);
        click(toAccountOption);
    }

    public void submitTransfer() {
        waitTime();
        click(transferButton);
    }

    public String getTransferSuccessMessage() {
        waitTime();
        return getText(transferFundsText);
    }

    public void validateMenuOption() {
        int retries = 0;
        boolean success = false;
        while (retries<5 && !success) {
            try {
                click(toAccountOption);
                success = true;
            } catch(Exception e) {
                refresh();
            }
        }
    }

}
