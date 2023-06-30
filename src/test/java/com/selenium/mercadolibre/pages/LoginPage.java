package com.selenium.mercadolibre.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{


    public LoginPage(WebDriver driver) {
        super(driver);
    }


    By message = By.className("center-card__title");


    public String getLoginMessage() {
        return getText(message);
    }


}
