package com.selenium.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    By myAccount = By.xpath("//*[@title='My Account']");
    By register = By.linkText("Register");
    By searchField = By.xpath("//*[@id='search']//*[@name='search']");
    By searchButton = By.xpath("//*[@id='search']//*[@type='button']");

    public void goToHome() {
        get("https://opencart.abstracta.us/index.php?route=common/home");
    }

    public void clickMyAccount() {
        click(myAccount);
    }

    public void clickRegister() {
        click(register);
    }

    public void inputSearch(String busqueda) {
        sendKeys(searchField,busqueda);
    }

    public void clickSearch() {
        click(searchButton);
    }

}
