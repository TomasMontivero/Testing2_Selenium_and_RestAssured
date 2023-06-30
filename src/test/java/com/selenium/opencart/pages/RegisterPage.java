package com.selenium.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    By firstName = By.id("input-firstname");
    By lastName = By.id("input-lastname");
    By email = By.id("input-email");
    By telephone = By.id("input-telephone");
    By password = By.id("input-password");
    By passwordConfirm = By.id("input-confirm");
    By newsLetterNo = By.xpath("//*[@name='newsletter' and @value='0']");
    By privacyPolicy = By.name("agree");
    By submitForm = By.xpath("//*[@value='Continue']");
    By registrationConfirmation = By.xpath("//*[text()='Congratulations! Your new account has been successfully created!']");


    public void inputFirstName(String firstName) {
        sendKeys(this.firstName,firstName);
    }

    public void inputLastName(String lastName) {
        sendKeys(this.lastName,lastName);
    }

    public void inputEmail(String email) {
        sendKeys(this.email,email);
    }

    public void inputTelephone(String telephone) {
        sendKeys(this.telephone,telephone);
    }

    public void inputPassword(String password) {
        sendKeys(this.password,password);
    }

    public void inputPasswordConfirm(String passwordConfirm) {
        sendKeys(this.passwordConfirm,passwordConfirm);
    }

    public void clickNewsletterNo() {
        click(newsLetterNo);
    }

    public void clickPrivacyPolicyAgree() {
        click(privacyPolicy);
    }

    public void clickContinue() {
        click(submitForm);
    }

    public String getRegistrationConfirmation() {
        return getText(registrationConfirmation);
    }


}
