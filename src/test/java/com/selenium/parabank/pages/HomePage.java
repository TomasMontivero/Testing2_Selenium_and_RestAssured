package com.selenium.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    By registerLink = By.xpath("//a[contains(text(), \"Register\")]");
    By username = By.name("username");
    By password = By.name("password");
    By loginButton = By.xpath("//*[@value='Log In']");

    public void goToHome() {
        get("https://parabank.parasoft.com/parabank/index.htm");
    }

    public void goToRegister() {
        click(registerLink);
    }

    public void login() {
        sendKeys(username, "username1234");
        sendKeys(password, "password");
        click(loginButton);
    }

}
