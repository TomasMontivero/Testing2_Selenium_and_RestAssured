package com.selenium.mercadolibre.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    By searchBox = By.id("cb1-edit");
    By searchButton = By.className("nav-search-btn");

    public void search(String searchParameter) {
        sendKeys(searchBox, searchParameter);
    }

    public void click() {
        click(searchButton);

    }

}
