package com.selenium.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage{

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    By firstName = By.id("customer.firstName");
    By lastName = By.id("customer.lastName");
    By addressStreet = By.id("customer.address.street");
    By city = By.id("customer.address.city");
    By state = By.id("customer.address.state");
    By zipCode = By.id("customer.address.zipCode");
    By phone = By.id("customer.phoneNumber");
    By ssn = By.id("customer.ssn");
    By userName = By.id("customer.username");
    By password = By.id("customer.password");
    By passwordConfirm = By.id("repeatedPassword");
    By registerButton = By.xpath("//*[@value='Register']");
    By successMessage = By.xpath("//*[@id='rightPanel']//p");

    public void fillRegisterFormRandomUser() {
        sendKeys(firstName, "ThisIsMyName");
        sendKeys(lastName, "ThisIsMyLastName");
        sendKeys(addressStreet, "MyStreet");
        sendKeys(city, "MyCity");
        sendKeys(state, "MyState");
        sendKeys(zipCode, "1234");
        sendKeys(phone, "12345678");
        sendKeys(ssn,"1234567890");
        sendKeys(userName, "Username" + (int)(Math.random()*100000));
        sendKeys(password, "password");
        sendKeys(passwordConfirm, "password");
    }

    public void fillRegisterFormFixedUser() {
        sendKeys(firstName, "ThisIsMyName");
        sendKeys(lastName, "ThisIsMyLastName");
        sendKeys(addressStreet, "MyStreet");
        sendKeys(city, "MyCity");
        sendKeys(state, "MyState");
        sendKeys(zipCode, "1234");
        sendKeys(phone, "12345678");
        sendKeys(ssn,"1234567890");
        sendKeys(userName, "username1234");
        sendKeys(password, "password");
        sendKeys(passwordConfirm, "password");
    }

    public void submitForm() {
        click(registerButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }



}
