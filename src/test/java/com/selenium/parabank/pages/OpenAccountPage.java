package com.selenium.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenAccountPage extends BasePage{

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    By accountTypeMenu = By.id("type");
    By accountTypeSavings = By.xpath("//*[@id='type']//option[@value='1']");
    By openAccountButton = By.xpath("//*[@value='Open New Account']");
    By successMessage = By.xpath("//*[@id='rightPanel']//p[contains(text(),'Congratulations, your account is now open.')]");

    public void selectSavings() {
        click(accountTypeMenu);
        click(accountTypeSavings);
    }

    public void submitNewAccount() {
        waitTime();
        click(openAccountButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

}
