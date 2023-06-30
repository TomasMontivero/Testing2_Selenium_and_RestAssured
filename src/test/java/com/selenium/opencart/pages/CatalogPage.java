package com.selenium.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage {

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    By addToCart = By.xpath("//*[@class='product-thumb']//*[@type='button'][1]");
    By successMessage = By.xpath("//*[text()=' Success: You have added ']");

    public void clickAddToCart() {
        click(addToCart);
    }

    public String getSuccessMessagge() {
        return getText(successMessage);
    }

}
